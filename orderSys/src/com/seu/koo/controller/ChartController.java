package com.seu.koo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seu.koo.po.Bill;
import com.seu.koo.service.BillService;

@Controller
//@RequestMapping("/echart")
public class ChartController {
	@Autowired
	BillService billService;
	
	@RequestMapping(value = "/bar",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String bar() throws Exception {
        List<Bill> bills = billService.findAllBills();
        for(int i=0;i<bills.size();i++){
        	bills.get(i).setTimeNew(bills.get(i).getTime().toString());
        	bills.get(i).setTimeNew(new SimpleDateFormat("yyyy-MM-dd").format(bills.get(i).getTime()));
        }
      //将同天的账单金额合在一起
        List<Bill> billsnew=new ArrayList<Bill>();
        int j=1;//表示新一天的插入位置
        billsnew.add(0,bills.get(0));
        for(int i=1;i<bills.size();i++) {
        	if(bills.get(i).getTimeNew().equals(bills.get(i-1).getTimeNew())) {
        		billsnew.get(j-1).setPrice(bills.get(i).getPrice()+billsnew.get(j-1).getPrice());
        	}else {
        		billsnew.add(j,bills.get(i));
        		j++;
        	}
        }
        
        ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
        String json = mapper.writeValueAsString(billsnew);    //将list中的对象转换为Json格式的数组
        return json;
    }
	
}
