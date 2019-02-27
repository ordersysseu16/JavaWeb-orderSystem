package com.seu.koo.po;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Staff{
	private int id;
	@NotEmpty(message = "姓名不能为空;")
	@Size(max = 10,message = "姓名最长为10个字符;")
	private String name;
	
	@Size(max = 20,message = "负责菜品最长为20个字符;")
	private String cook;
	
    @NotEmpty(message = "权限不能为空;")
    @Size(max = 10,message = "权限最长为10个字符;")
	private String permission;

	private String position;//职位

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Staff() {
		id=0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCook() {
		return cook;
	}
	public void setCook(String cook) {
		this.cook=cook;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission=permission;
	}
}
