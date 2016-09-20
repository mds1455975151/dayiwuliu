package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IPayInvoiceDetailService;
import com.tianrui.api.req.front.pay.PayInvoiceDetailQueryReq;
import com.tianrui.api.req.front.pay.PayInvoiceDetailSaveReq;
import com.tianrui.api.req.front.pay.PayInvoiceGenalReq;
import com.tianrui.api.resp.pay.PayInvoiceDetailResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.PayStatusEnum;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileCargo;
import com.tianrui.service.admin.mapper.FileCargoMapper;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.PayInvoice;
import com.tianrui.service.bean.PayInvoiceDetail;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.PayInvoiceDetailMapper;
import com.tianrui.service.mapper.PayInvoiceMapper;
@Service
public class PayInvoiceDetailService implements IPayInvoiceDetailService {
	@Autowired
	PayInvoiceDetailMapper  payInvoiceDetailMapper;
	@Autowired
	PayInvoiceMapper payInvoiceMapper;
	@Autowired
	BillMapper billMapper;
	@Autowired
	MemberVoService memberVoService;
	@Autowired
	FileCargoMapper fileCargoMapper;
	
	@Override
	public Result saveByBillPriceConfirm(PayInvoiceDetailSaveReq req) throws Exception {
		Result  rs =Result.getErrorResult();
		if( req !=null && StringUtils.isNotEmpty(req.getBillId()) ){
			//运单信息
			Bill bill =billMapper.selectByPrimaryKey(req.getBillId());
			if( bill !=null ){
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
				payInvoiceDetail.setBillPrice(bill.getPrice());
				payInvoiceDetail.setBillWeight(bill.getTrueweight());
				payInvoiceDetail.setBillTotalPrice(bill.getPrice()*bill.getTrueweight());
				//TODO 到货时间 税率 
//				payInvoiceDetail.setSignTime(bill);
				/**
				 * 货物信息
				 */
				FileCargo cargo=fileCargoMapper.selectByPlanId(bill.getPlanid());
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
			}
		}
		
		return rs;
	}

	@Override
	public PaginationVO<PayInvoiceDetailResp> page(PayInvoiceDetailQueryReq req) throws Exception {
		PaginationVO<PayInvoiceDetailResp> page =null;
		if( req !=null && StringUtils.isNotBlank(req.getCurruId())  ){
			page = new PaginationVO<PayInvoiceDetailResp> ();
			PayInvoiceDetail query = new PayInvoiceDetail();
			//TODO  分页查询条件封装.
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

	@Override
	public Result generalPayInvoice(PayInvoiceGenalReq req) throws Exception {
		Result  rs =Result.getErrorResult();
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
						payInvoice.setPaidPrice(getTotalPrice(items));
						payInvoice.setPayDealPrice(0d);
						payInvoice.setPayStatus(PayStatusEnum.create.getStatus());
						payInvoice.setAdviceStatus((byte)0);
						payInvoice.setAdviceTime(System.currentTimeMillis());
						
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
						
					}
				}else{
					rs.setErrorCode(ErrorCode.PAY_DATA_NOT_EXIST);	
				}
			}
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
		boolean rs =false;
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
				if(item !=null && item.getIsInvoice() ==1 ){
					
				}
			}
		}
		return rs;
	}


}
