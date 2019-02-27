package com.seu.koo.mapper;

import com.seu.koo.po.Admin;

public interface AdminMapper {
	/**
	 * 查询用户对象
	 */
	public Admin queryAdminByAccount(Admin admin);
	/**
	 * 插入用户对象
	 */
	public int insertAdmin(Admin admin);
	/**
	 * 更新用户对象
	 */
	public int updateAdminByAccount(Admin admin);
	/**
	 * 删除用户对象
	 */
	public int deleteAdminByAccount(String account);
}
