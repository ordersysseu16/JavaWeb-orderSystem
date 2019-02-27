package com.seu.koo.po;

import java.sql.Timestamp;

import net.sf.json.JSONArray;

public class Bill {
	private int bill_number;//订单号
	private String list;//明细
	private double price;//总价
	private Timestamp time;//完成时间
	
	private JSONArray list_json;//JsonArray格式的明细
	
	private String timeNew;
	
	public Bill(){
		
	}
	public int getBill_number() {
		return bill_number;
	}
	public void setBill_number(int bill_number) {
		this.bill_number = bill_number;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public JSONArray getList_json() {
		return list_json;
	}
	public void setList_json(JSONArray list_json) {
		this.list_json = list_json;
	}
	public String getTimeNew() {
		return timeNew;
	}
	public void setTimeNew(String timeNew) {
		this.timeNew = timeNew;
	}
}
