package com.tianrui.web.app.action.money;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.money.intf.ICapitalAccountService;
import com.tianrui.api.money.intf.ICapitalRecordService;
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.money.intf.IWithdrawRecordService;
import com.tianrui.api.req.app.AppMemberReq;
import com.tianrui.api.req.money.CapitalAccountByIdReq;
import com.tianrui.api.req.money.CheckPasswordReq;
import com.tianrui.api.req.money.FindCapitalRecordByIdReq;
import com.tianrui.api.req.money.FindCapitalRecordReq;
import com.tianrui.api.req.money.FindPendingBillMoneyReq;
import com.tianrui.api.req.money.FindPendingMoneyByIdReq;
import com.tianrui.api.req.money.FindWithdrawByIdReq;
import com.tianrui.api.req.money.FindWithdrawRecordReq;
import com.tianrui.api.req.money.SavePasswordReq;
import com.tianrui.api.req.money.SaveWithdrawReq;
import com.tianrui.api.resp.money.CapitalAccountResp;
import com.tianrui.api.resp.money.FindCapitalRecordResp;
import com.tianrui.api.resp.money.FindPendingBillMoneyResp;
import com.tianrui.api.resp.money.FindWithdrawRecordResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.common.vo.UserLoginVo;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/money")
public class AppMoneyAction {

	@Autowired
	ICapitalAccountService capitalAccountService;
	@Autowired
	ICapitalRecordService capitalRecordService;
	@Autowired
	IPendingBillMoneyService pendingBillMoneyService;
	@Autowired
	IWithdrawRecordService withdrawRecordService;
	@Autowired
	private CacheClient cache ;
	
