package com.seu.koo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.seu.koo.mapper.StaffMapper;
import com.seu.koo.po.Staff;

public class StaffServiceImpl implements StaffService {
	@Autowired
    private StaffMapper staffMapper; //用AutoWired注入DB层

    @Transactional(readOnly=true) //数据库的读取方式为：只读
	@Override
	public List<Staff> findAllStaffs() throws Exception {
    	List<Staff> list = new ArrayList<>();
		list = staffMapper.queryStaffList();
		return list;
	}
    
    @Override
    public List<Staff> findAllCooks() throws Exception {
    	List<Staff> list = new ArrayList<>();
		list = staffMapper.queryCookList();
    	return list;
    }

	@Override
	public void createStaff(Staff staff) throws Exception {
		if(staff.getCook() == null){
			staff.setCook("");
		}
		staffMapper.insertStaff(staff);
	}
	
	@Override
	public void updateStaffById(Staff staff) throws Exception {
		int id = staff.getId();
		//值为null则不变
		if(staff.getName() == null){
			staff.setName(staffMapper.queryStaffById(id).getName());
		}
		if(staff.getCook() == null){
			staff.setCook(staffMapper.queryStaffById(id).getCook());
		}
		if(staff.getPermission() == null){
			staff.setPermission(staffMapper.queryStaffById(id).getPermission());
		}
		//更新表
		staffMapper.updateStaff(staff);
	}

	@Override
	public void deleteStaffById(int id) throws Exception {
		staffMapper.deleteStaffById(id);
	}
}
