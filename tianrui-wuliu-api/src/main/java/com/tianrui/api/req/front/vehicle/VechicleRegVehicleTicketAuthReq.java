package com.tianrui.api.req.front.vehicle;

import com.tianrui.api.req.BaseReq;

public class VechicleRegVehicleTicketAuthReq extends BaseReq {
	
	private static final long serialVersionUID = -7190183760043617974L;
	private String id ;
	//认证类型   0:默认  1:完全  2:临时  3:开票
	private short authType;
	//使用性质 1-营运 2-非营运
	private String nature;
	//总质量
	private String quality;
	//证件号码
	private String idcardno;
	//登记证书编号
	private String certificateno;
	//检验有效期止
	private String expirydata;
	//车辆识别码
	private String identification;
	//发动机号
	private String motor;
	//发动机型号
	private String motorno;
	
	private String currVId;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public short getAuthType() {
		return authType;
	}
	public void setAuthType(short authType) {
		this.authType = authType;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getIdcardno() {
		return idcardno;
	}
	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}
	public String getCertificateno() {
		return certificateno;
	}
	public void setCertificateno(String certificateno) {
		this.certificateno = certificateno;
	}
	public String getExpirydata() {
		return expirydata;
	}
	public void setExpirydata(String expirydata) {
		this.expirydata = expirydata;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public String getMotorno() {
		return motorno;
	}
	public void setMotorno(String motorno) {
		this.motorno = motorno;
	}
	public String getCurrVId() {
		return currVId;
	}
	public void setCurrVId(String currVId) {
		this.currVId = currVId;
	}
	
	
	    
}
