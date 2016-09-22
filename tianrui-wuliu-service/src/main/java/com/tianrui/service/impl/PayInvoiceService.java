package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IPayInvoiceService;
import com.tianrui.api.req.front.pay.PayInvoiceAdviceReq;
import com.tianrui.api.req.front.pay.PayInvoiceQueryReq;
import com.tianrui.api.resp.pay.PayInvoiceDetailResp;
import com.tianrui.api.resp.pay.PayInvoiceResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.PayStatusEnum;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.PayInvoice;
import com.tianrui.service.bean.PayInvoiceDetail;
import com.tianrui.service.mapper.PayInvoiceDetailMapper;
import com.tianrui.service.mapper.PayInvoiceMapper;

@Service
public class PayInvoiceService implements IPayInvoiceService {

	@Autowired
	PayInvoiceMapper  payInvoiceMapper;
	@Autowired
	PayInvoiceDetailMapper payInvoiceDetailMapper;
	
	@Override
	public PaginationVO<PayInvoiceResp> page(PayInvoiceQueryReq req) throws Exception {
		PaginationVO<PayInvoiceResp> page =null;
		if( req !=null && StringUtils.isNotBlank(req.getCurruId())  ){
			page = new PaginationVO<PayInvoiceResp> ();
			PayInvoice query = new PayInvoice();
			//TODO 查询条件赋值
			long total =payInvoiceMapper.countByCondition(query);
			if(total >0 ){
				query.setStart((req.getPageNo()-1)*req.getPageSize());
				query.setLimit(req.getPageSize());
				List<PayInvoice> list =payInvoiceMapper.selectByCondition(query);
				page.setList(convert2PayInvoiceResps(list));
			}
			page.setTotal(total);
			page.setPageNo(req.getPageNo());
		}
		return page;
	}

	@Override
	public Result advice(PayInvoiceAdviceReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getId()) && StringUtils.isNotBlank(req.getCurruId()) ){
			PayInvoice db =payInvoiceMapper.selectByPrimaryKey(req.getId());
			if( db !=null && StringUtils.equals(db.getCreator(),req.getCurruId() )){
				PayInvoice update =new PayInvoice();
				update.setId(db.getId());
				update.setModifier(req.getId());
				update.setModifytime(System.currentTimeMillis());
				update.setAdviceStatus((byte)1);
				update.setAdviceTime(System.currentTimeMillis());
				payInvoiceMapper.updateByPrimaryKeySelective(update);
			}else{
				rs.setErrorCode(ErrorCode.PAY_DATA_NOT_USERPAY);
			}
		}else{
			rs.setErrorCode(ErrorCode.PAY_DATA_NOT_STATUS_NULL);
		}
		return rs;
	}                                                                                                                                                                                                                                            

	@Override
	public Result payNcCallBack(PayInvoiceAdviceReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getId())  ){
			PayInvoice db =payInvoiceMapper.selectByPrimaryKey(req.getId());
			if( db !=null && StringUtils.equals(db.getCreator(),req.getCurruId() )){
				if( db.getAdviceStatus()==(byte)1 ){
					if( db.getPayStatus()==PayStatusEnum.pushed.getStatus() ){
						PayInvoice update =new PayInvoice();
						update.setId(db.getId());
						update.setModifier(req.getId());
						update.setModifytime(System.currentTimeMillis());
						update.setAdviceStatus((byte)1);
						update.setAdviceTime(System.currentTimeMillis());
						payInvoiceMapper.updateByPrimaryKeySelective(update);
						rs=Result.getSuccessResult();
					}else{
						rs.setErrorCode(ErrorCode.PAY_DATA_NOT_EXISTSS);
					}
				}else{
					rs.setErrorCode(ErrorCode.PAY_DATA_NOT_ADVICE);
				}
			}else{
				rs.setErrorCode(ErrorCode.PAY_DATA_NOT_EXISTSS);
			}
		}
		return rs;
	}

	@Override
	public PayInvoiceResp queryPayInvoice(PayInvoiceQueryReq req) throws Exception {
		PayInvoiceResp rs =null;
		if( req !=null && StringUtils.isNotBlank(req.getId())){
			PayInvoice db =payInvoiceMapper.selectByPrimaryKey(req.getId());
			rs =convert2PayInvoiceResp(db);
		}
		return rs;
	}

	@Override
	public PayInvoiceResp queryPayInvoiceWithDetail(PayInvoiceQueryReq req) throws Exception {
		PayInvoiceResp rs =null;
		if( req !=null && StringUtils.isNotBlank(req.getId())){
			
			PayInvoice db =payInvoiceMapper.selectByPrimaryKey(req.getId());
			rs =convert2PayInvoiceResp(db);
			if( db!=null ){
				PayInvoiceDetail query =new PayInvoiceDetail();
				query.setPayId(db.getId());
				List<PayInvoiceDetail> items= payInvoiceDetailMapper.selectByCondition(query);
				rs.setItems(convert2PayInvoiceDetailResps(items));
			}
		}
		return rs;
	}
	
	
	
	private List<PayInvoiceResp> convert2PayInvoiceResps(List<PayInvoice> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<PayInvoiceResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs =new ArrayList<PayInvoiceResp>();
			for( PayInvoice item:list ){
				rs.add(convert2PayInvoiceResp(item));
			}
		}
		return rs;
	}
	
	private PayInvoiceResp convert2PayInvoiceResp(PayInvoice payInvoice) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		PayInvoiceResp rs =null;
		if( payInvoice !=null ){
			rs =new PayInvoiceResp();
			//TODO 发票账单信息返回
		PropertyUtils.copyProperties(rs, payInvoice);		
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
}
