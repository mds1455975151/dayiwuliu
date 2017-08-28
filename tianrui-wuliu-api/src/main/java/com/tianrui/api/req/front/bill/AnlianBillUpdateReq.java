package com.tianrui.api.req.front.bill;

public class AnlianBillUpdateReq {

	private String id;
	/**安联运单状态*/
	private String desc3;
	/** 文字提示*/
	private String desc4;
	//位置开始时间
	private Long ptBegintime;
    //位置结束时间
    private Long ptEndtime;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc3() {
		return desc3;
	}
	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}
	public String getDesc4() {
		return desc4;
	}
	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}
	public Long getPtBegintime() {
		return ptBegintime;
	}
	public void setPtBegintime(Long ptBegintime) {
		this.ptBegintime = ptBegintime;
	}
	public Long getPtEndtime() {
		return ptEndtime;
	}
	public void setPtEndtime(Long ptEndtime) {
		this.ptEndtime = ptEndtime;
	}
}
