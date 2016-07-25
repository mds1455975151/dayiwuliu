package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ISendMobileMessage;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.app.AppGetCodeReq;
import com.tianrui.api.req.app.AppMemberReq;
import com.tianrui.api.req.common.SmsDetails;
import com.tianrui.api.req.front.member.MemberFindReq;
import com.tianrui.api.req.front.member.MemberMassageReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.member.MemberSaveReq;
import com.tianrui.api.req.front.member.MemberUpdateReq;
import com.tianrui.api.req.weixin.WeixinMemberReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.member.MemberInfoMassageResp;
import com.tianrui.api.resp.front.member.MemberInfoResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.member.SystemMemberResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.exception.ApplicationExectpion;
import com.tianrui.common.utils.MakePrimaryKey;
import com.tianrui.common.utils.MsgUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.common.vo.UserLoginVo;
import com.tianrui.service.admin.bean.FileOrg;
import com.tianrui.service.admin.bean.Members;
import com.tianrui.service.admin.mapper.FileOrgMapper;
import com.tianrui.service.bean.MemberFind;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;
import com.tianrui.service.util.GetApplicationPropertery;
@Service
public class SystemMemberService implements ISystemMemberService{
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	SystemMemberInfoMapper systemMemberInfoMapper;
	@Autowired
	CacheClient cacheClient;
	@Autowired
	ISendMobileMessage sendMobileMessage;
	@Autowired
	MemberVoService moberVoService;
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	
	@Override
	public MemberResp findMemberByTelnum(MemberReq req) throws Exception {
		SystemMember member = new SystemMember();
		member = copyPropertiesMemberReqTelnum(req);
		member.setCellphone(req.getTelnum());
		List<SystemMember> list = systemMemberMapper.selectByCondition(member);
		MemberResp resp = null;
		if(list.size()==1){
			SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(list.get(0).getId());
			//查询名下绑定车辆判断是否为车主
			VehicleDriver vd = new VehicleDriver();
			vd.setCreator(list.get(0).getId());
			long count = vehicleDriverMapper.selectCountByCondition(vd);
			resp = copyPropertiesMemberResp(list.get(0));
			resp.setVehicleOwnerState(count);
			resp.setUserName(info.getUsername());
			resp.setTelphone(info.getTelphone());
			resp.setIdentityCard(info.getIdcard());
			resp.setIdcardsImagePath(info.getIdcardimage());
			resp.setDriveImagePath(info.getDriverimage());
			resp.setCompanyName(info.getCompanyname());
			resp.setCompanyContact(info.getCompanycontact());
			resp.setContactTel(info.getCompanytel());
			resp.setCompanycode(info.getCompanycode());
			resp.setLicenseImagePath(info.getLicenseImagePath());
			return resp;
		}
		return resp;
	}
	/**
	 * 
	 * @描述:查询类型转换
	 * @param req
	 * @return
	 * @返回类型 SystemMember
	 * @创建人 lsj
	 * @创建时间 2016年6月20日下午5:14:48
	 */
	public SystemMember copyPropertiesMemberReqTelnum(MemberReq req){
		SystemMember member = new SystemMember();
		member.setCellphone(req.getTelnum());
		return member;
	}
	/**
	 * 
	 * @描述:添加类型转换
	 * @param req
	 * @return
	 * @返回类型 SystemMember
	 * @创建人 lsj
	 * @创建时间 2016年6月20日下午5:15:41
	 */
	public SystemMember copyPropertiesMemberSaveReq(MemberSaveReq req){
		SystemMember member = new SystemMember();
		member.setCellphone(req.getCellphone());
		member.setPassword(req.getPassword());
		member.setReferrer(req.getReferrer());
		member.setStatus(req.getStatus());
		//同一设置为未认证状态
		member.setUserpercheck(req.getPercheckstatus());
		member.setDriverpercheck(req.getPercheckstatus());
		member.setCompanypercheck(req.getPercheckstatus());
		member.setSourcetype(req.getSourcetype());
		member.setRegisttime(new Date().getTime());
		return member;
	}
	/**
	 * 
	 * @描述:返回对象类型转换
	 * @param member
	 * @return
	 * @返回类型 MemberResp
	 * @创建人 lsj
	 * @创建时间 2016年6月21日上午8:52:18
	 */
	public MemberResp copyPropertiesMemberResp(SystemMember member){
		MemberResp rs = new MemberResp();
		rs =new MemberResp();
		rs.setId(member.getId());
		rs.setCellPhone(member.getCellphone());
		rs.setNickname(member.getNickname());
		rs.setUserpercheck(member.getUserpercheck());
		rs.setDriverpercheck(member.getDriverpercheck());
		rs.setCompanypercheck(member.getCompanypercheck());
		rs.setSex(member.getSex());
		rs.setSourceType(member.getSourcetype());
		rs.setPassWord(member.getPassword());
		rs.setLastTime(member.getLasttime());
		rs.setAvatarspath(member.getAvatarspath());
		rs.setRegisttime(member.getRegisttime());
		rs.setStatus(member.getStatus());
		rs.setOrganizationid(member.getOrgid());
		return rs;
	}
	/**
	 * 
	 * @描述:membre修改类型转换
	 * @param req
	 * @return
	 * @返回类型 SystemMember
	 * @创建人 lsj
	 * @创建时间 2016年6月21日上午11:05:46
	 */
	public SystemMember copyPropertiesUpdateReq(MemberUpdateReq req){
		SystemMember member = new SystemMember();
		member.setId(req.getId());
		member.setNickname(req.getLoginname());
		member.setSex(req.getSex());
		member.setAvatarspath(req.getAvatarspath());
		member.setStatus(req.getStatus());
		member.setOrgid(req.getOrgid());
		member.setPassword(req.getPassword());
		return member;
	}

