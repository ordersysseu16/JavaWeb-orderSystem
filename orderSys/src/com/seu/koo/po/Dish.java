package com.seu.koo.po;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class Dish {
	private int id;
	@NotEmpty(message = "菜名不能为空;")
    @Size(max = 8,message = "菜名最长为8个字符;")
	private String name;
	
	@Range(min=1, max=10000,message="价格应在1到10000之间;")
	private double price;
	
	@NotEmpty(message = "时间不能为空;")
    @Size(max = 5,message = "时间最长为5个字符;")
	private String time;
	
	private String image;
	
	@NotEmpty(message = "类别不能为空;")
    @Size(max = 10,message = "类别最长为10个字符;")
	private String type;
	
	@NotEmpty(message = "描述不能为空;")
    @Size(max = 50,message = "描述最长为50个字符;")
	private String description;
	
	public Dish() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price=price;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time=time;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