	/**
	 * 设置密码
	 * */
	@RequestMapping(value="/saveOrUptAcountPassword",method=RequestMethod.POST)
	@ApiParamRawType(SavePasswordReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult saveOrUptAcountPassword(AppParam<SavePasswordReq> appParam) throws Exception{
		Head head = appParam.getHead();
		SavePasswordReq req = appParam.getBody();
		req.setId(head.getId());
		req.setCellphone(head.getAccount());
		Result rs = capitalAccountService.saveOrUptAcountPassword(req);
		return AppResult.valueOf(rs);
	}
	
	/***
	 * 校验验证码
	 * @param appParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkValCode",method=RequestMethod.POST)
	@ApiParamRawType(AppMemberReq.class)
	@ResponseBody
	public AppResult checkValCode(AppParam<AppMemberReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		boolean codeValidate = false;//验证码认证
		String key = CacheHelper.buildKey(CacheModule.MOENY_APP_UPT_PASSWORD, new String[]{"3",appParam.getBody().getAccount()});
		UserLoginVo value=(UserLoginVo) cache.getObj(key,UserLoginVo.class);
		if(value!=null){
			String validateCode=value.getUserCode()+"";
			if(appParam.getBody().getAuthCode().equals(validateCode)){
				codeValidate=true;
				//cache.remove(key);
			}
		}
		if(!(StringUtils.equals(Constant.authCodeValue, appParam.getBody().getAuthCode() )|| codeValidate)){
			rs.setCode("123");
			rs.setError("验证码校验失败");
		}
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 校验密码
	 * */
	@RequestMapping(value="/checkPassword",method=RequestMethod.POST)
	@ApiParamRawType(CheckPasswordReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult checkPassword(AppParam<CheckPasswordReq> appParam) throws Exception{
		Head head = appParam.getHead();
		CheckPasswordReq req = appParam.getBody();
		req.setId(head.getId());
		req.setCellphone(head.getAccount());
		Result rs = capitalAccountService.checkPassword(req);
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 用户提现申请
	 * */
	@RequestMapping(value="/withdrawSave",method=RequestMethod.POST)
	@ApiParamRawType(SaveWithdrawReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult withdrawSave(AppParam<SaveWithdrawReq> appParam) throws Exception{
		Head head = appParam.getHead();
		//TODO
		SaveWithdrawReq req = appParam.getBody();
		req.setCellphone(head.getAccount());
		req.setUserId(head.getId());
		req.setBegintime(System.currentTimeMillis());
		req.setCapitalno(UUIDUtil.getId());
		Result rs = withdrawRecordService.save(req);
		return AppResult.valueOf(rs);
	}
	
	@RequestMapping(value="/getCapitalAccount",method=RequestMethod.POST)
	@ApiParamRawType(CapitalAccountByIdReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult getCapitalAccount(AppParam<CapitalAccountByIdReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		Head head = appParam.getHead();
		CapitalAccountResp resp = capitalAccountService.getByCellphone(head.getAccount());
		if(resp == null){
			appResult.setCode("1");
			appResult.setMessage("未找到资金账户");
		}else{
			appResult.setReturnData(resp);
		}
		return appResult;
	}
	
	/**
	 * 用户提现记录
	 * */
	@RequestMapping(value="/withdrawRecordSelect",method=RequestMethod.POST)
	@ApiParamRawType(FindWithdrawRecordReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult withdrawRecordSelect(AppParam<FindWithdrawRecordReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		FindWithdrawRecordReq req = appParam.getBody();
		Head head = appParam.getHead();
		req.setCellPhone(head.getAccount());
		PaginationVO<FindWithdrawRecordResp> page = withdrawRecordService.select(req);
		appResult.setReturnData(page.getList());
		appResult.setTotal(page.getTotalInt());
		return appResult;
	}
	
	/**
	 * 用户提现详情
	 * */
	@RequestMapping(value="/withdrawSelectByid",method=RequestMethod.POST)
	@ApiParamRawType(FindWithdrawByIdReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult withdrawSelectByid(AppParam<FindWithdrawByIdReq> appParam) throws Exception{
		FindWithdrawByIdReq req = appParam.getBody();
		Result rs = withdrawRecordService.selectByWithdrawId(req);
		return AppResult.valueOf(rs);
	}
	

	/**
	 * 司机运费记录
	 * */
	@RequestMapping(value="/pendingBillMoneySelect",method=RequestMethod.POST)
	@ApiParamRawType(FindPendingBillMoneyReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult pendingBillMoneySelect(AppParam<FindPendingBillMoneyReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		FindPendingBillMoneyReq req = appParam.getBody();
		Head head = appParam.getHead();
		req.setCellphone(head.getAccount());
		PaginationVO<FindPendingBillMoneyResp> page = pendingBillMoneyService.select(req);
		appResult.setReturnData(page.getList());
		appResult.setTotal(page.getTotalInt());
		return appResult;
	}
	
	/**
	 * 司机运费详情
	 * */
	@RequestMapping(value="/pendingBillMoneyById",method=RequestMethod.POST)
	@ApiParamRawType(FindPendingMoneyByIdReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult pendingBillMoneyById(AppParam<FindPendingMoneyByIdReq> appParam) throws Exception{
		FindPendingMoneyByIdReq req = appParam.getBody();
		Result rs = pendingBillMoneyService.selectPendingBillMoneyById(req);
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 用户资金流水
	 * */
	@RequestMapping(value="/capitalRecordSelect",method=RequestMethod.POST)
	@ApiParamRawType(FindCapitalRecordReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult capitalRecordSelect(AppParam<FindCapitalRecordReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		FindCapitalRecordReq req = appParam.getBody();
		Head head = appParam.getHead();
		req.setCellphone(head.getAccount());
		PaginationVO<FindCapitalRecordResp> page = capitalRecordService.select(req);
		appResult.setReturnData(page.getList());
		appResult.setTotal(page.getTotalInt());
		return appResult;
	}
	
	/**
	 * 用户资金流水详情
	 * */
	@RequestMapping(value="/capitalRecordById",method=RequestMethod.POST)
	@ApiParamRawType(FindCapitalRecordByIdReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult capitalRecordById(AppParam<FindCapitalRecordByIdReq> appParam) throws Exception{
		FindCapitalRecordByIdReq req = appParam.getBody();
		Result rs = capitalRecordService.selectCapitalRecordById(req);
		return AppResult.valueOf(rs);
	}
}
