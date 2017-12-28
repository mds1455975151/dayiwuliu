package com.tianrui.api.req.groupMsg;

public class MemberGroupReq {

	private byte groupType;//1-司机  2-车主  3-货主

	public byte getGroupType() {
		return groupType;
	}
	public void setGroupType(byte groupType) {
		this.groupType = groupType;
	}
}
