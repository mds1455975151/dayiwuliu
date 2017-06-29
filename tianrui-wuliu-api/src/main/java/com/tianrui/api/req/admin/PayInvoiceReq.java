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
	
	private String id;
	
	//账单编码
    private String code;
    //发票类型NAME
    private String invoiceName;
    //查询支付状态 1-未审核 2-未推单 3-推单中 4-已推单 5-支付中 6-已支付
    private Integer pay;
    //审核状态（0：未审核，1：审核中，2：已审核）
    private Integer auditStatus;
    //推单状态（0：未推单，1：退单中，2已退单）
    private Integer pushStatus;
    //支付状态（0：未支付，1：支付中，2：已支付）
    private Integer payStatus;
	
	private String searchKey;
	//模糊匹配运单号
	private String likeBillCode;
	//模糊匹配货物名称
	private String likeCargoName;
	//账单收款身份
	private Integer payeeIdentity;
	//收款人id
	private String payeeId;
	//是否有效数据
	private Integer state = Constant.DATA_VALID;
	
	public Integer getPay() {
		return pay;
	}
	public void setPay(Integer pay) {
		if(pay != null){
			if(pay==1){
				//未审核
				auditStatus=0;
				pushStatus=0;
				payStatus=0;
			}else if(pay==2){
				//未推单
				auditStatus=2;
				pushStatus=0;
				payStatus=0;
			}else if(pay==3){
				//推单中
				auditStatus=2;
				pushStatus=1;
				payStatus=0;
			}else if(pay==4){
				//已推单
				auditStatus=2;
				pushStatus=2;
				payStatus=0;
			}else if(pay==5){
				//支付中
				auditStatus=2;
				pushStatus=2;
				payStatus=1;
			}else if(pay==6){
				//已支付
				auditStatus=2;
				pushStatus=2;
				payStatus=2;
			}
		}
		this.pay = pay;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getPushStatus() {
		return pushStatus;
	}
	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
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
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	@Override
	public String toString() {
		return "PayInvoiceReq [likeBillCode=" + likeBillCode + ", likeCargoName=" + likeCargoName + ", payeeIdentity="
				+ payeeIdentity + ", state=" + state + "]";
	}
	
}