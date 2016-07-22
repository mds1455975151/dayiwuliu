package com.tianrui.service.bean;

public class OwnerDriver implements IModel{
	
	private static final long serialVersionUID = -4535315058213562100L;
	
   /**
    * ID
    */
    private String id;
    
    /**
     * app搜索字段
     */
    private String search;

    /**
     * 车主(会员)id
     */
    private String vehicleownerid;

    /**
     * 司机ID
     */
    private String driverid;

    /**
     * 司机姓名
     */
    private String drivername;

    /**
     * 司机电话
     */
    private String drivertel;

    /**
     * 备注名
     */
    private String remarkname;
    
    /** 状态(0-待回复，1-已同意，2-已拒绝，3-已失效) */
    private String status;
    
    /**
     * 创建人
     */

    private String creator;

    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 绑定车辆数目
     */
    private String count;
    
    /** 分页开始位置 */
    private Integer start;
    
    /** 分页数据行数 */
    private Integer limit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVehicleownerid() {
        return vehicleownerid;
    }

    public void setVehicleownerid(String vehicleownerid) {
        this.vehicleownerid = vehicleownerid == null ? null : vehicleownerid.trim();
    }

    public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
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

    public String getRemarkname() {
        return remarkname;
    }

    public void setRemarkname(String remarkname) {
        this.remarkname = remarkname == null ? null : remarkname.trim();
    }

	/**
	 * 获取状态
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 设置状态
	 * @param newStatus
	 */
	public void setStatus(String newStatus) {
		this.status = newStatus;
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
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
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