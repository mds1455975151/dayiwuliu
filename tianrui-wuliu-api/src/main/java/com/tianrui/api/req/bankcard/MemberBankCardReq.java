package com.tianrui.api.req.bankcard;

public class MemberBankCardReq {
    
	private String id;
    /** 0-非默认；1-默认*/
    private String bankstatus;
    /** 1-认证成功；2-认证中；3-认证失败*/
    private String bankautid;

    private String bankcard;

    private String bankname;

    private String bankcode;

    private String bankimg;

    private String idcard;

    private String idname;

    private String idcardimg;

    private String telphone;

    private String creater;

    private Long createtime;

    private String auditor;

    private Long auditortime;

    private String desc1;
    
    private Integer pageNo;
    
    private Integer pageSize;
    //银行支行id
    private String bankSubbranchId;
    //银行支行名称
    private String bankSubbranchName;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBankstatus() {
        return bankstatus;
    }

    public void setBankstatus(String bankstatus) {
        this.bankstatus = bankstatus == null ? null : bankstatus.trim();
    }

    public String getBankautid() {
        return bankautid;
    }

    public void setBankautid(String bankautid) {
        this.bankautid = bankautid == null ? null : bankautid.trim();
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard == null ? null : bankcard.trim();
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

    public String getBankimg() {
        return bankimg;
    }

    public void setBankimg(String bankimg) {
        this.bankimg = bankimg == null ? null : bankimg.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname == null ? null : idname.trim();
    }

    public String getIdcardimg() {
        return idcardimg;
    }

    public void setIdcardimg(String idcardimg) {
        this.idcardimg = idcardimg == null ? null : idcardimg.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public Long getAuditortime() {
        return auditortime;
    }

    public void setAuditortime(Long auditortime) {
        this.auditortime = auditortime;
    }

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getBankSubbranchId() {
		return bankSubbranchId;
	}

	public void setBankSubbranchId(String bankSubbranchId) {
		this.bankSubbranchId = bankSubbranchId;
	}

	public String getBankSubbranchName() {
		return bankSubbranchName;
	}

	public void setBankSubbranchName(String bankSubbranchName) {
		this.bankSubbranchName = bankSubbranchName;
	}

}