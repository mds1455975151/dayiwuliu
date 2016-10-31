package com.tianrui.trwl.admin.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class OverrideInterceptor implements HandlerInterceptor {
	
	public List<String> noFilterList;
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}
	
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String menuId=request.getParameter("menuId");
		if(modelAndView!=null && menuId!=null && !"".equals(menuId)){
			modelAndView.addObject("menuId", menuId);
		}

	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public List<String> getNoFilterList() {
		return noFilterList;
	}

	public void setNoFilterList(List<String> noFilterList) {
		this.noFilterList = noFilterList;
	}

}
