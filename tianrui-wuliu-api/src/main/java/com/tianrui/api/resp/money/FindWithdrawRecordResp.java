package com.tianrui.api.resp.money;

import java.util.Date;
/***
 * 提现管理
 * @author jh
 *
 */
public class FindWithdrawRecordResp {
    private Long id;

    private String cellphone;//登录账号

    private String username;//用户姓名

    private String useryhno;//银行（NC）唯一编号，身份证号

    private Long money;//提现金额

    private String expectpaycompany;//支付公司

    private String bankname;//银行名称

    private String bankcode;//银行编码

    private String bankcodeno;//银行卡号

    private Short transactionstate;//交易状态 1-处理中 2-成功 3-失败 4-未知

    private Long availablemoney;//用户可用余额

    private Long begintime;//提现开始时间

    private Long endtime;//提现完成时间

    private String errorcode;//错误码

    private String errormessage;//错误码描述

    private Long actualamount;//实际到账金额

    private String remark;//备注

    private Date lasttime;

    private String capitalno;//交易流水号

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

    public String getExpectpaycompany() {
        return expectpaycompany;
    }

    public void setExpectpaycompany(String expectpaycompany) {
        this.expectpaycompany = expectpaycompany == null ? null : expectpaycompany.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode == null ? null : bankcode.trim();
    }

    public String getBankcodeno() {
        return bankcodeno;
    }

    public void setBankcodeno(String bankcodeno) {
        this.bankcodeno = bankcodeno == null ? null : bankcodeno.trim();
    }

    public Short getTransactionstate() {
        return transactionstate;
    }

    public void setTransactionstate(Short transactionstate) {
        this.transactionstate = transactionstate;
    }

    public Long getAvailablemoney() {
        return availablemoney;
    }

    public void setAvailablemoney(Long availablemoney) {
        this.availablemoney = availablemoney;
    }

    public Long getBegintime() {
        return begintime;
    }

    public void setBegintime(Long begintime) {
        this.begintime = begintime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode == null ? null : errorcode.trim();
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage == null ? null : errormessage.trim();
    }

    public Long getActualamount() {
        return actualamount;
    }

    public void setActualamount(Long actualamount) {
        this.actualamount = actualamount;
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
}