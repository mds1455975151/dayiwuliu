package com.tianrui.api.req.admin;

import com.tianrui.api.req.BasePage;
import com.tianrui.common.constants.Constant;

/**
 * @Description 账单搜索条件
 * @author zhanggaohao
 * @version 2017年6月20日 上午11:25:56
 */
public class PayInvoiceReq extends BasePage {
	
	private static final long serialVersionUID = -5734564963486255823L;
	//模糊匹配运单号
	private String likeBillCode;
	//模糊匹配货物名称
	private String likeCargoName;
	//账单收款身份
	private Integer payeeIdentity;
	//是否有效数据
	private Integer state = Constant.DATA_VALID;
	
	public String getLikeBillCode() {
		return likeBillCode;
	}
	public void setLikeBillCode(String likeBillCode) {
		this.likeBillCode = likeBillCode;
	}
	public String getLikeCargoName() {
		return likeCargoName;
	}
	public void setLikeCargoName(String likeCargoName) {
		this.likeCargoName = likeCargoName;
	}
	public Integer getPayeeIdentity() {
		return payeeIdentity;
	}
	public void setPayeeIdentity(Integer payeeIdentity) {
		this.payeeIdentity = payeeIdentity;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PayInvoiceReq [likeBillCode=" + likeBillCode + ", likeCargoName=" + likeCargoName + ", payeeIdentity="
				+ payeeIdentity + ", state=" + state + "]";
	}
	
}