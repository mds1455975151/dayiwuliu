package com.tianrui.service.bean;

public class VehicleDriverMember implements IModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7131829366840656439L;

	/**
	 * 主键
	 */
    private String id;
    private String ids;
    private String vehicleprefix;
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
    /**
     * 开票认证状态
     */
    private String desc1;
    /**
     * 对应安联的司机id
     */
    private String aldriverid;
    
    /** 分页开始位置 */
    private Integer start;
    
    /** 分页数据行数 */
    private Integer limit;
    
    private String queryKey;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getVehicletypename() {
		return vehicletypename;
	}

	public void setVehicletypename(String vehicletypename) {
		this.vehicletypename = vehicletypename;
	}

	public String getVehicleremark() {
		return vehicleremark;
	}

	public void setVehicleremark(String vehicleremark) {
		this.vehicleremark = vehicleremark;
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

	public String getDriverremark() {
		return driverremark;
	}

	public void setDriverremark(String driverremark) {
		this.driverremark = driverremark;
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

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getAldriverid() {
		return aldriverid;
	}

	public void setAldriverid(String aldriverid) {
		this.aldriverid = aldriverid;
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

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getVehicleprefix() {
		return vehicleprefix;
	}

	public void setVehicleprefix(String vehicleprefix) {
		this.vehicleprefix = vehicleprefix;
	}
	
    
    
}
