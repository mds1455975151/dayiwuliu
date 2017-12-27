package com.tianrui.quartz.warning;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.req.money.TrackReq;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.CustomRcord;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.CustomRcordMapper;
/**
 * 定时跟踪在途车辆轨迹上传状态
 * @author Administrator
 *
 */
@Component 
public class TrackWarningTrigger {
	
	private Logger logger = LoggerFactory.getLogger(TrackWarningTrigger.class);
	/**
	 * 定时任务参数
	 */
	@Autowired
	CacheClient cacheClient;
	@Autowired
	CustomRcordMapper customRcordMapper;
	@Autowired
	BillMapper billMapper;
	@Scheduled(cron="0 0/1 *  * * ? ")
    public void getMessageAndPush() {  
    	Long st = new Date().getTime();
    	logger.info("定时器[TrackWarningTrigger]启动.时间是 :" + DateUtil.getDateString());  
    	int countONway  = 0;
    	int countAbnormal = 0;
        try {
        	//获取所有在途车辆
        	List<Bill> list = billMapper.selectPublic(new Bill());
        	countONway = list.size();
        	//循环判断是否存在跟踪器
        	for(Bill b : list){
        		String key = CacheHelper.buildKey(CacheModule.VEHICLE_TRACKING, b.getDriverid());
        		//获取原本跟踪器key值
        		TrackReq track = cacheClient.getObj(key, TrackReq.class);
        		if(null == track){//无跟踪器，记录轨迹异常信息
        			CustomRcord record = new CustomRcord();
        			record.setBillNo(b.getWaybillno());
        			record.setCellphone(b.getDrivertel());
        			record.setContactNumber(b.getDrivertel());//随车电话
        			record.setCreateTime(new Date().getTime());
        			record.setCustomerId(b.getDriverid());
        			record.setCustomerName(b.getDrivername());
        			record.setLossTime(new Date().getTime() - 4*60*1000);
        			record.setProblemType((byte)1);
        			record.setSolvingState((byte)0);
        			record.setVehicleNo(b.getVehicleno());
        			record.setIfCall((byte)0);
        			record.setIfPush((byte)0);
        			record.setIfSms((byte)0);
        			customRcordMapper.insert(record);
        			countAbnormal++;
        		}
        	}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        logger.info("定时任务[TrackWarningTrigger]完成.在途车辆：{}辆,未上传位置：{}辆,耗时：{}",new Object[]{countONway,countAbnormal,(new Date().getTime()-st)});
    }
	
}
