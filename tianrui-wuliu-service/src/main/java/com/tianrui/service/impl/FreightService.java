package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IFreightService;
import com.tianrui.api.req.front.cargoplan.FreightReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.cargoplan.FreightCRResp;
import com.tianrui.api.resp.front.cargoplan.FreightResp;
import com.tianrui.api.resp.front.cargoplan.FreightlistResp;
import com.tianrui.api.resp.front.cargoplan.FreightsResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.bean.FileOrgCargo;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.bean.Freight;
import com.tianrui.service.admin.bean.FreightInfo;
import com.tianrui.service.admin.mapper.FileFreightMapper;
import com.tianrui.service.admin.mapper.FileOrgCargoMapper;
import com.tianrui.service.admin.mapper.FileRouteMapper;
import com.tianrui.service.admin.mapper.FreightInfoMapper;
@Service
public class FreightService implements IFreightService{
	@Autowired
	private FileFreightMapper fileFreightMapper;
	@Autowired
	private FreightInfoMapper freightInfoMapper;
	@Autowired
	private FileOrgCargoMapper fileOrgCargoMapper;
	@Autowired
	private FileRouteMapper fileRouteMapper;
	@Override
	public List<FreightResp> findByEntity(FreightReq req) throws Exception{
		FileFreight freight = new FileFreight();
		PropertyUtils.copyProperties(freight, req);
		List<FileFreight> list = fileFreightMapper.findByEntity(freight);
		return copyProperties(list);
	}
	
	public List<FreightResp> findByStatus(FreightReq req) throws Exception{
		FileFreight freight = new FileFreight();
		PropertyUtils.copyProperties(freight, req);
		List<FileFreight> list = fileFreightMapper.selectByStatus(freight);
		return copyProperties(list);
	}
	
	public List<FreightResp> copyProperties(List<FileFreight> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<FreightResp> resplist = new ArrayList<FreightResp>();
		for(FileFreight freight : list){
			FreightResp resp = new FreightResp();
			PropertyUtils.copyProperties(resp, freight);
			resplist.add(resp);
		}
		return resplist;
	}

	@Override
	public FreightResp findById(String id) throws Exception {
		FileFreight freight = fileFreightMapper.selectByPrimaryKey(id);
		FreightResp resp = new FreightResp();
		PropertyUtils.copyProperties(resp, freight);
		return resp;
	}

	@Override
	public boolean delctcByIdStr(String idStr) throws Exception {
		return false;
	}

	@Override
	public PageResp<FreightlistResp> findByLisrEntity(FreightReq req) throws Exception {
		FileFreight freight = new FileFreight();
		PropertyUtils.copyProperties(freight, req);
		List<FileFreight> list = fileFreightMapper.findByToName(freight);
		long count = fileFreightMapper.findByToNameCount(freight);
		PageResp<FreightlistResp> page = new PageResp<FreightlistResp>(); 
		page.setCount(count);
		page.setPageNo(req.getPageNo());
		page.setPageSize(req.getPageSize());
		page.setList(copyPropertiesL(list));
		return page;
	}
	
	public List<FreightlistResp> copyPropertiesL(List<FileFreight> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<FreightlistResp> resplist = new ArrayList<FreightlistResp>();
		for(FileFreight freight : list){
			FreightlistResp resp = new FreightlistResp();
			PropertyUtils.copyProperties(resp, freight);
			resplist.add(resp);
		}
		return resplist;
	}

	@Override
	public boolean saveEntity(FreightReq req) throws Exception {
		saveFreightInfo(req);
		FileFreight freight = new FileFreight();
		req.setPrice(null);
		req.setTallage(null);
		req.setFreightType(null);
		PropertyUtils.copyProperties(freight, req);
		if(fileFreightMapper.insertSelective(freight)!=1){
			return false;
		}
		return true;
	}

