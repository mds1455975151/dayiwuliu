package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.cargoplan.PlanTemplateReq;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.common.vo.Result;

/**
 * 计划模版
  * @ClassName: ICargoPlanTemplateService 
  * @Description: 计划模版查询
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月24日 下午5:02:25 
  *
 */
public interface ICargoPlanTemplateService {
	
	List<PlanResp> findPlanTemplat(PlanTemplateReq req);
	
	PlanResp findOne(PlanTemplateReq req);
	
	Result delTemplat(PlanTemplateReq req);
}
