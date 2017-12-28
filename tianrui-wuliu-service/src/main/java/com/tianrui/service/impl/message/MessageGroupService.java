package com.tianrui.service.impl.message;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.tianrui.api.intf.ISendMobileMessage;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.message.intf.IMessageGroupService;
import com.tianrui.api.req.common.SmsDetails;
import com.tianrui.api.req.groupMsg.CustomRcordReq;
import com.tianrui.api.req.groupMsg.MemberGroupReq;
import com.tianrui.api.req.groupMsg.MessageGroupPushReq;
import com.tianrui.api.req.groupMsg.MessageGroupReq;
import com.tianrui.api.req.groupMsg.PushGroupMessageReq;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.groupMsg.MessageGroupPushResp;
import com.tianrui.api.resp.groupMsg.MessageGroupResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.MemberGroupEnum;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.OrgMember;
import com.tianrui.service.admin.mapper.OrgMemberMapper;
import com.tianrui.service.bean.CustomRcord;
import com.tianrui.service.bean.MemberPush;
import com.tianrui.service.bean.MemberPushOwner;
import com.tianrui.service.bean.MessageGroup;
import com.tianrui.service.bean.MessageGroupPush;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.mapper.CustomRcordMapper;
import com.tianrui.service.mapper.MemberPushMapper;
import com.tianrui.service.mapper.MemberPushOwnerMapper;
import com.tianrui.service.mapper.MessageGroupMapper;
import com.tianrui.service.mapper.MessageGroupPushMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.util.AndroidPushUtils;
import com.tianrui.service.util.BaiduPushUtils;
import com.tianrui.service.util.BaiduPushUtilsOwner;
import com.tianrui.service.util.IosPushUtils;

@Service
public class MessageGroupService implements IMessageGroupService{

	Logger logger=LoggerFactory.getLogger(MessageGroupService.class);
	
	@Autowired
	CustomRcordMapper customRcordMapper;
	@Autowired
	MemberPushMapper memberPushMapper;
	@Autowired
	MemberPushOwnerMapper memberPushOwnerMapper;
	@Autowired
	ISendMobileMessage sendMobileMessage;
	@Autowired
	MessageGroupPushMapper messageGroupPushMapper;
	@Autowired
	MessageGroupMapper messageGroupMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	OrgMemberMapper orgMemberMapper;
	
	@Override
	public Result uptMemberGroup(MemberGroupReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if(req.getGroupType()==MemberGroupEnum.GROUP_DRIVER.getType()){
			//司机分组-更新
			uptDriverGroup();
		}else if(req.getGroupType()==MemberGroupEnum.GROUP_VENDER.getType()){
			//车主分组
			uptVenderGroup();
		}else if(req.getGroupType()==MemberGroupEnum.GROUP_OWNER.getType()){
			//货主分组
			uptOwnerGroup();
		}else{
			logger.info("未找到消息分组");
			rs.setErrorCode(ErrorCode.MESSAGE_GROUP_GRNULL);
		}
		return rs;
	}

	private void uptOwnerGroup() throws PushClientException, PushServerException {
		String code = MemberGroupEnum.GROUP_OWNER.getCode();
		String groupName = MemberGroupEnum.GROUP_OWNER.getRemark();
		//更新分组
		uptBaiDuGroupOwner(code);
		
		SystemMember query = new SystemMember();
		query.setOrgid("notNull");
		//查询出所有货主
		List<SystemMember> list = systemMemberMapper.selectByCondition(query);
		
		List<String> android = new ArrayList<String>();
		List<String> ios = new ArrayList<String>();
		
		for(SystemMember member : list){
			MemberPushOwner push = memberPushOwnerMapper.selectByMemberId(member.getId());
			MessageGroup save = new MessageGroup();
			save.setId(UUIDUtil.getId());
			save.setGroupType(code);
			save.setGroupName(groupName);
			save.setCellphone(member.getCellphone());
			save.setUserName(member.getRemarkname());
			save.setUserPhone(member.getCellphone());
			if(push!=null){
				save.setChinnlId(push.getPushid());
				if(push.getApptype()==(byte)1){//android
					save.setChinnlType("3");
					//3
					android.add(push.getPushid());
					if(android.size()>=9){
						//最多保存9 推送并清空
						String[] ids = android.toArray(new String[android.size()]);
						BaiduPushUtilsOwner.addUserToTage(code, 3, ids);
						logger.info("添加用户至分组货主 android:"+ids.length);
						android.clear();
					}
				}else if(push.getApptype()==(byte)2){//IOS
					//4
					ios.add(push.getPushid());
					save.setChinnlType("4");
					if(ios.size()>=9){
						//最多保存9 推送并清空
						String[] ids = ios.toArray(new String[ios.size()]);
						BaiduPushUtilsOwner.addUserToTage(code, 4, ids);
						logger.info("添加用户至分组 货主ios:"+ids.length);
						ios.clear();
					}
				}
			}
			messageGroupMapper.insertSelective(save);
		}
		String[] and_ = android.toArray(new String[android.size()]);
		BaiduPushUtilsOwner.addUserToTage(code, 3, and_);
		logger.info("添加用户至分组货主android:"+and_.length);
		
		String[] ios_ = ios.toArray(new String[ios.size()]);
		BaiduPushUtilsOwner.addUserToTage(code, 4, ios_);
		logger.info("添加用户至分组司机货主 ios:"+ios_.length);
	}

