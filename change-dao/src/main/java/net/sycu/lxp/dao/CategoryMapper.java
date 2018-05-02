package net.sycu.lxp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.sycu.lxp.pojo.Category;

@Repository("categoryMapper")
public interface CategoryMapper {
	List<Category> getCategorys(Category category);
}