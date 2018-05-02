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
import net.sycu.lxp.service.IGoodsCommentsService;
import net.sycu.lxp.vo.CommentsVo;
import net.sycu.lxp.vo.DataVo;

@Controller
@RequestMapping("/goodsComments/")
public class GoodsCommentsController {
	
	@Autowired
	@Qualifier("commentsService")
	IGoodsCommentsService goodsCommentsService;
	
	@RequestMapping("getGoodsComments")
	@ResponseBody
	public DataVo getGoodsComments(@RequestBody CommentsVo commentsVo) {
				
        Integer pageNum = commentsVo.getPageNum();
        //设置查询开始的条数(就是从哪条开始查询)
        if(pageNum != null) {
        	commentsVo.setPageNum((pageNum-1)*commentsVo.getPageSize());
        }
        DataVo dataVo = new DataVo(); 
        try {
	        List<CommentsVo> voList = goodsCommentsService.getCommentsVo(commentsVo);	
			if (voList != null) {
				dataVo.setCount(goodsCommentsService.getCommentsCount(commentsVo));
			} else {
				dataVo.setCount(Constant.ZERO_NUM);
			}
			dataVo.setDatalist(voList);
			dataVo.setCode(Constant.DataCode.SUCCESS);
			dataVo.setMsg("数据获取成功");
		} catch (Exception e) {
            dataVo.setCode(Constant.DataCode.FAIL);
            dataVo.setMsg("数据获取失败");
			e.printStackTrace();
		}
        return dataVo;
	}
	
    /**
     * @throws IOException 
     *  @Description: 保存评论
     *  @author lxp
     *  @method saveGoods
     *  params  [goods] 评论对象
     *  @exception
     **/
    @RequestMapping("save")
    @ResponseBody
    public Map<String, String> saveGoods(HttpServletRequest request) throws IOException {
        Map<String, String> msgMap = new HashMap<String, String>();
        try {
        	goodsCommentsService.saveComments(request);
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"保存评论信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"保存评论信息失败");
            e.printStackTrace();
        }
        return msgMap;
        
    }
	
}