	@Override
	public boolean saveMember(MemberSaveReq memberSaveReq) throws Exception {
		String memberid = UUIDUtil.getId();
		SystemMember member = copyPropertiesMemberSaveReq(memberSaveReq);
		member.setId(memberid);
		int a = systemMemberMapper.insertSelective(member);
		//注册用户并新增用户信息
		SystemMemberInfo info = new SystemMemberInfo();
		info.setMemberid(memberid);
		info.setId(memberid);
		int b = systemMemberInfoMapper.insertSelective(info);
		if(a==1&&b==1){
			return true;
		}
		return false;
	}
	@Override
	public MemberResp login(MemberReq req) throws Exception {
		SystemMember member = new SystemMember();
		member.setCellphone(req.getTelnum());
		List<SystemMember> list = systemMemberMapper.selectByCondition(member);
		MemberResp resp = new MemberResp();
		if(list.size()!=1){
			throw new ApplicationExectpion("您输入的手机号未注册，请先注册或者重新输入...");
		}else if("0".equals(list.get(0).getStatus())){// 1-启用，0禁用
			throw new ApplicationExectpion("此账号以禁用，请联系管理员");
		} else if(list.get(0).getPassword().equalsIgnoreCase(req.getPassWord())){
			
			resp = copyPropertiesMemberResp(list.get(0));
			SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(list.get(0).getId());
			resp.setUserName(info.getUsername());
			resp.setTelphone(info.getTelphone());
			resp.setIdentityCard(info.getIdcard());
			resp.setIdcardsImagePath(info.getIdcardimage());
			resp.setDriveImagePath(info.getDriverimage());
			resp.setCompanyName(info.getCompanyname());
			resp.setCompanyContact(info.getCompanycontact());
			resp.setContactTel(info.getCompanytel());
			resp.setLicenseImagePath(info.getLicenseImagePath());
			//登陆时间
			SystemMember upt = new SystemMember();
			upt.setId(list.get(0).getId());
			upt.setLasttime(new Date().getTime());
			systemMemberMapper.updateByPrimaryKeySelective(upt);
		}else{
			throw new ApplicationExectpion("您输入的密码错误，请重新输入...");
		}
		return resp;
	}
	@Override
	public MemberResp findById(String id) throws Exception {
		SystemMember member = systemMemberMapper.selectByPrimaryKey(id);
		return copyPropertiesMemberResp(member);
	}
	@Override
	public boolean updateMember(MemberUpdateReq req) throws Exception {
		SystemMember member = copyPropertiesUpdateReq(req);
		int a = systemMemberMapper.updateByPrimaryKeySelective(member);
		if(a==1){
			moberVoService.flush(req.getId());
			return true;
		}
		return false;
	}
	@Override
	public PageResp<MemberResp> findMemberlist(MemberFindReq req) throws Exception {
		MemberFind find = new MemberFind();
		PropertyUtils.copyProperties(find, req);
		List<Members> list = systemMemberMapper.findsMemberList(find);
		long a = systemMemberMapper.findsMemberListCount(find);
		PageResp<MemberResp> page = new PageResp<MemberResp>();
		page.setList(copyProperties(list));
		page.setPageNo(req.getPageNo());
		page.setPageSize(req.getPageSize());
		page.setCount(a);
		return page;
	}
	
