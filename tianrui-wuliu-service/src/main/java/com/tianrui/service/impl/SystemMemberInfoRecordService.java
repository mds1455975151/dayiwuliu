package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ISystemMemberInfoRecordService;
import com.tianrui.api.req.front.member.MemberInfoReq;
import com.tianrui.api.resp.front.member.MemberInfoRecordResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Members;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfoRecord;
import com.tianrui.service.mapper.SystemMemberInfoRecordMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
/**
 * 
 * @类描述：会员认证记录
 * @创建人：lsj
 * @创建时间：2016年6月20日下午3:13:24
 *
 * @修改人：lsj
 * @修改时间：2016年6月20日下午3:13:24
 * @修改备注：
 *
 */
@Service
public class SystemMemberInfoRecordService implements ISystemMemberInfoRecordService{

	@Autowired
	SystemMemberInfoRecordMapper systemMemberInfoRecordMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	MemberVoService moberVoService;
	

	@Override
	public MemberInfoRecordResp findLastAutid(String id,String type) throws Exception {
		SystemMemberInfoRecord query = new SystemMemberInfoRecord();
		query.setMemberid(id);
		if(type.equals("1")){
			query.setDriverpercheck("3");
		}else if(type.equals("2")){
			query.setUserpercheck("3");
		}else if(type.equals("3")){
			query.setCompanypercheck("3");
		}
		List<SystemMemberInfoRecord> list = systemMemberInfoRecordMapper.selectByRecordEntity(query);
		MemberInfoRecordResp resp = new MemberInfoRecordResp();
		if(list.size()!=0){
			SystemMemberInfoRecord bean = list.get(0);
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
	
	/**
	 * 个人认证
	 */
	@Override
	public Result personalAuthentication(MemberInfoReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getMemberId());
//		short d = 1;
//		if(member.getCompanypercheck()==d){
//			rs.setCode("1");
//			rs.setError("企业认证后不能进行个人认证");
//			return rs;
//		}
		short s = 2;
		if(member.getDriverpercheck()==s||member.getUserpercheck()==s
				||member.getCompanypercheck()==s){
			rs.setCode("1");
			rs.setError("认证中用户不得修改");
			return rs;
		}
		short k = 3;
		//司机认证失败
		if(member.getDriverpercheck()==k){
			member.setDriverpercheck((short)0);
		}
		//企业认证失败
		if(member.getCompanypercheck()==k){
			member.setCompanypercheck((short)0);
		}
		
		Long t = new Date().getTime();
		SystemMemberInfoRecord info = new SystemMemberInfoRecord();
		//数值转换
		info = copyProperties(req);
		info.setId(UUIDUtil.getId());
		info.setMemberid(req.getMemberId());
		info.setUserpercheck("2");
		info.setStatus("0");//0-未审核，1-已审核
		info.setSubmittime(t);
		info.setSex(req.getSex());
		info.setBirthday(req.getBirthday());
		int a = systemMemberInfoRecordMapper.insertSelective(info);
		if(a==1){
			member.setUserpercheck((short)2);//认证中
			member.setSubmittime(t);
			member.setRemarkname(req.getUserName());
			systemMemberMapper.updateByPrimaryKeySelective(member);
			moberVoService.flush(req.getMemberId());
		}else{
			rs.setCode("1");
			rs.setError("认证失败");
			return rs;
		}
		return rs;
	}
	
	public SystemMemberInfoRecord copyProperties(MemberInfoReq req){
		SystemMemberInfoRecord info = new SystemMemberInfoRecord();
		//用户名称
		info.setUsername(req.getUserName());
		//联系电话
		info.setTelphone(req.getTelphone());
		//身份证号
		info.setIdcard(req.getIdentityCard());
		//身份证图片
		info.setIdcardimage(req.getIdcardsImagePath());
		//驾驶证图片
		info.setDriverimage(req.getDriveImagePath());
		//公司名称
		info.setCompanyname(req.getCompanyName());
		//公司联系人
		info.setCompanycontact(req.getCompanyContact());
		//公司联系人电话
		info.setCompanytel(req.getContactTel());
		//公司地址
		info.setCompanyAddress(req.getCompanyAddress());
		info.setCompanycode(req.getCompanycode());
		//营业执照图片
		info.setLicenseImagePath(req.getLicenseImagePath());
		//道路许可证
		info.setRtblimgurl(req.getRtblimgurl());
		info.setRtblno(req.getRtblno());
		
		return info;
	}

	@Override
	public Result enterpriseAuthentication(MemberInfoReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getMemberId());
//		short d = 1;
//		if(member.getDriverpercheck()==d||member.getUserpercheck()==d){
//			rs.setCode("1");
//			rs.setError("司机认证或者个人认证成功后不可以进行企业认证");
//			return rs;
//		}
		short s = 2;
		if(member.getDriverpercheck()==s||member.getUserpercheck()==s
				||member.getCompanypercheck()==s){
			rs.setCode("1");
			rs.setError("认证中用户不得修改");
			return rs;
		}
		short k = 3;
		//司机认证失败
		if(member.getUserpercheck()==k){
			member.setUserpercheck((short)0);
		}
		//企业认证失败
		if(member.getDriverpercheck()==k){
			member.setDriverpercheck((short)0);
		}
		Long t = new Date().getTime();
		SystemMemberInfoRecord info = new SystemMemberInfoRecord();
		//数值转换
		info = copyProperties(req);
		info.setId(UUIDUtil.getId());
		info.setMemberid(req.getMemberId());
		info.setCompanypercheck("2");
		info.setStatus("0");//0-未审核，1-已审核
		info.setSubmittime(t);
		int a = systemMemberInfoRecordMapper.insertSelective(info);
		if(a==1){
			member.setCompanypercheck((short)2);//认证中
			member.setSubmittime(t);
			member.setRemarkname(req.getCompanyName());
			systemMemberMapper.updateByPrimaryKeySelective(member);
			moberVoService.flush(req.getMemberId());
		}else{
			rs.setCode("1");
			rs.setError("认证失败");
			return rs;
		}
		return rs;
	}

