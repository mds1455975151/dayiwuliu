package com.tianrui.quartz.count.day;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.common.utils.DateUtil;
import com.tianrui.service.impl.AuditReportService;
/**
 * 审核通过及不通过数据
 * @author Administrator
 *
 */
@Component  
public class AuditReportSchedule {
	Logger logger = LoggerFactory.getLogger(AuditReportSchedule.class);
	@Autowired
	AuditReportService auditReportService;
	
	/**
	 * 
	 * @Title: time 
	 * @Description: 定时查询审核数据
	 * @param    
	 * @return void    
	 * @throws
	 */

	@Scheduled(cron="0 0 1 * * ?")
	public  void auditReport()throws Exception{
		Long st = new Date().getTime();
		logger.info("定时查询审核数据开始"+ DateUtil.getDateString());
		auditReportService.timingTask();
		logger.info("定时查询审核数据结束"+ (new Date().getTime()-st));
	}
}
