package net.sycu.lxp.controller;

import java.io.IOException;
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
import net.sycu.lxp.pojo.Goods;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.service.IGoodsService;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.DataVo;
import net.sycu.lxp.vo.GoodsVo;

/**
 * @author lxp
 **/
@Controller
@RequestMapping("/goods/")
public class GoodsController {
	
    @Autowired
    @Qualifier("goodsService")
    IGoodsService goodsService;
	
    /**
     *  @Description: 通过商品对象获取商品数据
     *  @author lxp
     *  @method getGoods
     *  params  [goods] 商品对象
     *  @return DataVo 商品集对象
     *  @exception
     **/
    @RequestMapping("view")
    public String toGoodsList(String goodsType) {
    	String page = "search";
    	if (Constant.GoodsConstants.BUYER_GOODS.equals(goodsType)) {
    		page = "seller";
    	}
    	return page;
    }
    
    /**
     *  @Description: 通过商品对象获取商品数据
     *  @author lxp
     *  @method getGoods
     *  params  [goods] 商品对象
     *  params  [request] 请求对象
     *  @return DataVo 商品集对象
     *  @exception
     **/
	@RequestMapping("goodsList")
	@ResponseBody
	public DataVo getGoods(HttpServletRequest request,@RequestBody GoodsVo goodsVo){
		
		User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
		if(user != null) {
			goodsVo.setCreatorId(user.getId());
		}
		return getGoodsList(request, goodsVo);
	}
	
    
    /**
     *  @Description: 通过商品对象获取商品数据
     *  @author lxp
     *  @method getGoods
     *  params  [goods] 商品对象
     *  params  [request] 请求对象
     *  @return DataVo 商品集对象
     *  @exception
     **/
	@RequestMapping("goodsAllList")
	@ResponseBody
	public DataVo getAllGoods(HttpServletRequest request,GoodsVo goodsVo){
		return getGoodsList(request, goodsVo);
	}
	
	@RequestMapping("getGoodsByCategory")
	@ResponseBody
	public DataVo getGoodsByCategory(HttpServletRequest request, @RequestBody GoodsVo goodsVo) {
        Integer pageNum = goodsVo.getPageNum();
        //设置查询开始的条数(就是从哪条开始查询)
        if(pageNum != null) {
        	goodsVo.setPageNum((pageNum-1)*goodsVo.getPageSize());
        }
        DataVo dataVo = new DataVo();
        try {
	        List<Goods> goodsList = goodsService.getGoodsByCategory(request, goodsVo);
			if (goodsList != null) {
				dataVo.setCount(goodsService.getGoodsCountByCategory(goodsVo));
			} else {
				dataVo.setCount(Constant.ZERO_NUM);
			}
			dataVo.setDatalist(goodsList);
			dataVo.setCode(Constant.DataCode.SUCCESS);
			dataVo.setMsg("数据获取成功");
        } catch (Exception e) {
            dataVo.setCode(Constant.DataCode.FAIL);
            dataVo.setMsg("数据获取失败");
			e.printStackTrace();
        }
        return dataVo;
	}
	
	@RequestMapping("getGoodsInfo")
	@ResponseBody
	public GoodsVo getGoodsInfo(HttpServletRequest request, @RequestBody Goods goods) {
		GoodsVo vo = goodsService.getGoodsInfo(request, goods);
		return vo;
	}
	
    /**
     * @throws IOException 
     *  @Description: 保存商品
     *  @author lxp
     *  @method saveGoods
     *  params  [goods] 商品对象
     *  @return 操作信息
     *  @exception
     **/
    @RequestMapping("save")
    @ResponseBody
    public Map<String, String> saveGoods(HttpServletRequest request) throws IOException {
        Map<String, String> msgMap = new HashMap<String, String>();
        try {
        	goodsService.saveGoods(request);
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"保存商品信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"保存商品信息失败");
        }
        return msgMap;
        
    }

    /**
     *  @Description: 删除商品
     *  @author lxp
     *  @method deleteGoodss
     *  params  [goods] 商品对象
     *  @return 操作信息
     *  @exception
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> deleteGoods(@RequestBody Goods goods) {
        Map<String, String> msgMap = new HashMap<String, String>();
        String [] goodsIds = null;
        if(StringUtils.isNotBlank(goods.getIds())){
            goodsIds = goods.getIds().split(",");
        }
        try {
            if(goodsIds != null && goodsIds.length > 0) {
                goodsService.deleteGoodsByIds(goodsIds);
            }
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"删除商品信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"删除商品信息失败");
        }
        return msgMap;
    }
    
    /**
     * 获取商品信息
     * @param request
     * @param goodsVo
     * @return
     */
    private DataVo getGoodsList(HttpServletRequest request,@RequestBody GoodsVo goodsVo) {
        Integer pageNum = goodsVo.getPageNum();
        //设置查询开始的条数(就是从哪条开始查询)
        if(pageNum != null) {
        	goodsVo.setPageNum((pageNum-1)*goodsVo.getPageSize());
        }
		DataVo dataVo = new DataVo();
		try {
			List<Goods> goodsList = goodsService.getGoodList(request, goodsVo);
			dataVo.setDatalist(goodsList);
			if (goodsList != null) {
				dataVo.setCount(goodsService.getGoodsCount(goodsVo));
			} else {
				dataVo.setCount(Constant.ZERO_NUM);
			} 
            dataVo.setCode(Constant.DataCode.SUCCESS);
            dataVo.setMsg("数据获取成功");
		} catch (Exception e) {
            dataVo.setCode(Constant.DataCode.FAIL);
            dataVo.setMsg("数据获取失败");
			e.printStackTrace();
		}
		return dataVo;
    }
	
}
