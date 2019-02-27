package com.seu.koo.service;

import java.util.List;

import com.seu.koo.po.Item;

public interface DeliverService {
	public List<Item> getListbyId(int waiterId) throws Exception;
	public List<Item> getDeliverList() throws Exception;
	public void acceptDeliver(int waiterId) throws Exception;
}
