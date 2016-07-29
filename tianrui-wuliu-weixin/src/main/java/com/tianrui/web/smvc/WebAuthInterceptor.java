package com.tianrui.web.smvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.common.vo.MemberVo;
import com.tianrui.web.util.SessionManager;

public class WebAuthInterceptor implements HandlerInterceptor{
	
	private static final ThreadLocal<Long> PerformanceCache = new ThreadLocal<Long>();

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
		if (handler != null && HandlerMethod.class.isAssignableFrom(handler.getClass())) {


			// 记录性能日志
			Object[] logParams = { System.currentTimeMillis() - PerformanceCache.get(),
					request.getRequestURI()};

			LoggerFactory.getLogger("perf").info("WEB {millsec={}}, {uri={}}}",
								logParams);
			
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView model)
			throws Exception {
		if( model!=null && StringUtils.isNotBlank(model.getViewName()) ){
			MemberVo member =SessionManager.getSessionMember(request);
			model.addObject("user",member);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//访问日志
		LoggerFactory.getLogger("access").info("WEB Access[{}]  ", request.getRequestURL());
		//性能日志
		PerformanceCache.set(System.currentTimeMillis());
		
		//用户权限操作需要
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		AuthValidation authType = handlerMethod.getMethodAnnotation(AuthValidation.class);
		return true;
	}

	
	
}
