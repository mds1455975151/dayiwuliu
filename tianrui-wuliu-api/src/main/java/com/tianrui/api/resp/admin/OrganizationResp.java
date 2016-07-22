package com.tianrui.api.resp.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tianrui.api.resp.BaseResp;

public class OrganizationResp extends BaseResp{
	
	private static final long serialVersionUID = -7190183760043617974L;
	
	private String id;
	
    private String count;
	
	private String organizationname;
	
	private String organizationtype;
	
	private String organizationno;
	
	private String status;
	
	private String creator;
	
	private Long createtime;
	
	private String createtimeStr;
	
	private List<OrganizationResp> Organizationlist;
	
	public String getCreatetimeStr() {
		String rs =null;
		if(createtime !=null){
			rs =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(createtime));
		}
		return rs;
	}
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}

	public String getOrganizationtype() {
		return organizationtype;
	}

	public void setOrganizationtype(String organizationtype) {
		this.organizationtype = organizationtype;
	}

	public String getOrganizationno() {
		return organizationno;
	}

	public void setOrganizationno(String organizationno) {
		this.organizationno = organizationno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public List<OrganizationResp> getOrganizationlist() {
		return Organizationlist;
	}

	public void setOrganizationlist(List<OrganizationResp> organizationlist) {
		Organizationlist = organizationlist;
	}
	
	
}
