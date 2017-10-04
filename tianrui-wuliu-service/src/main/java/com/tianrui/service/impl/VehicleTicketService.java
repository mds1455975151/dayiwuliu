package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.api.intf.IVehicleTicketService;
import com.tianrui.api.req.admin.anlian.AnlianTruckReq;
import com.tianrui.api.req.front.vehicle.TicketFindReq;
import com.tianrui.api.req.front.vehicle.VehicleTicketReq;
import com.tianrui.api.resp.front.vehicle.VehicleTicketResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.bean.VehicleTicket;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;
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
	@Autowired
	IAnlianService anlianService;
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	@Autowired
	ICrossVehicleService crossVehicleService;
	
	@Override
	public Result insert(VehicleTicketReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(req.getVehicleid());
		//验证车辆id
		if(vehicle == null){
			rs.setErrorCode(ErrorCode.VEHICLE_VEHICLE_IDNULL);
		}else{
			VehicleTicket query = new VehicleTicket();
			query.setVehicleid(req.getVehicleid());
			List<VehicleTicket> list = vehicleTicketMapper.selectByCondition(query);
			if(list.size()!=0){
				rs.setErrorCode(ErrorCode.VEHICLE_TICKET_NOTONLY);
			}else{
				//开票认证初始状态 安联认证未认证 开票认证认证中
				VehicleTicket record = new VehicleTicket();
				PropertyUtils.copyProperties(record, req);
				record.setId(UUIDUtil.getId());
				record.setAnlian("0");
				record.setStatus("2");
				record.setDesc1(vehicle.getVehicleprefix()+vehicle.getVehicleno());
				record.setCreatertime(System.currentTimeMillis());
				record.setOwner(vehicle.getVehiOwnerName());
				vehicleTicketMapper.insertSelective(record);
				
				//设置车辆开票认证状态为 认证中
				MemberVehicle mv = new MemberVehicle();
				mv.setId(vehicle.getId());
				mv.setDesc1("2");
				memberVehicleMapper.updateByPrimaryKeySelective(mv);
			}
		}
		return rs;
	}
	
	@Override
	public PaginationVO<VehicleTicketResp> page(TicketFindReq req) throws Exception {
		PaginationVO<VehicleTicketResp> pg = new PaginationVO<VehicleTicketResp>();

		VehicleTicket vt = new VehicleTicket();
		vt.setDesc1(req.getVehicleNo());
		vt.setStatus(req.getStatus());
		vt.setOwner(req.getOwner());
		if(req.getPageNo()!=null){
			vt.setPageNo(req.getPageNo()*req.getPageSize());
			vt.setPageSize(req.getPageSize());
			pg.setPageNo(req.getPageNo());
			pg.setPageSize(req.getPageSize());
		}
		long a = vehicleTicketMapper.selectByCount(vt);
		List<VehicleTicket> list = vehicleTicketMapper.selectByCondition(vt);
		pg.setTotal(a);
		pg.setList(copy(list));
		return pg;
	}
	
	public List<VehicleTicketResp> copy(List<VehicleTicket> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<VehicleTicketResp> resp = new ArrayList<VehicleTicketResp>();
		for(VehicleTicket vt : list){
			VehicleTicketResp t = new VehicleTicketResp();
			PropertyUtils.copyProperties(t, vt);
			resp.add(t);
		}
		return resp;
	}
	
	@Override
	public Result save(VehicleTicketReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(req.getVehicleid());
		VehicleDriver vd = new VehicleDriver();
		vd.setVehicleid(req.getVehicleid());
		List<VehicleDriver> li = vehicleDriverMapper.selectMyVehiDriverByCondition(vd);
		//5 空闲车辆
		if(!"5".equals(vehicle.getBillstatus())){
			rs.setErrorCode(ErrorCode.VEHICLE_DRIVER_NOTONLY);
			return rs;
		}else{
			for(VehicleDriver cd: li){
				vehicleDriverMapper.deleteByPrimaryKey(cd.getId());
			}
		}
		//验证车辆id
		if(vehicle == null){
			rs.setErrorCode(ErrorCode.VEHICLE_VEHICLE_IDNULL);
		}else{
			VehicleTicket query = new VehicleTicket();
			query.setVehicleid(req.getVehicleid());
			List<VehicleTicket> list = vehicleTicketMapper.selectByCondition(query);
			if(list.size()!=0){
				rs.setErrorCode(ErrorCode.VEHICLE_TICKET_NOTONLY);
			}else{
				//TODO
				rs = anlianService.adminTruck(req);
				if(rs.getCode().equals("000000")){
					//开票认证初始状态 安联认证成功 开票认证成功
					VehicleTicket record = new VehicleTicket();
					PropertyUtils.copyProperties(record, req);
					record.setId(UUIDUtil.getId());
					record.setAnlian("0");
					record.setStatus("1");
					record.setDesc1(vehicle.getVehicleprefix()+vehicle.getVehicleno());
					record.setCreatertime(System.currentTimeMillis());
					vehicleTicketMapper.insertSelective(record);
					
					MemberVehicle mv = new MemberVehicle();
					mv.setId(vehicle.getId());
					mv.setDesc1("1");
					memberVehicleMapper.updateByPrimaryKeySelective(mv);
				}
			}
		}
		return rs;
	}


	@Override
	public Result shenhe(VehicleTicketReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		VehicleTicket ticket = vehicleTicketMapper.selectByPrimaryKey(req.getId());
		if(ticket == null){
			rs.setErrorCode(ErrorCode.VEHICLE_TICKET_IDNULL);
		}else{
			//认证成功  推送安联
			if(req.getStatus().equals("1")){
				AnlianTruckReq alreq = new AnlianTruckReq();
				alreq.setVehicleid(ticket.getVehicleid());
				rs = anlianService.truck(alreq);
				if(!rs.getCode().equals("000000")){
					return rs;
				}else{
					try {
						crossVehicleService.systemInsertVehicle(req.getVehicleid());
					} catch (Exception e) {
						
					}
				}
			}
			VehicleTicket vt = new VehicleTicket();
			vt.setId(req.getId());
			vt.setStatus(req.getStatus());
			vt.setDesc4(req.getDesc4());
			vehicleTicketMapper.updateByPrimaryKeySelective(vt);
			
			MemberVehicle mv = new MemberVehicle();
			mv.setId(ticket.getVehicleid());
			mv.setDesc1(req.getStatus());
			memberVehicleMapper.updateByPrimaryKeySelective(mv);
			
		}
		return rs;
	}

	@Override
	public Result findByVehicleId(TicketFindReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		VehicleTicket vt = new VehicleTicket();
		vt.setVehicleid(req.getId());
		List<VehicleTicket> list = vehicleTicketMapper.selectByCondition(vt);
		if(list.size()==1){
			VehicleTicketResp resp = new VehicleTicketResp();
			PropertyUtils.copyProperties(resp, list.get(0));
			rs.setData(resp);
		}
		return rs;
	}

	@Override
	public Result upt(VehicleTicketReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		
		MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(req.getVehicleid());
		//验证车辆id
		//车辆开票认证 认证中
		VehicleTicket tick = vehicleTicketMapper.selectByPrimaryKey(req.getId());
		
		if(vehicle == null){
			rs.setErrorCode(ErrorCode.VEHICLE_VEHICLE_IDNULL);
		}else if(tick==null){
			rs.setErrorCode(ErrorCode.VEHICLE_TICKET_IDNULL);
		}else{
			VehicleTicket vt = new VehicleTicket();
			PropertyUtils.copyProperties(vt, req);
			vt.setStatus("2");
			vt.setDesc1(vehicle.getVehicleprefix()+vehicle.getVehicleno());
			vehicleTicketMapper.updateByPrimaryKeySelective(vt);
			//设置车辆开票认证状态为 认证中
			MemberVehicle mv = new MemberVehicle();
			mv.setId(req.getVehicleid());
			mv.setDesc1("2");
			memberVehicleMapper.updateByPrimaryKeySelective(mv);
		}
		return rs;
	}

	@Override
	public VehicleTicketResp findById(String id) throws Exception {
		// TODO Auto-generated method stub
		VehicleTicketResp resp = new VehicleTicketResp();
		VehicleTicket tick = vehicleTicketMapper.selectByPrimaryKey(id);
		PropertyUtils.copyProperties(resp, tick);
		return resp;
	}

	@Override
	public Result ticketSuccess(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		VehicleTicket ticket = vehicleTicketMapper.selectByPrimaryKey(id);
		if(ticket == null){
			rs.setErrorCode(ErrorCode.VEHICLE_TICKET_IDNULL);
		}else{
			VehicleTicket vt = new VehicleTicket();
			vt.setId(ticket.getId());
			vt.setStatus("1");
			vehicleTicketMapper.updateByPrimaryKeySelective(vt);
			
			MemberVehicle mv = new MemberVehicle();
			mv.setId(ticket.getVehicleid());
			mv.setDesc1("1");
			memberVehicleMapper.updateByPrimaryKeySelective(mv);
		}
		return rs;
	}

}
