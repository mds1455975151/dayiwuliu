package com.tianrui.service.bean;

public class MemberVehicle implements IModel {
	
	private static final long serialVersionUID = -2339847197298728504L;
	
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.id
     *
     * @mbggenerated
     */
    private String id;
    
    /** 车辆运单状态*/
	private String billstatus;
	
	private String registcode;
	private String registimage;
	private String opercode;
	private String operimage;
	
	private String identitycode;
	private String identieyimage;
	private String agreeimage;
	private String roadtransportcode;
	private String roadtransportimage;
	

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.memberid
     *
     * @mbggenerated
     */
    private String memberid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehicleid
     *
     * @mbggenerated
     */
    private String vehicleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehicleprefix
     *
     * @mbggenerated
     */
    private String vehicleprefix;
    
    /**车辆所有人电话*/
	private String vehiOwnerTel;
	/**车辆所有人姓名*/
	private String vehiOwnerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehicleno
     *
     * @mbggenerated
     */
    private String vehicleno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehicletype
     *
     * @mbggenerated
     */
    private String vehicletype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehicletypename
     *
     * @mbggenerated
     */
    private String vehicletypename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehilength
     *
     * @mbggenerated
     */
    private Double vehilength;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehiwidth
     *
     * @mbggenerated
     */
    private Double vehiwidth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehiheight
     *
     * @mbggenerated
     */
    private Double vehiheight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehiweight
     *
     * @mbggenerated
     */
    private Double vehiweight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehilicenseimgpath
     *
     * @mbggenerated
     */
    private String vehilicenseimgpath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehiheadimgpath
     *
     * @mbggenerated
     */
    private String vehiheadimgpath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehileftimgpath
     *
     * @mbggenerated
     */
    private String vehileftimgpath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehirightimgpath
     *
     * @mbggenerated
     */
    private String vehirightimgpath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.vehiandownerimgpath
     *
     * @mbggenerated
     */
    private String vehiandownerimgpath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.memo
     *
     * @mbggenerated
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.desc1
     *
     * @mbggenerated
     */
    private String desc1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.desc2
     *
     * @mbggenerated
     */
    private String desc2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.desc3
     *
     * @mbggenerated
     */
    private String desc3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.desc4
     *
     * @mbggenerated
     */
    private String desc4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.creator
     *
     * @mbggenerated
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.createtime
     *
     * @mbggenerated
     */
    private Long createtime;
    /**
     * 审核时间
     */
    private Long audittime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.modifier
     *
     * @mbggenerated
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wuliu_member_vehicle.modifytime
     *
     * @mbggenerated
     */
    private Long modifytime;

    /** 分页开始位置 */
    private Integer start;
    
    /** 分页数据行数 */
    private Integer limit;
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.id
     *
     * @return the value of wuliu_member_vehicle.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.id
     *
     * @param id the value for wuliu_member_vehicle.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.memberid
     *
     * @return the value of wuliu_member_vehicle.memberid
     *
     * @mbggenerated
     */
    public String getMemberid() {
        return memberid;
    }

    public Long getAudittime() {
		return audittime;
	}

	public void setAudittime(Long audittime) {
		this.audittime = audittime;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.memberid
     *
     * @param memberid the value for wuliu_member_vehicle.memberid
     *
     * @mbggenerated
     */
    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    public String getVehiOwnerTel() {
		return vehiOwnerTel;
	}

	public void setVehiOwnerTel(String vehiOwnerTel) {
		this.vehiOwnerTel = vehiOwnerTel;
	}

	public String getVehiOwnerName() {
		return vehiOwnerName;
	}

	public void setVehiOwnerName(String vehiOwnerName) {
		this.vehiOwnerName = vehiOwnerName;
	}

	public String getRegistcode() {
		return registcode;
	}

	public void setRegistcode(String registcode) {
		this.registcode = registcode;
	}

	public String getRegistimage() {
		return registimage;
	}

	public void setRegistimage(String registimage) {
		this.registimage = registimage;
	}

	public String getOpercode() {
		return opercode;
	}

	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}

	public String getOperimage() {
		return operimage;
	}

	public void setOperimage(String operimage) {
		this.operimage = operimage;
	}

	public String getIdentitycode() {
		return identitycode;
	}

	public void setIdentitycode(String identitycode) {
		this.identitycode = identitycode;
	}

	public String getIdentieyimage() {
		return identieyimage;
	}

	public void setIdentieyimage(String identieyimage) {
		this.identieyimage = identieyimage;
	}

	public String getAgreeimage() {
		return agreeimage;
	}

	public void setAgreeimage(String agreeimage) {
		this.agreeimage = agreeimage;
	}

	public String getRoadtransportcode() {
		return roadtransportcode;
	}

