package com.tianrui.service.admin.bean;

import com.tianrui.common.constants.Constant;

/**
 * @Description 账单Bean
 * @author zhanggaohao
 * @version 2017年6月20日 上午11:25:56
 */
public class PayInvoice {
	
    private String id;
    //账单编码
    private String code;
    //发票类型CODE
    private String invoiceCode;
    //发票类型NAME
    private String invoiceName;
    //应付金额
    private Double amountPayable;
    //已付金额
    private Double paidAmount;
    //审核状态（0：未审核，1：审核中，2：已审核）
    private Integer auditStatus;
    //审核时间
    private Long auditTime;
    //推单状态（0：未推单，1：退单中，2已退单）
    private Integer pushStatus;
    private String payInvoiceStatus;
    //推单时间
    private Long pushTime;
    //支付状态（0：未支付，1：支付中，2：已支付）
    private Integer payStatus;
    
    //支付时间
    private Long payTime;
    //组织ID
    private String orgid;
    //组织名称
    private String orgname;
    //物料编码
    private String materialCode;
    //物料名称
    private String materialName;
    //收款人ID
    private String payeeId;
    //收款人名称
    private String payeeName;
    //收款人身份（1：司机，2：车主）
    private Integer payeeIdentity;
    //收款人帐号
    private String payeeAccount;
    //收款人银行卡ID
    private String payeeBankCardId;
    //收款人银行卡号
    private String payeeBankCardNumber;
    //收款人有效证件号码
    private String payeeIdNo;
    //前台运单总价
    private Double receptionBillTotalPrice;
    //前台扣重扣杂
    private Double receptionDeductWeightMisc;
    //前台扣款
    private Double receptionDeductMoney;
    //前台其他扣款
    private Double receptionDeductOther;
    //前台油卡扣款
    private Double receptionDeductOilCard;
    //后台运单总价
    private Double backstageBillTotalPrice;
    //后台扣重扣杂
    private Double backstageDeductWeightMisc;
    //后台扣款
    private Double backstageDeductMoney;
    //后台其他扣款
    private Double backstageDeductOther;
    //后台油卡扣款
    private Double backstageDeductOilCard;
    //申请时间
    private Long applicationTime;
    //是否有效（0：无效，1：有效）
    private Integer state;
    //创建人
    private String creator;
    //创建时间
    private Long createTime;
    //修改人
    private String modifier;
    //修改时间
    private Long modifyTime;
    //说明
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName == null ? null : invoiceName.trim();
    }

    public Double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(Double amountPayable) {
        this.amountPayable = amountPayable;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Long getPushTime() {
        return pushTime;
    }

    public void setPushTime(Long pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode == null ? null : materialCode.trim();
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName == null ? null : materialName.trim();
	}

	public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId == null ? null : payeeId.trim();
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public Integer getPayeeIdentity() {
        return payeeIdentity;
    }

    public void setPayeeIdentity(Integer payeeIdentity) {
        this.payeeIdentity = payeeIdentity;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount == null ? null : payeeAccount.trim();
    }

    public String getPayeeBankCardId() {
		return payeeBankCardId;
	}

	public void setPayeeBankCardId(String payeeBankCardId) {
		this.payeeBankCardId = payeeBankCardId == null ? null : payeeBankCardId.trim();
	}

	public String getPayeeBankCardNumber() {
        return payeeBankCardNumber;
    }

    public void setPayeeBankCardNumber(String payeeBankCardNumber) {
        this.payeeBankCardNumber = payeeBankCardNumber == null ? null : payeeBankCardNumber.trim();
    }

    public String getPayeeIdNo() {
        return payeeIdNo;
    }

    public void setPayeeIdNo(String payeeIdNo) {
        this.payeeIdNo = payeeIdNo == null ? null : payeeIdNo.trim();
    }

    public Double getReceptionBillTotalPrice() {
		return receptionBillTotalPrice;
	}

	public void setReceptionBillTotalPrice(Double receptionBillTotalPrice) {
		this.receptionBillTotalPrice = receptionBillTotalPrice;
	}

	public Double getReceptionDeductWeightMisc() {
        return receptionDeductWeightMisc;
    }

    public void setReceptionDeductWeightMisc(Double receptionDeductWeightMisc) {
        this.receptionDeductWeightMisc = receptionDeductWeightMisc;
    }

    public Double getReceptionDeductMoney() {
        return receptionDeductMoney;
    }

    public void setReceptionDeductMoney(Double receptionDeductMoney) {
        this.receptionDeductMoney = receptionDeductMoney;
    }

    public Double getReceptionDeductOther() {
        return receptionDeductOther;
    }

    public void setReceptionDeductOther(Double receptionDeductOther) {
        this.receptionDeductOther = receptionDeductOther;
    }

    public Double getReceptionDeductOilCard() {
        return receptionDeductOilCard;
    }

    public void setReceptionDeductOilCard(Double receptionDeductOilCard) {
        this.receptionDeductOilCard = receptionDeductOilCard;
    }

    public Double getBackstageBillTotalPrice() {
		return backstageBillTotalPrice;
	}

	public void setBackstageBillTotalPrice(Double backstageBillTotalPrice) {
		this.backstageBillTotalPrice = backstageBillTotalPrice;
	}

	public Double getBackstageDeductWeightMisc() {
        return backstageDeductWeightMisc;
    }

    public void setBackstageDeductWeightMisc(Double backstageDeductWeightMisc) {
        this.backstageDeductWeightMisc = backstageDeductWeightMisc;
    }

    public Double getBackstageDeductMoney() {
        return backstageDeductMoney;
    }

    public void setBackstageDeductMoney(Double backstageDeductMoney) {
        this.backstageDeductMoney = backstageDeductMoney;
    }

    public Double getBackstageDeductOther() {
        return backstageDeductOther;
    }

    public void setBackstageDeductOther(Double backstageDeductOther) {
        this.backstageDeductOther = backstageDeductOther;
    }

    public Double getBackstageDeductOilCard() {
        return backstageDeductOilCard;
    }

    public void setBackstageDeductOilCard(Double backstageDeductOilCard) {
        this.backstageDeductOilCard = backstageDeductOilCard;
    }

    public Long getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Long applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getPayInvoiceStatus() {
		if(auditStatus==Constant.NOT_AUDIT){
			//未审核
			this.payInvoiceStatus = "未审核";
		}else if(auditStatus == Constant.YES_AUDIT&&pushStatus == Constant.NOT_PUSH){
			//已审核，未推送
			this.payInvoiceStatus = "已审核";
		}else if(pushStatus == Constant.PUSH_ING&&payStatus == Constant.NOT_PAY){
			//推送中
			this.payInvoiceStatus = "推送中";
		}else if(pushStatus == Constant.YES_PUSH&&payStatus == Constant.NOT_PAY){
			//支付中
			this.payInvoiceStatus = "已推送";
		}else if (payStatus == Constant.YES_PAY){
			//已支付
			this.payInvoiceStatus = "支付完成";
		} else {
			this.payInvoiceStatus = "";
		}
		return payInvoiceStatus;
	}

	public void setPayInvoiceStatus(String payInvoiceStatus) {
		this.payInvoiceStatus = payInvoiceStatus;
	}

	public PayInvoice() {
		super();
	}

	public PayInvoice(String id, String code, String invoiceCode, String invoiceName, Double amountPayable,
			Double paidAmount, Integer auditStatus, Long auditTime, Integer pushStatus, Long pushTime,
			Integer payStatus, Long payTime, String orgid, String orgname, String materialCode, String materialName,
			String payeeId, String payeeName, Integer payeeIdentity, String payeeAccount, String payeeBankCardId,
			String payeeBankCardNumber, String payeeIdNo, Double receptionBillTotalPrice,
			Double receptionDeductWeightMisc, Double receptionDeductMoney, Double receptionDeductOther,
			Double receptionDeductOilCard, Double backstageBillTotalPrice, Double backstageDeductWeightMisc,
			Double backstageDeductMoney, Double backstageDeductOther, Double backstageDeductOilCard,
			Long applicationTime, Integer state, String creator, Long createTime, String modifier, Long modifyTime,
			String remark) {
		super();
		this.id = id;
		this.code = code;
		this.invoiceCode = invoiceCode;
		this.invoiceName = invoiceName;
		this.amountPayable = amountPayable;
		this.paidAmount = paidAmount;
		this.auditStatus = auditStatus;
		this.auditTime = auditTime;
		this.pushStatus = pushStatus;
		this.pushTime = pushTime;
		this.payStatus = payStatus;
		this.payTime = payTime;
		this.orgid = orgid;
		this.orgname = orgname;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.payeeId = payeeId;
		this.payeeName = payeeName;
		this.payeeIdentity = payeeIdentity;
		this.payeeAccount = payeeAccount;
		this.payeeBankCardId = payeeBankCardId;
		this.payeeBankCardNumber = payeeBankCardNumber;
		this.payeeIdNo = payeeIdNo;
		this.receptionBillTotalPrice = receptionBillTotalPrice;
		this.receptionDeductWeightMisc = receptionDeductWeightMisc;
		this.receptionDeductMoney = receptionDeductMoney;
		this.receptionDeductOther = receptionDeductOther;
		this.receptionDeductOilCard = receptionDeductOilCard;
		this.backstageBillTotalPrice = backstageBillTotalPrice;
		this.backstageDeductWeightMisc = backstageDeductWeightMisc;
		this.backstageDeductMoney = backstageDeductMoney;
		this.backstageDeductOther = backstageDeductOther;
		this.backstageDeductOilCard = backstageDeductOilCard;
		this.applicationTime = applicationTime;
		this.state = state;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "PayInvoice [id=" + id + ", code=" + code + ", invoiceCode=" + invoiceCode + ", invoiceName="
				+ invoiceName + ", amountPayable=" + amountPayable + ", paidAmount=" + paidAmount + ", auditStatus="
				+ auditStatus + ", auditTime=" + auditTime + ", pushStatus=" + pushStatus + ", pushTime=" + pushTime
				+ ", payStatus=" + payStatus + ", payTime=" + payTime + ", orgid=" + orgid + ", orgname=" + orgname
				+ ", materialCode=" + materialCode + ", materialName=" + materialName + ", payeeId=" + payeeId
				+ ", payeeName=" + payeeName + ", payeeIdentity=" + payeeIdentity + ", payeeAccount=" + payeeAccount
				+ ", payeeBankCardId=" + payeeBankCardId + ", payeeBankCardNumber=" + payeeBankCardNumber
				+ ", payeeIdNo=" + payeeIdNo + ", receptionBillTotalPrice=" + receptionBillTotalPrice
				+ ", receptionDeductWeightMisc=" + receptionDeductWeightMisc + ", receptionDeductMoney="
				+ receptionDeductMoney + ", receptionDeductOther=" + receptionDeductOther + ", receptionDeductOilCard="
				+ receptionDeductOilCard + ", backstageBillTotalPrice=" + backstageBillTotalPrice
				+ ", backstageDeductWeightMisc=" + backstageDeductWeightMisc + ", backstageDeductMoney="
				+ backstageDeductMoney + ", backstageDeductOther=" + backstageDeductOther + ", backstageDeductOilCard="
				+ backstageDeductOilCard + ", applicationTime=" + applicationTime + ", state=" + state + ", creator="
				+ creator + ", createTime=" + createTime + ", modifier=" + modifier + ", modifyTime=" + modifyTime
				+ ", remark=" + remark + "]";
	}

}