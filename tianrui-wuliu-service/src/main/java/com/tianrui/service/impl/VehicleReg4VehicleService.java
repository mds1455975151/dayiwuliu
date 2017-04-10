package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IVehicleReg4VehicleService;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverSaveReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleAuthReq;
import com.tianrui.api.resp.front.vehicle.VehicleRegDriverListResp;
import com.tianrui.api.resp.front.vehicle.VehicleRegVehicleDetailResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
@Service
public class VehicleReg4VehicleService implements IVehicleReg4VehicleService{

	@Override
	public Result driverPage(VechicleRegDriverQueryReq req) {
		Result rs =Result.getSuccessResult();
		PaginationVO<VehicleRegDriverListResp> page= new PaginationVO<VehicleRegDriverListResp>();
		page.setTotal(1);
		page.setPageNo(1);
		List<VehicleRegDriverListResp> list =new ArrayList<VehicleRegDriverListResp>();
		
		
		
		VehicleRegDriverListResp resp =new VehicleRegDriverListResp();
		resp.setName("王紫");
		resp.setIdcard("410483199912091245");
		resp.setCheckStatus("1");
		resp.setAuthSatus("1");
		resp.setTelphone("1380000000");
		resp.setId("111");
		
		list.add(resp);
		page.setList(list);
		return rs;
	}

	@Override
	public Result driverSave(VechicleRegDriverSaveReq req) {
		Result rs =Result.getSuccessResult();
		return rs;
	}

	@Override
	public Result driverDel(VechicleRegDriverQueryReq req) {
		Result rs =Result.getSuccessResult();
		return rs;
	}

	@Override
	public Result driverCheck(VechicleRegDriverQueryReq req) {
		Result rs =Result.getSuccessResult();
		return rs;
	}

	@Override
	public Result vehicleDetil(VechicleRegDriverQueryReq req) {
		Result rs =Result.getSuccessResult();
		VehicleRegVehicleDetailResp resp=new VehicleRegVehicleDetailResp();
		resp.setVehicleNo("晋M54254");
		resp.setVehicleMobile("1380000001");
		resp.setAuthTime("2017-4-10 16:54:59");
		resp.setVehicleType("半挂车");
		resp.setVehicleLen("12");
		resp.setVehicleLoad("30");
		resp.setVehicleOwner("思敏高");
		resp.setVehicleOwnerTel("13800000000");
		resp.setAuthstatus("完全认证");
		
		resp.setTaxiLicenseImg("1234561");
		resp.setRoadTransportNo("1234562");
		resp.setTaxiLicenseImg("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
		
		resp.setVehicleImg("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
		
		resp.setDrivingLicenseNo("1234563");
		resp.setDrivingLicenseImg("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
		
		resp.setVehicleGradeNo("1234564");
		resp.setVehicleGradeImg("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
		
		
		
		rs.setData(resp);
		return rs;
	}

	@Override
	public Result vehicleAuth(VechicleRegVehicleAuthReq req) {
		Result rs =Result.getSuccessResult();
		return rs;
	}

	@Override
	public Result vehicleAuthTicket(VechicleRegVehicleAuthReq req) {
		Result rs =Result.getSuccessResult();
		return rs;
	}

	
}
