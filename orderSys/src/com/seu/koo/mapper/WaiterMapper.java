package com.seu.koo.mapper;

import com.seu.koo.po.Waiter;

public interface WaiterMapper {
	/**
	 * 查询用户对象
	 */
	public Waiter queryWaiterByAccount(Waiter waiter);
	/**
	 * 插入用户对象
	 */
	public int insertWaiter(Waiter waiter);
	/**
	 * 更新用户对象
	 */
	public int updateWaiterByAccount(Waiter waiter);
	/**
	 * 删除用户对象
	 */
	public int deleteWaiterByAccount(String account);
}
