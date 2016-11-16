package com.tianrui.web.app.action;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IMemberPushService;
import com.tianrui.api.req.front.member.MemberPushSaveReq;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
  * @ClassName: AppMessageAction 
  * @Description:站内信
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年7月4日 10:04:40
  *
 */
@Controller
@RequestMapping("/app/push")
public class AppPushAction {
	
	public Logger logger = LoggerFactory.getLogger(AppPushAction.class);
	@Autowired
	IMemberPushService pushService;

	//上传push信息
	@RequestMapping(value="/uploadPush",method=RequestMethod.POST)
	@ApiParamRawType(MemberPushSaveReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult uploadPush(AppParam<MemberPushSaveReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
	
		//拼装查询条件
		MemberPushSaveReq saveBean =appParam.getBody();	
		saveBean.setMemberId(uId);
		//iso:2 安卓:1
		if( appParam.getHead().getCallType().equals("ios") ){
			saveBean.setAppType(2);
		}else{
			saveBean.setAppType(1);
		}
		
		
		Result rs =null;
		if( StringUtils.isNotBlank(appParam.getHead().getAppIdCard()) && StringUtils.equals(appParam.getHead().getAppIdCard(), "2") ){
			rs=pushService.savePushOwner(saveBean);
		}else{
			rs=pushService.savePush(saveBean);
		}
		
		return AppResult.valueOf(rs);
	}
	
	
}
