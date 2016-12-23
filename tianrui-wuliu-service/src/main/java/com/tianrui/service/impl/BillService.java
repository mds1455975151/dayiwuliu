package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.intf.IFileService;
import com.tianrui.api.intf.IMemberVoService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.front.adminReport.StatReportReq;
import com.tianrui.api.req.front.bill.WaybillConfirmReq;
import com.tianrui.api.req.front.bill.WaybillEditReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.req.front.bill.WaybillSaveReq;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.front.position.PositionQueryReq;
import com.tianrui.api.req.front.system.FileUploadReq;
import com.tianrui.api.req.front.vehicle.MemberVehicleReq;
import com.tianrui.api.req.front.vehicle.VehicleDriverReq;
import com.tianrui.api.resp.front.adminReport.StatReportOfBillResp;
import com.tianrui.api.resp.front.bill.BillGpsResp;
import com.tianrui.api.resp.front.bill.BillPlanResp;
import com.tianrui.api.resp.front.bill.BillPositionResp;
import com.tianrui.api.resp.front.bill.BillTrackResp;
import com.tianrui.api.resp.front.bill.BillVehicleResp;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.api.resp.front.position.PositionResp;
import com.tianrui.api.resp.front.vehicle.MemberVehicleResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.BillStatusEnum;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.enums.PlanStatusEnum;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.ReportVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.bean.FilePositoin;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.bean.Merchant;
import com.tianrui.service.admin.impl.FreightInfoService;
import com.tianrui.service.admin.impl.OrganizationService;
import com.tianrui.service.admin.mapper.FileFreightMapper;
import com.tianrui.service.admin.mapper.FilePositoinMapper;
import com.tianrui.service.admin.mapper.FileRouteMapper;
import com.tianrui.service.admin.mapper.MerchantMapper;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.BillPosition;
import com.tianrui.service.bean.BillTrack;
import com.tianrui.service.bean.MemberCapa;
import com.tianrui.service.bean.MemberCapaList;
import com.tianrui.service.bean.MemberPositionRecord;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.MemberCapaMapper;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.OwnerDriverMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;
import com.tianrui.service.mongo.BillPositionDao;
import com.tianrui.service.mongo.BillTrackDao;
import com.tianrui.service.mongo.CodeGenDao;
import com.tianrui.service.mongo.MemberPositionRecordDao;
import com.tianrui.service.vo.BIllTrackMsg;
import com.tianrui.service.vo.VehicleDriverVO;


@Service
public class BillService implements IBillService{
	
	
	private static Logger loger =LoggerFactory.getLogger(BillService.class);
	
	@Autowired
	BillMapper billMapper;
	@Autowired
	BillTrackDao billTrackDao;
	@Autowired
	PlanMapper planMapper;
	@Autowired
	FilePositoinMapper positionMapper;
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	@Autowired
	MessageService messageService;
	@Autowired
	CodeGenDao codeGenDao;
	@Autowired
	IMemberVoService MemberVoService;
	@Autowired
	OrganizationService orgService;
	@Autowired
	FileRouteMapper routeMapper;
	@Autowired
	FilePositoinMapper posotionMapper;
	@Autowired
	IVehicleDriverService vehicleDriverService;
	@Autowired
	MemberVehicleService memberVehicleService;
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	@Autowired
	MemberPositionService memberPositionService;
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	protected ICargoPlanService cargoPlanService;
	@Autowired
	FileFreightMapper fileFreightMapper;
	@Autowired
	OwnerDriverMapper ownerDriverMapper;
	@Autowired
	SystemMemberInfoMapper systemMemberInfoMapper;
	@Autowired
	MemberCapaMapper memberCapaMapper;
	@Autowired
	private FreightInfoService freightInfoService;
	@Autowired
	private IFileService iFileService;
	@Autowired
	private MerchantMapper merchantMapper;
	@Autowired
	private BillPositionDao billPositionDao;
	@Autowired
	private MemberPositionRecordDao memberPositionRecordDao;
	
	@Override
	public Result saveWayBill(WaybillSaveReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		List<Bill> bills =null;
		if( req !=null && StringUtils.isNotBlank(req.getPlanId()) ){
			//获取车辆驾驶员信息
			List<VehicleDriverVO> vehicleDrivers =getVehicleDriver(req.getVehicleDriverIds());
			if(vehicleDrivers.size()==0){
				rs.setCode("1");
				rs.setError("上传数据有误");
				return rs;
			}
			if( CollectionUtils.isNotEmpty(vehicleDrivers) ){
				bills =new ArrayList<Bill>();
				Plan plan =planMapper.selectByPrimaryKey(req.getPlanId());
				if( plan !=null && StringUtils.isNotBlank(plan.getRouteid())){
					if(plan.getStatus() == PlanStatusEnum.COMPLETE.getStatus()){
						rs.setCode("1");
						rs.setError("计划已经完成，不能生成运单了！");
						return rs;
					}
					Plan planRoot = null;
					if(StringUtils.equals(plan.getIsAppoint(), "1")){
						planRoot = planMapper.selectRootPlanByPlanId(plan.getId());
					}
					for(  VehicleDriverVO item:vehicleDrivers){
						Bill bill =new Bill();
						//基本信息
						bill.setId(UUIDUtil.getId());
						bill.setPlanid(req.getPlanId());
						bill.setPlancode(plan.getPlancode());
						bill.setOrgid(plan.getOrgid());
						bill.setRouteid(plan.getRouteid());
						//创建人 修改人
						bill.setCreatetime(System.currentTimeMillis());
						bill.setCreator(req.getCurruId());
						bill.setModifier(req.getCurruId());
						bill.setModifytime(System.currentTimeMillis());
						//状态标记信息
						bill.setVenderdelflag(Byte.valueOf("0"));
						bill.setOwnerdelflag(Byte.valueOf("0"));
						bill.setDriverdelflag(Byte.valueOf("0"));
						bill.setStatus(Byte.valueOf("0"));
						if(Integer.parseInt(item.getOvernumber()) > 1){//批量运单
							//计划编码
							bill.setWaybillno("批量运单");
							bill.setType(Byte.valueOf("2"));
						}else{
							//计划编码
							bill.setWaybillno(codeGenDao.codeGen(2));
							bill.setType(Byte.valueOf("0"));
						}
						//车主 货主信息
						if(StringUtils.equals(plan.getIsAppoint(), "1")){
							bill.setOwnerid(planRoot.getCreator());
						}else{
							bill.setOwnerid(plan.getCreator());
						}
						bill.setVenderid(plan.getVehicleownerid());
						//车辆司机信息
						bill.setVehicleid(item.getVehicleId());
						bill.setDriverid(item.getDriverId());
						bill.setVehicleno(item.getVehicleno());
						bill.setVehicletypename(item.getVehicleTypeName());
						bill.setDrivername(item.getDriverName());
						bill.setDrivertel(item.getDriverTel());
						bill.setOvernumber(item.getOvernumber());
						bill.setTotalnumber(item.getOvernumber());
						//货物信息
						bill.setCargoname(plan.getCargoname());
						bill.setPriceunits(plan.getPriceunits());
						bill.setWeight(Double.valueOf(req.getWeight()));
						bill.setPrice(Double.valueOf(req.getPrice()));
						bill.setDesc1(plan.getMeasure());
						//公里数
						bill.setDistance(plan.getDistance());
						//起止时间
						bill.setStarttime(req.getBillStartTime());
						bill.setEndtime(req.getBillEndTime());
						//收发货人信息
						//发货地 收获地
						bill.setStartcity(plan.getStartcity());
						bill.setEndcity(plan.getEndcity());
						//发货人
						bill.setConsignorname(plan.getSendperson());
						bill.setConsignortel(plan.getSendpersonphone());
						//收货人
						bill.setReceivername(plan.getReceiveperson());
						bill.setReceivertel(plan.getReceivepersonphone());
						//是否由委派计划生成的运单
						bill.setDesc4(plan.getIsAppoint());
						bill.setPathID(plan.getPathID());
						bill.setIsClearing("0");
						bills.add(bill);
					}
				}
			}
			
		}
		
		//保存
		if( CollectionUtils.isNotEmpty(bills) ){
			MemberVo currUser =getMember(req.getCurruId());
			for( Bill item:bills ){
				billMapper.insert(item);
				saveBillTrack(item.getId(),1,BIllTrackMsg.INIT,req.getCurruId(),item.getStatus());
				//为司机发送站内信
				MemberVo receive =getMember(item.getDriverid());
				sendMsgInside(Arrays.asList(new String[]{item.getWaybillno(),(currUser.getRealName())}), item.getId(), currUser, receive, MessageCodeEnum.BILL_2DRIVER_CREATE, "driver");
			}
		}
		return rs;
	}

