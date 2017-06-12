//package com.tianrui.quartz.count.hour;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.tianrui.api.intf.ICountService;
//import com.tianrui.api.req.count.CountSelectReq;
//
///**
// * 
// * @类描述：每天执行的定时任务
// * @创建人：lsj
// * @创建时间：2016年10月29日下午2:47:17
// *
// * @修改人：lsj
// * @修改时间：2016年10月29日下午2:47:17
// * @修改备注：
// *
// */
//@Component  
//public class HourSchedule {
//	
//	@Autowired
//	ICountService countService;
//	/**
//	 * 每天早上1点触发
//	 * @throws Exception
//	 */
//	@Scheduled(cron="0 0 1 * * ? *")
//    public void everyDay() throws Exception{  
//	   CountSelectReq req = new CountSelectReq();
//	   req.setSelecttime(getDay());
//	   //-ok
//	   countService.everyHour(req);
//	   //-ok
//	   countService.billEveryDay(req);
//	   //-ok
//	   countService.payDetil();
//    }  
//	public long getDay() {
//		Date date = new Date();
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		c.set(Calendar.HOUR_OF_DAY, 0);
//		c.set(Calendar.MINUTE, 0);
//		c.set(Calendar.SECOND, 0);
//		c.set(Calendar.MILLISECOND, 0);
//		return c.getTimeInMillis();
//	}
//}
