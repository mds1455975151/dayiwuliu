package com.tianrui.service.impl.planGoods;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.admin.intf.IFileRouteService;
import com.tianrui.api.admin.intf.IMerchantService;
import com.tianrui.api.intf.planGoods.IPlanGoodsService;
import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.goods.GoodsAuditReq;
import com.tianrui.api.req.goods.GoodsTOPlanReq;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.req.money.MessagePushReq;
import com.tianrui.api.resp.admin.OrganizationResp;
import com.tianrui.api.resp.admin.RoutePosition;
import com.tianrui.api.resp.goods.PlanGoodsResp;
import com.tianrui.api.resp.goods.SelectAppBillResp;
import com.tianrui.api.resp.goods.SelectAppPlanGoodsResp;
import com.tianrui.api.resp.goods.SelectAppPlanResp;
import com.tianrui.api.resp.goods.SelectPlanGoodsResp;
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
import com.tianrui.service.admin.impl.OrganizationService;
import com.tianrui.service.admin.mapper.FileFreightMapper;
import com.tianrui.service.admin.mapper.FileOrgCargoMapper;
import com.tianrui.service.admin.mapper.FileRouteMapper;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.PlanGoods;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.impl.MemberVoService;
import com.tianrui.service.impl.MessageService;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.PlanGoodsMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mongo.CodeGenDao;
import com.tianrui.service.mongo.PlanTemplateDao;

@Service
public class PlanGoodsService implements IPlanGoodsService {

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
	CacheClient cacheClient;
	@Autowired
	PlanGoodsMapper planGoodsMapper;
	@Autowired
	private IMerchantService merchantService;
	@Autowired
	private PlanMapper planMapper;
	@Autowired
	IMessagePushService messagePushService;
	@Autowired
	IFileRouteService fileRouteService;
	@Autowired
	BillMapper billMapper;

	@Override
	public Double countPlanTotal(Long timeBegin, Long timeEnd) {
		PlanGoods query = new PlanGoods();
		query.setTimeBegin(timeBegin);
		query.setTimeEnd(timeEnd);
		Double tot = planGoodsMapper.countPlanTotal(query);
		if(tot==null){
			tot = 0.00;
		}
		return tot;
	}

