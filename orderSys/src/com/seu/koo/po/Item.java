package com.seu.koo.po;

import java.sql.Timestamp;

public class Item {
	private int dish_id;//菜品id
	
	private String dish_name;//菜名
	
	private int num;//菜品数量
	
	private boolean isUrgent;//是否加急
	
	private Timestamp createTime;//点菜时间
	
	private int finshTime;//预计成菜所需时间
	
	private int table;//桌号

	
	public Item() {
		createTime = new Timestamp(System.currentTimeMillis()); 
	}
	
	public int getDish_id() {
		return dish_id;
	}

	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgent(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}

	public int getFinshTime() {
		return finshTime;
	}

	public void setFinshTime(int finshTime) {
		this.finshTime = finshTime;
	}

	public String getDish_name() {
		return dish_name;
	}

	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}
}
