package com.seu.koo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.seu.koo.mapper.DishMapper;
import com.seu.koo.po.Dish;

public class DishServiceImpl implements DishService {
	@Autowired
    private DishMapper dishMapper; //用AutoWired注入DB层

    @Transactional(readOnly=true) //数据库的读取方式为：只读
	@Override
	public List<Dish> findAllDishes() throws Exception {
    	List<Dish> list = new ArrayList<>();
		list = dishMapper.queryDishList();
		return list;
	}

	@Override
	public void createDish(Dish dish) throws Exception {
		dishMapper.insertDish(dish);
	}
	
	@Override
	public void updateDishById(Dish dish) throws Exception {
		int id = dish.getId();
		//值为null则不变
		if(dish.getName() == null){
			dish.setName(dishMapper.queryDishById(id).getName());
		}
		if(dish.getPrice() == 0){
			dish.setPrice(dishMapper.queryDishById(id).getPrice());
		}
		if(dish.getTime() == null){
			dish.setTime(dishMapper.queryDishById(id).getTime());
		}
		if(dish.getImage() == null){
			dish.setImage(dishMapper.queryDishById(id).getImage());
		}
		if(dish.getType() == null){
			dish.setType(dishMapper.queryDishById(id).getType());
		}
		if(dish.getDescription() == null){
			dish.setDescription(dishMapper.queryDishById(id).getDescription());
		}
		//更新表
		dishMapper.updateDish(dish);
	}

	@Override
	public void deleteDishById(int id) throws Exception {
		dishMapper.deleteDishById(id);
	}
}
