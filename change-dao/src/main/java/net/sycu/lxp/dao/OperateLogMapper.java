package net.sycu.lxp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.sycu.lxp.pojo.OperateLog;
import net.sycu.lxp.vo.OperateLogVo;

@Repository("operateMapper")
public interface OperateLogMapper {

    int insert(OperateLog operate);
    List<OperateLog> getOperateLogs(OperateLogVo vo);
    Integer getOperateCount(OperateLogVo vo);
    void delete(String [] ids);

}