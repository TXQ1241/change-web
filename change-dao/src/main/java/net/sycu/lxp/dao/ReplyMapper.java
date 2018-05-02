package net.sycu.lxp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.sycu.lxp.pojo.Reply;

@Repository("replyMapper")
public interface ReplyMapper {
	
    int insert(Reply reply);
    
    List<Reply> getReplyByCommentsIds(List<String> commentsIds);
    
    void deleteReplyByIds(String [] replyIds);
}