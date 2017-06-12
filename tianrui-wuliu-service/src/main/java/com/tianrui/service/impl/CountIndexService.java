package com.tianrui.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ICountIndexService;
import com.tianrui.common.utils.CountDateUtils;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.service.bean.CountAdd;
import com.tianrui.service.bean.CountSelect;
import com.tianrui.service.bean.CountSum;
import com.tianrui.service.mapper.CountAddMapper;
import com.tianrui.service.mapper.CountSelectMapper;
import com.tianrui.service.mapper.CountSumMapper;

@Service
public class CountIndexService implements ICountIndexService{

	Logger logger=LoggerFactory.getLogger(CountIndexService.class);
	
	@Autowired
	CountSelectMapper countSelectMapper;
	@Autowired
	CountSumMapper countSumMapper;
	@Autowired
	CountAddMapper countAddMapper;
	
	//每天凌晨2天统计昨天的
	//dataStr 格式:yyyy-MM-dd  2017-06-12
	public void everyDay(String dataStr) throws ParseException{
		//获取昨天时间
		Date currDate = new Date();
		long countMinTime =CountDateUtils.getLastDayMin(currDate);
		long countMaxTime =CountDateUtils.getLastDayMax(currDate);
		long countMinTimeLast30 =CountDateUtils.countMinTimeLast30(currDate);
		//如果有值则获取传入参数
		if( StringUtils.isNotBlank(dataStr) ){
			countMinTime=CountDateUtils.getLastDayMin(dataStr);
			countMaxTime=CountDateUtils.getLastDayMax(dataStr);
			countMinTimeLast30 =CountDateUtils.countMinTimeLast30(currDate);
		}
		
		//货运计划
		planCount(countMinTime,countMaxTime);
		//车辆
		vehicleCount(countMinTime,countMaxTime,countMinTimeLast30);
		//交易运单数  运单创建条数
		billCount(countMinTime,countMaxTime);
		//运费综合
		payCount(countMinTime,countMaxTime);
		//道路信息
	}
	
	/**
	 * 1  货运总数
	 * 2  货运新增数  
	 */
	private void  planCount(long minTime,long longMaxTime){
		//查询条件拼装
		CountSelect query = new CountSelect();
		query.setMaxQueryTime(longMaxTime);
		
		//查询总数
		CountSelect planSum = countSelectMapper.selectPlanCount(query);
		//统计表插入或者新增
		countSumUpsert(minTime,2,planSum.getTotalplanned());
		
		//查询当天
		query.setMinQueryTime(minTime);
		CountSelect planAdd = countSelectMapper.selectPlanCount(query);
		//统计表插入或者新增
		countAddUpsert(minTime,2,planAdd.getTotalplanned());
	}
	/**
	 * 1  车辆总数
	 * 2  活跃车辆数    30天内有过运单的车辆数量
	 * 3  新增数量
	 */
	private void vehicleCount(long minTime, long longMaxTime,long countMinTimeLast30) {
		// 查询条件拼装
		CountSelect query = new CountSelect();
		query.setMaxQueryTime(longMaxTime);

		// 查询总数
		CountSelect vehicleSum = countSelectMapper.selectVehicleCount(query);
		// 统计表插入或者新增
		countSumUpsert(minTime, 3, vehicleSum.getCount());

		// 查询当天
		query.setMinQueryTime(minTime);
		CountSelect vehicleAdd = countSelectMapper.selectVehicleCount(query);
		// 统计表插入或者新增
		countAddUpsert(minTime, 3, vehicleAdd.getCount());
		
		
		//活跃车辆数(30天内)
		query.setMinQueryTime(countMinTimeLast30);
		List<CountSelect> vehicAct = countSelectMapper.selectVehicleAct(query);
		//countSumUpsert();
		countSumUpsert(minTime, 6, vehicAct.size());
		
	}
	/**
	 * 1  运单总数
	 * 2  运单新增数  
	 */
	private void billCount(long minTime, long longMaxTime) {
		// 查询条件拼装
		CountSelect query = new CountSelect();
		query.setMaxQueryTime(longMaxTime);
		
		// 查询总数
		long count1= countSelectMapper.selectBillCount(query);
		//安联运单数
		long count2= countSelectMapper.selectAlBillCount(query);
		// 统计表插入或者新增
		countSumUpsert(minTime, 4, count1+count2);
		
		// 查询当天
		query.setMinQueryTime(minTime);
		count1 = countSelectMapper.selectBillCount(query);
		count2 = countSelectMapper.selectBillCount(query);
		// 统计表插入或者新增
		countAddUpsert(minTime, 4, count1+count2);
	}
	/**
	 * 1  运费总数
	 * 2  昨日运费数量  
	 */
	private void payCount(long minTime, long longMaxTime) {
		// 查询条件拼装
		CountSelect query = new CountSelect();
		query.setMaxQueryTime(longMaxTime);
		
		// 查询总数
		double count1= countSelectMapper.selectPayCount(query);
		//安联运单数
		double count2= countSelectMapper.selectAlPayCount(query);
		// 统计表插入或者新增
		countSumUpsert(minTime, 5, count1+count2);
		
		// 查询当天
		query.setMinQueryTime(minTime);
		count1 = countSelectMapper.selectBillCount(query);
		count2 = countSelectMapper.selectBillCount(query);
		// 统计表插入或者新增
		countAddUpsert(minTime, 5, count1+count2);
	}
	
	/**
	 * countsum的新增或者修改操作
	 * @param showTime  显示日期
	 * @param type  2:计划总量
	 * @param data  数量量
	 */
	private void countSumUpsert(long showTime,int type,double data){
		CountSum query = new CountSum();
		query.setType(String.valueOf(type));
		query.setShowtime(showTime);
		List<CountSum> list = countSumMapper.selectByCondition(query);
		//存在就修改
		if( CollectionUtils.isNotEmpty(list) ){
			CountSum db=list.get(0);
			db.setSumdate(data);
			db.setModifytime(System.currentTimeMillis());
			countSumMapper.updateByPrimaryKeySelective(db);
		}else{
			//不存在就新增
			CountSum insert = new CountSum();
			insert.setType(String.valueOf(type));
			insert.setShowtime(showTime);
			insert.setSumdate(data);
			insert.setCreatetime(System.currentTimeMillis());
			insert.setId(UUIDUtil.getId());
			insert.setModifytime(System.currentTimeMillis());
			countSumMapper.insert(insert);
		}
	}
	/**
	 * countAdd的新增或者修改操作
	 * @param showTime  显示日期
	 * @param type  2:计划总量
	 * @param data  数量量
	 */
	private void countAddUpsert(long showTime,int type,double data){
		CountAdd query = new CountAdd();
		query.setType(String.valueOf(type));
		query.setShowtime(showTime);
		List<CountAdd> list = countAddMapper.selectByCondition(query);
		//存在就修改
		if( CollectionUtils.isNotEmpty(list) ){
			CountAdd db=list.get(0);
			db.setAdddate(data);
			db.setModifytime(System.currentTimeMillis());
			countAddMapper.updateByPrimaryKeySelective(db);
		}else{
			//不存在就新增
			CountAdd insert = new CountAdd();
			insert.setType(String.valueOf(type));
			insert.setShowtime(showTime);
			insert.setAdddate(data);
			insert.setCreatetime(System.currentTimeMillis());
			insert.setId(UUIDUtil.getId());
			insert.setModifytime(System.currentTimeMillis());
			countAddMapper.insert(insert);
		}
	}

	
		
}
