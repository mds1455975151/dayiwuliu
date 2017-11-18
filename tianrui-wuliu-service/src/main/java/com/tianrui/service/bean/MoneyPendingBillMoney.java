package com.tianrui.service.bean;

import java.util.Date;

public class MoneyPendingBillMoney {
    private Long id;

    private String cellphone;//登录账号

    private String username;//用户姓名

    private String useryhno;//银行（NC）唯一编号，身份证号

    private String waybillno;//运单编号

    private Long pendingmoney;//预计收入金额

    private Long createtime;//创建时间（卸货完成时间）

    private Short ifpaid;//是否到账(0-未到账，1-已到账)

    private Long paidmoney;//实际收入金额

    private Long deductionmoney;//其他抵扣金额合计（油卡等）

    private Long paidtime;//到账时间

    private Date lasttime;

    private String capitalno;//交易流水号

    private String desc1;

    private String desc2;

    private String desc3;

    private String desc4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUseryhno() {
        return useryhno;
    }

    public void setUseryhno(String useryhno) {
        this.useryhno = useryhno == null ? null : useryhno.trim();
    }

    public String getWaybillno() {
        return waybillno;
    }

    public void setWaybillno(String waybillno) {
        this.waybillno = waybillno == null ? null : waybillno.trim();
    }

    public Long getPendingmoney() {
        return pendingmoney;
    }

    public void setPendingmoney(Long pendingmoney) {
        this.pendingmoney = pendingmoney;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Short getIfpaid() {
        return ifpaid;
    }

    public void setIfpaid(Short ifpaid) {
        this.ifpaid = ifpaid;
    }

    public Long getPaidmoney() {
        return paidmoney;
    }

    public void setPaidmoney(Long paidmoney) {
        this.paidmoney = paidmoney;
    }

    public Long getDeductionmoney() {
        return deductionmoney;
    }

    public void setDeductionmoney(Long deductionmoney) {
        this.deductionmoney = deductionmoney;
    }

    public Long getPaidtime() {
        return paidtime;
    }

    public void setPaidtime(Long paidtime) {
        this.paidtime = paidtime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public String getCapitalno() {
        return capitalno;
    }

    public void setCapitalno(String capitalno) {
        this.capitalno = capitalno == null ? null : capitalno.trim();
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    public String getDesc4() {
        return desc4;
    }

    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
    }
}