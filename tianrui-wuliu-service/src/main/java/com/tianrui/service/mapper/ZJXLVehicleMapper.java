package com.tianrui.service.mapper;

import java.util.List;
import com.tianrui.service.bean.ZJXLVehicle;

public interface ZJXLVehicleMapper {
    int deleteByPrimaryKey(String id);

    int insert(ZJXLVehicle record);

    int insertSelective(ZJXLVehicle record);

    ZJXLVehicle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ZJXLVehicle record);

    int updateByPrimaryKey(ZJXLVehicle record);
    
    List<ZJXLVehicle> selectByCondition(ZJXLVehicle zjxlVehicle);
    /**
     * @Title: findsAuditReportListCount 
     * @Description: 查询总条数
     * @param @param record
     * @param @return   
     * @return long    
     * @throws
     */
    long findsZJXLVehicleListCount(ZJXLVehicle zjxlVehicle);
}