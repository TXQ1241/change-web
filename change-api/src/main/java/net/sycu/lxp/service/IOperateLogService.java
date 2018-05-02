package net.sycu.lxp.service;

import java.util.List;

import net.sycu.lxp.pojo.OperateLog;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.vo.OperateLogVo;

public interface IOperateLogService {
	
	/**
	 * 获取操作日志信息
	 * @param operateVo
	 * @return
	 */
	List<OperateLog> getOperateLogs(OperateLogVo operateVo);
	
	/**
	 * 获取操作日志记录数
	 * @param operateVo
	 * @return
	 */
	Integer getOperateLogCount(OperateLogVo operateVo);
	
	/**
	 * 批量删除操作日志
	 * @param operateIds
	 */
	void deleteOperateLogByIds(String[] operateIds);
	
	/**
	 * 保存操作信息
	 * @param operate
	 */
	void save(OperateLog operate);
	
	/**
	 * 保存当前用户操作的日志信息
	 * @param user
	 */
	void saveLog(User user, String operateType);
	
}
