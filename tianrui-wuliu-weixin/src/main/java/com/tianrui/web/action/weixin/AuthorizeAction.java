package com.tianrui.web.action.weixin;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.weixin.WeixinMemberReq;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.web.action.weixin.util.entity.Tokens;
import com.tianrui.web.action.weixin.util.util.CommonUtil;
import com.tianrui.web.action.weixin.util.util.Count;
import com.tianrui.web.filter.TimeFilter;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

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
	private static Logger log = LoggerFactory.getLogger(AuthorizeAction.class);
	
	@Autowired
	private ISystemMemberService systemMemberService;
	/**
	 * 
	 * @描述:网页授权获取openid
	 * @param code
	 * @param state
	 * @return
	 * @throws Exception 
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年7月23日下午3:30:59
	 */
	@RequestMapping(value="/authorize",method=RequestMethod.GET)
	@AuthValidation
	public ModelAndView authorize(HttpServletRequest request,String code,String state) throws Exception{
		ModelAndView view = new ModelAndView();
		//获取微信用户openid
		if(StringUtils.isBlank(code)){
			view.setViewName("/member/loginPage");
			return view;
		}
		String openid = getopenId(code);
		WeixinMemberReq req = new WeixinMemberReq();
		req.setOpenid(openid);
		MemberResp resp = systemMemberService.findByOpenid(openid);
		request.getSession().setAttribute("openid", openid);
		JSONObject obj = getMassage(openid);
		if(resp != null){
			SessionManager.setSessionMember(request, resp);
			view.addObject("headimgurl", obj.get("headimgurl"));
			view.setViewName("/weixin/about");
		}else{
			view.addObject("openid", openid);
			view.setViewName("/member/loginPage");
		}
		return view;
	}
	//网页授权通过code获取openid
	public String getopenId(String code){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		JSONObject js = CommonUtil.httpsRequest(url.replace("APPID", Count.APPID).replace("SECRET", Count.APPSECRET).replace("CODE", code), "POST", null);
		String openid=null;
		try {
			openid = js.getString("openid");
		} catch (Exception e) {
			openid = "";
			log.debug("获取openid失败");
		}
		return openid;
	}
	//获取用户基本信息
	public JSONObject getMassage(String openid){
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		Tokens token = TimeFilter.TOKEN;
		String access_token = "";
		if(token == null){
			token = CommonUtil.getToken();
		}
		JSONObject js = CommonUtil.httpsRequest(url.replace("ACCESS_TOKEN", token.getAccessToken()).replace("OPENID", openid), "POST", null);
		System.out.println(js.toString());
		return js;
	}
}
