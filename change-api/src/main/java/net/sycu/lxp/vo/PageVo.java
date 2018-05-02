package net.sycu.lxp.vo;

/**
 * 分页对象
 * @author lxp
 *
 */
public class PageVo {
    private Integer pageNum;//页数
    private Integer pageSize;//每页条数
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}   
}
