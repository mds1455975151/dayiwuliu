package com.tianrui.quartz.LED;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.ILEDCountService;

/**
 * LED定时任务
 * @author jh
 *
 */
@Component
public class LEDSchedule {

	@Autowired
	ILEDCountService lEDCountService;
	/** 运费统计*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void payAmountCount() throws Exception{
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
			//当月结束时间
			lEDCountService.payAmountCount(date, indexLong(date),dataStr);
		}
	}
	/** 头部统计数据*/
	@Scheduled(cron="0/20 * * * * ?")
	public void allCountData(){
		lEDCountService.allCountData();
	}
	
	/** 运量统计*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void ledIndex() throws Exception{
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
			//当月结束时间
			lEDCountService.billCouAl(date, indexLong(date),dataStr);
		}
	}
	
	/** 车主*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void venderCount() throws Exception{
		lEDCountService.venderCount();
	}
	
	/** 货主*/
//	@Scheduled(cron="0/20 * * * * ?")
	public void ownerCount() throws Exception{
		System.out.println("开始");
		lEDCountService.ownerCount();
		System.out.println("结束");
	}
	
	/** 货物类别统计*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void billCargo() throws Exception{
		lEDCountService.billCargo();
	}
	/** 车型统计*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void vehicleType() throws Exception{
		lEDCountService.vehicleType();
	}
	/** 车辆归属地*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void vehicleAddress() throws Exception{
		lEDCountService.vehicleAddress();
	}
	/** 车辆使用频率*/
//	@Scheduled(cron="0/5 * * * * ?")
	public void vehicleRate() throws Exception{
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
