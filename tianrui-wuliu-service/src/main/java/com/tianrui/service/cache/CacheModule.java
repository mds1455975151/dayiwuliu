package com.tianrui.service.cache;

/**
 * 
 * @类描述：缓存模块类型枚举类
 * @创建人：tank
 * @创建时间：2016年1月16日下午2:34:07
 *
 * @修改人：tank
 * @修改时间：2016年1月16日下午2:34:07
 * @修改备注：
 *
 */
public enum CacheModule {
	
	CAPITALACCOUNT("capital-","用户资金账户"),
	
	MEMBERVO("mvo-","用户实体"),
	ORG("org-","组织档案"),
	
	WEB_PC_ROLE("r-","pc端用户对用的角色选择"),
	WEB_APP_ROLE("r1-","移动端用户对应的角色选择"),
	
	
	REGISTER("register-","PC注册"),
	REGISTER_APP("register1-","APP注册"),
	LOGIN_APP("login_","app端登录"),
	LOGIN_PC("login1_","pc端登录"),
	LOGIN_PASS("login_pass","禁止访问的IP"),
	VCODE_NUMBER("code_number","获取验证码次数"),
	VCODE_ID("vcode_id","获取验证码次数"),
	VEHICLE_NO("vehicle_no","临时车牌号"),
	RESETPASS("resetPass-","PC端重置密码验证码"),
	RESETPASS_APP("resetPass1-","APP端重置密码验证码"),
	ZJXL_TOKEN("zjxl_token","中交兴路token"),
	
	FILE_KS("ks-","客商信息档案"),
	FILE_XL("xl-","线路信息档案"),
	FILE_QSL("qsl-","签收人信息档案"),
	FILE_MEMBER("m-","签收人信息档案"),
	FILE_YHK("yhk-","银行卡信息档案"),
	
	LOGIN_APP_AUTHCODE("loginAppCode-","APP端登录验证码"),
	MEMBERLOGIN_APP("logincodeApp-","APP登录标识"),
	MOENY_APP_UPT_PASSWORD("moneyUptPassword-","钱包修改密码"),
	ADMINLOGIN("loginAdmin-","后台管理用户登录");
	
	private String code;
	private String msg;
	
	
	private CacheModule(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
