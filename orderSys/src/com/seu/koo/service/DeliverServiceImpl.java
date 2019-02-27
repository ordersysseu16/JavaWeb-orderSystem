package com.seu.koo.service;

import java.util.LinkedList;
import java.util.List;

import com.seu.koo.po.Item;
import com.seu.koo.po.ItemMap;

public class DeliverServiceImpl implements DeliverService {

	@Override
	public List<Item> getListbyId(int waiterId) throws Exception {
		ItemMap map = ItemMap.getInstance();//获取ItemMap单例
		return map.getList(waiterId);
	}

	@Override
	public List<Item> getDeliverList() throws Exception {
		ItemMap map = ItemMap.getInstance();//获取ItemMap单例
		return map.getList(0);
	}

	//从闲置队列头部摘取传菜信息
	@Override
	public void acceptDeliver(int waiterId) throws Exception {
		ItemMap map = ItemMap.getInstance();//获取ItemMap单例
		LinkedList<Item> list = map.getList(waiterId);
		Item item = map.getList(0).pollFirst();
		list.add(item);
	}
	
}
