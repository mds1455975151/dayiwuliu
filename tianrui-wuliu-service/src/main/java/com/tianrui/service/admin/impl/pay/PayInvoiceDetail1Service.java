package com.tianrui.service.admin.impl.pay;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IPayInvoiceDetail1Service;
import com.tianrui.api.req.admin.pay.PayInvoiceDetail1Req;
import com.tianrui.api.resp.admin.pay.PayInvoiceDetail1Resp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.PayInvoiceDetail;
import com.tianrui.service.admin.mapper.PayInvoiceDetailMapper1;

@Service
public class PayInvoiceDetail1Service implements IPayInvoiceDetail1Service{

	@Autowired
	PayInvoiceDetailMapper1 payInvoiceDetailMapper1;
	
	@Override
	public PaginationVO<PayInvoiceDetail1Resp> select(PayInvoiceDetail1Req req) throws Exception {
		PaginationVO<PayInvoiceDetail1Resp> page = new PaginationVO<PayInvoiceDetail1Resp>();
		
		PayInvoiceDetail pay = new PayInvoiceDetail();
		PropertyUtils.copyProperties(pay, req);
		if(req.getPageNo()!=null){
			pay.setPageNo(req.getPageNo()*req.getPageSize());
			pay.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		List<PayInvoiceDetail> list = payInvoiceDetailMapper1.selectByCondition(pay);
		long a =payInvoiceDetailMapper1.selectBycount(pay);
		page.setTotal(a);
		page.setList(copyProperties2(list));
		return page;
	}
	protected List<PayInvoiceDetail1Resp> copyProperties2(List<PayInvoiceDetail> list) throws Exception {
		List<PayInvoiceDetail1Resp> resp = new ArrayList<PayInvoiceDetail1Resp>();
		for(PayInvoiceDetail pay : list){
			PayInvoiceDetail1Resp sp = new PayInvoiceDetail1Resp();
			PropertyUtils.copyProperties(sp, pay);
			
			resp.add(sp);
		}
		return resp;
	}
	@Override
	public PayInvoiceDetail1Resp selectById(String id) throws Exception {
		PayInvoiceDetail pay = payInvoiceDetailMapper1.selectByPrimaryKey(id);
		PayInvoiceDetail1Resp resp = new PayInvoiceDetail1Resp();
		
		PropertyUtils.copyProperties(resp, pay);
		
		return resp;
	}
	@Override
	public Result uptPrice(PayInvoiceDetail1Req req) throws Exception{
		Result rs = Result.getSuccessResult();
		PayInvoiceDetail upt = new PayInvoiceDetail();
		PropertyUtils.copyProperties(upt, req);
		payInvoiceDetailMapper1.updateByPrimaryKeySelective(upt);
		return rs;
	}
}
