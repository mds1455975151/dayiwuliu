package com.tianrui.quartz.plan;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.common.utils.DateUtil;

public class GetPlanAndCompletedTrigger {
	/**
	 * 定时任务参数
	 */
	private String para ; 
	@Autowired
	protected ICargoPlanService cargoPlanService;

	public String getPara() {  
        return para;  
    }     
    public void setPara(String para) {  
        this.para = para;  
    }     
    
    public void getPlanAndCompleted() {  
    	Long st = new Date().getTime();
        System.out.println("定时器正常启动:"  + " ! 时间是 :" + DateUtil.getDateString());  
        //获取已过时的货运计划
        try {
        	List<PlanResp> ls = cargoPlanService.findPlanByEndTime(st);
        	if(ls != null && ls.size() > 0){
        		for(PlanResp plan :ls){
        			try {
        				PlanConfirmReq planConfirmReq = new PlanConfirmReq();
            			planConfirmReq.setId(plan.getId());
						cargoPlanService.completePlan(planConfirmReq);
					} catch (Exception e) {
						e.printStackTrace();
					}
        			System.out.println(plan.getId());
            	}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        
		System.out.println("定时任务完成总时间："+(new Date().getTime()-st));
    }
}
