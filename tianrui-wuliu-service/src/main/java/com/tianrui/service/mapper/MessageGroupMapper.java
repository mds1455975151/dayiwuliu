package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.MessageGroup;

public interface MessageGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(MessageGroup record);

    int insertSelective(MessageGroup record);

    MessageGroup selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MessageGroup record);

    int updateByPrimaryKey(MessageGroup record);
    
    List<MessageGroup> selectByCondition(MessageGroup record);
    
    long selectByCount(MessageGroup record);
    
    int deleteByGroup(String groupType);
}