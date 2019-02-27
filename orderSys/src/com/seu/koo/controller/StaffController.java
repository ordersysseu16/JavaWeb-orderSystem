package com.seu.koo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seu.koo.po.Staff;
import com.seu.koo.service.StaffService;

import net.sf.json.JSONArray;

@Controller
public class StaffController {
	@Autowired
	StaffService staffService;

	//一次性获取职工列表
	@RequestMapping(value="/staffs", method={RequestMethod.GET}, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getStaffList() throws Exception {
		List<Staff> list = new ArrayList<>();
		list = staffService.findAllStaffs();
		//优化显示格式
		for(int i = 0; i < list.size(); i++) {
			Staff staff = list.get(i);
			String position = (staff.getCook() == null || staff.getCook().equals(""))?"服务员":"厨师";
			staff.setPosition(position);
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	
	//一次性获取厨师列表
	@RequestMapping(value="/cooks", method={RequestMethod.GET}, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getCooksList() throws Exception {
		List<Staff> list = new ArrayList<>();
		list = staffService.findAllCooks();
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	
	//创建新的员工记录
	@RequestMapping(value="/staff", method={RequestMethod.POST})
	@ResponseBody
	public String createNewStaff(HttpServletRequest request,@Validated Staff staff , BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {  
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMessage="";
            for(ObjectError  error:allErrors){
                errorMessage+=error.getDefaultMessage();
            }
            System.out.println(errorMessage);
            return errorMessage;
		}//返回错误信息
		System.out.println(staff.getId());
		staffService.createStaff(staff);
		return "success";
	}

	//编辑指定id的职工信息
	@RequestMapping(value="/staff/{id}", method={RequestMethod.POST})
	@ResponseBody
	public String changeStaffInfo(@PathVariable String id, HttpServletRequest request,@Validated Staff staff , BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {  

            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMessage="";
            for(ObjectError  error:allErrors){
                errorMessage+=error.getDefaultMessage();
            }     
            System.out.println(errorMessage);
            return errorMessage;
		}//返回错误信息
		staff.setId(Integer.parseInt(id));
		staffService.updateStaffById(staff);
		return "success";
	}

	//删除指定id的职工
	@RequestMapping(value="/staff/{id}", method={RequestMethod.DELETE})
	@ResponseBody
	public String deleteStaff(@PathVariable String id) throws Exception {
		int idToDelete = Integer.parseInt(id);
		staffService.deleteStaffById(idToDelete);
		return "success";
	}
}
