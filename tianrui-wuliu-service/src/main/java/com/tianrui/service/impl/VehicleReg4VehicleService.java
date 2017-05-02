package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IVehicleReg4VehicleService;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverSaveReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleAuthReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleTicketAuthReq;
import com.tianrui.api.resp.front.vehicle.VehicleRegDriverListResp;
import com.tianrui.api.resp.front.vehicle.VehicleRegVehicleDetailResp;
import com.tianrui.api.resp.pay.PayInvoiceResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.PayInvoice;
import com.tianrui.service.bean.vehiclereg.VehicleDriverNew;
import com.tianrui.service.mapper.FileVehicleNewMapper;
import com.tianrui.service.mapper.FileVehicleRecordNewMapper;
import com.tianrui.service.mapper.VehicleDriverNewMapper;
@Service
public class VehicleReg4VehicleService implements IVehicleReg4VehicleService{
	@Autowired
	VehicleDriverNewMapper vehicleDriverNewMapper;
	@Autowired
	FileVehicleRecordNewMapper fileVehicleRecordNewMapper;
	@Autowired
	FileVehicleNewMapper  fileVehicleNewMapper;
	

	@Override
	public Result driverPage(VechicleRegDriverQueryReq req) {
		Result rs =Result.getErrorResult();
		PaginationVO<VehicleRegDriverListResp> page =null;
		if( req !=null && StringUtils.isNotBlank(req.getCurrUId())  ){
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			page = new PaginationVO<VehicleRegDriverListResp> ();
			//TODO query
			VehicleDriverNew query = new VehicleDriverNew();
			long total =vehicleDriverNewMapper.countByCondition(query);
			if(total >0 ){
				query.setStart((req.getPageNo()-1)*req.getPageSize());
				query.setLimit(req.getPageSize());
				List<VehicleDriverNew> list =vehicleDriverNewMapper.selectByCondition(query);
				//page.setList(convert2DriverRespList(list));
			}
			page.setTotal(total);
			page.setPageNo(req.getPageNo());
			rs.setData(page);
		}
		return rs;
		
//		VehicleRegDriverListResp resp =new VehicleRegDriverListResp();
//		resp.setName("王紫");
//		resp.setIdcard("410483199912091245");
//		resp.setCheckStatus("1");
//		resp.setAuthSatus("1");
//		resp.setTelphone("1380000000");
//		resp.setId("111");
	}

	@Override
	public Result driverSave(VechicleRegDriverSaveReq req) {
		Result rs =Result.getSuccessResult();
		
		//VehicleDriverNew bean = 
				
				
		//vehicleDriverNewMapper.insert(req);
		return rs; 
	}

	@Override
	public Result driverDel(VechicleRegDriverQueryReq req) {
		Result rs =Result.getSuccessResult();
		
		//vehicleDriverNewMapper.deleteByPrimaryKey(id);
		return rs;
	}

	@Override
	public Result driverCheck(VechicleRegDriverQueryReq req) {
		Result rs =Result.getSuccessResult();
		
		//vehicleDriverNewMapper.
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
		//fileVehicleRecordNewMapper.get
		return rs;
	}

	@Override
	public Result vehicleAuthTicket(VechicleRegVehicleTicketAuthReq req) {
		Result rs =Result.getSuccessResult();
		return rs;
	}
	//输出参数类型转换
	private VehicleRegDriverListResp convert2DriverResp(VehicleDriverNew bean){
		VehicleRegDriverListResp resp =null;
		if( bean!=null ){
			
		}
		return resp;
	}
	//输出参数类型转换
	private List<VehicleRegDriverListResp> convert2DriverRespList(List<VehicleDriverNew> list){
		List<VehicleRegDriverListResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs=new ArrayList<VehicleRegDriverListResp>();
			for(VehicleDriverNew item:list){
				if(convert2DriverResp(item) !=null){
					rs.add(convert2DriverResp(item));
				}
			}
		}
		return rs;
	}
	
}
