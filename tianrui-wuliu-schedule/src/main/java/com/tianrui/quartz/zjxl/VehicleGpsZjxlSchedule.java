package com.tianrui.quartz.zjxl;

import java.math.BigDecimal;
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
import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.api.req.admin.ZJXLVehicleReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.ZJXLVehicleResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.ZjxlResult;
import com.tianrui.service.bean.VehicleGpsZjxl;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mongo.VehicleGpsZjxlDao;
import com.tianrui.service.util.DemoMain;
import com.tianrui.service.util.DemoReturnBean;

@Component  
public class VehicleGpsZjxlSchedule {
	
	@Autowired
	CacheClient cache ;

	@Autowired
	VehicleGpsZjxlDao vehicleGpsZjxlDao;
	@Autowired
	ICrossVehicleService crossVehicleService;
	
	Logger logger = LoggerFactory.getLogger(VehicleGpsZjxlSchedule.class);
	
	@Scheduled(cron="0 0/3 * * * ?")
	public  void auditReport()throws Exception{
		long begin = System.currentTimeMillis();
		logger.info("查询车辆位置开始"+begin);
		if(Constant.ZJXL_STATIC.equals("1")){
			//中交兴路状态为 1  查询中交兴路位置
			ZJXLVehicleReq req = new ZJXLVehicleReq();
			req.setVehiclelogo("1");
			req.setCrossloge("1");
			PageResp<ZJXLVehicleResp> page = crossVehicleService.find(req);
			List<ZJXLVehicleResp> list = page.getList();
			String token = DemoMain.getToken();
			for(ZJXLVehicleResp vehNo : list){
				ZjxlResult bean = DemoMain.vLastLocation(vehNo.getVehicleno(),token);
				try {
					if(StringUtils.equals("1001", bean.getStatus())){
						logger.debug("查询车辆位置成功---"+vehNo.getVehicleno());
						JSONObject obj =  (JSONObject) bean.getResult();
						logger.debug("请求返回值code="+bean.getResult());
						VehicleGpsZjxl zjxl = vehicleGpsZjxlDao.getVehiclePosition(vehNo.getVehicleno());
						if(zjxl == null || zjxl.getUtc()!=Long.valueOf(obj.getString("utc"))){
							VehicleGpsZjxl t = new VehicleGpsZjxl();
							t.setAddr(obj.getString("adr"));
							t.setId(UUIDUtil.getId());
							Double lat = Double.valueOf(obj.getString("lat"))/600000;
							BigDecimal   blat   =   new   BigDecimal(lat);
							//保留6位小数
							Double   f1lat   =   blat.setScale(6,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
							Double lon = Double.valueOf(obj.getString("lon"))/600000;
							BigDecimal   blon   =   new   BigDecimal(lon);
							//保留6位小数
							Double   f1lon   =   blon.setScale(6,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
							t.setLat(f1lat);
							t.setLon(f1lon);
							t.setSpd(obj.getString("spd"));
							t.setUtc(Long.valueOf(obj.getString("utc")));
							t.setVehicleNo(vehNo.getVehicleno());
							t.setTimeStamp(System.currentTimeMillis());
							vehicleGpsZjxlDao.save(t);
							logger.info("本次查询保存车辆位置lat[{}],lon[{}]",f1lat,f1lon);
						}else{
							logger.info("车辆位置无变化-old_lat="+zjxl.getLat()+"-old_lon="+zjxl.getLon());
							logger.info("车辆位置无变化-new_lat="+obj.getString("lat")+"-new_lon="+obj.getString("lon"));
						}
					}else{
						logger.info("查询车辆位置失败---"+vehNo.getVehicleno());
					}
				} catch (Exception e) {
					logger.info("数据解析异常---"+e.getMessage());
				}
			}
		}else{
			logger.info("未开启中交兴路查询功能");
		}
		logger.info("查询车辆位置结束"+(System.currentTimeMillis()-begin));
	}
	
}
