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
import com.tianrui.api.resp.pay.PayInvoiceResp;
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
			//TODO
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
		Result rs = Result.getErrorResult();
		if( req!=null && StringUtils.isNotBlank(req.getId()) && StringUtils.isNotBlank(req.getCurruId()) ){
			PayInvoice db =payInvoiceMapper.selectByPrimaryKey(req.getId());
			if( db !=null && StringUtils.equals(db.getCreator(),req.getCurruId() )){
				//审核通过  推单 TODO
				PayInvoice update =new PayInvoice();
				update.setId(db.getId());
				update.setModifier(req.getId());
				update.setModifytime(System.currentTimeMillis());
				update.setAdviceStatus((byte)1);
				update.setAdviceTime(System.currentTimeMillis());
				payInvoiceMapper.updateByPrimaryKeySelective(update);
			}
		}
		return rs;
	}                                                                                                                                                                                                                                            

	@Override
	public Result payNcCallBack(PayInvoiceAdviceReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
			if( db!=null ){
				PayInvoiceDetail query =new PayInvoiceDetail();
				query.setPayId(db.getId());
				List<PayInvoiceDetail> items= payInvoiceDetailMapper.selectByCondition(query);
				//TODO
			}
			rs =convert2PayInvoiceResp(db);
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
			PropertyUtils.copyProperties(rs, payInvoice);
		}
		return rs;
	}

}
