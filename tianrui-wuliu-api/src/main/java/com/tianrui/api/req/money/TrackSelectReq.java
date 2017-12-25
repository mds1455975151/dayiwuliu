package com.tianrui.api.req.money;

public class TrackSelectReq {
	
	private Integer pageNo;
	
	private Integer pageSize;
	
	private String customerName;//司机姓名

    private String cellphone;//司机账号

    private String contactNumber;//联系电话

    private String vehicleNo;//车牌号
    
    private Byte problemType;//问题类型1-轨迹异常
    
    private Byte solvingState;//处理状态0-待处理，1-处理中，2-已处理
    
    private Long createTimeBegin;//预警时间开始
    
    private Long createTimeEnd;//预警时间结束

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Byte getProblemType() {
		return problemType;
	}

	public void setProblemType(Byte problemType) {
		this.problemType = problemType;
	}

	public Byte getSolvingState() {
		return solvingState;
	}

	public void setSolvingState(Byte solvingState) {
		this.solvingState = solvingState;
	}

	public Long getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(Long createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	public Long getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Long createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

}
