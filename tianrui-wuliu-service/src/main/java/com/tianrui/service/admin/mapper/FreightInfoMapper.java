package com.tianrui.service.admin.mapper;

import java.util.List;

import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.bean.FreightInfo;

public interface FreightInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FreightInfo record);

    int insertSelective(FreightInfo record);

    FreightInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FreightInfo record);

    int updateByPrimaryKey(FreightInfo record);
    
    List<FileFreight> selectByCondition(FileFreight record);
    
    long selectByConunt(FileFreight record);
    
    List<FreightInfo> selectByInfo(FreightInfo record);
}