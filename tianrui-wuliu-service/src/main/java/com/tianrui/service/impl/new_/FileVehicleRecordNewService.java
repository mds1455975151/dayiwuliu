package com.tianrui.service.impl.new_;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.new_.IFileVehicleRecordNewService;
import com.tianrui.api.req.new_.FileVehicleRecordNewReq;
import com.tianrui.api.resp.new_.FileVehicleRecordNewResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.vehiclereg.FileVehicleNew;
import com.tianrui.service.bean.vehiclereg.FileVehicleRecordNew;
import com.tianrui.service.mapper.FileVehicleNewMapper;
import com.tianrui.service.mapper.FileVehicleRecordNewMapper;

@Service
public class FileVehicleRecordNewService implements IFileVehicleRecordNewService{

	@Autowired
	FileVehicleRecordNewMapper fileVehicleRecordNewMapper;
	@Autowired
	FileVehicleNewMapper fileVehicleNewMapper;
	
	@Override
	public PaginationVO<FileVehicleRecordNewResp> select(FileVehicleRecordNewReq req) throws Exception {
		PaginationVO<FileVehicleRecordNewResp> rs = new PaginationVO<FileVehicleRecordNewResp>();
		FileVehicleRecordNew record = new FileVehicleRecordNew();
		PropertyUtils.copyProperties(record, req);
		if(req.getPageNo()!=null){
			record.setPageNo(req.getPageNo()*req.getPageSize());
			record.setPageSize(req.getPageSize());
			rs.setPageNo(req.getPageNo());
			rs.setPageSize(req.getPageSize());
		}
		List<FileVehicleRecordNew> list = fileVehicleRecordNewMapper.selectByCondition(record);
		long a = fileVehicleRecordNewMapper.selectByCount(record);
		rs.setTotal(a);
		rs.setList(copyProperties2(list));
		return rs;
	}
	
	protected List<FileVehicleRecordNewResp> copyProperties2(List<FileVehicleRecordNew> list) throws Exception{
		List<FileVehicleRecordNewResp> resp = new ArrayList<FileVehicleRecordNewResp>();
		for(FileVehicleRecordNew n : list){
			FileVehicleRecordNewResp r = new FileVehicleRecordNewResp();
			PropertyUtils.copyProperties(r, n);
			resp.add(r);
		}
		return resp;
	}

	@Override
	public Result vehicleTrickCheck(FileVehicleRecordNewReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		//查询认证信息
		FileVehicleRecordNew vehciel = fileVehicleRecordNewMapper.selectByPrimaryKey(req.getId());
		if(vehciel==null){
			rs.setCode("1");
			rs.setError("查询不到该认证信息");
			return rs;
		}else{
			FileVehicleRecordNew record = new FileVehicleRecordNew();
			byte t = 1;//认证通过
			if(t==req.getAuthstatus()){
				uptVehicle(vehciel);
				record.setAuthtype((byte)-1);
			}
			record.setId(req.getId());
			record.setAuthstatus(req.getAuthstatus());
			String mat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
			record.setAuthtime(mat);
			record.setAuthuser(req.getAuthuser());
			fileVehicleRecordNewMapper.updateByPrimaryKeySelective(record);
		}
		return rs;
	}
	/** 修改*/
	protected void uptVehicle(FileVehicleRecordNew vehciel) {
		FileVehicleNew file = fileVehicleNewMapper.selectByPrimaryKey(vehciel.getVehicleid());
		
		//开票认证
		file.setAuthtype(Byte.valueOf("3"));
		file.setNature(vehciel.getNature());
		file.setQuality(vehciel.getQuality());
		file.setIdcardno(vehciel.getIdcardno());
		file.setCertificateno(vehciel.getCertificateno());
		file.setExpirydata(vehciel.getExpirydata());
		file.setIdentification(vehciel.getIdentification());
		file.setMotor(vehciel.getMotor());
		file.setMotorno(vehciel.getMotor());
		
		fileVehicleNewMapper.updateByPrimaryKeySelective(file);
	}

	@Override
	public Result vehicleCheck(FileVehicleRecordNewReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		//查询认证信息
		FileVehicleRecordNew vehciel = fileVehicleRecordNewMapper.selectByPrimaryKey(req.getId());
		if(vehciel==null){
			rs.setCode("1");
			rs.setError("查询不到该认证信息");
			return rs;
		}else{
			FileVehicleRecordNew record = new FileVehicleRecordNew();
			byte t = 1;//认证通过
			if(t==req.getAuthstatus()){
				uptAuthVehicle(vehciel);
				record.setAuthtype((byte)-1);
			}
			record.setId(req.getId());
			record.setAuthstatus(req.getAuthstatus());
			String mat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
			record.setAuthtime(mat);
			record.setAuthuser(req.getAuthuser());
			fileVehicleRecordNewMapper.updateByPrimaryKeySelective(record);
		}
		return rs;
	}
	
	/** 修改*/
	protected void uptAuthVehicle(FileVehicleRecordNew req) {
		FileVehicleNew saveBean = fileVehicleNewMapper.selectByPrimaryKey(req.getVehicleid());
		
		//完全认证
		saveBean.setAuthtype(Byte.valueOf("2"));
		
		saveBean.setTaxilicenseno(req.getTaxilicenseno());;
		saveBean.setRoadtransportno(req.getRoadtransportno());
		saveBean.setTaxilicenseimg(req.getTaxilicenseimg());
		saveBean.setVehicleimg(req.getVehicleimg());
		saveBean.setDrivinglicenseno(req.getDrivinglicenseno());
		saveBean.setDrivinglicenseimg(req.getDrivinglicenseimg());
		saveBean.setVehiclegradeno(req.getVehiclegradeno());
		saveBean.setVehiclegradeimg(req.getVehiclegradeimg());
		
		fileVehicleNewMapper.updateByPrimaryKeySelective(saveBean);
	}

}
