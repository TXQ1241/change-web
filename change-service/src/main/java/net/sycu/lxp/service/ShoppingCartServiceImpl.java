package net.sycu.lxp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.dao.ShoppingCartMapper;
import net.sycu.lxp.pojo.Goods;
import net.sycu.lxp.pojo.ShoppingCart;
import net.sycu.lxp.vo.ShopCartVo;

@Service("shopCartService")
@Transactional
public class ShoppingCartServiceImpl implements IShoppingCartService {
	
	@Autowired
	@Qualifier("shopCartMapper")
	ShoppingCartMapper shopCartMapper;
	
	@Autowired
	@Qualifier("goodsService")
	IGoodsService goodsService;
	
	public void save(ShoppingCart shopCart) {
		shopCartMapper.insert(shopCart);
	}

	public void deleteShopCartByIds(String[] ids) {
		shopCartMapper.deleteShopCartByIds(ids);
	}

	public Integer getShopCartCount(ShopCartVo shopCartVo) {
		return shopCartMapper.getShopCartCount(shopCartVo);
	}

	public List<ShoppingCart> getShopCarts(ShopCartVo shopCartVo) {
		
		List<ShoppingCart> cartList = shopCartMapper.getShopCarts(shopCartVo);
		if (cartList != null) {
			for (ShoppingCart cart : cartList) {
				if(StringUtils.isNotBlank(cart.getGoodsId())) {
					Goods goods = goodsService.getGoods(cart.getGoodsId());
					if(StringUtils.isNotBlank(goods.getImagesSrc())) {
						String [] imagesSrcArr = goods.getImagesSrc().split(",");
						goods.setImagesSrc(imagesSrcArr[0]);
					} else {
						goods.setImagesSrc(Constant.GoodsConstants.GOODS_IMAGE_SRC);
					}
					cart.setGoods(goods);
				}
			}
		}
		return cartList;
		
	}

}
