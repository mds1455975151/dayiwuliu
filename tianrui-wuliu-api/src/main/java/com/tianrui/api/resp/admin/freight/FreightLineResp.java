package com.tianrui.api.resp.admin.freight;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FreightLineResp {

	private String id;

    private String freightid;

    private Double price;

    private Double tallage;

    private String updater;

    private Long updatetime;
    
    private String updatetimeStr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFreightid() {
		return freightid;
	}

	public void setFreightid(String freightid) {
		this.freightid = freightid;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTallage() {
		return tallage;
	}

	public void setTallage(Double tallage) {
		this.tallage = tallage;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdatetimeStr() {
		if(updatetime != null){//yyyy-MM-dd HH:mm:ss
			updatetimeStr =new SimpleDateFormat("MM-dd").format(new Date(updatetime));
		}
		return updatetimeStr;
	}

	public void setUpdatetimeStr(String updatetimeStr) {
		this.updatetimeStr = updatetimeStr;
	}
    
    
}