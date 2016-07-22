package com.tianrui.api.req.front.system;

import com.tianrui.api.req.BaseReq;

public class FileUploadReq extends BaseReq{

	private static final long serialVersionUID = 1298849672113160842L;
	/**
	 * Base64编码字符串
	 */
	private String imgStr;
	/**
	 * 上传者id
	 */
	private String uId;

	public String getImgStr() {
		return imgStr;
	}

	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}
	
}
