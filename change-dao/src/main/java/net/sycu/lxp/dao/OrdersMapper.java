package net.sycu.lxp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.sycu.lxp.pojo.Orders;
import net.sycu.lxp.vo.OrdersVo;

@Repository("ordersMapper")
public interface OrdersMapper {

    int insert(Orders orders);
    List<Orders> getOrdersList(OrdersVo ordersVo);
    Integer getOrdersCount(OrdersVo ordersVo);
    void deleteOrdersByIds(String [] ids);
	void update(Orders orders);
	List<Orders> getOrders(Orders orders);

}