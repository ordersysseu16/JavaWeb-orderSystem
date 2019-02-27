package com.seu.koo.mapper;



import java.util.List;
import java.util.Map;

import com.seu.koo.po.Staff;;
 
public interface StaffMapper {
	//=======================XML版=======================
	/**
	 * 查询用户对象--返回User对象
	 */
	public Staff queryStaffById(int id);
	/**
	 * 查询用户对象--返回多个--List<Staff>
	 */
	public List<Staff> queryStaffList();
	/**
	 * 查询用户对象--返回厨师--List<Staff>
	 */
	public List<Staff> queryCookList();
	/**
	 * 查询用户对象--返回map
	 */
	public Map<String,Object> queryStaffnResultMap(int id);
	/**
	 * 查询用户对象--返回多个--List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryStaffResultListMap();
	/**
	 * 插入用户对象
	 */
	public int insertStaff(Staff staff);
	/**
	 * 更新用户对象
	 */
	public int updateStaff(Staff staff);
	/**
	 * 删除用户对象
	 */
	public int deleteStaffById(int id);
	

}