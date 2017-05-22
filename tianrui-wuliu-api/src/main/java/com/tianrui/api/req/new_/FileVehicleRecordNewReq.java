package com.tianrui.api.req.new_;

public class FileVehicleRecordNewReq {
    //  主键uuid
    private String id;

    //  车辆id
    private String vehicleid;

    //  驾驶员id
    private String driverid;

    //  车牌号码
    private String vehicleno;

    //  使用性质,1营运,2非营运
    private String nature;

    //  3开票认证,2完全认证,1临时认证,-1作废
    private Byte authtype;

    //  1认证通过,2认证中,3认证失败 
    private Byte authstatus;
    
    //  审核人
    private String authuser;
    
    private Integer pageNo;
    
    private Integer pageSize;

   

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

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
    }


    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature == null ? null : nature.trim();
    }


    public Byte getAuthtype() {
        return authtype;
    }

    public void setAuthtype(Byte authtype) {
        this.authtype = authtype;
    }

    public Byte getAuthstatus() {
        return authstatus;
    }

    public void setAuthstatus(Byte authstatus) {
        this.authstatus = authstatus;
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

	public String getAuthuser() {
		return authuser;
	}

	public void setAuthuser(String authuser) {
		this.authuser = authuser;
	}

}