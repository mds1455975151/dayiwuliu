package com.tianrui.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IFreightInfoService;
import com.tianrui.api.intf.ISignerBillService;
import com.tianrui.api.req.front.bill.BillConfirmPriceReq;
import com.tianrui.api.req.front.bill.SignerBillFindReq;
import com.tianrui.api.req.front.bill.SignerBillReq;
import com.tianrui.api.resp.front.bill.SignerBillResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileCargo;
import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.bean.FileOrgCargo;
import com.tianrui.service.admin.bean.PayInvoice;
import com.tianrui.service.admin.bean.PayInvoiceDetail;
import com.tianrui.service.admin.mapper.FileCargoMapper;
import com.tianrui.service.admin.mapper.FileOrgCargoMapper;
import com.tianrui.service.admin.mapper.PayInvoiceDetailMapper1;
import com.tianrui.service.admin.mapper.PayInvoiceMapper1;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.SignerBill;
import com.tianrui.service.bean.anlian.AnlianBill;
import com.tianrui.service.mapper.AnlianBillMapper;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mapper.SignerBillMapper;
import com.tianrui.service.util.TimeUtils;

@Service
public class SignerBillService implements ISignerBillService{

	@Autowired
	SignerBillMapper signerBillMapper;
	@Autowired
	PayInvoiceDetailMapper1 payInvoiceDetailMapper1;
	@Autowired
	BillMapper billMapper;
	@Autowired
	PlanMapper planMapper;
	@Autowired
	FileCargoMapper fileCargoMapper;
	@Autowired
	FileOrgCargoMapper fileOrgCargoMapper;
	@Autowired
	IFreightInfoService freightInfoService;
	@Autowired
	AnlianBillMapper anlianBillMapper;
	
