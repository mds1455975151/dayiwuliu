package com.tianrui.service.bean;

public class MemberCapa {
    private String id;

    private String memberid;
    //查询类型
    private String type;
    
    private String search;

    private String vehicleno;
    private String drivername;
    private String drivertel;
    
    private String vehicleid;

    private String ownerid;

    private String status;

    private String creater;

    private Long createtime;

    private String updater;

    private Long updatetime;

    private String desc1;

    private String desc2;

    private String desc3;

    private String desc4;
    
    private Integer start;
    
    private Integer limit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMemberid() {
        return memberid;
    }

    public String getVehicleno() {
		return vehicleno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDrivertel() {
		return drivertel;
	}

	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}

	public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid == null ? null : ownerid.trim();
    }

    public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
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
}