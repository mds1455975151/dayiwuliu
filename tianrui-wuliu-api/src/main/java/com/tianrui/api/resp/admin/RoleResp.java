package com.tianrui.api.resp.admin;

import java.util.ArrayList;
import java.util.List;

import com.tianrui.api.req.BaseReq;
import com.tianrui.api.resp.BaseResp;

public class RoleResp extends BaseResp{


	private static final long serialVersionUID = -7190183760043617974L;
	private Integer id;
    private String name;
    private String description;
    private String createtime;
    private String number;
    
    private String usercounts;
    
    // yes no
    private String allow;
    // 0 为拥有 1 拥有
    private int own;
    
    //用户集合
    private List<UserResp> userRespList;
    //菜单集合
    private List<MainMenuResp> menuRespList;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public List<UserResp> getUserRespList() {
		return userRespList;
	}
	public void setUserRespList(List<UserResp> userRespList) {
		this.userRespList = userRespList;
	}
	public List<MainMenuResp> getMenuRespList() {
		return menuRespList;
	}
	public void setMenuRespList(List<MainMenuResp> menuRespList) {
		this.menuRespList = menuRespList;
	}
	public String getAllow() {
		return allow;
	}
	public void setAllow(String allow) {
		this.allow = allow;
	}
	
	public int hashCode() {
		return id;
	}
	
	public boolean equals(RoleResp obj) {
		boolean flag =false;
		if(  obj.getId() !=null && this.id !=null){
			flag = obj.getId().intValue()==this.id.intValue();
		}
		
		return flag;
	}
	public int getOwn() {
		return own;
	}
	public void setOwn(int own) {
		this.own = own;
	}
	public String getUsercounts() {
		return usercounts;
	}
	public void setUsercounts(String usercounts) {
		this.usercounts = usercounts;
	}
	
	
}
