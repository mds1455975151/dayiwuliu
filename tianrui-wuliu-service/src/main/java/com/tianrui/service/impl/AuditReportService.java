package com.tianrui.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IAuditReportService;
import com.tianrui.api.req.count.AuditReportReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.count.AuditReportResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.mapper.MyVehicleMapper;
import com.tianrui.service.bean.AuditReport;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.bean.MemberFind;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.anlian.JtbPushCount;
import com.tianrui.service.mapper.AnlianBillMapper;
import com.tianrui.service.mapper.AuditReportMapper;
import com.tianrui.service.mapper.MemberBankCardMapper;
import com.tianrui.service.mapper.SystemMemberMapper;

@Service
public class AuditReportService implements IAuditReportService{
	@Autowired
	AuditReportMapper auditReportMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	MemberBankCardMapper memberBankCardMapper;
	@Autowired
	MyVehicleMapper vehicleMapper;
	@Autowired
	AnlianBillMapper anlianBillMapper;
	
	
	
	@Override
	public PageResp<AuditReportResp> find(AuditReportReq req) throws Exception{
		AuditReport record = new AuditReport();
		//PropertyUtils.copyProperties(record, req);
		//将字符串类型的时间转成date类型
		if(req.getEndtime()!=""&&req.getStarttime()!=""){
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date dateStr = dateFormat.parse(req.getStarttime());
		    Date dateEnd = dateFormat.parse(req.getEndtime());
		    record.setStarttime(dateStr.getTime());
		    record.setEndtime(dateEnd.getTime());
		}
		record.setPageNo(req.getPageNo());
		record.setPageSize(req.getPageSize());
		record.setLimit(req.getLimit());
		List<AuditReport> list = auditReportMapper.selectByCondition(record);
		long a = auditReportMapper.findsAuditReportListCount(record);
		PageResp<AuditReportResp> page = new PageResp<AuditReportResp>();
		page.setList(copyProperties(list));
		page.setPageNo(req.getPageNo());
		page.setPageSize(req.getPageSize());
		page.setCount(a);
		return page;
	}
	
	public List<AuditReportResp> findWithType(AuditReportReq req) throws Exception{
		AuditReport record = new AuditReport();
		PropertyUtils.copyProperties(record, req);
		List<AuditReport> list = auditReportMapper.selectByCondition(record);
		return copyProperties(list);
	}

	public List<AuditReportResp> copyProperties(List<AuditReport> list)throws Exception{
		List<AuditReportResp> resp = new ArrayList<AuditReportResp>();
		for(AuditReport con : list){
			AuditReportResp ca = new AuditReportResp();
			PropertyUtils.copyProperties(ca, con);
			resp.add(ca);
		}
		return resp;
	} 
	
	@Override
	public AuditReportResp findId(String id)throws Exception{
		AuditReport count = auditReportMapper.selectByPrimaryKey(id);
		AuditReportResp resp = new AuditReportResp();
		PropertyUtils.copyProperties(resp, count);
		return resp;
	}

