package net.sycu.lxp.vo;

public class OrdersVo extends PageVo{
	
    private String id;
    private String goodsId;
    private String sellerId;
    private String buyersId;
    private String status;
    private String goodsIds;
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
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getBuyersId() {
		return buyersId;
	}
	public void setBuyersId(String buyersId) {
		this.buyersId = buyersId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGoodsIds() {
		return goodsIds;
	}
	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}
    
}
