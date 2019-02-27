package com.seu.koo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.seu.koo.mapper.DishMapper;
import com.seu.koo.mapper.StaffMapper;
import com.seu.koo.po.Item;
import com.seu.koo.po.ItemMap;
import com.seu.koo.po.Staff;

public class CookServiceImpl implements CookService {
	@Autowired
    private DishMapper dishMapper;//用AutoWired注入DB层
	@Autowired
	private StaffMapper staffMapper;//用AutoWired注入DB层
	
	@Transactional(readOnly=true) //数据库的读取方式为：只读
	@Override
	public int planItemList(Item item) throws Exception {
		//获取厨师信息
		List<Staff> cookList = staffMapper.queryCookList();
		Staff[] cookArray = (Staff[])cookList.toArray(new Staff[0]);
		
		//获取菜品信息
		int itemId = item.getDish_id();
		int time = Integer.parseInt(dishMapper.queryDishById(itemId).getTime()) * item.getNum();
		
		ItemMap map = ItemMap.getInstance();//获取ItemMap单例
		
		int resultCookId = 0;//分配结果
		int resultFinishTime = 0;//当前分配结果所需时间
		boolean validAssignment = false;//是否具有有效分配，避免不加急且无人擅长而直接丢弃
		
		//遍历厨师
		for(int i = 0; i < cookArray.length; i++) {
			//不加急且 （不擅长且有有效分配），跳过
			boolean goodAt = isGoodAt(cookArray[i], itemId);
			if((!item.isUrgent()) && ((!goodAt) && validAssignment)) {continue;}
			
			int currentId = cookArray[i].getId();
			//当前厨师列表为空，直接分配
			if(map.getList(currentId).size() == 0 && item.isUrgent()) {
				resultCookId = currentId;
				map.getList(currentId).add(item);
				return currentId;
			} else if(map.getList(currentId).size() == 0 && (!item.isUrgent())) {
				resultCookId = currentId;
				validAssignment = goodAt;
				continue;
			}
			//尝试分配
			int currentFinishTime = map.getList(currentId).getLast().getFinshTime();
			if((0 == i) || ((resultCookId == 0) && (resultFinishTime > currentFinishTime))) {
				resultCookId = currentId;
				resultFinishTime = currentFinishTime;
				validAssignment = goodAt;
			}
		}
		item.setFinshTime(resultFinishTime + time);
		map.getList(resultCookId).add(item);
		System.out.println(resultCookId);
		return resultCookId;
	} 
	
	private boolean isGoodAt(Staff cook, int itemId) {
		String[] cookDishes = cook.getCook().split("/");
		//遍历厨师擅长菜
		for(int j = 0; j < cookDishes.length; j++) {
			if(cookDishes[j].equals(String.valueOf(itemId))) {return true;}
		}
		return false;
	}
	
	//成菜从厨师列表中移除加入闲置队列，并更新列表中菜的预计成菜时间
	public void finishCook(int cookId) throws Exception {
		ItemMap map = ItemMap.getInstance();//获取ItemMap单例
		
		Item cookedItem = map.getList(cookId).pollFirst();
		cookedItem.setFinshTime(0);//成菜，设所需时间为0
		map.getList(0).add(cookedItem);
		
		//更新时间
		int time = Integer.parseInt(dishMapper.queryDishById(cookedItem.getDish_id()).getTime()) * cookedItem.getNum();
		List<Item> list = map.getList(cookId);
		for(Item item : list) {
			item.setFinshTime(item.getFinshTime() - time);
		}
	}

	@Override
	public List<Item> getListById(int cookId) throws Exception {
		ItemMap map = ItemMap.getInstance();//获取ItemMap单例
		return map.getList(cookId);
	}
}
