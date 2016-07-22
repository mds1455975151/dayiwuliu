package com.tianrui.web.smvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.JsonUtil;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.web.util.WebUtils;

public class ApiParamInterceptor implements HandlerInterceptor{
	
	private static final ThreadLocal<AppParam<?>> AppParamCache = new ThreadLocal<AppParam<?>>();
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

			LoggerFactory.getLogger("perf").info("APP {millsec={}}, {uri={}}, {Remote-Addr={}}, {Host={}}, {Accept-Encoding={}} ,"
								+ "{Content-Length={}} ,{Content-Type={}}, {Connection={}} {User-Agent={}} {Accept={}}",
								logParams);
			
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView model)
			throws Exception {
	
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean flag =false;
		String jsonParam = request.getParameter("param");
		if( StringUtils.isNotBlank(jsonParam) ){
			HandlerMethod handlerMethod = (HandlerMethod) handler;

			ApiParamRawType apiRawType = handlerMethod.getMethodAnnotation(ApiParamRawType.class);
			ApiTokenValidation apiTokenValidation = handlerMethod.getMethodAnnotation(ApiTokenValidation.class);
			
			//日志初始化
			LoggerFactory.getLogger("access").info("APP Access[{}] - {} ", request.getRequestURL(),
					jsonParam);
			PerformanceCache.set(System.currentTimeMillis());
			
			if( apiRawType !=null ){
				AppParam<?> appParamObj =  JsonUtil.getAppParam(jsonParam, apiRawType.value());
				// 将转换后的ApiParam对象临时存储在线程缓存内
				AppParamCache.set(appParamObj);
				
				//验证签名串
				if( !JsonUtil.validateSign(jsonParam.trim(), appParamObj.getSign(), Constant.apiAuthKey) ){
					// 防篡改失败 验证失败
					LoggerFactory.getLogger(Constant.ACCESS).warn("拦截到非法的API请求[apiSign校验失败] - {}", jsonParam);
					writeJsonResponse(response,AppResult.valueOf(ErrorCode.PARAM_PARAM_ERROR));
					//验证token
				}else if(apiTokenValidation !=null  ){
					String tokenId =appParamObj.getHead().getTokenId();
					String mobile = appParamObj.getHead().getAccount();
					if( StringUtils.isNotBlank(tokenId) && StringUtils.isNotBlank(mobile) ){
						WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
						CacheClient cache=(CacheClient)wac.getBean("cacheClient");
						//缓存默认保存一天
						String userKey =CacheHelper.buildKey(CacheModule.LOGIN_APP, mobile);
						String cacheKey =cache.getString(userKey);
						if( StringUtils.isNotBlank(cacheKey) && StringUtils.equals(cacheKey, tokenId) ){
							String key =CacheHelper.buildKey(CacheModule.MEMBERLOGIN_APP, tokenId);
							Object obj =cache.getObj(key, MemberVo.class);
							if( obj !=null ){
								MemberVo user=(MemberVo)obj;
								if( StringUtils.equals(user.getCellphone(), mobile) ){
									flag=true;
									appParamObj.getHead().setId(user.getId());
								}
							}
						}
						
					}
					if( !flag ){
						//  token 验证失败
						writeJsonResponse(response,AppResult.valueOf(ErrorCode.PARAM_TOKEN_ERROR));
					}
				}else{
					flag=true;
				}
			}
		}else{
			//参数异常  jsonParam为空
			writeJsonResponse(response,AppResult.valueOf(ErrorCode.PARAM_NULL_ERROR));
		}
		return flag;
	}


	private void writeJsonResponse(HttpServletResponse response, AppResult apiResult) {
		
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(JSON.toJSON(apiResult).toString());
			response.getWriter().flush();
		} catch (Exception e) {
			
		}
	}
	
	
	
	/**
	 * 用于向被拦截的方法中填充ApiParam参数的切面；<br/>
	 * 被拦截的方法参数必须以ApiParam参数开头，如下：<br/>
	 * 
	 * somethingMethodName(ApiParam<?> apiParam, ... *);
	 * 
	 * @author Lei
	 */
	public static class ApiParamHandlerPointcut {

		public Object forAround(ProceedingJoinPoint joinPoint) throws Throwable {
			Object apiResultObj = null;

			Object[] args = joinPoint.getArgs();
			if (args != null && args.length > 0 && args[0] != null && args[0] instanceof AppParam) {

				// 为参数赋值
				args[0] = AppParamCache.get();

				// 调用被拦截的方法
				apiResultObj = joinPoint.proceed(args);
			} else {

				apiResultObj = joinPoint.proceed();
			}
			return apiResultObj;
		}
	}
}
