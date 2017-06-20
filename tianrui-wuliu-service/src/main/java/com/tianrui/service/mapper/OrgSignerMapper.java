package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.OrgSigner;

public interface OrgSignerMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrgSigner record);

    int insertSelective(OrgSigner record);

    OrgSigner selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrgSigner record);

    int updateByPrimaryKey(OrgSigner record);
    
    List<OrgSigner> selectByCondition(OrgSigner record);
    
    long selectByCount(OrgSigner record);
}