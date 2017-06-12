package com.tianrui.quartz.count.day;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.ICountIndexService;
import com.tianrui.api.intf.ICountService;
import com.tianrui.api.req.count.CountSelectReq;

/**
 * 
 * @类描述：每天执行的定时任务
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
	
<<<<<<< HEAD
	/**
	 * 每天早上1点触发
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 1 * * ? *")
=======
	//@Scheduled(cron="0 0 0/12  * * ? ")
>>>>>>> 52746b456cc53371dd4be6471ad9feb95a1fc743
    public void everyDay() throws Exception{  
		countIndexService.everyDay(null);
	   
//		CountSelectReq req = new CountSelectReq();
//	   //-ok
//	   countService.everyDay(req);
//	   //-ok
//	   countService.routeHot(req);
//	   //查询各省打点情况
//	   countService.selectpostition();
    }  
}
