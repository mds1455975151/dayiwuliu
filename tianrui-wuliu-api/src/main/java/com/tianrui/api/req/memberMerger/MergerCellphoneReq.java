package com.tianrui.api.req.memberMerger;

public class MergerCellphoneReq {

	//合并的主用户id
	private String mainMemberid;
	//待合并的用户id   多个id  ;  分割
	private String otherMemberids;
	//身份证号
	private String idCard;

	public String getMainMemberid() {
		return mainMemberid;
	}

	public void setMainMemberid(String mainMemberid) {
		this.mainMemberid = mainMemberid;
	}

	public String getOtherMemberids() {
		return otherMemberids;
	}

	public void setOtherMemberids(String otherMemberids) {
		this.otherMemberids = otherMemberids;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
