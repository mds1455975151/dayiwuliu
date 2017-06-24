package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.BankType;

public interface BankTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(BankType record);

    int insertSelective(BankType record);

    BankType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BankType record);

    int updateByPrimaryKey(BankType record);

	List<BankType> selectByCondtion(BankType record);
}