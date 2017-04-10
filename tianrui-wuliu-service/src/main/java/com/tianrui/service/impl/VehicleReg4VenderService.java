package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IVehicleReg4VenderService;
import com.tianrui.api.req.front.vehicle.VechicleRegVenderQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVenderSaveReq;
import com.tianrui.api.resp.front.vehicle.VehicleRegVehiceListResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
@Service
public class VehicleReg4VenderService implements IVehicleReg4VenderService{

	@Override
	public Result searchVehicle(VechicleRegVenderQueryReq req) {
		Result rs =Result.getSuccessResult();
		PaginationVO<VehicleRegVehiceListResp> page= new PaginationVO<VehicleRegVehiceListResp>();
		page.setTotal(1);
		page.setPageNo(1);
		List<VehicleRegVehiceListResp> list =new ArrayList<VehicleRegVehiceListResp>();
		
		
		
		VehicleRegVehiceListResp resp =new VehicleRegVehiceListResp();
		resp.setVehicleNo("晋M54254");
		resp.setVehicleType("半挂车");
		resp.setVehicleLen("12");
		resp.setVehicleLoad("30");
		resp.setVehicleOwner("思敏高");
		resp.setVehiclePhone("1389654551");
		
		resp.setDriverName("张三");
		resp.setDriverTelphone("1389654551");
		
		list.add(resp);
		page.setList(list);
		return rs;
	}

	@Override
	public Result saveVehicle(VechicleRegVenderSaveReq req) {
		Result rs =Result.getSuccessResult();
		return rs;
	}

	@Override
	public Result vehiclePage(VechicleRegVenderQueryReq req) {
		Result rs =Result.getSuccessResult();
		PaginationVO<VehicleRegVehiceListResp> page= new PaginationVO<VehicleRegVehiceListResp>();
		page.setTotal(1);
		page.setPageNo(1);
		List<VehicleRegVehiceListResp> list =new ArrayList<VehicleRegVehiceListResp>();
		
		
		
		VehicleRegVehiceListResp resp =new VehicleRegVehiceListResp();
		resp.setVehicleNo("晋M54254");
		resp.setVehicleType("半挂车");
		resp.setVehicleLen("12");
		resp.setVehicleLoad("30");
		resp.setVehicleOwner("思敏高");
		resp.setVehiclePhone("1389654551");
		
		resp.setDriverName("张三");
		resp.setDriverTelphone("1389654551");
		
		resp.setVehicleSource("原有运力");
		resp.setVehickeStatus("空闲");
		
		list.add(resp);
		page.setList(list);
		return rs;
	}

	

	
}
