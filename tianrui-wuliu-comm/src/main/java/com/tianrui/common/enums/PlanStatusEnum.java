package com.tianrui.common.enums;

/**
  * @ClassName: BillStatusEnum 
  * @Description: 运单状态
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月2日 上午10:29:02 
  *
 */
public enum PlanStatusEnum {
	
	
	CANCLE((byte)-1,"回收"),
	NEW((byte)0,"待接单"),
	refuse((byte)1,"已拒绝"),
	ACCEPT((byte)2,"执行中"),
	COMPLETE((byte)3,"已完成");
	
	
	private PlanStatusEnum(byte status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	private PlanStatusEnum(byte status, String statusStr, String desc) {
		this.status = status;
		this.statusStr = statusStr;
		this.desc = desc;
	}

	private byte status;
	private String statusStr;
	private String desc;
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
	
}
