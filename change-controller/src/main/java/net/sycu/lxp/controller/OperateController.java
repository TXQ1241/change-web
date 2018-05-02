package net.sycu.lxp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.pojo.OperateLog;
import net.sycu.lxp.service.IOperateLogService;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.DataVo;
import net.sycu.lxp.vo.OperateLogVo;

@Controller
@RequestMapping("operate")
public class OperateController {
	
	@Autowired
	@Qualifier("operateService")
	IOperateLogService operateService;
	
	/**
	 * 获取操作日志数据
	 * @param operateVo
	 * @return
	 */
	@RequestMapping("getOperateList")
	@ResponseBody
	public DataVo getOperateList(@RequestBody OperateLogVo operateVo) {
		DataVo dataVo = new DataVo();
        Integer pageNum = operateVo.getPageNum();
        //设置查询开始的条数(就是从哪条开始查询)
        if(pageNum != null) {
        	operateVo.setPageNum((pageNum-1)*operateVo.getPageSize());
        }
        try {
            List<OperateLog> userList = operateService.getOperateLogs(operateVo);
            dataVo.setDatalist(userList);
            dataVo.setCode(Constant.DataCode.SUCCESS);
            dataVo.setMsg("数据获取成功");
            if (userList != null) {
                dataVo.setCount(operateService.getOperateLogCount(operateVo));
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
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, String> delete(@RequestBody OperateLog operate) {
		Map<String, String> msgMap = new HashMap<String, String>();
        String [] operateIds = null;
        if(StringUtils.isNotBlank(operate.getIds())){
        	operateIds = operate.getIds().split(",");
        }
        try {
            if(operateIds != null && operateIds.length > 0) {
            	operateService.deleteOperateLogByIds(operateIds);
            }
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"删除日志信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"删除日志信息失败");
        }
        return msgMap;
	}
	
}
