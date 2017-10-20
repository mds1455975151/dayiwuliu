package com.tianrui.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.service.bean.BillCount;
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
    int deletePtBill();

    int insert(ReportBillAll record);
    
    int insertBatch(List<ReportBillAll> list);

    int insertSelective(ReportBillAll record);
    
    ReportBillAll selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReportBillAll record);

    int updateByPrimaryKey(ReportBillAll record);
    
    List<ReportBillAll> selectByCondition(ReportBillAll record);
    
    BillCount selectByBillCount(ReportBillAll record);
    
    long selectByCount(ReportBillAll record);
    
    long getAnlianBillCount();
    
    long getPtBillCount();
    /**
     * 获取所有的大易运单ids
     * @return
     */
    List<String> selectPtBillIds();
    /**
     * 获取所有的开票运单ids
     * @return
     */
    List<String> selectAnlianBillIds();
    
    List<ReportBillAll> getAnlianBill(@Param("limit")int limit,@Param("start")int start);
    List<ReportBillAll> getPtBill(@Param("limit")int limit,@Param("start")int start);
}