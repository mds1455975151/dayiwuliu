package com.tianrui.web.smvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.service.impl.VehicleDriverService;
import com.tianrui.web.util.SessionManager;
import com.tianrui.web.util.WebUtils;

public class WebAuthInterceptor implements HandlerInterceptor{
	
	private static final ThreadLocal<Long> PerformanceCache = new ThreadLocal<Long>();

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
		if (handler != null && HandlerMethod.class.isAssignableFrom(handler.getClass())) {


			// 记录性能日志
			Object[] logParams = { System.currentTimeMillis() - PerformanceCache.get(),
					request.getRequestURI(), request.getRemoteAddr(), WebUtils.getIpAddr(request),
					request.getHeader("Accept-Encoding"), request.getHeader("Content-Length"),
					request.getHeader("Content-Type"), request.getHeader("Connection"),
					request.getHeader("User-Agent"), request.getHeader("Accept") };

			LoggerFactory.getLogger("perf").info("WEB {millsec={}}, {uri={}}, {Remote-Addr={}}, {Host={}}, {Accept-Encoding={}} ,"
								+ "{Content-Length={}} ,{Content-Type={}}, {Connection={}} {User-Agent={}} {Accept={}}",
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
		boolean flag =true;
		//访问日志
		LoggerFactory.getLogger("access").info("WEB Access[{}]  ", request.getRequestURL());
		//性能日志
		PerformanceCache.set(System.currentTimeMillis());
		
		//用户权限操作需要
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		AuthValidation authType = handlerMethod.getMethodAnnotation(AuthValidation.class);
		if(authType !=null){
			MemberVo member =SessionManager.getSessionMember(request);
			//实名认证
			if( StringUtils.equals(authType.autyType(),Constant.AUTHCHECK_USER ) ){
				if( StringUtils.equals(member.getCompanypercheck(),Constant.AUTHSTATUS_PASS) || StringUtils.equals(member.getUserpercheck(),Constant.AUTHSTATUS_PASS)  ){
				}else{
					flag =false;
					response.sendRedirect("/trwuliu/common/certification?type=1"); 
				}
			//司机认证	
			}else if(StringUtils.equals(authType.autyType(),Constant.AUTHCHECK_DRIVBR )){
				if( !StringUtils.equals(member.getDriverpercheck(),Constant.AUTHSTATUS_PASS) ){
					flag =false;
					response.sendRedirect("/trwuliu/common/certification?type=2"); 
				}
			//是否是货主
			}else if(StringUtils.equals(authType.autyType(),Constant.AUTHCHECK_OWNER )){
				if(StringUtils.isBlank(member.getOrgid())||StringUtils.isBlank(member.getOrgStatus())){
					response.sendRedirect("/trwuliu/common/toOwner"); 
					flag =false;
				}
				if(StringUtils.equals("2",member.getOrgStatus())){
					response.sendRedirect("/trwuliu/common/toOwner?type=1"); 
					flag =false;
				}
				//是否是车主
			}else if(StringUtils.equals(authType.autyType(),Constant.AUTHCHECK_VEHICLE_OWNER )){
				WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
				IVehicleDriverService vehicleDriverService=(VehicleDriverService)wac.getBean("vehicleDriverService");
				Boolean vflag =  vehicleDriverService.getIFvehicleOwer(member.getId());
				if(!vflag){
					response.sendRedirect("/trwuliu/common/certification?type=4"); 
					flag =false;
				}
			}
		}
		return flag;
	}

	
	
}
