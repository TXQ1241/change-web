package net.sycu.lxp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.sycu.lxp.pojo.ShoppingCart;
import net.sycu.lxp.vo.ShopCartVo;

@Repository("shopCartMapper")
public interface ShoppingCartMapper {
	
	List<ShoppingCart> getShopCarts(ShopCartVo shopCartVo);
	Integer getShopCartCount(ShopCartVo shopCartVo);
    int insert (ShoppingCart shopCart);
    void deleteShopCartByIds(String [] ids);
    
}