package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.ReportPayAll;

public interface ReportPayAllMapper {
    int deleteByPrimaryKey(String id);
    /**
     * 根绝账单类型删除账单
     * @param type  1:安联运单 2:普通运单
     * @return
     */
    int deleteByType(String type);
    
    List<ReportPayAll> getReportPayAnlian();

    int insert(ReportPayAll record);
    int insertBatch(List<ReportPayAll> list);

    int insertSelective(ReportPayAll record);

    ReportPayAll selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReportPayAll record);

    int updateByPrimaryKey(ReportPayAll record);
    
    List<ReportPayAll> selectByCondition(ReportPayAll record);
    
    long selectByCount(ReportPayAll record);
}