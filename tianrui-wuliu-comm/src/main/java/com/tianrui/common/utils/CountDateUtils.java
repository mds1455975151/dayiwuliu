package com.tianrui.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CountDateUtils {

	public final static String FORMAT_DATA = "yyyy-MM-dd";
	public final static String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
	
	//获取昨天0点时刻
	public static long getLastDayMin(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//时间减少一天
		c.add(Calendar.DATE,-1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
	//获取30天前0点时刻
	public static long countMinTimeLast30(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//时间减少一天
		c.add(Calendar.DATE,-30);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	//获取今天0点时刻
	public static long getLastDayMax(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	//获取昨天0点时刻
	public static long getLastDayMin(String dataStr)throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(new SimpleDateFormat(FORMAT_DATA).parse(dataStr));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	//获取30天前0点时刻
	public static long countMinTimeLast30(String dataStr)throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(new SimpleDateFormat(FORMAT_DATA).parse(dataStr));
		//时间减少30天
		c.add(Calendar.DATE,-30);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
	//获取今天0点时刻
	public static long getLastDayMax(String dataStr) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(new SimpleDateFormat(FORMAT_DATA).parse(dataStr));
		c.add(Calendar.DATE,1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}

	
	public static long getOld30Day(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -30);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		System.out.println(c.getTime());
		return c.getTimeInMillis();
	}
	public static void main(String[] args) {
		//System.out.println(getLastDayMin(new Date()));
		//System.out.println(getLastDayMax(new Date()));
		
		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
//		//获取前月的最后一天
//        Calendar cale = Calendar.getInstance();   
//        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
//        String lastDay = format.format(cale.getTime());
//        System.out.println("-----2------lastDay:"+lastDay);
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		System.out.println(c.getTime());
		System.out.println(c.getTimeInMillis());
	}

	
	
}
