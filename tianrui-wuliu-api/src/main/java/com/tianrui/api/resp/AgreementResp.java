package com.tianrui.api.resp;

public class AgreementResp {
	
	//货物List
	
	private String billNo;//运单号
	private String venderName;//实际承运人
	private String receiveName;//收货人
	private String receiveCellphone;//收货人手机号
	private String sposition;//装货地点
	private String eposition;//卸货地点
	private String driverName;//驾驶员
	private String driverIDCardNO;//司机身份证号
	private String vehicleNo;//车牌号
	private String motor;//发动机号
	private String driverCellphone;//司机电话
	private String cargoName;//货物名称
	private String specifications;//规格
	private Double pickupweight;//始发数量
	private String measure;//计量单位
	private Double billPrice;//运费单价
	private Double trueweight;//实收数量
	private String	insurance;//保险金额
	private String cargoValue;//货物总价值
	private String dateAcctept;//运单日期
	private String dateUnloading;//到达日期
	private String timeAcctept;//运单时间
	private String timeBIllAcctept;//运单接受时间
	private String timeUnloading;//到达时间
	private Double backstageBillTotalPrice;//运费总价
	private String payMent;//付款方式1-现付。2-月付
	private String linkman;//发货人姓名
	private String linknumber;//发货人电话
    //后台扣重扣杂
    private Double backstageDeductWeightMisc;
    //后台扣款
    private Double backstageDeductMoney;
    //后台其他扣款
    private Double backstageDeductOther;
    //后台油卡扣款
    private Double backstageDeductOilCard;
		public String getBillNo() {
			return billNo;
		}
		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}
		public String getVenderName() {
			return venderName;
		}
		public void setVenderName(String venderName) {
			this.venderName = venderName;
		}
		public String getReceiveName() {
			return receiveName;
		}
		public void setReceiveName(String receiveName) {
			this.receiveName = receiveName;
		}
		public String getReceiveCellphone() {
			return receiveCellphone;
		}
		public void setReceiveCellphone(String receiveCellphone) {
			this.receiveCellphone = receiveCellphone;
		}
		public String getDriverName() {
			return driverName;
		}
		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}
		public String getVehicleNo() {
			return vehicleNo;
		}
		public void setVehicleNo(String vehicleNo) {
			this.vehicleNo = vehicleNo;
		}
		public String getDriverCellphone() {
			return driverCellphone;
		}
		public void setDriverCellphone(String driverCellphone) {
			this.driverCellphone = driverCellphone;
		}
		public String getCargoName() {
			return cargoName;
		}
		public void setCargoName(String cargoName) {
			this.cargoName = cargoName;
		}
		public Double getPickupweight() {
			return pickupweight;
		}
		public void setPickupweight(Double pickupweight) {
			this.pickupweight = pickupweight;
		}
		public Double getTrueweight() {
			return trueweight;
		}
		public void setTrueweight(Double trueweight) {
			this.trueweight = trueweight;
		}
		public String getPayMent() {
			return payMent;
		}
		public void setPayMent(String payMent) {
			this.payMent = payMent;
		}
		public String getLinkman() {
			return linkman;
		}
		public void setLinkman(String linkman) {
			this.linkman = linkman;
		}
		public String getLinknumber() {
			return linknumber;
		}
		public void setLinknumber(String linknumber) {
			this.linknumber = linknumber;
		}
		public Double getBillPrice() {
			return billPrice;
		}
		public void setBillPrice(Double billPrice) {
			this.billPrice = billPrice;
		}
		public Double getBackstageBillTotalPrice() {
			return backstageBillTotalPrice;
		}
		public void setBackstageBillTotalPrice(Double backstageBillTotalPrice) {
			this.backstageBillTotalPrice = backstageBillTotalPrice;
		}
		public String getDateAcctept() {
			return dateAcctept;
		}
		public void setDateAcctept(String dateAcctept) {
			this.dateAcctept = dateAcctept;
		}
		public String getDateUnloading() {
			return dateUnloading;
		}
		public void setDateUnloading(String dateUnloading) {
			this.dateUnloading = dateUnloading;
		}
		public String getSposition() {
			return sposition;
		}
		public void setSposition(String sposition) {
			this.sposition = sposition;
		}
		public String getEposition() {
			return eposition;
		}
		public void setEposition(String eposition) {
			this.eposition = eposition;
		}
		public String getDriverIDCardNO() {
			return driverIDCardNO;
		}
		public void setDriverIDCardNO(String driverIDCardNO) {
			this.driverIDCardNO = driverIDCardNO;
		}
		public String getMotor() {
			return motor;
		}
		public void setMotor(String motor) {
			this.motor = motor;
		}
		public String getMeasure() {
			return measure;
		}
		public void setMeasure(String measure) {
			this.measure = measure;
		}
		public String getSpecifications() {
			return specifications;
		}
		public void setSpecifications(String specifications) {
			this.specifications = specifications;
		}
		public String getInsurance() {
			return insurance;
		}
		public void setInsurance(String insurance) {
			this.insurance = insurance;
		}
		public String getCargoValue() {
			return cargoValue;
		}
		public void setCargoValue(String cargoValue) {
			this.cargoValue = cargoValue;
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
		public String getTimeAcctept() {
			return timeAcctept;
		}
		public void setTimeAcctept(String timeAcctept) {
			this.timeAcctept = timeAcctept;
		}
		public String getTimeUnloading() {
			return timeUnloading;
		}
		public void setTimeUnloading(String timeUnloading) {
			this.timeUnloading = timeUnloading;
		}
		public String getTimeBIllAcctept() {
			return timeBIllAcctept;
		}
		public void setTimeBIllAcctept(String timeBIllAcctept) {
			this.timeBIllAcctept = timeBIllAcctept;
		}
		
}
