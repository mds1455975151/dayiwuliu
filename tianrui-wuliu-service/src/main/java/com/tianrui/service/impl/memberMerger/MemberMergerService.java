package com.tianrui.service.impl.memberMerger;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.intf.memberMerger.IMemberMergerService;
import com.tianrui.api.req.front.member.MemberFindReq;
import com.tianrui.api.req.memberMerger.MergerCellphoneReq;
import com.tianrui.api.req.memberMerger.MergerQueryReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.OrgMember;
import com.tianrui.service.admin.bean.PayInvoiceDetail;
import com.tianrui.service.admin.mapper.OrgMemberMapper;
import com.tianrui.service.admin.mapper.PayInvoiceDetailMapper1;
import com.tianrui.service.bean.AddVehicleBankCard;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.bean.MemberCapa;
import com.tianrui.service.bean.MemberCapaList;
import com.tianrui.service.bean.MemberOwner;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.Message;
import com.tianrui.service.bean.OrgSigner;
import com.tianrui.service.bean.OwnerDriver;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.PlanGoods;
import com.tianrui.service.bean.ReportBillAll;
import com.tianrui.service.bean.ReportPayAll;
import com.tianrui.service.bean.ReportPlanAll;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfoRecord;
import com.tianrui.service.bean.Transfer;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.bean.anlian.AnlianBill;
import com.tianrui.service.mapper.AddVehicleBankCardMapper;
import com.tianrui.service.mapper.AnlianBillMapper;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.MemberBankCardMapper;
import com.tianrui.service.mapper.MemberCapaMapper;
import com.tianrui.service.mapper.MemberOwnerMapper;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.MessageMapper;
import com.tianrui.service.mapper.OrgSignerMapper;
import com.tianrui.service.mapper.OwnerDriverMapper;
import com.tianrui.service.mapper.PlanGoodsMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mapper.ReportBillAllMapper;
import com.tianrui.service.mapper.ReportPayAllMapper;
import com.tianrui.service.mapper.ReportPlanAllMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberInfoRecordMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.TransferMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;



@Service
public class MemberMergerService implements IMemberMergerService{
 
	Logger logger=LoggerFactory.getLogger(MemberMergerService.class);
	
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	SystemMemberInfoMapper systemMemberInfoMapper;
	@Autowired
	SystemMemberInfoRecordMapper systemMemberInfoRecordMapper;
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	@Autowired
	MemberCapaMapper memberCapaMapper;
	@Autowired
	MemberOwnerMapper memberOwnerMapper;
	@Autowired
	OwnerDriverMapper ownerDriverMapper;
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	@Autowired
	AddVehicleBankCardMapper addVehicleBankCardMapper;
	@Autowired
	TransferMapper transferMapper;
	@Autowired
	PlanGoodsMapper planGoodsMapper;
	@Autowired
	PlanMapper planMapper;
	@Autowired
	MessageMapper messageMapper;
	@Autowired
	MemberBankCardMapper memberBankCardMapper;
	@Autowired
	BillMapper billMapper;
	@Autowired
	AnlianBillMapper anlianBillMapper;
	@Autowired
	OrgMemberMapper orgMemberMapper;
	@Autowired
	OrgSignerMapper orgSignerMapper;
	@Autowired
	ReportBillAllMapper reportBillAllMapper;
	@Autowired
	ReportPlanAllMapper reportPlanAllMapper;
	@Autowired
	ReportPayAllMapper reportPayAllMapper;
	@Autowired
	PayInvoiceDetailMapper1 payInvoiceDetailMapper1;

	@Override
	public PageResp<MemberResp> selectMergerCellhpone(MergerQueryReq req) throws Exception {
		MemberFindReq member = new MemberFindReq();
		member.setIdCard(req.getIdCard());
		PageResp<MemberResp> list = systemMemberService.findMemberlist(member);
		return list;
	}

