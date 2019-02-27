package com.seu.koo.po;

public class Waiter {
	private String account;//服务员登陆前台账户
	
	private String password;//服务员登陆前台密码
	
	private int id;//服务员在职工表中索引

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
