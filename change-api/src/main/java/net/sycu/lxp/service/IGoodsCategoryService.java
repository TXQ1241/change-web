package net.sycu.lxp.service;

import net.sycu.lxp.pojo.GoodsCategory;

public interface IGoodsCategoryService {
	
	/**
     *  @Description: 通过商品id删除商品类型关联信息
     *  @author lxp
     *  @params  [goodsId] 商品id
     *  @exception
     **/
	void deleteGoodsCateByGoodsId(String goodsId);
	
	/**
     *  @Description: 保存商品类型的关联关系
     *  @author lxp
     *  @params  [GoodsCategory] 关联关系对象
     *  @exception
     **/
	void saveGoodsCategory(GoodsCategory goodsCategory);
		
}
