package com.seu.koo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seu.koo.po.Dish;
import com.seu.koo.service.DishService;
import com.seu.koo.util.SingleFileUpload;
import com.seu.koo.util.UploadFileFilter;

import net.sf.json.JSONArray;

@Controller
public class DishController {
	@Autowired
	DishService dishService;

	//一次性获取菜单列表
	@RequestMapping(value="/dishes", method={RequestMethod.GET}, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getDishList() throws Exception {
		List<Dish> list = new ArrayList<>();
		list = dishService.findAllDishes();
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}

	//创建新的菜品记录
	@RequestMapping(value="/dish", method={RequestMethod.POST})
	@ResponseBody
	public String createNewDish(HttpServletRequest request,@Validated Dish dish,BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {  

            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMessage="";
            for(ObjectError  error:allErrors){
                errorMessage+=error.getDefaultMessage();
            }
            System.out.println(errorMessage);
            return errorMessage;
		}//返回错误信息
		SingleFileUpload sUpload = new SingleFileUpload();
		sUpload.setFileType("gif,jpg,png,bmp");
		final String fileType = sUpload.getFileType();
		sUpload.setFilter(new UploadFileFilter() {
			@Override
			public boolean accept(String filename) {
				boolean flag = false;
				if (fileType == null) {
					flag = true;
				} else {
					String ext = SingleFileUpload.getFileExtension(filename);
					if (fileType.contains(ext.toLowerCase())) {
						flag = true;
					}
				}
				return flag;

			}
		});

		sUpload.parseRequest(request);
		String filePath = request.getServletContext().getRealPath("/") + "resource\\picture_dish\\";
		System.out.println(filePath);
		String fileName = null;
		if (sUpload.getFileItem() != null) {
			fileName = sUpload.getFileItem().getName();
		}
		try {
			String path = sUpload.upload(filePath, fileName);
			Map<String, String> parameters = sUpload.getParameters();
			dish.setName(parameters.get("name"));
			dish.setPrice(Double.parseDouble(parameters.get("price")));
			dish.setTime(parameters.get("time"));
			dish.setImage(path);
			dish.setType(parameters.get("type"));
			dish.setDescription(parameters.get("description"));
			dishService.createDish(dish);
		} catch (SizeLimitExceededException e) {
			// 文件大小超出最大值
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	//更改指定id的菜品记录
	@RequestMapping(value="/dish/{id}", method={RequestMethod.POST})
	@ResponseBody
	public String changeDishInfo(@PathVariable String id, HttpServletRequest request) throws Exception {
		SingleFileUpload sUpload = new SingleFileUpload();
		sUpload.setFileType("gif,jpg,png,bmp");
		final String fileType = sUpload.getFileType();
		sUpload.setFilter(new UploadFileFilter() {
			@Override
			public boolean accept(String filename) {
				boolean flag = false;
				if (fileType == null) {
					flag = true;
				} else {
					String ext = SingleFileUpload.getFileExtension(filename);
					if (fileType.contains(ext.toLowerCase())) {
						flag = true;
					}
				}
				return flag;

			}
		});

		sUpload.parseRequest(request);
		String filePath = request.getServletContext().getRealPath("/") + "resource\\picture_dish\\";
		System.out.println(filePath);
		String fileName = null;
		if (sUpload.getFileItem() != null) {
			fileName = sUpload.getFileItem().getName();
		}
		try {
			Dish dish= new Dish();
			String path = sUpload.upload(filePath, fileName);
			Map<String, String> parameters = sUpload.getParameters();
			dish.setId(Integer.parseInt(id));
			dish.setName(parameters.get("name"));
			dish.setPrice(Double.parseDouble(parameters.get("price")));
			dish.setTime(parameters.get("time"));
			dish.setImage(path);
			dish.setType(parameters.get("type"));
			dish.setDescription(parameters.get("description"));
			dishService.updateDishById(dish);
		} catch (SizeLimitExceededException e) {
			// 文件大小超出最大值
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	//删除指定id的菜品
	@RequestMapping(value="/dish/{id}", method={RequestMethod.DELETE})
	@ResponseBody
	public String deleteDish(@PathVariable String id) throws Exception {
		int idToDelete = Integer.parseInt(id);
		dishService.deleteDishById(idToDelete);
		return "success";
	}
}
