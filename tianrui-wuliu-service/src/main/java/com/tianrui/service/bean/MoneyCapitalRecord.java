package com.tianrui.service.bean;

import java.util.Date;

public class MoneyCapitalRecord {
    private Long id;

    private String cellphone;//登录账号

    private String username;//用户姓名

    private String useryhno;//银行（NC）唯一编号，身份证号

    private Long money;//交易金额

    private Long transactiontype;//交易类型11-收入运费，19-资金回流；  21-提现申请，22-提现成功

    private Long income;//收入金额

    private Long expenditure;//支出金额

    private Long totalmoney;//用户账户总额

    private Long availablemoney;//用户可用余额

    private Long withdrawalslockmoney;//提现中冻结累计金额

    private Long otherlockmoney;//其他冻结累计金额

    private Long createtime;//记录创建时间

    private String remark;//备注信息

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

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(Long transactiontype) {
        this.transactiontype = transactiontype;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Long getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Long expenditure) {
        this.expenditure = expenditure;
    }

    public Long getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Long totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Long getAvailablemoney() {
        return availablemoney;
    }

    public void setAvailablemoney(Long availablemoney) {
        this.availablemoney = availablemoney;
    }

    public Long getWithdrawalslockmoney() {
        return withdrawalslockmoney;
    }

    public void setWithdrawalslockmoney(Long withdrawalslockmoney) {
        this.withdrawalslockmoney = withdrawalslockmoney;
    }

    public Long getOtherlockmoney() {
        return otherlockmoney;
    }

    public void setOtherlockmoney(Long otherlockmoney) {
        this.otherlockmoney = otherlockmoney;
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