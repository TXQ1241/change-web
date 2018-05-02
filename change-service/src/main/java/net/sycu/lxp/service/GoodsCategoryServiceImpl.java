package net.sycu.lxp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.dao.GoodsCategoryMapper;
import net.sycu.lxp.pojo.GoodsCategory;

@Service("goodsCategoryService")
@Transactional
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {
	
	@Autowired
	@Qualifier("goodsCategoryMapper")
	GoodsCategoryMapper goodsCategoryMapper;
	
	public void deleteGoodsCateByGoodsId(String goodsId) {
		goodsCategoryMapper.deleteGoodsCateByGoodsId(goodsId);
	}

	public void saveGoodsCategory(GoodsCategory goodsCategory) {
		goodsCategoryMapper.insert(goodsCategory);
	}


}
