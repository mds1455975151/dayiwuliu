package com.tianrui.web.filter;

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

import com.tianrui.common.vo.MemberVo;
import com.tianrui.web.util.SessionManager;

/**
 * 
 * @类描述：会员是否登录 过滤器
 * 错误响应页面
 * @创建人：tank
 * @创建时间：2016年5月11日下午3:37:30
 *
 * @修改人：tank
 * @修改时间：2016年5月11日下午3:37:30
 * @修改备注：
 *
 */
public class PortalsFilter implements Filter {
	//不处理的请求
	String excludedUrl = "";
	String[] excludedUrlArray={};
	public void destroy() {

	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String contextPath=req.getContextPath();
			if(req.getRequestURI().equals("/")||req.getRequestURI().equals("/publicMember/index")){
				res.sendRedirect(contextPath+"/count/route");
			}else if(req.getRequestURI().contains("/trwuliu/")){
				//nc支付回调验证 不做session处理
				if(req.getRequestURI().contains("/driverNcConfirm")){
					chain.doFilter(request, response);
				}else{
					MemberVo sessionMember=SessionManager.getSessionMember((HttpServletRequest)request);
					if (sessionMember == null) {
						res.sendRedirect(contextPath+"/publicMember/loginPage");	
					}else {
						chain.doFilter(request, response);
					}
				}
			}else {
				chain.doFilter(request, response);
			}
	}

	public void init(FilterConfig config) throws ServletException {
		excludedUrl = config.getInitParameter("excludedUrl");
		if(excludedUrl!=null && !"".equals(excludedUrl)){
			 excludedUrlArray = excludedUrl.split(",");
		}
	}

}