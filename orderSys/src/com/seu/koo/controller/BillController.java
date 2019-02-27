package com.seu.koo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seu.koo.po.Bill;
import com.seu.koo.po.Item;
import com.seu.koo.service.BillService;
import com.seu.koo.service.CookService;
import com.seu.koo.util.DateJsonValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
public class BillController {
	@Autowired
	BillService billService;
	@Autowired
	CookService cookService;

	//一次性获取账单列表
	@RequestMapping(value="/bills", method={RequestMethod.GET}, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getBillList() throws Exception {
		List<Bill> list = new ArrayList<>();
		list = billService.findAllBills();
		//格式化Timestamp时间戳
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		
		//属性list的字符串值转换为JsonArray对象
		for(int i = 0; i < list.size(); i++) {
			Bill bill = list.get(i);
			String detailList = bill.getList();
			bill.setList_json(JSONArray.fromObject(detailList));
		}
		JSONArray jsonArray = JSONArray.fromObject(list,config);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	
	//订单提交，分配烹饪
	@RequestMapping(value="/order", method={RequestMethod.POST}, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String submitBill(HttpServletRequest request) throws Exception {
		String table=request.getParameter("table");
	   	String meal=request.getParameter("meal");
//	   	double price=Double.parseDouble(request.getParameter("price"));
	   	boolean isUrgent=Boolean.parseBoolean(request.getParameter("emergency"));
	   	
	   	String[] a = meal.split(",");
	   	String[][] information_items=new String[a.length][];
	    for(int i=0; i<a.length; i++){  
	  	 information_items[i] = a[i].split(" ");         
	    }
	    
	    if(billService.submitBill(Integer.parseInt(table))) {
	    	ArrayList<Item> itemList = new ArrayList<Item>(information_items.length);
	    	for(int i = 0; i < information_items.length; i++) {
	    		Item item = new Item();
	    		item.setDish_id(Integer.parseInt(information_items[i][0]));
	    		item.setDish_name(information_items[i][1]);
	    		item.setNum(Integer.parseInt(information_items[i][2]));
	    		item.setTable(Integer.parseInt(table));
	    		item.setUrgent(isUrgent);
	    		cookService.planItemList(item);//进行分配
	    		itemList.add(item);
	    	}
	    	billService.submitBill(new ArrayList<Item>(itemList), Integer.parseInt(table));
	    	System.out.println("0");
	    	return "0";//返回0表示成功
	    } else {
	    	System.out.println("1");
			return "1";//返回1表示失败
		}
	}
	
	//获取订单信息
	@ResponseBody
	@RequestMapping(value="/bill/{table}", method={RequestMethod.GET})
	public String getOrder(@PathVariable String table) throws Exception {
		return String.valueOf(billService.totalPrice(Integer.parseInt(table)));
	}
	
	//结算完成
	@RequestMapping(value="/bill/{table}", method={RequestMethod.POST})
	public void checkOutBill(@PathVariable String table) throws Exception {
		billService.checkOutBill(Integer.parseInt(table));
	}
}
