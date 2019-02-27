package com.seu.koo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.seu.koo.mapper.WaiterMapper;
import com.seu.koo.po.Waiter;

public class WaiterServiceImpl implements WaiterService{
	@Autowired
    private WaiterMapper waiterMapper; //用AutoWired注入DB层
	
    @Transactional(readOnly=true) //数据库的读取方式为：只读
	@Override
	public Waiter queryWaiterByAccount(Waiter waiter) throws Exception {
    	return waiterMapper.queryWaiterByAccount(waiter);
	}

	@Override
	public void updateWaiterByAccount(Waiter waiter) throws Exception {
		// TODO Auto-generated method stub
	}
}
