package net.sycu.lxp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sycu.lxp.pojo.Goods;
import net.sycu.lxp.vo.GoodsVo;


public interface IGoodsService {
		
	/**
	 *  @param request 
     *  @Description: 通过商品对象获取商品
     *  @author lxp
     *  params  [GoodsVo] 商品查询对象
     *  @return 商品集合
     *  @exception
     **/
    List<Goods> getGoodList(HttpServletRequest request, GoodsVo goodsVo);
    
    /**
     *  @Description: 通过 商品对象获取商品
     *  @author lxp
     *  params  [Goods] 商品
     *  @return List<Goods>商品集合
     *  @exception
     **/
    List<Goods> getGoods(Goods goods);
    
    /**
     *  @Description: 更新商品
     *  @author lxp
     *  params  [goods] 商品对象
     *  @exception
     **/
	void update(Goods goods);
	
    /**
     *  @Description: 保存商品
     *  @author lxp
     *  params  [goods] 商品对象
     *  @exception
     **/
	void save(Goods goods);
	
    /**
     *  @Description: 获取商品记录数
     *  @author lxp
     *  params  [goodsVo] 商品对象
     *  @exception
     **/
	Integer getGoodsCount(GoodsVo goodsVo);
	
    /**
     *  @Description:批量删除商品
     *  @author lxp
     *  params  [goodsIds] 商品id集合
     *  @exception
     **/
	void deleteGoodsByIds(String[] goodsIds);
		
    /**
     *  @Description:保存商品记录
     *  @author lxp
     *  @params request请求对象
     *  @exception
     **/
	void saveGoods(HttpServletRequest request);
	
    /**
     *  @Description:通过商品类型获取商品信息
     *  @author lxp
     *  @params request请求对象
     *  @param goodsVo商品查询对象
     *  @exception
     **/
	List<Goods> getGoodsByCategory(HttpServletRequest request, GoodsVo goodsVo);
	
    /**
     *  @Description:通过商品类型获取商品记录数
     *  @author lxp
     *  @param goodsVo商品查询对象
     *  @exception
     **/
	Integer getGoodsCountByCategory(GoodsVo goodsVo);
	
	/**
	 * 获取商品信息  
	 * @param request
	 * @param goods
	 * @return
	 */
	GoodsVo getGoodsInfo(HttpServletRequest request, Goods goods);
	
	/**
	 * 获取商品信息
	 * @param goodsId
	 * @return
	 */
	Goods getGoods(String goodsId);
	
}
