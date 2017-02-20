package com.tianrui.web.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.common.vo.Result;
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

	
	ValidateCode validateCode;
	
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
		
		HttpSession session = request.getSession();
		ValidateCode validateCode = new ValidateCode();
		VdCode vc = validateCode.getRandcode();
		session.removeAttribute("VdCode");
		session.setAttribute("VdCode", vc);
		session.removeAttribute("LoginCode");
		session.setAttribute("LoginCode", vc);
		Result rs = Result.getSuccessResult();
		rs.setData(vc.getImageString());
		return rs;
	}
}
