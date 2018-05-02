package net.sycu.lxp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sycu.lxp.pojo.GoodsComments;
import net.sycu.lxp.vo.CommentsVo;

public interface IGoodsCommentsService {
	
	/**
     *  @Description: 通过评论对象获取评论
     *  @author lxp
     *  params  [commentsVo] 评论查询对象
     *  @return 评论集合
     *  @exception
     **/
	List<GoodsComments> getComments(CommentsVo commentsVo);
	
	/**
     *  @Description: 获取评论组合对象
     *  @author lxp
     *  params  [commentsVo] 评论查询对象
     *  @return 评论集合
     *  @exception
     **/
	List<CommentsVo> getCommentsVo(CommentsVo commentsVo);
	
	/**
     *  @Description: 保存评论信息
     *  @author lxp
     *  params  [comments] 评论对象
     *  @exception
     **/
	void save(GoodsComments comments);
	
	/**
     *  @Description: 保存评论信息
     *  @author lxp
     *  params  [comments] 评论对象
     *  @exception
     **/
	void saveComments(HttpServletRequest request);
	
	/**
	 * 获取评论的
	 * @param commentsVo
	 * @return
	 */
	Integer getCommentsCount(CommentsVo commentsVo);

}
