package com.tianrui.service.admin.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IFreightInfoService;
import com.tianrui.api.req.admin.freight.AdminFreightReq;
import com.tianrui.api.req.admin.freight.AdminFreightUptReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.freight.AdminFreightResp;
import com.tianrui.api.resp.admin.freight.FreightLineResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileFreight;
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
		FileFreight record = freightMapper.selectByPrimaryKey(req.getId());
		if(record.getModifytime()!=null){
			if(!record.getModifytime().equals(req.getModifytime())){
//				if(record.getModifytime()!=req.getModifytime()){
				rs.setErrorCode(ErrorCode.FILE_FREIGHT_UPT);
				return rs;
			}
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
		//获取当前时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = sdf.format(date);
		Date te = sdf.parse(dateStr);
		Long sta = te.getTime();
		
		//修改认证信息
		info.setStatus(req.getAudit());
		info.setUpdater(req.getUpdater());
		info.setUpdatetime(new Date().getTime());
		if(info.getTaketime()==null){
			info.setTaketime(sta);
		}
		freightInfoMapper.updateByPrimaryKeySelective(info);
		//修改运价策略
		
		record.setAuditreason(req.getAuditresson());
		record.setAuditstatus(req.getAudit());
		record.setFreightType(info.getFreighttype());
		//原生效时间
		Long taketime = record.getTaketime();
		//新生效时间
		Long newtaketime = info.getTaketime();
		//1-审核成功 2-审核失败
		if("1".equals(req.getAudit())){
			//
			updateFreightInfoLine(taketime,newtaketime);
			record.setPrice(info.getPrice());
			record.setTallage(info.getTallage());
			if(info.getTaketime()==null){
				record.setTaketime(sta);
			}else{
				record.setTaketime(info.getTaketime());
			}
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
	/** 判断是否需要调整运价策略历史纪录，需要修改进行修改*/
	protected void updateFreightInfoLine(Long taketime, Long newtaketime){
		//原生效时间不为空，且新生效时间比原生效时间早，隐藏掉新生效时间之后的修改记录
		if(taketime != null&&newtaketime<taketime){
			FreightInfo info = new FreightInfo();
			info.setTaketime(newtaketime);
			info.setDesc4("0");
			freightInfoMapper.updFreightInfo(info);
		}
	}
	
	@Override
	public List<FreightLineResp> lineChart(AdminFreightReq req) throws Exception {
		FreightInfo info = new FreightInfo();
		info.setFreightid(req.getId());
		info.setStatus("1");
		info.setDesc4("!=0");
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

	@Override
	public Result findFreightInfo(String id, Date date) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		FileFreight freight = freightMapper.selectByPrimaryKey(id);
		if(date == null) {
			date = new Date();
		}
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		c.set(Calendar.HOUR_OF_DAY, 0);
//		c.set(Calendar.MINUTE, 0);
//		c.set(Calendar.SECOND, 0);
//		c.set(Calendar.MILLISECOND, 0);
		if(freight==null){
			rs.setCode("1");
			rs.setData("运价策略id有误");
			return rs;
		}
		Long time = date.getTime();
		List<FreightInfo> list = new ArrayList<FreightInfo>();
		if(time<freight.getTaketime()){
			FreightInfo info = new FreightInfo();
			info.setFreightid(id);
			info.setStatus("1");
			info.setDesc4("!=0");
			info.setTaketime(time);
			list = freightInfoMapper.selectByInfo(info);
			if(list.size() == 0){
				rs.setCode("1");
				rs.setError("运价策略未到生效日期，暂不可用");
				return rs;
			}
		}
		if(list.size() != 0 ){
			freight.setPrice(list.get(list.size()-1).getPrice());
			freight.setTallage(list.get(list.size()-1).getTallage());
		}
		rs.setData(freight);
		return rs;
	}
}
