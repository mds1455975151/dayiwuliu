package com.tianrui.common.vo;

import java.io.Serializable;

/**
 * 
 * @类描述：系统用户业务对象
 * @创建人：tank
 * @创建时间：2016年1月17日下午5:14:25
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午5:14:25
 * @修改备注：
 *
 */
public class UserLoginVo implements Serializable {

	private static final long serialVersionUID = -4533946150346561654L;
	private String userName;
	/**
	 * 用户登录验证码
	 */
	private int userCode;
	private long startTime;
	private long endTime;
	private int count;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

}
