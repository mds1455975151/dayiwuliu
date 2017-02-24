package com.tianrui.common.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 接口访问次数
 * @author jh
 *
 */
public class Visits {

	private String ip;
	//访问次数 或限制时间-分钟
	private Integer number;
	
	private Long time;
	
	private String timeStr;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getTimeStr() {
		if(time != null){
			timeStr = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss").format(new Date(time));
		}
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
}
