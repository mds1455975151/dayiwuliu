package com.tianrui.service.impl.planGoods;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.admin.intf.IMerchantService;
import com.tianrui.api.intf.planGoods.IPlanGoodsService;
import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.req.goods.GoodsAuditReq;
import com.tianrui.api.req.goods.GoodsTOPlanReq;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.req.money.MessagePushReq;
import com.tianrui.api.resp.admin.OrganizationResp;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.api.resp.goods.PlanGoodsResp;
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
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.PlanGoods;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.impl.MemberVoService;
import com.tianrui.service.impl.MessageService;
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

	@Override
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
					upt.setIsfamily((byte) 0);
					if(ty == 1 && req.getMessageType() != 0){
						//审核通过
						if(req.getMessageType() != 0 && req.getMessageType() != 9){
							//选择消息推送 且不为平台推送
							try {
								MessagePushReq mess = new MessagePushReq();
								mess.setMessageType((byte)1);
								mess.setChannel(req.getMessageType());
								mess.setCreateTime(System.currentTimeMillis());
								mess.setGoods(goods);
								messagePushService.save(mess);
							} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						}else if(req.getMessageType() == 9){
							//选择平台推送
							upt.setIsfamily((byte) 1);
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
	public Result goodsToPlan(GoodsTOPlanReq goods) {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		PlanGoods req = planGoodsMapper.selectByPrimaryKey(goods.getGoodsid());
		if(req.getStatus()!=(byte)-1 && 
				req.getStatus()!=(byte)0 && 
				 req.getStatus()!=(byte)4 && 
				  req.getStatus()!=(byte)9){
			//-1-已删除；0 待审核；1-审核通过；2-发单中；3-已完成  4-已关闭 9-审核失败',
			if(req != null){
				saveGoodsPlan(goods, req);
				PlanGoods upt = new PlanGoods();
				upt.setId(req.getId());
				Double completed = goods.getWeight();
				Double tot = req.getTotalplanned();//计划总量
				if(null != req.getCompleted()){
					completed = completed + req.getCompleted();
				}
				upt.setCompleted(completed);//计划完成量
				if(completed >= tot){
					upt.setStatus((byte)3);
				}else{
					upt.setStatus((byte)2);
				}
				planGoodsMapper.updateByPrimaryKeySelective(upt);
			}
		}else{
			rs.setCode("1");
			rs.setError("错误的货物状态");
		}
		return rs;
	}

	private void saveGoodsPlan(GoodsTOPlanReq goods, PlanGoods req) {
		Plan plan =new Plan();
		String id = UUIDUtil.getId();
		//车主信息
		plan.setTotalplanned(goods.getWeight());
		plan.setVehicleownerid(goods.getVenderid());
		plan.setPrice(goods.getPrice());
		MemberVo vender =memberVoService.get(goods.getVenderid());
		plan.setVehicleownername(vender.getRealName());
		plan.setVehicleownerphone(vender.getCellphone());
		
		plan.setId(id);
		plan.setDesc4(req.getId());
		plan.setPlancode("adminppp");
//			plan.setPlancode(codeGenDao.codeGen(1));
		//货物信息
		plan.setCargoid(req.getCargoid());
		plan.setCargoname(req.getCargoname());
		plan.setMeasure(req.getMeasure());
		plan.setCargocode(req.getCargocode());
		//策略信息
		plan.setFreightid(req.getFreightid());
		plan.setPriceunits(req.getPriceunits());
		plan.setTallage(req.getTallage());
		plan.setFreightname(req.getFreightname());
		plan.setOrgid(req.getOrgid());
		//路径信息
		plan.setRouteid(req.getRouteid());
		plan.setSendperson(req.getSendperson());
		plan.setSendpersonphone(req.getSendpersonphone());
		plan.setReceiveperson(req.getReceiveperson());
		plan.setReceivepersonphone(req.getReceivepersonphone());
		plan.setDistance(req.getDistance());
		plan.setStartcity(req.getStartcity());
		plan.setEndcity(req.getEndcity());
		
		//初始化信息
		plan.setVenderdelflag((byte)0);
		plan.setOwnerdelflag((byte)0);
		//自定义属性
		
		plan.setStarttime(req.getStarttime());
		plan.setEndtime(req.getEndtime());
		//合同 1:合同  0自由  价格信息
		plan.setType(req.getType());
		//联系人信息
		plan.setTelephone(req.getTelephone());
		plan.setLinkman(req.getLinkman());
		//备注字段
		plan.setCreator(goods.getUserId());
		plan.setCreatetime(System.currentTimeMillis());
		plan.setModifier(goods.getUserId());
		plan.setModifytime(System.currentTimeMillis());
		plan.setStatus(PlanStatusEnum.NEW.getStatus());
		plan.setIsfamily((byte)0);
		plan.setIsAppoint("0");
		
		plan.setPathID(goods.getUserId());
		//发货方收货方
		plan.setConsigneeMerchant(req.getConsigneemerchant());
		plan.setShipperMerchant(req.getShippermerchant());
		//发货人
		plan.setSendperson(req.getSendperson());
		plan.setSendpersonphone(req.getSendpersonphone());
		//收货人
		plan.setReceiveid(req.getReceiveid());
		plan.setReceiveperson(req.getReceiveperson());
		plan.setReceivepersonphone(req.getReceivepersonphone());
		//支付对象 1-司机 2-车主
		plan.setPayment(req.getPayment());
		
		planMapper.insert(plan);
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
	public PaginationVO<PlanGoodsResp> select(PlanGoodsReq req) throws Exception {
		PaginationVO<PlanGoodsResp> page = new PaginationVO<PlanGoodsResp>();
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

	private List<PlanGoodsResp> copyProperties2(List<PlanGoods> list)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<PlanGoodsResp> resp = new ArrayList<PlanGoodsResp>();
		for(PlanGoods goods : list){
			PlanGoodsResp sp = new PlanGoodsResp();
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
//				plan.setPlancode(codeGenDao.codeGen(1));
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
