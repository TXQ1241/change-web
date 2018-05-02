package net.sycu.lxp.vo;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import net.sycu.lxp.pojo.Goods;
import net.sycu.lxp.pojo.GoodsComments;
import net.sycu.lxp.pojo.Reply;

public class CommentsVo extends PageVo{
	
    private String id;

    private String goodsId;

    private String userId;
    
    private String userName;

    private String content;

    private Date createTime;
    
    private String createTimeStr;
    
    private String annexSrc;
    
    private Goods goods;
    
    private List<Reply> replyList; //评论的回复信息
    
    /**
     * 空构造函数
     */
    public CommentsVo() {}
    
    public CommentsVo(GoodsComments goodsComments) {
    	try {
			BeanUtils.copyProperties(this, goodsComments);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
		
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getAnnexSrc() {
		return annexSrc;
	}

	public void setAnnexSrc(String annexSrc) {
		this.annexSrc = annexSrc;
	}
	
}
