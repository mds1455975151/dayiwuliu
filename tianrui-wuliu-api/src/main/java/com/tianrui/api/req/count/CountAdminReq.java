package com.tianrui.api.req.count;

import com.tianrui.api.req.BaseReq;

public class CountAdminReq extends BaseReq{

	private String id;

    private String type;

    private String remark;

    private String data;

    private String creater;

    private Long creatertime;

    private String modify;

    private Long modifytime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Long getCreatertime() {
		return creatertime;
	}

	public void setCreatertime(Long creatertime) {
		this.creatertime = creatertime;
	}

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public Long getModifytime() {
		return modifytime;
	}

	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
    
}
