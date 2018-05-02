package net.sycu.lxp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sycu.lxp.pojo.Category;
import net.sycu.lxp.service.ICategoryService;

@Controller
@RequestMapping("/category/")
public class CategoryController {
	
	@Autowired
	@Qualifier("categoryService")
	ICategoryService categoryService;
	
	@RequestMapping("getCategory")
	@ResponseBody
	public List<Category> getCategory(@RequestBody Category category){
		List<Category> categoryList = categoryService.getCategorys(category);
		return categoryList;
	}
	
}
