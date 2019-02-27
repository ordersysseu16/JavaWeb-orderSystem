package com.seu.koo.service;

import com.seu.koo.po.Admin;

public interface AdminService {
	public Admin queryAdminByAccount (Admin admin) throws Exception;
	
	public void updateAdminByAccount (Admin admin) throws Exception;
}
