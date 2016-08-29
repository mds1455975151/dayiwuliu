package com.tianrui.api.resp.front.version;

import com.tianrui.api.resp.BaseResp;

public class AppVersionResp extends BaseResp{
	private String id;

    private String appname;

    private String code;
    
    private String appversion;

    private String versionurl;

    private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getAppversion() {
		return appversion;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public String getVersionurl() {
		return versionurl;
	}

	public void setVersionurl(String versionurl) {
		this.versionurl = versionurl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