	@Override
	public Result editWayBill(WaybillEditReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				if( checkBillauthForCuser(db,req.getCurruId(),"creator")){
					if( checkBillauthForstatus(db,"edit") ){
						Plan plan =planMapper.selectByPrimaryKey(db.getPlanid());
						if(plan.getStatus() == PlanStatusEnum.COMPLETE.getStatus()){
							Bill b =new Bill();
							b.setId(req.getId());
							b.setDriverdelflag((byte)1);
							billMapper.updateByPrimaryKeySelective(b);
							rs.setCode("1");
							rs.setError("计划已经完成，不能生成运单了！");
							return rs;
						}
						Bill update =new Bill();
						update.setId(req.getId());
						
						update.setStarttime(req.getBillStartTime());
						update.setEndtime(req.getBillEndTime());
						update.setWeight(Double.valueOf(req.getWeight()));
						update.setPrice(Double.valueOf(req.getPrice()));
						update.setOvernumber(req.getOvernumber());
						update.setTotalnumber(req.getOvernumber());
						if(StringUtils.equals(req.getOvernumber(), "1")){
							update.setWaybillno(codeGenDao.codeGen(2));
							update.setType(Byte.valueOf("0"));
						}else if(Integer.parseInt(req.getOvernumber())>1){
							update.setWaybillno("批量运单");
							update.setType(Byte.valueOf("2"));
						}

						update.setModifier(req.getCurruId());
						update.setModifytime(System.currentTimeMillis());
						
						//重新初始化状态
						update.setStatus((byte)BillStatusEnum.INIT.getStatus());
						
						update.setVenderdelflag(Byte.valueOf("0"));
						update.setOwnerdelflag(Byte.valueOf("0"));
						update.setDriverdelflag(Byte.valueOf("0"));
						//初始化原因
						update.setDesc3("");
						update.setDesc2("");
						//车辆信息
						VehicleDriver vehicleDriver =vehicleDriverMapper.selectByPrimaryKey(req.getVehicleId());
						
						update.setVehicleid(vehicleDriver.getVehicleid());
						update.setDriverid(vehicleDriver.getDriverid());
						update.setVehicleno(vehicleDriver.getVehicleno());
						update.setVehicletypename(vehicleDriver.getVehicletypename());
						update.setDrivername(vehicleDriver.getDrivername());
						update.setDrivertel(vehicleDriver.getDrivertel());
						
						billMapper.updateByPrimaryKeySelective(update);
						saveBillTrack(db.getId(),1,BIllTrackMsg.STEP1,req.getCurruId(),db.getStatus());
						//为司机发送站内信
						MemberVo currUser =getMember(req.getCurruId());
						MemberVo receive =getMember(db.getDriverid());
						sendMsgInside(Arrays.asList(new String[]{db.getWaybillno(),currUser.getRealName()}), db.getId(), currUser, receive, MessageCodeEnum.BILL_2DRIVER_UPDATE, "driver");
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public Result verderdelete(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				if( checkBillauthForCuser(db,req.getCurruId(),"vender")){
					if( checkBillauthForstatus(db,"del") ){
						Bill update =new Bill();
						update.setId(req.getId());
						
						update.setVenderdelflag(Byte.valueOf("1"));
						
						update.setModifier(req.getCurruId());
						update.setModifytime(System.currentTimeMillis());
						billMapper.updateByPrimaryKeySelective(update);
						saveBillTrack(db.getId(),0,BIllTrackMsg.STEP2,req.getCurruId(),db.getStatus());
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public Result cancleConfirm(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				if( checkBillauthForCuser(db,req.getCurruId(),"creator")){
					if( checkBillauthForstatus(db,"cancle") ){
						Bill update =new Bill();
						update.setId(req.getId());
						
						update.setStatus((byte)BillStatusEnum.CANCLE.getStatus());
						
						update.setModifier(req.getCurruId());
						update.setModifytime(System.currentTimeMillis());
						billMapper.updateByPrimaryKeySelective(update);
						saveBillTrack(db.getId(),1,BIllTrackMsg.STEP3,req.getCurruId(),BillStatusEnum.CANCLE.getStatus());
						//为司机发送站内信
						MemberVo currUser =getMember(req.getCurruId());
						MemberVo receive =getMember(db.getDriverid());
						sendMsgInside(Arrays.asList(new String[]{db.getWaybillno(),(currUser.getRealName())}), db.getId(), currUser, receive, MessageCodeEnum.BILL_2DRIVER_CANCLE, "driver");

					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public Result signConfirm(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				Plan plan =planMapper.selectByPrimaryKey(db.getPlanid());
				Plan rootPlan = planMapper.selectRootPlanByPlanId(plan.getId());
				if( checkBillauthForCuser(db,req.getCurruId(),"owner") || (StringUtils.equals(plan.getIsAppoint(), "1") && StringUtils.equals(rootPlan.getCreator(), req.getCurruId()))){
					if( checkBillauthForstatus(db,"sign") ){
						Bill update =new Bill();
						update.setId(req.getId());
						
						update.setStatus((byte)BillStatusEnum.COMPLETE.getStatus());
						update.setTrueweight(Double.valueOf(req.getWeight()));
						update.setModifier(req.getCurruId());
						update.setModifytime(System.currentTimeMillis());
						billMapper.updateByPrimaryKeySelective(update);
						saveBillTrack(db.getId(),1,BIllTrackMsg.STEP4,req.getCurruId(),BillStatusEnum.COMPLETE.getStatus());
						Plan planUpdate =new Plan();
						if(StringUtils.equals(plan.getIsAppoint(), "1")){
							planUpdate.setId(rootPlan.getId());
							if( rootPlan.getCompleted() !=null ){
								planUpdate.setCompleted(rootPlan.getCompleted()+Double.valueOf(req.getWeight()));
							}else{
								planUpdate.setCompleted(Double.valueOf(req.getWeight()));
							}
							//判断总计签收量 是否大于计划总量
							if(planUpdate.getCompleted() != null){
								if(planUpdate.getCompleted() >= rootPlan.getTotalplanned()){
									PlanConfirmReq planReq = new PlanConfirmReq();
									planReq.setId(rootPlan.getId());
									cargoPlanService.completePlan(planReq);
								}
							}
						}else{
							planUpdate.setId(plan.getId());
							if( plan.getCompleted() !=null ){
								planUpdate.setCompleted(plan.getCompleted()+Double.valueOf(req.getWeight()));
							}else{
								planUpdate.setCompleted(Double.valueOf(req.getWeight()));
							}
							//判断总计签收量 是否大于计划总量
							if(planUpdate.getCompleted() != null){
								if(planUpdate.getCompleted() >= plan.getTotalplanned()){
									PlanConfirmReq planReq = new PlanConfirmReq();
									planReq.setId(plan.getId());
									cargoPlanService.completePlan(planReq);
								}
							}
						}
						planMapper.updateByPrimaryKeySelective(planUpdate);
						//为车主发送站内信
						MemberVo currUser =getMember(req.getCurruId());
						MemberVo receive =getMember(db.getVenderid());
						sendMsgInside(Arrays.asList(new String[]{db.getWaybillno(),currUser.getRealName()}), db.getId(), currUser, receive, MessageCodeEnum.BILL_2VENDER_SIGN, "vender");
						
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public Result ownerdelete(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				if( checkBillauthForCuser(db,req.getCurruId(),"owner")){
					if( checkBillauthForstatus(db,"del") ){
						Bill update =new Bill();
						update.setId(req.getId());
						
						update.setOwnerdelflag((byte)1);
						
						update.setModifier(req.getCurruId());
						update.setModifytime(System.currentTimeMillis());
						billMapper.updateByPrimaryKeySelective(update);
						saveBillTrack(db.getId(),0,BIllTrackMsg.STEP5,req.getCurruId(),db.getStatus());
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public Result acceptConfirm(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				MemberVehicleResp vehicle = memberVehicleService.queryMyVehicleInfoById(db.getVehicleid());
				/** 车辆运输状态(2-发货中3-运货中4-卸货中5-空闲中)*/
				if(!"5".equals(vehicle.getBillstatus())){
					rs.setErrorCode(ErrorCode.BILL_VEHICLE_BILLSTATUS);
					return rs;
				}
				if("1".equals(db.getVenderdelflag())){
					rs.setErrorCode(ErrorCode.BILL_VENDER_DEL);
					return rs;
				}
				if("1".equals(db.getOwnerdelflag())){
					rs.setErrorCode(ErrorCode.BILL_DRIVER_DEL);
					return rs;
				}
				if( checkBillauthForCuser(db,req.getCurruId(),"driver")){
					if( checkBillauthForstatus(db,"accept") ){
						Plan plan =planMapper.selectByPrimaryKey(db.getPlanid());
						if(plan.getStatus() != PlanStatusEnum.ACCEPT.getStatus()){
							Bill b =new Bill();
							b.setId(req.getId());
							b.setDriverdelflag((byte)1);
							billMapper.updateByPrimaryKeySelective(b);
							rs.setCode("1");
							rs.setError("计划已经完成，不能生成运单");
							return rs;
						}
						Bill update =new Bill();
						update.setId(req.getId());
						int index = 0;
						if(StringUtils.equals(Constant.BILL_TYPE_0, req.getType())){//普通运单
							update.setModifier(req.getCurruId());
							update.setModifytime(System.currentTimeMillis());
							update.setStatus((byte)BillStatusEnum.ACCEPT.getStatus());
							index = billMapper.updateByPrimaryKeySelective(update);
						}else if(StringUtils.equals(Constant.BILL_TYPE_1, req.getType())){
						}else if(StringUtils.equals(Constant.BILL_TYPE_2, req.getType())){//批量运单
							Bill b = new Bill();
							PropertyUtils.copyProperties(b, db);
							if(Integer.parseInt(db.getOvernumber()) > 1){
								update.setOvernumber((Integer.parseInt(db.getOvernumber())-1)+"");
								billMapper.updateByPrimaryKeySelective(update);
							}else{
								billMapper.deleteByPrimaryKey(db.getId());
							}
							b.setId(UUIDUtil.getId());
							b.setWaybillno(codeGenDao.codeGen(2));
							b.setType(Byte.parseByte(Constant.BILL_TYPE_0));
							b.setOvernumber("1");
							b.setTotalnumber("1");
							b.setModifier(req.getCurruId());
							b.setModifytime(System.currentTimeMillis());
							b.setStatus((byte)BillStatusEnum.ACCEPT.getStatus());
							index = billMapper.insert(b);
						}
						if(index > 0){
							saveBillTrack(db.getId(),1,BIllTrackMsg.STEP6,req.getCurruId(),BillStatusEnum.ACCEPT.getStatus());
							//修改运力车辆 状态信息
							MemberVehicleReq req2 =new MemberVehicleReq();
							req2.setId(db.getVehicleid());
							req2.setBillstatus("2");
							memberVehicleService.updateVehiclebillStatus(req2);
							//为车主发送站内信
							MemberVo currUser =getMember(req.getCurruId());
							MemberVo receive =getMember(db.getVenderid());
							sendMsgInside(Arrays.asList(new String[]{currUser.getRealName(),db.getWaybillno()}), db.getId(), currUser, receive, MessageCodeEnum.BILL_2VENDER_ACCEPT, "vender");
						}else{
							rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
						}
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public Result refuseConfirm(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				if( checkBillauthForCuser(db,req.getCurruId(),"driver")){
					if( checkBillauthForstatus(db,"refuse") ){
						Bill update  =new Bill();
						update.setId(req.getId());
						update.setStatus((byte)BillStatusEnum.REFUSE.getStatus());
						update.setModifier(req.getCurruId());
						update.setModifytime(System.currentTimeMillis());
						//拒绝理由
						update.setDesc3(req.getRefuseType());
						update.setDesc2(req.getRefuseReson());
						billMapper.updateByPrimaryKeySelective(update);
						saveBillTrack(db.getId(),1,BIllTrackMsg.STEP7,req.getCurruId(),BillStatusEnum.REFUSE.getStatus());
						//为车主发送站内信
						MemberVo currUser =getMember(req.getCurruId());
						MemberVo receive =getMember(db.getVenderid());
						sendMsgInside(Arrays.asList(new String[]{currUser.getRealName(),db.getWaybillno()}), db.getId(), currUser, receive, MessageCodeEnum.BILL_2VENDER_REFUSE, "vender");
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public Result departureConfirm(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				if( checkBillauthForCuser(db,req.getCurruId(),"driver")){
					if( checkBillauthForstatus(db,"departure") ){
						Bill update =new Bill();
						update.setId(req.getId());
						
						update.setStatus((byte)BillStatusEnum.TRANSIT.getStatus());
						
						update.setModifier(req.getCurruId());
						update.setModifytime(System.currentTimeMillis());
						billMapper.updateByPrimaryKeySelective(update);
						saveBillTrack(db.getId(),1,BIllTrackMsg.STEP9,req.getCurruId(),BillStatusEnum.TRANSIT.getStatus());
						//为车主发送站内信
						MemberVo currUser =getMember(req.getCurruId());
						MemberVo receive =getMember(db.getVenderid());
						sendMsgInside(Arrays.asList(new String[]{currUser.getRealName(),db.getWaybillno()}), db.getId(), currUser, receive, MessageCodeEnum.BILL_2VENDER_DEPARTURE, "vender");
						//修改运力车辆 状态信息
						MemberVehicleReq req2 =new MemberVehicleReq();
						req2.setId(db.getVehicleid());
						req2.setBillstatus(""+BillStatusEnum.TRANSIT.getStatus());
						memberVehicleService.updateVehiclebillStatus(req2);
						rs.setCode("000000");
						rs.setData("操作成功");
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public Result arrivedConfirm(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				if( checkBillauthForCuser(db,req.getCurruId(),"driver")){
					if( checkBillauthForstatus(db,"arrived") ){
						Bill update =new Bill();
						update.setId(req.getId());
						
						update.setStatus((byte)BillStatusEnum.DISCHARGECARGO.getStatus());
						
						update.setModifier(req.getCurruId());
						update.setModifytime(System.currentTimeMillis());
						billMapper.updateByPrimaryKeySelective(update);
						saveBillTrack(db.getId(),1,BIllTrackMsg.STEP10,req.getCurruId(),BillStatusEnum.DISCHARGECARGO.getStatus());
						//为车主发送站内信
						MemberVo currUser =getMember(req.getCurruId());
						MemberVo receive =getMember(db.getVenderid());
						sendMsgInside(Arrays.asList(new String[]{currUser.getRealName(),db.getWaybillno()}), db.getId(), currUser, receive, MessageCodeEnum.BILL_2VENDER_ARRIVED, "vender");
						//修改运力车辆 状态信息
						MemberVehicleReq req2 =new MemberVehicleReq();
						req2.setId(db.getVehicleid());
						req2.setBillstatus(""+BillStatusEnum.DISCHARGECARGO.getStatus());
						memberVehicleService.updateVehiclebillStatus(req2);
						rs.setCode("000000");
						rs.setData("操作成功");
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public Result dischargeConfirm(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				//保存卸货地位置信息
				saveBillPosition(req);
				
				if( checkBillauthForCuser(db,req.getCurruId(),"driver")){
					if( checkBillauthForstatus(db,"transit") ){
						//移动端图片保存
						if(StringUtils.isNotBlank(req.getImgdata())){
							FileUploadReq uploadreq = new FileUploadReq();
							uploadreq.setuId(req.getCurruId());
							uploadreq.setImgStr(req.getImgdata());
							rs =fileUploadService.uploadImg(uploadreq);
						}else if(req.getFile() != null){
							rs = iFileService.uploadByteImg(req.getFile());
						}else{
							rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
							return rs;
						}
						if(rs!=null &&StringUtils.equals(rs.getCode(), "000000") && StringUtils.isNotBlank(rs.getData().toString())){
							Bill update =new Bill();
							update.setId(req.getId());
							update.setSignweight(req.getPsweight());
							update.setSignimgurl(String.valueOf(rs.getData()));
							update.setStatus((byte)BillStatusEnum.SIGN.getStatus());
							update.setUnloadtime(System.currentTimeMillis());
							update.setModifier(req.getCurruId());
							update.setModifytime(System.currentTimeMillis());
							billMapper.updateByPrimaryKeySelective(update);
							saveBillTrack(db.getId(),1,BIllTrackMsg.STEP11,req.getCurruId(),BillStatusEnum.SIGN.getStatus());
							//修改运力车辆 状态信息
							MemberVehicleReq req2 =new MemberVehicleReq();
							req2.setId(db.getVehicleid());
							req2.setBillstatus(""+BillStatusEnum.SIGN.getStatus());
							memberVehicleService.updateVehiclebillStatus(req2);
							//为车主发送站内信
							MemberVo currUser =getMember(req.getCurruId());
							MemberVo receive =getMember(db.getVenderid());
							sendMsgInside(Arrays.asList(new String[]{currUser.getRealName(),db.getWaybillno()}), db.getId(), currUser, receive, MessageCodeEnum.BILL_2VENDER_DISCHARGE, "vender");
							//为货主发送
							
							Plan plan = planMapper.selectRootPlanByPlanId(db.getPlanid());
							receive=getMember(plan.getCreator());
							sendMsgInside(Arrays.asList(new String[]{currUser.getRealName(),db.getWaybillno()}), db.getId(), currUser, receive, MessageCodeEnum.BILL_2OWNER_DISCHARGE, "owner");
							rs.setCode("000000");
							rs.setData("操作成功");
						}else{
							//磅单图片上传失败
							rs.setErrorCode(ErrorCode.BILL_STATUS_IMG_UPLOAD);
						}
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	public void saveBillPosition(WaybillConfirmReq req){
		if(req.getLat()!=null&&req.getLon()!=null){
			BillPosition t = new BillPosition();
			t.setId(UUIDUtil.getId());
			t.setBillid(req.getId());
			t.setLon(req.getLon());
			t.setLat(req.getLat());
			t.setStatus(req.getStatus());
			t.setRemark(req.getRemark());
			t.setCreatetime(System.currentTimeMillis());
			billPositionDao.save(t);
		}
	}
	
	@Override
	public Result pickupConfirm(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				//保存发货地位置信息
				saveBillPosition(req);
				if( checkBillauthForCuser(db,req.getCurruId(),"driver")){
					if( checkBillauthForstatus(db,"pickup") ){
						// 如果有已经确认装货的话 就不能操作 就不能
						Bill query = new Bill();
						query.setVehicleid(db.getVehicleid());
						query.setStatusStrs(new Byte[]{(byte)2,(byte)3,(byte)4});
						long count =billMapper.countByCondition(query);
						if(count == 0){
							Bill update =new Bill();
							update.setId(req.getId());
							//如果上传磅单了就保存
							if( StringUtils.isNotBlank(req.getImgdata()) ){//移动端图片保存
								FileUploadReq uploadreq = new FileUploadReq();
								uploadreq.setuId(req.getCurruId());
								uploadreq.setImgStr(req.getImgdata());
								Result uploadRs =fileUploadService.uploadImg(uploadreq);
								if( uploadRs!=null &&StringUtils.equals(uploadRs.getCode(), "000000") ){
									update.setPickupimgurl(uploadRs.getData().toString());
								}
							}
							if(req.getFile() != null){//PC端图片保存
								rs = iFileService.uploadByteImg(req.getFile());
								update.setPickupimgurl(rs.getData().toString());
							}
							if(req.getPsweight() !=null && StringUtils.isNotBlank(req.getPsweight().toString())){
								update.setPickupweight(req.getPsweight());
							}
							update.setStatus((byte)BillStatusEnum.DEPARTURE.getStatus());
							
							update.setModifier(req.getCurruId());
							update.setModifytime(System.currentTimeMillis());
							billMapper.updateByPrimaryKeySelective(update);
							saveBillTrack(db.getId(),1,BIllTrackMsg.STEP8,req.getCurruId(),BillStatusEnum.DEPARTURE.getStatus());
							//修改运力车辆 状态信息
							MemberVehicleReq req2 =new MemberVehicleReq();
							req2.setId(db.getVehicleid());
							req2.setBillstatus(""+BillStatusEnum.DEPARTURE.getStatus());
							memberVehicleService.updateVehiclebillStatus(req2);
							//为车主发送站内信
							MemberVo currUser =getMember(req.getCurruId());
							MemberVo receive =getMember(db.getVenderid());
							sendMsgInside(Arrays.asList(new String[]{currUser.getRealName(),db.getWaybillno()}), db.getId(), currUser, receive, MessageCodeEnum.BILL_2VENDER_DEPARTURE, "vender");
							rs.setCode("000000");
							rs.setData("操作成功");
						}else{
							rs.setErrorCode(ErrorCode.BILL_STATUS_VEHICLE_ONLYONE);
						}
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}
	
	@Override
	public Result driverdelete(WaybillConfirmReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				if( checkBillauthForCuser(db,req.getCurruId(),"driver")){
					if( checkBillauthForstatus(db,"delete") ){
						Bill update =new Bill();
						update.setId(req.getId());
						
						update.setDriverdelflag((byte)1);
						
						update.setModifier(req.getCurruId());
						update.setModifytime(System.currentTimeMillis());
						billMapper.updateByPrimaryKeySelective(update);
						saveBillTrack(db.getId(),1,BIllTrackMsg.STEP12,req.getCurruId(),db.getStatus());
					}else{
						rs.setErrorCode(ErrorCode.BILL_STATUS_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.BILL_PERMISSIONS);
				}
			}else{
				rs.setErrorCode(ErrorCode.BILL_NOT_FIND);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public WaybillResp queryWayBill(WaybillQueryReq req) throws Exception {
		WaybillResp resp =null;
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				resp = conver2billResp(db);
			}
		}
		return resp;
	}

	@Override
	public WaybillResp queryWayBillWithTrack(WaybillQueryReq req) throws Exception {
		WaybillResp resp= queryWayBill(req);
		if(resp !=null && StringUtils.isNotBlank(resp.getId())){
			List<BillTrack> list =billTrackDao.findWithBid(resp.getId());
			resp.setBillTrackList(conver2billTrackResp(list));
		}
		return resp;
	}

	@Override
	public PaginationVO<WaybillResp> page(WaybillQueryReq req) throws Exception {
		PaginationVO<WaybillResp> page =null;
		if(req!=null && req.getPageNo() >0 ){
			page=new PaginationVO<WaybillResp>();
			
			Bill query = new Bill();
			//以 车主 货主 司机身份查找运单
			if( req.getQueryType() ==1 ){
				//query.setOwnerid(req.getCurrId());
				Plan plan = new Plan();
				plan.setCreator(req.getCurrId());
				List<Plan> listPlan = planMapper.selectByCondition(plan);
				if(listPlan == null || listPlan.size() == 0){
					page.setTotal(0);
					page.setPageNo(req.getPageNo());
					return page;
				}
				String planCodes = "";
				for(Plan p : listPlan){
					planCodes += "\""+p.getPlancode()+"\",";
				}
				query.setPlancode(planCodes.substring(0, planCodes.length()-1));
			}else if(req.getQueryType() ==2 ){
				query.setVenderid(req.getCurrId());
//				query.setDesc4("0");
			}else if(req.getQueryType() ==3 ){
				query.setDriverid(req.getCurrId());
			}
			//关键字过滤
			if( StringUtils.isNotBlank(req.getKey()) ){
				query.setQueryKey(req.getKey().trim());
			}
			//计划id
			if( StringUtils.isNotBlank(req.getPlanId()) ){
				query.setPlanid(req.getPlanId());
			}
			//app状态查询  1:待确认   2 以拒绝   3 已收回  4 进行中 5 已完成
			if( StringUtils.isNotBlank(req.getAppStatus()) ){
				Byte[] status =null;
				switch (req.getAppStatus()) {
				case "1":
					status=new Byte[]{(byte)0};
					break;
				case "2":
					status=new Byte[]{(byte)7};
					break;
				case "3":
					status=new Byte[]{(byte)-1};
					break;
				case "4":
					status=new Byte[]{(byte)2,(byte)3,(byte)5,(byte)4,(byte)1};
					break;
				case "5":
					status=new Byte[]{(byte)6};
					break;
				}
				query.setStatusStrs(status);
			}
			//查询非委派计划生成的运单
			long total =billMapper.countByCondition(query);
			if( total>0 ){
				query.setStart((req.getPageNo()-1)*req.getPageSize());
				query.setLimit(req.getPageSize());
				List<Bill> list = billMapper.selectByCondition(query);
				if(req.getQueryType() == 3){
					for(int i=0;list!=null&&i<list.size();i++){
						Bill b = list.get(i);
						if(!StringUtils.equals(req.getCurrId(), b.getDriverid()) && !StringUtils.equals(b.getStatus().toString(), BillStatusEnum.COMPLETE.getStatus()+"")){
							b.setStatus(Byte.parseByte("-10"));
						}
					}
				}
				Collections.sort(list, new Comparator<Bill>() {
					@Override
					public int compare(Bill o1, Bill o2) {
						if(o1.getCreatetime()-o2.getCreatetime() < 0){
							return 1;
						}else if(o1.getModifytime()-o2.getModifytime() > 0){
							return -1;
						}else{
							return 0;
						}
					}
				});
				page.setList(conver2billResp(list));
			}
			page.setTotal(total);
			page.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
		}
		return page;
	}
	@Override
	public PaginationVO<WaybillResp> pageForBack(WaybillQueryReq req) throws Exception {
		PaginationVO<WaybillResp> page =null;
		if(req!=null && req.getPageNo() >0 ){
			page=new PaginationVO<WaybillResp>();
			
			Bill query = new Bill();
			query.setOrgid(req.getCurrOrgId());
			if( StringUtils.isNotBlank(req.getStatus()) ){
				query.setStatus(Byte.valueOf(req.getStatus()));
			}
			if( StringUtils.isNotBlank(req.getKey()) ){
				query.setQueryKey(req.getKey().trim());
			}
			long total =billMapper.countByConditionForBack(query);
			if( total>0 ){
				query.setStart((req.getPageNo()-1)*req.getPageSize());
				query.setLimit(req.getPageSize());
				page.setList(conver2billResp(billMapper.selectByConditionForBack(query)));
			}
			page.setTotal(total);
			page.setPageNo(req.getPageNo());
		}
		return page;
	}
	

	@Override
	public BillPlanResp queryWithPlanId(String pid) throws Exception {
		BillPlanResp resp =new BillPlanResp();
		if( StringUtils.isNotBlank(pid) ){
			Plan plan=planMapper.selectByPrimaryKey(pid);
			if( plan !=null && StringUtils.isNotBlank(plan.getRouteid())  ){
				
				resp.setPlanId(plan.getId());
				resp.setStartcity(plan.getStartcity());
				resp.setEndcity(plan.getEndcity());
				resp.setCargoname(plan.getCargoname());
				resp.setMeasure(plan.getMeasure());
				resp.setPriceunits(plan.getPriceunits());
				resp.setDistance(plan.getDistance());
				resp.setVender(plan.getVehicleownerid());
				resp.setPrice(String.valueOf(plan.getPrice()));
				resp.setIsAppoint(plan.getIsAppoint());
		
				if( plan.getStarttime() !=null ){
					resp.setStartTime(DateUtil.getDateString(new Date(plan.getStarttime())));
				}
				if( plan.getEndtime() !=null ){
					resp.setEndeTime(DateUtil.getDateString(new Date(plan.getEndtime())));
				}
				
				resp.setReceivepersion(plan.getReceiveperson());
				resp.setReceivepersionphone(plan.getReceivepersonphone());
				resp.setSendpersion(plan.getSendperson());
				resp.setSendpersionphone(plan.getSendpersonphone());
				FileFreight fileFreight = (FileFreight) freightInfoService.findFreightInfo(plan.getFreightid(), new Date()).getData();
				if(fileFreight != null){
					resp.setTallage(fileFreight.getTallage());
					resp.setPrice(fileFreight.getPrice()+"");
				}
				resp.setOverweight(inspectTraffic(pid));
				if(StringUtils.isNotBlank(plan.getConsigneeMerchant())){
					Merchant m = merchantMapper.selectByPrimaryKey(plan.getConsigneeMerchant());
					resp.setConsigneeMerchant(plan.getConsigneeMerchant());
					resp.setConsignee(m.getName());
				}
				if(StringUtils.isNotBlank(plan.getShipperMerchant())){
					Merchant m = merchantMapper.selectByPrimaryKey(plan.getShipperMerchant());
					resp.setShipperMerchant(plan.getShipperMerchant());
					resp.setShipper(m.getName());
				}
			}
			
		}
		return resp;
	}

	@Override
	public List<PositionResp> getBIllTrack(WaybillQueryReq req) throws Exception{
		List<PositionResp> list =null;
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db!=null && db.getStatus()==(byte)6 ){
				List<BillTrack> trackList =billTrackDao.findWithBid(db.getId());
				if( CollectionUtils.isNotEmpty(trackList) ){
					long startTime= 0l;
					long endTime=0l;
					for(BillTrack tarck:trackList){
						if( tarck.getStatus()==2 ){
							startTime=tarck.getTimestamp();
							continue;
						}
						if( tarck.getStatus()==5 ){
							endTime=tarck.getTimestamp();
							continue;
						}
					}
					if( startTime !=0l && endTime!=0l  ){
						PositionQueryReq req3 = new PositionQueryReq();
						req3.setStartTime(startTime);
						req3.setEndTime(endTime);
						req3.setCurrId(db.getDriverid());
						list=memberPositionService.queryPosition(req3);
					}
					
				}
			}
		}
		return list;
	}
	
	
	@Override
	public List<PositionResp> getBIllTrackAll(WaybillQueryReq req) throws Exception{
		//TODO
		List<PositionResp> list =null;
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			//获取始发点
			FileRoute route=routeMapper.selectByPrimaryKey(db.getRouteid());
			list=new ArrayList<PositionResp>(); 
			FilePositoin start = positionMapper.selectByPrimaryKey(route.getOpositionid());			
			list.add(conver2PositionResp(start));
			
			if( db!=null ){
				List<BillTrack> trackList =billTrackDao.findWithBid(db.getId());
				if( CollectionUtils.isNotEmpty(trackList) ){
					long startTime= 0l;
					long endTime=0l;
					for(BillTrack tarck:trackList){
						if( tarck.getStatus()==2 ){
							startTime=tarck.getTimestamp();
							continue;
						}
						if( tarck.getStatus()==5 ){
							endTime=tarck.getTimestamp();
							continue;
						}
					}
					if(endTime==0l){endTime=System.currentTimeMillis();}
					if( startTime !=0l  ){
						PositionQueryReq req3 = new PositionQueryReq();
						req3.setStartTime(startTime);
						req3.setEndTime(endTime);
						req3.setCurrId(db.getDriverid());
						List<PositionResp> dblist=memberPositionService.queryPosition(req3);
						if( CollectionUtils.isNotEmpty(dblist)){
							//少于等于三个点直接获取
							if(dblist.size()<=3){
								list.addAll(dblist);
							}else{
								//多于三个点则获取第一个 中间一个  最后一个	
								list.add(dblist.get(0));
								if(dblist.size()%2==0){
									list.add(dblist.get(dblist.size()/2));
								}else{
									list.add(dblist.get((dblist.size()+1)/2));
								}
								list.add(dblist.get(dblist.size()-1));
							}
							
						}
						
					}
					
				}
			}
			FilePositoin end = positionMapper.selectByPrimaryKey(route.getDpositionid());	
			list.add(conver2PositionResp(end));
		}
		return list;
	}
	
	//拼装参数  获取司机车辆信息
	private List<VehicleDriverVO> getVehicleDriver(String ids){
		List<VehicleDriverVO> list =null;
		if( StringUtils.isNotBlank(ids) ){
			list=new ArrayList<VehicleDriverVO>();
			String[]  idarr =ids.split(";");
			for(String id :idarr ){
				if( StringUtils.isNotBlank(id) ){

//					VehicleDriver db =vehicleDriverMapper.selectByPrimaryKey(id.split(",")[0]);
					//查询我的运力
					MemberCapa capa = new MemberCapa();
					capa.setId(id.split(",")[0]);
					List<MemberCapaList> cp = memberCapaMapper.selectByCondition(capa);
					if( cp.size()==1 ){
						VehicleDriverVO vo =new VehicleDriverVO();
						vo.setDriverId(cp.get(0).getDriverid());
						vo.setDriverName(cp.get(0).getDrivername());
						vo.setDriverTel(cp.get(0).getDrivertel());
						vo.setVehicleId(cp.get(0).getVehicleid());
						vo.setVehicleno(cp.get(0).getVehicleno());
						vo.setVehicleTypeName(cp.get(0).getVehicletype());
						vo.setOvernumber(id.split(",")[1]);
//						vo.setOvernumber("1");
						list.add(vo);
					}
				}
			}
		}
		return list;
	}
	
	//保存运单轨迹信息
	private void  saveBillTrack(String bId,int isShow,String msg,String currId,int status){
		if( StringUtils.isNotBlank(bId) ){
			BillTrack billTrack =new BillTrack();
			billTrack.setBillId(bId);
			billTrack.setCreateTime(DateUtil.getDateString(new Date(System.currentTimeMillis())));
			billTrack.setTimestamp(System.currentTimeMillis());
			billTrack.setIsShow(isShow);
			billTrack.setMsg(msg);
			billTrack.setOperator(currId);
			billTrack.setStatus(status);
			billTrackDao.insert(billTrack);
		}
	}
	//查看当前用户是否有操作权限
	private boolean checkBillauthForCuser(Bill bill,String cuserId,String type){
		boolean flag = false;
		switch (type) {
		//创建人
		case "creator":
			if( bill.getCreator().equals(cuserId) ){
				flag =true;
			}
			break;
		//车主	
		case "vender":
			if( bill.getVenderid().equals(cuserId) ){
				flag =true;
			}
			break;
		//司机	
		case "driver":
			if( bill.getDriverid().equals(cuserId) ){
				flag =true;
			}
			break;
		//货主	
		case "owner":
			if( bill.getOwnerid().equals(cuserId) ){
				flag =true;
			}
			break;

		default:
			flag=true;
			break;
		}
		return flag;
	}
	//运单状态权限控制
	private boolean checkBillauthForstatus(Bill bill,String type){
		boolean flag = false;
		switch (type) {
		//修改
		case "cancle":
		case "refuse":
		case "accept":
			if( bill.getStatus() ==(byte)BillStatusEnum.INIT.getStatus() ){
				flag =true;
			}
			break;
		//待提货确认	
		case "pickup":
			if( bill.getStatus() ==(byte)BillStatusEnum.ACCEPT.getStatus() ){
				flag =true;
			}
			break;
		//装货完成发车	
		case "departure":
			if( bill.getStatus() ==(byte)BillStatusEnum.DEPARTURE.getStatus() ){
				flag =true;
			}
			break;
		//到达完成发车	
		case "arrived":
			if( bill.getStatus() ==(byte)BillStatusEnum.TRANSIT.getStatus() ){
				flag =true;
			}
			break;
		//卸货发车	
		case "discharg":
			if( bill.getStatus() ==(byte)BillStatusEnum.DISCHARGECARGO.getStatus() ){
				flag =true;
			}
			break;
		//签收	
		case "sign":
			if( bill.getStatus() ==(byte)BillStatusEnum.SIGN.getStatus() ){
				flag =true;
			}
			break;
		//删除
		case "del":
		case "edit":
			if( bill.getStatus() ==(byte)BillStatusEnum.CANCLE.getStatus() ){
				flag =true;
			}else if(  bill.getStatus() ==(byte)BillStatusEnum.REFUSE.getStatus()){
				flag =true;
			}
			break;
		default:
			flag=true;
			break;
		}
		return flag;
	}
	
	private WaybillResp  conver2billResp(Bill bill) throws Exception{
		WaybillResp resp = null;
		if( bill !=null ){
			resp =new WaybillResp();
			PropertyUtils.copyProperties(resp, bill);
			if( StringUtils.isNotBlank(resp.getOrgid()) ){
				resp.setOrgName(orgService.findOne(resp.getOrgid()).getOrganizationname());
			}
			if( StringUtils.isNotBlank(bill.getDesc2()) ){
				resp.setRefuseType(bill.getDesc3());
				resp.setRefuseReson(bill.getDesc2());
			}
			
			if( StringUtils.isNotBlank(resp.getVenderid()) ){
				MemberVo member =getMember(resp.getVenderid());
				if( member !=null ){
					resp.setVenderName(member.getRealName());
					resp.setVenderTel(member.getCellphone());
				}
			}
			
			Plan p = planMapper.selectByPrimaryKey(bill.getPlanid());
			//发货方 收货方
			if(StringUtils.isNotBlank(p.getShipperMerchant())){
				Merchant m = merchantMapper.selectByPrimaryKey(p.getShipperMerchant());
				resp.setShipperMerchant(p.getShipperMerchant());
				resp.setShipper(m.getName());
			}
			if(StringUtils.isNotBlank(p.getConsigneeMerchant())){
				Merchant m = merchantMapper.selectByPrimaryKey(p.getConsigneeMerchant());
				resp.setConsigneeMerchant(p.getConsigneeMerchant());
				resp.setConsignee(m.getName());
			}
			Date date = null;
			if(StringUtils.isBlank(bill.getIsClearing()) || StringUtils.equals(bill.getIsClearing(), "0")){
				if(bill.getUnloadtime() == null){
					date = new Date();
				}else{
					date = new Date(bill.getUnloadtime());
				}
				FileFreight fileFreight = (FileFreight) freightInfoService.findFreightInfo(p.getFreightid(), date).getData();
				if(fileFreight != null){
					resp.setTallage(fileFreight.getTallage());
					resp.setPrice(fileFreight.getPrice());
					resp.setFrebilltype(fileFreight.getFrebilltype());
				}
			}
			resp.setOverweight(inspectTraffic(p.getId()));
			
			if(StringUtils.isNotBlank(resp.getPlanid())){
				Plan plan = planMapper.selectRootPlanByPlanId(resp.getPlanid());
				if(plan.getCompleted() == null){
					plan.setCompleted(0D);
				}
				resp.setPlanWeight(plan.getTotalplanned());
				resp.setPlanCompleteWeight(plan.getCompleted());
			}
		}
		return resp;
	}
	
	private List<WaybillResp>  conver2billResp(List<Bill> billList)throws Exception{
		List<WaybillResp> resp = null;
		if( CollectionUtils.isNotEmpty(billList) ){
			resp =new ArrayList<WaybillResp>();
			for( Bill bill :billList){
				resp.add(conver2billResp(bill));
			}
		}
		return resp;
	}
	private BillTrackResp  conver2billTrackResp(BillTrack billTrack) throws Exception{
		BillTrackResp resp = null;
		if( billTrack !=null ){
			resp =new BillTrackResp();
			PropertyUtils.copyProperties(resp, billTrack);
		}
		return resp;
	}
	
	private MemberVo getMember(String id){
		MemberVo member =MemberVoService.get(id);
		return member;
	}
	
	private List<BillTrackResp>  conver2billTrackResp(List<BillTrack> billTrackList)throws Exception{
		List<BillTrackResp> resp = null;
		if( CollectionUtils.isNotEmpty(billTrackList) ){
			resp =new ArrayList<BillTrackResp>();
			for( BillTrack billTrack :billTrackList){
				resp.add(conver2billTrackResp(billTrack));
			}
		}
		return resp;
	}
	
	private void sendMsgInside(List<String> params,String kId,MemberVo sendUser,MemberVo receiveUser,MessageCodeEnum codeEnum,String type ){
		SendMsgReq req =new SendMsgReq();
		if( sendUser !=null && receiveUser !=null && codeEnum !=null ){
			req.setParams(params);
			req.setKeyid(kId);
			//发送人
			req.setSendid(sendUser.getId());
			req.setSendname(sendUser.getRealName());
			//接受人
			req.setRecid(receiveUser.getId());
			req.setRecname(receiveUser.getRealName());
			req.setCodeEnum(codeEnum);
			req.setRecType(codeEnum.getType());
			//消息类别  系统 还是会员
			req.setType("2");
			//详情URI
			String uri ="";
			switch (type) {
				case "driver":
					uri ="/trwuliu/billdriver/detail?id="+kId;
					break;
				case "owner":
					uri ="/trwuliu/billowner/detail?id="+kId;
					break;
				case "vender":
					uri ="/trwuliu/billvender/detail?id="+kId;
					break;
			}
			req.setURI(uri);
			try {
				messageService.sendMessageInside(req);
			} catch (Exception e) {
				loger.warn("站内信发送失败,发送信息:{}",JSON.toJSON(req),e);
			}
		}
	}

	@Override
	public BillGpsResp gps(WaybillQueryReq req) throws Exception {
		BillGpsResp billGpsResp =new BillGpsResp();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill bill =billMapper.selectByPrimaryKey(req.getId());
			if( bill !=null ){
				FileRoute route =routeMapper.selectByPrimaryKey(bill.getRouteid());
				FilePositoin startp=posotionMapper.selectByPrimaryKey(route.getOpositionid());
				FilePositoin endp=posotionMapper.selectByPrimaryKey(route.getDpositionid());
				billGpsResp.setStartLat(""+startp.getLat());
				billGpsResp.setStartLon(""+startp.getLng());
				billGpsResp.setStartName(startp.getName());
				
				billGpsResp.setEndLat(""+endp.getLat());
				billGpsResp.setEndLon(""+endp.getLng());
				billGpsResp.setEndName(endp.getName());
				PositionQueryReq req3 =new PositionQueryReq();
				req3.setCurrId(bill.getDriverid());
				PositionResp posiotionDB =memberPositionService.queryLastPosition(req3);
				if( posiotionDB !=null ){
					billGpsResp.setCurrLat(String.valueOf(posiotionDB.getLat()));
					billGpsResp.setCurrLon(String.valueOf(posiotionDB.getLon()));
					billGpsResp.setUploadTime(DateUtil.getDateString(new Date(posiotionDB.getModifytime())));
				}
			}
		}
		return billGpsResp;
	}

	@Override
	public List<BillVehicleResp> queryVehicle(String pId) throws Exception {
		List<BillVehicleResp> resp = new ArrayList<BillVehicleResp>();
		Plan plan =planMapper.selectByPrimaryKey(pId);
		if(StringUtils.isNotBlank(pId) &&  plan !=null ){
			//查询所有车辆
			VehicleDriverReq query =new  VehicleDriverReq();
			query.setCreator(plan.getVehicleownerid());
			List<VehicleDriverResp> vehicleDriverList =vehicleDriverService.queryVehiDriverByCondition(query);
			List<String> vehicleIds = new ArrayList<String>();
			if( CollectionUtils.isNotEmpty(vehicleDriverList) ){
				for(int i=0;i<vehicleDriverList.size();i++){
					vehicleIds.add(vehicleDriverList.get(i).getVehicleId());
				}
			}
			List<MemberVehicle>  memberVehicleList =  memberVehicleMapper.selectVehicleByIds(vehicleIds);
			//查询计划实用车辆信息
			Bill query2 =new Bill();
			query2.setPlanid(pId);
			query2.setStatusStrs(new Byte[]{(byte)2,(byte)3,(byte)4,(byte)1,(byte)0});
//			List<Bill> billList=billMapper.selectByCondition(query2);
			if( CollectionUtils.isNotEmpty(vehicleDriverList) ){
				for(VehicleDriverResp item:vehicleDriverList){
					BillVehicleResp itemResp =new BillVehicleResp();
//					for( Bill bill: billList){
//						if( bill.getVehicleid().equals(item.getVehicleId()) ){
//							itemResp.setOvernumber(bill.getOvernumber());
//							break;
//						}
//					}
					itemResp.setId(item.getId());
					itemResp.setDriverName(item.getDriverName());
					itemResp.setDriverTel(item.getDriverTel());
					itemResp.setVehicleNo(item.getVehicleNo());
					itemResp.setVehicleTypeName(item.getVehicleTypeName());
					if(CollectionUtils.isNotEmpty(memberVehicleList)){
						for(MemberVehicle mv : memberVehicleList){
							if(StringUtils.equals(mv.getVehicleid(), item.getVehicleId())){
								itemResp.setVehiweight(mv.getVehiweight().toString());
								itemResp.setBillstatus(mv.getBillstatus());
							}
						}
					}
					resp.add(itemResp);
				}
			}
		}
		return resp;
	}
	//计划剩余运输量
	private Double inspectTraffic(String planid) {
		double overweight = 0D;
		Plan  plan = planMapper.selectByPrimaryKey(planid);
		overweight = plan.getTotalplanned();
		List<Plan> listPlanAppoint = planMapper.selectAppointByParams(plan.getId());
		if(listPlanAppoint != null){
			for(Plan p : listPlanAppoint){
				overweight -= p.getTotalplanned();
			}
		}
		List<Bill> listBill = billMapper.selectByPlanId(plan.getId());
		if(listBill != null){
			for(Bill b : listBill){
				overweight -= (b.getWeight()*Double.parseDouble(b.getOvernumber()));
			}	
		}
		return overweight;
	}
	
	public PaginationVO<WaybillResp> queryAppointBillPage(WaybillQueryReq req) throws Exception{
		PaginationVO<WaybillResp> page =null;
		if(req!=null && req.getPageNo() >0 ){
			page=new PaginationVO<WaybillResp>();
			Bill bill = new Bill();
			bill.setPathID(req.getCurrId());
			bill.setStart((req.getPageNo()-1)*req.getPageSize());
			bill.setLimit(req.getPageSize());
			//关键字过滤
			if( StringUtils.isNotBlank(req.getKey()) ){
				bill.setQueryKey(req.getKey().trim());
			}
			int count = billMapper.queryAppointCount(bill);
			if(count > 0){
				List<Bill> list = billMapper.queryAppointPage(bill);
				for(Bill bi : list){
					if(bi.getPathID().endsWith(req.getCurrId())){
						for(Bill b : list){
							if(b.getPathID().contains(bi.getVenderid())){
								b.setVenderid(bi.getVenderid());
							}
						}
					}
				}
				List<WaybillResp> resp = conver2billResp(list);
				page.setList(resp);
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
		}
		return page;
	}
	
	public Result updateBillImage(WaybillConfirmReq req) throws Exception{
		Result result = Result.getErrorResult();
		if(req != null){
			if(StringUtils.isNotBlank(req.getId())){
				Bill bill = billMapper.selectByPrimaryKey(req.getId());
				if(bill.getStatus() != BillStatusEnum.COMPLETE.getStatus()){
					Bill b = new Bill();
					b.setId(bill.getId());
					if( StringUtils.isNotBlank(req.getImgdata()) ){//移动端图片保存
						FileUploadReq uploadreq = new FileUploadReq();
						uploadreq.setuId(req.getCurruId());
						uploadreq.setImgStr(req.getImgdata());
						result =fileUploadService.uploadImg(uploadreq);
					}
					if(req.getFile() != null){//PC端图片保存
						result = iFileService.uploadByteImg(req.getFile());
					}
					if("TH".equals(req.getType())){
						b.setPickupimgurl(result.getData().toString());
						b.setPickupweight(req.getPsweight());
					}else if("XH".equals(req.getType())){
						b.setSignimgurl(result.getData().toString());
						b.setSignweight(req.getPsweight());
					}
					int i = billMapper.updateByPrimaryKeySelective(b);
					if(i > 0){
						result.setCode("000000");
						result.setData(result.getData().toString());
					}else{
						result.setCode("000002");
						result.setData("修改失败！");
					}
				}else{
					result.setCode("000001");
					result.setData("运单已签收不能修改磅单！");
				}
			}else{
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			}
		}
		return result;
	}
	
	public List<WaybillResp> queryReportBill(ReportVo vo) throws Exception{
		if(vo != null){
			List<Bill> list = billMapper.queryReportBill(vo);
			return conver2billResp(list);
		}
		return null;
	}
	
	@Override
	public PaginationVO<StatReportOfBillResp> queryAdminStatReport(StatReportReq req) {
		PaginationVO<StatReportOfBillResp> page = null;
		if(req != null){
			page = new PaginationVO<StatReportOfBillResp>();
			int start = (req.getPageNo() - 1) * req.getPageSize();
			int limit = req.getPageSize();
			req.setStart(start);
			req.setLimit(limit);
			int total = billMapper.queryAdminStatReportCount(req);
			if(total > 0){
				List<StatReportOfBillResp> list = billMapper.queryAdminStatReport(req);
				if(list != null && list.size()>0){
					for(StatReportOfBillResp resp : list){
						if(StringUtils.equals(resp.getIsAppoint(), "1")){
							Plan plan = planMapper.selectRootPlanByPlanId(resp.getPlanid());
							resp.setVenderName(plan.getVehicleownername());
						}
						
					}
				}
				page.setList(list);
				page.setPageNo(req.getPageNo());
				page.setPageSize(req.getPageSize());
				page.setTotal(total);
			}
		}
		return page;
	}

	@Override
	public List<StatReportOfBillResp> queryAdminAllStatReport(StatReportReq req) {
		List<StatReportOfBillResp> list = null;
		if(req != null){
			list = billMapper.queryAdminAllStatReport(req);
		}
		return list;
	}

	@Override
	public int queryAdminStatReportCount(StatReportReq req) {
		int count = 0;
		if(req != null){
			count = billMapper.queryAdminStatReportCount(req);
		}
		return count;
	}
	
	private PositionResp conver2PositionResp(FilePositoin positoin){
		PositionResp resp  =null;
		if(positoin !=null){
			resp=new PositionResp();
			resp.setLat(positoin.getLat());
			resp.setLon(positoin.getLng());
			resp.setProxyGps(positoin.getName());
		}
		return resp;
	}

	@Override
	public List<BillPositionResp> getBillPosition(String bid) throws Exception {
		// TODO Auto-generated method stub
		List<BillPositionResp> list= null;
		
		Bill db =billMapper.selectByPrimaryKey(bid);
		if(db!=null){
			list = new ArrayList<BillPositionResp>();
			//获取路线信息
			FileRoute route=routeMapper.selectByPrimaryKey(db.getRouteid());
			//获取始发地
			FilePositoin start = positionMapper.selectByPrimaryKey(route.getOpositionid());			
			list.add(copyBillPosition(start,"1", bid));
			//获取提货地 到货地
			List<BillPosition> p = billPositionDao.findwithBillId(bid);
			//获取用户位置查询的开始时间和结束时间
			Long beginTime = null;
			Long endTime = null;
			for (int i = 0; i < p.size(); i++) {
				if(p.get(i).getStatus().equals("2")){
					BillPositionResp r = new BillPositionResp();
					PropertyUtils.copyProperties(r, p.get(i));
					list.add(r);
					beginTime = p.get(i).getCreatetime();
				}else if(p.get(i).getStatus().equals("3")){
					endTime = p.get(i).getCreatetime();
				}
			}
			if(beginTime!=null&&endTime!=null){
				List<MemberPositionRecord> m = memberPositionRecordDao.findWithBid(db.getDriverid(), beginTime, endTime);
				Long t = null;
				for (int i = 0; i < m.size(); i++) {
					if(t==null){
						t=m.get(i).getCreatetime();
						BillPositionResp r = new BillPositionResp();
						r.setBillid(bid);
						r.setLat(m.get(i).getLat());
						r.setLon(m.get(i).getLon());
						r.setStatus("");
						r.setRemark("");
						r.setCreatetime(m.get(i).getCreatetime());
						list.add(r);
					}else if((m.get(i).getCreatetime()-t)>=(3000*60)){
						//时间价格大于三分钟
						t=m.get(i).getCreatetime();
						BillPositionResp r = new BillPositionResp();
						r.setBillid(bid);
						r.setLat(m.get(i).getLat());
						r.setLon(m.get(i).getLon());
						r.setStatus("");
						r.setRemark("");
						r.setCreatetime(m.get(i).getCreatetime());
						list.add(r);
					}
				}
			}
			for (int i = 0; i < p.size(); i++) {
				if(p.get(i).getStatus().equals("3")){
					endTime = p.get(i).getCreatetime();
					BillPositionResp r = new BillPositionResp();
					PropertyUtils.copyProperties(r, p.get(i));
					list.add(r);
				}
			}
			
			//获取目的地
			FilePositoin end = positionMapper.selectByPrimaryKey(route.getDpositionid());	
			list.add(copyBillPosition(end,"4",bid));
		}
		return list;
	}
	
	public BillPositionResp copyBillPosition(FilePositoin p,String status ,String bid){
		BillPositionResp resp = new BillPositionResp();
		resp.setBillid(bid);
		resp.setLat(p.getLat());
		resp.setLon(p.getLng());
		resp.setStatus(status);
		resp.setRemark(p.getName());
		return resp;
	}
}
