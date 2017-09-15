package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.api.req.front.bill.AnReportReq;
import com.tianrui.service.bean.anlian.AnReport;
import com.tianrui.service.bean.anlian.WuReport;

public interface AnWuReportMapper {
	//根据收货人id查找开票运单信息
    List<AnReport> selectAnReportCondition(AnReportReq req);
    //开票运单信息数量查询
    long queryAnReportCount(AnReportReq req);
    
    //根据收货人id查找普通运单信息
    List<WuReport> selectWuReportCondition(AnReportReq req);
    //普通运单信息数量查询
    long queryWuReportCount(AnReportReq req);
    
    //查询安联运单路线名称
    List<AnReport> findAnRoutename(AnReportReq req);
    //查询大易运单路线名称
    List<WuReport> findWuRoutename(AnReportReq req);
    
}