	@Override
	public Result driverAuthentication(MemberInfoReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getMemberId());
//		short d = 1;
//		if(member.getCompanypercheck()==d){
//			rs.setCode("1");
//			rs.setError("企业认证后不能进行司机认证");
//			return rs;
//		}
		short s = 2;
		if(member.getDriverpercheck()==s||member.getUserpercheck()==s
				||member.getCompanypercheck()==s){
			rs.setCode("1");
			rs.setError("认证中用户不得修改");
			return rs;
		}
		short k = 3;
		//司机认证失败
		if(member.getUserpercheck()==k){
			member.setUserpercheck((short)0);
		}
		//企业认证失败
		if(member.getCompanypercheck()==k){
			member.setCompanypercheck((short)0);
		}
		
		Long t = new Date().getTime();
		SystemMemberInfoRecord info = new SystemMemberInfoRecord();
		//数值转换
		info = copyProperties(req);
		info.setId(UUIDUtil.getId());
		info.setMemberid(req.getMemberId());
		info.setDriverpercheck("2");
		info.setStatus("0");//0-未审核，1-已审核
		info.setSubmittime(t);
		info.setLicenseType(req.getLicenseType());
		
		info.setSex(req.getSex());
		info.setBirthday(req.getBirthday());
		info.setFirstlicens(req.getFirstlicens());
		info.setLicenceorg(req.getLicenceorg());
		info.setStarttime(req.getStarttime());
		if(StringUtils.isNotBlank(req.getUsefullife())){
			if(req.getUsefullife().endsWith("年")){
				info.setUsefullife(req.getUsefullife().substring(0, req.getUsefullife().length()-1));
			}else{
				info.setUsefullife(req.getUsefullife());
			}
		}
		
