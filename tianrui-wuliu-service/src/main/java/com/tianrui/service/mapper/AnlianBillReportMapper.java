package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.anlian.AnlianBillReport;

public interface AnlianBillReportMapper {
    
    List<AnlianBillReport> selectByCondition(AnlianBillReport record);
    
    long selectBycount(AnlianBillReport record);
}