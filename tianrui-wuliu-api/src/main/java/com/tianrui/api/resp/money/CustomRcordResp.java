package com.tianrui.api.resp.money;

public class CustomRcordResp {

	private Long id;//主键

    private Byte problemType;//问题类型1-轨迹异常

    private String billNo;//运单号

    private String customerId;//司机id

    private String customerName;//司机姓名

    private String cellphone;//司机账号

    private String contactNumber;//联系电话

    private String vehicleNo;//车牌号

    private Long createTime;//预警时间

    private Long lossTime;//失联时间

    private Long reconnectionTime;//重连时间

    private Byte solvingState;//处理状态0-待处理，1-处理中，2-已处理

    private Long endTime;//处理完成时间

    private String problemDescribe;//问题备注信息

    private String solvingUserid;//处理人id

    private String solvingUsername;//处理人姓名

    private Byte ifPush;//推送消息0-未推送，1-已推送

    private Byte ifSms;//短信通知0-未发送，1-已发送

    private Byte ifCall;//电话通知0-未拨打，1-已打通

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getProblemType() {
		return problemType;
	}

	public void setProblemType(Byte problemType) {
		this.problemType = problemType;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLossTime() {
		return lossTime;
	}

	public void setLossTime(Long lossTime) {
		this.lossTime = lossTime;
	}

	public Long getReconnectionTime() {
		return reconnectionTime;
	}

	public void setReconnectionTime(Long reconnectionTime) {
		this.reconnectionTime = reconnectionTime;
	}

	public Byte getSolvingState() {
		return solvingState;
	}

	public void setSolvingState(Byte solvingState) {
		this.solvingState = solvingState;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getProblemDescribe() {
		return problemDescribe;
	}

	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}

	public String getSolvingUserid() {
		return solvingUserid;
	}

	public void setSolvingUserid(String solvingUserid) {
		this.solvingUserid = solvingUserid;
	}

	public String getSolvingUsername() {
		return solvingUsername;
	}

	public void setSolvingUsername(String solvingUsername) {
		this.solvingUsername = solvingUsername;
	}

	public Byte getIfPush() {
		return ifPush;
	}

	public void setIfPush(Byte ifPush) {
		this.ifPush = ifPush;
	}

	public Byte getIfSms() {
		return ifSms;
	}

	public void setIfSms(Byte ifSms) {
		this.ifSms = ifSms;
	}

	public Byte getIfCall() {
		return ifCall;
	}

	public void setIfCall(Byte ifCall) {
		this.ifCall = ifCall;
	}
}
