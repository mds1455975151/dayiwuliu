package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.ReportBillAll;

public interface ReportBillAllMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReportBillAll record);

    int insertSelective(ReportBillAll record);

    ReportBillAll selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReportBillAll record);

    int updateByPrimaryKey(ReportBillAll record);
    
    List<ReportBillAll> selectByCondition(ReportBillAll record);
    
    long selectByCount(ReportBillAll record);
}