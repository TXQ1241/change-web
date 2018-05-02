package net.sycu.lxp.pojo;

import java.util.Date;
/**
 * 商品
 * @author lxp
 *
 */
public class Goods {
    private String id;
    
    private String ids;

    private String name;

    private String description;

    private String creatorId;

    private Date createTime;

    private String updateUserId;

    private Date updateTime;

    private Integer isDeleted;
    
    private String imagesSrc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

	public String getImagesSrc() {
		return imagesSrc;
	}

	public void setImagesSrc(String imagesSrc) {
		this.imagesSrc = imagesSrc;
	}
    
}