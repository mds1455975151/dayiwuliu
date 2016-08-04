package com.tianrui.api.req.front.transfer;

import com.tianrui.api.req.BaseReq;

public class TransferReq extends BaseReq{
	private String id;

    private String billid;

    private String vehicleno;

    private String status;

    private String reason;

    private String startid;

    private String starter;

    private String starttele;

    private Long starttime;

    private String sendid;

    private String sender;

    private String sendtele;

    private Long sendtime;

    private String isvalid;
    
    private String memberid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStartid() {
		return startid;
	}

	public void setStartid(String startid) {
		this.startid = startid;
	}

	public String getStarter() {
		return starter;
	}

	public void setStarter(String starter) {
		this.starter = starter;
	}

	public String getStarttele() {
		return starttele;
	}

	public void setStarttele(String starttele) {
		this.starttele = starttele;
	}

	public Long getStarttime() {
		return starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public String getSendid() {
		return sendid;
	}

	public void setSendid(String sendid) {
		this.sendid = sendid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendtele() {
		return sendtele;
	}

	public void setSendtele(String sendtele) {
		this.sendtele = sendtele;
	}

	public Long getSendtime() {
		return sendtime;
	}

	public void setSendtime(Long sendtime) {
		this.sendtime = sendtime;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

}
