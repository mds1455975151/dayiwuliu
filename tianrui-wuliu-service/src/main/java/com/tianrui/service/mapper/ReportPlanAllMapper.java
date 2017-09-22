package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.ReportPlanAll;

public interface ReportPlanAllMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReportPlanAll record);

    int insertSelective(ReportPlanAll record);

    ReportPlanAll selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReportPlanAll record);

    int updateByPrimaryKey(ReportPlanAll record);
    
    List<ReportPlanAll> selectByCondition(ReportPlanAll record);
    
    long selectByCount(ReportPlanAll record);
    
    List<ReportPlanAll> selectByAllPlan(ReportPlanAll record);
}