		info.setIdcardaddress(req.getIdcardaddress());
		
		info.setOpposite(req.getOpposite());
		info.setPositive(req.getPositive());
		int a = systemMemberInfoRecordMapper.insertSelective(info);
		if(a==1){
			member.setDriverpercheck((short)2);//认证中
			member.setSubmittime(t);
			member.setRemarkname(req.getUserName());
			systemMemberMapper.updateByPrimaryKeySelective(member);
			moberVoService.flush(req.getMemberId());
		}else{
			rs.setCode("1");
			rs.setError("认证失败");
			return rs;
		}
		return rs;
	}

	@Override
	public MemberInfoRecordResp findByMemberId(String id) throws Exception {
		SystemMemberInfoRecord record = new SystemMemberInfoRecord();
		record.setMemberid(id);
		record.setStatus("0");//0-未审核，1-已审核
		List<SystemMemberInfoRecord> list = systemMemberInfoRecordMapper.selectByRecordEntity(record);
		MemberInfoRecordResp resp = new MemberInfoRecordResp();
		if(list.size()==1){
			PropertyUtils.copyProperties(resp, list.get(0));
		}
		return resp;
	}

	@Override
	public List<MemberInfoRecordResp> findListByEntity(String memberid) throws Exception {
		SystemMemberInfoRecord record = new SystemMemberInfoRecord();
		record.setMemberid(memberid);
		List<SystemMemberInfoRecord> list = systemMemberInfoRecordMapper.selectByRecordEntity(record);
		return copyPropertiesInfoRecordList(list);
	}
	/**
	 * 集合类型转换
	 * @创建时间 2016年6月23日下午3:52:33
	 */
	public List<MemberInfoRecordResp> copyPropertiesInfoRecordList(List<SystemMemberInfoRecord> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<MemberInfoRecordResp> resp = new ArrayList<MemberInfoRecordResp>();
		for(SystemMemberInfoRecord record : list){
			MemberInfoRecordResp m = new MemberInfoRecordResp();
			PropertyUtils.copyProperties(m, record);
			resp.add(m);
		}
		return resp;
	}

	@Override
	public MemberInfoRecordResp findByMemberRecordId(String id) throws Exception {
		SystemMemberInfoRecord record = new SystemMemberInfoRecord();
		record.setId(id);
		List<SystemMemberInfoRecord> list = systemMemberInfoRecordMapper.selectByRecordEntity(record);
		MemberInfoRecordResp resp = new MemberInfoRecordResp();
		if(list.size()==1){
			PropertyUtils.copyProperties(resp, list.get(0));
		}
		return resp;
	}

	@Override
	public Result memberRecoredAutid(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMemberInfoRecord record = new SystemMemberInfoRecord();
		record.setMemberid(id);
		List<SystemMemberInfoRecord> list = systemMemberInfoRecordMapper.selectByRecordEntity(record);
		if(list.size()==1){
			rs.setData(list.get(0));
		}else {
			rs.setCode("1");
			rs.setError("用户尚未进行认证");
		}
		return rs;
	}

	@Override
	public Result findReason(String id,Long submitDate) throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMemberInfoRecord record = new SystemMemberInfoRecord();
		record.setMemberid(id);
		record.setSubmittime(submitDate);
		SystemMemberInfoRecord memberInfoRecord = systemMemberInfoRecordMapper.findReason(record);
		SystemMember  member = systemMemberMapper.selectByPrimaryKey(id);
		if(member!=null){
			memberInfoRecord.setRegisttime(member.getRegisttime());
			memberInfoRecord.setCellphone(member.getCellphone());
		}
		if(memberInfoRecord!=null){
			rs.setData(memberInfoRecord);
		}else{
			rs.setCode("1");
			rs.setError("未查到审核原因！");
		}
		return rs;
	}
}
