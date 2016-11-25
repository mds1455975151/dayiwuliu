package com.tianrui.api.req.front.bill;

public class BillAssessReq {
    private String id;

    private String billid;

    private String timelystart;

    private String timelydelivery;

    private String timelyreturn;

    private String cardamage;

    private String transportaccident;

    private String creator;

    private Long createtime;

    private String remark;

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

    public String getTimelystart() {
        return timelystart;
    }

    public void setTimelystart(String timelystart) {
        this.timelystart = timelystart == null ? null : timelystart.trim();
    }

    public String getTimelydelivery() {
        return timelydelivery;
    }

    public void setTimelydelivery(String timelydelivery) {
        this.timelydelivery = timelydelivery == null ? null : timelydelivery.trim();
    }

    public String getTimelyreturn() {
        return timelyreturn;
    }

    public void setTimelyreturn(String timelyreturn) {
        this.timelyreturn = timelyreturn == null ? null : timelyreturn.trim();
    }

    public String getCardamage() {
        return cardamage;
    }

    public void setCardamage(String cardamage) {
        this.cardamage = cardamage == null ? null : cardamage.trim();
    }

    public String getTransportaccident() {
        return transportaccident;
    }

    public void setTransportaccident(String transportaccident) {
        this.transportaccident = transportaccident == null ? null : transportaccident.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}