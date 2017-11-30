package com.tianrui.service.impl.planGoods;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.planGoods.IPlanGoodsService;
import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.resp.goods.PlanGoodsResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.PlanStatusEnum;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.bean.FileOrgCargo;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.impl.OrganizationService;
import com.tianrui.service.admin.mapper.FileFreightMapper;
import com.tianrui.service.admin.mapper.FileOrgCargoMapper;
import com.tianrui.service.admin.mapper.FileRouteMapper;
import com.tianrui.service.bean.PlanGoods;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.impl.MemberVoService;
import com.tianrui.service.impl.MessageService;
import com.tianrui.service.mapper.PlanGoodsMapper;
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
