package net.sycu.lxp.dao;

import org.springframework.stereotype.Repository;

import net.sycu.lxp.pojo.GoodsCategory;

@Repository("goodsCategoryMapper")
public interface GoodsCategoryMapper {
	
	void deleteGoodsCateByGoodsId(String goodsId);
	void insert(GoodsCategory goodsCategory);
	
}