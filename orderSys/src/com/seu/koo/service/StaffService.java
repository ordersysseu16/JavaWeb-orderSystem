package com.seu.koo.service;

import java.util.List;

import com.seu.koo.po.Staff;

public interface StaffService {
	public List<Staff> findAllStaffs() throws Exception;
	
	public List<Staff> findAllCooks() throws Exception;
	
	public void updateStaffById (Staff staff) throws Exception;
	
	public void deleteStaffById (int id) throws Exception;

	public void createStaff(Staff staff) throws Exception;
}
