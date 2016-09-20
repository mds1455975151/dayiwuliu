package com.tianrui.service.bean;

import java.util.List;

public class PayInvoiceDetail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.pay_id
     *
     * @mbggenerated
     */
    private String payId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.bill_id
     *
     * @mbggenerated
     */
    private String billId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.bill_code
     *
     * @mbggenerated
     */
    private String billCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.cargo_id
     *
     * @mbggenerated
     */
    private String cargoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.cargo_code
     *
     * @mbggenerated
     */
    private String cargoCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.invoice_type
     *
     * @mbggenerated
     */
    private String invoiceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.sign_time
     *
     * @mbggenerated
     */
    private String signTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.bill_weight
     *
     * @mbggenerated
     */
    private Double billWeight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.bill_price
     *
     * @mbggenerated
     */
    private Double billPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.bill_total_price
     *
     * @mbggenerated
     */
    private Double billTotalPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.is_invoice
     *
     * @mbggenerated
     */
    private Byte isInvoice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.orgid
     *
     * @mbggenerated
     */
    private String orgid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.org_name
     *
     * @mbggenerated
     */
    private String orgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.vender_name
     *
     * @mbggenerated
     */
    private String venderName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.vender_type
     *
     * @mbggenerated
     */
    private Byte venderType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.vender_code
     *
     * @mbggenerated
     */
    private String venderCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.creator
     *
     * @mbggenerated
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.createtime
     *
     * @mbggenerated
     */
    private Long createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.modifier
     *
     * @mbggenerated
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice_detail.modifytime
     *
     * @mbggenerated
     */
    private Long modifytime;

    private String venderId;
    private String ownerId;
    private String cargoName;
    private String invoiceTypeName;
    private String taxRate;//税率
    /**
     * this fileds not in db ,only for query;
     */
    private Integer start;
    private Integer limit;
    private List<String> ids;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.id
     *
     * @return the value of pay_invoice_detail.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.id
     *
     * @param id the value for pay_invoice_detail.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.pay_id
     *
     * @return the value of pay_invoice_detail.pay_id
     *
     * @mbggenerated
     */
    public String getPayId() {
        return payId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.pay_id
     *
     * @param payId the value for pay_invoice_detail.pay_id
     *
     * @mbggenerated
     */
    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.bill_id
     *
     * @return the value of pay_invoice_detail.bill_id
     *
     * @mbggenerated
     */
    public String getBillId() {
        return billId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.bill_id
     *
     * @param billId the value for pay_invoice_detail.bill_id
     *
     * @mbggenerated
     */
    public void setBillId(String billId) {
        this.billId = billId == null ? null : billId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.bill_code
     *
     * @return the value of pay_invoice_detail.bill_code
     *
     * @mbggenerated
     */
    public String getBillCode() {
        return billCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.bill_code
     *
     * @param billCode the value for pay_invoice_detail.bill_code
     *
     * @mbggenerated
     */
    public void setBillCode(String billCode) {
        this.billCode = billCode == null ? null : billCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.cargo_id
     *
     * @return the value of pay_invoice_detail.cargo_id
     *
     * @mbggenerated
     */
    public String getCargoId() {
        return cargoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.cargo_id
     *
     * @param cargoId the value for pay_invoice_detail.cargo_id
     *
     * @mbggenerated
     */
    public void setCargoId(String cargoId) {
        this.cargoId = cargoId == null ? null : cargoId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.cargo_code
     *
     * @return the value of pay_invoice_detail.cargo_code
     *
     * @mbggenerated
     */
    public String getCargoCode() {
        return cargoCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.cargo_code
     *
     * @param cargoCode the value for pay_invoice_detail.cargo_code
     *
     * @mbggenerated
     */
    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode == null ? null : cargoCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.invoice_type
     *
     * @return the value of pay_invoice_detail.invoice_type
     *
     * @mbggenerated
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.invoice_type
     *
     * @param invoiceType the value for pay_invoice_detail.invoice_type
     *
     * @mbggenerated
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.sign_time
     *
     * @return the value of pay_invoice_detail.sign_time
     *
     * @mbggenerated
     */
    public String getSignTime() {
        return signTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.sign_time
     *
     * @param signTime the value for pay_invoice_detail.sign_time
     *
     * @mbggenerated
     */
    public void setSignTime(String signTime) {
        this.signTime = signTime == null ? null : signTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.bill_weight
     *
     * @return the value of pay_invoice_detail.bill_weight
     *
     * @mbggenerated
     */
    public Double getBillWeight() {
        return billWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.bill_weight
     *
     * @param billWeight the value for pay_invoice_detail.bill_weight
     *
     * @mbggenerated
     */
    public void setBillWeight(Double billWeight) {
        this.billWeight = billWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.bill_price
     *
     * @return the value of pay_invoice_detail.bill_price
     *
     * @mbggenerated
     */
    public Double getBillPrice() {
        return billPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.bill_price
     *
     * @param billPrice the value for pay_invoice_detail.bill_price
     *
     * @mbggenerated
     */
    public void setBillPrice(Double billPrice) {
        this.billPrice = billPrice;
    }



    public Double getBillTotalPrice() {
		return billTotalPrice;
	}

	public void setBillTotalPrice(Double billTotalPrice) {
		this.billTotalPrice = billTotalPrice;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.is_invoice
     *
     * @return the value of pay_invoice_detail.is_invoice
     *
     * @mbggenerated
     */
    public Byte getIsInvoice() {
        return isInvoice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.is_invoice
     *
     * @param isInvoice the value for pay_invoice_detail.is_invoice
     *
     * @mbggenerated
     */
    public void setIsInvoice(Byte isInvoice) {
        this.isInvoice = isInvoice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.orgid
     *
     * @return the value of pay_invoice_detail.orgid
     *
     * @mbggenerated
     */
    public String getOrgid() {
        return orgid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.orgid
     *
     * @param orgid the value for pay_invoice_detail.orgid
     *
     * @mbggenerated
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.org_name
     *
     * @return the value of pay_invoice_detail.org_name
     *
     * @mbggenerated
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.org_name
     *
     * @param orgName the value for pay_invoice_detail.org_name
     *
     * @mbggenerated
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.vender_name
     *
     * @return the value of pay_invoice_detail.vender_name
     *
     * @mbggenerated
     */
    public String getVenderName() {
        return venderName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.vender_name
     *
     * @param venderName the value for pay_invoice_detail.vender_name
     *
     * @mbggenerated
     */
    public void setVenderName(String venderName) {
        this.venderName = venderName == null ? null : venderName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.vender_type
     *
     * @return the value of pay_invoice_detail.vender_type
     *
     * @mbggenerated
     */
    public Byte getVenderType() {
        return venderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.vender_type
     *
     * @param venderType the value for pay_invoice_detail.vender_type
     *
     * @mbggenerated
     */
    public void setVenderType(Byte venderType) {
        this.venderType = venderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.vender_code
     *
     * @return the value of pay_invoice_detail.vender_code
     *
     * @mbggenerated
     */
    public String getVenderCode() {
        return venderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.vender_code
     *
     * @param venderCode the value for pay_invoice_detail.vender_code
     *
     * @mbggenerated
     */
    public void setVenderCode(String venderCode) {
        this.venderCode = venderCode == null ? null : venderCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.creator
     *
     * @return the value of pay_invoice_detail.creator
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.creator
     *
     * @param creator the value for pay_invoice_detail.creator
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.createtime
     *
     * @return the value of pay_invoice_detail.createtime
     *
     * @mbggenerated
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.createtime
     *
     * @param createtime the value for pay_invoice_detail.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.modifier
     *
     * @return the value of pay_invoice_detail.modifier
     *
     * @mbggenerated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.modifier
     *
     * @param modifier the value for pay_invoice_detail.modifier
     *
     * @mbggenerated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice_detail.modifytime
     *
     * @return the value of pay_invoice_detail.modifytime
     *
     * @mbggenerated
     */
    public Long getModifytime() {
        return modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice_detail.modifytime
     *
     * @param modifytime the value for pay_invoice_detail.modifytime
     *
     * @mbggenerated
     */
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getVenderId() {
		return venderId;
	}

	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	
	
    
}