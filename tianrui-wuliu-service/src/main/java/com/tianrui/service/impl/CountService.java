package com.tianrui.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ICountService;
import com.tianrui.api.req.count.CountSelectReq;
import com.tianrui.common.utils.CountDateUtils;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.service.bean.CountAdd;
import com.tianrui.service.bean.CountSelect;
import com.tianrui.service.bean.CountSum;
import com.tianrui.service.mapper.CountAddMapper;
import com.tianrui.service.mapper.CountSelectMapper;
import com.tianrui.service.mapper.CountSumMapper;

@Service
public class CountService implements ICountService{

	Logger logger=LoggerFactory.getLogger(CountService.class);
	
	@Autowired
	CountSelectMapper countSelectMapper;
	@Autowired
	CountSumMapper countSumMapper;
	@Autowired
	CountAddMapper countAddMapper;
	
	
	@Override
	public void everyDay(CountSelectReq count)throws Exception {
		long time = getDay();
		
		CountSelect s = new CountSelect();
		PropertyUtils.copyProperties(s, count);
		
		//查询路线总数 -ok
		s.setStatus("1");
		long route = countSelectMapper.selectRouteCount(s);
		routeCountSave(route,time);
		
		//查询货物量 -ok
		s.setStatus("2");
		CountSelect plan = countSelectMapper.selectPlanCount(s);
		planCountSave(plan,time);
		
		//查询车辆总数 -ok
		s.setStatus("1");
		CountSelect vehcile= countSelectMapper.selectVehicleCount(s);
		VehicleCountSave(vehcile,time);
		
		//查询运单量 -ok
		s.setStatus(null);
		long bill = countSelectMapper.selectBillCount(s);
		billCoubtSave(bill,time);
		//运费总和 -ok
		s.setStatus(null);
		double pay = countSelectMapper.selectPayCount(s);
		payCountSave(pay,time);
		
		//查询活跃车辆 30天内 -ok
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE,-30);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		s.setStatus(null);
		s.setSelecttime(c.getTimeInMillis());
		List<CountSelect> vehicAct = countSelectMapper.selectVehicleAct(s);
		vehicleActSave(vehicAct,time);
		logger.info("查询路线总数="+route+"查询货物总量="+plan+"车辆总数="+vehcile+"活跃车辆="+vehicAct+"运单总量="+bill+"运费总和="+pay);
	}
	/** 插入运费总和*/
	public void payCountSave(double pay,long time){
		//'1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额,6-活跃车辆数',
		CountSum query = new CountSum();
		query.setType("5");
		query.setShowtime(time);
		List<CountSum> list = countSumMapper.selectByCondition(query);
		if(list.size()==0){
			CountSum sum = new CountSum();
			sum.setId(UUIDUtil.getId());
			sum.setType("5");
			sum.setSumdate((double)pay);
			sum.setShowtime(time);
			sum.setCreatetime(System.currentTimeMillis());
			countSumMapper.insertSelective(sum);
		}else{
			for(CountSum sum : list){
				sum.setSumdate(pay);
				sum.setModifytime(System.currentTimeMillis());
				countSumMapper.updateByPrimaryKeySelective(sum);
			}
		}
	}
	
	/** 插入交易总量*/
	public void billCoubtSave(long bill,long time){
		//'1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额,6-活跃车辆数',
		CountSum query = new CountSum();
		query.setType("4");
		query.setShowtime(time);
		List<CountSum> list = countSumMapper.selectByCondition(query);
		if(list.size()==0){
			CountSum sum = new CountSum();
			sum.setId(UUIDUtil.getId());
			sum.setType("4");
			sum.setSumdate((double)bill);
			sum.setShowtime(time);
			sum.setCreatetime(System.currentTimeMillis());
			countSumMapper.insertSelective(sum);
		}else{
			for(CountSum sum : list){
				sum.setSumdate((double)bill);
				sum.setModifytime(System.currentTimeMillis());
				countSumMapper.updateByPrimaryKeySelective(sum);
			}
		}
	}
	/** 插入活跃车辆*/
	public void vehicleActSave(List<CountSelect> vehcile,long time){
		//'1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额,6-活跃车辆数',
		CountSum query = new CountSum();
		query.setType("6");
		query.setShowtime(time);
		List<CountSum> list = countSumMapper.selectByCondition(query);
		if(list.size()==0){
			CountSum sum = new CountSum();
			sum.setId(UUIDUtil.getId());
			sum.setType("6");
			sum.setSumdate((double)vehcile.size());
			sum.setShowtime(time);
			sum.setCreatetime(System.currentTimeMillis());
			countSumMapper.insertSelective(sum);
		}else{
			for(CountSum sum : list){
				sum.setSumdate((double)vehcile.size());
				sum.setModifytime(System.currentTimeMillis());
				countSumMapper.updateByPrimaryKeySelective(sum);
			}
		}
	}
	/** 插入车辆总数*/
	public void VehicleCountSave(CountSelect vehcile,long time){
		//'1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额',
		CountSum query = new CountSum();
		query.setType("3");
		query.setShowtime(time);
		List<CountSum> list = countSumMapper.selectByCondition(query);
		if(list.size()==0){
			CountSum sum = new CountSum();
			sum.setId(UUIDUtil.getId());
			sum.setType("3");
			sum.setSumdate((double)vehcile.getCount());
			sum.setShowtime(time);
			sum.setCreatetime(System.currentTimeMillis());
			countSumMapper.insertSelective(sum);
		}else{
			for(CountSum sum : list){
				sum.setSumdate((double)vehcile.getCount());
				sum.setModifytime(System.currentTimeMillis());
				countSumMapper.updateByPrimaryKeySelective(sum);
			}
		}
	}
	
	/** 插入货物量*/
	public void planCountSave(CountSelect plan,long time){
		//'1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额',
		CountSum query = new CountSum();
		query.setType("2");
		query.setShowtime(time);
		List<CountSum> list = countSumMapper.selectByCondition(query);
		if(list.size()==0){
			CountSum sum = new CountSum();
			sum.setId(UUIDUtil.getId());
			sum.setType("2");
			sum.setSumdate(plan.getTotalplanned()-plan.getCompleted());
			sum.setShowtime(time);
			sum.setCreatetime(System.currentTimeMillis());
			countSumMapper.insertSelective(sum);
		}else{
			for(CountSum sum : list){
				sum.setSumdate(plan.getTotalplanned()-plan.getCompleted());
				sum.setModifytime(System.currentTimeMillis());
				countSumMapper.updateByPrimaryKeySelective(sum);
			}
		}
		
	}
	/** 插入路线总数*/
	public void routeCountSave(long count,long time){
		//获取当前时间 精确到日
		
		//'1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额',
		//判断是否有数据存在
		CountSum query = new CountSum();
		query.setType("1");
		query.setShowtime(time);
		List<CountSum> list = countSumMapper.selectByCondition(query);
		//当日数据不存在 -添加，存在 -修改
		if(list.size()==0){
			CountSum sum = new CountSum();
			sum.setId(UUIDUtil.getId());
			sum.setType("1");
			sum.setSumdate((double)count);
			sum.setShowtime(time);
			sum.setCreatetime(System.currentTimeMillis());
			countSumMapper.insertSelective(sum);
		}else{
			for(CountSum sum : list){
				sum.setSumdate((double)count);
				sum.setModifytime(System.currentTimeMillis());
				countSumMapper.updateByPrimaryKeySelective(sum);
			}
		}
	}
	
	/** 插入路线总数*/
	public void routeHotSave(int count,long time){
		//'1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额7-最热路线',
		//判断是否有数据存在
		CountSum query = new CountSum();
		query.setType("7");
		query.setShowtime(time);
		List<CountSum> list = countSumMapper.selectByCondition(query);
		//当日数据不存在 -添加，存在 -修改
		if(list.size()==0){
			CountSum sum = new CountSum();
			sum.setId(UUIDUtil.getId());
			sum.setType("7");
			sum.setSumdate((double)count);
			sum.setShowtime(time);
			sum.setCreatetime(System.currentTimeMillis());
			countSumMapper.insertSelective(sum);
		}else{
			for(CountSum sum : list){
				sum.setSumdate((double)count);
				sum.setModifytime(System.currentTimeMillis());
				countSumMapper.updateByPrimaryKeySelective(sum);
			}
		}
	}
	
	@Override
	public void everyHour(CountSelectReq count)throws Exception {
		CountSelect s = new CountSelect();
		PropertyUtils.copyProperties(s, count);
		long time = getDay();
		//新增路线数 -ok
		long routeAdd = countSelectMapper.selectRouteAdd(s);
		routeAddSave(routeAdd,time);
		logger.info("新增路线数="+routeAdd);
		//新增货物量 -ok
		s.setStatus("2");
		CountSelect plan = countSelectMapper.selectPlanCount(s);
		planAddSave(plan,time);
		logger.info("新增货物量="+plan);
		//新增车辆 -ok
		s.setStatus("1");
		CountSelect vehcile= countSelectMapper.selectVehicleCount(s);
		vehicleAddSave(vehcile,time);
		logger.info("车辆总数"+vehcile.toString());
		//新增运费 -ok
		s.setStatus(null);
		CountSelect pay = countSelectMapper.selectPayAdd(s);
		if(pay!=null){
			payAddSave(pay.getSum(),time);
		}else{
			payAddSave((double)0,time);
		}
		logger.info("新增运费"+pay);
	}
	
	public void payAddSave(Double pay,long time){
		//1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额
		CountAdd add = new CountAdd();
		add.setType("5");
		add.setShowtime(time);
		List<CountAdd> list =  countAddMapper.selectByCondition(add);
		if(list.size()==0){
			CountAdd c = new CountAdd();
			c.setId(UUIDUtil.getId());
			c.setType("5");
			c.setAdddate((double)pay);
			c.setShowtime(time);
			c.setCreatetime(System.currentTimeMillis());
			countAddMapper.insertSelective(c);
		}else{
			for(CountAdd d : list){
				d.setType("5");
				d.setAdddate((double)pay);
				d.setShowtime(time);
				d.setModifytime(System.currentTimeMillis());
				countAddMapper.updateByPrimaryKeySelective(d);
			}
		}
	}
	
	/**交易量：当日卸货完成运单数*/
	public void billAddSave(long bill,long time){
		//1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额
		CountAdd add = new CountAdd();
		add.setType("4");
		add.setShowtime(time);
		List<CountAdd> list =  countAddMapper.selectByCondition(add);
		if(list.size()==0){
			CountAdd c = new CountAdd();
			c.setId(UUIDUtil.getId());
			c.setType("4");
			c.setAdddate((double)bill);
			c.setShowtime(time);
			c.setCreatetime(System.currentTimeMillis());
			countAddMapper.insertSelective(c);
		}else{
			for(CountAdd d : list){
				d.setType("4");
				d.setAdddate((double)bill);
				d.setShowtime(time);
				d.setModifytime(System.currentTimeMillis());
				countAddMapper.updateByPrimaryKeySelective(d);
			}
		}
	};
	
	/** 本日新增车辆*/
	public void vehicleAddSave(CountSelect vehicle,long time){
		//1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额
		CountAdd add = new CountAdd();
		add.setType("3");
		add.setShowtime(time);
		List<CountAdd> list =  countAddMapper.selectByCondition(add);
		if(list.size()==0){
			CountAdd c = new CountAdd();
			c.setId(UUIDUtil.getId());
			c.setType("3");
			c.setAdddate((double)vehicle.getCount());
			c.setShowtime(time);
			c.setCreatetime(System.currentTimeMillis());
			countAddMapper.insertSelective(c);
		}else{
			for(CountAdd d : list){
				d.setType("3");
				d.setAdddate((double)vehicle.getCount());
				d.setShowtime(time);
				d.setModifytime(System.currentTimeMillis());
				countAddMapper.updateByPrimaryKeySelective(d);
			}
		}
		
	}
	/** 本月新增货物量*/
	public void planAddSave(CountSelect plan,long time){
		//1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额
		CountAdd add = new CountAdd();
		add.setType("2");
		add.setShowtime(time);
		List<CountAdd> list =  countAddMapper.selectByCondition(add);
		if(list.size()==0){
			CountAdd c = new CountAdd();
			c.setId(UUIDUtil.getId());
			c.setType("2");
			c.setAdddate(plan.getTotalplanned()-plan.getCompleted());
			c.setShowtime(time);
			c.setCreatetime(System.currentTimeMillis());
			countAddMapper.insertSelective(c);
		}else{
			for(CountAdd d : list){
				d.setType("2");
				d.setAdddate(plan.getTotalplanned()-plan.getCompleted());
				d.setShowtime(time);
				d.setModifytime(System.currentTimeMillis());
				countAddMapper.updateByPrimaryKeySelective(d);
			}
		}
		
	}
	/** 本月新增路线*/
	public void routeAddSave(long route,long time){
		//1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额
		CountAdd add = new CountAdd();
		add.setType("1");
		add.setShowtime(time);
		List<CountAdd> list =  countAddMapper.selectByCondition(add);
		if(list.size()==0){
			CountAdd c = new CountAdd();
			c.setId(UUIDUtil.getId());
			c.setType("1");
			c.setAdddate((double)route);
			c.setShowtime(time);
			c.setCreatetime(System.currentTimeMillis());
			countAddMapper.insertSelective(c);
		}else{
			for(CountAdd d : list){
				d.setType("1");
				d.setAdddate((double)route);
				d.setShowtime(time);
				d.setModifytime(System.currentTimeMillis());
				countAddMapper.updateByPrimaryKeySelective(d);
			}
		}
		
	}
	
	@Override
	public void routeHot(CountSelectReq count) throws Exception {
		//-ok
		CountSelect s = new CountSelect();
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE,-7);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		//查询最热路线
		s.setSelecttime(c.getTimeInMillis());
		List<CountSelect> list = countSelectMapper.selectRouteHot(s);
		routeHotSave(list.size(),getDay());
		logger.info("最热路线="+list);
	}
	//--ok
	@Override
	public void billEveryDay(CountSelectReq count) throws Exception {
		CountSelect s = new CountSelect();
		s.setStatus("5,6");
		s.setSelecttime(getDay());
		long c = countSelectMapper.selectBillCount(s);
		billAddSave(c, getDay());
		logger.info("每日运单量="+c);
	}

	public long getDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	@Override
	public void selectpostition() throws Exception {
		List<CountSelect> list = countSelectMapper.selectRouteMax(null);
		for (int i = 0; i < list.size(); i++) {
			CountSum quer = new CountSum();
			quer.setShowtime(getDay());
			quer.setRemark(list.get(i).getOpc());
			List<CountSum> qulist = countSumMapper.selectByCondition(quer);
			if(qulist.size()==0){
				CountSum sum = new CountSum();
				sum.setId(UUIDUtil.getId());
				sum.setDesc1(list.get(i).getOp());
				sum.setShowtime(getDay());
				sum.setRemark(list.get(i).getOpc());
				sum.setType("8");
				sum.setCreatetime(System.currentTimeMillis());
				sum.setSumdate((double)list.get(i).getCount());
				countSumMapper.insertSelective(sum);
			}else{
				CountSum sum = qulist.get(0);
				sum.setDesc1(list.get(i).getOp());
				sum.setShowtime(getDay());
				sum.setRemark(list.get(i).getOpc());
				sum.setType("8");
				sum.setModifytime(System.currentTimeMillis());
				sum.setSumdate((double)list.get(i).getCount());
				countSumMapper.updateByPrimaryKeySelective(sum);
			}
		}
		
		
	}
	@Override
	public void payDetil() throws Exception {
		CountSelect s = new CountSelect();
		s.setStatus("3");
		s.setSelecttime(getDay());
		//每日完成的支付单数
		long a = countSelectMapper.selectPayBillCount(s);
		CountAdd add = new CountAdd();
		add.setShowtime(getDay());
		add.setType("6");
		List<CountAdd> qulist = countAddMapper.selectByCondition(add);
		if(qulist.size()==0){
			CountAdd save = new CountAdd();
			save.setId(UUIDUtil.getId());
			save.setType("6");
			save.setAdddate((double)a);
			save.setShowtime(getDay());
			save.setCreatetime(System.currentTimeMillis());
			countAddMapper.insertSelective(save);
		}else{
			CountAdd save = qulist.get(0);
			save.setType("6");
			save.setAdddate((double)a);
			save.setShowtime(getDay());
			save.setModifytime(System.currentTimeMillis());
			countAddMapper.updateByPrimaryKeySelective(save);
		}
	}
	
}
