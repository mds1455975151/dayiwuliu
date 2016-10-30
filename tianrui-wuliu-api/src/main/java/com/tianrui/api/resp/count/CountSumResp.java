package com.tianrui.api.resp.count;

public class CountSumResp {
	private String id;

    private String type;

    private String remark;

    private Double sumdate;

    private Long showtime;

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