	public List<MemberResp> copyProperties(List<Members> memberList) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<MemberResp> list = new ArrayList<MemberResp>();
		for (Members member : memberList) {
			MemberResp resp = new MemberResp();
			PropertyUtils.copyProperties(resp, member);
			list.add(resp);
		}
		return list;
	}
	public List<SystemMemberResp> copyPropertiesListSystemMember(List<SystemMember> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<SystemMemberResp> resp = new ArrayList<SystemMemberResp>();
		for(SystemMember m : list){
			SystemMemberResp r = new SystemMemberResp();
			PropertyUtils.copyProperties(r, m);
			resp.add(r);
		}
		return resp;
	}
	@Override
	public List<SystemMemberResp> findMemberByMassage(MemberMassageReq req) throws Exception {
		SystemMember member = new SystemMember();
		PropertyUtils.copyProperties(member, req);
		List<SystemMember> list = systemMemberMapper.selectByCondition(member);
		return copyPropertiesListSystemMember(list);
	}
	@Override
	public MemberInfoMassageResp findInfoMassageById(String id) throws Exception {
		Members m = systemMemberMapper.findsMemberbyId(id);
		MemberInfoMassageResp resp = new MemberInfoMassageResp();
		PropertyUtils.copyProperties(resp, m);
		return resp;
	}
	@Override
	public Result getValCode(String telnum, String type, String resource) throws Exception {
		Result rs = Result.getSuccessResult();
		//验证码
		int userCode=(int)((Math.random()*9+1)*100000);
		if(!"".equals(telnum) && MakePrimaryKey.isMobileNO(telnum)){
			//拼装查询条件
			SystemMember member =new SystemMember();
			member.setCellphone(telnum);
			//查询db
			List<SystemMember> list =systemMemberMapper.selectByCondition(member);
			
			SystemMember m = null;
			if( CollectionUtils.isNotEmpty(list) ){
				m =list.get(0);
			}
			if("0".equals(type)  && m != null){
				rs.setCode("1"); 
				rs.setError("注册用户已存在");
			}else if ("1".equals(type)  && m == null) {
				rs.setCode("2"); 
				rs.setError("重置密码手机号不正确 ");
			}else if ("2".equals(type)  && m == null) {
				rs.setCode("3"); 
				rs.setError("登录手机号未注册，请先注册 ");
			}else {
				CacheModule module = null;
				if("app".equals(resource)){
					if("0".equals(type)){
						module = CacheModule.REGISTER_APP;
					}else if ("1".equals(type)) {
						module = CacheModule.RESETPASS_APP;
					}
					else if ("2".equals(type)) {
						module = CacheModule.MEMBERLOGIN_APP;
					}
				}else if ("pc".equals(resource)) {
					if("0".equals(type)){
						module = CacheModule.REGISTER;
					}else if ("1".equals(type)) {
						module = CacheModule.RESETPASS;
					}
					else if ("2".equals(type)) {
						module = CacheModule.ADMINLOGIN;
					}
				}
				
				String key = cacheClient.getCacheHelper().buildKey(module, new String[]{type,telnum});
				UserLoginVo value = (UserLoginVo) cacheClient.getObj(key, UserLoginVo.class);
				String msg="";
				String sendPermit=GetApplicationPropertery.getValueByKey("sendPermit");
				//判断是否第一次发送
				if(value==null){
					UserLoginVo u=new UserLoginVo();
					u.setCount(1);
					u.setStartTime(System.currentTimeMillis());
					u.setEndTime(System.currentTimeMillis());
					u.setUserCode(userCode);
					u.setUserName(telnum);
					cacheClient.saveObject(key, u, 60*30); //半小时内有效
					msg = MsgUtil.getSendmsg(userCode,type);
					SmsDetails sms = new SmsDetails();
					sms.setTelephoneReceiver(telnum);
					sms.setSmsContent(msg);
					//该处代码插入发送代码
					sendMobileMessage.sendMobileMessage(sms);
				}else{
					boolean isTimeOut = true;// 获取时间是否超时
					int count=value.getCount();
					long stime=value.getStartTime();
					long now=System.currentTimeMillis();
					isTimeOut = now - stime > 60 * 1000;
					if(isTimeOut && count<5){
						count=count+1;
						value.setCount(count);
						cacheClient.saveObject(key, value, Long.valueOf((30 * 60 - (now - stime) / 1000)).intValue());
					}else{
						if(count>=5){
							rs.setCode("4"); 
							rs.setError("用户频繁操作");
						}else{
							rs.setCode("5"); 
							rs.setError("一分钟内请求次数过多");
						}
					}
					msg = MsgUtil.getSendmsg(userCode,type);
					SmsDetails sms = new SmsDetails();
					sms.setTelephoneReceiver(telnum);
					sms.setSmsContent(msg);
					sendMobileMessage.sendMobileMessage(sms);
				}
				if(sendPermit.equals("0")){
					System.out.println(msg);
				}
			}
		}else{
			rs.setCode("11"); 
			rs.setError("手机号码不合法");
		}
		return rs;
	}
	@Override
	public Result wxlogin(WeixinMemberReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		
		//微信未绑定账户,验证手机号密码是否正确
		SystemMember member = new SystemMember();
		member.setCellphone(req.getCellPhone());
		List<SystemMember> list = systemMemberMapper.selectByCondition(member);
		
		MemberResp resp = new MemberResp();
		if(list.size()!=1){
			rs.setCode("1");
			rs.setError("您输入的手机号未注册，请先注册或者重新输入...");
			return rs;
		}else if("0".equals(list.get(0).getStatus())){// 1-启用，0禁用
			rs.setCode("1");
			rs.setError("此账号以禁用，请联系管理员");
			return rs;
		}else if(list.get(0).getPassword().equalsIgnoreCase(req.getPassWord())){
			resp = copyPropertiesMemberResp(list.get(0));
			SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(list.get(0).getId());
			resp.setUserName(info.getUsername());
			resp.setTelphone(info.getTelphone());
			resp.setIdentityCard(info.getIdcard());
			resp.setIdcardsImagePath(info.getIdcardimage());
			resp.setDriveImagePath(info.getDriverimage());
			resp.setCompanyName(info.getCompanyname());
			resp.setCompanyContact(info.getCompanycontact());
			resp.setContactTel(info.getCompanytel());
			resp.setLicenseImagePath(info.getLicenseImagePath());
			//登陆时间
			SystemMember upt = new SystemMember();
			upt.setId(list.get(0).getId());
			upt.setLasttime(new Date().getTime());
			upt.setOpenid(req.getOpenid());
			systemMemberMapper.updateByPrimaryKeySelective(upt);
		}else{
			rs.setCode("1");
			rs.setError("您输入的密码错误，请重新输入...");
			return rs;
		}
		rs.setData(resp);
		return rs;
	}
	@Override
	public MemberResp findByOpenid(String openid) throws Exception {
		// TODO Auto-generated method stub
		SystemMember member = new SystemMember();
		member.setOpenid(openid);
		List<SystemMember> list = systemMemberMapper.selectByCondition(member);
		MemberResp resp = new MemberResp();
		if(list.size()==1){
			resp = copyPropertiesMemberResp(list.get(0));
			SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(list.get(0).getId());
			resp.setUserName(info.getUsername());
			resp.setTelphone(info.getTelphone());
			resp.setIdentityCard(info.getIdcard());
			resp.setIdcardsImagePath(info.getIdcardimage());
			resp.setDriveImagePath(info.getDriverimage());
			resp.setCompanyName(info.getCompanyname());
			resp.setCompanyContact(info.getCompanycontact());
			resp.setContactTel(info.getCompanytel());
			resp.setLicenseImagePath(info.getLicenseImagePath());
			//登陆时间
			SystemMember upt = new SystemMember();
			upt.setId(list.get(0).getId());
			upt.setLasttime(new Date().getTime());
			systemMemberMapper.updateByPrimaryKeySelective(upt);
			return resp;
		}
		return null;
	}
	
	@Override
	public Result applogin(AppMemberReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		boolean successFlag =false;
		if( req !=null && StringUtils.isNotBlank(req.getAccount()) ){
			SystemMember quey =new SystemMember();
			quey.setCellphone(req.getAccount());
			List<SystemMember> dbList =systemMemberMapper.selectByCondition(quey);
			if( CollectionUtils.isNotEmpty(dbList) ){
				SystemMember loginUser = dbList.get(0);
				//用户登录类别 密码登录
				if( req.getLoginType() ==0 ){
					if( !StringUtils.equalsIgnoreCase(loginUser.getPassword(), req.getPswdMd5()) ){
						rs.setErrorCode(ErrorCode.MEMBER_LOGIN_PSWD_ERROR);
					}else{
						successFlag =true;
					}
				//验证码登录	
				}else{
					String key =CacheHelper.buildKey(CacheModule.MEMBERLOGIN_APP, new String[]{
							req.getAccount(),req.getAuthCode()});
					if(StringUtils.isNotBlank(cacheClient.getString(key))){
						successFlag =true;
						cacheClient.remove(key);
					}else{
						rs.setErrorCode(ErrorCode.MEMBER_LOGIN_PSWD_ERROR);
					}
				}
				if( successFlag ){
					String tokenId =UUIDUtil.getId();
					MemberVo userVO =moberVoService.get(loginUser.getId());
					userVO.setTokenId(tokenId);
					rs.setData(userVO);
					
					//缓存默认保存一天
					String key =CacheHelper.buildKey(CacheModule.MEMBERLOGIN_APP, tokenId);
					cacheClient.saveObject(key, userVO,7*24*60*60);
					//保存用户关于token相关的  用于控制用户只能登录一个
					String userKey =CacheHelper.buildKey(CacheModule.LOGIN_APP, userVO.getCellphone());
					cacheClient.saveString(userKey, tokenId,7*24*60*60);
				}
			}else{
				rs.setErrorCode(ErrorCode.MEMBER_LOGIN_NOREG_ERROR);
			}
			
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}
	@Override
	public Result sendAuthCodeMsg(AppGetCodeReq req) throws Exception {
		Result rs = getValCode(req.getAccount(),req.getType(),"app");
		return rs;
	}
	@Override
	public MemberInfoResp authenticationInfoByid(String id) throws Exception {
		Members fo = systemMemberMapper.findsMemberbyId(id);
		MemberInfoResp resp = new MemberInfoResp(); 
		resp.setAuditName(fo.getAuditName());
		resp.setAuditTime(fo.getAuditTime());
		resp.setSubmitDate(fo.getSubmitDate());
		resp.setCompanyAddress(fo.getCompanyAddress());
		resp.setCompanyContact(fo.getCompanyContact());
		resp.setCompanyName(fo.getCompanyName());
		resp.setContactTel(fo.getContactTel());
		resp.setDriveImagePath(fo.getDriveImagePath());
		resp.setId(fo.getId());
		resp.setIdcardsImagePath(fo.getIdcardsImagePath());
		resp.setIdentityCard(fo.getIdentityCard());
		return resp;
	}
}
