package com.tianrui.quartz.LED;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tianrui.common.utils.DateUtil;

/**
 * LED定时任务
 * @author jh
 *
 */
@Component
public class LEDSchedule {

	public void ledIndex(){
		
	}
	
	public static void main(String[] args) {
		//1506787200000  --2017-10-01
		//1504195200000  --2017-09-01
		//1501516800000  --2017-08-01
		//1498838400000  --2017-07-01
		//1496246400000  --2017-06-01
		//1493568000000  --2017-05-01
		//1490976000000  --2017-04-01
//		System.out.println(getLong());
		List<Long> list = new ArrayList<Long>();
		list.add(1506787200000l);
		list.add(1504195200000l);
		list.add(1501516800000l);
		list.add(1498838400000l);
		list.add(1496246400000l);
		list.add(1493568000000l);
		list.add(1490976000000l);
		for(Long tm : list){
			System.out.println(tm+"---"+getDataStr(tm));
			System.out.println(indexLong(tm)+"---"+getDataStr(indexLong(tm)));
			System.out.println("-----------------------------------");
		}
	}
	
	public static Long indexLong(Long tm){
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
	
	/** */
	public static String getDataStr(Long lon){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date(lon));
	}
	
	/** 获取时间*/
	public static Long getLong(){
		String dateStr = "2017-04";
		try {
			return new SimpleDateFormat("yyyy-MM").parse(dateStr).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
