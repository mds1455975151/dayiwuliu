package com.tianrui.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.api.intf.IFileService;
import com.tianrui.api.intf.IMemberVoService;
import com.tianrui.api.intf.IMoenyDisposeService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.req.admin.ZJXLVehicleReq;
import com.tianrui.api.req.front.adminReport.StatReportReq;
import com.tianrui.api.req.front.bill.AnlianBillSaveReq;
import com.tianrui.api.req.front.bill.BillBankReq;
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
import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.api.resp.admin.OrganizationResp;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.ZJXLVehicleResp;
import com.tianrui.api.resp.front.adminReport.StatReportOfBillResp;
import com.tianrui.api.resp.front.bill.BillGpsResp;
import com.tianrui.api.resp.front.bill.BillPlanResp;
import com.tianrui.api.resp.front.bill.BillPositionResp;
import com.tianrui.api.resp.front.bill.BillTrackResp;
import com.tianrui.api.resp.front.bill.BillVehicleResp;
import com.tianrui.api.resp.front.bill.JTBBillResp;
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
import com.tianrui.service.bean.AddVehicleBankCard;
import com.tianrui.service.bean.AnlianDict;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.BillPosition;
import com.tianrui.service.bean.BillTrack;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.bean.MemberCapa;
import com.tianrui.service.bean.MemberCapaList;
import com.tianrui.service.bean.MemberPositionRecord;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.OrgSigner;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.bean.VehicleGpsZjxl;
import com.tianrui.service.bean.anlian.AnlianBill;
import com.tianrui.service.bean.anlian.PositionCounty;
import com.tianrui.service.jtb.BillMassageReq;
import com.tianrui.service.jtb.JtbHttpRequset;
import com.tianrui.service.mapper.AddVehicleBankCardMapper;
import com.tianrui.service.mapper.AnlianBillMapper;
import com.tianrui.service.mapper.AnlianDictMapper;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.MemberBankCardMapper;
import com.tianrui.service.mapper.MemberCapaMapper;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.OrgSignerMapper;
import com.tianrui.service.mapper.OwnerDriverMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mapper.PositionCountyMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;
import com.tianrui.service.mongo.BillPositionDao;
import com.tianrui.service.mongo.BillTrackDao;
import com.tianrui.service.mongo.CodeGenDao;
import com.tianrui.service.mongo.MemberPositionRecordDao;
import com.tianrui.service.mongo.VehicleGpsZjxlDao;
import com.tianrui.service.util.MapDistanceUtil;
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
	SystemMemberMapper systemMemberMapper;
	@Autowired
	IAnlianBillService anlianBillService;
	@Autowired
	PositionCountyMapper positionCountyMapper;
	@Autowired
	AnlianDictMapper anlianDictMapper;
	@Autowired
	AnlianBillMapper anlianBillMapper;
	@Autowired
	OrgSignerMapper orgSignerMapper;
	@Autowired
	MemberVoService memberVoService;
	@Autowired
	VehicleGpsZjxlDao vehicleGpsZjxlDao;
	@Autowired
	MemberPositionRecordDao memberPositionRecordDao;
	@Autowired
	ICrossVehicleService crossVehicleService;
	@Autowired
	MemberBankCardMapper memberBankCardMapper;
	@Autowired
	AddVehicleBankCardMapper addVehicleBankCardMapper;
	@Autowired
	IMoenyDisposeService moenyDisposeService;

	@Override
	public Result findPlanId(String id,String type) {
		Result rs = Result.getSuccessResult();
		if("al".equals(type)){
			AnlianBill bill = anlianBillMapper.selectByPrimaryKey(id);
			rs.setData(bill.getDesc1());
		}else if("dy".equals(type)){
			Bill bill = billMapper.selectByPrimaryKey(id);
			rs.setData(bill.getPlanid());
		}
		return rs;
	}
	
	@Override
	public Result uptBankCard(BillBankReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		Bill bill = billMapper.selectByPrimaryKey(req.getBillId());
		MemberBankCard bank = memberBankCardMapper.selectByPrimaryKey(req.getBankId());
		if(bill==null||bank==null){
			rs.setCode("1");
			rs.setError("运单id或银行卡id有误");
			return rs;
		}
		if("1".equals(bill.getConfirmPriceA())){
			rs.setCode("1");
			rs.setError("已运价确认运单，不能更换银行卡");
			return rs;
		}
		if(bill.getPayment().equals("2")){
			//支付对象车主
			rs.setCode("1");
			rs.setError("支付对象为车主，不能更换银行卡");
			return rs;
		}
		if(!bill.getDriverid().equals(req.getDriverId())){
			rs.setCode("1");
			rs.setError("非该司机运单，不能更换银行卡");
			return rs;
		}
		String bankType = "1";//1-默认银行卡，2-引用银行卡
		if(req.getBankType().equals("0")){
			//引用银行卡
			bankType = "2";
			AddVehicleBankCard add = new AddVehicleBankCard();
			add.setDriverid(req.getDriverId());
			add.setVehicleownerid(bank.getCreater());
			List<AddVehicleBankCard> adlist = addVehicleBankCardMapper.selectByCondition(add);
			if(adlist.size()!=1){
				rs.setCode("2");
				rs.setError("司机暂未引用该银行卡");
				return rs;
			}
		}else{
			bankType = "1";
			//添加银行卡
			if(!bank.getCreater().equals(req.getDriverId())){
				rs.setCode("3");
				rs.setError("非该司机银行卡");
				return rs;
			}
		}
		Bill upt = new Bill();
		upt.setId(req.getBillId());
		upt.setBankId(bank.getId());
		upt.setBankType(bankType);
		upt.setBankCard(bank.getBankcard());
		upt.setBankOwnerName(bank.getIdname());
		upt.setBankOwnerPhone(bank.getTelphone());
		billMapper.updateByPrimaryKeySelective(upt);
		return rs;
	}
	
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
					OrgSigner orgsigner = null;
					if(StringUtils.isNotBlank(plan.getReceiveid())){
						orgsigner = orgSignerMapper.selectByPrimaryKey(plan.getReceiveid());
					}
					String payment = null;
					if(StringUtils.isNotBlank(plan.getPayment())){
						payment = plan.getPayment();
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
							//支付对象
							bill.setPayment(payment);
							//支付对象银行卡信息
							if(payment.equals("1")){
								//司机
								String driver = bill.getDriverid();
								MemberBankCard bank = getBankCard(driver);
								if(bank!=null){
									bill.setBankId(bank.getId());
									bill.setBankCard(bank.getBankcard());
									bill.setBankOwnerName(bank.getIdname());
									bill.setBankOwnerPhone(bank.getTelphone());
									bill.setBankType("1");//默认银行卡
								}
							}else if(payment.equals("2")){
								//车主
								String vender = bill.getVenderid();
//								MemberBankCard bank = getBankCard(vender);
//								if(bank!=null){
//									bill.setBankId(bank.getId());
//									bill.setBankCard(bank.getBankcard());
//									bill.setBankOwnerName(bank.getIdname());
//									bill.setBankOwnerPhone(bank.getTelphone());
//									bill.setBankType("1");//默认银行卡
//								}
							}
							
							//收货人
							if (orgsigner != null) {
								bill.setReceive_memberid(orgsigner.getMemberid());
							}
							bills.add(bill);
					}
				}
			}
			
		}
		
		//保存
		if( CollectionUtils.isNotEmpty(bills) ){
			MemberVo currUser =getMember(req.getCurruId());
			for( Bill item:bills ){
				//车辆信息
				MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(item.getVehicleid());
				//司机信息
				SystemMember member = systemMemberMapper.selectByPrimaryKey(item.getDriverid());
				//车辆 司机均开票认证成功
				if(StringUtils.isNotBlank(member.getAldriverid())&&"1".equals(vehicle.getDesc1())){
					AnlianBillSaveReq alreq = new AnlianBillSaveReq();
					alreq.setPlanid(item.getPlanid());
					alreq.setBillEndTime(item.getEndtime());
					alreq.setBillStartTime(item.getStarttime());
					alreq.setDriverid(item.getDriverid());
					alreq.setPrice(req.getPrice());
					alreq.setSize(req.getWeight());
					alreq.setWeight(req.getWeight());
					alreq.setVolume("10");
					//计量单位
					alreq.setDesc1(item.getDesc1());
					//车辆信息
					alreq.setVehicleid(item.getVehicleid());
					//司机信息
					alreq.setDriverid(item.getDriverid());
					//车主信息
					alreq.setVenderid(item.getVenderid());
					// 货主信息
					alreq.setOwnerid(item.getOwnerid());
					//支付对象
					String payment = item.getPayment();
					alreq.setPayment(item.getPayment());
					//支付对象银行卡信息
					if(payment.equals("1")){
						//司机
						String driver = item.getDriverid();
						MemberBankCard bank = getBankCard(driver);
						if(bank!=null){
							alreq.setBankId(bank.getId());
							alreq.setBankCard(bank.getBankcard());
							alreq.setBankOwnerName(bank.getIdname());
							alreq.setBankOwnerPhone(bank.getTelphone());
							alreq.setBankType("1");//默认银行卡
						}
					}else if(payment.equals("2")){
						//车主
						String vender = item.getVenderid();
//						MemberBankCard bank = getBankCard(vender);
//						if(bank!=null){
//							alreq.setBankId(bank.getId());
//							alreq.setBankCard(bank.getBankcard());
//							alreq.setBankOwnerName(bank.getIdname());
//							alreq.setBankOwnerPhone(bank.getTelphone());
//							alreq.setBankType("1");//默认银行卡
//						}
					}
					
					//收货人
					alreq.setReceive_memberid(item.getReceive_memberid());
					item.getOvernumber();
					for (int i = 0; i < Integer.valueOf(item.getOvernumber()); i++) {
						rs = anlianBillService.alBillSave(alreq);
						if(!StringUtils.equals(rs.getCode(), "000000")){
							return rs;
						}
					}
				}else{
					billMapper.insert(item);
					saveBillTrack(item.getId(),1,BIllTrackMsg.INIT,req.getCurruId(),item.getStatus());
					//为司机发送站内信
					MemberVo receive =getMember(item.getDriverid());
					sendMsgInside(Arrays.asList(new String[]{item.getWaybillno(),(currUser.getRealName())}), item.getId(), currUser, receive, MessageCodeEnum.BILL_2DRIVER_CREATE, "driver");
				}
			}
		}
		return rs;
	}
	/** 获取用户默认银行卡*/
	protected MemberBankCard getBankCard(String creater) {
		MemberBankCard card = null;
		MemberBankCard bank = new MemberBankCard();
		bank.setCreater(creater);
		bank.setBankstatus("1");
		bank.setBankautid("1");
		List<MemberBankCard> bankList = memberBankCardMapper.selectByCondition(bank);
		if(bankList.size()==1){
			card = new MemberBankCard();
			card = bankList.get(0);
		}
		return card;
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
//				if( checkBillauthForCuser(db,req.getCurruId(),"owner") || (StringUtils.equals(plan.getIsAppoint(), "1") && StringUtils.equals(rootPlan.getCreator(), req.getCurruId()))){
				//是否为收货人||是否为货主
				if(checkBillauthForCuser(db,req.getCurruId(),"signer")||checkBillauthForCuser(db,req.getCurruId(),"owner")){
					//是否为代签收状态	
					if( checkBillauthForstatus(db,"sign") ){
						Bill update =new Bill();
						update.setId(req.getId());
						
						update.setStatus((byte)BillStatusEnum.COMPLETE.getStatus());
						update.setTrueweight(Double.valueOf(req.getWeight()));
						update.setModifier(req.getCurruId());
						update.setModifytime(System.currentTimeMillis());
						update.setOwnerSigntime(System.currentTimeMillis());
						update.setConfirmPriceA("0");//前台未运价确认状态
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

	public Result billExchange(Bill db) throws Exception{
		Result rs = Result.getSuccessResult();
		boolean flag = true;
		
		BillMassageReq billMassage = new BillMassageReq();
		billMassage.setOriginalDocumentNumber(db.getWaybillno());
		billMassage.setCarrier("中原大易科技有限公司");
		billMassage.setUnifiedSocialCreditIdentifier("91410482MA3XD9CA67");
		billMassage.setPermitNumber("410482006680");
		billMassage.setConsignmentDateTime(dateExchange(db.getCreatetime()));
		//干线运输
		billMassage.setBusinessTypeCode("1002996");
		billMassage.setDespatchActualDateTime(dateStrExchange(db.getStarttime()));
		billMassage.setGoodsReceiptDateTime(dateExchange(System.currentTimeMillis()));
		billMassage.setConsignor(db.getConsignorname());
		
		FileRoute route = routeMapper.selectByPrimaryKey(db.getRouteid());
		String countrySubdivisionCode = positionExchange(route.getOpositionid());
		if(StringUtils.isBlank(countrySubdivisionCode)){
			rs.setCode("1");
			rs.setError("未找到位置信息");
			flag = false;
			return rs;
			
		}
		billMassage.setCountrySubdivisionCode(countrySubdivisionCode);//装货地
		billMassage.setConsignee(db.getReceivername());
		String receiptCountrySubdivisionCode = positionExchange(route.getDpositionid());
		if(StringUtils.isBlank(receiptCountrySubdivisionCode)){
			rs.setCode("1");
			rs.setError("未找到位置信息");
			flag = false;
			return rs;
		}
		billMassage.setReceiptCountrySubdivisionCode(receiptCountrySubdivisionCode);//收货地址
		if(db.getTrueweight()==null||db.getPrice()==null){
			rs.setCode("1");
			rs.setError("签收量或运费为空");
			flag = false;
			return rs;
		}
		Double price = db.getTrueweight()*db.getPrice();
		billMassage.setTotalMonetaryAmount(String.format("%.3f ",price));//总金额 三位小数 整数
		//车辆牌照类型 其它-99
		MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(db.getVehicleid());
		//临时车辆不推送交通部
		if("1".equals(vehicle.getDesc2())){
			rs.setCode("1");
			rs.setError("临时车辆不推送交通部");
			flag = false;
			return rs;
		}
		billMassage.setLicensePlateTypeCode("01");
		billMassage.setVehicleNumber(db.getVehicleno());
		String vehicleClassificationCode = vheicleExchange(vehicle.getVehicletype());
		if(StringUtils.isBlank(vehicleClassificationCode)){
			rs.setCode("1");
			rs.setError("车辆类型有误");
			flag = false;
			return rs;
		}
		billMassage.setVehicleClassificationCode(vehicleClassificationCode);//车辆分类
		billMassage.setVehicleTonnage(String.format("%.2f ",vehicle.getVehiweight()));//车辆载重量 2位小数
		String roadTransportCertificateNumber = vehicle.getOpercode();
		if(StringUtils.isBlank(roadTransportCertificateNumber)){
			rs.setCode("1");
			rs.setError("营运证号不能为空");
			flag = false;
			return rs;
		}
		billMassage.setRoadTransportCertificateNumber(roadTransportCertificateNumber);//车辆道路运输经营许可证
		billMassage.setNameOfPerson(db.getDrivername());
		billMassage.setTelephoneNumber(db.getDrivertel());
		billMassage.setDescriptionOfGoods(db.getCargoname());
		billMassage.setCargoTypeClassificationCode("94");//货物分类代码 4.2.5
		billMassage.setGoodsItemGrossWeight(String.format("%.3f ",db.getTrueweight()*1000));//三位小数
		
		if(flag){
			JtbHttpRequset jtb = new JtbHttpRequset();
			String result = jtb.putJtb(billMassage);
			if(!StringUtils.equals(result, "success")){
				rs.setCode("1");
				rs.setError("操作失败，请稍候再试");
			}
		}
		return rs;
	}
	/** 获取车辆类型代码*/
	public String vheicleExchange(String  vehicleType){
		AnlianDict record = new AnlianDict();
		record.setType("vehicle");
		record.setWlcode(vehicleType);
		List<AnlianDict> list = anlianDictMapper.selectByCondition(record);
		String code = null;
		if(list.size()==1){
			code = list.get(0).getJtbCode();
		}
		return code;
	}
	/** 获取城市代码*/
	public String positionExchange(String positionid){
		FilePositoin position = positionMapper.selectByPrimaryKey(positionid);
		PositionCounty 	record = new PositionCounty();
		record.setName(position.getOa());
		List<PositionCounty> list = positionCountyMapper.findCount(record);
		String resp = null;
		if(list.size()==1){
			resp = list.get(0).getId();
		}
		return resp;
	}
	
	public String dateExchange(Long time){
		String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(time));
		return str;
	}
	public String dateStrExchange(String time) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(time);
		String str = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		return str;
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
							update.setAcctepttime(System.currentTimeMillis());
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
							b.setAcctepttime(System.currentTimeMillis());
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
	public Result dischargeConfirm(WaybillConfirmReq req,HttpServletRequest request) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				if( checkBillauthForCuser(db,req.getCurruId(),"driver")){
					if( checkBillauthForstatus(db,"transit") ){
						//保存卸货地位置信息
						saveBillPosition(req);
						//移动端图片保存
						if(StringUtils.isNotBlank(req.getImgdata())){
							FileUploadReq uploadreq = new FileUploadReq();
							uploadreq.setuId(req.getCurruId());
							uploadreq.setImgStr(req.getImgdata());
							rs =fileUploadService.uploadImg(uploadreq);
						}else if(req.getFile() != null){
							rs = iFileService.uploadByteImg(req.getFile(),req.getCurruId());
						}
						if(rs!=null &&StringUtils.equals(rs.getCode(), "000000") && StringUtils.isNotBlank(rs.getData().toString())){
							Long timestape = System.currentTimeMillis();
							
							Long money = (long) (db.getPrice()*db.getWeight()*100l);
							SaveBillMoneyReq bm = new SaveBillMoneyReq();
							bm.setCapitalno(UUIDUtil.getId());//流水号
							bm.setWaybillno(db.getWaybillno());
							bm.setCreatetime(timestape);
							bm.setPendingmoney(money);
							bm.setUseryhno(db.getDriverid());//身份证号
							rs = moenyDisposeService.billSaveMoney(bm);
							if(!rs.getCode().equals("000000")){
								return rs;
							}
							Bill update =new Bill();
							update.setId(req.getId());
							update.setSignweight(req.getPsweight());
							update.setSignimgurl(String.valueOf(rs.getData()));
							update.setStatus((byte)BillStatusEnum.SIGN.getStatus());
							update.setUnloadtime(timestape);
							update.setModifier(req.getCurruId());
							update.setModifytime(timestape);
							//计算到货地距离偏差
							FileRoute route = routeMapper.selectByPrimaryKey(db.getRouteid());
							update.setD_deviation(distanceChange(route.getDpositionid(),req.getLon(),req.getLat()));
							//提货位置
							BillPosition bp = billPositionDao.findBillIdAndStatus(req.getId(), "2");
							if(bp!=null){
								//提货-到货，时间间隔
								update.setInterTime(timestape-bp.getCreatetime());
								//提货-到货，距离间隔
								update.setInterDistance(MapDistanceUtil.getDistance(bp.getLon()*Math.pow(10,-6), bp.getLat()*Math.pow(10,-6), req.getLon()*Math.pow(10,-6),req.getLat()*Math.pow(10,-6)));
							}
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
							try {
								crossVehicleService.updateLogoStatus(request,db.getVehicleno(), "0",db.getCargoname());
							} catch (Exception e) {
								System.out.println("关闭中交车辆状态失败");
							}
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
	public Result pickupConfirm(WaybillConfirmReq req,HttpServletRequest request) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Bill db =billMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				if( checkBillauthForCuser(db,req.getCurruId(),"driver")){
					if( checkBillauthForstatus(db,"pickup") ){
						//保存发货地位置信息
						saveBillPosition(req);
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
								rs = iFileService.uploadByteImg(req.getFile(),req.getCurruId());
								update.setPickupimgurl(rs.getData().toString());
							}
							if(req.getPsweight() !=null && StringUtils.isNotBlank(req.getPsweight().toString())){
								update.setPickupweight(req.getPsweight());
							}
							update.setStatus((byte)BillStatusEnum.DEPARTURE.getStatus());
							//运单开始时间
							update.setBegintime(System.currentTimeMillis());
							update.setModifier(req.getCurruId());
							update.setModifytime(System.currentTimeMillis());
							//计算始发地距离偏差
							FileRoute route = routeMapper.selectByPrimaryKey(db.getRouteid());
							update.setQ_deviation(distanceChange(route.getOpositionid(),req.getLon(),req.getLat()));
							
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
							try {
								crossVehicleService.updateLogoStatus(request,db.getVehicleno(), "1",db.getCargoname());
							} catch (Exception e) {
								System.out.println("开启中交车辆状态失败");
							}
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
	//计算两点间距离
	public Double distanceChange(String positionId,Integer lon,Integer lat){
		
		FilePositoin position = posotionMapper.selectByPrimaryKey(positionId);
		Double resp = MapDistanceUtil.getDistance(lon*Math.pow(10,-6),lat*Math.pow(10,-6),position.getLng()*Math.pow(10,-6),position.getLat()*Math.pow(10,-6));
		return resp;
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
			if( StringUtils.isNotBlank(req.getBillNo()) ){
				query.setWaybillno(req.getBillNo().trim());
			}
			if(StringUtils.isNotBlank(req.getPayType())){
				query.setPayType(req.getPayType().trim());
			}
			if(StringUtils.isNotBlank(req.getPayDesc1())){
				query.setPayDESC1(req.getPayDesc1().trim());
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
				resp.setPayment(plan.getPayment());
		
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
	
	/**保存运单轨迹信息*/
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
		//收货人	
		case "signer":
			if(StringUtils.isNotBlank(bill.getReceive_memberid())){
				if( bill.getReceive_memberid().equals(cuserId) ){
					flag =true;
				}
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
				OrganizationResp org =orgService.findOne(resp.getOrgid());
				if( org !=null ){
					resp.setOrgName(org.getOrganizationname());
				}
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
				WaybillResp rp = new WaybillResp();
				PropertyUtils.copyProperties(rp, bill);
				
				//防止为空
				rp.setVenderTel("");
				rp.setVenderName("");
				if( bill!=null && StringUtils.isNotBlank(bill.getVenderid()) ){
					MemberVo vender =memberVoService.get(bill.getVenderid());
					if(vender!=null ){
						rp.setVenderName(vender.getRealName());
						rp.setVenderTel(vender.getCellphone());
					}
				}
				resp.add(rp);
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
						result = iFileService.uploadByteImg(req.getFile(),req.getCurruId());
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
	public Result getPosition(String bid) throws Exception {
		Result rs = Result.getSuccessResult();
		List<BillPositionResp> zjlist= null;
		Bill db =billMapper.selectByPrimaryKey(bid);
		if(db!=null){
			zjlist = new ArrayList<BillPositionResp>();
			String billStatus = db.getStatus().toString();
			//获取路线信息
			FileRoute route=routeMapper.selectByPrimaryKey(db.getRouteid());
			//获取始发地
			FilePositoin start = positionMapper.selectByPrimaryKey(route.getOpositionid());			
			zjlist.add(copyBillPosition(start,"1", bid,billStatus));
			//获取提货地 到货地
			List<BillPosition> p = billPositionDao.findwithBillId(bid);
			//获取用户位置查询的开始时间和结束时间
			Long beginTime = null;
			Long endTime = null;
			for (int i = 0; i < p.size(); i++) {
				if(p.get(i).getStatus().equals("2")){
					BillPositionResp r = new BillPositionResp();
					PropertyUtils.copyProperties(r, p.get(i));
					zjlist.add(r);
					beginTime = p.get(i).getCreatetime();
				}else if(p.get(i).getStatus().equals("3")){
					endTime = p.get(i).getCreatetime();
				}
			}
			endTime = endTime==null?System.currentTimeMillis():endTime;
			if(beginTime!=null){
				//查询车辆是否有中交兴路地址
				ZJXLVehicleReq req = new ZJXLVehicleReq();
				req.setVehicleno(db.getVehicleno());
//				req.setVehiclelogo("1");
				req.setCrossloge("1");
				PageResp<ZJXLVehicleResp> page = crossVehicleService.find(req);
				List<ZJXLVehicleResp> zjxl = page.getList();
				if(zjxl.size()==1){
					List<VehicleGpsZjxl> m = vehicleGpsZjxlDao.getVehicleTrack(db.getVehicleno(),beginTime,endTime );
					System.out.println("m.size()="+m.size());
					for (int i = 0; i < m.size(); i++) {
						BillPositionResp r = new BillPositionResp();
						r.setBillid(bid);
						r.setLat(Integer.valueOf((int) (m.get(i).getLat()*1000000)));
						r.setLon(Integer.valueOf((int) (m.get(i).getLon()*1000000)));
						r.setStatus("");
						r.setRemark("");
						r.setCreatetime(m.get(i).getUtc());
						zjlist.add(r);
					}
				}
			}
			for (int i = 0; i < p.size(); i++) {
				if(p.get(i).getStatus().equals("3")){
					endTime = p.get(i).getCreatetime();
					BillPositionResp r = new BillPositionResp();
					PropertyUtils.copyProperties(r, p.get(i));
					zjlist.add(r);
				}
			}
			
			//获取目的地
			FilePositoin end = positionMapper.selectByPrimaryKey(route.getDpositionid());	
			zjlist.add(copyBillPosition(end,"4",bid,billStatus));
		}
		rs.setData(zjlist);
		return rs;
	}

	@Override
	public List<BillPositionResp> getBillPositionOld(String bid) throws Exception {
		List<BillPositionResp> list= null;
		
		Bill db =billMapper.selectByPrimaryKey(bid);
		if(db!=null){
			list = new ArrayList<BillPositionResp>();
			String billStatus = db.getStatus().toString();
			//获取路线信息
			FileRoute route=routeMapper.selectByPrimaryKey(db.getRouteid());
			//获取始发地
			FilePositoin start = positionMapper.selectByPrimaryKey(route.getOpositionid());			
			list.add(copyBillPosition(start,"1", bid,billStatus));
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
			endTime = endTime==null?System.currentTimeMillis():endTime;
			if(beginTime!=null){
				//查询车辆是否有中交兴路地址
				ZJXLVehicleReq req = new ZJXLVehicleReq();
				req.setVehicleno(db.getVehicleno());
				req.setVehiclelogo("1");
				req.setCrossloge("1");
				PageResp<ZJXLVehicleResp> page = crossVehicleService.find(req);
				List<ZJXLVehicleResp> zjxl = page.getList();
				if(zjxl.size()==1){
					List<VehicleGpsZjxl> m = vehicleGpsZjxlDao.getVehicleTrack(db.getVehicleno(),beginTime,endTime );
					for (int i = 0; i < m.size(); i++) {
						BillPositionResp r = new BillPositionResp();
						r.setBillid(bid);
						r.setLat(Integer.valueOf((int) (m.get(i).getLat()*1000000)));
						r.setLon(Integer.valueOf((int) (m.get(i).getLon()*1000000)));
						r.setStatus("");
						r.setRemark("");
						r.setCreatetime(m.get(i).getUtc());
						list.add(r);
					}
				}else{
					List<MemberPositionRecord> m = memberPositionRecordDao.findWithBid(db.getDriverid(), beginTime, endTime);
					for (int i = 0; i < m.size(); i++) {
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
			list.add(copyBillPosition(end,"4",bid,billStatus));
		}
		return list;
	}

	@Override
	public List<BillPositionResp> getBillPosition (String bid) throws Exception {
		List<BillPositionResp> list= null;
		
		Bill db =billMapper.selectByPrimaryKey(bid);
		if(db!=null){
			list = new ArrayList<BillPositionResp>();
			String billStatus = db.getStatus().toString();
			//获取路线信息
			FileRoute route=routeMapper.selectByPrimaryKey(db.getRouteid());
			//获取始发地
			FilePositoin start = positionMapper.selectByPrimaryKey(route.getOpositionid());			
			list.add(copyBillPosition(start,"1", bid,billStatus));
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
			endTime = endTime==null?System.currentTimeMillis():endTime;
			if(beginTime!=null){
				List<MemberPositionRecord> m = memberPositionRecordDao.findWithBid(db.getDriverid(), beginTime, endTime);
				for (int i = 0; i < m.size(); i++) {
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
			list.add(copyBillPosition(end,"4",bid,billStatus));
		}
		return list;
	}
	
	public BillPositionResp copyBillPosition(FilePositoin p,String status ,String bid,String billStatus){
		BillPositionResp resp = new BillPositionResp();
		resp.setBillid(bid);
		resp.setBillStatus(billStatus);
		resp.setLat(p.getLat());
		resp.setLon(p.getLng());
		resp.setStatus(status);
		resp.setRemark(p.getName());
		return resp;
	}

	@Override
	public PaginationVO<JTBBillResp> findJtbBill(WaybillQueryReq req) throws Exception {
		Bill bill = new Bill();
		if(null!=req.getNo()){
			bill.setStart(req.getNo()*req.getSize());
			bill.setLimit(req.getSize());
		}
		bill.setWaybillno(req.getBillNo());
		bill.setJtb(req.getJtb());
		bill.setVehicleno(req.getVehicleno());
		if(req.getCreatetime().length()>0){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String dstr=req.getCreatetime();
			Date date = sdf.parse(dstr); 
			bill.setCreateTimeBegin(date.getTime());
			bill.setCreateTimeEnd(date.getTime()+24*60*60*1000);
		}
		Long a = billMapper.countSelectJtbBill(bill);
		List<Bill> list = billMapper.selectJtbBill(bill);
		
		PaginationVO<JTBBillResp> resp = new PaginationVO<JTBBillResp>();
		List<JTBBillResp> ll = new ArrayList<JTBBillResp>();
		for(Bill b : list){
			JTBBillResp r = new JTBBillResp();
			PropertyUtils.copyProperties(r, b);
			ll.add(r);
		}
		resp.setList(ll);
		resp.setTotal(a);
		return resp;
	}

	@Override
	public Result findJtbBillDetail(WaybillQueryReq req) throws Exception {
		Result result = Result.getSuccessResult();
		if (req != null && StringUtils.isNotBlank(req.getId())) {
			result.setData(conver2billResp(billMapper.selectByBillId(req.getId())));
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public Result putJtbBill(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		Bill db = billMapper.selectByPrimaryKey(id);
		if(StringUtils.equals("1", db.getJtb())){
			rs.setCode("1");
			rs.setError("运单已推送交通部，不能重复提交");
			return rs;
		}
		if(db.getPrice() == null || db.getPrice() <= 0){
			rs.setCode("1");
			rs.setError("推送单价必须大于0");
			return rs;
		}
		if(db.getTrueweight() == null || db.getTrueweight() >= 99 || db.getTrueweight() <= 0){
			rs.setCode("1");
			rs.setError("推送重量必须大于0且小于99吨");
			return rs;
		}
//		if(db.getInterTime() == null ||db.getInterTime()<=1000*60*30){
//			rs.setCode("1");
//			rs.setError("提货卸货时间间隔不能小于30分钟");
//			return rs;
//		}
		
		
		//运单推送交通部
		rs = billExchange(db);
		if(StringUtils.equals(rs.getCode(), "000000")){
//		if(true){
			Bill upt = new Bill();
			upt.setId(id);
			upt.setJtb("1");
			upt.setJTBPushTime(System.currentTimeMillis());
			billMapper.updateByPrimaryKeySelective(upt);
		}
		return rs;
	}
	/** 安联运单推送交通部 数据处理
	 * @throws Exception */
	public Result anlianBillExchange(AnlianBill ab) throws Exception{
		Result rs = Result.getSuccessResult();
		
		BillMassageReq billMassage = new BillMassageReq();
		billMassage.setOriginalDocumentNumber(ab.getBillno());
		billMassage.setCarrier("中原大易科技有限公司");
		billMassage.setUnifiedSocialCreditIdentifier("91410482MA3XD9CA67");
		billMassage.setPermitNumber("410482006680");
		billMassage.setConsignmentDateTime(dateExchange(ab.getCreatetime()));
		//干线运输
		billMassage.setBusinessTypeCode("1002996");
		billMassage.setDespatchActualDateTime(dateStrExchange(ab.getYqthrq()));
		billMassage.setGoodsReceiptDateTime(dateExchange(System.currentTimeMillis()));
		billMassage.setConsignor(Constant.SYSTEM_SHIPPER);
		if(StringUtils.isBlank(ab.getDesc1())){
			rs.setCode("1");
			rs.setError("计划不能为空");
			return rs;
		}
		Plan plan = planMapper.selectByPrimaryKey(ab.getDesc1());
		FileRoute route = routeMapper.selectByPrimaryKey(plan.getRouteid());
		String countrySubdivisionCode = positionExchange(route.getOpositionid());
		if(StringUtils.isBlank(countrySubdivisionCode)){
			rs.setCode("1");
			rs.setError("未找到位置信息");
			return rs;
		}
		billMassage.setCountrySubdivisionCode(countrySubdivisionCode);//装货地
		billMassage.setConsignee(ab.getShr());
		String receiptCountrySubdivisionCode = positionExchange(route.getDpositionid());
		if(StringUtils.isBlank(receiptCountrySubdivisionCode)){
			rs.setCode("1");
			rs.setError("未找到位置信息");
			return rs;
		}
		billMassage.setReceiptCountrySubdivisionCode(receiptCountrySubdivisionCode);//收货地址
		billMassage.setTotalMonetaryAmount(String.format("%.3f",Double.valueOf(ab.getYf())));//总金额 三位小数 整数
		//车辆牌照类型 其它-99
		MemberVehicle veh = new MemberVehicle();
		veh.setVehicleprefix(ab.getCph().substring(0, 2));
		veh.setVehicleno(ab.getCph().substring(2, ab.getCph().length()));
		List<MemberVehicle> ml = memberVehicleMapper.selectMyVehicleByCondition(veh);
		MemberVehicle vehicle = null;
		if(ml.size()==1){
			vehicle = ml.get(0);
		}else{
			rs.setCode("1");
			rs.setError("车辆信息有误");
			return rs;
		}
		//临时车辆不推送交通部
		if("1".equals(vehicle.getDesc2())){
			rs.setCode("1");
			rs.setError("临时车辆不推送交通部");
			return rs;
		}
		billMassage.setLicensePlateTypeCode("01");
		billMassage.setVehicleNumber(ab.getCph());
		String vehicleClassificationCode = vheicleExchange(vehicle.getVehicletype());
		if(StringUtils.isBlank(vehicleClassificationCode)){
			rs.setCode("1");
			rs.setError("车辆类型有误");
			return rs;
		}
		billMassage.setVehicleClassificationCode(vehicleClassificationCode);//车辆分类
		billMassage.setVehicleTonnage(String.format("%.2f ",vehicle.getVehiweight()));//车辆载重量 2位小数
		String roadTransportCertificateNumber = vehicle.getOpercode();
		if(StringUtils.isBlank(roadTransportCertificateNumber)){
			rs.setCode("1");
			rs.setError("营运证号不能为空");
			return rs;
		}
		billMassage.setRoadTransportCertificateNumber(roadTransportCertificateNumber);//车辆道路运输经营许可证
		
		SystemMember membre = new SystemMember();
		membre.setAldriverid(ab.getSj());
		List<SystemMember> sl = systemMemberMapper.selectByCondition(membre);
		if(sl.size()==0){
			rs.setCode("1");
			rs.setError("司机信息有误");
			return rs;
		}
		SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(sl.get(0).getId());
		billMassage.setNameOfPerson(info.getUsername());
		billMassage.setTelephoneNumber(sl.get(0).getCellphone());
		billMassage.setDescriptionOfGoods(ab.getHpmc());
		billMassage.setCargoTypeClassificationCode("94");//货物分类代码 4.2.5
		billMassage.setGoodsItemGrossWeight(String.format("%.3f ",Double.valueOf(ab.getSl())*1000));//三位小数
		
		JtbHttpRequset jtb = new JtbHttpRequset();
		String result = jtb.putJtb(billMassage);
		if(!StringUtils.equals(result, "success")){
			rs.setCode("1");
			rs.setError("操作失败，请稍候再试");
		}
		return rs;
	}

	@Override
	public Result putAnlianJtbBill(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		AnlianBill ab = anlianBillMapper.selectByPrimaryKey(id);
		if(StringUtils.equals("1", ab.getDesc2())){
			rs.setCode("1");
			rs.setError("运单已经推送交通部，不能重复推送");
			return rs;
		}
		if (Double.valueOf(ab.getYf()) <= 0) {
			rs.setCode("1");
			rs.setError("单价必须大于0");
			return rs;
		}
		if (Double.valueOf(ab.getZzl()) <= 0 || Double.valueOf(ab.getZzl()) >= 99) {
			rs.setCode("1");
			rs.setError("总量必须大于0且小于99吨");
			return rs;
		}
		
		rs = anlianBillExchange(ab);
//		if(true){
		if(StringUtils.equals("000000", rs.getCode())){
			AnlianBill upt = new AnlianBill();
			upt.setId(ab.getId());
			upt.setDesc2("1"); 
			upt.setJTBPushTime(System.currentTimeMillis());
			anlianBillMapper.updateByPrimaryKeySelective(upt);
		}
		return rs;
	}

	@Override
	public PaginationVO<JTBBillResp> findALJtbBill(WaybillQueryReq req) throws Exception {
		AnlianBill ab = new AnlianBill();
		ab.setBillno(req.getBillNo());
		ab.setStart(req.getNo());
		ab.setLimit(req.getSize());
		
		ab.setDesc2(req.getJtb());
		ab.setCph(req.getVehicleno());
		if(req.getCreatetime()!=""&& req.getCreatetime()!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String dstr=req.getCreatetime();
			Date date = sdf.parse(dstr); 
			ab.setPtBegintime(date.getTime());
			ab.setPtEndtime(date.getTime()+24*60*60*1000);
		}
		List<AnlianBill> list = anlianBillMapper.selectByJTB(ab);
		Long a = anlianBillMapper.selectByJTBCount(ab);
		PaginationVO<JTBBillResp> page = new PaginationVO<JTBBillResp>();
		List<JTBBillResp> ds = new ArrayList<JTBBillResp>();
		for(AnlianBill s : list){
			JTBBillResp res = new JTBBillResp();
			res.setId(s.getId());
			res.setJtb(s.getDesc2());
			res.setVehicleno(s.getCph());
			res.setWaybillno(s.getBillno());
			res.setCreatetime(s.getCreatetime());
			ds.add(res);
		}
		page.setList(ds);
		page.setTotal(a);
		return page;
	}
}
