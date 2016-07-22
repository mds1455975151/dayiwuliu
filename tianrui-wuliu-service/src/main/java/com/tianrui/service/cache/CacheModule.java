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
	
	MEMBERVO("mvo-","用户实体"),
	ORG("org-","组织档案"),
	
	
	REGISTER("register-","PC注册"),
	REGISTER_APP("register1-","APP注册"),
	LOGIN_APP("login_","app端登录"),
	
	RESETPASS("resetPass-","PC端重置密码验证码"),
	RESETPASS_APP("resetPass1-","APP端重置密码验证码"),
	
	MEMBERLOGIN_APP("logincodeApp-","APP端登录验证码"),
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
