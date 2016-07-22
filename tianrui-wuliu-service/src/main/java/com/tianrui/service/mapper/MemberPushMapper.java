package com.tianrui.service.mapper;

import com.tianrui.service.bean.MemberPush;

public interface MemberPushMapper {
    int deleteByPrimaryKey(String id);

    int insert(MemberPush record);

    int insertSelective(MemberPush record);

    MemberPush selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MemberPush record);

    int updateByPrimaryKey(MemberPush record);
    
    //删除当前app绑定的推送
    int deleteByAppid(String appId);
	//删除当前用户绑定的推送
    int deleteByMemberId(String memberId);
    //获取用户
    MemberPush selectByMemberId(String membrid);
}