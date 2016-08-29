package com.tianrui.service.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IAppVersionService;
import com.tianrui.api.resp.front.version.AppVersionResp;
import com.tianrui.service.bean.AppVersion;
import com.tianrui.service.mapper.AppVersionMapper;
@Service
public class AppVersionService implements IAppVersionService{

	@Autowired
	AppVersionMapper appVersionMapper;
	
	@Override
	public AppVersionResp selectByid(String appType)throws Exception {
		AppVersion app = appVersionMapper.selectByPrimaryKey(appType);
		AppVersionResp resp = new AppVersionResp();
		PropertyUtils.copyProperties(resp, app);
		return resp;
	}

}
