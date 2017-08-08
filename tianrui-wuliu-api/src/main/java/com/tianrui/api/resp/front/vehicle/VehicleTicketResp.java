package com.tianrui.api.resp.front.vehicle;

import org.apache.commons.lang.StringUtils;

public class VehicleTicketResp {

	 private String id;

	    private String vehicleid;

	    private String status;

	    private String anlian;

	    private String nature;

	    private String quality;

	    private String owner;

	    private String idcard;

	    private String certificateno;

	    private String expirydata;

	    private String identification;

	    private String motor;

	    private String motorno;

	    private String desc1;
	    
	    private String desc4;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getVehicleid() {
			return vehicleid;
		}

		public void setVehicleid(String vehicleid) {
			this.vehicleid = vehicleid;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getAnlian() {
			return anlian;
		}

		public void setAnlian(String anlian) {
			this.anlian = anlian;
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

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		public String getIdcard() {
			idcard = StringUtils.isBlank(idcard)?"":idcard.toUpperCase();
			return idcard;
		}

		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}

		public String getCertificateno() {
			certificateno = StringUtils.isBlank(certificateno)?"":certificateno.toUpperCase();
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
			identification = StringUtils.isBlank(identification)?"":identification.toUpperCase();
			return identification;
		}

		public void setIdentification(String identification) {
			this.identification = identification;
		}

		public String getMotor() {
			motor = StringUtils.isBlank(motor)?"":motor.toUpperCase();
			return motor;
		}

		public void setMotor(String motor) {
			this.motor = motor;
		}

		public String getMotorno() {
			motorno = StringUtils.isBlank(motorno)?"":motorno.toUpperCase();
			return motorno;
		}

		public void setMotorno(String motorno) {
			this.motorno = motorno;
		}

		public String getDesc1() {
			return desc1;
		}

		public void setDesc1(String desc1) {
			this.desc1 = desc1;
		}

		public String getDesc4() {
			return desc4;
		}

		public void setDesc4(String desc4) {
			this.desc4 = desc4;
		}
	    
}
