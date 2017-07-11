package com.tianrui.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public static void main(String[] args) throws Exception {
		System.out.println(StringZoLong("2017-07-09 00:00:00"));
	}
}
