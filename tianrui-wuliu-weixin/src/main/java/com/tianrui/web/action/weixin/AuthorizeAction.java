package com.tianrui.web.action.weixin;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.tianrui.web.action.weixin.util.util.CommonUtil;
import net.sf.json.JSONObject;
/**
 * 
 * @类描述：微信网页授权处理
 * @创建人：lsj
 * @创建时间：2016年7月23日下午3:31:18
 *
 * @修改人：lsj
 * @修改时间：2016年7月23日下午3:31:18
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/authorize")
public class AuthorizeAction {

	/**
	 * 
	 * @描述:网页授权获取openid
	 * @param code
	 * @param state
	 * @return
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年7月23日下午3:30:59
	 */
	@RequestMapping(value="/authorize",method=RequestMethod.GET)
	public ModelAndView authorize(String code,String state){
		ModelAndView view = new ModelAndView();
		if(StringUtils.isBlank(code)||StringUtils.isBlank(state)){
			return view;
		}
		String openid = getopenId(code);
		System.out.println("code="+code+"state"+state+"openid="+openid);
		view.addObject("openid", openid);
		view.setViewName("/member/loginPage");
		return view;
	}
	
	public String getopenId(String code){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		String appid="wxf22ce076abf3d066";
		String secret = "4add7800a76bf23866778b14d69bf6d4";
		JSONObject js = CommonUtil.httpsRequest(url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code), "POST", null);
		String openid=null;
		try {
			openid = js.getString("openid");
		} catch (Exception e) {

		}
		return openid;
	}
}
