package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.api.req.money.AppMessageReq;
import com.tianrui.api.resp.money.MessageRollingResp;
import com.tianrui.service.bean.MessageRolling;

public interface MessageRollingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageRolling record);

    int insertSelective(MessageRolling record);

    MessageRolling selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageRolling record);

    int updateByPrimaryKey(MessageRolling record);

	List<MessageRollingResp> selectByCondition(AppMessageReq req);
}