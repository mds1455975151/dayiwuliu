package com.tianrui.api.resp.money;

import java.util.Date;
/** 资金账户*/
public class FindCapitalAccountResp {
    private Long id;

    private String cellphone;//登录账号

    private String username;//用户姓名

    private String useryhno;//银行（NC）唯一编号，身份证号

    private Long availablemoney;//可用余额(单位：分)

    private Long lockmoney;//冻结金额

    private Long totalmoney;//账户总额=用户可用余额+用户冻结金额

    private Long pendingmoney;//未到账总金额

    private Integer totalbill;//运单总笔数

    private Integer paidbill;//已到账笔数

    private Integer pendingbill;//未到账笔数

    private Long paidmoney;//已到账总金额

    private Date lasttime;

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

    public Long getAvailablemoney() {
        return availablemoney;
    }

    public void setAvailablemoney(Long availablemoney) {
        this.availablemoney = availablemoney;
    }

    public Long getLockmoney() {
        return lockmoney;
    }

    public void setLockmoney(Long lockmoney) {
        this.lockmoney = lockmoney;
    }

    public Long getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Long totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Long getPendingmoney() {
        return pendingmoney;
    }

    public void setPendingmoney(Long pendingmoney) {
        this.pendingmoney = pendingmoney;
    }

    public Integer getTotalbill() {
        return totalbill;
    }

    public void setTotalbill(Integer totalbill) {
        this.totalbill = totalbill;
    }

    public Integer getPaidbill() {
        return paidbill;
    }

    public void setPaidbill(Integer paidbill) {
        this.paidbill = paidbill;
    }

    public Integer getPendingbill() {
        return pendingbill;
    }

    public void setPendingbill(Integer pendingbill) {
        this.pendingbill = pendingbill;
    }

    public Long getPaidmoney() {
        return paidmoney;
    }

    public void setPaidmoney(Long paidmoney) {
        this.paidmoney = paidmoney;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }
}