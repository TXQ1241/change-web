package net.sycu.lxp.controller;

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
import net.sycu.lxp.pojo.Orders;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.service.IOrdersService;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.DataVo;
import net.sycu.lxp.vo.OrdersVo;

@Controller
@RequestMapping("/orders/")
public class OrdersController {
	
	@Autowired
	@Qualifier("ordersService")
	IOrdersService ordersService;
	
	@RequestMapping("getOrdersList")
	@ResponseBody
	public DataVo getOrdersList(HttpServletRequest request, @RequestBody OrdersVo ordersVo) {
		DataVo dataVo = new DataVo();
		
		User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
		if(user != null) {
			ordersVo.setBuyersId(user.getId());
		}
		
		Integer pageNum = ordersVo.getPageNum();
        //设置查询开始的条数(就是从哪条开始查询)
        if(pageNum != null) {
        	ordersVo.setPageNum((pageNum-1)*ordersVo.getPageSize());
        }
        try {
            List<Orders> userList = ordersService.getOrderList(ordersVo);
            dataVo.setDatalist(userList);
            dataVo.setCode(Constant.DataCode.SUCCESS);
            dataVo.setMsg("数据获取成功");
            if (userList != null) {
                dataVo.setCount(ordersService.getOrdersCount(ordersVo));
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
     *  @Description: 保存订单（系统新增，注册新增）
     *  @author lxp
     *  @method saveOrdsers
     *  params  [orders] 订单对象
     *  @return 操作信息
     *  @exception
     **/
    @RequestMapping("save")
    @ResponseBody
    public Map<String, String> saveOrders(HttpServletRequest request, @RequestBody Orders orders) {
        Map<String, String> msgMap = new HashMap<String, String>();
        try {
            ordersService.saveOrders(request, orders);       	
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"保存订单信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"保存订单信息失败");
        }
        return msgMap;
    }
    
    @RequestMapping("update")
    @ResponseBody
    public Map<String, String> update(HttpServletRequest request, @RequestBody Orders orders) {
        Map<String, String> msgMap = new HashMap<String, String>();
        Orders ordersInfo = new Orders();
        try {
    		List<Orders> ordersList = ordersService.getOrders(orders);
    		if (ordersList != null && ordersList.size() > 0) {
    			ordersInfo = ordersList.get(0);
        		ordersInfo.setStatus(orders.getStatus());
        		ordersService.update(ordersInfo);
    		}
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"保存订单信息成功");
        } catch (Exception e) {
        	e.printStackTrace();
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"保存订单信息失败");
        }
        return msgMap;
    }
    
    @RequestMapping("saveOrders")
    @ResponseBody
    public Map<String, String> saveOrders(HttpServletRequest request, @RequestBody OrdersVo ordersVo){
    	Map<String, String> msgMap = new HashMap<String, String>();
        try {
        	ordersService.saveOrders(request, ordersVo);
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"保存订单信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"保存订单信息失败");
        }
        return msgMap;
    }

    /**
     *  @Description: 删除订单
     *  @author lxp
     *  @method deleteOrders
     *  params  [orders] 订单对象
     *  @return 操作信息
     *  @exception
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> deleteOrders(@RequestBody Orders orders) {
        Map<String, String> msgMap = new HashMap<String, String>();
        String [] ordersIds = null;
        if(StringUtils.isNotBlank(orders.getIds())){
            ordersIds = orders.getIds().split(",");
        }
        try {
            if(ordersIds != null && ordersIds.length > 0) {
                ordersService.deleteOrdersByIds(ordersIds);
            }
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"删除订单信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"删除订单信息失败");
        }
        return msgMap;
    }
    
}
