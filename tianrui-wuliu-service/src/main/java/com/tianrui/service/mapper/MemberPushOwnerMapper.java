package com.tianrui.service.mapper;

import com.tianrui.service.bean.MemberPushOwner;

public interface MemberPushOwnerMapper {
    int deleteByPrimaryKey(String id);

    int insert(MemberPushOwner record);

    int insertSelective(MemberPushOwner record);

    MemberPushOwner selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MemberPushOwner record);

    int updateByPrimaryKey(MemberPushOwner record);
    
    //删除当前app绑定的推送
    int deleteByAppid(String appId);
	//删除当前用户绑定的推送
    int deleteByMemberId(String memberId);
    //获取用户
    MemberPushOwner selectByMemberId(String membrid);
}