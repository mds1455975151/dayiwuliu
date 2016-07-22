package com.tianrui.api.req.admin;

import java.util.List;

import com.tianrui.api.req.BaseReq;

public class RoleDeleteReq extends BaseReq{


	private static final long serialVersionUID = -7190183760043617974L;
	private Integer id;
	
	private List<Integer> idList;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	
	
}
