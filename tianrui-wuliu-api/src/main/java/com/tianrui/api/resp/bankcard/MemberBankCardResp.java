package com.tianrui.api.resp.bankcard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberBankCardResp {
    private String id;
    
    //联行号
    private String bankLineNumber;
    
    private String bankLineCode;

    private String bankstatus;

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
    
    private String createtimeStr;

    private String auditor;

    private Long auditortime;

    private String desc1;

    private String desc2;

    private String desc3;

    private String desc4;
    //推送状态（0：未推送，1：推送中，2：已推送）
    private Integer pushStatus;
    //推送时间
    private Long pushTime;
    //推送nc错误返回信息
    private String errorMassage;
    //审核不通过返回信息
    private String auditMassage;

    public String getAuditMassage() {
		return auditMassage;
	}

	public void setAuditMassage(String auditMassage) {
		this.auditMassage = auditMassage;
	}

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

    public String getCreatetimeStr() {
		if(createtime != null){
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			createtimeStr = fmt.format(new Date(createtime));
		}
    	return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
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

	public Integer getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}

	public Long getPushTime() {
		return pushTime;
	}

	public void setPushTime(Long pushTime) {
		this.pushTime = pushTime;
	}

	public String getErrorMassage() {
		return errorMassage;
	}

	public void setErrorMassage(String errorMassage) {
		this.errorMassage = errorMassage;
	}

	public String getBankLineNumber() {
		return bankLineNumber;
	}

	public void setBankLineNumber(String bankLineNumber) {
		this.bankLineNumber = bankLineNumber;
	}

	public String getBankLineCode() {
		return bankLineCode;
	}

	public void setBankLineCode(String bankLineCode) {
		this.bankLineCode = bankLineCode;
	}
}