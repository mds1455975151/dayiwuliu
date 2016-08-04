package com.tianrui.service.bean;

public class Transfer {
    private String id;

    private String billid;

    private String vehicleid;

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
        this.id = id == null ? null : id.trim();
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid == null ? null : billid.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getStartid() {
        return startid;
    }

    public void setStartid(String startid) {
        this.startid = startid == null ? null : startid.trim();
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter == null ? null : starter.trim();
    }

    public String getStarttele() {
        return starttele;
    }

    public void setStarttele(String starttele) {
        this.starttele = starttele == null ? null : starttele.trim();
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
        this.sendid = sendid == null ? null : sendid.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getSendtele() {
        return sendtele;
    }

    public void setSendtele(String sendtele) {
        this.sendtele = sendtele == null ? null : sendtele.trim();
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
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }
}