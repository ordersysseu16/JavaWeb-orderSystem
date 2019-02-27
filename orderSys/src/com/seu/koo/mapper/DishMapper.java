package com.seu.koo.mapper;

import java.util.List;

import com.seu.koo.po.Dish;

public interface DishMapper {
	/**
	 * 查询菜品对象
	 */
	public List<Dish> queryDishByName(String name);
	/**
	 * 查询菜品对象
	 */
	public Dish queryDishById(int id);
	/**
	 * 查询账单对象--返回多个--List<Dish>
	 */
	public List<Dish> queryDishList();
	/**
	 * 插入菜品对象
	 */
	public int insertDish(Dish dish);
	/**
	 * 更新菜品对象
	 */
	public int updateDish(Dish dish);
	/**
	 * 删除菜品对象
	 */
	public int deleteDishById(int id);
}
