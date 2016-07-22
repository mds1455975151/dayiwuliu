package com.tianrui.common.constants;

public interface CacheTableInfo {

	String PROJECTNAME = Constant.PRE_REDIS;
	
	//登录验证码
	String LOGIN_AUTH_CODE =PROJECTNAME+"code1-";
	//找回密码验证码
	String FIND_PASS_AUTH_CODE =PROJECTNAME+"code2-";
	//修改密码验证
	String EDIT_PASS_AUTH_CODE =PROJECTNAME+"code3-";
}
