package com.tianrui.api.resp.common;

import com.tianrui.api.resp.BaseResp;

public class DataDictResp extends BaseResp{


	private static final long serialVersionUID = 3985041522148122448L;
	
	private String id;
	/**
	 * 父节点编号
	 */
	private String subcode;
	/**
	 * 父节点名称
	 */
	private String subname;
	/**
	 * 子节点编号
	 */
	private String itemcode;
	/**
	 * 子节点名称
	 */
	private String itemname;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubcode() {
		return subcode;
	}
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	
}
