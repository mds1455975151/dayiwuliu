package com.tianrui.service.mapper;

import com.tianrui.service.bean.MessageRolling;

public interface MessageRollingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageRolling record);

    int insertSelective(MessageRolling record);

    MessageRolling selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageRolling record);

    int updateByPrimaryKey(MessageRolling record);
}