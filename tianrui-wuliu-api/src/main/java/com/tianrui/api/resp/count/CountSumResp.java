package com.tianrui.api.resp.count;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CountSumResp {
	private String id;

    private String type;

    private String remark;

    private Double sumdate;

    private Long showtime;
    
    private String showtimeStr;
    
    private String desc1;

    
	public String getShowtimeStr() {
		if(showtime != null){
			showtimeStr = new SimpleDateFormat("MM月dd日").format(new Date(showtime));
		}
		return showtimeStr;
	}
	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public void setShowtimeStr(String showtimeStr) {
		this.showtimeStr = showtimeStr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getSumdate() {
		return sumdate;
	}

	public void setSumdate(Double sumdate) {
		this.sumdate = sumdate;
	}

	public Long getShowtime() {
		return showtime;
	}

	public void setShowtime(Long showtime) {
		this.showtime = showtime;
	}

    
}
