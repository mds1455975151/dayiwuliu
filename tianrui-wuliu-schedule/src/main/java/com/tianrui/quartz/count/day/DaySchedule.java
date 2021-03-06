package com.tianrui.quartz.count.day;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.ICountIndexService;

/**
 * 
 * @类描述：首页统计定时任务
 * @创建人：lsj
 * @创建时间：2016年10月29日下午2:47:17
 *
 * @修改人：lsj
 * @修改时间：2016年10月29日下午2:47:17
 * @修改备注：
 *
 */
@Component  
public class DaySchedule {
	
	@Autowired
	ICountIndexService countIndexService;
	
	/**
	 * 每天早上1点触发
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 1 * * ?")
    public void everyDay() throws Exception{  
		countIndexService.everyDay(null);
    }  
}
