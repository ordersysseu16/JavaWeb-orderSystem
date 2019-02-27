package com.seu.koo.po;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.seu.koo.service.StaffService;
import com.seu.koo.util.SpringInit;

public class ItemMap {

	//非Controller类注入服务
	StaffService staffService = (StaffService)SpringInit.getApplicationContext().getBean("StaffService");

	//内部静态类单例
    private static class SingletonItemMap {

        private static final ItemMap INSTANCE = new ItemMap();

    }

    private ItemMap (){
    	map.put(0, new LinkedList<Item>());//初始化闲置队列
    	
    	//获取职工信息
    	try{
    		List<Staff> list = staffService.findAllStaffs();
    		Staff[] array = (Staff[])list.toArray(new Staff[0]);
    		//为每个职工初始化任务队列
    		for(int i = 0; i < array.length; i++) {
    			System.out.println(array[i].getId());
    			map.put(array[i].getId(), new LinkedList<Item>());
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public static final ItemMap getInstance() {

        return SingletonItemMap.INSTANCE;

    }

    //key为员工id，对应value为该员工任务列表；若key=0，对应value为介于厨师与服务员间的待传菜队列
    private static Map<Integer, LinkedList<Item>> map = new HashMap<Integer, LinkedList<Item>>();
    
    public void put(Integer key, LinkedList<Item> value) {
    	map.put(key, value);
    }
     
    //获取id=key的职工任务队列，key==0时获取闲置（待传菜）任务队列
    public LinkedList<Item> getList(Integer key) {
    	return map.get(key);
    }
}