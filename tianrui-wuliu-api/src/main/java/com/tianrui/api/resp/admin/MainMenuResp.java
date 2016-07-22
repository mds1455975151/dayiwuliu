package com.tianrui.api.resp.admin;

import java.util.List;

import com.tianrui.api.resp.BaseResp;

public class MainMenuResp extends BaseResp {
	private static final long serialVersionUID = -7190183760043617974L;

	private Integer id;
	private String nodename;
	private String afternodename;
	private String createtime;
	private Integer parentnodeid;
	private String fronturl;
	private String afterurl;
	private Integer order1;
	private String desc1;
	private String desc2;
	private String desc3;
	private String desc4;
	
	//该菜单对应的角色
	private List<RoleResp> roleRespList;
	//该菜单包含的子菜单
	private List<MainMenuResp> nodeList;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public String getAfternodename() {
		return afternodename;
	}
	public void setAfternodename(String afternodename) {
		this.afternodename = afternodename;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getParentnodeid() {
		return parentnodeid;
	}
	public void setParentnodeid(Integer parentnodeid) {
		this.parentnodeid = parentnodeid;
	}
	public String getFronturl() {
		return fronturl;
	}
	public void setFronturl(String fronturl) {
		this.fronturl = fronturl;
	}
	public String getAfterurl() {
		return afterurl;
	}
	public void setAfterurl(String afterurl) {
		this.afterurl = afterurl;
	}
	public Integer getOrder1() {
		return order1;
	}
	public void setOrder1(Integer order1) {
		this.order1 = order1;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getDesc3() {
		return desc3;
	}
	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}
	public String getDesc4() {
		return desc4;
	}
	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}
	public List<RoleResp> getRoleRespList() {
		return roleRespList;
	}
	public void setRoleRespList(List<RoleResp> roleRespList) {
		this.roleRespList = roleRespList;
	}
	public List<MainMenuResp> getNodeList() {
		return nodeList;
	}
	public void setNodeList(List<MainMenuResp> nodeList) {
		this.nodeList = nodeList;
	}
	
	
	
	
}
