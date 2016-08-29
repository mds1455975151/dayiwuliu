package com.tianrui.service.admin.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.fabric.xmlrpc.base.Data;
import com.tianrui.api.admin.intf.IFreightInfoService;
import com.tianrui.api.req.admin.freight.AdminFreightReq;
import com.tianrui.api.req.admin.freight.AdminFreightUptReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.freight.AdminFreightResp;
import com.tianrui.api.resp.admin.freight.FreightLineResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.bean.Freight;
import com.tianrui.service.admin.bean.FreightInfo;
import com.tianrui.service.admin.mapper.FileFreightMapper;
import com.tianrui.service.admin.mapper.FreightInfoMapper;

@Service
public class FreightInfoService implements IFreightInfoService{

	@Autowired
	FreightInfoMapper freightInfoMapper;
	
	@Autowired
	FileFreightMapper freightMapper;
	@Override
	public PageResp<AdminFreightResp> find(AdminFreightReq req) throws Exception {
		FileFreight record = new FileFreight();
		PropertyUtils.copyProperties(record, req);
		long count = freightInfoMapper.selectByConunt(record);
		record.setLimit((req.getPageNo()-1)*req.getPageSize());
		record.setPageSize(req.getPageSize());
		List<FileFreight> list = freightInfoMapper.selectByCondition(record);
		PageResp<AdminFreightResp> vo = new PageResp<AdminFreightResp>();
		vo.setPageNo(req.getPageNo());
		vo.setPageSize(req.getPageSize());
		vo.setCount(count);
		vo.setList(copyProperties(list));
		return vo;
	}
	
	protected List<AdminFreightResp> copyProperties(List<FileFreight> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<AdminFreightResp> resp = new ArrayList<AdminFreightResp>();
		for(FileFreight info : list){
			AdminFreightResp ad = new AdminFreightResp();
			PropertyUtils.copyProperties(ad, info);
			resp.add(ad);
		}
		return resp;
	}

	@Override
	public Result update(AdminFreightUptReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		FreightInfo info = freightInfoMapper.selectByPrimaryKey(req.getInfoid());
		if(info == null){
			rs.setErrorCode(ErrorCode.FILE_FREIGHT_INFO);
			return rs;
		}
		if(!"0".equals(info.getStatus())){
			rs.setErrorCode(ErrorCode.FILE_FREIGHT_AUDIT0);
			return rs;
		}
		if("2".equals(req.getAudit())){
			if(StringUtils.isBlank(req.getAuditresson())){
				rs.setErrorCode(ErrorCode.FILE_FREIGHT_AUDIT_NULL);
				return rs;
			}
		}
		//修改认证信息
		info.setStatus(req.getAudit());
		info.setUpdater(req.getUpdater());
		info.setUpdatetime(new Date().getTime());
		freightInfoMapper.updateByPrimaryKeySelective(info);
		//修改运价策略
		FileFreight record = new FileFreight();
		record.setId(req.getId());
		record.setAuditreason(req.getAuditresson());
		record.setAuditstatus(req.getAudit());
		//1-审核成功 2-审核失败
		if("1".equals(req.getAudit())){
			record.setPrice(info.getPrice());
			record.setFreightType(info.getFreighttype());
			record.setTallage(info.getTallage());
			updatePlanAndBill(info);
		}else if("2".equals(req.getAudit())){
		}else{
			rs.setErrorCode(ErrorCode.FILE_FREIGHT_NULL);
			return rs;
		}
		if(1!=freightMapper.updateByPrimaryKeySelective(record)){
			rs.setErrorCode(ErrorCode.FILE_FREIGHT_ERROR);
			return rs;
		}
		return rs;
	}
	/** 修改 历史 计划，运单 单价*/
	protected void updatePlanAndBill(FreightInfo info) {
		freightInfoMapper.uptBilForFreight(info);
		freightInfoMapper.uptPlanForFreight(info);
	}
	
	@Override
	public List<FreightLineResp> lineChart(AdminFreightReq req) throws Exception {
		FreightInfo info = new FreightInfo();
		info.setFreightid(req.getId());
		info.setStatus("1");
		List<FreightInfo> list = freightInfoMapper.selectByInfo(info);
		return copyFreightLine(list); 
	}
	
	public List<FreightLineResp> copyFreightLine(List<FreightInfo> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<FreightLineResp> resp = new ArrayList<FreightLineResp>();
		for(FreightInfo info : list){
			FreightLineResp re = new FreightLineResp();
			PropertyUtils.copyProperties(re, info);
			resp.add(re);
		}
		return resp;
	}
}
