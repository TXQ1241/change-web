package net.sycu.lxp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.pojo.ShoppingCart;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.service.IShoppingCartService;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.DataVo;
import net.sycu.lxp.vo.ShopCartVo;

@Controller
@RequestMapping("/shopcart/")
public class ShoppingCartController {
	
	@Autowired
	@Qualifier("shopCartService")
	IShoppingCartService shopCartService;
	
    /**
     *  @Description: 获取购物车信息
     *  @author lxp
     *  params  [shopCartVo] 购物车对象
     *  @return 操作信息
     *  @exception
     **/
	@RequestMapping("getShopCartList")
	@ResponseBody
	public DataVo getShopCarts (HttpServletRequest request, @RequestBody ShopCartVo shopCartVo) {
		
		User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
		if(user != null) {
			shopCartVo.setUserId(user.getId());
		}
		
		DataVo dataVo = new DataVo();
        Integer pageNum = shopCartVo.getPageNum();
        //设置查询开始的条数(就是从哪条开始查询)
        if(pageNum != null) {
        	shopCartVo.setPageNum((pageNum-1)*shopCartVo.getPageSize());
        }
        try {
            List<ShoppingCart> userList = shopCartService.getShopCarts(shopCartVo);
            dataVo.setDatalist(userList);
            dataVo.setCode(Constant.DataCode.SUCCESS);
            dataVo.setMsg("数据获取成功");
            if (userList != null) {
                dataVo.setCount(shopCartService.getShopCartCount(shopCartVo));
            } else {
                dataVo.setCount(Constant.ZERO_NUM);
            }
        } catch (Exception e) {
            dataVo.setCode(Constant.DataCode.FAIL);
            dataVo.setMsg("数据获取失败");
            e.printStackTrace();
        }
        return dataVo;
	}
	
    /**
     *  @Description: 保存购物车
     *  @author lxp
     *  @params  [shopCart] 购物车对象
     *  @return 操作信息
     *  @exception
     **/
	@RequestMapping("save")
	@ResponseBody
	public Map<String, String> saveShopCart(HttpServletRequest request, @RequestBody ShoppingCart shopCart){		
        Map<String, String> msgMap = new HashMap<String, String>();
		User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
		if(user != null) {
			shopCart.setUserId(user.getId());
		}		
        try {
        	shopCart.setId(StringUtils.getUUID());
        	shopCart.setCreateTime(new Date());
        	shopCart.setIsDeleted(Constant.DeleteStatus.IS_NOT_DELETE);
        	shopCartService.save(shopCart);
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"保存用户信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"保存用户信息失败");
        }
        return msgMap;
	}
	
    /**
     *  @Description: 删除购物车
     *  @author lxp
     *  @params  [shopCart] 购物车对象
     *  @return 操作信息
     *  @exception
     **/
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, String> deleteShopCart(@RequestBody ShoppingCart shoppingCart) {
        Map<String, String> msgMap = new HashMap<String, String>();		
        try {
            String [] shoppingCartIds = null;
            if(StringUtils.isNotBlank(shoppingCart.getIds())){
            	shoppingCartIds = shoppingCart.getIds().split(",");
            }
            if(shoppingCartIds != null && shoppingCartIds.length > 0) {
            	shopCartService.deleteShopCartByIds(shoppingCartIds);
            }
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"保存用户信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"保存用户信息失败");
        }
        return msgMap;
	}
	

}
