package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IAccessLogService;
import com.tianrui.api.req.accessLog.AccessLogReq;
import com.tianrui.api.req.front.api.VehicleGpsReq;
import com.tianrui.api.resp.accessLog.AccessLogResp;
import com.tianrui.api.resp.front.api.APIVehicleGpsResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.AccessLog;
import com.tianrui.service.mapper.AccessLogMapper;

@Service
public class AccessLogService implements IAccessLogService{

	@Autowired
	AccessLogMapper accessLogMapper;
	
	@Override
	public PaginationVO<AccessLogResp> select(AccessLogReq req) throws Exception {
		PaginationVO<AccessLogResp> page = new PaginationVO<AccessLogResp>();
		AccessLog query = new AccessLog();
		PropertyUtils.copyProperties(query, req);
		if(req.getPageNo()!=null){
			query.setPageNo(req.getPageNo()*req.getPageSize());
			query.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		List<AccessLog> list = accessLogMapper.selectByCondition(query);
		long total = accessLogMapper.selectByCount(query);
		page.setList(copyProperties2(list));
		page.setTotal(total);
		return page;
	}

	protected List<AccessLogResp> copyProperties2(List<AccessLog> list) throws Exception {
		List<AccessLogResp> resp = new ArrayList<AccessLogResp>();
		for(AccessLog log : list){
			AccessLogResp sp = new AccessLogResp();
			PropertyUtils.copyProperties(sp, log);
			resp.add(sp);
		}
		return resp;
	}
	
	@Override
	public Result save(AccessLogReq req) throws Exception {
		Result rs= Result.getSuccessResult();
		AccessLog save = new AccessLog();
		PropertyUtils.copyProperties(save, req);
		save.setId(UUIDUtil.getId());
		save.setCreatetime(System.currentTimeMillis());
		accessLogMapper.insertSelective(save);
		return rs;
	}

	@Override
	public Result saveLog(Result rs, VehicleGpsReq req, String url) throws Exception {
		Result resp = Result.getSuccessResult();
		try {
			AccessLogReq save = new AccessLogReq();
			if(StringUtils.isNotBlank(req.getVehicleNO())){
				save.setVehicleNo(req.getVehicleNO());
			}
			if(StringUtils.isNotBlank(req.getBeginTime())){
				save.setBeginTime(req.getBeginTime());
			}
			if(StringUtils.isNotBlank(req.getEndTime())){
				save.setEndTime(req.getEndTime());
			}
			if(StringUtils.isNotBlank(req.getToken())){
				save.setSystemToken(req.getToken());
			}
			save.setSystemUrl(url);
			save.setRespCode(rs.getCode());
			if(StringUtils.isNotBlank(rs.getError())){
				save.setRespError(rs.getError());
			}
			if(rs.getData()!=null){
				save.setRespData(rs.getData().toString());
				List<APIVehicleGpsResp> list = (List<APIVehicleGpsResp>) rs.getData();
				save.setRespTotal(String.valueOf(list.size()));
			}
			resp = save(save);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

}
