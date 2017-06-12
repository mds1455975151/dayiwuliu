package com.tianrui.service.bean;

public class NCbankType {
    private String id;

    private String banktype;

    private String bankname;

    private String bankkey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype == null ? null : banktype.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankkey() {
        return bankkey;
    }

    public void setBankkey(String bankkey) {
        this.bankkey = bankkey == null ? null : bankkey.trim();
    }
}