	private void uptBaiDuGroupOwner(String code) throws PushClientException, PushServerException {
		//删除数据库分组
		messageGroupMapper.deleteByGroup(code);
		//删除分组
		BaiduPushUtilsOwner.deleteTage(code, 3);
		BaiduPushUtilsOwner.deleteTage(code, 4);
		//创建分组
		BaiduPushUtilsOwner.createTage(code, 3);
		BaiduPushUtilsOwner.createTage(code, 4);
	}

	private void uptVenderGroup() throws Exception {
		String code = MemberGroupEnum.GROUP_VENDER.getCode();
		String groupName = MemberGroupEnum.GROUP_VENDER.getRemark();
		//更新百度推送分组
		uptBaiDuPushGroup(code);
		List<MemberResp> list = systemMemberService.findAllVender();
		logger.info("车主集合数="+list.size());
		
		List<String> android = new ArrayList<String>();
		List<String> ios = new ArrayList<String>();
		for(MemberResp member : list){
			MemberPush push = memberPushMapper.selectByMemberId(member.getId());
			MessageGroup save = new MessageGroup();
			save.setId(UUIDUtil.getId());
			save.setGroupType(code);
			save.setGroupName(groupName);
			save.setCellphone(member.getCellPhone());
			save.setUserName(member.getRemarkname());
			save.setUserPhone(member.getCellPhone());
			if(push!=null){
				save.setChinnlId(push.getPushid());
				if(push.getApptype()==(byte)1){//android
					save.setChinnlType("3");
					//3
					android.add(push.getPushid());
					if(android.size()>=9){
						//最多保存9 推送并清空
						String[] ids = android.toArray(new String[android.size()]);
						BaiduPushUtils.addUserToTage(code, 3, ids);
						logger.info("添加用户至分组车主 android:"+ids.length);
						android.clear();
					}
				}else if(push.getApptype()==(byte)2){//IOS
					//4
					ios.add(push.getPushid());
					save.setChinnlType("4");
					if(ios.size()>=9){
						//最多保存9 推送并清空
						String[] ids = ios.toArray(new String[ios.size()]);
						BaiduPushUtils.addUserToTage(code, 4, ids);
						logger.info("添加用户至分组 车主ios:"+ids.length);
						ios.clear();
					}
				}
			}
			messageGroupMapper.insertSelective(save);
		}
		
		String[] and_ = android.toArray(new String[android.size()]);
		BaiduPushUtils.addUserToTage(code, 3, and_);
		logger.info("添加用户至分组 车主android:"+and_.length);
		
		String[] ios_ = ios.toArray(new String[ios.size()]);
		BaiduPushUtils.addUserToTage(code, 4, ios_);
		logger.info("添加用户至分组司机 车主 ios:"+ios_.length);
	}

