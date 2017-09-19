package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.ReportPayAll;

public interface ReportPayAllMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReportPayAll record);

    int insertSelective(ReportPayAll record);

    ReportPayAll selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReportPayAll record);

    int updateByPrimaryKey(ReportPayAll record);
    
    List<ReportPayAll> selectByCondition(ReportPayAll record);
    
    long selectByCount(ReportPayAll record);
}