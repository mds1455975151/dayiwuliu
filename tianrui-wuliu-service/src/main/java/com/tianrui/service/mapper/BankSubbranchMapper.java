package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.BankSubbranch;

public interface BankSubbranchMapper {
    int deleteByPrimaryKey(String id);

    int insert(BankSubbranch record);

    int insertSelective(BankSubbranch record);

    BankSubbranch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BankSubbranch record);

    int updateByPrimaryKey(BankSubbranch record);

	List<BankSubbranch> selectByCondtion(BankSubbranch record);
}