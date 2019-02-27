package com.seu.koo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.seu.koo.mapper.AdminMapper;
import com.seu.koo.po.Admin;

public class AdminServiceImpl implements AdminService{
	@Autowired
    private AdminMapper adminMapper; //用AutoWired注入DB层
	
    @Transactional(readOnly=true) //数据库的读取方式为：只读
	@Override
	public Admin queryAdminByAccount(Admin admin) throws Exception {
    	return adminMapper.queryAdminByAccount(admin);
	}

	@Override
	public void updateAdminByAccount(Admin admin) throws Exception {
		// TODO Auto-generated method stub
	}
}
