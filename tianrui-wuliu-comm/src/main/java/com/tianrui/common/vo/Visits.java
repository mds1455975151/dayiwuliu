package com.tianrui.common.vo;

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
}
