package com.tianrui.web.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("/test")
public class TestAction {

	
	public static Logger loger=org.slf4j.LoggerFactory.getLogger(TestAction.class);
	
	
	@ResponseBody
	@RequestMapping("query")
	public Result getTicket(String code){
		Result rs =Result.getErrorResult();
		if( StringUtils.isNotBlank(code) ){
			rs=Result.getSuccessResult();
			TestBill bill =new TestBill();
			bill.setCode(System.currentTimeMillis()+"");
			bill.setId(code);
			rs.setData(bill);
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
}


class TestBill{
	private String id;
	private String  code="1";
	private String  vehicleNo="2";
	private String  cargoName="3";
	private String  shdw="4";
	private String  suportRemark="5";
	private String  yuting="6";
	private String  icCard="7";
	private String  icCode="8";
	private String  sequenceNo="9";
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getShdw() {
		return shdw;
	}
	public void setShdw(String shdw) {
		this.shdw = shdw;
	}
	public String getSuportRemark() {
		return suportRemark;
	}
	public void setSuportRemark(String suportRemark) {
		this.suportRemark = suportRemark;
	}
	public String getYuting() {
		return yuting;
	}
	public void setYuting(String yuting) {
		this.yuting = yuting;
	}
	public String getIcCard() {
		return icCard;
	}
	public void setIcCard(String icCard) {
		this.icCard = icCard;
	}
	public String getIcCode() {
		return icCode;
	}
	public void setIcCode(String icCode) {
		this.icCode = icCode;
	}
	public String getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}