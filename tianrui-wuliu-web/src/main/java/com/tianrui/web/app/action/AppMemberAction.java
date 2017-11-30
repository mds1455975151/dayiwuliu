package com.tianrui.web.app.action;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.IFileService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.app.AppGetCodeReq;
import com.tianrui.api.req.app.AppMemberReq;
import com.tianrui.api.req.app.AppMemberRoleReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.member.MemberSaveReq;
import com.tianrui.api.req.front.member.MemberUpdateReq;
import com.tianrui.api.req.front.system.FileUploadReq;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.MakePrimaryKey;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.common.vo.UserLoginVo;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;


@Controller
@RequestMapping("/app/member")
public class AppMemberAction {
	
	public Logger logger = LoggerFactory.getLogger(AppMemberAction.class);
	
	@Autowired
	private CacheClient cache ;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	private IFileService iFileService;
	/**
	 * 
	 * @描述:移动APP登录
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 tank
	 * @创建时间 2016年5月22日下午5:20:13
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ApiParamRawType(AppMemberReq.class)
	@ResponseBody
	public AppResult login(AppParam<AppMemberReq> appParam) throws Exception{
		AppResult appResult =new AppResult();
		Result  rs =systemMemberService.applogin(appParam.getBody());
		appResult.setCode(rs.getCode());
		appResult.setMessage(rs.getError());
		appResult.setReturnData(rs.getData());
		return appResult;
	}
	/**
	 * 
	 * @描述:移动APP登录选择角色
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 tank
	 * @创建时间 2016年5月22日下午5:20:13
	 */
	@RequestMapping(value="/chooseRole",method=RequestMethod.POST)
	@ApiParamRawType(AppMemberRoleReq.class)
	@ResponseBody
	@ApiTokenValidation
	public AppResult chooseRole(AppParam<AppMemberRoleReq> appParam) throws Exception{
		AppMemberRoleReq req =appParam.getBody();
		req.setCurrId(appParam.getHead().getAccount());
		Result  rs =systemMemberService.chooseRole(req);
		return AppResult.valueOf(rs);
	}
	
	
	@RequestMapping(value="/getUploadTimeLimit",method=RequestMethod.POST)
	@ApiParamRawType(Object.class)
	@ResponseBody
	public AppResult getUploadTimeLimit(AppParam<Object> appParam){
		Result  rs =Result.getSuccessResult();
		rs.setData(3);
		return AppResult.valueOf(rs);
	}
	/**
	 * 
	 * @描述:获取手机验证码
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 tank
	 * @创建时间 2016年5月23日上午10:37:50
	 */
	@RequestMapping(value="/getValCode",method=RequestMethod.POST)
	@ApiParamRawType(AppGetCodeReq.class)
	@ResponseBody
	public AppResult getValCode(AppParam<AppGetCodeReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		if(appParam.getBody().getAccount() == null || "".equals(appParam.getBody().getAccount())){
			appResult.setCode("126");
			appResult.setMessage("手机号码不能为空！");
		}else if(appParam.getBody().getType() == null || "".equals(appParam.getBody().getType())){
			appResult.setCode("127");
			appResult.setMessage("验证码类型不能为空！");
		}else {
			Result rs= systemMemberService.sendAuthCodeMsg(appParam.getBody());
			appResult.setCode(rs.getCode());
			appResult.setMessage(rs.getError());
		}
		return appResult;
	}
	
