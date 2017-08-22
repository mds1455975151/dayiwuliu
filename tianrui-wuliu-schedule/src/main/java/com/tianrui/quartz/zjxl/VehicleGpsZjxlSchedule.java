package com.tianrui.quartz.zjxl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Visits;
import com.tianrui.common.vo.ZjxlResult;
import com.tianrui.quartz.pushNCTask.NCPushSchedule;
import com.tianrui.service.bean.VehicleGpsZjxl;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mongo.VehicleGpsZjxlDao;

@Component  
public class VehicleGpsZjxlSchedule {
	
	@Autowired
	CacheClient cache ;

	@Autowired
	VehicleGpsZjxlDao vehicleGpsZjxlDao;
	
	Logger logger = LoggerFactory.getLogger(NCPushSchedule.class);
	
	//@Scheduled(cron="0 0/1 * * * ?")
	public  void auditReport()throws Exception{
		long begin = System.currentTimeMillis();
		logger.info("查询车辆位置开始"+begin);
		List<String> list = new ArrayList<String>();
		list.add("豫AC7336");
		list.add("豫N81498");
		list.add("豫D68263");
		list.add("豫A5038K");
		list.add("豫P6K087");
		String token = getToken();
		for(String vehNo : list){
			ZjxlResult bean = DemoMain.vLastLocation(vehNo,token);
			try {
				if(StringUtils.equals("1001", bean.getStatus())){
					logger.debug("查询车辆位置成功---"+vehNo);
					JSONObject obj =  (JSONObject) bean.getResult();
					logger.debug("请求返回值code="+bean.getResult());
					VehicleGpsZjxl zjxl = vehicleGpsZjxlDao.getVehiclePosition(vehNo);
					if(zjxl == null || 
						(zjxl.getLat()!=Long.valueOf(obj.getString("lat"))&&
						  zjxl.getLon()!=Long.valueOf(obj.getString("lon")))){
						VehicleGpsZjxl t = new VehicleGpsZjxl();
						t.setAddr(obj.getString("adr"));
						t.setId(UUIDUtil.getId());
						t.setLat(Long.valueOf(obj.getString("lat"))/600000);
						t.setLon(Long.valueOf(obj.getString("lon"))/600000);
						t.setSpd(obj.getString("spd"));
						t.setUtc(Long.valueOf(obj.getString("utc")));
						t.setVehicleNo(vehNo);
						t.setTimeStamp(System.currentTimeMillis());
						vehicleGpsZjxlDao.save(t);
					}else{
						logger.info("车辆位置无变化-old_lat="+zjxl.getLat()+"-old_lon="+zjxl.getLon());
						logger.info("车辆位置无变化-new_lat="+obj.getString("lat")+"-new_lon="+obj.getString("lon"));
					}
				}else{
					logger.info("查询车辆位置失败---"+vehNo);
				}
			} catch (Exception e) {
				logger.info("数据解析异常---"+e.getMessage());
			}
		}
		logger.info("查询车辆位置结束"+(System.currentTimeMillis()-begin));
	}
	
	protected String getToken() {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		CacheClient cacheClient =wac.getBean(CacheClient.class);
		String key=CacheHelper.buildKey(CacheModule.ZJXL_TOKEN, "");
		String token = cacheClient.getObj(key, String.class);
		if(StringUtils.isBlank(token)){
			DemoReturnBean bean = DemoMain.login();
			if(bean.getStatus().equals("1001")){
				token = bean.getResult().toString();
				cacheClient.saveObject(key, token, 2*24*60*60);
			}
			logger.info("token为空请求token--"+token);
		}else{
			logger.info("token不为空--"+token);
		}
		return token;
	}
}