	public void setRoadtransportcode(String roadtransportcode) {
		this.roadtransportcode = roadtransportcode;
	}

	public String getRoadtransportimage() {
		return roadtransportimage;
	}

	public void setRoadtransportimage(String roadtransportimage) {
		this.roadtransportimage = roadtransportimage;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehicleid
     *
     * @return the value of wuliu_member_vehicle.vehicleid
     *
     * @mbggenerated
     */
    public String getVehicleid() {
        return vehicleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehicleid
     *
     * @param vehicleid the value for wuliu_member_vehicle.vehicleid
     *
     * @mbggenerated
     */
    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehicleprefix
     *
     * @return the value of wuliu_member_vehicle.vehicleprefix
     *
     * @mbggenerated
     */
    public String getVehicleprefix() {
        return vehicleprefix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehicleprefix
     *
     * @param vehicleprefix the value for wuliu_member_vehicle.vehicleprefix
     *
     * @mbggenerated
     */
    public void setVehicleprefix(String vehicleprefix) {
        this.vehicleprefix = vehicleprefix == null ? null : vehicleprefix.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehicleno
     *
     * @return the value of wuliu_member_vehicle.vehicleno
     *
     * @mbggenerated
     */
    public String getVehicleno() {
        return vehicleno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehicleno
     *
     * @param vehicleno the value for wuliu_member_vehicle.vehicleno
     *
     * @mbggenerated
     */
    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehicletype
     *
     * @return the value of wuliu_member_vehicle.vehicletype
     *
     * @mbggenerated
     */
    public String getVehicletype() {
        return vehicletype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehicletype
     *
     * @param vehicletype the value for wuliu_member_vehicle.vehicletype
     *
     * @mbggenerated
     */
    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype == null ? null : vehicletype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehicletypename
     *
     * @return the value of wuliu_member_vehicle.vehicletypename
     *
     * @mbggenerated
     */
    public String getVehicletypename() {
        return vehicletypename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehicletypename
     *
     * @param vehicletypename the value for wuliu_member_vehicle.vehicletypename
     *
     * @mbggenerated
     */
    public void setVehicletypename(String vehicletypename) {
        this.vehicletypename = vehicletypename == null ? null : vehicletypename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehilength
     *
     * @return the value of wuliu_member_vehicle.vehilength
     *
     * @mbggenerated
     */
    public Double getVehilength() {
        return vehilength;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehilength
     *
     * @param vehilength the value for wuliu_member_vehicle.vehilength
     *
     * @mbggenerated
     */
    public void setVehilength(Double vehilength) {
        this.vehilength = vehilength;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehiwidth
     *
     * @return the value of wuliu_member_vehicle.vehiwidth
     *
     * @mbggenerated
     */
    public Double getVehiwidth() {
        return vehiwidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehiwidth
     *
     * @param vehiwidth the value for wuliu_member_vehicle.vehiwidth
     *
     * @mbggenerated
     */
    public void setVehiwidth(Double vehiwidth) {
        this.vehiwidth = vehiwidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehiheight
     *
     * @return the value of wuliu_member_vehicle.vehiheight
     *
     * @mbggenerated
     */
    public Double getVehiheight() {
        return vehiheight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehiheight
     *
     * @param vehiheight the value for wuliu_member_vehicle.vehiheight
     *
     * @mbggenerated
     */
    public void setVehiheight(Double vehiheight) {
        this.vehiheight = vehiheight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehiweight
     *
     * @return the value of wuliu_member_vehicle.vehiweight
     *
     * @mbggenerated
     */
    public Double getVehiweight() {
        return vehiweight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehiweight
     *
     * @param vehiweight the value for wuliu_member_vehicle.vehiweight
     *
     * @mbggenerated
     */
    public void setVehiweight(Double vehiweight) {
        this.vehiweight = vehiweight;
    }

    public String getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehilicenseimgpath
     *
     * @return the value of wuliu_member_vehicle.vehilicenseimgpath
     *
     * @mbggenerated
     */
    public String getVehilicenseimgpath() {
        return vehilicenseimgpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehilicenseimgpath
     *
     * @param vehilicenseimgpath the value for wuliu_member_vehicle.vehilicenseimgpath
     *
     * @mbggenerated
     */
    public void setVehilicenseimgpath(String vehilicenseimgpath) {
        this.vehilicenseimgpath = vehilicenseimgpath == null ? null : vehilicenseimgpath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehiheadimgpath
     *
     * @return the value of wuliu_member_vehicle.vehiheadimgpath
     *
     * @mbggenerated
     */
    public String getVehiheadimgpath() {
        return vehiheadimgpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehiheadimgpath
     *
     * @param vehiheadimgpath the value for wuliu_member_vehicle.vehiheadimgpath
     *
     * @mbggenerated
     */
    public void setVehiheadimgpath(String vehiheadimgpath) {
        this.vehiheadimgpath = vehiheadimgpath == null ? null : vehiheadimgpath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehileftimgpath
     *
     * @return the value of wuliu_member_vehicle.vehileftimgpath
     *
     * @mbggenerated
     */
    public String getVehileftimgpath() {
        return vehileftimgpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehileftimgpath
     *
     * @param vehileftimgpath the value for wuliu_member_vehicle.vehileftimgpath
     *
     * @mbggenerated
     */
    public void setVehileftimgpath(String vehileftimgpath) {
        this.vehileftimgpath = vehileftimgpath == null ? null : vehileftimgpath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehirightimgpath
     *
     * @return the value of wuliu_member_vehicle.vehirightimgpath
     *
     * @mbggenerated
     */
    public String getVehirightimgpath() {
        return vehirightimgpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehirightimgpath
     *
     * @param vehirightimgpath the value for wuliu_member_vehicle.vehirightimgpath
     *
     * @mbggenerated
     */
    public void setVehirightimgpath(String vehirightimgpath) {
        this.vehirightimgpath = vehirightimgpath == null ? null : vehirightimgpath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.vehiandownerimgpath
     *
     * @return the value of wuliu_member_vehicle.vehiandownerimgpath
     *
     * @mbggenerated
     */
    public String getVehiandownerimgpath() {
        return vehiandownerimgpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.vehiandownerimgpath
     *
     * @param vehiandownerimgpath the value for wuliu_member_vehicle.vehiandownerimgpath
     *
     * @mbggenerated
     */
    public void setVehiandownerimgpath(String vehiandownerimgpath) {
        this.vehiandownerimgpath = vehiandownerimgpath == null ? null : vehiandownerimgpath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.status
     *
     * @return the value of wuliu_member_vehicle.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.status
     *
     * @param status the value for wuliu_member_vehicle.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.memo
     *
     * @return the value of wuliu_member_vehicle.memo
     *
     * @mbggenerated
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.memo
     *
     * @param memo the value for wuliu_member_vehicle.memo
     *
     * @mbggenerated
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.desc1
     *
     * @return the value of wuliu_member_vehicle.desc1
     *
     * @mbggenerated
     */
    public String getDesc1() {
        return desc1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.desc1
     *
     * @param desc1 the value for wuliu_member_vehicle.desc1
     *
     * @mbggenerated
     */
    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.desc2
     *
     * @return the value of wuliu_member_vehicle.desc2
     *
     * @mbggenerated
     */
    public String getDesc2() {
        return desc2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.desc2
     *
     * @param desc2 the value for wuliu_member_vehicle.desc2
     *
     * @mbggenerated
     */
    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.desc3
     *
     * @return the value of wuliu_member_vehicle.desc3
     *
     * @mbggenerated
     */
    public String getDesc3() {
        return desc3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.desc3
     *
     * @param desc3 the value for wuliu_member_vehicle.desc3
     *
     * @mbggenerated
     */
    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.desc4
     *
     * @return the value of wuliu_member_vehicle.desc4
     *
     * @mbggenerated
     */
    public String getDesc4() {
        return desc4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.desc4
     *
     * @param desc4 the value for wuliu_member_vehicle.desc4
     *
     * @mbggenerated
     */
    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.creator
     *
     * @return the value of wuliu_member_vehicle.creator
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.creator
     *
     * @param creator the value for wuliu_member_vehicle.creator
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.createtime
     *
     * @return the value of wuliu_member_vehicle.createtime
     *
     * @mbggenerated
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.createtime
     *
     * @param createtime the value for wuliu_member_vehicle.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.modifier
     *
     * @return the value of wuliu_member_vehicle.modifier
     *
     * @mbggenerated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.modifier
     *
     * @param modifier the value for wuliu_member_vehicle.modifier
     *
     * @mbggenerated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_member_vehicle.modifytime
     *
     * @return the value of wuliu_member_vehicle.modifytime
     *
     * @mbggenerated
     */
    public Long getModifytime() {
        return modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_member_vehicle.modifytime
     *
     * @param modifytime the value for wuliu_member_vehicle.modifytime
     *
     * @mbggenerated
     */
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }
    
    /**
	 * 获取分页开始位置
	 * @return start
	 */
	public Integer getStart() {
		return start;
	}
	
	/**
	 * 设置分页开始位置
	 * @param newStart
	 */
	public void setStart(Integer newStart) {
		this.start = newStart;
	}
	
	/**
	 * 获取分页数据行数
	 * @return limit
	 */
	public Integer getLimit() {
		return limit;
	}
	
	/**
	 * 设置分页数据行数
	 * @param newLimit
	 */
	public void setLimit(Integer newLimit) {
		this.limit = newLimit;
	}
}