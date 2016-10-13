package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IFreightInfoService;
import com.tianrui.api.intf.IPayInvoiceDetailService;
import com.tianrui.api.req.front.pay.PayInvoiceDetailQueryReq;
import com.tianrui.api.req.front.pay.PayInvoiceDetailSaveReq;
import com.tianrui.api.req.front.pay.PayInvoiceGenalReq;
import com.tianrui.api.resp.pay.PayInvoiceDetailResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.BillStatusEnum;
import com.tianrui.common.enums.PayStatusEnum;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileCargo;
import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.bean.FileOrgCargo;
import com.tianrui.service.admin.mapper.FileCargoMapper;
import com.tianrui.service.admin.mapper.FileOrgCargoMapper;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.BillTrack;
import com.tianrui.service.bean.PayInvoice;
import com.tianrui.service.bean.PayInvoiceDetail;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.PayInvoiceDetailMapper;
import com.tianrui.service.mapper.PayInvoiceMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mongo.BillTrackDao;
import com.tianrui.service.mongo.CodeGenDao;
import com.tianrui.service.util.TimeUtils;
@Service
public class PayInvoiceDetailService implements IPayInvoiceDetailService {
	@Autowired
	PayInvoiceDetailMapper  payInvoiceDetailMapper;
	@Autowired
	PayInvoiceMapper payInvoiceMapper;
	@Autowired
	BillMapper billMapper;
	@Autowired
	PlanMapper planMapper;
	@Autowired
	MemberVoService memberVoService;
	@Autowired
	FileCargoMapper fileCargoMapper;
	@Autowired
	FileOrgCargoMapper fileOrgCargoMapper;
	@Autowired
	CodeGenDao codeGenDao;
	@Autowired
	BillTrackDao billTrackDao;
	@Autowired
	IFreightInfoService freightInfoService;
	@Override
	public Result saveByBillPriceConfirm(PayInvoiceDetailSaveReq req,String orgid) throws Exception {
		Result  rs =Result.getSuccessResult();
		if( req !=null && StringUtils.isNotEmpty(req.getBillId()) ){
			//运单信息
			Bill bill =billMapper.selectByPrimaryKey(req.getBillId());
			if(!orgid.equals(bill.getOrgid())){
				rs.setErrorCode(ErrorCode.PAY_DATA_NOT_ADMIN);
			}else if("1".equals(bill.getIsClearing())){
				rs.setErrorCode(ErrorCode.PAY_DATA_NOT_ISCLEAN);
			}else if( bill !=null ){
				PayInvoiceDetail payInvoiceDetail =new PayInvoiceDetail();
				payInvoiceDetail.setId(UUIDUtil.getId());
				//状态信息
				payInvoiceDetail.setIsInvoice((byte)0);
				payInvoiceDetail.setCreator(req.getCurruId());
				payInvoiceDetail.setCreatetime(System.currentTimeMillis());
				payInvoiceDetail.setModifier(req.getCurruId());
				payInvoiceDetail.setModifytime(System.currentTimeMillis());
				
				/**
				 * 货主信息
				 */
				MemberVo owner =memberVoService.get(bill.getOwnerid());
				payInvoiceDetail.setOwnerId(bill.getOwnerid());
				payInvoiceDetail.setOrgid(owner.getOrgid());
				payInvoiceDetail.setOrgName(owner.getCompanyName());
				/**
				 * 运单信息
				 */
				payInvoiceDetail.setBillId(bill.getId());
				payInvoiceDetail.setBillCode(bill.getWaybillno());
				payInvoiceDetail.setBillWeight(bill.getTrueweight());
				payInvoiceDetail.setBillTotalPrice(bill.getPrice()*bill.getWeight());
				payInvoiceDetail.setSignTime(TimeUtils.LongZoString(bill.getUnloadtime()));
				
				/**
				 * 运价策略
				 */
				Plan plan = planMapper.selectByPrimaryKey(bill.getPlanid());
				FileFreight freight = getFileFreight(plan.getFreightid(),bill.getUnloadtime());
				payInvoiceDetail.setBillPrice(freight.getPrice());
				payInvoiceDetail.setTaxRate(freight.getTallage().toString());
				
				/**
				 * 货物信息
				 */
//				FileCargo cargo=fileCargoMapper.selectByPlanId(plan.getId());
				FileOrgCargo orgcargo = fileOrgCargoMapper.selectByPrimaryKey(plan.getCargoid());
				FileCargo cargo=fileCargoMapper.selectByPrimaryKey(orgcargo.getCargoid());
				payInvoiceDetail.setCargoId(cargo.getId());
				payInvoiceDetail.setCargoCode(cargo.getCargono());
				payInvoiceDetail.setCargoName(bill.getCargoname());
				//货物发票类型
				payInvoiceDetail.setInvoiceType(cargo.getDesc1());
				payInvoiceDetail.setInvoiceTypeName(cargo.getDesc2());
				
				/**
				 * 车主信息
				 */
				MemberVo vender =memberVoService.get(bill.getVenderid());
				//车主身份  类别及号码
				if(StringUtils.isNotBlank(vender.getCompanypercheck()) && StringUtils.equals(vender.getCompanypercheck(), "1")){
					//企业
					payInvoiceDetail.setVenderCode(vender.getCompCode());
					payInvoiceDetail.setVenderType((byte)1);
					payInvoiceDetail.setVenderName(vender.getCompanyName());
				}else{
					//个人
					payInvoiceDetail.setVenderCode(vender.getIdcard());
					payInvoiceDetail.setVenderType((byte)0);
					payInvoiceDetail.setVenderName(vender.getUserName());
				}
				payInvoiceDetail.setVenderId(bill.getVenderid());
				payInvoiceDetailMapper.insert(payInvoiceDetail);
				
				updateBill(bill.getId(),freight);
			}
		}
		
		return rs;
	}
	/** 修改运单为已结算状态 并为价格和税率赋值*/
	protected void updateBill(String billid,FileFreight freight) {
		Bill bill = new Bill();
		bill.setId(billid);
		bill.setIsClearing("1");
		bill.setPrice(freight.getPrice());
		bill.setTallage(freight.getTallage());
		billMapper.updateByPrimaryKeySelective(bill);
	}
	
