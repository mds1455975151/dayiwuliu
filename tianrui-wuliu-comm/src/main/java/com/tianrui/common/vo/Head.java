package com.tianrui.common.vo;

public class Head {

	private String tokenId;
	private String account;
	private String callType;
	private String callTypeNo;
	private String mobileType;
	private String appVersion;
	// 默认为空或者1 车主   2:货主版本
	private String appIdCard;
	//默认为不存在
	private String id ="-1";
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getCallTypeNo() {
		return callTypeNo;
	}
	public void setCallTypeNo(String callTypeNo) {
		this.callTypeNo = callTypeNo;
	}
	public String getMobileType() {
		return mobileType;
	}
	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppIdCard() {
		return appIdCard;
	}
	public void setAppIdCard(String appIdCard) {
		this.appIdCard = appIdCard;
	}
	
}
