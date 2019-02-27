package com.seu.koo.service;

import com.seu.koo.po.Waiter;

public interface WaiterService {
	public Waiter queryWaiterByAccount (Waiter waiter) throws Exception;
	
	public void updateWaiterByAccount (Waiter waiter) throws Exception;
}
