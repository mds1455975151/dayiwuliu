package com.tianrui.api.resp.front.bill;

import com.tianrui.api.resp.BaseResp;

public class BillTrackResp extends BaseResp{

	private static final long serialVersionUID = 1L;
	private String id;
    private String billId;
    private String msg;
    private String createTime;
    //是否需要显示 1显示  0不显示
    private int isShow;
    private long timestamp;
    private String operator;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
    
    

}
