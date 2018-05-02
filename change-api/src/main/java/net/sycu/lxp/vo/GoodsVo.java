package net.sycu.lxp.vo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import net.sycu.lxp.pojo.Goods;
import net.sycu.lxp.pojo.User;

public class GoodsVo extends PageVo{
	
    private String id;

    private String name;

    private String creatorId;

    private String updateUserId;
    
    private String description;
    
    private String imagesSrc;
    
    private String goodsType; //1.代表买家商品，2.代表卖家商品
    
    private User sellerInfo; //买家信息
    
    private List<User> userList;
    
    public GoodsVo () {}
    
    public GoodsVo(Goods goods){
    	try {
			BeanUtils.copyProperties(this, goods);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImagesSrc() {
		return imagesSrc;
	}

	public void setImagesSrc(String imagesSrc) {
		this.imagesSrc = imagesSrc;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	public User getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(User sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
    
}
