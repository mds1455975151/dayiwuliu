package com.tianrui.api.req.admin.vehicle_new;

public class DriverNewAuthReq {
    //  主键uuid
    private String id;
    //  0 未审批1审核成功 2审核中3审核失败
    private Byte authstats;
    //  审核人
    private String authuser;

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Byte getAuthstats() {
		return authstats;
	}
	public void setAuthstats(Byte authstats) {
		this.authstats = authstats;
	}
	public String getAuthuser() {
		return authuser;
	}
	public void setAuthuser(String authuser) {
		this.authuser = authuser;
	}
}