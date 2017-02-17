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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianrui.common.vo.MemberVo;
import com.tianrui.web.action.member.MemberAction;
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
	
	public static Logger logger =LoggerFactory.getLogger(PortalsFilter.class);
	
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
			try {
				String ip = getIpAddr(req);
				logger.info("本次访问IP地址={}",ip);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(req.getRequestURI().equals("/")||req.getRequestURI().equals("/publicMember/index")||req.getRequestURI().equals("/publicMember/loginPage")){
				res.sendRedirect(contextPath+"/count/route");
			}else if(req.getRequestURI().contains("/trwuliu/")){
				//nc支付回调验证 不做session处理
				if(req.getRequestURI().contains("/driverNcConfirm")){
					chain.doFilter(request, response);
				}else{
					MemberVo sessionMember=SessionManager.getSessionMember((HttpServletRequest)request);
					if (sessionMember == null) {
						res.sendRedirect(contextPath+"/count/route");	
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
	
	//获取访问者ip
	public String getIpAddr(HttpServletRequest request) throws Exception{  
        String ip = request.getHeader("X-Real-IP");  
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {  
            return ip;  
        }  
        ip = request.getHeader("X-Forwarded-For");  
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {  
            // 多次反向代理后会有多个IP值，第一个为真实IP。  
            int index = ip.indexOf(',');  
            if (index != -1) {  
                return ip.substring(0, index);  
            } else {  
                return ip;  
            }  
        } else {  
            return request.getRemoteAddr();  
        }  
    }  
	

}