package com.seu.koo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.seu.koo.po.Admin;
import com.seu.koo.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	//外部直接访问后台登录页
	@RequestMapping(value="", method={RequestMethod.GET})
	public ModelAndView goLogin() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/../../adminLogin");
		return modelAndView;
	}
	
	//登陆请求
	@RequestMapping(value="/login", method={RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Admin adminToVerity = new Admin();
		adminToVerity.setAccount(request.getParameter("account"));
		adminToVerity.setPassword(request.getParameter("password"));
		Admin admin = adminService.queryAdminByAccount(adminToVerity);
		if(admin != null) {
			modelAndView.setViewName("/admin/masterPage");
			modelAndView.addObject(admin);
			//TODO save in session
			return modelAndView;
		}
		else {
			modelAndView.setViewName("/../../adminLogin");
			modelAndView.addObject("alert", "账号或密码错误,请重试");
		}
		return modelAndView;
	}
	
	//退出登录
	@RequestMapping(value="/logout", method={RequestMethod.GET})
	public ModelAndView logout(Admin adminOnline) throws Exception {
		//TODO remove from session
		return new ModelAndView("/../../adminLogin"); 
	}
	
	//修改密码
	@RequestMapping(value="/adminInfo", method={RequestMethod.PUT})
	public String updatePassword(HttpServletRequest request) throws Exception {
		Admin adminToUpdate = new Admin();
		adminToUpdate.setAccount(request.getParameter("account"));
		adminToUpdate.setPassword(request.getParameter("password_old"));
		if(adminService.queryAdminByAccount(adminToUpdate) != null) {
			adminToUpdate.setPassword(request.getParameter("password_new"));
			adminService.updateAdminByAccount(adminToUpdate);
			return "success";
		}
		else {
			return "error";
		}
	}
	
	//管理员主页跳转
	@RequestMapping(value="/masterPage", method={RequestMethod.GET})
	public ModelAndView goMasterPage() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/masterPage");
		return modelAndView;
	}
	
	//获取各类功能页
	@RequestMapping(value="/functions/{page}", method={RequestMethod.GET})
	public String getFuctionsPage(@PathVariable String page) throws Exception {
		String path = "/admin/" + page;
		return path;
	}
}
