package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class MainMenuReq extends BaseReq{


	private static final long serialVersionUID = -7190183760043617974L;
	private String menuName;
	private Integer id;
	private Integer userId;
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}
