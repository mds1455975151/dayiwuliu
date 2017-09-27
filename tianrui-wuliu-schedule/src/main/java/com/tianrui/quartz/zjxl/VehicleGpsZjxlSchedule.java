package com.tianrui.quartz.zjxl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.xmlrpc.base.Array;
import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.api.req.admin.ZJXLVehicleReq;
import com.tianrui.api.resp.admin.ZJXLVehicleResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.ZjxlResult;
import com.tianrui.service.bean.VehicleGpsZjxl;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.mongo.VehicleGpsZjxlDao;
import com.tianrui.service.util.DemoMain;

@Component
public class VehicleGpsZjxlSchedule {

	@Autowired
	CacheClient cache;

	@Autowired
	VehicleGpsZjxlDao vehicleGpsZjxlDao;
	@Autowired
	ICrossVehicleService crossVehicleService;

	Logger logger = LoggerFactory.getLogger(VehicleGpsZjxlSchedule.class);

	@Scheduled(cron = "0 0/9 * * * ?")
	public void auditReport() throws Exception {
		long begin = System.currentTimeMillis();

		int successFlag = 0;
		int noChangeFlag = 0;
		int falseFlag = 0;
		int index = 0;
		if (Constant.ZJXL_STATIC.equals("1")) {
			// 中交兴路状态为 1 查询中交兴路位置
			ZJXLVehicleReq req = new ZJXLVehicleReq();
			req.setVehiclelogo("1");
			req.setCrossloge("1");
			List<ZJXLVehicleResp> list = crossVehicleService.findList(req);
			String token = DemoMain.getToken();
			
			List<String> errorList=new ArrayList<String>();
			
			for (ZJXLVehicleResp vehNo : list) {
				logger.info("本次查询总数[{}],查询到第[{}]条", list.size(), index);
				index++;
				ZjxlResult bean = DemoMain.vLastLocation(vehNo.getVehicleno(), token);
				try {
					if (StringUtils.equals("1001", bean.getStatus())) {
						JSONObject obj = (JSONObject) bean.getResult();
						VehicleGpsZjxl zjxl = vehicleGpsZjxlDao.getVehiclePosition(vehNo.getVehicleno());
						if (zjxl == null || zjxl.getUtc() != Long.valueOf(obj.getString("utc"))) {
							VehicleGpsZjxl t = new VehicleGpsZjxl();
							t.setAddr(obj.getString("adr"));
							t.setId(UUIDUtil.getId());
							Double lat = Double.valueOf(obj.getString("lat")) / 600000;
							BigDecimal blat = new BigDecimal(lat);
							// 保留6位小数
							Double f1lat = blat.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
							Double lon = Double.valueOf(obj.getString("lon")) / 600000;
							BigDecimal blon = new BigDecimal(lon);
							// 保留6位小数
							Double f1lon = blon.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
							t.setLat(f1lat);
							t.setLon(f1lon);
							t.setSpd(obj.getString("spd"));
							t.setUtc(Long.valueOf(obj.getString("utc")));
							t.setVehicleNo(vehNo.getVehicleno());
							t.setTimeStamp(System.currentTimeMillis());
							vehicleGpsZjxlDao.save(t);
							logger.debug("本次查询保存车辆位置lat[{}],lon[{}]", f1lat, f1lon);
							successFlag++;
						} else {
							logger.debug("车辆位置无变化-old_lat={} -old_lon={}", zjxl.getLat(), zjxl.getLon());
							noChangeFlag++;
						}
					} else {
						logger.info("查询车辆位置失败{}", vehNo.getVehicleno());
						falseFlag++;
						errorList.add(vehNo.getVehicleno());
					}
				} catch (Exception e) {
					logger.error("中交兴路", e.getMessage(), e);
				}
			}
			if( CollectionUtils.isNotEmpty(errorList) ){
				for(String vehicleNo :errorList ){
					crossVehicleService.updateVehicleLogo(vehicleNo);
					logger.info("禁用无位置车辆{}",vehicleNo);
				}
			}
		} else {
			logger.info("未开启中交兴路查询功能");
		}
		logger.info("中交兴路查询车辆位置结束,耗时{}ms;{}条成功更改位置;{}条无更新;{}条调用失败;",
				new Object[] { (System.currentTimeMillis() - begin), successFlag, noChangeFlag, falseFlag });
	}

}
