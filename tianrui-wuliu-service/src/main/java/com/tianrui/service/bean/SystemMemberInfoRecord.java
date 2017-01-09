package com.tianrui.service.bean;

public class SystemMemberInfoRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.id
     *
     * @mbggenerated
     */
    private String id;
    
    private String cellphone;
    
    private Long registtime;
    
    private String sex;
    private String birthday;
    private String firstlicens;
    private String licenceorg;
    private String starttime;
    private String usefullife;
    private String idcardaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.memberid
     *
     * @mbggenerated
     */
    private String memberid;
    /**
     * 营业执照路径
     */
    private String licenseImagePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.userpercheck
     *
     * @mbggenerated
     */
    private String userpercheck;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.driverpercheck
     *
     * @mbggenerated
     */
    private String driverpercheck;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.companypercheck
     *
     * @mbggenerated
     */
    private String companypercheck;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.submittime
     *
     * @mbggenerated
     */
    private Long submittime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.username
     *
     * @mbggenerated
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.telphone
     *
     * @mbggenerated
     */
    private String telphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.idcard
     *
     * @mbggenerated
     */
    private String idcard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.idcardimage
     *
     * @mbggenerated
     */
    private String idcardimage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.driverimage
     *
     * @mbggenerated
     */
    private String driverimage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.companyname
     *
     * @mbggenerated
     */
    private String companyname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.companycontact
     *
     * @mbggenerated
     */
    private String companycontact;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.companycode
     *
     * @mbggenerated
     */
    private String companycode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.companytel
     *
     * @mbggenerated
     */
    private String companytel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.companyAddress
     *
     * @mbggenerated
     */
    private String companyAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.auditid
     *
     * @mbggenerated
     */
    private String auditid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.auditname
     *
     * @mbggenerated
     */
    private String auditname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.audittime
     *
     * @mbggenerated
     */
    private Long audittime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_info_record.auditresson
     *
     * @mbggenerated
     */
    private String auditresson;

    /**
     * 
		道路运输经营许可证号
		Road transport business license No.
     */
    private String rtblno;
    /**
     * 
		道路运输经营许可证图片
		Road transport business license No.
     */
    private String rtblimgurl;
    /**
     * 准驾车型
     */
    private String licenseType;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.id
     *
     * @return the value of member_info_record.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.id
     *
     * @param id the value for member_info_record.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.memberid
     *
     * @return the value of member_info_record.memberid
     *
     * @mbggenerated
     */
    public String getMemberid() {
        return memberid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.memberid
     *
     * @param memberid the value for member_info_record.memberid
     *
     * @mbggenerated
     */
    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.userpercheck
     *
     * @return the value of member_info_record.userpercheck
     *
     * @mbggenerated
     */
    public String getUserpercheck() {
        return userpercheck;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.userpercheck
     *
     * @param userpercheck the value for member_info_record.userpercheck
     *
     * @mbggenerated
     */
    public void setUserpercheck(String userpercheck) {
        this.userpercheck = userpercheck == null ? null : userpercheck.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.driverpercheck
     *
     * @return the value of member_info_record.driverpercheck
     *
     * @mbggenerated
     */
    public String getDriverpercheck() {
        return driverpercheck;
    }

    public String getCellphone() {
		return cellphone;
	}

	public Long getRegisttime() {
		return registtime;
	}

	public void setRegisttime(Long registtime) {
		this.registtime = registtime;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.driverpercheck
     *
     * @param driverpercheck the value for member_info_record.driverpercheck
     *
     * @mbggenerated
     */
    public void setDriverpercheck(String driverpercheck) {
        this.driverpercheck = driverpercheck == null ? null : driverpercheck.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.companypercheck
     *
     * @return the value of member_info_record.companypercheck
     *
     * @mbggenerated
     */
    public String getCompanypercheck() {
        return companypercheck;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.companypercheck
     *
     * @param companypercheck the value for member_info_record.companypercheck
     *
     * @mbggenerated
     */
    public void setCompanypercheck(String companypercheck) {
        this.companypercheck = companypercheck == null ? null : companypercheck.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.submittime
     *
     * @return the value of member_info_record.submittime
     *
     * @mbggenerated
     */
    public Long getSubmittime() {
        return submittime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.submittime
     *
     * @param submittime the value for member_info_record.submittime
     *
     * @mbggenerated
     */
    public void setSubmittime(Long submittime) {
        this.submittime = submittime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.status
     *
     * @return the value of member_info_record.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.status
     *
     * @param status the value for member_info_record.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.username
     *
     * @return the value of member_info_record.username
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.username
     *
     * @param username the value for member_info_record.username
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.telphone
     *
     * @return the value of member_info_record.telphone
     *
     * @mbggenerated
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.telphone
     *
     * @param telphone the value for member_info_record.telphone
     *
     * @mbggenerated
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.idcard
     *
     * @return the value of member_info_record.idcard
     *
     * @mbggenerated
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.idcard
     *
     * @param idcard the value for member_info_record.idcard
     *
     * @mbggenerated
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getFirstlicens() {
		return firstlicens;
	}

	public void setFirstlicens(String firstlicens) {
		this.firstlicens = firstlicens;
	}

	public String getLicenceorg() {
		return licenceorg;
	}

	public void setLicenceorg(String licenceorg) {
		this.licenceorg = licenceorg;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getUsefullife() {
		return usefullife;
	}

	public void setUsefullife(String usefullife) {
		this.usefullife = usefullife;
	}

	public String getIdcardaddress() {
		return idcardaddress;
	}

	public void setIdcardaddress(String idcardaddress) {
		this.idcardaddress = idcardaddress;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.idcardimage
     *
     * @return the value of member_info_record.idcardimage
     *
     * @mbggenerated
     */
    public String getIdcardimage() {
        return idcardimage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.idcardimage
     *
     * @param idcardimage the value for member_info_record.idcardimage
     *
     * @mbggenerated
     */
    public void setIdcardimage(String idcardimage) {
        this.idcardimage = idcardimage == null ? null : idcardimage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.driverimage
     *
     * @return the value of member_info_record.driverimage
     *
     * @mbggenerated
     */
    public String getDriverimage() {
        return driverimage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.driverimage
     *
     * @param driverimage the value for member_info_record.driverimage
     *
     * @mbggenerated
     */
    public void setDriverimage(String driverimage) {
        this.driverimage = driverimage == null ? null : driverimage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.companyname
     *
     * @return the value of member_info_record.companyname
     *
     * @mbggenerated
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.companyname
     *
     * @param companyname the value for member_info_record.companyname
     *
     * @mbggenerated
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.companycontact
     *
     * @return the value of member_info_record.companycontact
     *
     * @mbggenerated
     */
    public String getCompanycontact() {
        return companycontact;
    }

    public String getLicenseImagePath() {
		return licenseImagePath;
	}

	public void setLicenseImagePath(String licenseImagePath) {
		this.licenseImagePath = licenseImagePath;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.companycontact
     *
     * @param companycontact the value for member_info_record.companycontact
     *
     * @mbggenerated
     */
    public void setCompanycontact(String companycontact) {
        this.companycontact = companycontact == null ? null : companycontact.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.companycode
     *
     * @return the value of member_info_record.companycode
     *
     * @mbggenerated
     */
    public String getCompanycode() {
        return companycode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.companycode
     *
     * @param companycode the value for member_info_record.companycode
     *
     * @mbggenerated
     */
    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.companytel
     *
     * @return the value of member_info_record.companytel
     *
     * @mbggenerated
     */
    public String getCompanytel() {
        return companytel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.companytel
     *
     * @param companytel the value for member_info_record.companytel
     *
     * @mbggenerated
     */
    public void setCompanytel(String companytel) {
        this.companytel = companytel == null ? null : companytel.trim();
    }

    

    public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.auditid
     *
     * @return the value of member_info_record.auditid
     *
     * @mbggenerated
     */
    public String getAuditid() {
        return auditid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.auditid
     *
     * @param auditid the value for member_info_record.auditid
     *
     * @mbggenerated
     */
    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.auditname
     *
     * @return the value of member_info_record.auditname
     *
     * @mbggenerated
     */
    public String getAuditname() {
        return auditname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.auditname
     *
     * @param auditname the value for member_info_record.auditname
     *
     * @mbggenerated
     */
    public void setAuditname(String auditname) {
        this.auditname = auditname == null ? null : auditname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.audittime
     *
     * @return the value of member_info_record.audittime
     *
     * @mbggenerated
     */
    public Long getAudittime() {
        return audittime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.audittime
     *
     * @param audittime the value for member_info_record.audittime
     *
     * @mbggenerated
     */
    public void setAudittime(Long audittime) {
        this.audittime = audittime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_info_record.auditresson
     *
     * @return the value of member_info_record.auditresson
     *
     * @mbggenerated
     */
    public String getAuditresson() {
        return auditresson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_info_record.auditresson
     *
     * @param auditresson the value for member_info_record.auditresson
     *
     * @mbggenerated
     */
    public void setAuditresson(String auditresson) {
        this.auditresson = auditresson == null ? null : auditresson.trim();
    }

	public String getRtblno() {
		return rtblno;
	}

	public void setRtblno(String rtblno) {
		this.rtblno = rtblno;
	}

	public String getRtblimgurl() {
		return rtblimgurl;
	}

	public void setRtblimgurl(String rtblimgurl) {
		this.rtblimgurl = rtblimgurl;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
    
    
}