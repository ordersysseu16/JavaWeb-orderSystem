package com.seu.koo.service;

import java.util.List;

import com.seu.koo.po.Dish;

public interface DishService {
	public List<Dish> findAllDishes() throws Exception;
	
	public void createDish (Dish dish) throws Exception;
	
	public void updateDishById (Dish dish) throws Exception;
	
	public void deleteDishById (int id) throws Exception;
}

