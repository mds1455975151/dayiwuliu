package com.tianrui.service.bean;

public class PayInvoice {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.invoice_type
     *
     * @mbggenerated
     */
    private String invoiceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.pay_deal_price
     *
     * @mbggenerated
     */
    private Double payDealPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.paid_price
     *
     * @mbggenerated
     */
    private Double paidPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.advice_status
     *
     * @mbggenerated
     */
    private Byte adviceStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.advice_time
     *
     * @mbggenerated
     */
    private Long adviceTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.pay_status
     *
     * @mbggenerated
     */
    private Byte payStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.orgid
     *
     * @mbggenerated
     */
    private String orgid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.org_name
     *
     * @mbggenerated
     */
    private String orgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.vender_name
     *
     * @mbggenerated
     */
    private String venderName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.vender_type
     *
     * @mbggenerated
     */
    private Byte venderType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.vender_code
     *
     * @mbggenerated
     */
    private String venderCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.creator
     *
     * @mbggenerated
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.createtime
     *
     * @mbggenerated
     */
    private Long createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.modifier
     *
     * @mbggenerated
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_invoice.modifytime
     *
     * @mbggenerated
     */
    private Long modifytime;
    
    private String venderId;
    private String ownerId;
    private String applyDate;
    private String invoiceTypeName;
    
    /**
     * this fileds not in db ,only for query;
     */
    private Integer start;
    private Integer limit;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.id
     *
     * @return the value of pay_invoice.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.id
     *
     * @param id the value for pay_invoice.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.invoice_type
     *
     * @return the value of pay_invoice.invoice_type
     *
     * @mbggenerated
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.invoice_type
     *
     * @param invoiceType the value for pay_invoice.invoice_type
     *
     * @mbggenerated
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.pay_deal_price
     *
     * @return the value of pay_invoice.pay_deal_price
     *
     * @mbggenerated
     */
    public Double getPayDealPrice() {
        return payDealPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.pay_deal_price
     *
     * @param payDealPrice the value for pay_invoice.pay_deal_price
     *
     * @mbggenerated
     */
    public void setPayDealPrice(Double payDealPrice) {
        this.payDealPrice = payDealPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.paid_price
     *
     * @return the value of pay_invoice.paid_price
     *
     * @mbggenerated
     */
    public Double getPaidPrice() {
        return paidPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.paid_price
     *
     * @param paidPrice the value for pay_invoice.paid_price
     *
     * @mbggenerated
     */
    public void setPaidPrice(Double paidPrice) {
        this.paidPrice = paidPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.advice_status
     *
     * @return the value of pay_invoice.advice_status
     *
     * @mbggenerated
     */
    public Byte getAdviceStatus() {
        return adviceStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.advice_status
     *
     * @param adviceStatus the value for pay_invoice.advice_status
     *
     * @mbggenerated
     */
    public void setAdviceStatus(Byte adviceStatus) {
        this.adviceStatus = adviceStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.advice_time
     *
     * @return the value of pay_invoice.advice_time
     *
     * @mbggenerated
     */
    public Long getAdviceTime() {
        return adviceTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.advice_time
     *
     * @param adviceTime the value for pay_invoice.advice_time
     *
     * @mbggenerated
     */
    public void setAdviceTime(Long adviceTime) {
        this.adviceTime = adviceTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.pay_status
     *
     * @return the value of pay_invoice.pay_status
     *
     * @mbggenerated
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.pay_status
     *
     * @param payStatus the value for pay_invoice.pay_status
     *
     * @mbggenerated
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.orgid
     *
     * @return the value of pay_invoice.orgid
     *
     * @mbggenerated
     */
    public String getOrgid() {
        return orgid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.orgid
     *
     * @param orgid the value for pay_invoice.orgid
     *
     * @mbggenerated
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.org_name
     *
     * @return the value of pay_invoice.org_name
     *
     * @mbggenerated
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.org_name
     *
     * @param orgName the value for pay_invoice.org_name
     *
     * @mbggenerated
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.vender_name
     *
     * @return the value of pay_invoice.vender_name
     *
     * @mbggenerated
     */
    public String getVenderName() {
        return venderName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.vender_name
     *
     * @param venderName the value for pay_invoice.vender_name
     *
     * @mbggenerated
     */
    public void setVenderName(String venderName) {
        this.venderName = venderName == null ? null : venderName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.vender_type
     *
     * @return the value of pay_invoice.vender_type
     *
     * @mbggenerated
     */
    public Byte getVenderType() {
        return venderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.vender_type
     *
     * @param venderType the value for pay_invoice.vender_type
     *
     * @mbggenerated
     */
    public void setVenderType(Byte venderType) {
        this.venderType = venderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.vender_code
     *
     * @return the value of pay_invoice.vender_code
     *
     * @mbggenerated
     */
    public String getVenderCode() {
        return venderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.vender_code
     *
     * @param venderCode the value for pay_invoice.vender_code
     *
     * @mbggenerated
     */
    public void setVenderCode(String venderCode) {
        this.venderCode = venderCode == null ? null : venderCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.creator
     *
     * @return the value of pay_invoice.creator
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.creator
     *
     * @param creator the value for pay_invoice.creator
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.createtime
     *
     * @return the value of pay_invoice.createtime
     *
     * @mbggenerated
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.createtime
     *
     * @param createtime the value for pay_invoice.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.modifier
     *
     * @return the value of pay_invoice.modifier
     *
     * @mbggenerated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.modifier
     *
     * @param modifier the value for pay_invoice.modifier
     *
     * @mbggenerated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_invoice.modifytime
     *
     * @return the value of pay_invoice.modifytime
     *
     * @mbggenerated
     */
    public Long getModifytime() {
        return modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_invoice.modifytime
     *
     * @param modifytime the value for pay_invoice.modifytime
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

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}
    
    
}