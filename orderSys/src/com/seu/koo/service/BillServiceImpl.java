package com.seu.koo.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.seu.koo.mapper.BillMapper;
import com.seu.koo.mapper.DishMapper;
import com.seu.koo.po.Bill;
import com.seu.koo.po.CheckOutList;
import com.seu.koo.po.Dish;
import com.seu.koo.po.Item;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BillServiceImpl implements BillService {
	
	DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Autowired
    private BillMapper billMapper; //用AutoWired注入DB层
	@Autowired
	private DishMapper dishMapper; //用AutoWired注入DB层

    @Transactional(readOnly=true) //数据库的读取方式为：只读
	@Override
	public List<Bill> findAllBills() throws Exception {
    	List<Bill> list = new ArrayList<>();
		list = billMapper.queryBillList();
		return list;
	}
    
    @Override
	public boolean submitBill(int table) throws Exception {
		CheckOutList arrayList = CheckOutList.getInstance();//获取CheckOutList单例
		
		ArrayList<Item> array = arrayList.getItemList(table);
		//判断该桌号是否已有未结算订单
		return array.size() == 0;
	}

	@Override
	public boolean submitBill(ArrayList<Item> itemList, int table) throws Exception {
		CheckOutList arrayList = CheckOutList.getInstance();//获取CheckOutList单例

		ArrayList<Item> array = arrayList.getItemList(table);
		//判断该桌号是否已有未结算订单
		if(array.size() == 0){
			array.addAll(itemList);
			arrayList.setItemList(table, itemList);
			return true;
		}
		return false;
	}
	
	//计算订单总价
	public double totalPrice(int tableId) throws Exception {
		CheckOutList arrayList = CheckOutList.getInstance();//获取CheckOutList单例
		ArrayList<Item> array = arrayList.getItemList(tableId);
		
		double price = 0;
		for(int i = 0; i < array.size(); i++) {
			Item item = array.get(i);
			Dish dish = dishMapper.queryDishById(item.getDish_id());
			price += dish.getPrice() * item.getNum();
		}
		
		return price;
	}
	
	//结算订单，写入数据库账单表
	@Override
	public void checkOutBill(int tableId) throws Exception {
		CheckOutList arrayList = CheckOutList.getInstance();//获取CheckOutList单例
		ArrayList<Item> array = arrayList.getItemList(tableId);
		
		//生成不再改变的账单明细
		Bill completedBill = new Bill();
		completedBill.setTime(new Timestamp(System.currentTimeMillis()));//结算时间
		
		completedBill.setPrice(totalPrice(tableId));//总价
		
		//存储账单明细时忽略临时的（不必要的）Item属性，减少数据长度 2018.9.11新增
		JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"urgent","createTime","finshTime","table"});
		JSONArray jsonArray = JSONArray.fromObject(array, jsonConfig);
		System.out.println(jsonArray.toString());
		String list =  jsonArray.toString();
		completedBill.setList(list);//明细
		
		billMapper.insertBill(completedBill);//插入记录
		
		arrayList.setItemList(tableId, new ArrayList<Item>());
	}
}
