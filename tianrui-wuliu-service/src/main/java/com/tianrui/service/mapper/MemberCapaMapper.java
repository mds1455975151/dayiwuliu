package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.MemberCapa;

public interface MemberCapaMapper {
    int deleteByPrimaryKey(String id);

    int insert(MemberCapa record);

    int insertSelective(MemberCapa record);

    MemberCapa selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MemberCapa record);

    int updateByPrimaryKey(MemberCapa record);
    
    List<MemberCapa> selectByCondition(MemberCapa record);
    
    long selectByCount(MemberCapa record);
}