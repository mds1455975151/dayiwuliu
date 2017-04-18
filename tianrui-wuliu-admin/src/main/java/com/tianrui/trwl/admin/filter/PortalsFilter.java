package com.tianrui.trwl.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tianrui.service.admin.bean.Users;


public class PortalsFilter implements Filter {
	String excludedUrl = "";
	String[] excludedUrlArray={};
	final String loginUrl="/user/login"; //登陆页面url
	final String logininUrl="/user/loginin"; //登陆
	final String resourcesUrl="/resources"; //不拦截静态资源
	final String getValCode  = "/user/getValCode";
	final String wxloginUrl  = "/weixin/login/loginPage";
	final String wxlogininUrl="/weixin/login/loginin"; //登陆
	final String wxloginin="/weixin/login/wxLogin"; //登陆
	final String wysqweixin="/weixin/login/MP_verify_yjjzuHc971cUloGB.txt";
	@Override
	public void destroy() {

	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession(true);
			String contextPath=req.getContextPath();
			Users sessionUser = (Users)session.getAttribute("session_user");
			//不拦截资源文件 和登录请求
		if (req.getRequestURI().contains("/error/")
				|| req.getRequestURI().contains(resourcesUrl)
				|| req.getRequestURI().contains(getValCode)
				|| req.getRequestURI().equals(contextPath+wxloginUrl)
				|| req.getRequestURI().equals(contextPath+wxlogininUrl)
				|| req.getRequestURI().equals(contextPath+loginUrl)
				|| req.getRequestURI().equals(contextPath+wxloginin)
				|| req.getRequestURI().equals(contextPath+wysqweixin)
				|| req.getRequestURI().equals(contextPath+logininUrl)){
			chain.doFilter(request, response);
		}else if(req.getRequestURI().equals("/")){
				res.sendRedirect(contextPath+loginUrl);
		}else{
			if (sessionUser == null) {
				res.sendRedirect(contextPath+loginUrl);
			}else{
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		excludedUrl = config.getInitParameter("excludedUrl");
		if(excludedUrl!=null && !"".equals(excludedUrl)){
			 excludedUrlArray = excludedUrl.split(",");
		}
	}

}