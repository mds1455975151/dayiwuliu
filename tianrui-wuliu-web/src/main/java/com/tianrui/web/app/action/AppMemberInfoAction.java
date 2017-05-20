package com.tianrui.web.app.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IFileService;
import com.tianrui.api.intf.ISystemMemberInfoRecordService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.front.member.MemberInfoReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.resp.front.member.MemberInfoMassageResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.impl.MemberPushService;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/MemberInfo")
public class AppMemberInfoAction {

	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	private IFileService iFileService;
	@Autowired
	protected ISystemMemberInfoRecordService systemMemberInfoRecordService;
	@Autowired
	CacheClient cacheClient;
	@Autowired
	MemberPushService memberPushService;
	
	/**
	 * 
	 * @描述:根据账号查询用户信息
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年6月30日下午4:34:06
	 */
	@RequestMapping(value="/findMemberCellphone",method=RequestMethod.POST)
	@ApiParamRawType(MemberReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult findMemberCellphone(AppParam<MemberReq> appParam) throws Exception{
		
		MemberResp resp = systemMemberService.findMemberByTelnum(appParam.getBody());
		AppResult result = new AppResult();
		if(resp == null){
			result.setCode("1");
			result.setMessage("账号为空");
			return result;
		}
		result.setCode("000000");
		result.setReturnData(resp);
		return result;
	}
	
	/**
	 * 
	 * @描述:个人认证
	 * @param appParam
	 * @return
	 * @throws Exception 
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年6月30日上午10:34:53
	 */
	@RequestMapping(value="/personalAuthen",method=RequestMethod.POST)
	@ApiParamRawType(MemberInfoReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult personalAuthen(AppParam<MemberInfoReq> appParam) throws Exception{
		Result result = Result.getSuccessResult();
		
		MemberInfoReq req = appParam.getBody();
		req.setMemberId(appParam.getHead().getId());
		
		result= systemMemberInfoRecordService.personalAuthentication(req);
		return AppResult.valueOf(result);
	}
	/**
	 * 
	 * @描述:企业认证
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年8月11日下午3:12:43
	 */
	@RequestMapping(value="/companyAuthen",method=RequestMethod.POST)
	@ApiParamRawType(MemberInfoReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult companyAuthen(AppParam<MemberInfoReq> appParam) throws Exception{
		Result result = Result.getSuccessResult();
		
		MemberInfoReq req = appParam.getBody();
		req.setMemberId(appParam.getHead().getId());
		result= systemMemberInfoRecordService.enterpriseAuthentication(req);
		return AppResult.valueOf(result);
	}
	/**
	 * 
	 * @描述:司机认证
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年6月30日上午10:43:07
	 */
	@RequestMapping(value="/driverAuthen",method=RequestMethod.POST)
	@ApiParamRawType(MemberInfoReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult driverAuthen(AppParam<MemberInfoReq> appParam) throws Exception{
		Result result = Result.getSuccessResult();
		
		MemberInfoReq req = appParam.getBody();
		req.setMemberId(appParam.getHead().getId());
		result= systemMemberInfoRecordService.driverAuthentication(req);
		return AppResult.valueOf(result);
	}
	/**
	 * 
	 * @描述:查看认证失败原因
	 * @return
	 * @throws Exception 
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年7月1日上午11:38:37
	 */
	@RequestMapping(value="/authenReason",method=RequestMethod.POST)
	@ApiParamRawType(MemberInfoReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult authenReason(AppParam<MemberInfoReq> appParam) throws Exception{
		String id = appParam.getHead().getId();
		MemberInfoMassageResp resp = systemMemberService.findInfoMassageById(id);
		AppResult result = new AppResult();
		if(resp == null){
			result.setCode("1");
			result.setMessage("账号不存在");
			return result;
		}
		result.setCode("000000");
		result.setReturnData(resp);
		return result;
	}
	/**
	 * 
	 * @描述:app退出登录
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年7月4日上午11:42:21
	 */
	@RequestMapping(value="/removeLogin",method=RequestMethod.POST)
	@ApiParamRawType(MemberInfoReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult removeLogin(AppParam<MemberInfoReq> appParam) throws Exception{
		String mobile = appParam.getHead().getAccount();
		String mid = appParam.getHead().getId();
		String appIdCard =appParam.getHead().getAppIdCard();
		AppResult result = new AppResult();
		
		//TODO 1 删除缓存 
		String userKey =CacheHelper.buildKey(CacheModule.LOGIN_APP, mobile);
		cacheClient.remove(userKey);
		// 2删除手机号绑定的推送id   
		memberPushService.deletePushWithMId(mid);
		
		result.setCode("000000");
		result.setReturnData(null);
		return result;
	}
	
}
