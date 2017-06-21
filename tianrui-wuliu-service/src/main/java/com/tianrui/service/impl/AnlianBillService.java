package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.req.admin.anlian.AnlianShipmentReq;
import com.tianrui.api.req.admin.anlian.LinesReq;
import com.tianrui.api.req.admin.anlian.OrdersReq;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.req.front.bill.AnlianBillSaveReq;
import com.tianrui.api.req.front.bill.AnlianBillUpdateReq;
import com.tianrui.api.resp.front.bill.AnlianBillResp;
import com.tianrui.api.resp.front.cargoplan.PlanRouteResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileCargo;
import com.tianrui.service.admin.bean.FileOrgCargo;
import com.tianrui.service.admin.bean.FilePositoin;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.bean.Merchant;
import com.tianrui.service.admin.mapper.FileCargoMapper;
import com.tianrui.service.admin.mapper.FileOrgCargoMapper;
import com.tianrui.service.admin.mapper.FilePositoinMapper;
import com.tianrui.service.admin.mapper.FileRouteMapper;
import com.tianrui.service.admin.mapper.MerchantMapper;
import com.tianrui.service.bean.AnlianDict;
import com.tianrui.service.bean.BillAnlianPosition;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.bean.anlian.AnlianBill;
import com.tianrui.service.mapper.AnlianBillMapper;
import com.tianrui.service.mapper.AnlianDictMapper;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;
import com.tianrui.service.mapper.VehicleTicketMapper;
import com.tianrui.service.mongo.BillAnlianPositionDao;
@Service
public class AnlianBillService implements IAnlianBillService{

	@Autowired
	PlanMapper planMapper;
	@Autowired
	FileRouteMapper routeMapper;
	@Autowired
	FilePositoinMapper positionMapper;
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	@Autowired
	VehicleTicketMapper vehicleTicketMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	SystemMemberInfoMapper systemMemberInfoMapper;
	@Autowired
	AnlianDictMapper anlianDictMapper;
	@Autowired
	FileCargoMapper fileCargoMapper;
	@Autowired
	FileOrgCargoMapper fileOrgCargoMapper;
	@Autowired
	IAnlianService anlianService;
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	@Autowired
	AnlianBillMapper anlianBillMapper;
	@Autowired
	BillAnlianPositionDao billAnlianPositionDao;
	@Autowired
	MerchantMapper merchantMapper;
	
	@Override
	public Result alBillSave(AnlianBillSaveReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		Plan plan = planMapper.selectByPrimaryKey(req.getPlanid());
		FileRoute route = routeMapper.selectByPrimaryKey(plan.getRouteid());
		//始发地id FilePositoin 取 oc
		FilePositoin sfd = positionMapper.selectByPrimaryKey(route.getOpositionid());
		//目的地id FilePositoin 取 oc
		FilePositoin mdd = positionMapper.selectByPrimaryKey(route.getDpositionid());
//		//车辆司机关系表
//		VehicleDriver vd = vehicleDriverMapper.selectByPrimaryKey(req.getVehicleDrvierid());
		//车辆信息
		MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(req.getVehicleid());
		//司机信息
		SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getDriverid());
		
		//++++++++++++++++++++++++++++++
		//拼装安联所需数据
		AnlianShipmentReq shipment = new AnlianShipmentReq();
		//车牌号
		shipment.setCph(vehicle.getVehicleprefix()+vehicle.getVehicleno());
		//安联司机会员号
		shipment.setSj(member.getAldriverid());
		//运单总质量
		shipment.setZzl(req.getWeight());
		//总体积
		shipment.setZtj(req.getVolume());
		//总数量
		shipment.setZsl(req.getSize());
		
		Double siz = Double.valueOf(req.getSize());
		Double pric = Double.valueOf(req.getPrice());
		Double yf = siz*pric;
		//运费
		shipment.setYf(String.valueOf(yf));
		//计费方式 10-重量 20-体积 默认按重量计算
		shipment.setJffs("10");
		//始发地
		shipment.setQycs(sfd.getOc());
		//目的城市
		shipment.setMdcs(mdd.getOc());
		//距离
		shipment.setLc(route.getDistance().toString());
		//要求提货日期
		shipment.setYqthrq(req.getBillStartTime());
		//要求到货日期
		shipment.setYqdhrq(req.getBillEndTime());
		String uu = UUIDUtil.getId();
		shipment.setPzdh(uu.substring(0, 6));
		
		Merchant mchant = merchantMapper.selectByPrimaryKey(plan.getShipperMerchant());
		
		OrdersReq order = new OrdersReq();
		// 客户代码
		if(mchant!=null){
			order.setKhdm(mchant.getCode());
		}
		//提货地址
		order.setThdz(plan.getStartcity());
		//收货地址
		order.setShdz(plan.getEndcity());
		//收货人
		order.setShr(plan.getReceiveperson());
		//联系手机
		order.setLxsj(plan.getReceivepersonphone());
		//加急 Y- 加急 N-不加急
		order.setJj("Y");
		
		order.setDdh(uu.substring(5,12));
		LinesReq lines = new LinesReq();
		//货品名称
		lines.setHpmc(plan.getCargoname());
		//数量
		lines.setSl(req.getWeight());
		//单位
		lines.setDw("吨");
		
