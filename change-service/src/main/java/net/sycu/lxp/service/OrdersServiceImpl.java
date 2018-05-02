package net.sycu.lxp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.dao.OrdersMapper;
import net.sycu.lxp.pojo.Goods;
import net.sycu.lxp.pojo.Orders;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.OrdersVo;

@Service("ordersService")
@Transactional
public class OrdersServiceImpl implements IOrdersService {
	
	@Autowired
	@Qualifier("ordersMapper")
	OrdersMapper ordersMapper;
	
	@Autowired
	@Qualifier("goodsService")
	IGoodsService goodsService;
	
	public List<Orders> getOrderList(OrdersVo ordersVo) {
		List<Orders> ordersList = ordersMapper.getOrdersList(ordersVo);
		if(ordersList != null && ordersList.size() > 0) {
			for (Orders orders:ordersList) {
				if (StringUtils.isNotBlank(orders.getGoodsId())) {
					Goods goods = goodsService.getGoods(orders.getGoodsId());
					if (StringUtils.isNotBlank(goods.getImagesSrc())) {
						String [] imageArr = goods.getImagesSrc().split(",");
						goods.setImagesSrc(imageArr[0]);
					} else {
						goods.setImagesSrc(Constant.GoodsConstants.GOODS_IMAGE_SRC);
					}
					orders.setGoods(goods);
				}
				if (orders.getCreateTime() != null) {
					//设置时间格式
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					orders.setCreateTimeStr(df.format(orders.getCreateTime()));
				}
			}
		}
		return ordersList;
	}

	public void save(Orders orders) {
		ordersMapper.insert(orders);
		
	}

	public void deleteOrdersByIds(String[] ids) {
		ordersMapper.deleteOrdersByIds(ids);
	}

	public Integer getOrdersCount(OrdersVo ordersVo) {
		return ordersMapper.getOrdersCount(ordersVo);
	}

	public void saveOrders(HttpServletRequest request, Orders orders) {
		saveOrder(request, orders);
	}

	public void saveOrders(HttpServletRequest request, OrdersVo ordersVo) {
	
		if(StringUtils.isNotBlank(ordersVo.getGoodsIds())) {
			String [] goodsIdArr = ordersVo.getGoodsIds().split(",");
			for (int i=0; i<goodsIdArr.length; i++) {
				Orders orders = new Orders();
				orders.setGoodsId(goodsIdArr[0]);
				saveOrder(request, orders);
			}
		}
		
	}
	
	/**
	 * 保存订单信息
	 * @param request
	 * @param orders
	 */
	private void saveOrder(HttpServletRequest request, Orders orders) {
		//保存订单信息
    	User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
        orders.setId(StringUtils.getUUID());
        orders.setCreateTime(new Date());
        orders.setBuyersId(user.getId());
        orders.setIsDeleted(Constant.DeleteStatus.IS_NOT_DELETE);
        orders.setStatus(Constant.OrdersConstants.WAIT_STATUS);
        this.save(orders);
        
        //保存要交换者的信息到商品对象中
        if(StringUtils.isNotBlank(orders.getGoodsId())) {
    		Goods goods = goodsService.getGoods(orders.getGoodsId());
    		String updateUserId = goods.getUpdateUserId();
    		if(StringUtils.isNotBlank(updateUserId)) {
    			updateUserId = updateUserId + "," +user.getId();
    		} else {
    			updateUserId = user.getId();
    		}
    		goods.setUpdateUserId(updateUserId);
    		goodsService.update(goods);
        }
	}

	public void update(Orders orders) {
		ordersMapper.update(orders);
	}

	public List<Orders> getOrders(Orders orders) {
		return ordersMapper.getOrders(orders);
	}

}
