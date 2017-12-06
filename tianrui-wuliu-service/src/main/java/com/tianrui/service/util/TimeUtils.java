package com.tianrui.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/***
 * 
 * @类描述：时间格式转换
 * @创建人：lsj
 * @创建时间：2016年9月26日下午2:25:41
 *
 * @修改人：lsj
 * @修改时间：2016年9月26日下午2:25:41
 * @修改备注：
 *
 */
public class TimeUtils {

	/** Long时间转String*/
	public static String LongZoString(Long time){
		String signtime = null;
		if(time != null){
			signtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
		}
		return signtime;
	}
	/** Long时间转String
	 * @throws ParseException */
	public static Long StringZoLong(String dateStr) throws ParseException{
		Long time = null;
		if(dateStr != null){
			time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr).getTime();
		}
		return time;
	}
	/** Long转Data
	 * @throws ParseException */
	public static Date LongZoDate(Long time) throws ParseException{
		Date date = null;
		if(time != null){	
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = sdf.format(new Date(time));
			date = sdf.parse(dateStr);
		}
		return date;
	}
	/**
	 * 今天
	 *
	 * @return
	 */
	public static String getTime()
	{
		SimpleDateFormat	dft		= new SimpleDateFormat( "yyyy-MM-dd" );
		Date			beginDate	= new Date();
		Calendar		date		= Calendar.getInstance();
		date.setTime( beginDate );
		date.set( Calendar.DATE, date.get( Calendar.DATE ) );
		String etime = "";
		try {
			Date			endDate = dft.parse( dft.format( date.getTime() ) );
			SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy-MM-dd" );
			etime = sdf.format( endDate );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}

		return(etime);
	}


	/**
	 * 今天 带时分秒
	 *
	 * @return
	 */
	public static String getTimeHMS()
	{
		SimpleDateFormat	dft		= new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date			beginDate	= new Date();
		Calendar		date		= Calendar.getInstance();
		date.setTime( beginDate );
		date.set( Calendar.DATE, date.get( Calendar.DATE ) );
		String etime = "";
		try {
			Date			endDate = dft.parse( dft.format( date.getTime() ) );
			SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			etime = sdf.format( endDate );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}

		return(etime);
	}
	/**
	 * 今天 long
	 *
	 * @return
	 */
	public static long getTadoyTime()
	{
		SimpleDateFormat	dft		= new SimpleDateFormat( "yyyy-MM-dd" );
		Date			beginDate	= new Date();
		Calendar		date		= Calendar.getInstance();
		date.setTime( beginDate );
		date.set( Calendar.DATE, date.get( Calendar.DATE ) );
		try {
			return(dft.parse(dft.format(date.getTime())).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 前一天
	 *
	 * @return
	 */
	public static long getYesterdayTime(){
		SimpleDateFormat	dft		= new SimpleDateFormat( "yyyy-MM-dd" );
		Date			beginDate	= new Date();
		Calendar		date		= Calendar.getInstance();
		date.setTime( beginDate );
		date.set( Calendar.DATE, date.get( Calendar.DATE ) - 1 );
		try {
			return(dft.parse(dft.format(date.getTime())).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 上个月
	 *
	 * @return
	 */
	public static long getLastMonthTime(){
		SimpleDateFormat	dft		= new SimpleDateFormat( "yyyy-MM-dd" );
		Date			beginDate	= new Date();
		Calendar		date		= Calendar.getInstance();
		date.setTime( beginDate );
		date.set( Calendar.MONTH, date.get( Calendar.MONTH ) - 1 );
		try {
			return(dft.parse(dft.format(date.getTime())).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 前一天
	 *
	 * @return
	 */
	public static String getTimes()
	{
		SimpleDateFormat	dft		= new SimpleDateFormat( "yyyy-MM-dd" );
		Date			beginDate	= new Date();
		Calendar		date		= Calendar.getInstance();
		date.setTime( beginDate );
		date.set( Calendar.DATE, date.get( Calendar.DATE ) - 1 );
		String etime = "";
		try {
			Date			endDate = dft.parse( dft.format( date.getTime() ) );
			SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy-MM-dd" );
			etime = sdf.format( endDate );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}

		return(etime);
	}


	/**
	 * 获取所传时间前一天
	 * @param time
	 * @return
	 */
	public static String getSpecifiedDayBefore( String time )
	{
		Calendar	c	= Calendar.getInstance();
		Date		date	= null;
		try {
			date = new SimpleDateFormat( "yy-MM-dd" ).parse( time );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		c.setTime( date );
		int day = c.get( Calendar.DATE );
		c.set( Calendar.DATE, day - 1 );
		String dayBefore = new SimpleDateFormat( "yyyy-MM-dd" ).format( c.getTime() );
		return(dayBefore);
	}

	public static void main(String[] args) throws Exception {
		
		System.out.println(getTadoyTime());
		System.out.println(getYesterdayTime());
	}
}
