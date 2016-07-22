package com.tianrui.api.req.front.system;

import com.tianrui.api.req.BaseReq;

public class DataDictReq extends BaseReq{


	private static final long serialVersionUID = -7190183760043617974L;
	
	/**
	 * 父菜单编号
	 */
	private String subCode;
	/**
	 * 父菜单名称
	 */
	private String subname;
	/**
	 * 子菜单编号
	 */
	private String itemcode;

    /**
     * 子菜单名称
     */
    private String itemname;

	
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
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