	/**
	 * 
	 * @描述:新会员注册
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 tank
	 * @创建时间 2016年5月23日上午10:37:33
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ApiParamRawType(AppMemberReq.class)
	@ResponseBody
	public AppResult register(
			AppParam<AppMemberReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		Result rs =Result.getSuccessResult();
		if(!"".equals(appParam.getBody().getAccount()) && MakePrimaryKey.isMobileNO(appParam.getBody().getAccount())){
			MemberReq req =new MemberReq();
			req.setTelnum(appParam.getBody().getAccount());
			MemberResp member= systemMemberService.findMemberByTelnum(req);
			if( member != null){
				rs.setCode("0");
				rs.setError("您输入的手机号已注册，请重新输入");
			}else{
				boolean codeValidate = false;//验证码认证
				String key = CacheHelper.buildKey(CacheModule.REGISTER_APP, new String[]{"0",appParam.getBody().getAccount()});
				UserLoginVo value=(UserLoginVo) cache.getObj(key,UserLoginVo.class);
				if(value!=null){
					String validateCode=value.getUserCode()+"";
					if(appParam.getBody().getAuthCode().equals(validateCode)){
						codeValidate=true;
						//cache.remove(key);
					}
				}
				if(StringUtils.equals(Constant.authCodeValue, appParam.getBody().getAuthCode() )|| codeValidate){
					MemberSaveReq memberSaveReq =new MemberSaveReq();
					memberSaveReq.setCellphone(appParam.getBody().getAccount());
					memberSaveReq.setPassword(appParam.getBody().getPswdMd5());
					memberSaveReq.setStatus("1");
					memberSaveReq.setPercheckstatus((short) 0);
					memberSaveReq.setSourcetype((short) 0);
					memberSaveReq.setRegisttime(DateUtil.getDateString());
					systemMemberService.saveMember(memberSaveReq);
					// TODO 会员注册缓存存储
					member= systemMemberService.findMemberByTelnum(req);
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
		appResult.setCode(rs.getCode());
		appResult.setMessage(rs.getError());
		return appResult;
	}
	/**
	 * 
	 * @描述:重置用户密码
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 tank
	 * @创建时间 2016年5月23日上午10:37:08
	 */
	@RequestMapping(value="/updatePass",method=RequestMethod.POST)
	@ApiParamRawType(AppMemberReq.class)
	@ResponseBody
	public AppResult updatePass(AppParam<AppMemberReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		Result rs =Result.getSuccessResult();
		MemberReq req =new MemberReq();
		req.setTelnum(appParam.getBody().getAccount());
		boolean codeValidate = false;//验证码认证
		String key = CacheHelper.buildKey(CacheModule.RESETPASS_APP, new String[]{"1",appParam.getBody().getAccount()});
		UserLoginVo value=(UserLoginVo) cache.getObj(key,UserLoginVo.class);
		if(value!=null){
			String validateCode=value.getUserCode()+"";
			if(appParam.getBody().getAuthCode().equals(validateCode)){
				codeValidate=true;
				//cache.remove(key);
			}
		}
		if(StringUtils.equals(Constant.authCodeValue, appParam.getBody().getAuthCode() ) || codeValidate){
			if(!"".equals(appParam.getBody().getAccount()) && MakePrimaryKey.isMobileNO(appParam.getBody().getAccount())){
				MemberResp member= systemMemberService.findMemberByTelnum(req);
				if( member == null){
					rs.setCode("1");
					rs.setError("您输入的手机号未注册，请重新输入");
				}else {
					MemberUpdateReq up = new MemberUpdateReq();
					up.setId(member.getId());
					up.setPassword(appParam.getBody().getPswdMd5());
					systemMemberService.updateMember(up);
					rs.setCode("000000");
					//TODO 会员登录缓存存储
				}
			}else {
				rs.setCode("2");
				rs.setError("您输入的手机号不正确，请重新输入");
			}
		}else{
			rs.setCode("0");
			rs.setError("非法访问，认证码输入错误请确认");
		}
		
		appResult.setCode(rs.getCode());
		appResult.setMessage(rs.getError());
		return appResult;
	}
	
