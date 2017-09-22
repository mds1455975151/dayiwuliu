package com.tianrui.quartz.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.IReportAllService;
import com.tianrui.common.utils.DateUtil;

@Component  
public class ReportAllSchedule {

	Logger logger = LoggerFactory.getLogger(ReportAllSchedule.class);
	
	@Autowired
	IReportAllService reportAllService;
	
	@Scheduled(cron="0 0 1 * * ?")
	public void plan() throws Exception{
		Long time = System.currentTimeMillis();
		logger.info("定时查询plan数据开始"+ DateUtil.getDateString());
		reportAllService.savePlan();
		logger.info("定时查询plan数据结束，耗时/毫秒"+ (System.currentTimeMillis()-time));
	}
	
	@Scheduled(cron="0 0 1 * * ?")
	public void pay() throws Exception{
		Long time = System.currentTimeMillis();
		logger.info("定时查询pay数据开始"+ DateUtil.getDateString());
		reportAllService.savePay();
		logger.info("定时查询pay数据结束，耗时/毫秒"+ (System.currentTimeMillis()-time));
	}
	
	@Scheduled(cron="0 0 1 * * ?")
	public void bill() throws Exception{
		Long time = System.currentTimeMillis();
		logger.info("定时查询bill数据开始"+ DateUtil.getDateString());
		reportAllService.saveBill();
		logger.info("定时查询bill数据结束，耗时/毫秒"+ (System.currentTimeMillis()-time));
	}
}
