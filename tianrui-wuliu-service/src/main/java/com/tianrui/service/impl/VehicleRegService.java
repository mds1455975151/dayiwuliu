package com.tianrui.service.impl;


import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.new_.IMemberVehicleNewService;
import com.tianrui.api.intf.new_.IVehicleRegService;
import com.tianrui.api.req.front.vehicle.VechicleRegQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegStep1Req;
import com.tianrui.api.req.front.vehicle.VechicleRegStep2Req;
import com.tianrui.api.req.front.vehicle.VechicleRegStep3Req;
import com.tianrui.api.req.new_.VehiclReqStepReq;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.VehicleReg;
import com.tianrui.service.bean.vehiclereg.FileVehicleNew;
import com.tianrui.service.mapper.FileVehicleNewMapper;
import com.tianrui.service.mongo.VehicleRegDao;
@Service
public class VehicleRegService implements IVehicleRegService{
	
	@Autowired
	VehicleRegDao vehicleRegDao;
	@Autowired
	FileVehicleNewMapper fileVehicleNewMapper;

	@Override
	public Result vehicleRegStep1(VechicleRegStep1Req req) {
		Result rs =Result.getErrorResult();
		//判断参数
		if( req!=null &&StringUtils.isNotBlank(req.getVehicleNo()) ){
			//验证车牌号是否存在
			if( !isExistVehicleNo(req.getVehicleNo()) ){
				//拼装数据
				VehicleReg bean =new VehicleReg();
				String id =UUIDUtil.getId();
				try {
					BeanUtils.copyProperties(bean, req);
					bean.setId(id);
					bean.setRegStepStatus((short)1);
					bean.setCheckStatus((short)1);
					bean.setCreateTime(System.currentTimeMillis());
					bean.setLastUpdateTime(System.currentTimeMillis());
					vehicleRegDao.save(bean);
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					rs.setData(id);
				}  catch (Exception e) {
					rs.setErrorCode(ErrorCode.SYSTEM_SERVER_ERROR);
				}
			}
		}
		return rs;
	}

	@Override
	public Result vehicleRegStep2(VechicleRegStep2Req req) {
		Result rs =Result.getErrorResult();
		//判断参数
		if( req!=null &&StringUtils.isNotBlank(req.getId()) ){
			VehicleReg bean = vehicleRegDao.findOne(req.getId());
			if( bean !=null && bean.getRegStepStatus()==(short)1){
				try {
					BeanUtils.copyProperties(bean, req);
					bean.setRegStepStatus((short)2);
					bean.setLastUpdateTime(System.currentTimeMillis());
					vehicleRegDao.remove(req.getId());
					vehicleRegDao.save(bean);
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}  catch (Exception e) {
					rs.setErrorCode(ErrorCode.SYSTEM_SERVER_ERROR);
				}
			}
		}
		return rs;
	}

	@Override
	public Result vehicleRegStep3(VechicleRegStep3Req req) {
		Result rs =Result.getErrorResult();
		//判断参数
		if( req!=null &&StringUtils.isNotBlank(req.getId()) ){
			VehicleReg bean = vehicleRegDao.findOne(req.getId());
			if( bean !=null && bean.getRegStepStatus()==(short)2){
				try {
					BeanUtils.copyProperties(bean, req);
					bean.setRegStepStatus((short)3);
					bean.setLastUpdateTime(System.currentTimeMillis());
					vehicleRegDao.remove(req.getId());
					vehicleRegDao.save(bean);
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}  catch (Exception e) {
					rs.setErrorCode(ErrorCode.SYSTEM_SERVER_ERROR);
				}
			}
		}
		return rs;
	}

	@Override
	public Result checkVehicleExist(VechicleRegQueryReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		FileVehicleNew fv = new FileVehicleNew();
		fv.setVehicleno(req.getVehicleNo());
		List<FileVehicleNew> list = fileVehicleNewMapper.selectByContent(fv);
		if(list.size()!=0){
			rs.setCode("1");
			rs.setError("车牌号已存在");
		}
		return rs;
	}

	@Override
	public Result checkVehicleAccountExist(VechicleRegQueryReq req) {
		Result rs =Result.getErrorResult();
		//判断参数
		if( req!=null &&StringUtils.isNotBlank(req.getVehicleNo()) ){
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			rs.setData("1");
		}
		return rs;
	}

	@Override
	public Result getTempVechicleNo(VechicleRegQueryReq req) {
		Result rs =Result.getErrorResult();
		rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		rs.setData("临A54321");
		return rs;
	}

	/**
	 * 车牌号码是否已经被认证
	 * true:已经存在  false:不存在
	 * @param vehicleNo
	 * @return
	 */
	private boolean isExistVehicleNo(String vehicleNo){
		return false;
	}

	@Override
	public Result saveVehicleRegStep(VehiclReqStepReq req) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		VehicleReg bean = new VehicleReg();
		PropertyUtils.copyProperties(bean, req);
		bean.setCreateTime(System.currentTimeMillis());
		vehicleRegDao.save(bean);
		return rs;
	}
	
}
