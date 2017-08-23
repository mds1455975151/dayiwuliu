package com.tianrui.quartz.zjxl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.ZjxlResult;
import com.tianrui.quartz.pushNCTask.NCPushSchedule;
import com.tianrui.service.bean.VehicleGpsZjxl;
import com.tianrui.service.mongo.VehicleGpsZjxlDao;

@Component  
public class VehicleGpsZjxlSchedule {

	@Autowired
	VehicleGpsZjxlDao vehicleGpsZjxlDao;
	
	Logger logger = LoggerFactory.getLogger(NCPushSchedule.class);
	
	//@Scheduled(cron="0 0/1 * * * ?")
	public  void auditReport()throws Exception{
		logger.info("查询车辆位置开始");
		try {
			ZjxlResult bean = DemoMain.vLastLocation("豫AC7336");
			System.out.println(bean.getStatus());
			JSONObject obj =  (JSONObject) bean.getResult();
			logger.info("请求返回值code="+bean.getResult());
			VehicleGpsZjxl t = new VehicleGpsZjxl();
			t.setAddr(obj.getString("adr"));
			t.setId(UUIDUtil.getId());
			t.setLat(Long.valueOf(obj.getString("lat")));
			t.setLon(Long.valueOf(obj.getString("lon")));
			t.setSpd(obj.getString("spd"));
			t.setUtc(Long.valueOf(obj.getString("utc")));
			t.setVehicleNo("豫AC7336");
			t.setTimeStamp(System.currentTimeMillis());
			vehicleGpsZjxlDao.save(t);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("车辆位置获取异常");
		}
		logger.info("查询车辆位置结束");
	}
}
