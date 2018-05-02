package net.sycu.lxp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.pojo.ShoppingCart;
import net.sycu.lxp.vo.ShopCartVo;

public interface IShoppingCartService {
	
	/**
     *  @Description: 保存购物车记录
     *  @author lxp
     *  @params  [shopCart] 购物车对象
     *  @exception
     **/
	void save(ShoppingCart shopCart);
	
	/**
     *  @Description: 批量删除购物车记录
     *  @author lxp
     *  @params  [ids] 购物车id集
     *  @exception
     **/
	void deleteShopCartByIds(String[] ids);
	
	/**
     *  @Description: 获取购物车记录数
     *  @author lxp
     *  @params  [shopCartVo] 购物车查询对象
     *  @return 记录数
     *  @exception
     **/
	Integer getShopCartCount(ShopCartVo shopCartVo);
	
	/**
     *  @Description: 获取购物车对象
     *  @author lxp
     *  @params  [shopCartVo] 购物车查询对象
     *  @return 购物车集合
     *  @exception
     **/
	List<ShoppingCart> getShopCarts(ShopCartVo shopCartVo);
	
	
}