		FileOrgCargo orgcargo = fileOrgCargoMapper.selectByPrimaryKey(plan.getCargoid());
		FileCargo cargo = fileCargoMapper.selectByPrimaryKey(orgcargo.getCargoid());
		AnlianDict record = new AnlianDict();
		record.setType("cargo");
		record.setWlname(cargo.getCargotype());
		List<AnlianDict> list = anlianDictMapper.selectByCondition(record);
		if(list.size()==1){
			//货品属性
			lines.setHpsx(list.get(0).getAlcode());
		}
		List<OrdersReq> ol = new ArrayList<OrdersReq>();
		ol.add(order);
		List<LinesReq> ll = new ArrayList<LinesReq>();
		ll.add(lines);
		
		order.setLines(ll);
		shipment.setOrders(ol);
		//TODO
//		rs = anlianService.shipment(shipment);
//		if(rs.getCode().equals("000000")){
//			anlianBillInsert(shipment,rs.getData().toString(),req);
//		}
		anlianBillInsert(shipment,"CS0000000",req);
		return rs;
	}
	/** 运单本地保存
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException */
	public void anlianBillInsert(AnlianShipmentReq shipment,String billNo,AnlianBillSaveReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		AnlianBill bill = new AnlianBill();
		bill.setId(UUIDUtil.getId());
		bill.setBillno(billNo);
		bill.setDriverid(req.getDriverid());
		bill.setOwnerid(req.getOwnerid());
		bill.setVenderid(req.getVenderid());
		bill.setDesc1(req.getPlanid());
		bill.setPayment(req.getPayment());
		bill.setReceive_memberid(req.getReceive_memberid());
		PropertyUtils.copyProperties(bill, shipment);
		List<OrdersReq> lo = shipment.getOrders();
		for(OrdersReq r : lo){
			PropertyUtils.copyProperties(bill, r);
			List<LinesReq> l = r.getLines();
			for(LinesReq v : l){
				PropertyUtils.copyProperties(bill, v);
			}
		}
		bill.setCreatetime(System.currentTimeMillis());
		anlianBillMapper.insert(bill);
	}
	@Override
	public PaginationVO<AnlianBillResp> find(AnlianBillFindReq req) throws Exception {
		PaginationVO<AnlianBillResp> pv = new PaginationVO<AnlianBillResp>();
		AnlianBill bill = new AnlianBill();
		PropertyUtils.copyProperties(bill, req);
		if(req.getPageNo()!=null){
			bill.setStart(req.getPageNo()*req.getPageSize());
			bill.setLimit(req.getPageSize());
			pv.setPageNo(req.getPageNo());
			pv.setPageSize(req.getPageSize());
		}
		List<AnlianBill> list = anlianBillMapper.selectByCondition(bill);
		long a = anlianBillMapper.selectByCount(bill);
		List<AnlianBillResp> rs =copy(list);
		//道路名称
		setRouteNames(rs);
		pv.setList(rs);
		pv.setTotal(a);
		return pv;
	}
	@Override
	public List<AnlianBillResp> findAll(AnlianBillFindReq req) throws Exception {
		AnlianBill bill = new AnlianBill();
		PropertyUtils.copyProperties(bill, req);
		List<AnlianBill> list = anlianBillMapper.selectByCondition(bill);
		return copy(list);
	}

	@Override
	public Result findByid(AnlianBillFindReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		AnlianBill bill = anlianBillMapper.selectByPrimaryKey(req.getId());
		
		AnlianBillResp resp = new AnlianBillResp();
		PropertyUtils.copyProperties(resp, bill);
		resp.setStatus(bill.getDesc4());
		rs.setData(resp);
		return rs;
	}
	
	public List<AnlianBillResp> copy(List<AnlianBill> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<AnlianBillResp> r = new ArrayList<AnlianBillResp>();
		for(AnlianBill b :list){
			AnlianBillResp s = new AnlianBillResp();
			PropertyUtils.copyProperties(s, b);
			r.add(s);
		}
		return r;
	}
	@Override
	public Result findPosition(AnlianBillFindReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		AnlianBill bill = anlianBillMapper.selectByPrimaryKey(req.getId());
		if(bill != null){
			List<BillAnlianPosition> list = billAnlianPositionDao.findPosition(bill.getBillno());
			rs.setData(list);
		}else{
			rs.setCode("1");
			rs.setError("请输入正确id");
		}
		return rs;
	}
	
	//开票运单增加路线名称
	private void setRouteNames(List<AnlianBillResp> list){
		if( CollectionUtils.isNotEmpty(list) ){
			List<String> planIds = new ArrayList<String>();
			//拼装计划id
			for(AnlianBillResp item :list ){
				if( StringUtils.isNotBlank(item.getDesc1()) ){
					planIds.add(item.getDesc1());
				}
			}
			//根据计划id查询路线信息
			if( CollectionUtils.isNotEmpty(planIds) ){
				List<PlanRouteResp> db =routeMapper.selectByPlanIds(planIds);
				if(CollectionUtils.isNotEmpty(db)  ){
					for(PlanRouteResp item  :db){
						for(AnlianBillResp item2:list ){
							if( StringUtils.isNotBlank(item2.getDesc1()) && StringUtils.equals(item2.getDesc1(), item.getPlanId()) ){
								item2.setRouteName(item.getRouteName());
							}
						}
					}
				}
			}
		}
	}
	@Override
	public Result update(AnlianBillUpdateReq req) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		AnlianBill record = new AnlianBill();
		record.setId(req.getId());
		record.setDesc3(req.getDesc3());
		record.setDesc4(req.getDesc4());
		anlianBillMapper.updateByPrimaryKeySelective(record);
		return rs;
	}
}
