package com.tianrui.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IFileFreightService;
import com.tianrui.service.admin.mapper.FileFreightMapper;
/**
 * 
 * @类描述：运价管理
 * @创建人：lsj
 * @创建时间：2016年5月26日上午10:12:13
 *
 * @修改人：lsj
 * @修改时间：2016年5月26日上午10:12:13
 * @修改备注：
 *
 */
@Service
public class FileFreightService implements IFileFreightService{
	
	@Autowired
	FileFreightMapper freightMapper;
	
}
