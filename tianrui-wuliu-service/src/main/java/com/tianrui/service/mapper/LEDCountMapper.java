package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.LEDCount;

/** LED统计查询Mapper*/
public interface LEDCountMapper {

	/** 查询大易运单*/
	Double selectByBillDy(LEDCount record);
	/** 查询安联运单*/
	Double selectByBillAl(LEDCount record);
	/** 统计运费*/
	Double selectByPayAmount(LEDCount record);
	/** 安联货物统计*/
	List<LEDCount> selectByCargoAl(LEDCount record);
	/** 大易货物统计*/
	List<LEDCount> selectByCargoDy(LEDCount record);
	/** 查询车辆类型*/
	List<LEDCount> selectByVehicleType(LEDCount record);
	/** 车辆归属地*/
	List<LEDCount> selectByVehicleAddress(LEDCount record);
	/** 车辆使用频率安联*/
	List<LEDCount> selectByVehicleRateAl(LEDCount record);
	/** 车辆使用频率大易*/
	List<LEDCount> selectByVehicleRateDy(LEDCount record);
	/** 查询大易车主统计*/
	List<LEDCount> selectByVenderDy(LEDCount record);
	/** 查询安联车主统计*/
	List<LEDCount> selectByVenderAl(LEDCount record);
	/** 查询大易货主统计*/
	List<LEDCount> selectByOwnerDy(LEDCount record);
	/** 查询安联货主统计*/
	List<LEDCount> selectByOwnerAl(LEDCount record);
	/** 统计车辆数量*/
	Double selectByCountVehicle(LEDCount record);
	/** 统计安联运单数量*/
	Double selectByCountAl(LEDCount record);
	/** 统计大易运单数量*/
	Double selectByCountDy(LEDCount record);
	/** 活跃车辆*/
	Double selectByVehcileAct(LEDCount record);
	
}
