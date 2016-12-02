package com.tianrui.trwl.admin.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.tianrui.common.vo.MemberVo;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;

/**
 * 前台web会员信息管理
 * @author jh
 *
 */
public class WebManager {

	/** 清除前台web会员session
	 * id-前台用户id*/
	public static void removeWebSession(String id,String cellPhone){
		if(StringUtils.isNotBlank(id)){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			CacheClient cacheClient =wac.getBean(CacheClient.class);
			String key_user_pc_login=CacheHelper.buildKey(CacheModule.LOGIN_PC, id);
			cacheClient.remove(key_user_pc_login);
			
			String key_user_app_login=CacheHelper.buildKey(CacheModule.LOGIN_APP, cellPhone);
			cacheClient.remove(key_user_app_login);
		}
	}
}
