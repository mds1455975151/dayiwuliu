package com.tianrui.service.bean;

public class LEDCountData {
    private String id;

    private String ledType;

    private String dataType;

    private String remark;

    private Double countdata;

    private String stimestr;

    private Long createTime;

    private Long modifyTime;
    
    private Integer pageNO;
    
    private Integer pageSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLedType() {
        return ledType;
    }

    public void setLedType(String ledType) {
        this.ledType = ledType == null ? null : ledType.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Double getCountdata() {
        return countdata;
    }

    public void setCountdata(Double countdata) {
        this.countdata = countdata;
    }

    public String getStimestr() {
        return stimestr;
    }

    public void setStimestr(String stimestr) {
        this.stimestr = stimestr == null ? null : stimestr.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

	public Integer getPageNO() {
		return pageNO;
	}

	public void setPageNO(Integer pageNO) {
		this.pageNO = pageNO;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}