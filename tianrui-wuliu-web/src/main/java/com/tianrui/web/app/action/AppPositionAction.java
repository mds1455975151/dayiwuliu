package com.tianrui.web.app.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IMemberPositionService;
import com.tianrui.api.req.front.position.PositionSaveReq;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
  * @ClassName: AppPositionAction 
  * @Description: 上传当前位置
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年7月5日 上午10:35:07 
  *
 */
@Controller
@RequestMapping("/app/position")
public class AppPositionAction {
	
	public Logger logger = LoggerFactory.getLogger(AppPositionAction.class);
	@Autowired
	IMemberPositionService positionService;

	//获取承运计划列表
	@RequestMapping(value="/uploadPosition",method=RequestMethod.POST)
	@ApiParamRawType(PositionSaveReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult uploadPosition(AppParam<PositionSaveReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
	
		//拼装查询条件
		PositionSaveReq saveBean =appParam.getBody();	
		saveBean.setCurrId(uId);
		Result rs =positionService.savePosition(saveBean);
		
		return AppResult.valueOf(rs);
	}
	
	
	
}
