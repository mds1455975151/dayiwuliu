package com.tianrui.service.bean;

public class Transfer {
    private String id;

    private String billid;

    private String vehicleno;

    private String startid;

    private String starter;

    private String starttele;

    private String sendid;

    private String sender;

    private String sendtele;

    private Long sendtime;

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

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
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
}