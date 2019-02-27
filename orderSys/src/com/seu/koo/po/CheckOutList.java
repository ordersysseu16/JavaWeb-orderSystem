package com.seu.koo.po;

import java.util.ArrayList;

public class CheckOutList {

	//内部静态类单例
    private static class SingletonCheckOutList {

        private static final CheckOutList INSTANCE = new CheckOutList();

    }

    private CheckOutList() {
    	//初始化指定桌数的ItemList
    	for(int i = 0; i <= tables; i++) {
    		checkOutList.add(new ArrayList<Item>());
    	}
    	System.out.println(checkOutList.size());
    }

    public static final CheckOutList getInstance() {

        return SingletonCheckOutList.INSTANCE;

    }
    
    //初始默认桌位数量,该值两倍设为扩容上限
    private final int tables = 50;
    
    //checkOutList索引为桌号，值为该桌当前Item的List
    private static ArrayList<ArrayList<Item>> checkOutList = new ArrayList<>();
    
    public ArrayList<Item> getItemList(int table) {
    	//索引越界时，判断是否扩容数组
    	if((table > checkOutList.size()) && (table <= 2 * tables)) {
    		for(int i = checkOutList.size() ; i <= table ; i++) {
                checkOutList.add(new ArrayList<Item>());
            }
    	} else if(table > 2 * tables) {
    		//TODO IndexOutOfBoundsException
		}
    	return checkOutList.get(table);
    }
    
    public void setItemList(int table, ArrayList<Item> list) {
		checkOutList.set(table, list);
	}
}
