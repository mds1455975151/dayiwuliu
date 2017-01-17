package com.tianrui.service.bean.anlian;

import java.util.List;

/***
 * 运单
 * @author jh
 *
 */
public class AnlianShipment extends AnlianBase{

	/** 配载单号*/
	private String pzdh;
	/** 司机会员号*/
	private String sj;
	/** 车牌号*/
	private String cph;
	/** 总质量*/
	private String zzl;
	/** 总体积*/
	private String ztj;
	/** 总数量*/
	private String zsl;
	/** 元*/
	private String yf;
	/**计费方式*/
	private String jffs;
	/** 启运城市*/
	private String qycs;
	/** 目的城市*/
	private String mdcs;
	/** 里程*/
	private String lc;
	/** 要求提货日期*/
	private String yqthrq;
	/** 要求到货日期*/
	private String yqdhrq;
	
	private List<Orders> orders;
 	
	
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getCph() {
		return cph;
	}
	public void setCph(String cph) {
		this.cph = cph;
	}
	public String getZzl() {
		return zzl;
	}
	public void setZzl(String zzl) {
		this.zzl = zzl;
	}
	public String getZtj() {
		return ztj;
	}
	public void setZtj(String ztj) {
		this.ztj = ztj;
	}
	public String getZsl() {
		return zsl;
	}
	public void setZsl(String zsl) {
		this.zsl = zsl;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getJffs() {
		return jffs;
	}
	public void setJffs(String jffs) {
		this.jffs = jffs;
	}
	public String getQycs() {
		return qycs;
	}
	public void setQycs(String qycs) {
		this.qycs = qycs;
	}
	public String getMdcs() {
		return mdcs;
	}
	public void setMdcs(String mdcs) {
		this.mdcs = mdcs;
	}
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public String getYqthrq() {
		return yqthrq;
	}
	public void setYqthrq(String yqthrq) {
		this.yqthrq = yqthrq;
	}
	public String getYqdhrq() {
		return yqdhrq;
	}
	public void setYqdhrq(String yqdhrq) {
		this.yqdhrq = yqdhrq;
	}
	public String getPzdh() {
		return pzdh;
	}
	public void setPzdh(String pzdh) {
		this.pzdh = pzdh;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
}
