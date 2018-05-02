package net.sycu.lxp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sycu.lxp.pojo.Orders;
import net.sycu.lxp.vo.OrdersVo;


public interface IOrdersService {
	
	/**
     *  @Description: 通过订单对象获取订单
     *  @author lxp
     *  params  [OrdersVo] 订单查询对象
     *  @return 订单集合
     *  @exception
     **/
	List<Orders> getOrderList(OrdersVo ordersVo);
	
	/**
     *  @Description:保存订单
     *  @author lxp
     *  params  [Orders] 订单对象
     *  @exception
     **/
	void save(Orders orders);
	
	/**
     *  @Description:删除订单
     *  @author lxp
     *  params  [ids] 订单id集
     *  @exception
     **/
	void deleteOrdersByIds(String [] ids);
	
	/**
	 * 获取订单记录数
	 * @param ordersVo
	 * @return
	 */
	Integer getOrdersCount(OrdersVo ordersVo);
	
	/**
	 * 保存订单信息
	 * @param request
	 * @param orders
	 */
	void saveOrders(HttpServletRequest request, Orders orders);
	
	/**
	 * 批量生产订单
	 * @param request
	 * @param ordersVo
	 */
	void saveOrders(HttpServletRequest request, OrdersVo ordersVo);
	
	/**
	 * 更新订单数据
	 * @param orders
	 */
	void update(Orders orders);
	
	/**
	 * 获取订单数据
	 * @param orders
	 * @return
	 */
	List<Orders> getOrders(Orders orders);
	
}