	@Override
	public Double countGoodsTotal(Long timeBegin, Long timeEnd) {
		PlanGoods query = new PlanGoods();
		query.setTimeBegin(timeBegin);
		query.setTimeEnd(timeEnd);
		return planGoodsMapper.countGoodsTotal(query);
	}

	
	@Override
	@Transactional
	public Result auditGoods(GoodsAuditReq req) {
		Result rs = Result.getSuccessResult();
		PlanGoods goods = planGoodsMapper.selectByPrimaryKey(req.getId());
		if(goods != null){
			//0 待审核；1-审核通过；2-发单中；3-已完成  4-已关闭 9-审核失败'
			PlanGoods upt = new PlanGoods();
			upt.setId(goods.getId());
			byte sa = goods.getStatus();
			byte ty = req.getAudType();
			if(ty == 1 || ty == 9){
				//运单审核通过 或审核不通过
				if(sa==0){
					upt.setStatus(ty);
					upt.setIsfamily(req.getIsfamily());
					if(ty == 1 && req.getMessageType() != 0){
						//审核通过
						if(req.getMessageType() != 0){
							//选择消息推送 且不为平台推送
							try {
								MessagePushReq mess = new MessagePushReq();
								mess.setMessageType((byte)1);
								mess.setChannel(req.getMessageType());
								mess.setCreateTime(System.currentTimeMillis());
								mess.setGoods(goods);
								messagePushService.save(mess);
							} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
								e.printStackTrace();
							}
						}
					}
					planGoodsMapper.updateByPrimaryKeySelective(upt);
				}else{
					rs.setCode("1");
					rs.setError("不合法的审核状态");
				}
			}else if(ty == 4){
				//关闭
				if(sa==2||sa==3){
					upt.setStatus(ty);
					planGoodsMapper.updateByPrimaryKeySelective(upt);
				}else{
					rs.setCode("1");
					rs.setError("不合法的关闭状态");
				}
			}else{
				rs.setCode("1");
				rs.setError("不合法的申请状态");
			}
		}
		return rs;
	}
	
	@Override
	@Transactional
	public Result goodsToPlan(GoodsTOPlanReq req) {
		Result rs = Result.getSuccessResult();
		PlanGoods goods = planGoodsMapper.selectByPrimaryKey(req.getGoodsid());
		if(goods.getStatus()!=(byte)-1 && 
				goods.getStatus()!=(byte)0 && 
						goods.getStatus()!=(byte)4 && 
								goods.getStatus()!=(byte)9){
			//-1-已删除；0 待审核；1-审核通过；2-发单中；3-已完成  4-已关闭 9-审核失败',
			if(req != null){
				Plan plan = saveGoodsPlan(req, goods);
				PlanGoods upt = new PlanGoods();
				upt.setId(goods.getId());
				Double completed = req.getWeight();
				Double tot = goods.getTotalplanned();//计划总量
				if(null != goods.getCompleted()){
					completed = completed + goods.getCompleted();
				}
				upt.setCompleted(completed);//计划完成量
				if(completed >= tot){
					upt.setStatus((byte)3);
				}else{
					upt.setStatus((byte)2);
				}
				planGoodsMapper.updateByPrimaryKeySelective(upt);
				if(req.getMessageType() != 0){
					//选择消息推送 且不为平台推送
					try {
						MessagePushReq mess = new MessagePushReq();
						mess.setMessageType((byte)2);//货运计划
						mess.setChannel(req.getMessageType());
						mess.setCreateTime(System.currentTimeMillis());
						mess.setGoods(plan);
						messagePushService.save(mess);
					} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
						e.printStackTrace();
					}
				}
				
			}
		}else{
			rs.setCode("1");
			rs.setError("错误的货物状态");
		}
		return rs;
	}

	private Plan saveGoodsPlan(GoodsTOPlanReq req, PlanGoods goods) {
		Plan plan =new Plan();
		String id = UUIDUtil.getId();
		//车主信息
		plan.setTotalplanned(req.getWeight());
		plan.setVehicleownerid(req.getVenderid());
		plan.setPrice(req.getPrice());
		plan.setDesc3(req.getIsfamily());//是否公开
		MemberVo vender =memberVoService.get(req.getVenderid());
		plan.setVehicleownername(vender.getRealName());
		plan.setVehicleownerphone(vender.getCellphone());
		
		plan.setId(id);
		plan.setDesc4(goods.getId());//货源id
		plan.setPlancode(codeGenDao.codeGen(1));
		//货物信息
		plan.setCargoid(goods.getCargoid());
		plan.setCargoname(goods.getCargoname());
		plan.setMeasure(goods.getMeasure());
		plan.setCargocode(goods.getCargocode());
		//策略信息
		plan.setFreightid(goods.getFreightid());
		plan.setPriceunits(goods.getPriceunits());
		plan.setTallage(goods.getTallage());
		plan.setFreightname(goods.getFreightname());
		plan.setOrgid(goods.getOrgid());
		//路径信息
		plan.setRouteid(goods.getRouteid());
		plan.setSendperson(goods.getSendperson());
		plan.setSendpersonphone(goods.getSendpersonphone());
		plan.setReceiveperson(goods.getReceiveperson());
		plan.setReceivepersonphone(goods.getReceivepersonphone());
		plan.setDistance(goods.getDistance());
		plan.setStartcity(goods.getStartcity());
		plan.setEndcity(goods.getEndcity());
		
		//初始化信息
		plan.setVenderdelflag((byte)0);
		plan.setOwnerdelflag((byte)0);
		//自定义属性
		
		plan.setStarttime(goods.getStarttime());
		plan.setEndtime(goods.getEndtime());
		//合同 1:合同  0自由  价格信息
		plan.setType(goods.getType());
		//联系人信息
		plan.setTelephone(goods.getTelephone());
		plan.setLinkman(goods.getLinkman());
		//备注字段
		plan.setCreatetime(System.currentTimeMillis());
		plan.setCreator(req.getUserId());
		plan.setModifier(req.getUserId());
		plan.setPathID(req.getUserId());
		plan.setModifytime(System.currentTimeMillis());
		plan.setStatus(PlanStatusEnum.NEW.getStatus());
		plan.setIsfamily((byte)0);
		plan.setIsAppoint("0");
		//发货方收货方
		plan.setConsigneeMerchant(goods.getConsigneemerchant());
		plan.setShipperMerchant(goods.getShippermerchant());
		//发货人
		plan.setSendperson(goods.getSendperson());
		plan.setSendpersonphone(goods.getSendpersonphone());
		//收货人
		plan.setReceiveid(goods.getReceiveid());
		plan.setReceiveperson(goods.getReceiveperson());
		plan.setReceivepersonphone(goods.getReceivepersonphone());
		//支付对象 1-司机 2-车主
		plan.setPayment(goods.getPayment());
		
		planMapper.insert(plan);
		
		MemberVo owner = new MemberVo();
		owner.setUserName("调度中心管理员"+req.getUserId());
		owner.setId(req.getUserId());
		sendMsgInside(Arrays.asList(new String[]{plan.getPlancode(),owner.getRealName()}), plan.getId(), owner, vender, MessageCodeEnum.PLAN_2VENDER_CREATE);
		
		return plan;
	}
	
	//发送站内信
		private void sendMsgInside(List<String> params,String keyId,MemberVo sender,MemberVo receiver,MessageCodeEnum codeEnum){
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
				String uri ="/trwuliu/planvender/detail?id="+keyId;
				req.setURI(uri);
				try {
					messageService.sendMessageInside(req);
				} catch (Exception e) {
				
				}
			}
		}
	
	@Override
	public Result findPlanGoodsId(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		PlanGoodsResp resp = null;
		PlanGoods plan = planGoodsMapper.selectByPrimaryKey(id);
//		if(plan.getCompleted() == null){
//			plan.setCompleted((double) 0);
//		}
		resp =copyPropertie(plan);
		FileFreight fileFreight = freightMapper.selectByPrimaryKey(resp.getFreightid());
		resp.setOrgname(fileFreight.getOrganizationname());
		if(StringUtils.isNotBlank(plan.getConsigneemerchant())){
			resp.setConsignee(merchantService.findByid(plan.getConsigneemerchant()).getName());
		}
		if(StringUtils.isNotBlank(plan.getShippermerchant())){
			resp.setShipper(merchantService.findByid(plan.getShippermerchant()).getName());
		}
		rs.setData(resp);
		return rs;
	}
	private PlanGoodsResp copyPropertie(PlanGoods plan)throws Exception{
		PlanGoodsResp resp =null;
		if( plan !=null ){
			resp = new PlanGoodsResp();
			PropertyUtils.copyProperties(resp, plan);
			if( StringUtils.isNotBlank(resp.getOrgid()) ){
				OrganizationResp ss =orgService.findOne(resp.getOrgid());
				if( ss !=null ){
					resp.setOrgname(ss.getOrganizationname());
				}
			}
			if( StringUtils.isNotBlank(resp.getCreator()) ){
				MemberVo member =memberVoService.get(resp.getCreator());
				if( member !=null ){
					resp.setOwnerName(member.getRealName());
					resp.setOwnerCellphone(member.getCellphone());
				}
			}
		}
		return resp;
	}
	@Override
	public PaginationVO<SelectPlanGoodsResp> select(PlanGoodsReq req) throws Exception {
		PaginationVO<SelectPlanGoodsResp> page = new PaginationVO<SelectPlanGoodsResp>();
		PlanGoods query = new PlanGoods();
		PropertyUtils.copyProperties(query, req);
		if(req.getPageNo()!=null){
			query.setPageNo(req.getPageNo()*req.getPageSize());
			query.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		long total = planGoodsMapper.selectByCount(query);
		List<PlanGoods> list = planGoodsMapper.selectByCondition(query);
		page.setTotal(total);
		page.setList(copyProperties2(list));
		return page;
	}
	
	@Override
	public PaginationVO<SelectAppBillResp> appBillSelect(PlanGoodsReq req) throws Exception {
		// TODO Auto-generated method stub
		PaginationVO<SelectAppBillResp> page = new PaginationVO<SelectAppBillResp>();
		Bill bill = new Bill();
		if(req.getPageNo()!=null){
			bill.setStart((req.getPageNo()-1)*req.getPageSize());
			bill.setLimit(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		bill.setStartOc(req.getStartOc());
		bill.setStartOp(req.getStartOp());
		bill.setEndOc(req.getEndOc());
		bill.setEndOp(req.getEndOp());
		bill.setCargoname(req.getCargoname());
		List<Bill> list = billMapper.selectPublic(bill);
		long a = billMapper.countPublic(bill);
		page.setList(billCopyProperties2(list));
		page.setTotal(a);
		return page;
	}
	
	private List<SelectAppBillResp> billCopyProperties2(List<Bill> list)
			throws Exception {
		List<SelectAppBillResp> resp = new ArrayList<SelectAppBillResp>();
		for(Bill bill : list){
			SelectAppBillResp sp = new SelectAppBillResp();
			String routeId = bill.getRouteid();
			RoutePosition post = fileRouteService.getPositionByRouteId(routeId);
			
			sp.setId(bill.getId());
			
			sp.setVehicleno(bill.getVehicleno());
			sp.setVehicletypename(bill.getVehicletypename());
			sp.setWeight(bill.getWeight());
			
			sp.setCargoname(bill.getCargoname());
			sp.setCreatetime(bill.getCreatetime());
			sp.setDistance(bill.getDistance());
			sp.setMeasure(bill.getDesc1());
			sp.setBillcode(bill.getWaybillno());
			sp.setPrice(bill.getPrice());
			sp.setPriceunits(bill.getPriceunits());
			sp.setStarttime(bill.getStarttime());
			sp.setEndtime(bill.getEndtime());
			
			sp.setStartCity(post.getStartCity());
			sp.setStartLat(post.getStartLat());
			sp.setStartLon(post.getStartLon());
			sp.setStartName(post.getStartName());
			
			sp.setEndCity(post.getEndCity());
			sp.setEndLat(post.getEndLat());
			sp.setEndLon(post.getEndLon());
			sp.setEndName(post.getEndName());
			
			resp.add(sp);
		}
		return resp;
	}

	@Override
	public PaginationVO<SelectAppPlanResp> appPlanSelect(PlanGoodsReq req) throws Exception {
		PaginationVO<SelectAppPlanResp> page = new PaginationVO<SelectAppPlanResp>();
		Plan query = new Plan();
		if(req.getPageNo()!=null){
			query.setStart((req.getPageNo()-1)*req.getPageSize());
			query.setLimit(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		query.setCargoname(req.getCargoname());
		query.setDesc3("1");
		query.setStartOc(req.getStartOc());
		query.setStartOp(req.getStartOp());
		query.setEndOc(req.getEndOc());
		query.setEndOp(req.getEndOp());
		List<Plan> list = planMapper.selectByCondition(query);
		long a = planMapper.countByCondition(query);
		page.setList(planCopyProperties2(list));
		page.setTotal(a);
		return page;
	}
	
	private List<SelectAppPlanResp> planCopyProperties2(List<Plan> list)
			throws Exception {
		List<SelectAppPlanResp> resp = new ArrayList<SelectAppPlanResp>();
		for(Plan goods : list){
			SelectAppPlanResp sp = new SelectAppPlanResp();
			String routeId = goods.getRouteid();
			RoutePosition post = fileRouteService.getPositionByRouteId(routeId);
			PropertyUtils.copyProperties(sp, goods);
			
			sp.setVender(goods.getVehicleownername());
			sp.setVenderPhone(goods.getVehicleownerphone());
			
			sp.setStartCity(post.getStartCity());
			sp.setStartLat(post.getStartLat());
			sp.setStartLon(post.getStartLon());
			sp.setStartName(post.getStartName());
			
			sp.setEndCity(post.getEndCity());
			sp.setEndLat(post.getEndLat());
			sp.setEndLon(post.getEndLon());
			sp.setEndName(post.getEndName());
			
			resp.add(sp);
		}
		return resp;
	}
	
	@Override
	public PaginationVO<SelectAppPlanGoodsResp> appSelect(PlanGoodsReq req) throws Exception {
		PaginationVO<SelectAppPlanGoodsResp> page = new PaginationVO<SelectAppPlanGoodsResp>();
		PlanGoods query = new PlanGoods();
		PropertyUtils.copyProperties(query, req);
		if(req.getPageNo()!=null){
			query.setPageNo((req.getPageNo()-1)*req.getPageSize());
			query.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		long total = planGoodsMapper.selectByCount(query);
		List<PlanGoods> list = planGoodsMapper.selectByCondition(query);
		page.setTotal(total);
		page.setList(appCopyProperties2(list));
		return page;
	}
	private List<SelectAppPlanGoodsResp> appCopyProperties2(List<PlanGoods> list)
			throws Exception {
		List<SelectAppPlanGoodsResp> resp = new ArrayList<SelectAppPlanGoodsResp>();
		for(PlanGoods goods : list){
			SelectAppPlanGoodsResp sp = new SelectAppPlanGoodsResp();
			String routeId = goods.getRouteid();
			RoutePosition post = fileRouteService.getPositionByRouteId(routeId);
			PropertyUtils.copyProperties(sp, goods);
			
			sp.setStartCity(post.getStartCity());
			sp.setStartLat(post.getStartLat());
			sp.setStartLon(post.getStartLon());
			sp.setStartName(post.getStartName());
			
			sp.setEndCity(post.getEndCity());
			sp.setEndLat(post.getEndLat());
			sp.setEndLon(post.getEndLon());
			sp.setEndName(post.getEndName());
			Double residual = goods.getTotalplanned();
			if(goods.getCompleted()!=null){
				residual = residual - goods.getCompleted();
			}
			if(residual > 0){
				sp.setResidual(residual);
			}else {
				sp.setResidual((double)0);
			}
			resp.add(sp);
		}
		return resp;
	}

	private List<SelectPlanGoodsResp> copyProperties2(List<PlanGoods> list)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<SelectPlanGoodsResp> resp = new ArrayList<SelectPlanGoodsResp>();
		for(PlanGoods goods : list){
			SelectPlanGoodsResp sp = new SelectPlanGoodsResp();
			PropertyUtils.copyProperties(sp, goods);
			resp.add(sp);
		}
		return resp;
	}
	
	@Override
	public Result savePlanGoods(PlanSaveReq req) {
		Result rs =Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getCurruId() ) && StringUtils.isNotBlank(req.getFreightid()) && StringUtils.isNotBlank(req.getRouteid()) && StringUtils.isNotBlank(req.getCargoid())){
			FileFreight fileFreight = freightMapper.selectByPrimaryKey(req.getFreightid());
			FileRoute fileRoute =routeMapper.selectByPrimaryKey(req.getRouteid());
			FileOrgCargo cargo =orgCargoMapper.selectByPrimaryKey(req.getCargoid());
			if( fileFreight !=null &&  fileRoute !=null && cargo!=null){
				//保存货运信息
				saveBean(req, fileFreight, fileRoute, cargo);
				//保存模版
				if( StringUtils.equals("1", req.getIstemplate()) ){
//					planTempateDao.save(plan);
				}
			}else{
				rs.setErrorCode(ErrorCode.PARAM_ERROR);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}

	private PlanGoods saveBean(PlanSaveReq req, FileFreight fileFreight, FileRoute fileRoute, FileOrgCargo cargo) {
		PlanGoods plan =new PlanGoods();
		String id = UUIDUtil.getId();
		plan.setId(id);
		plan.setPlancode(codeGenDao.codeGen(1));
		//通过策略以及路径形成的信息
		setPlanData(fileFreight, fileRoute, cargo, plan);
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
		plan.setStatus((byte)0);//0 待审核
		plan.setIsfamily((byte)1);//1-展示
		
		plan.setIsappoint("0");
		plan.setPathid(req.getCurruId());
		//发货方收货方
		plan.setConsigneemerchant(req.getConsigneeMerchant());
		plan.setShippermerchant(req.getShipperMerchant());
		//发货人
		plan.setSendperson(req.getShipperName());
		plan.setSendpersonphone(req.getShipperTell());
		//收货人
		plan.setReceiveid(req.getReceiveid());
		plan.setReceiveperson(req.getConsigneeName());
		plan.setReceivepersonphone(req.getConsigneeTell());
		//支付对象 1-司机 2-车主
		plan.setPayment(req.getPayment());
		
		planGoodsMapper.insert(plan);
		return plan;
	}
	
	private void setPlanData( FileFreight fileFreight, FileRoute fileRoute, FileOrgCargo cargo,
			PlanGoods plan) {
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
		plan.setFreightname(fileFreight.getFreightName());
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
}
