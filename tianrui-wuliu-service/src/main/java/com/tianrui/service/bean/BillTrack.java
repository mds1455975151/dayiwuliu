package com.tianrui.service.bean;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
  * @ClassName: BillTrack 
  * @Description: 运单轨迹
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月4日 下午3:38:40 
  *
 */
@Document(collection = "billtrack")
public class BillTrack implements Serializable{
	private static final long serialVersionUID = 8379994076474746067L;
	private String id;
    private String billId;
    private String msg;
    private String createTime;
    //是否需要显示 1显示  0不显示
    private int isShow;
    private long timestamp;
    private String operator;
    private int status;
    
    
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
    
    
}