package com.tianrui.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.IPayInvoiceService;
import com.tianrui.api.req.front.pay.PayInvoiceAdviceReq;
import com.tianrui.api.req.front.pay.PayInvoiceQueryReq;
import com.tianrui.api.req.front.pay.PayInvoiceReq;
import com.tianrui.api.resp.pay.PayInvoiceDetailResp;
import com.tianrui.api.resp.pay.PayInvoiceResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.PayStatusEnum;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileOrg;
import com.tianrui.service.admin.mapper.FileOrgMapper;
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
	@Autowired
	FileOrgMapper fileOrgMapper;
	
	@Override
	public PaginationVO<PayInvoiceResp> page(PayInvoiceQueryReq req) throws Exception {
		PaginationVO<PayInvoiceResp> page =null;
		if( req !=null && StringUtils.isNotBlank(req.getCurruId())  ){
			page = new PaginationVO<PayInvoiceResp> ();
			PayInvoice query = copyQuery(req);
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
	
	/** 封装查询条件*/
	protected PayInvoice copyQuery(PayInvoiceQueryReq req) {
		PayInvoice query = new PayInvoice();
		query.setVenderId(req.getCurruId());
		query.setPayCode(req.getPaycode());
		query.setApplyDate(req.getApplytime());
		//`advice_status` tinyint(4) DEFAULT '0' COMMENT '审核状态  0未审核  1 已审核 ',
		//`pay_status` tinyint(4) DEFAULT '0' COMMENT '发票单 0新建  1 已推单 2支付中  3支付完成 ',
		if("0".equals(req.getAdviceStatus())){
			query.setAdviceStatus((byte)0);
		}else if("1".equals(req.getAdviceStatus())){
			query.setAdviceStatus((byte)1);
		}
		if("0".equals(req.getPaystatus())){
			query.setPayStatus((byte)0);
		}else if("1".equals(req.getPaystatus())){
			query.setPayStatus((byte)1);
		}else if("2".equals(req.getPaystatus())){
			query.setPayStatus((byte)2);
		}else if("3".equals(req.getPaystatus())){
			query.setPayStatus((byte)3);
		}
		query.setCreator(req.getCurruId());
		return query;
	}
	
	@Override
	public Result advice(PayInvoiceAdviceReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getId()) && StringUtils.isNotBlank(req.getCurruId()) ){
			PayInvoice db =payInvoiceMapper.selectByPrimaryKey(req.getId());
			if( db !=null && StringUtils.equals(db.getCreator(),req.getCurruId() )){
				if(db.getAdviceStatus()==(byte)1){
					rs.setErrorCode(ErrorCode.PAY_DATA_PAY_ADVICE);
				}else{
					PayInvoice update =new PayInvoice();
					update.setId(db.getId());
					update.setModifier(req.getId());
					update.setModifytime(System.currentTimeMillis());
					update.setAdviceStatus((byte)1);
					update.setAdviceTime(System.currentTimeMillis());
					payInvoiceMapper.updateByPrimaryKeySelective(update);
				}
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
			if( db.getAdviceStatus()==(byte)1 ){
				if( db.getPayStatus()==PayStatusEnum.pushed.getStatus() ){
					PayInvoice update =new PayInvoice();
					update.setId(db.getId());
					update.setModifier(req.getId());
					update.setModifytime(System.currentTimeMillis());
					update.setPayStatus((byte)2);//支付中
					payInvoiceMapper.updateByPrimaryKeySelective(update);
					rs=Result.getSuccessResult();
				}else{
					rs.setErrorCode(ErrorCode.PAY_DATA_NOT_EXISTSS);
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

	@Override
	public Result PayNcSave(PayInvoiceReq req) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		PayInvoice invoice = payInvoiceMapper.selectByPrimaryKey(req.getId());
		if(!req.getCurruId().equals(invoice.getCreator())){
			rs.setErrorCode(ErrorCode.PAY_DATA_NOT_USERPAY);
		}else if(invoice.getAdviceStatus()!=(byte)1){
			rs.setErrorCode(ErrorCode.PAY_DATA_PAY_ADVICE);
		}else if(invoice.getPayStatus()!=(byte)0){
			rs.setErrorCode(ErrorCode.PAY_DATA_PAY_ADVICE);
		}else{
			FileOrg org = fileOrgMapper.selectByPrimaryKey(invoice.getOrgid());
			invoice.setOrgid(org.getOrganizationno());
//			invoice.setVenderCode("410482199012015570");
			String dataString = "payInvoice=" + JSON.toJSON(invoice).toString();
			String dateStr = httpNcurl(dataString,"/tcp/payinvoice/savePay");
			if(dateStr.equals("000000")){
				PayInvoice upt = new PayInvoice();
				upt.setId(invoice.getId());
				upt.setPayStatus((byte)2);
				payInvoiceMapper.updateByPrimaryKeySelective(upt);
			}else{
				rs.setCode("1");
				rs.setError(dateStr);
			}
		}
		return rs;
	}
	

	@Override
	public Result withdrawPay(PayInvoiceReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		PayInvoice pay = payInvoiceMapper.selectByPrimaryKey(req.getId());
		byte b = 0;
		if(b != pay.getPayStatus()){
			rs.setErrorCode(ErrorCode.PAY_DATA_PAY_ADVICE);
			return rs;
		}
		if(!pay.getCreator().equals(req.getCurruId())){
			rs.setErrorCode(ErrorCode.PAY_DATA_NOT_USERPAY);
			return rs;
		}
		PayInvoiceDetail record = new PayInvoiceDetail();
		record.setPayId(req.getId());
		List<PayInvoiceDetail> list = payInvoiceDetailMapper.selectByCondition(record);
		for(PayInvoiceDetail detail : list){
			detail.setPayId(null);
			detail.setIsInvoice((byte)0);
			payInvoiceDetailMapper.updateByPrimaryKeySelective(detail);
		}
		payInvoiceMapper.deleteByPrimaryKey(req.getId());
		return rs;
	}

	@Override
	public List<PayInvoiceResp> paylist(PayInvoiceQueryReq req) throws Exception {
		PayInvoice query = copyQuery(req);
		List<PayInvoice> list =payInvoiceMapper.selectByCondition(query);
		return convert2PayInvoiceResps(list);
	}

	@Override
	public void queryNCPayStatus(PayInvoiceQueryReq req) throws Exception {
		// TODO Auto-generated method stub
		PayInvoice query = copyQuery(req);
		List<PayInvoice> list =payInvoiceMapper.selectByCondition(query);
		String dataString = "payInvoiceids=";
		for(PayInvoice pay : list){
			dataString += pay.getId()+",";
		}
		String back = httpNcurl(dataString,"/tcp/payinvoice/queryPayStatus");
		System.out.println("back="+back);
		try {
			JSONObject json = JSONObject.parseObject(back);
			String code = (String) json.get("code");
			if(code.equals("000000")){
				JSONArray array = (JSONArray) json.get("data");
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = array.getJSONObject(i);
					String id = (String) obj.get("id");
					String value =  (String) obj.get("value");
					PayInvoice pi = payInvoiceMapper.selectByPrimaryKey(id);
					PayInvoice pay = new PayInvoice();
					if(pi.getPayDealPrice().toString().equals(value)){
						pay.setPayStatus((byte)3);
					}
					pay.setId(id);
					pay.setPaidPrice(Double.valueOf(value));
					payInvoiceMapper.updateByPrimaryKeySelective(pay);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	

	protected  String httpNcurl(String invoice,String ncurl) throws IOException{
		try {
//			URL url = new URL(Constant.NC_PAY_URL+ncurl);
//			System.out.println(Constant.NC_PAY_URL+ncurl);
			URL url = new URL("http://172.20.10.100"+ncurl);
			System.out.println("http://172.20.10.100"+ncurl);
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");

			System.out.println("nc请求数据=="+invoice);
			byte[] bypes = invoice.getBytes("utf-8");
			
			connection.getOutputStream().write(bypes);// 输入参数
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String response = in.readLine();
			System.out.println(response);
			return response;
		} catch (Exception e) {
			return "网络异常";
		}
	}
}
