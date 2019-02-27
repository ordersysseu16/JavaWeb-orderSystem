package com.seu.koo.mapper;

import java.util.List;

import com.seu.koo.po.Bill;

public interface BillMapper {
	/**
	 * 查询账单对象
	 */
	public Bill queryBillByNumber(int bill_number);
	/**
	 * 查询账单对象--返回多个--List<Bill>
	 */
	public List<Bill> queryBillList();
	/**
	 * 插入账单对象
	 */
	public int insertBill(Bill bill);
	/**
	 * 更新账单对象
	 */
	public int updateBill(Bill bill);
	/**
	 * 删除账单对象
	 */
	public int deleteBillByNumber(int bill_number);

}
