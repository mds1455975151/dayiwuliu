package com.tianrui.quartz.LED;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.ILEDCountService;
import com.tianrui.service.util.TimeUtils;

/**
 * LED定时任务
 * @author jh
 *
 */
@Component
public class LEDSchedule {
	Logger logger = LoggerFactory.getLogger(LEDSchedule.class);
	@Autowired
	ILEDCountService lEDCountService;
	
	/** 每两个小时执行*/
	@Scheduled(cron="0 0 0/2 * * ?")
	public void LEDCountAll() throws Exception{
		Long timeBegin = System.currentTimeMillis();
		logger.info("LED定时任务开始"+TimeUtils.LongZoString(timeBegin));
		lEDCountService.utpConfig("upt");
		String dataStr = new SimpleDateFormat("yyyy-MM").format(new Date());
		//当月开始时间
		Long date = new SimpleDateFormat("yyyy-MM").parse(dataStr).getTime();
		//运费统计
		lEDCountService.payAmountCount(date, indexLong(date),dataStr);
		//头部统计数据
		lEDCountService.allCountData();
		//////////////
		String tdStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Long tdate = new SimpleDateFormat("yyyy-MM-dd").parse(tdStr).getTime();
		//统计当天数据
		lEDCountService.allCountToday(tdate);
		//运量统计
		lEDCountService.billCouAl(date, indexLong(date),dataStr);
		//车主
		lEDCountService.venderCount();
		//货主
		lEDCountService.ownerCount();
		//货物类别统计
		lEDCountService.billCargo();
		//车型统计
		lEDCountService.vehicleType();
		//车辆归属地
		lEDCountService.vehicleAddress();
		//车辆使用频率
		lEDCountService.vehicleRate();
		lEDCountService.utpConfig("conf");
		logger.info("LED定时任务结束"+TimeUtils.LongZoString(System.currentTimeMillis())+"耗时/毫秒"+(System.currentTimeMillis()-timeBegin));
	}
	
	/** 运费统计*/
//	@Scheduled(cron="0/10 * * * * ?")
	public void payAmountCount() throws Exception{
		System.out.println("-----------------运费统计开始---------------------");
		List<Long> list = new ArrayList<Long>();
		list.add(1506787200000l);
		list.add(1504195200000l);
		list.add(1501516800000l);
		list.add(1498838400000l);
		list.add(1496246400000l);
		list.add(1493568000000l);
		list.add(1490976000000l);
		for(Long tm : list){
			String dataStr = new SimpleDateFormat("yyyy-MM").format(new Date(tm));
			//当月开始时间
			Long date = new SimpleDateFormat("yyyy-MM").parse(dataStr).getTime();
			//运费统计
			lEDCountService.payAmountCount(date, indexLong(date),dataStr);
		}
		System.out.println("-----------------运费统计结束---------------------");
	}
	/** 头部统计数据
	 * @throws ParseException */
//	@Scheduled(cron="0/20 * * * * ?")
	public void allCountData() throws ParseException{
		//头部统计数据
		lEDCountService.allCountData();
	}
	/** 统计当天数据*/
//	@Scheduled(cron="0/20 * * * * ?")
	public void countToday() throws ParseException{
		String dataStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Long date = new SimpleDateFormat("yyyy-MM-dd").parse(dataStr).getTime();
		//统计当天数据
		lEDCountService.allCountToday(date);
	}
	
	/** 运量统计*/
//	@Scheduled(cron="0/10 * * * * ?")
	public void ledIndex() throws Exception{
		System.out.println("--------------运量统计开始---");
		List<Long> list = new ArrayList<Long>();
		list.add(1506787200000l);
		list.add(1504195200000l);
		list.add(1501516800000l);
		list.add(1498838400000l);
		list.add(1496246400000l);
		list.add(1493568000000l);
		list.add(1490976000000l);
		for(Long tm : list){
			String dataStr = new SimpleDateFormat("yyyy-MM").format(new Date(tm));
			//当月开始时间
			Long date = new SimpleDateFormat("yyyy-MM").parse(dataStr).getTime();
			//运量统计
			lEDCountService.billCouAl(date, indexLong(date),dataStr);
		}
		System.out.println("--------------运量统计结束---");
	}
	
	/** 车主*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void venderCount() throws Exception{
		//车主
		lEDCountService.venderCount();
	}
	
	/** 货主*/
//	@Scheduled(cron="0/20 * * * * ?")
	public void ownerCount() throws Exception{
		System.out.println("开始");
		//货主
		lEDCountService.ownerCount();
		System.out.println("结束");
	}
	
	/** 货物类别统计*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void billCargo() throws Exception{
		//货物类别统计
		lEDCountService.billCargo();
	}
	/** 车型统计*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void vehicleType() throws Exception{
		//车型统计
		lEDCountService.vehicleType();
	}
	/** 车辆归属地*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void vehicleAddress() throws Exception{
		//车辆归属地
		lEDCountService.vehicleAddress();
	}
	/** 车辆使用频率*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void vehicleRate() throws Exception{
		//车辆使用频率
		lEDCountService.vehicleRate();
	}
	public Long indexLong(Long tm){
		Date date=new Date(tm);//取时间
	    Calendar calendar = new GregorianCalendar(); 
	    calendar.setTime(date); 
//	    calendar.add(calendar.YEAR, 1);//把日期往后增加一年.整数往后推,负数往前移动
//	    calendar.add(calendar.DAY_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
//	    calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
//	    calendar.add(calendar.WEEK_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
	    calendar.add(calendar.MONTH, 1);//获取当前月份
	    date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
		return date.getTime();
	}
}
