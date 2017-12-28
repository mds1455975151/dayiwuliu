package com.tianrui.service.impl.message;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.req.money.TrackReq;
import com.tianrui.api.req.money.TrackSelectReq;
import com.tianrui.api.resp.money.CustomRcordResp;
import com.tianrui.api.tracking.intf.ITrackingService;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.CustomRcord;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.CustomRcordMapper;

@Service
public class TrackingService implements ITrackingService {

	@Autowired
	CacheClient cacheClient;
	@Autowired
	CustomRcordMapper customRcordMapper;
	static int warningTime = 4*60;//15分钟
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
					customRcord.setSolvingUserid("-1");
					customRcord.setSolvingUsername("系统跟踪");
				}else {//轨迹重连，但是超时
					customRcord.setSolvingState((byte)1);
				}
				customRcordMapper.updateByPrimaryKeySelective(customRcord);
			}
		}
		return rs;
	}
	@Override
	public PaginationVO<CustomRcordResp> select(TrackSelectReq req) throws Exception {
		PaginationVO<CustomRcordResp> page = new PaginationVO<CustomRcordResp>();
		CustomRcord query = new CustomRcord();
		PropertyUtils.copyProperties(query, req);
		if(req.getPageNo()!= null){
			query.setPageNo(req.getPageNo()*req.getPageSize());
			query.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		List<CustomRcord> list = customRcordMapper.selectByCondition(query);
		long a = customRcordMapper.selectByCount(query);
		List<CustomRcordResp> splist = copyProperties2(list);
		page.setList(splist);
		page.setTotal(a);
		return page;
	}
	private List<CustomRcordResp> copyProperties2(List<CustomRcord> list)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<CustomRcordResp> splist = new ArrayList<CustomRcordResp>();
		for(CustomRcord sp : list){
			CustomRcordResp resp = new CustomRcordResp();
			PropertyUtils.copyProperties(resp, sp);
			splist.add(resp);
		}
		return splist;
	}

}
