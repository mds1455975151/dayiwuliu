package com.tianrui.quartz.plan;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.common.utils.DateUtil;

@Component 
public class GetPlanAndCompletedTrigger {
	
	private Logger logger = LoggerFactory.getLogger(GetPlanAndCompletedTrigger.class);
	/**
	 * 定时任务参数
	 */
	@Autowired
	protected ICargoPlanService cargoPlanService;

	
	@Scheduled(cron="0 0/30 *  * * ? ")
    public void getPlanAndCompleted() {  
    	Long st = new Date().getTime();
    	logger.info("定时器[GetPlanAndCompletedTrigger]启动.时间是 :" + DateUtil.getDateString());  
        //获取已过时的货运计划
    	int count =0;
        try {
        	List<PlanResp> ls = cargoPlanService.findPlanByEndTime(st);
        	if(ls != null && ls.size() > 0){
        		for(PlanResp item :ls){
        			try {
        				PlanConfirmReq planConfirmReq = new PlanConfirmReq();
            			planConfirmReq.setId(item.getId());
						cargoPlanService.completePlan(planConfirmReq);
						count++;
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
					}
            	}
        	}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        logger.info("定时任务[GetPlanAndCompletedTrigger]完成.处理数据{}条,耗时：{}",new Object[]{count,(new Date().getTime()-st)});
    }
}
