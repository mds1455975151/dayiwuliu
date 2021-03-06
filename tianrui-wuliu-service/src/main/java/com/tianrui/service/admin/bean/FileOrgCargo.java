package com.tianrui.service.admin.bean;

import com.tianrui.service.bean.IModel;


public class FileOrgCargo implements IModel {
	
	private static final long serialVersionUID = -2339847197298728504L;
	
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.ID
     *
     * @mbggenerated
     */
    private String id;
    
    private String count;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.organizationname
     *
     * @mbggenerated
     */
    private String organizationname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.organizationid
     *
     * @mbggenerated
     */
    private String organizationid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.cargoid
     *
     * @mbggenerated
     */
    private String cargoid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.cargoname
     *
     * @mbggenerated
     */
    private String cargoname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.cargono
     *
     * @mbggenerated
     */
    private String cargono;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.cargostatus
     *
     * @mbggenerated
     */
    private String cargostatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.measure
     *
     * @mbggenerated
     */
    private String measure;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.creator
     *
     * @mbggenerated
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.createtime
     *
     * @mbggenerated
     */
    private Long createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.modifier
     *
     * @mbggenerated
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.modifytime
     *
     * @mbggenerated
     */
    private Long modifytime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.DESC1
     *
     * @mbggenerated
     */
    private String desc1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.DESC2
     *
     * @mbggenerated
     */
    private String desc2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.DESC3
     *
     * @mbggenerated
     */
    private String desc3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_org_cargo.DESC4
     *
     * @mbggenerated
     */
    private String desc4;
    
    /** 分页开始位置 */
    private Integer start;
    
    /** 分页数据行数 */
    private Integer limit;
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.ID
     *
     * @return the value of file_org_cargo.ID
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.ID
     *
     * @param id the value for file_org_cargo.ID
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.organizationname
     *
     * @return the value of file_org_cargo.organizationname
     *
     * @mbggenerated
     */
    public String getOrganizationname() {
        return organizationname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.organizationname
     *
     * @param organizationname the value for file_org_cargo.organizationname
     *
     * @mbggenerated
     */
    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname == null ? null : organizationname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.organizationid
     *
     * @return the value of file_org_cargo.organizationid
     *
     * @mbggenerated
     */
    public String getOrganizationid() {
        return organizationid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.organizationid
     *
     * @param organizationid the value for file_org_cargo.organizationid
     *
     * @mbggenerated
     */
    public void setOrganizationid(String organizationid) {
        this.organizationid = organizationid == null ? null : organizationid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.cargoid
     *
     * @return the value of file_org_cargo.cargoid
     *
     * @mbggenerated
     */
    public String getCargoid() {
        return cargoid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.cargoid
     *
     * @param cargoid the value for file_org_cargo.cargoid
     *
     * @mbggenerated
     */
    public void setCargoid(String cargoid) {
        this.cargoid = cargoid == null ? null : cargoid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.cargoname
     *
     * @return the value of file_org_cargo.cargoname
     *
     * @mbggenerated
     */
    public String getCargoname() {
        return cargoname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.cargoname
     *
     * @param cargoname the value for file_org_cargo.cargoname
     *
     * @mbggenerated
     */
    public void setCargoname(String cargoname) {
        this.cargoname = cargoname == null ? null : cargoname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.cargono
     *
     * @return the value of file_org_cargo.cargono
     *
     * @mbggenerated
     */
    public String getCargono() {
        return cargono;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.cargono
     *
     * @param cargono the value for file_org_cargo.cargono
     *
     * @mbggenerated
     */
    public void setCargono(String cargono) {
        this.cargono = cargono == null ? null : cargono.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.cargostatus
     *
     * @return the value of file_org_cargo.cargostatus
     *
     * @mbggenerated
     */
    public String getCargostatus() {
        return cargostatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.cargostatus
     *
     * @param cargostatus the value for file_org_cargo.cargostatus
     *
     * @mbggenerated
     */
    public void setCargostatus(String cargostatus) {
        this.cargostatus = cargostatus == null ? null : cargostatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.status
     *
     * @return the value of file_org_cargo.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.status
     *
     * @param status the value for file_org_cargo.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.measure
     *
     * @return the value of file_org_cargo.measure
     *
     * @mbggenerated
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.measure
     *
     * @param measure the value for file_org_cargo.measure
     *
     * @mbggenerated
     */
    public void setMeasure(String measure) {
        this.measure = measure == null ? null : measure.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.creator
     *
     * @return the value of file_org_cargo.creator
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.creator
     *
     * @param creator the value for file_org_cargo.creator
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.createtime
     *
     * @return the value of file_org_cargo.createtime
     *
     * @mbggenerated
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.createtime
     *
     * @param createtime the value for file_org_cargo.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.modifier
     *
     * @return the value of file_org_cargo.modifier
     *
     * @mbggenerated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.modifier
     *
     * @param modifier the value for file_org_cargo.modifier
     *
     * @mbggenerated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.modifytime
     *
     * @return the value of file_org_cargo.modifytime
     *
     * @mbggenerated
     */
    public Long getModifytime() {
        return modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.modifytime
     *
     * @param modifytime the value for file_org_cargo.modifytime
     *
     * @mbggenerated
     */
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.DESC1
     *
     * @return the value of file_org_cargo.DESC1
     *
     * @mbggenerated
     */
    public String getDesc1() {
        return desc1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.DESC1
     *
     * @param desc1 the value for file_org_cargo.DESC1
     *
     * @mbggenerated
     */
    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.DESC2
     *
     * @return the value of file_org_cargo.DESC2
     *
     * @mbggenerated
     */
    public String getDesc2() {
        return desc2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.DESC2
     *
     * @param desc2 the value for file_org_cargo.DESC2
     *
     * @mbggenerated
     */
    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.DESC3
     *
     * @return the value of file_org_cargo.DESC3
     *
     * @mbggenerated
     */
    public String getDesc3() {
        return desc3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.DESC3
     *
     * @param desc3 the value for file_org_cargo.DESC3
     *
     * @mbggenerated
     */
    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_org_cargo.DESC4
     *
     * @return the value of file_org_cargo.DESC4
     *
     * @mbggenerated
     */
    public String getDesc4() {
        return desc4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_org_cargo.DESC4
     *
     * @param desc4 the value for file_org_cargo.DESC4
     *
     * @mbggenerated
     */
    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
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