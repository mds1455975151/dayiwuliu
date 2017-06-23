package com.tianrui.api.req.admin;

public class PushMember {
	//供应商id
	private String suppid;
	//供应商名称
	private String name;
	//营业执照号或身份证号
	private String vbusinlicense;
	
	public String getSuppid() {
		return suppid;
	}
	public void setSuppid(String suppid) {
		this.suppid = suppid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVbusinlicense() {
		return vbusinlicense;
	}
	public void setVbusinlicense(String vbusinlicense) {
		this.vbusinlicense = vbusinlicense;
	}
	
}
