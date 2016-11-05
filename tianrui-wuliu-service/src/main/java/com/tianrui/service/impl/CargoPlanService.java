package com.tianrui.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.req.admin.AdminPlanReq;
import com.tianrui.api.req.front.cargoplan.PlanAppointReq;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.cargoplan.PlanEditReq;
import com.tianrui.api.req.front.cargoplan.PlanQueryReq;
import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.api.resp.front.cargoplan.PlanStatResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.enums.PlanStatusEnum;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.bean.FileOrgCargo;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.impl.FreightInfoService;
import com.tianrui.service.admin.impl.OrganizationService;
import com.tianrui.service.admin.mapper.FileFreightMapper;
import com.tianrui.service.admin.mapper.FileOrgCargoMapper;
import com.tianrui.service.admin.mapper.FileRouteMapper;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mongo.CodeGenDao;
import com.tianrui.service.mongo.PlanTemplateDao;
@Service
public class CargoPlanService implements ICargoPlanService{

	private static Logger loger =LoggerFactory.getLogger(CargoPlanService.class);
	
	@Autowired
	private PlanMapper planMapper;
	@Autowired
	private BillMapper billMapper;
	@Autowired
	MessageService messageService;
	@Autowired
	MemberVoService memberVoService;
	@Autowired
	protected CodeGenDao codeGenDao;
	@Autowired
	FileFreightMapper freightMapper ;
	@Autowired
	FileRouteMapper routeMapper;
	@Autowired
	FileOrgCargoMapper orgCargoMapper;
	@Autowired
	PlanTemplateDao planTempateDao;
	@Autowired
	OrganizationService orgService;
	@Autowired
	private FreightInfoService freightInfoService;
	

