package net.sycu.lxp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.dao.OperateLogMapper;
import net.sycu.lxp.pojo.OperateLog;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.OperateLogVo;

@Service("operateService")
@Transactional
public class OperateLogServiceImpl implements IOperateLogService {
	
	@Autowired
	@Qualifier("operateMapper")
	OperateLogMapper operateMapper;
	
	public List<OperateLog> getOperateLogs(OperateLogVo operateVo) {
		List<OperateLog> operateList = operateMapper.getOperateLogs(operateVo);
		if (operateList != null && operateList.size() > 0) {
			for (OperateLog operate:operateList) {
				if(operate.getOperateTime() != null) {
					//设置时间格式
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					operate.setOperateTimeStr(df.format(operate.getOperateTime()));
				}
			}
		}
		return operateList;
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

	public void saveLog(User user, String operateType) {
		OperateLog log = new OperateLog();
		log.setId(StringUtils.getUUID());
		log.setOperateTime(new Date());
		log.setOperateType(operateType);
		log.setUserAccount(user.getAccount());
		log.setUserName(user.getUserName());
		this.save(log);
	}

}
