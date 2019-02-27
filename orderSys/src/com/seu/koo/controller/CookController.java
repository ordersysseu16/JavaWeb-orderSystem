package com.seu.koo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seu.koo.po.Item;
import com.seu.koo.service.CookService;
import com.seu.koo.util.DateJsonValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
public class CookController {
	@Autowired
	CookService cookService;
	
	//访问后厨界面
	@RequestMapping(value="/cook", method={RequestMethod.GET})
	public ModelAndView showCookPage() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/../../cookPage");
		return modelAndView;
	}
	
	//获取烹饪列表
	@RequestMapping(value="/cookList/{id}", method={RequestMethod.GET}, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getCookList(@PathVariable String id) throws Exception {
		List<Item> list = new ArrayList<>();
		list = cookService.getListById(Integer.parseInt(id));
		//格式化Timestamp时间戳
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
				
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	
	//提交成菜信息
	@RequestMapping(value="/cookList/{id}", method={RequestMethod.PUT})
	@ResponseBody
	public String finishCook(@PathVariable String id) throws Exception {
		cookService.finishCook(Integer.parseInt(id));
		return "success";
	}
}