	@Override
	@Transactional
	public Result mergerCellphone(MergerCellphoneReq req) {
		Result rs = Result.getSuccessResult();
		SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getMainMemberid());
//		SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(req.getMainMemberid());
		//查询查询用户是否存在
		if(member!=null){
			String[] ids = req.getOtherMemberids().split(";");
			logger.info("待合并用户："+ids.length+","+req.getOtherMemberids());
			if(ids.length!=0){
				if(checkVehicle(ids)){
					for(String memberId:ids){
						if(StringUtils.isNotBlank(memberId)){
							//待合并用户
							SystemMember base = systemMemberMapper.selectByPrimaryKey(memberId);
							
							if(base!=null){
								//wuliu_member_vehicle  处理用户车辆数据 修改车辆归属人 修改
								wuliuMemberVehicle(memberId);
								//处理 wuliu_transfer 修改
								tarnSfer(memberId,member);
								//处理  wuliu_plan_goods 修改
								planGoods(memberId,member);
								//处理 wuliu_plan 修改
								plan(memberId,member);
								//处理 wuliu_message 修改
								message(memberId);
								//处理 report_bill_all 修改
								reportBillAll(memberId,member);
								//处理  report_plan_all 修改
								reportPlanAll(memberId);
								//处理 report_pay_all 修改
								reportPayAll(memberId);
								//处理 pay_invoice_detail_1 修改
								payDetail(memberId);
								//处理 wuliu_bill 修改
								bill(memberId,member);
								//处理 anlian_bill 修改
								anlianBill(memberId,member);
								
								//处理 wuliu_member_bankcard 删除
								bankCard(memberId);
								//处理我的运力  wuliu_member_capa  解除用户绑定关系 删除
								memberCapa(memberId);
								//处理wuliu_member_owner  解除车主关系表 删除
								memberOwner(memberId);
								//处理 wuliu_owner_driver 解除司机绑定关系 删除
								ownerDriver(memberId);
								//处理 wuliu_vehicle_driver 删除车辆司机绑定关系 删除
								vehicleDriver(memberId);
								//处理 wulliu_addvehicle_bankcard 删除车主绑定关系 删除
								vehicleBankCard(memberId);
								//处理 file_org_member 删除
								orgMember(memberId);
								//处理 file_org_signer 删除
								orgSigner(memberId);
								
								//用户删除
								systemMemberMapper.deleteByPrimaryKey(memberId);
								systemMemberInfoMapper.deleteByPrimaryKey(memberId);
								
								SystemMemberInfoRecord qq = new SystemMemberInfoRecord();
								qq.setMemberid(memberId);
								List<SystemMemberInfoRecord> list = systemMemberInfoRecordMapper.findReason(qq);
								for(SystemMemberInfoRecord sp : list){
									systemMemberInfoRecordMapper.deleteByPrimaryKey(sp.getId());
								}

							}
						}
					}
				}else{
					rs.setErrorCode(ErrorCode.MEMBER_VEHICLE_STATUS);
				};
			}else{
				rs.setErrorCode(ErrorCode.MEMBER_MERGER_NULL);
			}
		}else{
			rs.setErrorCode(ErrorCode.MEMBER_MERGER_NULL);
		}
		return rs;
	}

	private void payDetail(String memberId) {
		PayInvoiceDetail pdd = new PayInvoiceDetail();
		pdd.setDriverId(memberId);
		List<PayInvoiceDetail> pddList = payInvoiceDetailMapper1.selectByCondition(pdd);
		for(PayInvoiceDetail sp : pddList){
			PayInvoiceDetail upt = new PayInvoiceDetail();
			upt.setId(sp.getId());
			upt.setDriverId(memberId);
			payInvoiceDetailMapper1.updateByPrimaryKeySelective(upt);
		}
		PayInvoiceDetail pdo = new PayInvoiceDetail();
		pdo.setOwnerId(memberId);
		List<PayInvoiceDetail> pdoList = payInvoiceDetailMapper1.selectByCondition(pdo);
		for(PayInvoiceDetail sp : pdoList){
			PayInvoiceDetail upt = new PayInvoiceDetail();
			upt.setId(sp.getId());
			upt.setOwnerId(memberId);
			payInvoiceDetailMapper1.updateByPrimaryKeySelective(upt);
		}
		PayInvoiceDetail pdv = new PayInvoiceDetail();
		pdv.setVenderId(memberId);
		List<PayInvoiceDetail> pdvList = payInvoiceDetailMapper1.selectByCondition(pdv);
		for(PayInvoiceDetail sp : pdvList){
			PayInvoiceDetail upt = new PayInvoiceDetail();
			upt.setId(sp.getId());
			upt.setVenderId(memberId);
			payInvoiceDetailMapper1.updateByPrimaryKeySelective(upt);
		}
	}

	private void reportPayAll(String memberId) {
		ReportPayAll rpay = new ReportPayAll();
		rpay.setPayDriverId(memberId);
		List<ReportPayAll> rpayList = reportPayAllMapper.selectByCondition(rpay);
		for(ReportPayAll sp : rpayList){
			ReportPayAll upt = new ReportPayAll();
			upt.setId(sp.getId());
			upt.setPayDriverId(memberId);
			reportPayAllMapper.updateByPrimaryKeySelective(upt);
		}
		ReportPayAll rpayv = new ReportPayAll();
		rpayv.setPayVenderId(memberId);
		List<ReportPayAll> rpayvList = reportPayAllMapper.selectByCondition(rpayv);
		for(ReportPayAll sp : rpayvList){
			ReportPayAll upt = new ReportPayAll();
			upt.setId(sp.getId());
			upt.setPayVenderId(memberId);
			reportPayAllMapper.updateByPrimaryKeySelective(upt);
		}
		ReportPayAll rpayo = new ReportPayAll();
		rpayo.setPayOwnerId(memberId);
		List<ReportPayAll> rpayoList = reportPayAllMapper.selectByCondition(rpayo);
		for(ReportPayAll sp : rpayoList){
			ReportPayAll upt = new ReportPayAll();
			upt.setId(sp.getId());
			upt.setPayOwnerId(memberId);
			reportPayAllMapper.updateByPrimaryKeySelective(upt);
		}
		ReportPayAll rpayr = new ReportPayAll();
		rpayr.setPayReceiveId(memberId);
		List<ReportPayAll> rpayrList = reportPayAllMapper.selectByCondition(rpayr);
		for(ReportPayAll sp : rpayrList){
			ReportPayAll upt = new ReportPayAll();
			upt.setId(sp.getId());
			upt.setPayReceiveId(memberId);
			reportPayAllMapper.updateByPrimaryKeySelective(upt);
		}
	}

	private void reportPlanAll(String memberId) {
		ReportPlanAll repPlan = new ReportPlanAll();
		repPlan.setPlanOwnerId(memberId);
		List<ReportPlanAll> repPlanList = reportPlanAllMapper.selectByCondition(repPlan);
		for(ReportPlanAll sp : repPlanList){
			ReportPlanAll upt = new ReportPlanAll();
			upt.setId(sp.getId());
			upt.setPlanOwnerId(memberId);
			reportPlanAllMapper.updateByPrimaryKeySelective(upt);
		}
		ReportPlanAll repPlanv = new ReportPlanAll();
		repPlanv.setPlanVenderId(memberId);
		List<ReportPlanAll> repPlanvList = reportPlanAllMapper.selectByCondition(repPlanv);
		for(ReportPlanAll sp : repPlanvList){
			ReportPlanAll upt = new ReportPlanAll();
			upt.setId(sp.getId());
			upt.setPlanVenderId(memberId);
			reportPlanAllMapper.updateByPrimaryKeySelective(upt);
		}
	}

	private void reportBillAll(String memberId,SystemMember member) {
		ReportBillAll repblD = new ReportBillAll();
		repblD.setBillDriverId(memberId);
		List<ReportBillAll> repblDList = reportBillAllMapper.selectByCondition(repblD);
		for(ReportBillAll sp : repblDList){
			ReportBillAll upt = new ReportBillAll();
			upt.setId(sp.getId());
			upt.setBillDriverId(memberId);
			reportBillAllMapper.updateByPrimaryKeySelective(upt);
		}
		ReportBillAll repblO = new ReportBillAll();
		repblO.setBillOwnerId(memberId);
		List<ReportBillAll> repblOList = reportBillAllMapper.selectByCondition(repblO);
		for(ReportBillAll sp : repblOList){
			ReportBillAll upt = new ReportBillAll();
			upt.setId(sp.getId());
			upt.setBillOwnerId(memberId);
			reportBillAllMapper.updateByPrimaryKeySelective(upt);
		}
		ReportBillAll repblV = new ReportBillAll();
		repblV.setBillVenderId(memberId);
		List<ReportBillAll> repblVList = reportBillAllMapper.selectByCondition(repblV);
		for(ReportBillAll sp : repblVList){
			ReportBillAll upt = new ReportBillAll();
			upt.setId(sp.getId());
			upt.setBillVenderId(memberId);
			reportBillAllMapper.updateByPrimaryKeySelective(upt);
		}
		ReportBillAll repblR = new ReportBillAll();
		repblR.setBillReceiveId(memberId);
		List<ReportBillAll> repblRList = reportBillAllMapper.selectByCondition(repblR);
		for(ReportBillAll sp : repblRList){
			ReportBillAll upt = new ReportBillAll();
			upt.setId(sp.getId());
			upt.setBillReceiveId(memberId);
			reportBillAllMapper.updateByPrimaryKeySelective(upt);
		}
	}

	private void orgSigner(String memberId) {
		OrgSigner orgs = new OrgSigner();
		orgs.setMemberid(memberId);
		List<OrgSigner> orgsList = orgSignerMapper.selectByCondition(orgs);
		for(OrgSigner sp : orgsList){
			orgSignerMapper.deleteByPrimaryKey(sp.getId());
		}
	}

	private void orgMember(String memberId) {
		OrgMember org = new OrgMember();
		org.setMembertel(memberId);
		List<OrgMember> orgm = orgMemberMapper.findByEntity(org);
		for(OrgMember orm : orgm){
			orgMemberMapper.delectById(orm.getId());
		}
	}

	private void bill(String memberId,SystemMember member) {
		Bill bdr = new Bill();
		bdr.setDriverid(memberId);
		List<Bill> bdrList = billMapper.selectByCondition(bdr);
		for(Bill sp : bdrList){
			Bill upt = new Bill();
			upt.setId(sp.getId());
			upt.setDriverid(memberId);
			upt.setDrivername(member.getRemarkname());
			upt.setDrivertel(member.getCellphone());
			billMapper.updateByPrimaryKeySelective(upt);
		}
		
		Bill bdow = new Bill();
		bdow.setOwnerid(memberId);
		List<Bill> bdowList = billMapper.selectByCondition(bdow);
		for(Bill sp : bdowList){
			Bill upt = new Bill();
			upt.setId(sp.getId());
			upt.setOwnerid(memberId);
			billMapper.updateByPrimaryKeySelective(upt);
		}
		
		Bill bdvd = new Bill();
		bdvd.setVenderid(memberId);
		List<Bill> bdvdList = billMapper.selectByCondition(bdvd);
		for(Bill sp : bdvdList){
			Bill upt = new Bill();
			upt.setId(sp.getId());
			upt.setVenderid(memberId);
			billMapper.updateByPrimaryKeySelective(upt);
		}
		
		Bill bdcr = new Bill();
		bdcr.setCreator(memberId);
		List<Bill> bdcrList = billMapper.selectByCondition(bdcr);
		for(Bill sp : bdcrList){
			Bill upt = new Bill();
			upt.setId(sp.getId());
			upt.setCreator(memberId);
			billMapper.updateByPrimaryKeySelective(upt);
		}
		
		Bill bdrec = new Bill();
		bdrec.setReceive_memberid(memberId);
		List<Bill> bdrecList = billMapper.selectByCondition(bdrec);
		for(Bill sp : bdrecList){
			Bill upt = new Bill();
			upt.setId(sp.getId());
			upt.setReceive_memberid(memberId);
			upt.setReceivername(member.getRemarkname());
			upt.setReceivertel(member.getCellphone());
			billMapper.updateByPrimaryKeySelective(upt);
		}
	}

	private void anlianBill(String memberId,SystemMember member) {
		AnlianBill albd = new AnlianBill();
		albd.setDriverid(memberId);
		List<AnlianBill> albdList = anlianBillMapper.selectByCondition(albd);
		for(AnlianBill sp : albdList){
			AnlianBill upt = new AnlianBill();
			upt.setId(sp.getId());
			upt.setDriverid(memberId);
			anlianBillMapper.updateByPrimaryKeySelective(upt);
		}
		
		AnlianBill albow = new AnlianBill();
		albow.setOwnerid(memberId);
		List<AnlianBill> albowList = anlianBillMapper.selectByCondition(albow);
		for(AnlianBill sp : albowList){
			AnlianBill upt = new AnlianBill();
			upt.setId(sp.getId());
			upt.setOwnerid(memberId);
			anlianBillMapper.updateByPrimaryKeySelective(upt);
		}
		AnlianBill albvd = new AnlianBill();
		albvd.setVenderid(memberId);
		List<AnlianBill> albvdList = anlianBillMapper.selectByCondition(albvd);
		for(AnlianBill sp : albvdList){
			AnlianBill upt = new AnlianBill();
			upt.setId(sp.getId());
			upt.setVenderid(memberId);
			anlianBillMapper.updateByPrimaryKeySelective(upt);
		}
		AnlianBill albrec = new AnlianBill();
		albrec.setReceive_memberid(memberId);
		List<AnlianBill> albrecList = anlianBillMapper.selectByCondition(albrec);
		for(AnlianBill sp : albrecList){
			AnlianBill upt = new AnlianBill();
			upt.setId(sp.getId());
			upt.setReceive_memberid(memberId);
			anlianBillMapper.updateByPrimaryKeySelective(upt);
		}
	}


	private void bankCard(String memberId) {
		MemberBankCard bank = new MemberBankCard();
		bank.setCreater(memberId);
		List<MemberBankCard> banklist = memberBankCardMapper.selectByCondition(bank);
		for(MemberBankCard sp : banklist){
			memberBankCardMapper.deleteByPrimaryKey(sp.getId());
		}
	}

	private void message(String memberId) {
		Message msg = new Message();
		msg.setRecid(memberId);
		List<Message> msgList = messageMapper.findByEntity(msg);
		for(Message sp : msgList){
			Message upt = new Message();
			upt.setId(sp.getId());
			upt.setRecid(memberId);
			messageMapper.updateByPrimaryKeySelective(upt);
		}
		Message ssg = new Message();
		ssg.setSendid(memberId);
		List<Message> ssgList = messageMapper.findByEntity(ssg);
		for(Message sp : ssgList){
			Message upt = new Message();
			upt.setId(sp.getId());
			upt.setSendid(memberId);
			messageMapper.updateByPrimaryKeySelective(upt);
		}
	}

	private void plan(String memberId,SystemMember member) {
		Plan pl = new Plan();
		pl.setCreator(memberId);
		List<Plan> plcList = planMapper.selectByCondition(pl);
		for(Plan sp : plcList){
			Plan upt = new Plan();
			upt.setId(sp.getId());
			upt.setCreator(memberId);
			planMapper.updateByPrimaryKeySelective(upt);
		}
		Plan plv = new Plan();
		plv.setVehicleownerid(memberId);
		List<Plan> plvList = planMapper.selectByCondition(plv);
		for(Plan sp : plvList){
			Plan upt = new Plan();
			upt.setId(sp.getId());
			upt.setVehicleownerid(memberId);
			planMapper.updateByPrimaryKeySelective(upt);
		}
		Plan recv = new Plan();
		recv.setReceiveid(memberId);
		List<Plan> recvList = planMapper.selectByCondition(recv);
		for(Plan sp : recvList){
			Plan upt = new Plan();
			upt.setId(sp.getId());
			upt.setReceiveid(memberId);
			upt.setReceiveperson(member.getRemarkname());
			upt.setReceivepersonphone(member.getCellphone());
			planMapper.updateByPrimaryKeySelective(upt);
		}
	}

	private void planGoods(String memberId,SystemMember member) {
		PlanGoods pgoods = new PlanGoods();
		pgoods.setCreator(memberId);
		List<PlanGoods> gsList = planGoodsMapper.selectByCondition(pgoods);
		for(PlanGoods cp : gsList){
			PlanGoods upt = new PlanGoods();
			upt.setId(cp.getId());
			upt.setCreator(memberId);
			planGoodsMapper.updateByPrimaryKeySelective(upt);
		}
		PlanGoods pgoodsc = new PlanGoods();
		pgoodsc.setReceiveid(memberId);
		List<PlanGoods> pgoodscList = planGoodsMapper.selectByCondition(pgoodsc);
		for(PlanGoods cp : pgoodscList){
			PlanGoods upt = new PlanGoods();
			upt.setId(cp.getId());
			upt.setReceiveid(memberId);
			upt.setReceiveperson(member.getRemarkname());
			upt.setReceivepersonphone(member.getCellphone());
			planGoodsMapper.updateByPrimaryKeySelective(upt);
		}
	}

	private void tarnSfer(String memberId,SystemMember member) {
		Transfer tra = new Transfer();
		tra.setSendid(memberId);
		List<Transfer> trsList = transferMapper.selectByCondition(tra);
		for(Transfer sp : trsList){
			Transfer upt = new Transfer();
			upt.setId(sp.getId());
			upt.setSendid(memberId);
			upt.setSender(member.getRemarkname());
			upt.setSendtele(member.getCellphone());
			transferMapper.updateByPrimaryKeySelective(upt);
		}
		Transfer trast = new Transfer();
		trast.setStartid(memberId);
		List<Transfer> trastList = transferMapper.selectByCondition(tra);
		for(Transfer sp : trastList){
			Transfer upt = new Transfer();
			upt.setId(sp.getId());
			upt.setStartid(memberId);
			upt.setStarter(member.getRemarkname());
			upt.setStarttele(member.getCellphone());
			transferMapper.updateByPrimaryKeySelective(upt);
		}
	}

	private void vehicleBankCard(String memberId) {
		AddVehicleBankCard bkcard = new AddVehicleBankCard();
		bkcard.setDriverid(memberId);
		List<AddVehicleBankCard> listbk = addVehicleBankCardMapper.selectByCondition(bkcard);
		for(AddVehicleBankCard bc : listbk){
			addVehicleBankCardMapper.deleteByPrimaryKey(bc.getId());
		}
		AddVehicleBankCard bkvv = new AddVehicleBankCard();
		bkvv.setVehicleownerid(memberId);
		List<AddVehicleBankCard> listvv = addVehicleBankCardMapper.selectByCondition(bkvv);
		for(AddVehicleBankCard bc : listvv){
			addVehicleBankCardMapper.deleteByPrimaryKey(bc.getId());
		}
	}

	private void vehicleDriver(String memberId) {
		VehicleDriver vd = new VehicleDriver();
		vd.setCreator(memberId);//删除车主绑定
		List<VehicleDriver> vdList = vehicleDriverMapper.selectMyVehiDriverByCondition(vd);
		for(VehicleDriver db : vdList){
			vehicleDriverMapper.deleteByPrimaryKey(db.getId());
		}
		VehicleDriver vdd = new VehicleDriver();
		vdd.setDriverid(memberId);//删除司机绑定
		List<VehicleDriver> vddList = vehicleDriverMapper.selectMyVehiDriverByCondition(vdd);
		for(VehicleDriver db : vddList){
			vehicleDriverMapper.deleteByPrimaryKey(db.getId());
		}
	}

	private void ownerDriver(String memberId) {
		OwnerDriver odd = new OwnerDriver();
		odd.setDriverid(memberId);
		List<OwnerDriver> oddList = ownerDriverMapper.selectMyDriverByCondition(odd);
		for(OwnerDriver od : oddList){
			ownerDriverMapper.deleteByPrimaryKey(od.getId());
		}
		OwnerDriver odc = new OwnerDriver();
		odc.setCreator(memberId);
		List<OwnerDriver> odcList = ownerDriverMapper.selectMyDriverByCondition(odc);
		for(OwnerDriver od : odcList){
			ownerDriverMapper.deleteByPrimaryKey(od.getId());
		}
	}

	private void memberOwner(String memberId) {
		MemberOwner ownerM = new MemberOwner();
		ownerM.setMemberid(memberId);
		List<MemberOwner> ownerMList = memberOwnerMapper.selectMyVehiOwnerByCondition(ownerM);
		for(MemberOwner ow : ownerMList){
			memberOwnerMapper.deleteByPrimaryKey(ow.getId());
		}
		MemberOwner ownerO = new MemberOwner();
		ownerO.setOwnerid(memberId);
		List<MemberOwner> ownerOList = memberOwnerMapper.selectMyVehiOwnerByCondition(ownerO);
		for(MemberOwner ow : ownerOList){
			memberOwnerMapper.deleteByPrimaryKey(ow.getId());
		}
	}

	private void memberCapa(String memberId) {
		MemberCapa capao = new MemberCapa();
		capao.setMemberid(memberId);
		List<MemberCapaList> cpOw = memberCapaMapper.selectByCondition(capao);
		for(MemberCapaList cp : cpOw){
			memberCapaMapper.deleteByPrimaryKey(cp.getId());
		}
	}

	private void wuliuMemberVehicle(String memberId) {
		MemberVehicle vehicle = new MemberVehicle();
		vehicle.setMemberid(memberId);
		List<MemberVehicle> vehList = memberVehicleMapper.selectMyVehicleByCondition(vehicle);
		for(MemberVehicle vehic : vehList){
			MemberVehicle upt = new MemberVehicle();
			upt.setId(vehic.getId());
			upt.setMemberid(memberId);
			memberVehicleMapper.updateByPrimaryKeySelective(upt);
			//被引用的车辆  解除引用关系
			MemberCapa capac = new MemberCapa();
			capac.setVehicleid(vehic.getId());
			List<MemberCapaList> cpCr = memberCapaMapper.selectByCondition(capac);
			for(MemberCapaList cp : cpCr){
				memberCapaMapper.deleteByPrimaryKey(cp.getId());
			}
			VehicleDriver vdd = new VehicleDriver();
			vdd.setVehicleid(vehic.getId());//删除车辆绑定关系
			List<VehicleDriver> vddList = vehicleDriverMapper.selectMyVehiDriverByCondition(vdd);
			for(VehicleDriver db : vddList){
				vehicleDriverMapper.deleteByPrimaryKey(db.getId());
			}
		}
	}

	/** 校验是否有非空闲车辆*/
	private boolean checkVehicle(String[] ids) {
		boolean a = true;
		for(String memberId:ids){
			if(StringUtils.isNotBlank(memberId)){
				MemberVehicle vehicle = new MemberVehicle();
				vehicle.setMemberid(memberId);
				List<MemberVehicle> vehList = memberVehicleMapper.selectMyVehicleByCondition(vehicle);
				for(MemberVehicle veh : vehList){
					if(!veh.getBillstatus().equals("5")){
						a = false;
						logger.info("校验用户"+memberId+"非空闲车辆车牌号："+veh.getId()+veh.getVehicleprefix()+veh.getVehicleno());
					}
				}
			}
		}
		return a;
	}

}
