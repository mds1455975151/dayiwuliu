package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.req.admin.anlian.AnlianDriverReq;
import com.tianrui.api.req.front.member.MemberInfoReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.resp.front.member.MemberTransferResp;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.bean.SystemMemberInfoRecord;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberInfoRecordMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
@Service
public class SystemMemberInfoService implements ISystemMemberInfoService {

	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	SystemMemberInfoMapper systemMemberInfoMapper;
	@Autowired
	SystemMemberInfoRecordMapper systemMemberInfoRecorMapper;
	@Autowired
	MemberVoService moberVoService;
	@Autowired
	IMessageService messageService;
	@Autowired
	BillMapper billMapper;
	@Autowired
	IAnlianService anlianService;
	
	@Override
	public Result userReview(MemberInfoReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		SystemMemberInfoRecord record = systemMemberInfoRecorMapper.selectByPrimaryKey(req.getId());
		SystemMember member = systemMemberMapper.selectByPrimaryKey(record.getMemberid());
		short b = 2;
		if(member.getUserpercheck()!=b){
			rs.setCode("1");
			rs.setError("非认证会员中不得审核");
			return rs;
		}
		//认证记录修改
		record.setAuditid(req.getAuditid());
		record.setAuditname(req.getAuditname());
		record.setAudittime(req.getAudittime());
		record.setUserpercheck(req.getUserpercheck());
		record.setAuditresson(req.getRejectReason());
		record.setStatus("1");// 0-未审核，1-已审核',
		int a = systemMemberInfoRecorMapper.updateByPrimaryKeySelective(record);
		if(a != 1){
			rs.setCode("2");
			rs.setError("认证失败，请稍后再试");
			return rs;
		}
		if("1".equals(req.getUserpercheck())){//认证通过
			SystemMemberInfo info = new SystemMemberInfo();
			info.setId(record.getMemberid());
			info.setUsername(record.getUsername());
			info.setTelphone(record.getTelphone());
			info.setIdcard(record.getIdcard());
			info.setSubmittime(record.getSubmittime());
			info.setIdcardimage(record.getIdcardimage());
			
			info.setSex(record.getSex());
			info.setBirthday(record.getBirthday());
			
			systemMemberInfoMapper.updateByPrimaryKeySelective(info);
			member.setUserpercheck((short)1);
		}
		if("3".equals(req.getUserpercheck())){
			member.setUserpercheck((short)3);
		}
		systemMemberMapper.updateByPrimaryKeySelective(member);
		//发送消息
		SendMsgReq mreq = new SendMsgReq();
		List<String> strs = new ArrayList<String>();
		strs.add(member.getCellphone());
		mreq.setType("1");
		mreq.setRecid(member.getId());
		mreq.setRecname(record.getUsername());
		mreq.setParams(strs);
		if("1".equals(req.getUserpercheck())){//通过审核
			mreq.setCodeEnum(MessageCodeEnum.ADMIN_USER_PASS);
			mreq.setRecType(MessageCodeEnum.ADMIN_USER_PASS.getType());
		}else {//if("3".equals(req.getUserpercheck())){//没通过审核
			mreq.setCodeEnum(MessageCodeEnum.ADMIN_USER_NOTPASS);
			mreq.setRecType(MessageCodeEnum.ADMIN_USER_NOTPASS.getType());
		}
		messageService.sendMessageInside(mreq);
		//刷新缓存
		moberVoService.flush(member.getId());
		return rs;
	}

	@Override
	public Result driverReview(MemberInfoReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMemberInfoRecord record = systemMemberInfoRecorMapper.selectByPrimaryKey(req.getId());
		SystemMember member = systemMemberMapper.selectByPrimaryKey(record.getMemberid());
		short b = 2;
		if(member.getDriverpercheck()!=b){
			rs.setCode("1");
			rs.setError("非认证会员中不得审核");
			return rs;
		}
		
		if("1".equals(req.getDriverpercheck())){//认证通过
			
			//TODO
			AnlianDriverReq alreq = new AnlianDriverReq();
			alreq.setRecorid(req.getId());
			rs = anlianService.driver(alreq);
			if(rs.getCode().equals("000000")){
				member.setAldriverid(rs.getData().toString());
			}else{
				return rs;
			}
			
			SystemMemberInfo info = new SystemMemberInfo();
			info.setId(record.getMemberid());
			info.setUsername(record.getUsername());
			info.setTelphone(record.getTelphone());
			info.setIdcard(record.getIdcard());
			info.setSubmittime(record.getSubmittime());
			info.setDriverimage(record.getDriverimage());
			info.setLicenseType(record.getLicenseType());
			
			info.setSex(record.getSex());
			info.setBirthday(record.getBirthday());
			info.setFirstlicens(record.getFirstlicens());
			info.setLicenceorg(record.getLicenceorg());
			info.setStarttime(record.getStarttime());
			info.setUsefullife(record.getUsefullife());
			info.setIdcardaddress(record.getIdcardaddress());
			
			systemMemberInfoMapper.updateByPrimaryKeySelective(info);
			
			
			member.setDriverpercheck((short)1);
			//司机审核通过 默认个人审核通过
			member.setUserpercheck((short)1);
		}
		if("3".equals(req.getDriverpercheck())){
			member.setDriverpercheck((short)3);
		}
		systemMemberMapper.updateByPrimaryKeySelective(member);
		
		//认证记录修改
		record.setAuditid(req.getAuditid());
		record.setAuditname(req.getAuditname());
		record.setAudittime(req.getAudittime());
		record.setDriverpercheck(req.getDriverpercheck());
		record.setAuditresson(req.getRejectReason());
		record.setStatus("1");// 0-未审核，1-已审核',
		systemMemberInfoRecorMapper.updateByPrimaryKeySelective(record);
		
		//消息推送
		SendMsgReq mreq = new SendMsgReq();
		List<String> strs = new ArrayList<String>();
		strs.add(member.getCellphone());
		mreq.setParams(strs);
		mreq.setType("1");
		mreq.setRecid(member.getId());
		mreq.setRecname(record.getUsername());
		if("1".equals(req.getDriverpercheck())){//通过审核
			mreq.setCodeEnum(MessageCodeEnum.ADMIN_DRIVER_PASS);
			mreq.setRecType(MessageCodeEnum.ADMIN_DRIVER_PASS.getType());
		}else if("3".equals(req.getDriverpercheck())){//没通过审核
			mreq.setCodeEnum(MessageCodeEnum.ADMIN_DRIVER_NOTPASS);
			mreq.setRecType(MessageCodeEnum.ADMIN_DRIVER_NOTPASS.getType());
		}
		messageService.sendMessageInside(mreq);
		//刷新缓存
		moberVoService.flush(member.getId());
		return rs;
	}

