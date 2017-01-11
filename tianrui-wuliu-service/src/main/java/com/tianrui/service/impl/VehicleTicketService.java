package com.tianrui.service.impl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IVehicleTicketService;
import com.tianrui.api.req.front.vehicle.VehicleTicketReq;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.VehicleTicket;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.VehicleTicketMapper;
/**
 * 开票认证
 * @author jh
 *
 */
@Service
public class VehicleTicketService implements IVehicleTicketService{

	@Autowired
	VehicleTicketMapper vehicleTicketMapper;
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	
	@Override
	public Result insert(VehicleTicketReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(req.getVehicleid());
		//验证车辆id
		if(vehicle == null){
			rs.setErrorCode(ErrorCode.VEHICLE_TICKET_IDNULL);
		}else{
			VehicleTicket query = new VehicleTicket();
			query.setVehicleid(req.getVehicleid());
			List<VehicleTicket> list = vehicleTicketMapper.selectByCondition(query);
			if(list.size()!=0){
				rs.setErrorCode(ErrorCode.VEHICLE_TICKET_NOTONLY);
			}else{
				VehicleTicket record = new VehicleTicket();
				PropertyUtils.copyProperties(record, req);
				record.setId(UUIDUtil.getId());
				record.setAnlian("0");
				record.setStatus("2");
				vehicleTicketMapper.insertSelective(record);
			}
		}
		return rs;
	}

}
