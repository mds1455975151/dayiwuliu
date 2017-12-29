package com.tianrui.api.req.groupMsg;

public class CustomRcordReq {
	
	private Long id;//主键
	
	private String problemDescribe;//问题备注信息
	
	private Byte solvingState;//处理状态0-待处理，1-处理中，2-已处理
	
	private String sysUser;//操作员

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProblemDescribe() {
		return problemDescribe;
	}

	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}

	public Byte getSolvingState() {
		return solvingState;
	}

	public void setSolvingState(Byte solvingState) {
		this.solvingState = solvingState;
	}

	public String getSysUser() {
		return sysUser;
	}

	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}
}