	@Override
	public Result companyReview(MemberInfoReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMemberInfoRecord record = systemMemberInfoRecorMapper.selectByPrimaryKey(req.getId());
		SystemMember member = systemMemberMapper.selectByPrimaryKey(record.getMemberid());
		short b = 2;
		if(member.getCompanypercheck()!=b){
			rs.setCode("1");
			rs.setError("非认证会员中不得审核");
			return rs;
		}
		//认证记录修改
		record.setAuditid(req.getAuditid());
		record.setAuditname(req.getAuditname());
		record.setAudittime(req.getAudittime());
		record.setCompanypercheck(req.getCompanypercheck());
		record.setAuditresson(req.getRejectReason());
		record.setStatus("1");// 0-未审核，1-已审核',
		int a = systemMemberInfoRecorMapper.updateByPrimaryKeySelective(record);
		if(a != 1){
			rs.setCode("2");
			rs.setError("认证失败，请稍后再试");
			return rs;
		}
		if("1".equals(req.getCompanypercheck())){//认证通过
			SystemMemberInfo info = new SystemMemberInfo();
			info.setId(record.getMemberid());
			info.setCompanycode(record.getCompanycode());
			info.setCompanyname(record.getCompanyname());
			info.setCompanycontact(record.getCompanycontact());
			info.setCompanytel(record.getCompanytel());
			info.setSubmittime(record.getSubmittime());
			info.setLicenseImagePath(record.getLicenseImagePath());
			systemMemberInfoMapper.updateByPrimaryKeySelective(info);
			member.setCompanypercheck((short)1);
		}
		if("3".equals(req.getCompanypercheck())){
			member.setCompanypercheck((short)3);
		}
		systemMemberMapper.updateByPrimaryKeySelective(member);
		//消息推送
		SendMsgReq mreq = new SendMsgReq();
		List<String> strs = new ArrayList<String>();
		strs.add(member.getCellphone());
		mreq.setParams(strs);
		mreq.setType("1");
		mreq.setRecid(member.getId());
		mreq.setRecname(record.getUsername());
		if("1".equals(req.getCompanypercheck())){//通过审核
			mreq.setCodeEnum(MessageCodeEnum.ADMIN_COMPANY_PASS);
			mreq.setRecType(MessageCodeEnum.ADMIN_COMPANY_PASS.getType());
			messageService.sendMessageInside(mreq);
		}else if("3".equals(req.getCompanypercheck())){//没通过审核
			mreq.setCodeEnum(MessageCodeEnum.ADMIN_COMPANY_NOTPASS);
			mreq.setRecType(MessageCodeEnum.ADMIN_COMPANY_NOTPASS.getType());
			messageService.sendMessageInside(mreq);
		}
		//刷新缓存
		moberVoService.flush(member.getId());
		return rs;
	}
	
	public Result handView(String dirverId) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<SystemMemberInfo> list = systemMemberInfoMapper.selectVenderByDriverId(dirverId);
		List<MemberTransferResp> rlist = copyProperties(list);
		for (int i = 0; i < rlist.size(); i++) {
			Bill bil =new Bill();
			bil.setDriverid(dirverId);
			bil.setVenderid(rlist.get(i).getMemberid());
			List<Bill> blist = billMapper.selectByBillTransfer(bil);
			rlist.get(i).setCount(blist.size());
		}
		
		Result result = Result.getSuccessResult();
		result.setData(rlist);
		return result;
	}
	
	public List<MemberTransferResp> copyProperties(List<SystemMemberInfo> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<MemberTransferResp> cop = new ArrayList<MemberTransferResp>();
		for(SystemMemberInfo info : list){
			MemberTransferResp resp = new MemberTransferResp();
			PropertyUtils.copyProperties(resp, info);
			cop.add(resp);
		}
		return cop;
	}

	@Override
	public Result uptMemberPic(MemberInfoReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMemberInfo info = new SystemMemberInfo();
		
		info.setDriverimage(req.getDriveImagePath());
		info.setIdcardimage(req.getIdcardsImagePath());
		info.setLicenseImagePath(req.getLicenseImagePath());
		info.setRtblimgurl(req.getRtblimgurl());
		info.setRtblno(req.getRtblno());
		info.setId(req.getId());
		
		int a = systemMemberInfoMapper.updateByPrimaryKeySelective(info);
		if(a != 1){
			rs.setCode("1");
			rs.setError("修改失败");
			return rs;
		}
		return rs;
	}

}
