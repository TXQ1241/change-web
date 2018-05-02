package net.sycu.lxp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.dao.CategoryMapper;
import net.sycu.lxp.pojo.Category;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements ICategoryService {
	
    @Autowired
    @Qualifier("categoryMapper")
    CategoryMapper categoryMapper;
	public List<Category> getCategorys(Category category) {
		return categoryMapper.getCategorys(category);
	}

}
