package com.tianrui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IApiPostionService;
import com.tianrui.api.req.front.api.APIPositionReq;
import com.tianrui.common.constants.ApiErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.service.bean.ApiPosition;
import com.tianrui.service.mongo.ApiPositionDao;

/**
 * 安联对接接口
 * @author lixp 2016年12月30日 10:24:32
 */
@Service
public class ApiPositionService implements IApiPostionService{

	@Autowired
	ApiPositionDao appPositionDao; 
	@Override
	public ApiErrorCode save(APIPositionReq req) {
		ApiErrorCode rs =ApiErrorCode.API_SYSTEM_SUCCESS;
		try {
			ApiPosition apiPosition =new ApiPosition();
			apiPosition.setId(UUIDUtil.getId());
			apiPosition.setLat(req.getLat());
			apiPosition.setLng(req.getLng());
			apiPosition.setTrackingdate(req.getTrackingdate());
			apiPosition.setTrackingid(req.getTrackingid());
			apiPosition.setCreateTime(System.currentTimeMillis());
			appPositionDao.save(apiPosition);
		} catch (Exception e) {
			rs= ApiErrorCode.API_SYSTEM_ERROR;
		}
		return rs;
	}


}
