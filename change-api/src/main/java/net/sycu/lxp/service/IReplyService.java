package net.sycu.lxp.service;

import java.util.List;
import java.util.Map;

import net.sycu.lxp.pojo.Reply;

public interface IReplyService {
	
	/**
     *  @Description: 保存用户回复信息
     *  @author lxp
     *  @params  [reply] 回复对象
     *  @exception
     **/
	void save(Reply reply);
	
	/**
     *  @Description: 获取回复信息
     *  @author lxp
     *  @params  [reply] 回复对象
     *  @return 回复对象集
     *  @exception
     **/
	Map<String, List<Reply>> getReply(List<String> idList);
		
	/**
     *  @Description: 批量删除回复信息
     *  @author lxp
     *  @params  [reply] 回复id集合
     *  @return 回复对象集
     *  @exception
     **/
	public void deleteReplyByIds (String[] ids);

}
