package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.AddVehicleBankCard;

public interface AddVehicleBankCardMapper {
	 int deleteByPrimaryKey(String id);
	 int insert(AddVehicleBankCard addVehicleBankCard);
	 int insertSelective(AddVehicleBankCard record);
	 AddVehicleBankCard selectByPrimaryKey(String id);
	 int updateByPrimaryKeySelective(AddVehicleBankCard record);
	 List<AddVehicleBankCard> selectAddVehicleBankCard(AddVehicleBankCard addVehicleBankCard);
}
