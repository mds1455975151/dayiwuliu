package com.tianrui.api.resp.front.creditManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.resp.front.CreditScore.CreditScoreResp;

public class CreditManageResp extends BaseResp {

	private static final long serialVersionUID = -6135490751934868740L;

	private String id;

    private String venderid;

    private String vendertel;

    private String vendername;

    private String vehiclesum;

    private String billvehiclesum;

    private String billcount;

    private String timelystart;

    private String timelydelivery;

    private String timelyreturn;

    private String cardamage;

    private String transportaccident;

    private String credityear;

    private String creditmonth;

    private Long createtime;

    private String remark;
    
    private CreditScoreResp creditScoreResp;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVenderid() {
        return venderid;
    }

    public void setVenderid(String venderid) {
        this.venderid = venderid == null ? null : venderid.trim();
    }

    public String getVendertel() {
        return vendertel;
    }

    public void setVendertel(String vendertel) {
        this.vendertel = vendertel == null ? null : vendertel.trim();
    }

    public String getVendername() {
        return vendername;
    }

    public void setVendername(String vendername) {
        this.vendername = vendername == null ? null : vendername.trim();
    }

    public String getVehiclesum() {
        return vehiclesum;
    }

    public void setVehiclesum(String vehiclesum) {
        this.vehiclesum = vehiclesum == null ? null : vehiclesum.trim();
    }

    public String getBillvehiclesum() {
        return billvehiclesum;
    }

    public void setBillvehiclesum(String billvehiclesum) {
        this.billvehiclesum = billvehiclesum == null ? null : billvehiclesum.trim();
    }

    public String getBillcount() {
        return billcount;
    }

    public void setBillcount(String billcount) {
        this.billcount = billcount == null ? null : billcount.trim();
    }

    public String getTimelystart() {
        return timelystart;
    }

    public void setTimelystart(String timelystart) {
        this.timelystart = timelystart == null ? null : timelystart.trim();
    }

    public String getTimelydelivery() {
        return timelydelivery;
    }

    public void setTimelydelivery(String timelydelivery) {
        this.timelydelivery = timelydelivery == null ? null : timelydelivery.trim();
    }

    public String getTimelyreturn() {
        return timelyreturn;
    }

    public void setTimelyreturn(String timelyreturn) {
        this.timelyreturn = timelyreturn == null ? null : timelyreturn.trim();
    }

    public String getCardamage() {
        return cardamage;
    }

    public void setCardamage(String cardamage) {
        this.cardamage = cardamage == null ? null : cardamage.trim();
    }

    public String getTransportaccident() {
        return transportaccident;
    }

    public void setTransportaccident(String transportaccident) {
        this.transportaccident = transportaccident == null ? null : transportaccident.trim();
    }

    public String getCredityear() {
        return credityear;
    }

    public void setCredityear(String credityear) {
        this.credityear = credityear == null ? null : credityear.trim();
    }

    public String getCreditmonth() {
        return creditmonth;
    }

    public void setCreditmonth(String creditmonth) {
        this.creditmonth = creditmonth == null ? null : creditmonth.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public CreditScoreResp getCreditScoreResp() {
		return creditScoreResp;
	}

	public void setCreditScoreResp(CreditScoreResp creditScoreResp) {
		this.creditScoreResp = creditScoreResp;
	}

}