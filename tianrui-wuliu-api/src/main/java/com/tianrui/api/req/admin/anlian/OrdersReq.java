package com.tianrui.api.req.admin.anlian;

import java.util.List;

public class OrdersReq {

	/** 订单号*/
	private String ddh;
	/** 客户代码*/
	private String khdm;
	/** 提货地址*/
	private String thdz;
	/** 送货地址*/
	private String shdz;
	/** 收货人*/
	private String shr;
	/** 联系手机*/
	private String lxsj;
	/** 加急*/
	private String jj;
	
	private List<LinesReq> lines;
	public String getDdh() {
		return ddh;
	}
	public void setDdh(String ddh) {
		this.ddh = ddh;
	}
	public String getKhdm() {
		return khdm;
	}
	public void setKhdm(String khdm) {
		this.khdm = khdm;
	}
	public String getThdz() {
		return thdz;
	}
	public void setThdz(String thdz) {
		this.thdz = thdz;
	}
	public String getShdz() {
		return shdz;
	}
	public void setShdz(String shdz) {
		this.shdz = shdz;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getLxsj() {
		return lxsj;
	}
	public void setLxsj(String lxsj) {
		this.lxsj = lxsj;
	}
	public String getJj() {
		return jj;
	}
	public void setJj(String jj) {
		this.jj = jj;
	}
	public List<LinesReq> getLines() {
		return lines;
	}
	public void setLines(List<LinesReq> lines) {
		this.lines = lines;
	}
	
}
