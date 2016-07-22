package com.tianrui.trwl.admin.util;

import javax.servlet.http.HttpServletRequest;
import com.tianrui.service.admin.bean.Users;

public class SessionManager {


	public static void setSessionMember(HttpServletRequest request,Users users) {
		request.getSession().setAttribute("session_user", users);
	}
	
	public static Users getSessionMember(HttpServletRequest request) {
		return (Users)request.getSession().getAttribute("session_user");
	}
}
