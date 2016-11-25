package com.tianrui.service.mapper;

import java.util.List;
import java.util.Map;

import com.tianrui.service.bean.Assess;

public interface AssessMapper {
    int deleteByPrimaryKey(String id);

    int insert(Assess record);

    int insertSelective(Assess record);

    Assess selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Assess record);

    int updateByPrimaryKey(Assess record);
    
    Assess queryAssessByBillId(String billid);
    
    List<Assess> queryAssessByVenderId(Map<String, Object> params);
    
}