	private void uptDriverGroup() throws PushClientException, PushServerException {
		
		String code = MemberGroupEnum.GROUP_DRIVER.getCode();
		String groupName = MemberGroupEnum.GROUP_DRIVER.getRemark();
		//更新百度推送分组
		uptBaiDuPushGroup(code);
		
		SystemMember query = new SystemMember();
		query.setDriverpercheck((short)1);
		//查询出所有司机
		List<SystemMember> list = systemMemberMapper.selectByCondition(query);
		logger.info("司机集合数="+list.size());
		List<String> android = new ArrayList<String>();
		List<String> ios = new ArrayList<String>();
		for(SystemMember member : list){
			MemberPush push = memberPushMapper.selectByMemberId(member.getId());
			MessageGroup save = new MessageGroup();
			save.setId(UUIDUtil.getId());
			save.setGroupType(code);
			save.setGroupName(groupName);
			save.setCellphone(member.getCellphone());
			save.setUserName(member.getRemarkname());
			save.setUserPhone(member.getCellphone());
			if(push!=null){
				save.setChinnlId(push.getPushid());
				if(push.getApptype()==(byte)1){//android
					save.setChinnlType("3");
					//3
					android.add(push.getPushid());
					if(android.size()>=9){
						//最多保存9 推送并清空
						String[] ids = android.toArray(new String[android.size()]);
						BaiduPushUtils.addUserToTage(code, 3, ids);
						logger.info("添加用户至分组司机 android:"+ids.length);
						android.clear();
					}
				}else if(push.getApptype()==(byte)2){//IOS
					//4
					ios.add(push.getPushid());
					save.setChinnlType("4");
					if(ios.size()>=9){
						//最多保存9 推送并清空
						String[] ids = ios.toArray(new String[ios.size()]);
						BaiduPushUtils.addUserToTage(code, 4, ids);
						logger.info("添加用户至分组 司机ios:"+ids.length);
						ios.clear();
					}
				}
			}
			messageGroupMapper.insertSelective(save);
		}
		
		String[] and_ = android.toArray(new String[android.size()]);
		BaiduPushUtils.addUserToTage(code, 3, and_);
		logger.info("添加用户至分组 司机android:"+and_.length);
		
		logger.info("ios.size()"+ios.size());
		String[] ios_ = ios.toArray(new String[ios.size()]);
		BaiduPushUtils.addUserToTage(code, 4, ios_);
		logger.info("添加用户至分组司机 ios:"+ios_.length);
	}

	private void uptBaiDuPushGroup(String code) throws PushClientException, PushServerException {
		//删除数据库分组
		messageGroupMapper.deleteByGroup(code);
		//删除分组
		BaiduPushUtils.deleteTage(code, 3);
		BaiduPushUtils.deleteTage(code, 4);
		//创建分组
		BaiduPushUtils.createTage(code, 3);
		BaiduPushUtils.createTage(code, 4);
	}
	
