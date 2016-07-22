package com.tianrui.service.bean;

public class VehicleDriver implements IModel {
	
	private static final long serialVersionUID = -2339847197298728504L;
	
	/**
	 * 主键
	 */
    private String id;

   /**
    * 司机ID
    */
    private String driverid;

    /**
     * 车辆ID
     */
    private String vehicleid;

    /**
	 * 车牌号
	 */
    private String vehicleno;

    /**
	 * 类型名称
	 */
    private String vehicletypename;

    /**
     * 车辆标识
     */
    private String vehicleremark;

    /**
     * 司机名
     */
    private String drivername;

    /**
     * 司机联系电话
     */
    private String drivertel;

    /**
     * 司机标识
     */
    private String driverremark;
    
    private String creator;
    private Long createtime;
    
    /** 分页开始位置 */
    private Integer start;
    
    /** 分页数据行数 */
    private Integer limit;
    
    private String queryKey;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
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

    public String getVehicletypename() {
        return vehicletypename;
    }

    public void setVehicletypename(String vehicletypename) {
        this.vehicletypename = vehicletypename == null ? null : vehicletypename.trim();
    }

    public String getVehicleremark() {
        return vehicleremark;
    }

    public void setVehicleremark(String vehicleremark) {
        this.vehicleremark = vehicleremark == null ? null : vehicleremark.trim();
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername == null ? null : drivername.trim();
    }

    public String getDrivertel() {
        return drivertel;
    }

    public void setDrivertel(String drivertel) {
        this.drivertel = drivertel == null ? null : drivertel.trim();
    }

    public String getDriverremark() {
        return driverremark;
    }

    public void setDriverremark(String driverremark) {
        this.driverremark = driverremark == null ? null : driverremark.trim();
    }

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
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

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}
	
}