	/**
	 * 货主手机号验证码 验证
	 * @param appParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/registerValid",method=RequestMethod.POST)
	@ApiParamRawType(AppMemberReq.class)
	@ResponseBody
	public AppResult registerValid(AppParam<AppMemberReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		if(!"".equals(appParam.getBody().getAccount()) && MakePrimaryKey.isMobileNO(appParam.getBody().getAccount())){
			MemberReq req =new MemberReq();
			req.setTelnum(appParam.getBody().getAccount());
			MemberResp member= systemMemberService.findMemberByTelnum(req);
			if( member != null){
				appResult.setCode("1");
				appResult.setMessage("您输入的手机号已注册，请重新输入");
			}else{
				boolean codeValidate = false;//验证码认证
				String key = CacheHelper.buildKey(CacheModule.REGISTER_APP, new String[]{"0",appParam.getBody().getAccount()});
				UserLoginVo value=(UserLoginVo) cache.getObj(key,UserLoginVo.class);
				if(value!=null){
					String validateCode=value.getUserCode()+"";
					if(appParam.getBody().getAuthCode().equals(validateCode)){
						codeValidate=true;
					}
				}
				if(StringUtils.equals(appParam.getBody().getAuthCode(),Constant.authCodeValue ) || codeValidate){
					//cache.remove(key);
					appResult.setCode("000000");
					appResult.setMessage("验证通过");
				}else {
					appResult.setCode("1");
					appResult.setMessage("认证码输入错误请确认");
				}
			}
		}else{
			appResult.setCode("2");
			appResult.setMessage("您输入的手机号不正确，请重新输入");
		}
		logger.info(" register 返回 {}",JSON.toJSON(appResult));
		return appResult;
	}
	
	/**
	 * 货主 注册
	 * @param appParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveIdCard",method=RequestMethod.POST)
	@ApiParamRawType(AppMemberReq.class)
	@ResponseBody
	public AppResult saveIdCard(AppParam<AppMemberReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		Result rs =Result.getSuccessResult();
		if(!"".equals(appParam.getBody().getAccount()) && MakePrimaryKey.isMobileNO(appParam.getBody().getAccount())){
			MemberReq req =new MemberReq();
			req.setTelnum(appParam.getBody().getAccount());
			MemberResp member= systemMemberService.findMemberByTelnum(req);
			if( member != null){
				rs.setCode("0");
				rs.setError("您输入的手机号已注册，请重新输入");
			}else{
				MemberSaveReq memberSaveReq =new MemberSaveReq();
				if(StringUtils.isNotBlank(appParam.getBody().getImgStr())){
					FileUploadReq upload = new FileUploadReq();
					upload.setImgStr(appParam.getBody().getImgStr());
					rs = iFileService.uploadImg(upload);
					if(!StringUtils.equals(rs.getCode(), "000000")){
						appResult.setCode(rs.getCode());
						appResult.setMessage(rs.getError());
						return appResult;
					}
					if(StringUtils.isNotBlank(String.valueOf(rs.getData()))){
						memberSaveReq.setAvatarspath(rs.getData().toString());
					}
				}
				memberSaveReq.setCellphone(appParam.getBody().getAccount());
				memberSaveReq.setPassword(appParam.getBody().getPswdMd5());
				memberSaveReq.setNickname(appParam.getBody().getNickname());
				memberSaveReq.setStatus("1");
				memberSaveReq.setPercheckstatus((short) 0);
				memberSaveReq.setSourcetype((short) 0);
				memberSaveReq.setRegisttime(DateUtil.getDateString());
				systemMemberService.saveMember(memberSaveReq);
				// TODO 会员注册缓存存储
				member= systemMemberService.findMemberByTelnum(req);
			}
		}else {
			rs.setCode("2");
			rs.setError("您输入的手机号不正确，请重新输入");
		}
		logger.info(" register 返回 {}",JSON.toJSON(rs));
		appResult.setCode(rs.getCode());
		appResult.setMessage(rs.getError());
		return appResult;
	}
	
	@RequestMapping(value="/updateAdmin",method=RequestMethod.POST)
	@ApiParamRawType(AppMemberReq.class)
	@ResponseBody
	public AppResult updateAdmin(AppParam<AppMemberReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		AppMemberReq req = appParam.getBody();
		MemberUpdateReq mur = new MemberUpdateReq();
		mur.setId(appParam.getHead().getId());
		mur.setLoginname(req.getNickname());
		mur.setAvatarspath(req.getImgStr());
		boolean flag = systemMemberService.updateMember(mur);
		if(flag){
			appResult.setCode("000000");
			appResult.setMessage("修改成功！");
		}else{
			appResult.setCode("000001");
			appResult.setMessage("修改失败！");
		}
		return appResult;
	}
	
}
