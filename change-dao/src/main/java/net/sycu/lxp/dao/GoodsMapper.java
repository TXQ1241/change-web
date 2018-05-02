package net.sycu.lxp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.pojo.Goods;
import net.sycu.lxp.vo.GoodsVo;

@Repository("goodsMapper")
@Transactional
public interface GoodsMapper {
    int insert(Goods goods);

    List<Goods> getGoods (Goods goods);
    
    List<Goods> getGoodsList (GoodsVo goods);

    int updateByGoodsSelective(Goods goods);

	Integer getGoodsCount(GoodsVo goodsVo);
	
	Integer getGoodsCountByCategory(GoodsVo goodsVo);

	void deleteGoodsByIds(String[] goodsIds);

	List<Goods> getGoodsByCategory(GoodsVo goodsVo);
}