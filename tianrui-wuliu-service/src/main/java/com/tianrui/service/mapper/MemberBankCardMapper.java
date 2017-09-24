package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.MemberBankCard;

public interface MemberBankCardMapper {
    int deleteByPrimaryKey(String id);

    int insert(MemberBankCard record);

    int insertSelective(MemberBankCard record);

    MemberBankCard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MemberBankCard record);

    int updateByPrimaryKey(MemberBankCard record);
    
    List<MemberBankCard> selectByCondition(MemberBankCard record);
    
    List<MemberBankCard> selectAll();
    
    long selectBycount(MemberBankCard record);
    
    List<MemberBankCard> selectSelective(MemberBankCard record);
    
    MemberBankCard selectOwnerCard(MemberBankCard record);

    //银行卡审核
    Long bankcardByNum(MemberBankCard record);
    
}