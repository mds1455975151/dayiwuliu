package com.tianrui.service.impl.message;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			//司机分组
			String code = MemberGroupEnum.GROUP_DRIVER.getCode();
			String groupName = MemberGroupEnum.GROUP_DRIVER.getRemark();
			SystemMember query = new SystemMember();
			query.setDriverpercheck((short)1);
			//查询出所有司机
			List<SystemMember> list = systemMemberMapper.selectByCondition(query);
			logger.info("司机集合数="+list.size());
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
					save.setChinnlType(push.getApptype().toString());
				}
				messageGroupMapper.insertSelective(save);
			}
		}else if(req.getGroupType()==MemberGroupEnum.GROUP_VENDER.getType()){
			//车主分组
			String code = MemberGroupEnum.GROUP_VENDER.getCode();
			String groupName = MemberGroupEnum.GROUP_VENDER.getRemark();
			List<MemberResp> list = systemMemberService.findAllVender();
			logger.info("车主集合数="+list.size());
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
					save.setChinnlType(push.getApptype().toString());
				}
				messageGroupMapper.insertSelective(save);
			}
		}else if(req.getGroupType()==MemberGroupEnum.GROUP_OWNER.getType()){
			//货主分组
			String code = MemberGroupEnum.GROUP_OWNER.getCode();
			String groupName = MemberGroupEnum.GROUP_OWNER.getRemark();
			SystemMember query = new SystemMember();
			query.setOrgid("notNull");
			//查询出所有司机
			List<SystemMember> list = systemMemberMapper.selectByCondition(query);
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
					save.setChinnlType(push.getApptype().toString());
				}
				messageGroupMapper.insertSelective(save);
			}
		}else{
			logger.info("未找到消息分组");
			rs.setErrorCode(ErrorCode.MESSAGE_GROUP_GRNULL);
		}
		return rs;
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