	@Override
	public Result delectEntity(FreightReq req) throws Exception {
		FileFreight file = new FileFreight();
		PropertyUtils.copyProperties(file, req);
		int a = fileFreightMapper.updateByPrimaryKeySelective(file);
		Result rs = Result.getSuccessResult();
		if(a != 1){
			rs.setCode("1");
			rs.setError("删除失败");
		}
		return rs;
	}
	@Override
	public Result updateEntity(FreightReq req) throws Exception {
		//TODO
		Result rs = Result.getSuccessResult();
		FileFreight freight = fileFreightMapper.selectByPrimaryKey(req.getId());
		if("0".equals(freight.getAuditstatus())){
			if(!uptAuditFreight(req)){
				rs.setErrorCode(ErrorCode.FILE_FREIGHT_ERROR);
				return rs;
			}
		}else{
			//审核记录操作
			updateFreightInfo(req.getId());
			saveFreightInfo(req);
		}
		//修改运价策略状态为审核中
		freight.setAuditstatus("0");
		freight.setModifytime(System.currentTimeMillis());
		fileFreightMapper.updateByPrimaryKeySelective(freight);
		return rs;
	}
	/** 修改审核中策略
	 * @throws ParseException */
	public boolean uptAuditFreight(FreightReq req) throws ParseException{
		//TODO
		FreightInfo fo = new FreightInfo();
		fo.setFreightid(req.getId());
		fo.setRecent("1");
		List<FreightInfo> list = freightInfoMapper.selectByInfo(fo);
		if(list.size()==1){
			FreightInfo info = new FreightInfo();
			info.setId(list.get(0).getId());
			info.setFrebilltype(req.getFrebilltype());
			info.setFreightid(req.getId());
			info.setPrice(req.getPrice());
			info.setTallage(req.getTallage());
			info.setFreighttype(req.getFreightType());
			info.setCreater(req.getModifier());
			info.setCreatetime(new Date().getTime());
			info.setTaketime(req.getTaketime());
			info.setDesc1(req.getDesc1());
			info.setRecent("1");
			info.setStatus("0");
			freightInfoMapper.updateByPrimaryKeySelective(info);
			return true;
		}else{
			return false;
		}
	}
	/** 插入审核记录
	 * @throws ParseException */
	protected int saveFreightInfo(FreightReq req) throws ParseException {
		FreightInfo info = new FreightInfo();
		info.setId(UUIDUtil.getId());
		info.setFrebilltype(req.getFrebilltype());
		info.setFreightid(req.getId());
		info.setPrice(req.getPrice());
		info.setTallage(req.getTallage());
		info.setFreighttype(req.getFreightType());
		info.setCreater(req.getModifier());
		info.setCreatetime(new Date().getTime());
		info.setTaketime(req.getTaketime());
		info.setDesc1(req.getDesc1());
		info.setRecent("1");
		info.setStatus("0");
		return freightInfoMapper.insertSelective(info);
	}
	
	/** 更新原最新审核为非最新*/
	protected int updateFreightInfo(String freightid) {
		FreightInfo info = new FreightInfo();
		info.setFreightid(freightid);
		info.setRecent("1");
		List<FreightInfo> list = freightInfoMapper.selectByInfo(info);
		int a = 0;
		for(FreightInfo n : list){
			n.setRecent("0");
			a += freightInfoMapper.updateByPrimaryKeySelective(n);
		}
		return a;
	}

	@Override
	public FreightsResp findsById(String id) throws Exception {
		Freight f = fileFreightMapper.selectByKey(id);
		FreightsResp r = new FreightsResp();
		PropertyUtils.copyProperties(r, f);
		return r;
	}

	@Override
	public String findByName(String desc1Name) throws Exception {
		FileFreight freight = new FileFreight();
		freight.setFreightName(desc1Name);
		List<FileFreight> ls =  fileFreightMapper.findByName(freight);
		if(ls != null && ls.size() > 0){
			return ls.get(0).getId();
		}
		return null;
	}

	@Override
	public boolean batchUpdate(FreightReq req) throws Exception {
		FileFreight freight = new FileFreight();
		PropertyUtils.copyProperties(freight, req);
		int a = fileFreightMapper.batchUpdateByPrimaryKeySelective(freight);
		if(a!=0){
			return true;
		}
		return false;
		
	}

	@Override
	public Result closeFreight(FreightReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		FileFreight file = new FileFreight();
		PropertyUtils.copyProperties(file, req);
		int a = fileFreightMapper.updateByPrimaryKeySelective(file);
		if(a != 1){
			rs.setCode("1");
			rs.setError("修改失败");
		}
		return rs;
	}

	@Override
	public FreightCRResp findFCR(FreightReq req) throws Exception{
		FreightCRResp resp = new FreightCRResp();
		if(req != null){
			FileFreight fileFreight = fileFreightMapper.selectByPrimaryKey(req.getId());
			if(fileFreight != null){
				PropertyUtils.copyProperties(resp, fileFreight);
				FileOrgCargo cargo = fileOrgCargoMapper.selectByPrimaryKey(fileFreight.getCargoid());
				if(cargo != null){
					resp.setCargono(cargo.getCargono());
					resp.setCargoname(cargo.getCargoname());
				}
				FileRoute route = fileRouteMapper.selectByPrimaryKey(fileFreight.getRouteid());
				if(route != null){
					resp.setOaddr(route.getOaddr());
					resp.setDaddr(route.getDaddr());
					resp.setDistance(route.getDistance());
					resp.setSendpersion(route.getSendpersion());
					resp.setReceivepersion(route.getReceivepersion());
				}
			}
		}
		return resp;
	}
}
