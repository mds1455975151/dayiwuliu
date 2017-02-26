package com.tianrui.web.action.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.web.util.ValidateCode;
import com.tianrui.web.util.VdCode;
/**
 * 图片查看器
 * @author jh
 *
 */
@Controller
@RequestMapping("imageView")
public class ImageViewAction {

	
	/**图片旋转*/
	@RequestMapping("index")
	public ModelAndView index(String imageUrl){
		ModelAndView view = new ModelAndView();
		view.addObject("imageUrl", imageUrl);
		view.setViewName("img");
		return view;
	}
	
	/** 获取验证码图片*/
	@RequestMapping("code")
	@ResponseBody
	public Result code(HttpServletRequest request,
			HttpServletResponse response){
		//设COOKIE
		String ksessionid =UUIDUtil.getId();
		Cookie cookie = new Cookie("VCODEID", ksessionid);
		cookie.setPath("/");
		response.addCookie(cookie);
		cookie.getValue();
		
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		CacheClient cacheClient =wac.getBean(CacheClient.class);
		String key=CacheHelper.buildKey(CacheModule.VCODE_ID, ksessionid);
		
		ValidateCode validateCode = new ValidateCode();
		VdCode vc = validateCode.getRandcode();
		cacheClient.saveString(key, vc.getCode(), 3*60);
		Result rs = Result.getSuccessResult();
		rs.setData(vc.getImageString());
		return rs;
	}
}
