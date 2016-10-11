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

public class SessionManager {


	//登录成功后添加cookie
	public static void setSessionMember(HttpServletRequest request,MemberResp sessionMember,HttpServletResponse response) {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		IMemberVoService memberVoService =wac.getBean(IMemberVoService.class);
		MemberVo memberVo =memberVoService.get(sessionMember.getId(),true);
		
		CacheClient cacheClient =wac.getBean(CacheClient.class);
		//保存cookie到共享缓存2小时
		cacheClient.saveObject(setCookie(response), memberVo,8*60*60);
	}
	//获取用户信息
	public static MemberVo getSessionMember(HttpServletRequest request) {
		MemberVo vo =null;
		Cookie cookie =getCookie(request);
		if( cookie !=null && StringUtils.isNotBlank(cookie.getValue())){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			CacheClient cacheClient =wac.getBean(CacheClient.class);
			vo =cacheClient.getObj(cookie.getValue(), MemberVo.class);
		}
		return vo;
	}
	//登出时候删除用户缓存信息
	public static void removeSessionMember(HttpServletRequest request){
		Cookie cookie =getCookie(request);
		if( cookie !=null && StringUtils.isNotBlank(cookie.getValue())){
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			CacheClient cacheClient =wac.getBean(CacheClient.class);
			cacheClient.remove(cookie.getValue());
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
	
	
	static String setCookie(HttpServletResponse response){
		String ksessionid =UUIDUtil.getId();
		Cookie cookie = new Cookie("KSESSIONID",ksessionid);
		cookie.setPath("/");
		response.addCookie(cookie);
		return ksessionid;
	}
	static Cookie getCookie(HttpServletRequest request){
		Cookie cookie =null;
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
		for(Cookie item : cookies){
		    if(item.getName().equalsIgnoreCase("KSESSIONID")){
		    	cookie=item;
		    	System.out.println("###  COOKIIE ["+cookie.getName()+","+cookie.getValue()+","+cookie.getPath()+"]");
		    	break;
		    };
		}
		return cookie;
	}
	
}