	@Override
	public boolean save(AuditReportReq req) throws Exception{
		AuditReport record = new AuditReport();
		PropertyUtils.copyProperties(record, req);
		int a = auditReportMapper.insertSelective(record);
		if(a == 1){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(AuditReportReq req) throws Exception{
		AuditReport record = new AuditReport();
		PropertyUtils.copyProperties(record, req);
		int a = auditReportMapper.updateByPrimaryKeySelective(record);
		
		if(a==1){
			return true;
		}
		return false;
	}
	@Override
	public Result updateWithId(String id,String val) throws Exception{
		Result rs =Result.getErrorResult();
		AuditReport AuditReport=auditReportMapper.selectByPrimaryKey(id);
		if( AuditReport !=null ){
			AuditReport update = new AuditReport();
			auditReportMapper.updateByPrimaryKeySelective(update);
			rs=Result.getSuccessResult();
		}
		
		return rs;
	}

	@Override
	public boolean delete(String id) throws Exception{
		int a = auditReportMapper.deleteByPrimaryKey(id);
		if(a == 1){
			return true;
		}
		return false;
	}

	@Override
	public void timingTask() throws Exception {
//		userMember();
//		driverMember();
//		vehicleMember();
		findReview();
	}
	/**
	 * @throws Exception 
	 * @Title: findReview 
	 * @Description: 查询各类型审核数据并进行保存
	 * @param    
	 * @return void    
	 * @throws
	 */
	public void findReview() throws Exception {
		//查询银行卡审核通过/不通过数量
		MemberBankCard  mb= new MemberBankCard();
		String yes = "1";
		String no = "3";
		mb.setBankautid(yes);
		mb.setBeginTime(startTime());
		mb.setEndTime(endTime());
		Long bankcardNum = memberBankCardMapper.bankcardByNum(mb);
		mb.setBankautid(no);
		mb.setBeginTime(startTime());
		mb.setEndTime(endTime());
		Long bankcardNums = memberBankCardMapper.bankcardByNum(mb);
		
		//查询车辆审核通过/不通过数量
		MemberVehicle mv = new MemberVehicle();
		mv.setStatus("1");
		mv.setBeginTime(startTime());
		mv.setEndTime(endTime());
		Long vehicleNum  = vehicleMapper.vehicleNum(mv);
		mv.setStatus("-1");
		mv.setBeginTime(startTime());
		mv.setEndTime(endTime());
		Long vehicleNums  = vehicleMapper.vehicleNum(mv);
		
		//查询用户审核通过/不通过数量
		MemberFind m = new MemberFind();
		Short a = new Short(yes);
		m.setUserpercheck(a);
		m.setBeginTime(startTime());
		m.setEndTime(endTime());
		Long userNum =systemMemberMapper.driverByNum(m);
		Short as = new Short(no);
		m.setUserpercheck(as);
		m.setBeginTime(startTime());
		m.setEndTime(endTime());
		Long userNums =systemMemberMapper.driverByNum(m);
		
		//查询大易推送
		JtbPushCount record = new JtbPushCount();
		String ye = "1";
		String ne = "2";
		record.setPustStatus(ye);
		record.setBeginTime(startTime());
		record.setEndTime(endTime());
		Long JtbPushCountNum = anlianBillMapper.selectDYJTBPushCount(record);
		record.setBeginTime(startTime());
		record.setEndTime(endTime());
		record.setPustStatus(ne);
		Long JtbPushCountNums =anlianBillMapper.selectDYJTBPushCount(record);
		//查询安联推送
		JtbPushCount ds = new JtbPushCount();
		String yd = "1";
		String nd = "2";
		ds.setPustStatus(yd);
		record.setBeginTime(startTime());
		record.setEndTime(endTime());
		Long ad = anlianBillMapper.selectAlJTBPushCount(ds);
		ds.setPustStatus(nd);
		record.setBeginTime(startTime());
		record.setEndTime(endTime());
		Long ads = anlianBillMapper.selectAlJTBPushCount(ds);
		
		//查询司机审核通过/不通过数量
		MemberFind ms = new MemberFind();
		Short ab = new Short(yes);
		ms.setUserpercheck(ab);
		ms.setBeginTime(startTime());
		ms.setEndTime(endTime());
		Long driverNum =systemMemberMapper.driverByNum(ms);
		Short ac = new Short(no);
		ms.setUserpercheck(ac);
		ms.setBeginTime(startTime());
		ms.setEndTime(endTime());
		Long driverNums =systemMemberMapper.driverByNum(ms);
		AuditReport ar = new AuditReport();
		
		ar.setBankcardByNum(bankcardNums.toString());//银行卡审核失败的数量
		ar.setBankcardFailNum(bankcardNum.toString());
		ar.setVehicleByNum(vehicleNums.toString());
		ar.setVehicleFailNum(vehicleNum.toString());
		ar.setDriverByNum(driverNums.toString());
		ar.setDriverFailNum(driverNum.toString());
		ar.setUserByNum(userNums.toString());
		ar.setUserFailNum(userNum.toString());
		ar.setWaybillByPushAJ(ads.toString());
		ar.setWaybillFailPushAJ(ad.toString());
		ar.setWaybillByPushDJ(JtbPushCountNums.toString());
		ar.setWaybillFailPushDJ(JtbPushCountNum.toString());
		ar.setCreatertime(System.currentTimeMillis());
		ar.setReviewTime(System.currentTimeMillis()-24*60*60*1000);
		auditReportMapper.insertSelective(ar);
	}
	/**
	 * 
	 * @Title: endTime 
	 * @Description: TODO审核查询结束时间
	 * @param @return
	 * @param @throws Exception   
	 * @return Long    
	 * @throws
	 */
	protected  Long endTime() throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = fmt.format(date);
		Date b = fmt.parse(str);
		Long endTime = b.getTime();
		return endTime;
	}
	/**
	 * 
	 * @Title: startTime 
	 * @Description: 审核查询开始时间
	 * @param @return
	 * @param @throws Exception   
	 * @return Long    
	 * @throws
	 */
	protected  Long startTime() throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = fmt.format(date);
		Date b = fmt.parse(str);
		Long startTime = b.getTime()-24*60*60*1000;
		return startTime;
	}

	@Override
	public List<AuditReportResp> queryAuditReport(AuditReportReq req) {
		List<AuditReportResp> list = null;
		if(req != null){
			list = auditReportMapper.queryAuditReport(req);
		}
		return list;
	}

	@Override
	public int queryAuditReportCount(AuditReportReq req) {
		int count = 0;
		if(req != null){
			count = auditReportMapper.queryAuditReportCount(req);
		}
		return count;
	}



	


	

	


}
