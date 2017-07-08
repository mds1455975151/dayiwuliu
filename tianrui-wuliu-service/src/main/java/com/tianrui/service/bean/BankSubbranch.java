package com.tianrui.service.bean;

public class BankSubbranch {
	
    private String id;
    //联行号
    private String bankLineNumber;
    //银行编码
    private String code;
    //内部码
    private String innercode;
    //支行名称
    private String name;
    //之行名称模糊查询
    private String likeName;
    //银行类别表ID
    private String bankTypeId;

    public String getId() {
        return id;
    }

	public String getLikeName() {
		return likeName;
	}

	public void setLikeName(String likeName) {
		this.likeName = likeName;
	}


	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBankLineNumber() {
        return bankLineNumber;
    }

    public void setBankLineNumber(String bankLineNumber) {
        this.bankLineNumber = bankLineNumber == null ? null : bankLineNumber.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getInnercode() {
        return innercode;
    }

    public void setInnercode(String innercode) {
        this.innercode = innercode == null ? null : innercode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBankTypeId() {
        return bankTypeId;
    }

    public void setBankTypeId(String bankTypeId) {
        this.bankTypeId = bankTypeId == null ? null : bankTypeId.trim();
    }
}