	@Override
	public PaginationVO<PayInvoiceDetailResp> page(PayInvoiceDetailQueryReq req) throws Exception {
		PaginationVO<PayInvoiceDetailResp> page =null;
		if( req !=null && StringUtils.isNotBlank(req.getCurruId())  ){
			page = new PaginationVO<PayInvoiceDetailResp> ();
			//封装查询条件
			PayInvoiceDetail query = copyQuery(req);
			long total =payInvoiceDetailMapper.countByCondition(query);
			if(total >0 ){
				query.setStart((req.getPageNo()-1)*req.getPageSize());
				query.setLimit(req.getPageSize());
				List<PayInvoiceDetail> list =payInvoiceDetailMapper.selectByCondition(query);
				page.setList(convert2PayInvoiceDetailResps(list));
			}
			page.setTotal(total);
			page.setPageNo(req.getPageNo());
		}
		
		return page;
	}
	/** 分页查询条件封装.*/
	protected PayInvoiceDetail copyQuery(PayInvoiceDetailQueryReq req) {
		PayInvoiceDetail query = new PayInvoiceDetail();
		if(StringUtils.isNotBlank(req.getIds())){
			String[] idArr = req.getIds().split(";");
			query.setIds(Arrays.asList(idArr));
		}
		query.setVenderId(req.getCurruId());
		query.setPayId(req.getPayId());
		query.setBillCode(req.getBillNO());
		query.setSignTime(req.getSignTime());
		if(StringUtils.isNotBlank(req.getIsInvoice())){
			if("0".equals(req.getIsInvoice())){
				query.setIsInvoice((byte)0);
			}else if("1".equals(req.getIsInvoice())){
				query.setIsInvoice((byte)1);
			}
		}
		query.setCargoName(req.getCargoName());
		return query;
	}
	@Override
	public Result generalPayInvoice(PayInvoiceGenalReq req) throws Exception {
		Result  rs =Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getCurruId()) && StringUtils.isNotBlank(req.getIds())  ){
			String[] idArr = req.getIds().split(";");
			//验证数量
			if( idArr.length>20){
				//数量过长
				rs.setErrorCode(ErrorCode.PAY_ITEMS_THAN_MAX);
			}else{
				PayInvoiceDetail query =new PayInvoiceDetail();
				query.setIds(Arrays.asList(idArr));
				List<PayInvoiceDetail> items =  payInvoiceDetailMapper.selectByCondition(query);
				if(CollectionUtils.isNotEmpty(items)  && items.size()==idArr.length){
					List<String> typesList =getInvoiceType(items);
					//验证类型及状态
					if(typesList.size()>1 ){
						rs.setErrorCode(ErrorCode.PAY_DATA_TYPE_NOT_EQUAL);	
					}
					//验证状态 都为未开票
					if( payInvoiceCheckStats(items) ){
						//验证通过生成发票单
						PayInvoice payInvoice=new PayInvoice();
						String id =UUIDUtil.getId();
						payInvoice.setId(id);
						
						PayInvoiceDetail item =items.get(0);
						
						payInvoice.setInvoiceType(item.getInvoiceType());
						payInvoice.setInvoiceTypeName(item.getInvoiceTypeName());
						payInvoice.setPaidPrice(0d);
						payInvoice.setPayDealPrice(getTotalPrice(items));
						payInvoice.setPayStatus(PayStatusEnum.create.getStatus());
						payInvoice.setAdviceStatus((byte)0);
						payInvoice.setAdviceTime(System.currentTimeMillis());
						payInvoice.setPayCode(codeGenDao.codeGen(3));
						
						payInvoice.setOwnerId(item.getOwnerId());
						payInvoice.setOrgid(item.getOrgid());
						payInvoice.setOrgName(item.getOrgName());
						
						payInvoice.setVenderId(item.getVenderId());
						payInvoice.setVenderName(item.getVenderName());
						payInvoice.setVenderCode(item.getVenderCode());
						payInvoice.setVenderType(item.getVenderType());
						
						payInvoice.setCreator(req.getCurruId());
						payInvoice.setCreatetime(System.currentTimeMillis());
						payInvoice.setApplyDate(DateUtil.getDateString(new Date(), "yyyy-MM-dd"));
						payInvoice.setModifier(req.getCurruId());
						payInvoice.setModifytime(System.currentTimeMillis());
						//保存发票单数据
						payInvoiceMapper.insert(payInvoice);
						//修改账单状态及主表id
						payInvoiceDetailMapper.updateStatusByIds(Arrays.asList(idArr),id);
					}else{
						rs.setErrorCode(ErrorCode.PAY_DATA_NOT_STATUS_EQUALE);	
					}
				}else{
					rs.setErrorCode(ErrorCode.PAY_DATA_NOT_EXIST);	
				}
			}
		}else{
			rs.setErrorCode(ErrorCode.PAY_DATA_NOT_STATUS_NULL);	
		}
		return rs;
	}

	
	
	@Override
	public PayInvoiceDetailResp queryPayInvoice(PayInvoiceDetailQueryReq req) throws Exception {
		PayInvoiceDetailResp rs =null;
		if( req !=null && StringUtils.isNotBlank(req.getId())){
			PayInvoiceDetail db =payInvoiceDetailMapper.selectByPrimaryKey(req.getId());
			rs =convert2PayInvoiceDetailResp(db);
		}
		return rs;
	}

	private List<PayInvoiceDetailResp> convert2PayInvoiceDetailResps(List<PayInvoiceDetail> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<PayInvoiceDetailResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs =new ArrayList<PayInvoiceDetailResp>();
			for( PayInvoiceDetail item:list ){
				rs.add(convert2PayInvoiceDetailResp(item));
			}
		}
		return rs;
	}
	
	private PayInvoiceDetailResp convert2PayInvoiceDetailResp(PayInvoiceDetail payInvoiceDetail) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		PayInvoiceDetailResp rs =null;
		if( payInvoiceDetail !=null ){
			rs =new PayInvoiceDetailResp();
			PropertyUtils.copyProperties(rs, payInvoiceDetail);
		}
		return rs;
	}
	
	
	//获取所选取数据的发票类型
	private List<String> getInvoiceType(List<PayInvoiceDetail> items){
		List<String> typeList =null;
		if( CollectionUtils.isNotEmpty(items) ){
			typeList =new ArrayList<String>();
			for(PayInvoiceDetail item:items){
				if(item !=null ){
					if( !typeList.contains(item.getInvoiceType()) ){
						typeList.add(item.getInvoiceType());
					}
				}
			}
		}
		return typeList;
	}
	//获取所选中数据的状态  true 都为未开   false 有已开票数据
	private boolean payInvoiceCheckStats(List<PayInvoiceDetail> items){
		boolean rs =true;
		if( CollectionUtils.isNotEmpty(items) ){
			for(PayInvoiceDetail item:items){ 
				if(item !=null && item.getIsInvoice() ==1 ){
					rs=false;
					break;
				}
			}
		}
		return rs;
	}
	
	private double getTotalPrice(List<PayInvoiceDetail> items){
		double rs =0d;
		if( CollectionUtils.isNotEmpty(items) ){
			for(PayInvoiceDetail item:items){ 
				if(item !=null){
					rs += item.getBillTotalPrice();
				}
			}
		}
		return rs;
	}
	/** 获取运价策略
	 * @throws Exception */
	private FileFreight getFileFreight(String id,Long time) throws Exception{
		//TODO
		FileFreight freight = null;
		Date date = null;
		if(time != null){
			date = TimeUtils.LongZoDate(time);
		}else{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date da = new Date();
			String dateStr = sdf.format(da);
			date = sdf.parse(dateStr);
		}
		Result rs = freightInfoService.findFreightInfo(id, date);
		if("000000".equals(rs.getCode())){
			freight = (FileFreight) rs.getData();
		}
		return freight;
	}

}
