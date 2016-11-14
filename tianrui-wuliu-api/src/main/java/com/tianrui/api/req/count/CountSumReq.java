package com.tianrui.api.req.count;

public class CountSumReq {
	private String id;

    private String type;

    private String remark;

    private Double sumdate;

    private Long showtime;
    
    private String stype;
    
    private String desc1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
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

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
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
