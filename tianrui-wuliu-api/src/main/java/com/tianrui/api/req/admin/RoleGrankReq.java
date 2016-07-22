package com.tianrui.api.req.admin;

import java.util.List;

import com.tianrui.api.req.BaseReq;

public class RoleGrankReq extends BaseReq{


	private static final long serialVersionUID = -7190183760043617974L;
	private Integer rid;
	
	private List<Integer> mIds;
	

	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public List<Integer> getmIds() {
		return mIds;
	}
	public void setmIds(List<Integer> mIds) {
		this.mIds = mIds;
	}

	
}
