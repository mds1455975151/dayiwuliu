package com.tianrui.api.resp.admin;

import java.util.List;

import com.tianrui.api.req.BaseReq;

public class RoleMenuResp extends BaseReq{
	private static final long serialVersionUID = -7190183760043617974L;
	
	
    private Integer id;
    private String name;
    private String description;
    private String createtime;
    private String number;
    private String creator;
    private String desc1;
    private String desc2;
    private String desc3;
    private String desc4;
    
    private List<MainMenuResp> mainMenuResp;
	
}
