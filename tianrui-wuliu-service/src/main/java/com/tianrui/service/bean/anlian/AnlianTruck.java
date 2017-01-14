package com.tianrui.service.bean.anlian;

import org.apache.commons.lang.StringUtils;

public class AnlianTruck extends AnlianBase{

	/** 车牌号码*/
	private String cphm;
	/** 检验有效日期*/
	private String jyyxqz;
	/**核定在质量 */
	private String hdzzl;
	/** 总质量*/
	private String zzl;
	/** 登记证书编号*/
	private String djzsbh;
	/** 所有人*/
	private String syr;
	/** 身份证明*/
	private String sfzm;
	/** 标准车辆类型*/
	private String bzcllx;
	/** 使用性质*/
	private String syxz;
	/** 车辆识别代码*/
	private String clsbdm;
	/** 发动机型号*/
	private String fdjxh;
	/** 发动机号*/
	private String fdjh;
	
	private String dlysjyxkzbh;
	
	private String dlysjyxkzyxqz;
	
	private String sf;
	
	private String fzjg;
	
	private String clccrq;
	
	private String ccdjrq;
	
	private String cwkc;
	
	private String cwkk;
	
	private String cwkg;
	
	private String csys;
	
	private String clpp;
	
	private String zz;
	
	private String bxzzrq;
	
	public String getCphm() {
		return cphm;
	}
	public void setCphm(String cphm) {
		this.cphm = cphm;
	}
	public String getJyyxqz() {
		return jyyxqz;
	}
	public void setJyyxqz(String jyyxqz) {
		this.jyyxqz = jyyxqz;
	}
	public String getHdzzl() {
		return hdzzl;
	}
	public void setHdzzl(String hdzzl) {
		this.hdzzl = hdzzl;
	}
	public String getZzl() {
		return zzl;
	}
	public void setZzl(String zzl) {
		this.zzl = zzl;
	}
	public String getDjzsbh() {
		return djzsbh;
	}
	public void setDjzsbh(String djzsbh) {
		this.djzsbh = djzsbh;
	}
	public String getSyr() {
		return syr;
	}
	public void setSyr(String syr) {
		this.syr = syr;
	}
	public String getSfzm() {
		return sfzm;
	}
	public void setSfzm(String sfzm) {
		this.sfzm = sfzm;
	}
	public String getBzcllx() {
		return bzcllx;
	}
	public void setBzcllx(String bzcllx) {
		this.bzcllx = bzcllx;
	}
	public String getSyxz() {
		return syxz;
	}
	public void setSyxz(String syxz) {
		this.syxz = syxz;
	}
	public String getClsbdm() {
		return clsbdm;
	}
	public void setClsbdm(String clsbdm) {
		this.clsbdm = clsbdm;
	}
	public String getFdjxh() {
		return fdjxh;
	}
	public void setFdjxh(String fdjxh) {
		this.fdjxh = fdjxh;
	}
	public String getFdjh() {
		return fdjh;
	}
	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}
	public String getDlysjyxkzbh() {
		return dlysjyxkzbh;
	}
	public void setDlysjyxkzbh(String dlysjyxkzbh) {
		this.dlysjyxkzbh = StringUtils.isBlank(dlysjyxkzbh)?"":dlysjyxkzbh;
	}
	public String getDlysjyxkzyxqz() {
		return dlysjyxkzyxqz;
	}
	public void setDlysjyxkzyxqz(String dlysjyxkzyxqz) {
		this.dlysjyxkzyxqz = StringUtils.isBlank(dlysjyxkzyxqz)?"":dlysjyxkzyxqz;
	}
	public String getSf() {
		return sf;
	}
	public void setSf(String sf) {
		this.sf = StringUtils.isBlank(sf)?"":sf;
	}
	public String getFzjg() {
		return fzjg;
	}
	public void setFzjg(String fzjg) {
		this.fzjg = StringUtils.isBlank(fzjg)?"":fzjg;
	}
	public String getClccrq() {
		return clccrq;
	}
	public void setClccrq(String clccrq) {
		this.clccrq = StringUtils.isBlank(clccrq)?"":clccrq;
	}
	public String getCcdjrq() {
		return ccdjrq;
	}
	public void setCcdjrq(String ccdjrq) {
		this.ccdjrq = StringUtils.isBlank(ccdjrq)?"":ccdjrq;
	}
	public String getCwkc() {
		return cwkc;
	}
	public void setCwkc(String cwkc) {
		this.cwkc = StringUtils.isBlank(cwkc)?"":cwkc;
	}
	public String getCwkk() {
		return cwkk;
	}
	public void setCwkk(String cwkk) {
		this.cwkk = StringUtils.isBlank(cwkk)?"":cwkk;
	}
	public String getCwkg() {
		return cwkg;
	}
	public void setCwkg(String cwkg) {
		this.cwkg = StringUtils.isBlank(cwkg)?"":cwkg;
	}
	public String getCsys() {
		return csys;
	}
	public void setCsys(String csys) {
		this.csys = StringUtils.isBlank(csys)?"":csys;
	}
	public String getClpp() {
		return clpp;
	}
	public void setClpp(String clpp) {
		this.clpp = StringUtils.isBlank(clpp)?"":clpp;
	}
	public String getZz() {
		return zz;
	}
	public void setZz(String zz) {
		this.zz = zz;
	}
	public String getBxzzrq() {
		return bxzzrq;
	}
	public void setBxzzrq(String bxzzrq) {
		this.bxzzrq = bxzzrq;
	}
}
