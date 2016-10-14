package com.tianrui.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.tianrui.api.intf.IMemberVoService;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;

public class SessionManager {


	//登录成功后添加cookie
	public static void setSessionMember(HttpServletRequest request,MemberResp sessionMember,HttpServletResponse response) {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		IMemberVoService memberVoService =wac.getBean(IMemberVoService.class);
		MemberVo memberVo =memberVoService.get(sessionMember.getId(),true);
		
		CacheClient cacheClient =wac.getBean(CacheClient.class);
		//保存cookie对应的用户数据到共享缓存2小时
		String key_cookie_2_user=setCookie(response);
		cacheClient.saveObject(key_cookie_2_user, memberVo,8*60*60);
		//保证 单账户单一地点登录   需要保存用户-cookie信息
		String key_user_2_login=CacheHelper.buildKey(CacheModule.LOGIN_PC, memberVo.getId());
		cacheClient.saveString(key_user_2_login, key_cookie_2_user, 8*60*60);
	}
	//获取用户信息
	public static MemberVo getSessionMember(HttpServletRequest request) {
		MemberVo vo =null;
		Cookie cookie =getCookie(request);
		if( cookie !=null && StringUtils.isNotBlank(cookie.getValue())){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			//使用缓存通过cookie获取当前用户信息
			CacheClient cacheClient =wac.getBean(CacheClient.class);
			vo =cacheClient.getObj(cookie.getValue(), MemberVo.class);
			if( vo !=null && StringUtils.isNotBlank(vo.getId())){
				//比对缓存里面的用户对应的cookie信息是否跟request保持一致
				String key_user_2_login=CacheHelper.buildKey(CacheModule.LOGIN_PC, vo.getId());
				String cookie_redis=cacheClient.getString(key_user_2_login);
				//如果cookie值内 缓存的不一致 则此账户在别处登录
				if( !StringUtils.equals(cookie_redis, cookie.getValue()) ){
					vo =null;
					cacheClient.remove(cookie.getValue());
				}
			}else{
				vo =null;
			}
		}
		return vo;
	}
	//登出时候删除用户缓存信息
	public static void removeSessionMember(HttpServletRequest request){
		Cookie cookie =getCookie(request);
		if( cookie !=null && StringUtils.isNotBlank(cookie.getValue())){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			CacheClient cacheClient =wac.getBean(CacheClient.class);
			
			//查询存在用户对应的cookie信息 则删除
			MemberVo vo =cacheClient.getObj(cookie.getValue(), MemberVo.class);
			if( vo !=null && StringUtils.isNotBlank(vo.getId())){
				String key_user_2_login=CacheHelper.buildKey(CacheModule.LOGIN_PC, vo.getId());
				cacheClient.remove(key_user_2_login);
			}
			//删除cookie对用的用户信息
			cacheClient.remove(cookie.getValue());
		}
	}
	
	//用户选择之后添加角色信息
	public static void setSessionRole(HttpServletRequest request,String role) {
		MemberVo member =getSessionMember(request);
		if(  member !=null &&StringUtils.isNotBlank(member.getId())){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			CacheClient cacheClient =wac.getBean(CacheClient.class);
			String key=CacheHelper.buildKey(CacheModule.WEB_PC_ROLE, member.getId());
			//保存cookie到共享缓存永久
			cacheClient.saveString(key, role,-1);
		}
		
	}
	//获取用户角色信息
	public static String getSessionRole(HttpServletRequest request) {
		String rs =null;
		MemberVo member =getSessionMember(request);
		if( member !=null && StringUtils.isNotBlank(member.getId())){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			CacheClient cacheClient =wac.getBean(CacheClient.class);
			String key=CacheHelper.buildKey(CacheModule.WEB_PC_ROLE, member.getId());
			rs =cacheClient.getString(key);
		}
		return rs;
	}
	//获取用户角色信息
	public static String getSessionRole(String memberid) {
		String rs =null;
		if(  StringUtils.isNotBlank(memberid)){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			CacheClient cacheClient =wac.getBean(CacheClient.class);
			String key=CacheHelper.buildKey(CacheModule.WEB_PC_ROLE,memberid);
			rs =cacheClient.getString(key);
		}
		return rs;
	}
	//删除用户角色信息
	public static void removeSessionRole(HttpServletRequest request){
		MemberVo member =getSessionMember(request);
		if( member !=null && StringUtils.isNotBlank(member.getId())){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			CacheClient cacheClient =wac.getBean(CacheClient.class);
			String key=CacheHelper.buildKey(CacheModule.WEB_PC_ROLE, member.getId());
			cacheClient.remove(key);
		}
	}

	//通过cookie获取当前登录用户 并更新缓存
	public static void flushMember(HttpServletRequest request){
		Cookie cookie =getCookie(request);
		if(cookie !=null && StringUtils.isNotBlank(cookie.getValue())){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			CacheClient cacheClient =wac.getBean(CacheClient.class);
			IMemberVoService memberVoService =wac.getBean(IMemberVoService.class);
			//获取当前用户信息
			MemberVo vo =cacheClient.getObj(cookie.getValue(), MemberVo.class);
			if( vo !=null ){
				//缓存内容更新
				MemberVo memberVo =memberVoService.get(vo.getId(),true);
				cacheClient.saveObject(cookie.getValue(), memberVo,8*60*60);
			}
		};
	}
	
	
	
	public static String setCookie(HttpServletResponse response){
		String ksessionid =UUIDUtil.getId();
		Cookie cookie = new Cookie("KSESSIONID",ksessionid);
		cookie.setPath("/");
		response.addCookie(cookie);
		return ksessionid;
	}
	public static Cookie getCookie(HttpServletRequest request){
		Cookie cookie =null;
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
		for(Cookie item : cookies){
		    if(item.getName().equalsIgnoreCase("KSESSIONID")){
		    	cookie=item;
		    	break;
		    };
		}
		return cookie;
	}
	
}
