package com.tianrui.api.req.money;

public class CheckPasswordReq {

	private String id;
	
	private String cellphone;//用户账号

    private String checkType;//1-支付密码   2-手势密码
	
    private String password;//支付密码

    private String gesturepass;//手势密码

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGesturepass() {
		return gesturepass;
	}

	public void setGesturepass(String gesturepass) {
		this.gesturepass = gesturepass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
