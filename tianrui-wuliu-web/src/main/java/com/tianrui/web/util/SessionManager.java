package com.tianrui.web.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.tianrui.api.intf.IMemberVoService;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.vo.MemberVo;

public class SessionManager {


	public static void setSessionMember(HttpServletRequest request,MemberResp sessionMember) {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		IMemberVoService memberVoService =wac.getBean(IMemberVoService.class);
		MemberVo memberVo =memberVoService.get(sessionMember.getId());
		request.getSession().setAttribute("session_member", memberVo);
	}
	public static MemberVo getSessionMember(HttpServletRequest request) {
		MemberVo vo =null;
		Object obj =request.getSession().getAttribute("session_member");
		if( obj !=null ){
			vo =(MemberVo)obj;
		}
		return vo;
	}
	public static void removeSessionMember(HttpServletRequest request){
		request.getSession().removeAttribute("session_member");
	}
	public static void flushMember(HttpServletRequest request,MemberResp sessionMember){
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		IMemberVoService memberVoService =wac.getBean(IMemberVoService.class);
		request.getSession().removeAttribute("session_member");
		MemberVo memberVo =memberVoService.get(sessionMember.getId(),true);
		request.getSession().setAttribute("session_member", memberVo);
	}
}
