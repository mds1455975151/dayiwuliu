package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.PlanGoods;

public interface PlanGoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlanGoods record);

    int insertSelective(PlanGoods record);

    PlanGoods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlanGoods record);

    int updateByPrimaryKey(PlanGoods record);
    
    List<PlanGoods> selectByCondition(PlanGoods record);
    
    long selectByCount(PlanGoods record);
}