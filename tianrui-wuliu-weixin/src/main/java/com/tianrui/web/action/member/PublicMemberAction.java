package com.tianrui.web.action.member;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.ISendMobileMessage;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.member.MemberSaveReq;
import com.tianrui.api.req.front.member.MemberUpdateReq;
import com.tianrui.api.req.weixin.WeixinMemberReq;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.exception.ApplicationExectpion;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.MakePrimaryKey;
import com.tianrui.common.vo.Result;
import com.tianrui.common.vo.UserLoginVo;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.web.util.SessionManager;


/**
 * 
 * @类描述：平台会员模块web响应,不需要登录的
 * @创建人：tank
 * @创建时间：2016年4月22日上午11:12:02
 *
 * @修改人：tank
 * @修改时间：2016年4月22日上午11:12:02
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/publicMember")
public class PublicMemberAction {
	
	public Logger logger = LoggerFactory.getLogger(PublicMemberAction.class);
	
	@Autowired
	protected ISendMobileMessage sendMobileMessage;
	@Autowired
	CacheClient cache ;
	@Autowired
	private ISystemMemberService systemMemberService;
	/**
	 * 
	 * @描述:平台协议查看
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月23日下午4:31:10
	 */
	@RequestMapping("/protocol")
	public ModelAndView protocolPage() throws IOException{
		return new ModelAndView("/member/authentication/protocol");
	}
	/**
	 * 
	 * @描述:平台首页
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月23日下午4:31:10
	 */
	@RequestMapping("/index")
	public ModelAndView indexPage() throws IOException{
		return new ModelAndView("/member/loginPage");//临时登录页
	}
	/**
	 * 
	 * @描述:忘记密码页面
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月29日下午16:31:10
	 */
	@RequestMapping("/lostPass")
	public ModelAndView lostPass() throws IOException{
		return new ModelAndView("/member/lostPass");
	}
	/**
	 * 
	 * @描述:修改密码成功页面
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月29日下午16:31:10
	 */
	@RequestMapping("/resetpsu")
	public ModelAndView resetPSu() throws IOException{
		return new ModelAndView("/member/resetPSu");
	}
	
	/**
	 * 
	 * @描述:重置会员密码页面跳转
	 * @param tel
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月29日下午5:24:03
	 */
	@RequestMapping("/resetPassPage")
	public ModelAndView resetPassPage(String tel,String registerCode) throws IOException{
		ModelAndView mav = new ModelAndView();
		mav.addObject("tel", tel);
		mav.addObject("registerCode", registerCode);
		mav.setViewName("/member/resetPass");
		return mav;
	}
	/**
	 * 
	 * @描述:会员注册页面跳转
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月23日下午4:31:10
	 */
	@RequestMapping("/registerPage")
	public ModelAndView registerPage() throws IOException{
		return new ModelAndView("/member/registerPage");
	}

	
	/**
	 * 
	 * @描述:会员登录页面跳转
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月23日下午4:31:10
	 */
	@RequestMapping("/loginPage")
	public ModelAndView loginPage() throws IOException{
		return new ModelAndView("/member/loginPage");
	}
	
	/**
	 * 
	 * @描述: 获取手机验证码
	 * @param telnum
	 * @param type 0-注册；1-密码找回;2-验证码登陆
	 * @return
	 * errorCode 11-手机号码不合法   000000-正确  1-注册用户已存在  2-重置密码手机号不正确 3-已发送过验证码 4-用户频繁操作 5一分钟内请求次数过多
	 * @throws Exception
	 * @返回类型 Result-->json
	 * @创建人 tank
	 * @创建时间 2016年4月24日上午11:49:29
	 */
	@RequestMapping(value = "/registerCode",method=RequestMethod.POST)
	@ResponseBody
	public Result getValCode(@RequestParam(defaultValue = "") String telnum,
					@RequestParam(defaultValue = "0") String type) throws Exception{
		Result rs =systemMemberService.getValCode(telnum,type,"pc");
		return rs;
	}
	
	/**
	 * 
	 * @描述:新会员注册
	 * @param telnum
	 * @param passWord
	 * @param registerCode
	 * @param referrerTel
	 * @throws Exception
	 * @返回类型 Result-->json
	 * @创建人 tank
	 * @创建时间 2016年4月24日下午3:50:06
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Result register(
			@RequestParam(defaultValue = "") String telnum,
			@RequestParam(defaultValue = "") String passWord,
			@RequestParam(defaultValue = "") String registerCode,
			@RequestParam(defaultValue = "") String referrerTel,
			HttpServletRequest request) throws Exception{
		
		Result rs =Result.getSuccessResult();
		if(!"".equals(telnum) && MakePrimaryKey.isMobileNO(telnum)){
			MemberReq req =new MemberReq();
			req.setTelnum(telnum);
			MemberResp member = systemMemberService.findMemberByTelnum(req);
			if(member!=null){
				rs.setCode("0");
				rs.setError("您输入的手机号已注册，请重新输入");
			}else{
				boolean codeValidate = false;//验证码认证
				String key = CacheHelper.buildKey(CacheModule.REGISTER, new String[]{"0",telnum});
				UserLoginVo value=(UserLoginVo) cache.getObj(key,UserLoginVo.class);
				if(value!=null){
					String validateCode=value.getUserCode()+"";
					if(registerCode.equals(validateCode)){
						codeValidate=true;
						cache.remove(key);
					}
				}
				//增加万能验证码 111111,系统正式上线需要去除
				if(registerCode.equals("111111") || codeValidate){
					MemberSaveReq memberSaveReq =new MemberSaveReq();
					memberSaveReq.setCellphone(telnum);
					memberSaveReq.setPassword(passWord);
					memberSaveReq.setReferrer(referrerTel);
					memberSaveReq.setStatus("1");
					memberSaveReq.setPercheckstatus((short) 0);
					memberSaveReq.setSourcetype((short) 0);
					memberSaveReq.setRegisttime(DateUtil.getDateString());
					systemMemberService.saveMember(memberSaveReq);
					//会员注册缓存存储
					member= systemMemberService.findMemberByTelnum(req);
					SessionManager.setSessionMember(request, member);
				}else {
					rs.setCode("1");
					rs.setError("您输入的验证码不正确，请重新输入");
				}
			}
		}else {
			rs.setCode("2");
			rs.setError("您输入的手机号不正确，请重新输入");
		}
		logger.info(" register 返回 {}",JSON.toJSON(rs));
		return rs;
	}
	/**
	 * 
	 * @描述:重置密码验证码验证
	 * @param telnum
	 * @param registerCode
	 * @return
	 * @throws Exception
	 * @返回类型 Result-->json
	 * @创建人 tank
	 * @创建时间 2016年4月29日下午4:55:02
	 */
	@RequestMapping(value="/resetPass",method=RequestMethod.POST)
	@ResponseBody
	public Result resetPass(
			@RequestParam(defaultValue = "") String telnum,
			@RequestParam(defaultValue = "") String registerCode) throws Exception{
		Result rs =Result.getSuccessResult();
		MemberReq req =new MemberReq();
		req.setTelnum(telnum);
		if(!"".equals(telnum) && MakePrimaryKey.isMobileNO(telnum)){
			MemberResp member= systemMemberService.findMemberByTelnum(req);
			if( member == null){
				rs.setCode("0");
				rs.setError("您输入的手机号未注册，请重新输入");
			}else{
				boolean codeValidate = false;//验证码认证
				String key = CacheHelper.buildKey(CacheModule.RESETPASS, new String[]{"1",telnum});
				UserLoginVo value=(UserLoginVo) cache.getObj(key,UserLoginVo.class);
				if(value!=null){
					String validateCode=value.getUserCode()+"";
					if(registerCode.equals(validateCode)){
						codeValidate=true;
					}
				}
				//增加万能验证码 111111,系统正式上线需要去除
				if(registerCode.equals("111111") || codeValidate){
				}else {
					rs.setCode("1");
					rs.setError("认证码输入错误请确认");
				}
			}
		}else {
			rs.setCode("2");
			rs.setError("您输入的手机号不正确，请重新输入");
		}
		return rs;
	}
	/**
	 * 
	 * @描述:会员登录
	 * @param telnum
	 * @param passWord
	 * @return
	 * @throws Exception
	 * @返回类型 Result-->json
	 * @创建人 tank
	 * @创建时间 2016年5月10日下午4:46:25
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result login(@RequestParam(defaultValue = "") String cellphone,
			@RequestParam(defaultValue = "") String passWord,
			@RequestParam(defaultValue = "") String openid,
			HttpServletRequest request) throws Exception {
		Result rs =Result.getSuccessResult();
		WeixinMemberReq req =new WeixinMemberReq();
		req.setCellPhone(cellphone);
		req.setPassWord(passWord);
		req.setOpenid(openid);
		if(cellphone != "" && passWord != ""){
			try {
				rs = systemMemberService.wxlogin(req);
				MemberResp resp = (MemberResp) rs.getData();
				SessionManager.setSessionMember(request, resp);//会员登录缓存存储
			}catch (ApplicationExectpion e) {
				logger.debug("{}",e.getMessage(),e);
				rs.setCode("0");
				rs.setError(e.getMessage());
			} catch (Exception e) {
				logger.error("{}",e.getMessage(),e);
				rs.setCode("1");
				rs.setError("系统发生意外故障，请稍后再试.....");
			}
		}else {
			rs.setCode("2");
			rs.setError("用户名和密码不能为空，请输入...");
		}
		return rs;
	}
	/**
	 * 
	 * @描述:重置用户密码
	 * @param telnum
	 * @param registerCode
	 * @param passWord
	 * @return
	 * @throws Exception
	 * @返回类型 Result-->json
	 * @创建人 tank
	 * @创建时间 2016年5月5日上午9:15:02
	 */
	@RequestMapping(value="/updatePass",method=RequestMethod.POST)
	@ResponseBody
	public Result updatePass(
			@RequestParam(defaultValue = "") String telnum,
			@RequestParam(defaultValue = "") String registerCode,
			@RequestParam(defaultValue = "") String passWord,
			HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		MemberReq req =new MemberReq();
		req.setTelnum(telnum);
		boolean codeValidate = false;//验证码认证
		String key = CacheHelper.buildKey(CacheModule.RESETPASS, new String[]{"1",telnum});
		UserLoginVo value=(UserLoginVo) cache.getObj(key,UserLoginVo.class);
		if(value!=null){
			String validateCode=value.getUserCode()+"";
			if(registerCode.equals(validateCode)){
				codeValidate=true;
				cache.remove(key);
			}
		}
		//增加万能验证码 111111,系统正式上线需要去除
		if(registerCode.equals("111111") || codeValidate){
			
		}else {
			rs.setCode("0");
			rs.setError("非法访问，认证码输入错误请确认");
		}
		if(!"".equals(telnum) && MakePrimaryKey.isMobileNO(telnum)){
			MemberResp member= systemMemberService.findMemberByTelnum(req);
			if( member == null){
				rs.setCode("1");
				rs.setError("您输入的手机号未注册，请重新输入");
			}else {
				MemberUpdateReq up = new MemberUpdateReq();
				up.setId(member.getId());
				up.setPassword(passWord);
				systemMemberService.updateMember(up);
				SessionManager.setSessionMember(request, member);//会员登录缓存存储
			}
		}else {
			rs.setCode("2");
			rs.setError("您输入的手机号不正确，请重新输入");
		}
		return rs;
	}
	
}
