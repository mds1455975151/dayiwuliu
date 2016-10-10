package com.tianrui.common.enums;

/**
  * @ClassName: BillStatusEnum 
  * @Description: 运单状态
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月2日 上午10:29:02 
  *
 */
public enum BillStatusEnum {

	INIT(0,"待司机接受","新建运单,等待司机确认"),
	CANCLE(-1,"已取消","车主收回"),
	REFUSE(7,"已取消","司机拒绝接单"),
	ACCEPT(1,"已接受","司机接单确认,等待提货"),
	DEPARTURE(2,"已装货","司机提货确认,等待到货"),
	TRANSIT(3,"运输中","司机到达确认,等待卸货"),
	DISCHARGECARGO(4,"已到达","司机到货确认,等待签收"),
	SIGN(5,"已卸货","货主签收确认"),
	COMPLETE(6,"已完成","运单已完成");
	
	
	private BillStatusEnum(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	private BillStatusEnum(int status, String statusStr, String desc) {
		this.status = status;
		this.statusStr = statusStr;
		this.desc = desc;
	}

	private int status;
	private String statusStr;
	private String desc;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
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