	@Override
	public PaginationVO<MessageGroupResp> selectMsgGroup(MessageGroupReq req) throws Exception {
		PaginationVO<MessageGroupResp> page = new PaginationVO<MessageGroupResp>();
		MessageGroup query = new MessageGroup();
		if(req.getPageNo()!=null){
			query.setPageNo(req.getPageNo()*req.getPageSize());
			query.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		if(req.getGroupType().equals("1")){
			query.setGroupType(MemberGroupEnum.GROUP_DRIVER.getCode());
		}else if(req.getGroupType().equals("2")){
			query.setGroupType(MemberGroupEnum.GROUP_VENDER.getCode());
		}else if(req.getGroupType().equals("3")){
			query.setGroupType(MemberGroupEnum.GROUP_OWNER.getCode());
		}
		
		List<MessageGroup> list = messageGroupMapper.selectByCondition(query);
		long a = messageGroupMapper.selectByCount(query);
		List<MessageGroupResp> resp = new ArrayList<MessageGroupResp>();
		for(MessageGroup sp : list){
			MessageGroupResp msg = new MessageGroupResp();
			PropertyUtils.copyProperties(msg, sp);
			resp.add(msg);
		}
		page.setList(resp);
		page.setTotal(a);
		return page;
	}

	@Override
	public PaginationVO<MessageGroupPushResp> selectMsgGroupPush(MessageGroupPushReq req) throws Exception {
		PaginationVO<MessageGroupPushResp> page = new PaginationVO<MessageGroupPushResp>();
		MessageGroupPush query = new MessageGroupPush();
		if(req.getPageNo()!=null){
			query.setPageNo(req.getPageNo()*req.getPageSize());
			query.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		List<MessageGroupPush> list = messageGroupPushMapper.selectByCondition(query);
		long a = messageGroupPushMapper.selectByCount(query);
		List<MessageGroupPushResp> resp = new ArrayList<MessageGroupPushResp>();
		for(MessageGroupPush pu : list){
			MessageGroupPushResp sp = new MessageGroupPushResp();
			PropertyUtils.copyProperties(sp, pu);
			resp.add(sp);
		}
		page.setList(resp);
		page.setTotal(a);
		return page;
	}
	
	@Override
	public Result uptErrMsg(CustomRcordReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		CustomRcord upt = new CustomRcord();
		upt.setId(req.getId());
		upt.setProblemDescribe(req.getProblemDescribe());
		upt.setSolvingState(req.getSolvingState());
		int a = customRcordMapper.updateByPrimaryKeySelective(upt);
		if(a==0){
			logger.info("未查到对应数据");
			rs.setErrorCode(ErrorCode.MESSAGE_GROUP_ERNULL);
		}
		return rs;
	}
	
	@Override
	public Result pushGroupMsg(PushGroupMessageReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		CustomRcord cust = customRcordMapper.selectByPrimaryKey(req.getId());
		if(cust != null){
			if("4".equals(req.getGroupType())){
				//用户消息推送
				if("2".equals(req.getMsgType())){
					//2-APP
					appMemberPush(req, rs, cust);
				}else if("1".equals(req.getMsgType())){
					//1-短信
					phoneMemberPush(req, rs, cust);
				}else if("3".equals(req.getMsgType())){
					//3-电话
					rs.setCode("123");
					rs.setError("功能开发中...");
				}
			}else{
				//TODO 分组消息推送
			}
		}else{
			logger.info("未查到对应数据");
			rs.setErrorCode(ErrorCode.MESSAGE_GROUP_ERNULL);
		}
		return rs;
	}

	private void phoneMemberPush(PushGroupMessageReq req, Result rs, CustomRcord cust) throws Exception {
		if(cust.getCustomerId().equals(req.getMemberId())){
			if(StringUtils.isNotBlank(req.getMsgTxt())){
				String cellPhone = cust.getCellphone();
				SmsDetails sms = new SmsDetails();
				sms.setTelephoneReceiver(cellPhone);
				sms.setSmsContent(req.getMsgTxt());
				sendMobileMessage.sendMobileMessage(sms);
				
				CustomRcord upt = new CustomRcord();
				upt.setId(cust.getId());
				upt.setIfSms((byte)1);
				customRcordMapper.updateByPrimaryKeySelective(upt);
			}else{
				logger.info("推送消息为空");
				rs.setErrorCode(ErrorCode.MESSAGE_GROUP_MSGNULL);
			}
		}else{
			logger.info("推送用户id有误");
			rs.setErrorCode(ErrorCode.MESSAGE_GROUP_ERMEMBER);
		}
	}

	private void appMemberPush(PushGroupMessageReq req, Result rs, CustomRcord cust) {
		if(cust.getCustomerId().equals(req.getMemberId())){
			if(StringUtils.isNotBlank(req.getMsgTxt())){
				//传入用户id 与  推送用户id相同
				MemberPush push = memberPushMapper.selectByMemberId(req.getMemberId());
				MemberPushOwner owner =memberPushOwnerMapper.selectByMemberId(req.getMemberId());
				if(push != null || owner != null){
					//车主 APP 推送
					if(push != null){
						String chinnl = push.getPushid();
						if(push.getApptype()==(byte)1){
							//android
							AndroidPushUtils.push(chinnl, req.getMsgTxt(), "123");
						}else if(push.getApptype()==(byte)2){
							//ios
							IosPushUtils.push(chinnl, req.getMsgTxt(), "123");
						}
					}
					//货主APP 推送
					if(owner != null){
						String chinnl = owner.getPushid();
						if(owner.getApptype()==(byte)1){
							//android
							AndroidPushUtils.pushOwner(chinnl, req.getMsgTxt(), "123");
						}else if(owner.getApptype()==(byte)2){
							//ios
							IosPushUtils.pushOwner(chinnl, req.getMsgTxt(), "123");
						}
					}
					CustomRcord upt = new CustomRcord();
					upt.setId(cust.getId());
					upt.setIfPush((byte)1);
					customRcordMapper.updateByPrimaryKeySelective(upt);
				}else{
					logger.info("未找到用户推送ID");
					rs.setErrorCode(ErrorCode.MESSAGE_GROUP_PUSHIDNULL);
				}
			}else{
				logger.info("推送消息为空");
				rs.setErrorCode(ErrorCode.MESSAGE_GROUP_MSGNULL);
			}
		}else{
			logger.info("推送用户id有误");
			rs.setErrorCode(ErrorCode.MESSAGE_GROUP_ERMEMBER);
		}
	}
}
