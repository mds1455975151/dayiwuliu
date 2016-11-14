package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.CountAdd;
import com.tianrui.service.bean.CountSelect;
import com.tianrui.service.bean.CountSum;

/**
 * 
 * @类描述：统计查询
 * @创建人：lsj
 * @创建时间：2016年10月26日下午2:58:15
 *
 * @修改人：lsj
 * @修改时间：2016年10月26日下午2:58:15
 * @修改备注：
 *
 */
public interface CountSelectMapper {
	/** 查询路线总数*/
	long selectRouteCount(CountSelect countData);
	/** 本月新增路线*/
	long selectRouteAdd(CountSelect countData);
	
	/** 查询最热路线*/
	List<CountSelect> selectRouteHot(CountSelect countData);
	
	/** 查询地图最多省份
	 * @return */
	List<CountSelect> selectRouteMax(CountSelect countData);
	
	/** 货运计划总量*/
	CountSelect selectPlanCount(CountSelect countData);
	
	/** 查询车辆总数*/
	CountSelect selectVehicleCount(CountSelect countData);
	/** 活跃车辆*/
	List<CountSelect> selectVehicleAct(CountSelect countData);
	
	/** 成运单量*/
	long selectBillCount(CountSelect countData);
	
	/** 运费总和*/
	long selectPayCount(CountSelect countData);
	//TODO
	/** 运费新增量*/
	CountSelect selectPayAdd(CountSelect countData);
}
