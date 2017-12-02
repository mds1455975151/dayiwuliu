package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.MessagePush;

public interface MessagePushMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessagePush record);

    int insertSelective(MessagePush record);

    MessagePush selectByPrimaryKey(Long id);
    
    MessagePush selectLastMessagePush();

    int updateByPrimaryKeySelective(MessagePush record);

    int updateByPrimaryKey(MessagePush record);

	List<MessagePush> findPendingMessage();
}