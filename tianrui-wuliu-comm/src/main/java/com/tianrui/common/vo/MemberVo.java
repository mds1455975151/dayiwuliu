package com.tianrui.common.vo;

import org.apache.commons.lang.StringUtils;

/**
 * 
  * @ClassName: MemberVo 
  * @Description: 前台账户用户信息vo
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月20日 下午2:35:18 
  *
 */
public class MemberVo {

	private String id;
	private String cellphone;
	
	//微信唯一标识
	private String openid;
	
	//用户名称
	private String nickname;
	private String userName;
	private String companyName;
	private String compCode;
	
	//头像
	private String avatarspath;
	
	//认证状态
	private String userpercheck;
	private String driverpercheck;
	private String companypercheck;
	
	
	private String orgid;
	private String orgStatus;
	
	private String idcard;
	private String idcardimage;
	private String driverimage;
	
	//为APP服务的tokenId
	private String tokenId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOrgStatus() {
		return orgStatus;
	}
	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAvatarspath() {
		return avatarspath;
	}
	public void setAvatarspath(String avatarspath) {
		this.avatarspath = avatarspath;
	}
	public String getUserpercheck() {
		return userpercheck;
	}
	public void setUserpercheck(String userpercheck) {
		this.userpercheck = userpercheck;
	}
	public String getDriverpercheck() {
		return driverpercheck;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public void setDriverpercheck(String driverpercheck) {
		this.driverpercheck = driverpercheck;
	}
	public String getCompanypercheck() {
		return companypercheck;
	}
	public void setCompanypercheck(String companypercheck) {
		this.companypercheck = companypercheck;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getIdcardimage() {
		return idcardimage;
	}
	public void setIdcardimage(String idcardimage) {
		this.idcardimage = idcardimage;
	}
	public String getDriverimage() {
		return driverimage;
	}
	public void setDriverimage(String driverimage) {
		this.driverimage = driverimage;
	}
	
	
	public String getRealName(){
		String realName ="";
		if( StringUtils.isNotBlank(companyName) ){
			realName=companyName;
		}else if(  StringUtils.isNotBlank(userName) ){
			realName=userName;
		}else if(  StringUtils.isNotBlank(nickname) ){
			realName=nickname;
		}else if(  StringUtils.isNotBlank(cellphone) ){
			realName=cellphone;
		}
		return realName;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	

	
}