	@Override
	public PaginationVO<SignerBillResp> select(SignerBillFindReq req) throws Exception {
		PaginationVO<SignerBillResp> page = new PaginationVO<SignerBillResp>();
		SignerBill bill = new SignerBill();
//		PropertyUtils.copyProperties(bill, req);
		bill.setReceiveMemberid(req.getReceiveMemberid());
		bill.setBillno(req.getBillno());
		bill.setBilltype(req.getBilltype());
		bill.setCargoname(req.getCargoname());
		bill.setVehicleno(req.getVehicleno());
		bill.setBillstatus(req.getBillstatus());
		if(req.getPageNo()!=null){
			bill.setPageNo(req.getPageNo()*req.getPageSize());
			bill.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		List<SignerBill> list = signerBillMapper.selectByCondition(bill);
		Long a = signerBillMapper.selectBycount(bill);
		page.setList(copyProperties2(list));
		page.setTotal(a);
		return page;
	}
	protected List<SignerBillResp> copyProperties2(List<SignerBill> list) throws Exception{
		List<SignerBillResp> resp = new ArrayList<SignerBillResp>();
		for(SignerBill bill : list){
			SignerBillResp sb = new SignerBillResp();
			PropertyUtils.copyProperties(sb, bill);
			resp.add(sb);
		}
		return resp;
	}
	@Override
	public Result BillConfirmPrice(BillConfirmPriceReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		
		PayInvoiceDetail query = new PayInvoiceDetail();
		query.setBillId(req.getId());
		//验证是否运价确认
		List<PayInvoiceDetail> list = payInvoiceDetailMapper1.selectByCondition(query);
		if(list.size()>1){
			rs.setCode("1");
			rs.setError("数据异常，请联系管理员");
			return rs;
		}else if(list.size()==1){
			if(list.get(0).getBackstageBillTotalPrice()!=0){
				rs.setCode("3");
				rs.setError("后台已运价确认，不能修改");
				return rs;
			}
		}
		
		PayInvoiceDetail pay = null;
		if(StringUtils.equals(req.getType(), "dy")){
			//处理大易平台运单
			Bill bill = billMapper.selectByPrimaryKey(req.getId());
			pay = changeDyBill(req,bill);
			pay.setRemark("dy");
			Bill upt = new Bill();
			upt.setId(bill.getId());
			upt.setConfirmPriceA("1");//前台已运价确认
			upt.setConfirmPriceB("0");//后台未已运价确认
			billMapper.updateByPrimaryKeySelective(upt);
		}else if(StringUtils.equals(req.getType(), "al")){
			//处理安联平台运单
			AnlianBill bill = anlianBillMapper.selectByPrimaryKey(req.getId());
			pay = changeAlBill(req,bill);
			pay.setRemark("al");
			AnlianBill alupt = new AnlianBill();
			alupt.setId(bill.getId());
			alupt.setConfirmPriceA("1");//前台已运价确认
			alupt.setConfirmPriceB("0");//后台未已运价确认
			anlianBillMapper.updateByPrimaryKeySelective(alupt);
		}
		if(list.size()==0){
			payInvoiceDetailMapper1.insertSelective(pay);
		}else if(list.size()==1){
			pay.setId(list.get(0).getId());
			payInvoiceDetailMapper1.updateByPrimaryKeySelective(pay);
		}
		return rs;
	}
	
	/**处理安联
	 * @throws Exception */
	protected PayInvoiceDetail changeAlBill(BillConfirmPriceReq req,AnlianBill bill) throws Exception{
		PayInvoiceDetail pay = new PayInvoiceDetail();
		Plan plan = planMapper.selectByPrimaryKey(bill.getDesc1());
		FileOrgCargo orgcargo = fileOrgCargoMapper.selectByPrimaryKey(plan.getCargoid());
		FileCargo cargo=fileCargoMapper.selectByPrimaryKey(orgcargo.getCargoid());
		FileFreight freight = getFileFreight(plan.getFreightid(),bill.getSigntime());
		
		pay.setId(UUIDUtil.getId());
		//账单好、运单号
		pay.setCode(bill.getBillno());
		//司机id
		pay.setDriverId(bill.getDriverid());
		//车主id
		pay.setVenderId(bill.getVenderid());
		//货主id
		pay.setOwnerId(bill.getOwnerid());
		//发票类型
		pay.setInvoiceCode(cargo.getDesc1());
		//发票类型
		pay.setInvoiceName(cargo.getDesc2());
		//运单id
		pay.setBillId(bill.getId());
		//运单号
		pay.setBillCode(bill.getBillno());
		//货物名称
		pay.setCargoName(cargo.getCargoname());
		//运单重量
		pay.setBillWeight(req.getBillTrueWeight());
		//安联运单 单价
		pay.setBillPrice(req.getBillPrice());
		//税率
		pay.setTaxRate(freight.getTallage());
		//前台运价确认价格
		pay.setReceptionBillTotalPrice(req.getBillTrueWeight()*req.getBillPrice());
		//前台扣重扣杂
		pay.setReceptionDeductWeightMisc(req.getDeduct_weight_misc());
		//前台扣款
		pay.setReceptionDeductMoney(req.getDeduct_money());
		//前台其它扣款
		pay.setReceptionDeductOther(req.getDeduct_other());
		//前台扣油卡
		pay.setReceptionDeductOilCard(req.getDeduct_oil_card());
		//组织id
		pay.setOrgid(freight.getOrganizationid());
		//有效
		pay.setState(true);
		pay.setCreator(req.getCreater());
		pay.setCreateTime(System.currentTimeMillis());
		
//		if(StringUtils.equals(bill.getPayment(), "1")){
//			//支付司机
//		}else if(StringUtils.equals(bill.getPayment(), "1")){
//			//支付车主
//		}else 
		if(StringUtils.isNotBlank(bill.getPayment())){
			pay.setWhetherClose(false);
			//支付对象 1-司机  2-车主
			pay.setBillType(Integer.valueOf(bill.getPayment()));
		}
		
		return pay;
	}
	
	/**处理大易
	 * @throws Exception */
	protected PayInvoiceDetail changeDyBill(BillConfirmPriceReq req,Bill bill) throws Exception{
		PayInvoiceDetail pay = new PayInvoiceDetail();
		Plan plan = planMapper.selectByPrimaryKey(bill.getPlanid());
		FileOrgCargo orgcargo = fileOrgCargoMapper.selectByPrimaryKey(plan.getCargoid());
		FileCargo cargo=fileCargoMapper.selectByPrimaryKey(orgcargo.getCargoid());
		FileFreight freight = getFileFreight(plan.getFreightid(),bill.getUnloadtime());
		pay.setId(UUIDUtil.getId());
		//账单好、运单号
		pay.setCode(bill.getWaybillno());
		//司机id
		pay.setDriverId(bill.getDriverid());
		//车主id
		pay.setVenderId(bill.getVenderid());
		//货主id
		pay.setOwnerId(bill.getOwnerid());
		//发票类型
		pay.setInvoiceCode(cargo.getDesc1());
		//发票类型
		pay.setInvoiceName(cargo.getDesc2());
		//运单id
		pay.setBillId(bill.getId());
		//运单号
		pay.setBillCode(bill.getWaybillno());
		//货物名称
		pay.setCargoName(bill.getCargoname());
		//运单重量
		pay.setBillWeight(req.getBillTrueWeight());
		//运单单价
		pay.setBillPrice(req.getBillPrice());
		//税率
		pay.setTaxRate(freight.getTallage());
		//账单总价
		pay.setReceptionBillTotalPrice(req.getBillPrice()*req.getBillTrueWeight());
		//前台扣重扣杂
		pay.setReceptionDeductWeightMisc(req.getDeduct_weight_misc());
		//前台扣款
		pay.setReceptionDeductMoney(req.getDeduct_money());
		//前台其它扣款
		pay.setReceptionDeductOther(req.getDeduct_other());
		//前台扣油卡
		pay.setReceptionDeductOilCard(req.getDeduct_oil_card());
		//组织id
		pay.setOrgid(bill.getOrgid());
		//有效
		pay.setState(true);
		pay.setCreator(req.getCreater());
		pay.setCreateTime(System.currentTimeMillis());
		
//		if(StringUtils.equals(bill.getPayment(), "1")){
//			//支付司机
//		}else if(StringUtils.equals(bill.getPayment(), "1")){
//			//支付车主
//		}else 
		if(StringUtils.isNotBlank(bill.getPayment())){
			//支付对象 1-司机  2-车主
			pay.setWhetherClose(false);
			pay.setBillType(Integer.valueOf(bill.getPayment()));
		}
		return pay;
	}
	
	/** 获取运价策略
	 * @throws Exception */
	protected FileFreight getFileFreight(String id,Long time) throws Exception{
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