	@Override
	public PaginationVO<PlanResp> pageForAdmin(AdminPlanReq req) throws Exception {
		PaginationVO<PlanResp> page =null;
		if(req!=null && req.getPageNo()>0){
			Plan plan = new Plan();
			page = new PaginationVO<PlanResp>();
			if(StringUtils.isNotBlank(req.getStatus()))plan.setStatus(Byte.valueOf(req.getStatus()));
			if(StringUtils.isNotBlank(req.getPlancode()))plan.setPlancode(req.getPlancode());
			if(StringUtils.isNotBlank(req.getVenderName()))plan.setVehicleownername(req.getVenderName());
			if(StringUtils.isNotBlank(req.getCargoname()))plan.setCargoname(req.getCargoname());
			if(StringUtils.isNotBlank(req.getOrgId())) plan.setOrgid(req.getOrgId());
			if(StringUtils.isNotBlank(req.getCreatetimeForStr())) plan.setCreateTimeBegin(DateUtil.parse(req.getCreatetimeForStr(), "yyyy-MM-dd HH:mm:ss"));
			if(StringUtils.isNotBlank(req.getCreatetimeEndStr())) plan.setCreateTimeEnd(DateUtil.parse(req.getCreatetimeEndStr(), "yyyy-MM-dd HH:mm:ss"));
			plan.setOrgid(req.getOrgId());
			long total = planMapper.countByCondition(plan);
			if(total>0){
				plan.setStart((req.getPageNo()-1)*req.getPageSize());
				plan.setLimit(req.getPageSize());
				page.setList(copyProperties(planMapper.selectByCondition(plan)));
			}
			page.setPageNo(req.getPageNo());
			page.setTotal(total);
		}
		return page;
	}
	
	
	@Override
	public PaginationVO<PlanResp> pageForApp(PlanQueryReq req) throws Exception {
		PaginationVO<PlanResp> page =null;
		if(req!=null && req.getPageNo()>0){
			page =new PaginationVO<PlanResp>();
			Plan plan = new Plan();
			if(StringUtils.isNotBlank(req.getStatus()))plan.setStatus(Byte.valueOf(req.getStatus()));
			if(StringUtils.isNotBlank(req.getOwnerId()))plan.setCreator(req.getOwnerId());
			if(StringUtils.isNotBlank(req.getVenderId()))plan.setVehicleownerid(req.getVenderId());
			if(StringUtils.isNotBlank(req.getIsAppoint()))plan.setIsAppoint(req.getIsAppoint());
			if(StringUtils.isNotBlank(req.getPlanId()))plan.setPid(req.getPlanId());
			if(StringUtils.isNotBlank(req.getSearchParam())){
				plan.setQueryKey(req.getSearchParam());
			}
			plan.setVenderdelflag(Byte.valueOf("0"));
			long total = planMapper.countByCondition(plan);
			if(total>0){
				plan.setStart((req.getPageNo()-1)*req.getPageSize());
				plan.setLimit(req.getPageSize());
				page.setList(copyProperties(planMapper.selectByCondition(plan)));
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(total);
		}
		return page;
	}
	@Override
	public PlanResp detail(PlanQueryReq req) throws Exception {
		PlanResp resp = null;
		if( req!=null && StringUtils.isNotBlank(req.getId()) ){
			Plan  plan =planMapper.selectByPrimaryKey(req.getId());
			if(plan.getCompleted() == null){
				plan.setCompleted((double) 0);
			}
			resp =copyPropertie(plan);
			if(plan.getStatus() != PlanStatusEnum.COMPLETE.getStatus()){
				FileFreight fileFreight = (FileFreight) freightInfoService.findFreightInfo(resp.getFreightid(),new Date()).getData();
				resp.setPrice(fileFreight.getPrice());
				resp.setTallage(fileFreight.getTallage());
				resp.setOrgname(fileFreight.getOrganizationname());
			}else{
				FileFreight fileFreight = freightMapper.selectByPrimaryKey(resp.getFreightid());
				resp.setOrgname(fileFreight.getOrganizationname());
			}
			resp.setOverweight(inspectTraffic(plan.getId()));
		}
		return resp;
	}
	@Override
	public Result acceptConfirm(PlanConfirmReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req !=null  && StringUtils.isNotBlank(req.getId())){
			Plan  plan =planMapper.selectByPrimaryKey(req.getId());
			if( plan !=null  ){
				Plan update =new Plan();
				update.setId(plan.getId());
				update.setStatus(PlanStatusEnum.ACCEPT.getStatus());
				update.setModifier(req.getCurruId());
				update.setModifytime(System.currentTimeMillis());
				planMapper.updateByPrimaryKeySelective(update);
				
				
				MemberVo owner =memberVoService.get(plan.getCreator());
				MemberVo vender=memberVoService.get(plan.getVehicleownerid());
				sendMsgInside(Arrays.asList(new String[]{plan.getPlancode(),vender.getRealName()}), plan.getId(), vender, owner, MessageCodeEnum.PLAN_2OWNER_ACCEPT, "vender");
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	@Override
	public Result refuseConfirm(PlanConfirmReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req !=null  && StringUtils.isNotBlank(req.getId())){
			Plan  plan =planMapper.selectByPrimaryKey(req.getId());
			if( plan !=null  ){
				Plan update =new Plan();
				update.setId(plan.getId());
				update.setStatus(PlanStatusEnum.refuse.getStatus());
				update.setModifier(req.getCurruId());
				update.setModifytime(System.currentTimeMillis());
				update.setRefusereson(req.getReson());
				update.setRefuseresontype(req.getResonType());
				planMapper.updateByPrimaryKeySelective(update);
				
				MemberVo owner =memberVoService.get(plan.getCreator());
				MemberVo vender=memberVoService.get(plan.getVehicleownerid());
				sendMsgInside(Arrays.asList(new String[]{plan.getPlancode(),vender.getRealName()}), plan.getId(), vender, owner, MessageCodeEnum.PLAN_2OWNER_REFUSE, "owner");
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	@Override
	public Result venderDelConfirm(PlanConfirmReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req !=null  && StringUtils.isNotBlank(req.getId())){
			Plan  plan =planMapper.selectByPrimaryKey(req.getId());
			if( plan !=null  ){
				Plan update =new Plan();
				update.setId(plan.getId());
				update.setModifier(req.getCurruId());
				update.setModifytime(System.currentTimeMillis());
				update.setVenderdelflag((byte)1);
				planMapper.updateByPrimaryKeySelective(update);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	@Override
	public Result cancleConfirm(PlanConfirmReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req !=null  && StringUtils.isNotBlank(req.getId())){
			Plan  plan =planMapper.selectByPrimaryKey(req.getId());
			if( plan !=null  ){
				Plan update =new Plan();
				update.setId(plan.getId());
				update.setModifier(req.getCurruId());
				update.setModifytime(System.currentTimeMillis());
				update.setStatus(PlanStatusEnum.CANCLE.getStatus());
				planMapper.updateByPrimaryKeySelective(update);
				
				MemberVo owner =memberVoService.get(plan.getCreator());
				MemberVo vender=memberVoService.get(plan.getVehicleownerid());
				sendMsgInside(Arrays.asList(new String[]{plan.getPlancode(),owner.getRealName()}), plan.getId(), owner, vender, MessageCodeEnum.PLAN_2VENDER_CANCLE, "owner");
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	
	
	@Override
	public Result completePlan(PlanConfirmReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req !=null  && StringUtils.isNotBlank(req.getId())){
			Plan  plan =planMapper.selectByPrimaryKey(req.getId());
			if( plan !=null  ){
				Plan update =new Plan();
				update.setId(plan.getId());
				update.setModifier(req.getCurruId());
				update.setModifytime(System.currentTimeMillis());
				update.setStatus(PlanStatusEnum.COMPLETE.getStatus());
				planMapper.updateByPrimaryKeySelective(update);
				update.setPlancode(plan.getPlancode());
				planMapper.updateComplete(update);
				// 修改未接受的0  已拒绝的 7 已收回 -1的运单并删除.
				Bill query =new Bill();
				Byte[] status=new Byte[]{(byte)0,(byte)7,(byte)-1};
				//query.setPlanid(req.getId());
				query.setPlancode("'"+plan.getPlancode()+"'");
				query.setStatusStrs(status);
				List<Bill> billList= billMapper.selectByCondition(query);
				if( CollectionUtils.isNotEmpty(billList) ){
					MemberVo sender = memberVoService.get(plan.getVehicleownerid());
					for( Bill item:billList ){
						//未接受的发送消息
						if( item.getStatus()==(byte)0 ){
							MemberVo receiver =memberVoService.get(item.getDriverid());
							sendMsgInside(Arrays.asList(new String[]{plan.getPlancode(),item.getWaybillno()}), item.getId(), sender, receiver, MessageCodeEnum.BILL_2DRIVER_PLANCOMPLATE, "driver");
						}
						Bill updateBill = new Bill();
						updateBill.setId(item.getId());
						updateBill.setOwnerdelflag((byte)1);
						updateBill.setVenderdelflag((byte)1);
						updateBill.setDriverdelflag((byte)1);
						billMapper.updateByPrimaryKeySelective(updateBill);
					}
				}
				
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}


	@Override
	public Result ownerDeleteConfirm(PlanConfirmReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req !=null  && StringUtils.isNotBlank(req.getId())){
			Plan  plan =planMapper.selectByPrimaryKey(req.getId());
			if( plan !=null  ){
				Plan update =new Plan();
				update.setId(plan.getId());
				update.setModifier(req.getCurruId());
				update.setModifytime(System.currentTimeMillis());
				update.setOwnerdelflag((byte)1);
				planMapper.updateByPrimaryKeySelective(update);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	
	@Override
	public Result editPlan(PlanEditReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getId())&& StringUtils.isNotBlank(req.getCurruId() )  ){
			Plan plan =planMapper.selectByPrimaryKey(req.getId());
			if( plan !=null ){
				//车主信息
				Plan update =new Plan();
				update.setId(plan.getId());
				update.setVehicleownerid(req.getVenderId());
				MemberVo vender =memberVoService.get(req.getVenderId());
				update.setVehicleownername(vender.getRealName());
				update.setVehicleownerphone(vender.getCellphone());
				//初始化信息
				update.setVenderdelflag((byte)0);
				update.setOwnerdelflag((byte)0);
				update.setModifier(req.getCurruId());
				update.setModifytime(System.currentTimeMillis());
				update.setRefusereson("");
				update.setRefuseresontype("");
				//自定义属性
				update.setTotalplanned(Double.valueOf(req.getTotalplanned()));
				update.setStarttime(DateUtil.parse(req.getStarttimeStr(), "yyyy-MM-dd HH:mm:ss"));
				update.setEndtime(DateUtil.parse(req.getEndtimeStr(), "yyyy-MM-dd HH:mm:ss"));
				//联系人信息
				update.setTelephone(req.getTelephone());
				update.setLinkman(req.getLinkman());
				//状态status 熟车计划 状态为已接受
				if( StringUtils.equals("1", req.getIsFamily()) ){
					update.setStatus(PlanStatusEnum.ACCEPT.getStatus());
					update.setIsfamily((byte)1);
				//普通运单 状态为待接受
				}else{
					update.setStatus(PlanStatusEnum.NEW.getStatus());
					update.setIsfamily((byte)0);
				}
				planMapper.updateByPrimaryKeySelective(update);
				//发送消息
				if( plan.getIsfamily()==0 ){
					MemberVo owner =memberVoService.get(plan.getCreator());
					sendMsgInside(Arrays.asList(new String[]{plan.getPlancode(),owner.getRealName()}), plan.getId(), owner, vender, MessageCodeEnum.PLAN_2VENDER_CREATE, "vender");
				}
			}else{
				rs.setErrorCode(ErrorCode.PARAM_ERROR);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}

	@Override
	public Result savePlan(PlanSaveReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getCurruId() ) && StringUtils.isNotBlank(req.getFreightid()) && StringUtils.isNotBlank(req.getRouteid()) && StringUtils.isNotBlank(req.getCargoid()) &&  StringUtils.isNotBlank(req.getVenderId())){
			FileFreight fileFreight = freightMapper.selectByPrimaryKey(req.getFreightid());
			FileRoute fileRoute =routeMapper.selectByPrimaryKey(req.getRouteid());
			FileOrgCargo cargo =orgCargoMapper.selectByPrimaryKey(req.getCargoid());
			if( fileFreight !=null &&  fileRoute !=null && cargo!=null){
				Plan plan =new Plan();
				plan.setId(UUIDUtil.getId());
				plan.setPlancode(codeGenDao.codeGen(1));
				//通过策略以及路径形成的信息
				setPlanData(fileFreight, fileRoute, cargo, plan);
				//车主信息
				plan.setVehicleownerid(req.getVenderId());
				MemberVo vender =memberVoService.get(req.getVenderId());
				plan.setVehicleownername(vender.getRealName());
				plan.setVehicleownerphone(vender.getCellphone());
				//初始化信息
				plan.setVenderdelflag((byte)0);
				plan.setOwnerdelflag((byte)0);
				//自定义属性
				plan.setTotalplanned(Double.valueOf(req.getTotalplanned()));
				plan.setStarttime(DateUtil.parse(req.getStarttimeStr(), "yyyy-MM-dd HH:mm:ss"));
				plan.setEndtime(DateUtil.parse(req.getEndtimeStr(), "yyyy-MM-dd HH:mm:ss"));
				//合同 1:合同  0自由  价格信息
				if( StringUtils.equals("1",fileFreight.getDesc2() ) ){
					plan.setType((byte)1);
				}else{
					plan.setType((byte)0);
				}
				//联系人信息
				plan.setTelephone(req.getTelephone());
				plan.setLinkman(req.getLinkman());
				//备注字段
				plan.setCreator(req.getCurruId());
				plan.setCreatetime(System.currentTimeMillis());
				plan.setModifier(req.getCurruId());
				plan.setModifytime(System.currentTimeMillis());
				//状态status 熟车计划 状态为已接受
				if( StringUtils.equals("1", req.getIsFamily()) ){
					plan.setStatus(PlanStatusEnum.ACCEPT.getStatus());
					plan.setIsfamily((byte)1);
				//普通运单 状态为待接受
				}else{
					plan.setStatus(PlanStatusEnum.NEW.getStatus());
					plan.setIsfamily((byte)0);
				}
				plan.setIsAppoint("0");
				plan.setPathID(req.getCurruId());
				planMapper.insert(plan);
				
				//发送消息
				if( plan.getIsfamily()==0 ){
					MemberVo owner = new MemberVo();
					owner.setUserName(req.getOrganizationname());
					owner.setId(req.getCurruId());
					sendMsgInside(Arrays.asList(new String[]{plan.getPlancode(),owner.getRealName()}), plan.getId(), owner, vender, MessageCodeEnum.PLAN_2VENDER_CREATE, "vender");
				}

				//保存模版
				if( StringUtils.equals("1", req.getIstemplate()) ){
					planTempateDao.save(plan);
				}
			}else{
				rs.setErrorCode(ErrorCode.PARAM_ERROR);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}

	private void setPlanData( FileFreight fileFreight, FileRoute fileRoute, FileOrgCargo cargo,
			Plan plan) {
		//货物信息
		plan.setCargoid(cargo.getId());
		plan.setCargoname(cargo.getCargoname());
		plan.setMeasure(cargo.getMeasure());
		plan.setCargocode(cargo.getCargono());
		//策略信息
		plan.setFreightid(fileFreight.getId());
		plan.setPriceunits(fileFreight.getPriceunits());
		plan.setPrice(fileFreight.getPrice());
		plan.setTallage(fileFreight.getTallage());
		plan.setFreightname(fileFreight.getDesc1());
		plan.setOrgid(fileFreight.getOrganizationid());
		//路径信息
		plan.setRouteid(fileRoute.getId());
		plan.setSendperson(fileRoute.getSendpersion());
		plan.setSendpersonphone(fileRoute.getSendpersionphone());
		plan.setReceiveperson(fileRoute.getReceivepersion());
		plan.setReceivepersonphone(fileRoute.getReceivepersionphone());
		plan.setDistance(fileRoute.getDistance());
		plan.setStartcity(fileRoute.getOaddr());
		plan.setEndcity(fileRoute.getDaddr());
	}

	private List<PlanResp> copyProperties(List<Plan> list) throws Exception{
		List<PlanResp> la = new ArrayList<PlanResp>();
		for(Plan p : list){
			PlanResp re =copyPropertie(p);
			la.add(re);
		}
		return la;
	}
	
	private PlanResp copyPropertie(Plan plan)throws Exception{
		PlanResp resp =null;
		if( plan !=null ){
			resp = new PlanResp();
			PropertyUtils.copyProperties(resp, plan);
			
			if( StringUtils.isNotBlank(resp.getOrgid()) ){
				resp.setOrgname(orgService.findOne(resp.getOrgid()).getOrganizationname());
			}
			if( StringUtils.isNotBlank(resp.getCreator()) ){
				MemberVo member =memberVoService.get(resp.getCreator());
				if( member !=null ){
					resp.setOwnerName(member.getRealName());
				}
			}
		}
		return resp;
	}
	//发送站内信
	private void sendMsgInside(List<String> params,String keyId,MemberVo sender,MemberVo receiver,MessageCodeEnum codeEnum,String type ){
		SendMsgReq req = new SendMsgReq();
		if(sender != null && receiver != null && codeEnum != null){
			req.setParams(params);
			req.setKeyid(keyId);
			//发送人
			req.setSendid(sender.getId());
			req.setSendname(sender.getRealName());
			//接受人
			req.setRecid(receiver.getId());
			req.setRecname(receiver.getRealName());
			req.setCodeEnum(codeEnum);
			req.setRecType(codeEnum.getType());
			//消息类别  系统 还是会员
			req.setType("2");
			//详情URI
			String uri ="";
			switch (type) {
			case "owner":
				uri ="/trwuliu/planowner/detail?id="+keyId;
				break;
			case "vender":
				uri ="/trwuliu/planvender/detail?id="+keyId;
				break;
			case "appoint":
				uri ="/trwuliu/planAppoint/detail?id="+keyId;
				break;
			case "driver":
				uri = "/trwuliu/billdriver/detail?id="+keyId;
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
	public PlanStatResp planstat(PlanQueryReq req) throws Exception {
		PlanStatResp resp = new PlanStatResp();
		if( req!=null && StringUtils.isNotBlank(req.getId()) ){
			Bill query = new Bill();
			query.setPlanid(req.getId());
			query.setStatusStrs(new Byte[]{(byte)6});
			Double comple =billMapper.querySumWithPlan(query);
			if(comple !=null){
				resp.setCompTotal(comple.doubleValue());
			}
			query.setStatusStrs(new Byte[]{(byte)2,(byte)3,(byte)5,(byte)4,(byte)1});
			Double tran =billMapper.querySumWithPlan(query);
			if(tran !=null){
				resp.setTranTotal(tran.doubleValue());
			}
		}
		return resp;
	}

	@Override
	public List<PlanResp> findPlanByEndTime(Long st) throws Exception {
		return copyProperties(planMapper.selectByEndTime(st));
	}


	@Override
	public Result addAppointPlan(PlanAppointReq req) throws Exception {
		Result result = Result.getSuccessResult();
		Plan plan = planMapper.selectByPrimaryKey(req.getPlanid());
		if(plan == null){
			result.setCode("000001");
			result.setError("该计划不存在");
			return result;
		}
		if(inspectTraffic(plan.getId()) - Double.valueOf(req.getTotalplanned()) < 0){
			result.setCode("000002");
			result.setError("运输量已超出计划剩余运输量");
			return result;
		}
		if(plan.getStatus() == PlanStatusEnum.COMPLETE.getStatus()){
			result.setCode("1");
			result.setError("计划已经完成，不能继续委派运单了！");
			return result;
		}
		plan.setId(UUIDUtil.getId());
		//自定义属性
		plan.setTotalplanned(Double.valueOf(req.getTotalplanned()));
		plan.setStarttime(DateUtil.parse(req.getBegintime(), "yyyy-MM-dd HH:mm:ss"));
		plan.setEndtime(DateUtil.parse(req.getEndtime(), "yyyy-MM-dd HH:mm:ss"));
		plan.setStatus(Byte.valueOf("0"));
		
		//联系人信息
		plan.setTelephone(req.getMemberVo().getCellphone());
		plan.setLinkman(req.getMemberVo().getRealName());
		plan.setCreator(req.getMemberVo().getId());
		plan.setCreatetime(System.currentTimeMillis());
		plan.setModifier(req.getMemberVo().getId());
		plan.setModifytime(System.currentTimeMillis());
		//执行计划车主
		plan.setVehicleownerid(req.getVenderid());
		plan.setVehicleownername(req.getVenderName());
		plan.setVehicleownerphone(req.getVenderTel());
		//委派运单
		plan.setIsAppoint("1");
		plan.setPid(req.getPlanid());
		plan.setPathID(plan.getPathID()+","+req.getMemberVo().getId());
		planMapper.insert(plan);
		//发送消息
		MemberVo owner = new MemberVo();
		owner.setUserName(req.getVenderName());
		owner.setId(req.getVenderid());
		sendMsgInside(Arrays.asList(new String[]{plan.getPlancode(),req.getMemberVo().getRealName()}), plan.getId(), req.getMemberVo(), owner, MessageCodeEnum.PLAN_2VENDER_APPOINT, "appoint");
		result.setCode("000000");
		result.setData("委派计划成功");
		return result;
	}
	

	@Override
	public Result editAppointPlan(PlanAppointReq req) throws Exception {
		Result result = Result.getSuccessResult();
		Plan plan = planMapper.selectByPrimaryKey(req.getPlanid());
		if(plan == null){
			result.setCode("000001");
			result.setError("该计划不存在");
			return result;
		}
		if(inspectTraffic(plan.getPid()) - Double.valueOf(req.getTotalplanned()) < 0){
			result.setCode("000002");
			result.setError("运输量已超出计划剩余运输量");
			return result;
		}
		if(plan.getStatus() != PlanStatusEnum.COMPLETE.getStatus()){
			result.setCode("1");
			result.setError("计划已经完成，不能继续委派运单了！");
			return result;
		}
		//自定义属性
		plan.setId(req.getPlanid());
		plan.setTotalplanned(Double.valueOf(req.getTotalplanned()));
		plan.setStarttime(DateUtil.parse(req.getBegintime(), "yyyy-MM-dd HH:mm:ss"));
		plan.setEndtime(DateUtil.parse(req.getEndtime(), "yyyy-MM-dd HH:mm:ss"));
		plan.setStatus(Byte.valueOf("0"));
		
		plan.setModifier(req.getMemberVo().getId());
		plan.setModifytime(System.currentTimeMillis());
		//执行计划车主
		plan.setVehicleownerid(req.getVenderid());
		plan.setVehicleownername(req.getVenderName());
		plan.setVehicleownerphone(req.getVenderTel());
		plan.setPathID(plan.getPathID().substring(0, plan.getPathID().lastIndexOf(","))+","+req.getMemberVo().getId());
		//委派运单
		planMapper.updateByPrimaryKeySelective(plan);
		//发送消息
		MemberVo owner = new MemberVo();
		owner.setUserName(req.getVenderName());
		owner.setId(req.getVenderid());
		sendMsgInside(Arrays.asList(new String[]{plan.getPlancode(),req.getMemberVo().getRealName()}), plan.getId(), req.getMemberVo(), owner, MessageCodeEnum.PLAN_2VENDER_APPOINT, "appoint");
		result.setCode("000000");
		result.setData("委派计划成功");
		return result;
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
	
	@Override
	public Map<String, String> planComplete(String planId, String memberid, int type) {
		Map<String, String> map = new HashMap<String, String>();
		DecimalFormat df = new DecimalFormat("#0.00"); 
		double complete = 0D, remain  = 0D;
		if(StringUtils.isNotBlank(planId) && StringUtils.isNotBlank(memberid)){
			Plan plan = planMapper.selectByPrimaryKey(planId);
			if(plan != null){
				Map<String, String> params = new HashMap<String, String>();
				params.put("plancode", plan.getPlancode());
				params.put("venderid", memberid);
				complete = billMapper.queryComplete(params);
				remain  = plan.getTotalplanned();
				if(type == 1 && StringUtils.isNotBlank(plan.getPid())){
					Plan p = planMapper.selectByPrimaryKey(plan.getPid());
					remain  = p.getTotalplanned();
				}
				remain  -= complete;
			}
		}
		map.put("complete", df.format(complete));
		map.put("remain", df.format(remain ));
		return map;
	}
	
}