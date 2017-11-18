package com.tianrui.service.bean;

import java.util.Date;

public class MoneyCapitalAccount {
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