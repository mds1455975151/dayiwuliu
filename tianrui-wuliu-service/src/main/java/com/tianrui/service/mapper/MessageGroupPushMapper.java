package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.MessageGroupPush;

public interface MessageGroupPushMapper {
    int deleteByPrimaryKey(String id);

    int insert(MessageGroupPush record);

    int insertSelective(MessageGroupPush record);

    MessageGroupPush selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MessageGroupPush record);

    int updateByPrimaryKey(MessageGroupPush record);
    
    List<MessageGroupPush> selectByCondition(MessageGroupPush record);
    
    long selectByCount(MessageGroupPush record);
}