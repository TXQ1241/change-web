package net.sycu.lxp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.dao.OperateLogMapper;
import net.sycu.lxp.pojo.OperateLog;
import net.sycu.lxp.vo.OperateLogVo;

@Service("operateService")
@Transactional
public class OperateLogServiceImpl implements IOperateLogService {
	
	@Autowired
	@Qualifier("operateMapper")
	OperateLogMapper operateMapper;
	
	public List<OperateLog> getOperateLogs(OperateLogVo operateVo) {
		return operateMapper.getOperateLogs(operateVo);
	}

	public Integer getOperateLogCount(OperateLogVo operateVo) {
		return operateMapper.getOperateCount(operateVo);
	}

	public void deleteOperateLogByIds(String[] operateIds) {
		operateMapper.delete(operateIds);
	}

	public void save(OperateLog operate) {
		operateMapper.insert(operate);
	}

}
