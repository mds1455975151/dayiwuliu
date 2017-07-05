package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.admin.intf.IPayInvoiceService;
import com.tianrui.api.req.admin.PayInvoiceAuditUpdate;
import com.tianrui.api.req.admin.PayInvoiceDriverPush;
import com.tianrui.api.req.admin.PayInvoiceReq;
import com.tianrui.api.req.admin.PayInvoiceVenderPush;
import com.tianrui.api.resp.admin.PayInvoiceAuditVo;
import com.tianrui.api.resp.admin.PayInvoiceVo;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.constants.HttpUrl;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.HttpUtil;
import com.tianrui.common.vo.ApiResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.PayInvoice;
import com.tianrui.service.admin.bean.PayInvoiceDetail;
import com.tianrui.service.admin.mapper.PayInvoiceDetailMapper1;
import com.tianrui.service.admin.mapper.PayInvoiceMapper1;

@Service
public class PayInvoiceService1 implements IPayInvoiceService {
	
	private Logger logger = LoggerFactory.getLogger(PayInvoiceService1.class);

	@Autowired
	private PayInvoiceMapper1 payInvoiceMapper;
	@Autowired
	PayInvoiceDetailMapper1 payInvoiceDetailMapper;
	
	@Override
	public PaginationVO<PayInvoiceVo> page(PayInvoiceReq req) {
		logger.info("into service: driver pay invoice page.");
		PaginationVO<PayInvoiceVo> page = null;
		if(req != null){
			logger.info("into service: driver pay invoice page params: " + req.toString());
			page = new PaginationVO<PayInvoiceVo>();
			long count = payInvoiceMapper.selectPayInvoicePageCount(req);
			logger.info("select driver pay invoice page count : " + count);
			if(count > 0){
				req.setOrderColumn("code");
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<PayInvoiceVo> list = payInvoiceMapper.selectPayInvoicePage(req);
				logger.info("select driver pay invoice page : pageListSize:" + list.size());
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		logger.info("out service: driver pay invoice page.");
		return page;
	}
	
	@Override
	public Result payInvoiceUpdateData(String id, boolean isAudit){
		logger.info("into service: driver pay invoice updateData.");
		Result result = Result.getErrorResult();
		if (StringUtils.isNotBlank(id)) {
			logger.info("into service: driver pay invoice page params: id=" + id);
			PayInvoice bean = payInvoiceMapper.selectByPrimaryKey(id);
			PayInvoiceAuditVo vo = new PayInvoiceAuditVo();
			if (bean != null) {
				vo.setId(bean.getId());
				vo.setPayeeName(bean.getPayeeName());
				vo.setPayeeBankCardNumber(bean.getPayeeBankCardNumber());
				if (isAudit) {
					vo.setBillTotalPrice(String.valueOf(bean.getReceptionBillTotalPrice()));
					vo.setDeductWeightMisc(String.valueOf(bean.getReceptionDeductWeightMisc()));
					vo.setDeductMoney(String.valueOf(bean.getReceptionDeductMoney()));
					vo.setDeductOther(String.valueOf(bean.getReceptionDeductOther()));
					vo.setDeductOilCard(String.valueOf(bean.getReceptionDeductOilCard()));
				} else {
					vo.setBillTotalPrice(String.valueOf(bean.getBackstageBillTotalPrice()));
					vo.setDeductWeightMisc(String.valueOf(bean.getBackstageDeductWeightMisc()));
					vo.setDeductMoney(String.valueOf(bean.getBackstageDeductMoney()));
					vo.setDeductOther(String.valueOf(bean.getBackstageDeductOther()));
					vo.setDeductOilCard(String.valueOf(bean.getBackstageDeductOilCard()));
				}
				vo.setAmountPayable(String.valueOf(bean.getAmountPayable()));
			}
			result.setData(vo);
			logger.info("into service: driver pay invoice page params: id=" + vo.toString());
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		logger.info("out service: driver pay invoice updateData.");
		return result;
	}

	@Override
	public Result payInvoiceUpdate(PayInvoiceAuditUpdate auditUpdate, boolean isAudit) {
		logger.info("into service: driver pay invoice update.");
		Result result = Result.getErrorResult();
		if (validateAuditUpdate(auditUpdate)) {
			logger.info("into service: driver pay invoice update selectByPrimaryKey");
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(auditUpdate.getId());
			logger.info("into service: driver pay invoice update selectByPrimaryKey bean: " + payInvoice.toString());
			PayInvoice bean = new PayInvoice();
			bean.setId(payInvoice.getId());
			bean.setBackstageBillTotalPrice(auditUpdate.getBillTotalPrice());
			bean.setBackstageDeductWeightMisc(auditUpdate.getDeductWeightMisc());
			bean.setBackstageDeductMoney(auditUpdate.getDeductMoney());
			bean.setBackstageDeductOther(auditUpdate.getDeductOther());
			bean.setBackstageDeductOilCard(auditUpdate.getDeductOilCard());
			bean.setAmountPayable(bean.getBackstageBillTotalPrice() 
										- bean.getBackstageDeductMoney() 
										- bean.getBackstageDeductOilCard() 
										- bean.getBackstageDeductOther() 
										- bean.getBackstageDeductWeightMisc());
			logger.info("into service: driver pay invoice isAudit: " + isAudit);
			if (isAudit) {
				bean.setAuditStatus(Constant.YES_AUDIT);
				bean.setAuditTime(System.currentTimeMillis());
			}
			logger.info("into service: driver pay invoice update Bean: " + bean.toString());
			if (payInvoiceMapper.updateByPrimaryKeySelective(bean) == 1) {
				logger.info("into service: driver pay invoice audit success");
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}else{
			logger.info("into service: driver pay invoice validate failure. because " + ErrorCode.PARAM_ERROR);
			result.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return result;
	}

	private boolean validateAuditUpdate(PayInvoiceAuditUpdate auditUpdate) {
		logger.info("into service: driver pay invoice audit validate params: " + auditUpdate.toString());
		if(auditUpdate != null
				&& StringUtils.isNotBlank(auditUpdate.getId())
				&& auditUpdate.getDeductMoney() != null
				&& auditUpdate.getDeductOilCard() != null
				&& auditUpdate.getDeductOther() != null
				&& auditUpdate.getDeductWeightMisc() != null){
			logger.info("into service: driver pay invoice audit validate params: true");
			return true;
		}
		logger.info("into service: driver pay invoice audit validate params: false");
		return false;
	}

	@Override
	public Result driverPush(String id) {
		logger.info("into service: driver pay invoice push.");
		Result result = Result.getErrorResult();
		if (StringUtils.isNotBlank(id)) {
			logger.info("into service: driver pay invoice push params: id=" + id);
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(id);
			if (payInvoice != null) {
				//修改原（Constant.NOT_PUSH 未推单） 改（Constant.PUSH_ING 推单中）
				if (payInvoice.getPushStatus() == Constant.PUSH_ING) {
					logger.info("into service: driver pay invoice selectByPrimaryKey. bean: =" + payInvoice.toString());
					PayInvoiceDriverPush push = new PayInvoiceDriverPush();
					push.setId(payInvoice.getId());
					push.setBillcode(payInvoice.getCode());
					push.setSupplierId(payInvoice.getPayeeId());
					push.setBankCardId(payInvoice.getPayeeBankCardId());
					push.setBankCard(payInvoice.getPayeeBankCardNumber());
					push.setDrivercode(payInvoice.getPayeeIdNo());
					push.setInvoiceType(payInvoice.getMaterialCode());
					push.setBillTotalPrice(String.valueOf(payInvoice.getAmountPayable()));
					push.setSignTime(DateUtil.getDateString(payInvoice.getApplicationTime(), DateUtil.Y_M_D_H_M_S));
					logger.info("into service: driver pay invoice push NC param bean: =" + push.toString());
					ApiResult apiResult = HttpUtil.post_longlong(push, HttpUrl.NC_URL_IP_PORT + HttpUrl.PAY_INVOICE_DRIVER_PUSH);
					logger.info("into service: driver pay invoice push NC http result{}: =" + JSON.toJSONString(apiResult).toString());
					if (apiResult != null && StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
						PayInvoice bean = new PayInvoice();
						bean.setId(payInvoice.getId());
						bean.setPushStatus(Constant.YES_PUSH);
						bean.setPushTime(System.currentTimeMillis());
						logger.info("into service: driver pay invoice update pushStatus Bean{}: " + JSON.toJSONString(bean).toString());
						if (payInvoiceMapper.updateByPrimaryKeySelective(bean) == 1) {
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						} else {
							result.setErrorCode(ErrorCode.SYSTEM_ERROR);
						}
					} else {
						result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR);
					}
				} else {
					result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR1);
				}
			} else {
				result.setErrorCode(ErrorCode.PAY_DATA_NOT_EXISTSS);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		logger.info("out service: driver pay invoice push.");
		return result;
	}

	@Override
	public Result venderPush(String id) {
		logger.info("into service: vender pay invoice push.");
		Result result = Result.getErrorResult();
		if (StringUtils.isNotBlank(id)) {
			logger.info("into service: vender pay invoice push params: id=" + id);
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(id);
			if (payInvoice != null) {
				
				//修改验证条件——原（Constant.NOT_PUSH-未推送） -改（Constant.PUSH_ING -前台用户推单中）
				if (payInvoice.getPushStatus() == Constant.PUSH_ING) {
					logger.info("into service: vender pay invoice selectByPrimaryKey. bean: =" + payInvoice.toString());
					PayInvoiceVenderPush push = new PayInvoiceVenderPush();
					push.setId(payInvoice.getId());
					push.setInvoiceType(payInvoice.getMaterialCode());
					push.setSupplierId(payInvoice.getPayeeId());
					push.setApplyDate(DateUtil.getDateString(payInvoice.getApplicationTime(), DateUtil.Y_M_D_H_M_S));
					push.setPayDealPrice(String.valueOf(payInvoice.getAmountPayable()));
					push.setBankCard(payInvoice.getPayeeBankCardNumber());
					push.setBankCardId(payInvoice.getPayeeBankCardId());
					logger.info("into service: vender pay invoice push NC param bean: =" + push.toString());
					ApiResult apiResult = HttpUtil.post_longlong(push, HttpUrl.NC_URL_IP_PORT + HttpUrl.PAY_INVOICE_VENDER_PUSH);
					logger.info("into service: vender pay invoice push NC http result{}: =" + JSON.toJSONString(apiResult).toString());
					if (apiResult != null && StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
						PayInvoice bean = new PayInvoice();
						bean.setId(payInvoice.getId());
						bean.setPushStatus(Constant.YES_PUSH);
						bean.setPushTime(System.currentTimeMillis());
						logger.info("into service: vender pay invoice update pushStatus Bean{}: " + JSON.toJSONString(bean).toString());
						if (payInvoiceMapper.updateByPrimaryKeySelective(bean) == 1) {
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						} else {
							result.setErrorCode(ErrorCode.SYSTEM_ERROR);
						}
					} else {
						result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR);
					}
				} else {
					result.setErrorCode(ErrorCode.PAY_DATA_NOT_EXISTSS);
				}
			} else {
				result.setErrorCode(ErrorCode.PAY_DATA_NOT_EXISTSS);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		logger.info("out service: vender pay invoice push.");
		return result;
	}
	
	@Override
	public Result payAudit(String id) {
		Result rs = Result.getSuccessResult();
		PayInvoice upt = new PayInvoice();
		upt.setId(id);
		upt.setAuditStatus(2);
		payInvoiceMapper.updateByPrimaryKeySelective(upt);
		return rs;
	}

	@Override
	public Result payPush(String id) {
		Result rs = Result.getSuccessResult();
		PayInvoice upt = new PayInvoice();
		upt.setId(id);
		upt.setPushStatus(1);
		payInvoiceMapper.updateByPrimaryKeySelective(upt);
		return rs;
	}

	@Override
	public Result pushBack(String id) {
		Result rs = Result.getSuccessResult();
		PayInvoice pay = payInvoiceMapper.selectByPrimaryKey(id);
		//已推送运单，不能操作
		if(pay.getPushStatus()==2){
			rs.setCode("1");
			rs.setError("已推送运单，不能操作");
		}else{
			PayInvoice upt = new PayInvoice();
			upt.setId(id);
			upt.setPushStatus(0);
			payInvoiceMapper.updateByPrimaryKeySelective(upt);
		}
		return rs;
	}

	@Override
	public Result payDelete(String id) {
		Result rs = Result.getSuccessResult();
		
		PayInvoice pa = payInvoiceMapper.selectByPrimaryKey(id);
		if(pa.getPushStatus()==2){
			rs.setCode("1");
			rs.setError("已推送运单，不能操作");
			return rs;
		}
		
		PayInvoiceDetail query = new PayInvoiceDetail();
		query.setPayInvoiceId(id);
		List<PayInvoiceDetail> list = payInvoiceDetailMapper.selectByCondition(query);
		for(PayInvoiceDetail pay : list){
			PayInvoiceDetail upt = new PayInvoiceDetail();
			upt.setId(pay.getId());
			upt.setPayInvoiceId("");
			upt.setWhetherClose(false);
			payInvoiceDetailMapper.updateByPrimaryKeySelective(upt);
		}
		payInvoiceMapper.deleteByPrimaryKey(id);
		return rs;
	}

	@Override
	public void callBackPayInvoicePaidAmount() {
		PayInvoice record = new PayInvoice();
		record.setPushStatus(Constant.YES_PUSH);
		record.setPayStatus(Constant.NOT_PAY);
		List<PayInvoice> list = payInvoiceMapper.selectByCondition(record);
		if (CollectionUtils.isNotEmpty(list)) {
			List<String> driverParams = new ArrayList<String>();
			List<String> venderParams = new ArrayList<String>();
			for (PayInvoice payInvoice : list) {
				if (payInvoice.getPayeeIdentity() == Constant.PAY_INVOICE_DRIVER) {
					driverParams.add(payInvoice.getId());
				}
				if (payInvoice.getPayeeIdentity() == Constant.PAY_INVOICE_VENDER) {
					venderParams.add(payInvoice.getId());
				}
			}
			postNcDriverCallBack(driverParams);
			postNcVenderCallBack(venderParams);
		}
	}

	private void postNcDriverCallBack(List<String> params) {
		if (CollectionUtils.isNotEmpty(params)) {
			ApiResult apiResult = HttpUtil.post_longlong(params, HttpUrl.NC_URL_IP_PORT + HttpUrl.PAY_INVOICE_DRIVER_CALLBACK_PAIDAMOUNT);
			if (apiResult != null && StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				JSONArray array = JSONArray.parseArray(apiResult.getData().toString());
				if (CollectionUtils.isNotEmpty(array)) {
					callBackPaidAmount(array);
				}
			}
		}
		
	}

	private void postNcVenderCallBack(List<String> params) {
		if (CollectionUtils.isNotEmpty(params)) {
			ApiResult apiResult = HttpUtil.post_longlong(params, HttpUrl.NC_URL_IP_PORT + HttpUrl.PAY_INVOICE_VENDER_CALLBACK_PAIDAMOUNT);
			if (apiResult != null && StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				JSONArray array = JSONArray.parseArray(apiResult.getData().toString());
				if (CollectionUtils.isNotEmpty(array)) {
					callBackPaidAmount(array);
				}
			}
		}
		
	}

	private void callBackPaidAmount(JSONArray array) {
		for (Object object : array) {
			JSONObject jsonObject = (JSONObject) object;
			String id = jsonObject.getString("id");
			String paidAmount = jsonObject.getString("value");
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(id);
			if (payInvoice != null) {
				PayInvoice bean = new PayInvoice();
				bean.setId(id);
				bean.setPaidAmount(Double.valueOf(paidAmount));
				if (bean.getPaidAmount().doubleValue() == payInvoice.getAmountPayable().doubleValue()) {
					bean.setPayStatus(Constant.YES_PAY);
				}
				payInvoiceMapper.updateByPrimaryKeySelective(bean);
			}
		}
	}

	@Override
	public Result payDetail(String id) {
		Result rs = Result.getSuccessResult();
		PayInvoice pay = payInvoiceMapper.selectByPrimaryKey(id);
		rs.setData(pay);
		return rs;
	}

}
