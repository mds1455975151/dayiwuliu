package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.req.admin.PushMember;
import com.tianrui.api.req.front.member.AdminMenberInfoReq;
import com.tianrui.api.req.front.member.MemberInfoReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.resp.front.member.MemberInfoRecordResp;
import com.tianrui.api.resp.front.member.MemberTransferResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.constants.HttpUrl;
import com.tianrui.common.constants.NCResultEnum;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.utils.HttpUtil;
import com.tianrui.common.vo.ApiResult;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.bean.SystemMemberInfoRecord;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberInfoRecordMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;
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
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	@Autowired
    private TaskExecutor taskExecutor;
	
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
		member.setAuditname(record.getAuditname());
		member.setAudittime(new Date().getTime());
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
			SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(record.getMemberid());
			//审核通过推送到NC
			if(info.getPushStatus()==0){
				//审核通过推送到NC
				PushMember push = new PushMember();
				push.setSuppid(record.getMemberid());
				push.setName(record.getUsername());
				push.setVbusinlicense(record.getIdcard());
				runTheadPoolTask(push);
			}
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
			
			member.setAldriverid("1");
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
			info.setPositive(record.getPositive());
			info.setOpposite(record.getOpposite());
			systemMemberInfoMapper.updateByPrimaryKeySelective(info);
			
			
			member.setDriverpercheck((short)1);
			//司机审核通过 默认个人审核通过
			member.setUserpercheck((short)1);
		}
		if("3".equals(req.getDriverpercheck())){
			member.setDriverpercheck((short)3);
		}
		member.setAuditname(record.getAuditname());
		member.setAudittime(new Date().getTime());
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
			SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(record.getMemberid());
			//审核通过推送到NC
			if(info.getPushStatus()==0){
				//用户未推送NC
				PushMember push = new PushMember();
				push.setSuppid(record.getMemberid());
				push.setName(record.getUsername());
				push.setVbusinlicense(record.getIdcard());
				runTheadPoolTask(push);
			}
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
			info.setRtblimgurl(record.getRtblimgurl());
			info.setRtblno(record.getRtblno());
			info.setCompanyaddress(record.getCompanyAddress());
			info.setAuditname(record.getAuditname());
			info.setAudittime(record.getAudittime());
			systemMemberInfoMapper.updateByPrimaryKeySelective(info);
			member.setCompanypercheck((short)1);
		}
		if("3".equals(req.getCompanypercheck())){
			member.setCompanypercheck((short)3);
		}
		member.setAuditname(record.getAuditname());
		member.setAudittime(new Date().getTime());
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
			SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(record.getMemberid());
			//审核通过推送到NC
			if(info.getPushStatus()==0){
				//推送审核通过的供应商
				PushMember push = new PushMember();
				push.setSuppid(record.getMemberid());
				push.setName(record.getCompanyname());
				push.setVbusinlicense(record.getCompanycode());
				runTheadPoolTask(push);
			}
		}else if("3".equals(req.getCompanypercheck())){//没通过审核
			mreq.setCodeEnum(MessageCodeEnum.ADMIN_COMPANY_NOTPASS);
			mreq.setRecType(MessageCodeEnum.ADMIN_COMPANY_NOTPASS.getType());
			messageService.sendMessageInside(mreq);
		}
		//刷新缓存
		moberVoService.flush(member.getId());
		return rs;
	}
	
	private void runTheadPoolTask(final PushMember push) {
		try {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					pushMemberToNc(push);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Result pushNc(String id){
		Result result = Result.getSuccessResult();
		if(StringUtils.isNotBlank(id)){
			MemberVo vo = moberVoService.get(id,true);
			PushMember push = new PushMember();
			push.setSuppid(vo.getId());
			if(StringUtils.equals(vo.getCompanypercheck(), "1")){
				push.setName(vo.getCompanyName());
				push.setVbusinlicense(vo.getCompCode());
			}
			if(StringUtils.equals(vo.getUserpercheck(), "1")){
				push.setName(vo.getUserName());
				push.setVbusinlicense(vo.getIdcard());
			}
			result = pushMemberToNc(push);
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	private Result pushMemberToNc(PushMember push){
		Result result = Result.getErrorResult();
		if(push != null){
			//这里编写处理业务代码---333
			ApiResult apiResult = HttpUtil.post(push, HttpUrl.NC_URL_IP_PORT + HttpUrl.MEMBER_INFO_PUSH);
			if (apiResult != null){
				if(StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
					//推送成功修改推送状态
					SystemMemberInfo info = new SystemMemberInfo();
					info.setId(push.getSuppid());
					info.setPushStatus(Constant.YES_PUSH);
					info.setNcStatus(Constant.NC_MEMBER_PUSH_STATUS_NOT_AUDIT);
					systemMemberInfoMapper.updateByPrimaryKeySelective(info);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else if(StringUtils.equals(apiResult.getCode(), ErrorCode.MEMBER_PUSH_ERROR1.getCode())){
					//推送成功修改推送状态
					SystemMemberInfo info = new SystemMemberInfo();
					info.setId(push.getSuppid());
					info.setPushStatus(Constant.YES_PUSH);
					systemMemberInfoMapper.updateByPrimaryKeySelective(info);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.MEMBER_PUSH_ERROR);
					result.setError(result.getError() + "失败信息" + apiResult.getMessage());
					LoggerFactory.getLogger("pushMessage").info("供应商推送失败错误信息: ", apiResult.getMessage());
				}
			} else {
				result.setErrorCode(ErrorCode.MEMBER_PUSH_ERROR);
				LoggerFactory.getLogger("pushMessage").info("供应商推送失败错误信息: apiResult= " + apiResult);
			}
		}
		return result;
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
		info.setOpposite(req.getOpposite());
		info.setPositive(req.getPositive());
		info.setId(req.getId());
		
		int a = systemMemberInfoMapper.updateByPrimaryKeySelective(info);
		if(a != 1){
			rs.setCode("1");
			rs.setError("修改失败");
			return rs;
		}
		return rs;
	}

	@Override
	public Result uptDrvierAnlian(AdminMenberInfoReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getId());
		
		VehicleDriver vd = new VehicleDriver();
		vd.setDriverid(req.getId());
		List<VehicleDriver> li = vehicleDriverMapper.selectMyVehiDriverByCondition(vd);
		if(li.size()!=0){
			rs.setErrorCode(ErrorCode.VEHICLE_DRIVER1_NOTONLY);
			return rs;
		}
		
		if(StringUtils.isNotBlank(member.getAldriverid())){
			rs.setCode("1");
			rs.setError("司机已通过安联认证");
			return rs;
		}
//		rs = anlianService.adminDriver(req);
//		if(!"000000".equals(rs.getCode())){
//			return rs;
//		}
		
		SystemMemberInfo info = new SystemMemberInfo();
		PropertyUtils.copyProperties(info, req);
		systemMemberInfoMapper.updateByPrimaryKeySelective(info);
		
		SystemMember men = new SystemMember();
		men.setId(req.getId());
		men.setAldriverid("1");
		systemMemberMapper.updateByPrimaryKeySelective(men);
		return rs;
	}

	@Override
	public MemberInfoRecordResp selectMemberInfo(String id) throws Exception {
		SystemMember member = systemMemberMapper.selectByPrimaryKey(id);
		SystemMemberInfo info =systemMemberInfoMapper.selectByPrimaryKey(id);
		MemberInfoRecordResp resp = new MemberInfoRecordResp();
		resp.setUserpercheck(member.getUserpercheck().toString());
		resp.setDriverpercheck(member.getDriverpercheck().toString());
		resp.setCompanypercheck(member.getCompanypercheck().toString());
		resp.setCellphone(member.getCellphone());
		resp.setRegisttime(member.getRegisttime());
		resp.setCompanyAddress(info.getCompanyaddress());
//		PropertyUtils.copyProperties(resp, member);
		PropertyUtils.copyProperties(resp, info);
		return resp;
	}
	
	@Override
	public void callBackMemberPushStatus() {
		SystemMemberInfo info = new SystemMemberInfo();
		info.setPushStatus(Constant.YES_PUSH);
		info.setNcStatus(Constant.NC_MEMBER_PUSH_STATUS_YES_ORG);
		List<SystemMemberInfo> list = systemMemberInfoMapper.selectNcNotUse(info);
		if (CollectionUtils.isNotEmpty(list)) {
			List<Object> ids = new ArrayList<Object>();
			for (SystemMemberInfo bean : list) {
				JSONObject obj = new JSONObject();
				obj.put("suppid", bean.getId());
				if(StringUtils.isBlank(bean.getCompanyname())){
					//企业名为空 个人认证
					obj.put("name", bean.getUsername());
					obj.put("vbusinlicense", bean.getIdcard());
				}else {
					//企业认证
					obj.put("name", bean.getCompanyname());
					obj.put("vbusinlicense", bean.getCompanycode());
				}
				ids.add(obj);
			}
			selectNCMemberPushStatus(ids);
		}
	}
	
	private ApiResult selectNCMemberPushStatus(List<Object> list){
		ApiResult apiResult = null;
		if (CollectionUtils.isNotEmpty(list)) {
			apiResult = HttpUtil.post(list, HttpUrl.NC_URL_IP_PORT + HttpUrl.MEMBER_INFO_PUSH_NC_STATUS);
			if (apiResult != null) {
				if (StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
					JSONArray array = JSONArray.parseArray(apiResult.getData().toString());
					if(CollectionUtils.isNotEmpty(array)){
						for (Object object : array) {
							JSONObject jsonObject = (JSONObject) object;
							String id = jsonObject.getString("id");
							String status = jsonObject.getString("status");
							//审核通过并分配组织
							SystemMemberInfo info = new SystemMemberInfo();
							info.setId(id);
							if (StringUtils.equals(status, String.valueOf(NCResultEnum.NC_RESULT_ENUM_1.getCode()))) {
								//回写供应商ncStatus
								info.setPushStatus(Constant.NOT_PUSH);
								info.setNcStatus(Constant.ZERO);
							} else if (StringUtils.equals(status, String.valueOf(NCResultEnum.NC_RESULT_ENUM_2.getCode()))) {
								//回写供应商ncStatus
								info.setNcStatus(Constant.NC_MEMBER_PUSH_STATUS_NOT_AUDIT);
							} else if (StringUtils.equals(status, String.valueOf(NCResultEnum.NC_RESULT_ENUM_3.getCode()))) {
								//回写供应商ncStatus
								info.setNcStatus(Constant.NC_MEMBER_PUSH_STATUS_AUDIT_REFUSED);
							} else if (StringUtils.equals(status, String.valueOf(NCResultEnum.NC_RESULT_ENUM_4.getCode()))) {
								//回写供应商ncStatus
								info.setNcStatus(Constant.NC_MEMBER_PUSH_STATUS_AUDIT_ING);
							} else if (StringUtils.equals(status, String.valueOf(NCResultEnum.NC_RESULT_ENUM_5.getCode()))) {
								//回写供应商ncStatus
								info.setNcStatus(Constant.NC_MEMBER_PUSH_STATUS_NOT_ORG);
							} else if (StringUtils.equals(status, String.valueOf(NCResultEnum.NC_RESULT_ENUM_6.getCode()))) {
								//回写供应商ncStatus
								info.setNcStatus(Constant.NC_MEMBER_PUSH_STATUS_YES_ORG);
							} else {
								LoggerFactory.getLogger("pushMessage").info("查询供应商推送状态: 供应商ID: "+ id + ", 查询结果： " + NCResultEnum.getMessage(status));
							}
							systemMemberInfoMapper.updateByPrimaryKeySelective(info);
						}
					}
				} else {
					LoggerFactory.getLogger("querySupplierAndCallbackNcStatus").info("查询供应商推送状态错误信息: " + apiResult.getMessage());
				}
			}
		}
		return apiResult;
	}
	
}
