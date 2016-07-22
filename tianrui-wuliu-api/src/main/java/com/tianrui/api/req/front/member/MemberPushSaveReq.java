package com.tianrui.api.req.front.member;


import com.tianrui.api.req.BaseReq;

public class MemberPushSaveReq extends BaseReq{

	private static final long serialVersionUID = 3497731544666445253L;
	private String appId;
	private String memberId;
	private String pushId;
	//iso:2 安卓:1
	private int appType;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public int getAppType() {
		return appType;
	}

	public void setAppType(int appType) {
		this.appType = appType;
	}  
	
	

}
