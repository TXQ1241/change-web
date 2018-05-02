package net.sycu.lxp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sycu.lxp.common.Constant;
import net.sycu.lxp.pojo.Reply;
import net.sycu.lxp.service.IReplyService;
import net.sycu.lxp.utils.StringUtils;

@Controller
@RequestMapping("/reply/")
public class ReplyController {
	
    @Autowired
    @Qualifier("replyService")
    IReplyService replyService;
    
	@RequestMapping("save")
	public Map<String, String> saveReply(Reply reply) {
        Map<String, String> msgMap = new HashMap<String, String>();
        try {
        	reply.setId(StringUtils.getUUID());
        	reply.setCreateTime(new Date());
        	reply.setIsDeleted(Constant.DeleteStatus.IS_NOT_DELETE);
            replyService.save(reply);
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"保存回复信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"保存回复信息失败");
        }
        return msgMap;
	}
	
    /**
     *  @Description: 删除回复
     *  @author lxp
     *  @method deleteReplys
     *  params  [reply] 回复对象
     *  @return 操作信息
     *  @exception
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> deleteReplys(@RequestBody Reply reply) {
        Map<String, String> msgMap = new HashMap<String, String>();
        String [] replyIds = null;
        if(StringUtils.isNotBlank(reply.getIds())){
            replyIds = reply.getIds().split(",");
        }
        try {
            if(replyIds != null && replyIds.length > 0) {
                replyService.deleteReplyByIds(replyIds);
            }
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"删除回复信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"删除回复信息失败");
        }
        return msgMap;
    }
	
}
