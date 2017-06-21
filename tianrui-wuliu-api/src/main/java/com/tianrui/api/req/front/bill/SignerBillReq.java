package com.tianrui.api.req.front.bill;

public class SignerBillReq {
    private String id;
    
    private Integer pageNo;
    
    private Integer pageSize;

    private String billtype;

    private String billno;

    private String vehicleno;

    private String cargoname;

    private String billstatus;

    private String payment;

    private String receiveMemberid;

    private Long createtime;

    private Long modifytime;

    private String reciveCellphone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno == null ? null : billno.trim();
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
    }

    public String getCargoname() {
        return cargoname;
    }

    public void setCargoname(String cargoname) {
        this.cargoname = cargoname == null ? null : cargoname.trim();
    }

    public String getBillstatus() {
        return billstatus;
    }

    public void setBillstatus(String billstatus) {
        this.billstatus = billstatus == null ? null : billstatus.trim();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public String getReceiveMemberid() {
        return receiveMemberid;
    }

    public void setReceiveMemberid(String receiveMemberid) {
        this.receiveMemberid = receiveMemberid == null ? null : receiveMemberid.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getModifytime() {
        return modifytime;
    }

    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

    public String getReciveCellphone() {
        return reciveCellphone;
    }

    public void setReciveCellphone(String reciveCellphone) {
        this.reciveCellphone = reciveCellphone == null ? null : reciveCellphone.trim();
    }

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}