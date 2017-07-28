package com.tianrui.quartz.count.day;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.service.impl.AuditReportService;
@Component  
public class AuditReportSchedule {
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
	@Scheduled(cron="0/5 * *  * * ? ")
	public  void auditReport()throws Exception{
		System.out.println("定时任务测试");
		auditReportService.timingTask();
//		Timer timer = new Timer();
//		 timer.schedule(new TimerTask() {
//			public void run() {
//		        System.out.println("-------设定要指定任务--------");
//		        try {
//					auditReportService.timingTask();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//		      }
//		    }, 1*60*1000);
	}
}
