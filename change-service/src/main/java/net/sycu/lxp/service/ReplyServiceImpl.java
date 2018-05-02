package net.sycu.lxp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.dao.ReplyMapper;
import net.sycu.lxp.pojo.Reply;

@Service("replyService")
@Transactional
public class ReplyServiceImpl implements IReplyService {
	
	@Autowired
	@Qualifier("replyMapper")
	ReplyMapper replyMapper;
	
	public void save(Reply reply) {
		replyMapper.insert(reply);
	}

	public Map<String, List<Reply>> getReply(List<String> idList) {
		Map <String, List<Reply>> replyMap = new HashMap<String, List<Reply>>();
		List<Reply> allReply = replyMapper.getReplyByCommentsIds(idList);
		if (allReply != null && allReply.size() > 0) {
			for (Reply reply: allReply) {
				List<Reply> replyList = replyMap.get(reply.getCommentsId());
				if(replyList != null) {
					replyList.add(reply);
					replyMap.put(reply.getCommentsId(), replyList);
				}
			}
		}
		return replyMap;
	}
	
	public void deleteReplyByIds (String[] ids) {
		replyMapper.deleteReplyByIds(ids);
	}
	
}
