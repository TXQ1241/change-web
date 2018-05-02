package net.sycu.lxp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.sycu.lxp.pojo.GoodsComments;
import net.sycu.lxp.vo.CommentsVo;

@Repository("commentsMapper")
public interface GoodsCommentsMapper {

    int insert(GoodsComments goodsComments);
    List<GoodsComments> getComments(CommentsVo commentsVo);
    Integer getCommentsCount (CommentsVo commentsVo);
    void deleteCommentsByIds(String [] ids);
    
}