package com.tianrui.service.impl.message;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianrui.api.req.money.TrackReq;
import com.tianrui.api.tracking.intf.ITrackingService;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.CustomRcord;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.CustomRcordMapper;

public class TrackingService implements ITrackingService {

	@Autowired
	CacheClient cacheClient;
	@Autowired
	CustomRcordMapper customRcordMapper;
	static int warningTime = 15*60;//15分钟
	@Override
	public Result save(TrackReq req) {
		Result rs = Result.getSuccessResult();
		String key = CacheHelper.buildKey(CacheModule.VEHICLE_TRACKING, req.getDriverid());
		//获取原本跟踪器key值
		TrackReq track = cacheClient.getObj(key, TrackReq.class);
		//新建或者更新跟踪器
		cacheClient.saveObject(key, req, warningTime);
		if(null == track ){
			//获取轨迹异常记录
			CustomRcord customRcord = customRcordMapper.selectByCustomerId(req.getDriverid());
			if(null != customRcord){
				customRcord.setReconnectionTime(req.getBeginTime());
				//更新异常记录
				if((req.getBeginTime() - customRcord.getLossTime()) < warningTime*1000){//轨迹自动恢复
					customRcord.setEndTime(req.getEndTime());
					customRcord.setSolvingState((byte)2);
					customRcord.setProblemDescribe("间歇性网络异常，已自动恢复");
					customRcord.setCustomerId("-1");
					customRcord.setCustomerName("系统跟踪");
				}else {//轨迹重连，但是超时
					customRcord.setSolvingState((byte)1);
				}
				customRcordMapper.updateByPrimaryKeySelective(customRcord);
			}
		}
		return rs;
	}

}
