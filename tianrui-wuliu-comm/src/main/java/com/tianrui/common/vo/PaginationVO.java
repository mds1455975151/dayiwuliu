package com.tianrui.common.vo;

import java.util.List;

/**
 * 
 * @类描述：分页数据封装类
 * @创建人：tank
 * @创建时间：2016年1月17日下午5:14:57
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午5:14:57
 * @修改备注：
 *
 */
public class PaginationVO<T> {
	/**
	 * 数据总条数
	 */
	private long total;
	
	private List<T> list;
	// 当前页码
	private int pageNo;
	//每页条数
	private int pageSize;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getTotal() {
		return total;
	}
	public int getTotalInt() {
		return (int)total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public PaginationVO(long total, int pageNo) {
		super();
		this.total = total;
		this.pageNo = pageNo;
	}

	public PaginationVO() {
		super();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
