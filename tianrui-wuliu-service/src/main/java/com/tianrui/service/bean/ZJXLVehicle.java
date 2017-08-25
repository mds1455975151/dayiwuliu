package com.tianrui.service.bean;

public class ZJXLVehicle {
    private String id;

    private String vehicleid;

    private String vehicleno;

    private String crossloge;

    private String vehiclelogo;

    private String creator;

    private Long createtime;

    private String modifier;

    private Long modifytime;
    
    private int pageNo;
	/**
	 * 每页几条
	 */
	private int pageSize;
	private Integer limit;
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
    }

    public String getCrossloge() {
        return crossloge;
    }

    public void setCrossloge(String crossloge) {
        this.crossloge = crossloge == null ? null : crossloge.trim();
    }

    public String getVehiclelogo() {
        return vehiclelogo;
    }

    public void setVehiclelogo(String vehiclelogo) {
        this.vehiclelogo = vehiclelogo == null ? null : vehiclelogo.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Long getModifytime() {
        return modifytime;
    }

    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
    
    
}