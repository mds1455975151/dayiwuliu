package com.tianrui.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.service.bean.ReportBillAll;

public interface ReportBillAllMapper {
    int deleteByPrimaryKey(String id);
    
    /**
     * 
     * @param type 运单类型 dy-大易运单 al-安联运单
     * @return
     */
    int deleteAnlianBill();
    /**
     * 删除平台运单
     * @return
     */
    int deletePtlianBill();

    int insert(ReportBillAll record);
    
    int insertBatch(List<ReportBillAll> list);

    int insertSelective(ReportBillAll record);

    ReportBillAll selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReportBillAll record);

    int updateByPrimaryKey(ReportBillAll record);
    
    List<ReportBillAll> selectByCondition(ReportBillAll record);
    
    long selectByCount(ReportBillAll record);
    
    long getAnlianBillCount();
    
    long getPtBillCount();
    
    List<ReportBillAll> getAnlianBill(@Param("limit")int limit,@Param("start")int start);
}