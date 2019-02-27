package com.seu.koo.service;

import java.util.ArrayList;
import java.util.List;

import com.seu.koo.po.Bill;
import com.seu.koo.po.Item;

public interface BillService {
	public List<Bill> findAllBills() throws Exception;
	
	public boolean submitBill(ArrayList<Item> itemList, int table) throws Exception;
	
	public double totalPrice(int tableId) throws Exception;
	
	public void checkOutBill(int tableId) throws Exception;

	boolean submitBill(int table) throws Exception;
}
