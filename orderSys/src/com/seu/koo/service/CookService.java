package com.seu.koo.service;

import java.util.List;

import com.seu.koo.po.Item;

public interface CookService {
	public List<Item> getListById(int cookId) throws Exception;
	public int planItemList(Item item) throws Exception;
	public void finishCook(int cookId) throws Exception;
}
