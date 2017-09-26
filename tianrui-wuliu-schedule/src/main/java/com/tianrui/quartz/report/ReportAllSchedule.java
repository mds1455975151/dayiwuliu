package com.tianrui.quartz.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.IReportAllInputService;

@Component  
public class ReportAllSchedule {

	Logger logger = LoggerFactory.getLogger(ReportAllSchedule.class);
	
	@Autowired
	IReportAllInputService reportAllInputService;
	
	
	/**
	 * 计划报表步骤:
	 * 	1  客商档案,线路档案,签收人档案 缓存更新
	 *  2  删除所有计划
	 *  3 重新添加计划
	 *  
	 * 账单报表步骤:(只考虑安联账单)
	 * 	1  用户档案,银行卡档案,客商档案,线路档案信息缓存更新
	 *  2  删除所有安联账单
	 *  3 分页查询账单,并插入
	 *  
	 * 运单报表步骤:(开票运单和安联运单)
	 * 	#开票运单
	 *  1  删除报表开票运单 只删除2017-08-01之后 签收时间为空的运单
	 *  2  获取db的开票运单总数
	 *  3 分页获取并批量数据插入 
	 *  #大易运单
	 *  1  删除未完成的大易运单报表 只删除2017-08-01之后 状态不为已签收的运单
	 *  2  获取当前报表中的运单id
	 *  3 分页获取db的大于运单,比对是否在报表中存在,不存在则批量插入             
	 */
	@Scheduled(cron="0 0 2 * * ?")
	public void reportSchedule() throws Exception{
		long begin =System.currentTimeMillis();
		logger.info("报表定时调度任务开始");
		//缓存更新
		reportAllInputService.cacheUpdate();
		//计划更新
		//reportAllInputService.planUpdate();
		//账单更新
		//reportAllInputService.payAlianUpdate();
		//安联运单更新
		reportAllInputService.billAlianUpdate();
		//平台运单更新
		reportAllInputService.billPtUpdate();
		logger.info("报表定时调度任务结束,reportSchedule总耗时:{}ms",(System.currentTimeMillis()-begin));
	}

}
