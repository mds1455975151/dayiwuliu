package com.tianrui.api.resp.front.transfer;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.resp.BaseResp;

public class TransferResp extends BaseResp{
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

	public String getStarttime() {
		return format(starttime);
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

	public String getSendtime() {
		return format(sendtime);
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

	public String format(Long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		if(time != null){
			date.setTime(time);
		}
		return sdf.format(date);
	}
}
