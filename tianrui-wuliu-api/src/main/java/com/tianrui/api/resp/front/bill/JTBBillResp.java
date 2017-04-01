package com.tianrui.api.resp.front.bill;

import java.text.SimpleDateFormat;

public class JTBBillResp {

	private String id;
	private String waybillno;
	private String vehicleno;
	private Long createtime;
	private String creatimeStr;
	private String jtb;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWaybillno() {
		return waybillno;
	}
	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getJtb() {
		return jtb;
	}
	public void setJtb(String jtb) {
		this.jtb = jtb;
	}
	public String getCreatimeStr() {
		if(createtime != null){
			creatimeStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(createtime);
		}
		return creatimeStr;
	}
	public void setCreatimeStr(String creatimeStr) {
		this.creatimeStr = creatimeStr;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	
	
}
