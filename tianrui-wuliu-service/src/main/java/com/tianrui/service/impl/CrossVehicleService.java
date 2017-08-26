package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.api.req.admin.ZJXLVehicleReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.ZJXLVehicleResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.ZJXLVehicle;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.ZJXLVehicleMapper;

@Service
public class CrossVehicleService implements ICrossVehicleService{

	@Autowired
	ZJXLVehicleMapper zjxlVehicleMapper;
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	/**
	 * 查询车辆列表信息
	 */
	@Override
	public PageResp<ZJXLVehicleResp> find(ZJXLVehicleReq req) throws Exception {
		// TODO Auto-generated method stub
		ZJXLVehicle zjxlVehicle = new ZJXLVehicle();
		zjxlVehicle.setCreatetime(req.getCreatetime());
		zjxlVehicle.setCrossloge(req.getCrossloge());
		zjxlVehicle.setVehiclelogo(req.getVehiclelogo());
		zjxlVehicle.setVehicleno(req.getVehicleno());
		zjxlVehicle.setCreator(req.getCreator());
		zjxlVehicle.setModifier(req.getModifier());
		zjxlVehicle.setModifytime(req.getModifytime());
		zjxlVehicle.setPageNo(req.getPageNo());
		zjxlVehicle.setPageSize(req.getPageSize());
		zjxlVehicle.setLimit(req.getLimit());
		List<ZJXLVehicle> list = zjxlVehicleMapper.selectByCondition(zjxlVehicle);
		long a = zjxlVehicleMapper.findsZJXLVehicleListCount(zjxlVehicle);
		PageResp<ZJXLVehicleResp> page = new PageResp<ZJXLVehicleResp>();
		page.setList(copyProperties(list));
		page.setPageNo(req.getPageNo());
		page.setPageSize(req.getPageSize());
		page.setCount(a);
		return page;
	} 
	
	public List<ZJXLVehicleResp> copyProperties(List<ZJXLVehicle> list)throws Exception{
		List<ZJXLVehicleResp> resp = new ArrayList<ZJXLVehicleResp>();
		for(ZJXLVehicle con : list){
			ZJXLVehicleResp ca = new ZJXLVehicleResp();
			PropertyUtils.copyProperties(ca, con);
			resp.add(ca);
		}
		return resp;
	}
	/**
	 * 添加车辆信息
	 */
	@Override
	public Result insert(ZJXLVehicleReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		ZJXLVehicle  record = new ZJXLVehicle();
		record.setVehicleid(req.getVehicleid());
		record.setVehiclelogo(req.getVehiclelogo());
		record.setVehicleno(req.getVehicleno());
		record.setCrossloge(req.getCrossloge());
		record.setCreatetime(new Date().getTime());
		record.setCreator(req.getCreator());
		record.setId(UUIDUtil.getId());
		int a =zjxlVehicleMapper.insertSelective(record);
		if(a!=1){
			rs.setCode("2");
			rs.setError("添加失败！");
		}else{
			rs.setCode("000000");
			rs.setError("添加成功！");
		}
		return rs;
	}
	
	/**
	 * 启用/禁用
	 */
	@Override
	public Result update(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		ZJXLVehicle  record = new ZJXLVehicle();
		ZJXLVehicle zjxlVehicle =zjxlVehicleMapper.selectByPrimaryKey(id);
		if(zjxlVehicle.getVehiclelogo().equals("1")){
			record.setVehiclelogo("0");
		}else if(zjxlVehicle.getVehiclelogo().equals("0")){
			record.setVehiclelogo("1");
		}	
		record.setModifytime(new Date().getTime());
		record.setId(id);
		int re = zjxlVehicleMapper.updateByPrimaryKeySelective(record);
		if(re!=1){
			rs.setCode("2");
			rs.setError("操作失败！");
		}else{
			rs.setCode("000000");
			rs.setError("操作成功！");
		}
		return rs;
	}
	
	/**
	 * 查询车辆信息
	 */
	@Override
	public Result selectVehicle(String vehicleno) throws Exception {
		Result rs = Result.getSuccessResult();
		MemberVehicle memberVehicles = new MemberVehicle();
		 String vehicleprefix=vehicleno.substring(0,2);
		 String vehiclenos =vehicleno.substring(2,7);
		 memberVehicles.setVehicleprefix(vehicleprefix);
		 memberVehicles.setVehicleno(vehiclenos);
		 //查询车辆是否被添加过
		 ZJXLVehicle  zjxlVehicle = new ZJXLVehicle();
		 zjxlVehicle.setVehicleno(vehicleno);
		 List<ZJXLVehicle> list = zjxlVehicleMapper.selectByCondition(zjxlVehicle);
		 if(list.size()<1){
			 MemberVehicle memberVehicle =memberVehicleMapper.selectVehicle(memberVehicles);
			 if(memberVehicle!=null){
				 SystemMember  member = systemMemberMapper.selectByPrimaryKey(memberVehicle.getMemberid());
				 if(member!=null){
					 memberVehicle.setCellphone(member.getCellphone());
				 }
				 rs.setCode("000000");
				 rs.setData(memberVehicle);
			 }else{
				 rs.setCode("666666");
				 rs.setError("未查到该车辆，请确认车牌号！");
			 }
		 }else{
			 rs.setCode("777777");
			 rs.setError("该车辆已添加过！");
		 }
		return rs;
	} 
}
