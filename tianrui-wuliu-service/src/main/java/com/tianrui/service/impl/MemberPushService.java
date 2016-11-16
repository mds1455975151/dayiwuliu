package com.tianrui.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberPushService;
import com.tianrui.api.req.front.member.MemberPushSaveReq;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberPush;
import com.tianrui.service.bean.MemberPushOwner;
import com.tianrui.service.mapper.MemberPushMapper;
import com.tianrui.service.mapper.MemberPushOwnerMapper;
import com.tianrui.service.util.AndroidPushUtils;
import com.tianrui.service.util.IosPushUtils;

@Service
public class MemberPushService implements IMemberPushService{
	@Autowired
	MemberPushMapper memberPushMapper;
	@Autowired
	MemberPushOwnerMapper memberPushOwnerMapper;

	@Override
	public Result savePush(MemberPushSaveReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getMemberId()) && StringUtils.isNotBlank(req.getPushId())&& StringUtils.isNotBlank(req.getAppId())){
			//删除当前app绑定的推送;
			memberPushMapper.deleteByAppid(req.getAppId());
			//删除当前用户绑定的推送
			memberPushMapper.deleteByMemberId(req.getMemberId());
			//添加当期数据
			MemberPush pushSave = new MemberPush();
			pushSave.setAppid(req.getAppId());
			pushSave.setMemberid(req.getMemberId());
			pushSave.setPushid(req.getPushId());
			pushSave.setCreatetime(System.currentTimeMillis());
			pushSave.setCreator(req.getMemberId());
			pushSave.setApptype((byte)req.getAppType());
			pushSave.setId(UUIDUtil.getId());
			memberPushMapper.insert(pushSave);
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	@Override
	public Result savePushOwner(MemberPushSaveReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getMemberId()) && StringUtils.isNotBlank(req.getPushId())&& StringUtils.isNotBlank(req.getAppId())){
			//删除当前app绑定的推送;
			memberPushOwnerMapper.deleteByAppid(req.getAppId());
			//删除当前用户绑定的推送
			memberPushOwnerMapper.deleteByMemberId(req.getMemberId());
			//添加当期数据
			MemberPushOwner pushSave = new MemberPushOwner();
			pushSave.setAppid(req.getAppId());
			pushSave.setMemberid(req.getMemberId());
			pushSave.setPushid(req.getPushId());
			pushSave.setCreatetime(System.currentTimeMillis());
			pushSave.setCreator(req.getMemberId());
			pushSave.setApptype((byte)req.getAppType());
			pushSave.setId(UUIDUtil.getId());
			memberPushOwnerMapper.insert(pushSave);
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}

	@Override
	public Result sendPsuhMesage(String memberId,final  String msg,final String code) throws Exception {
		//消息内容,消息code,用户id
		final MemberPush memberPush=memberPushMapper.selectByMemberId(memberId);
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);  
		fixedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				if( memberPush !=null  ){
					if( memberPush.getApptype() ==1 ){
						AndroidPushUtils.push(memberPush.getPushid(), msg, code);
					}else if(memberPush.getApptype() ==2){
						IosPushUtils.push(memberPush.getPushid(), msg, code);
					}
				}
			}
		});
		
		return Result.getSuccessResult();
	}
	@Override
	public Result sendPsuhOwnerMesage(String memberId,final  String msg,final String code) throws Exception {
		//消息内容,消息code,用户id
		final MemberPushOwner memberPush=memberPushOwnerMapper.selectByMemberId(memberId);
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);  
		fixedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				if( memberPush !=null  ){
					if( memberPush.getApptype() ==1 ){
						AndroidPushUtils.pushOwner(memberPush.getPushid(), msg, code);
					}else if(memberPush.getApptype() ==2){
						IosPushUtils.pushOwner(memberPush.getPushid(), msg, code);
					}
				}
			}
		});
		
		return Result.getSuccessResult();
	}

	@Override
	public Result deletePushWithMId(String memberId) {
		if( StringUtils.isNotBlank(memberId) ){
			memberPushMapper.deleteByMemberId(memberId);
		}
		return Result.getSuccessResult();
	}
	
	
}
