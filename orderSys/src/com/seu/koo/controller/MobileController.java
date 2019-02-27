package com.seu.koo.controller;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seu.koo.po.Item;
import com.seu.koo.po.Waiter;
import com.seu.koo.service.DeliverService;
import com.seu.koo.service.WaiterService;
import com.seu.koo.util.DateJsonValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
public class MobileController {
	@Autowired
	WaiterService waiterService;
	@Autowired
	DeliverService deliverService;
	
	@RequestMapping(value="/mobile/login", method={RequestMethod.POST})
	@ResponseBody
	public String login(HttpServletRequest request) throws Exception {
		Waiter waiterToVerity = new Waiter();
		waiterToVerity.setAccount(request.getParameter("account"));
		waiterToVerity.setPassword(request.getParameter("password"));
		Waiter waiter = waiterService.queryWaiterByAccount(waiterToVerity);
		if(waiter != null) {
			return String.valueOf(waiter.getId());//返回id，登陆成功
		} else {
			return "-1";//返回0，账号不存在或密码错误
		}
	}
	
	@RequestMapping(value="/deliver", method={RequestMethod.GET}, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getPublicDeliverList() throws Exception {
		List<Item> list = new LinkedList<>();
		list = deliverService.getListbyId(0);
		//格式化Timestamp时间戳
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	
	@RequestMapping(value="/deliver/{id}", method={RequestMethod.GET}, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getDeliverList(@PathVariable String id) throws Exception {
		List<Item> list = new LinkedList<>();
		list = deliverService.getListbyId(Integer.parseInt(id));
		//格式化Timestamp时间戳
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	
	@RequestMapping(value="/deliver/{id}", method={RequestMethod.POST})
	@ResponseBody
	public String acceptDeliver(@PathVariable String id) throws Exception {
		deliverService.acceptDeliver(Integer.parseInt(id));
		return "0";//返回0，接受成功
	}
}
