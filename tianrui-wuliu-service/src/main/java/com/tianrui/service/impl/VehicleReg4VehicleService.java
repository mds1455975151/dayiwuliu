package com.tianrui.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.new_.IVehicleReg4VehicleService;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverSaveReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleAuthReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleTicketAuthReq;
import com.tianrui.api.resp.front.vehicle.VehicleRegDriverListResp;
import com.tianrui.api.resp.front.vehicle.VehicleRegVehicleDetailResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.vehiclereg.FileVehicleNew;
import com.tianrui.service.bean.vehiclereg.FileVehicleRecordNew;
import com.tianrui.service.bean.vehiclereg.VehicleDriverNew;
import com.tianrui.service.mapper.FileVehicleNewMapper;
import com.tianrui.service.mapper.FileVehicleRecordNewMapper;
import com.tianrui.service.mapper.VehicleDriverNewMapper;
@Service
public class VehicleReg4VehicleService implements IVehicleReg4VehicleService{
	
	@Autowired
	VehicleDriverNewMapper vehicleDriverNewMapper;
	@Autowired
	FileVehicleRecordNewMapper fileVehicleRecordNewMapper;
	@Autowired
	FileVehicleNewMapper  fileVehicleNewMapper;
	

	@Override
	public Result driverPage(VechicleRegDriverQueryReq req) {
		Result rs =Result.getErrorResult();
		if( req !=null && StringUtils.isNotBlank(req.getCurrVId())  ){
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			//查询条件
			VehicleDriverNew query = new VehicleDriverNew();
			query.setVehicleid(req.getCurrVId());
			List<VehicleDriverNew> list =vehicleDriverNewMapper.selectByCondition(query);
			rs.setData(convert2DriverRespList(list));
		}
		return rs;
	}

	@Override
	public Result driverSave(VechicleRegDriverSaveReq req) {
		Result rs =Result.getErrorResult();
		if( checkSaveBeanReq(req)){
			//拼装数据
			VehicleDriverNew saveBean = new VehicleDriverNew();		
			saveBean.setId(UUIDUtil.getId());
			saveBean.setCreatetime(System.currentTimeMillis());
			saveBean.setVehicleid(req.getCurrVId());
			saveBean.setCheckstatus((byte)0);
			saveBean.setAuthstats((byte)0);
			
			saveBean.setDrivername(req.getDriverName());
			saveBean.setDriversex(req.getDriverSex());
			saveBean.setDriveridcard(req.getDriverIdCard());
			saveBean.setDriveridcardaddr(req.getDriverIdCardAddr());
			saveBean.setDrivercardfirstlicens(req.getDriverCardFirstlicens());
			saveBean.setDrivercardlicenceorg(req.getDriverCardLicenceorg());
			saveBean.setDrivercardimg(req.getDriverCardImg());
			saveBean.setDrivercardregdate(req.getDriverCardRegDate());
			saveBean.setDrivercardusefullife(req.getDriverCardUsefullife());
			saveBean.setDrivercardtype(req.getDriverCardType());
			saveBean.setDriverbirthdate(req.getDriverBirthDate());
			vehicleDriverNewMapper.insert(saveBean);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs; 
	}

	@Override
	public Result driverDel(VechicleRegDriverQueryReq req) {
		Result rs =Result.getErrorResult();
		if(rs !=null && StringUtils.isNotBlank(req.getId()) && StringUtils.isNotBlank(req.getCurrVId())){
			VehicleDriverNew dbBean=vehicleDriverNewMapper.selectByPrimaryKey(req.getId());
			//验证是否为当前用户
			if( dbBean !=null && StringUtils.equals(dbBean.getVehicleid(), req.getCurrVId())){
				vehicleDriverNewMapper.deleteByPrimaryKey(req.getId());
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return rs;
	}

	@Override
	public Result driverCheck(VechicleRegDriverQueryReq req) {
		Result rs =Result.getErrorResult();
		if(rs !=null && StringUtils.isNotBlank(req.getId()) && StringUtils.isNotBlank(req.getCurrVId())){
			VehicleDriverNew dbBean=vehicleDriverNewMapper.selectByPrimaryKey(req.getId());
			//验证是否为当前用户
			if( dbBean !=null && StringUtils.equals(dbBean.getVehicleid(), req.getCurrVId())){
				//vehicleDriverNewMapper.updateCheckStatusByVehicleId(dbBean.getVehicleid());
				
				VehicleDriverNew update = new VehicleDriverNew();
				update.setCheckstatus((byte)1);
				update.setId(req.getId());
				vehicleDriverNewMapper.updateByPrimaryKeySelective(update);
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return rs;
	}

	@Override
	public Result vehicleDetil(VechicleRegDriverQueryReq req) {
		Result rs =Result.getErrorResult();
		//TODO 我的车辆信息  认证成功的就获取认证成功的.  认证中的就获取
		if( req!=null && StringUtils.isNotBlank(req.getCurrVId()) ){
			FileVehicleNew dbBean=fileVehicleNewMapper.selectByPrimaryKey(req.getCurrVId());
			if( dbBean !=null ){
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				VehicleRegVehicleDetailResp resp=new VehicleRegVehicleDetailResp();
				resp.setId(dbBean.getId());
				resp.setVehicleNo(dbBean.getVehicleno());
				resp.setVehicleMobile(dbBean.getVehiclemobile());
				resp.setAuthTime(DateUtil.getDateString(dbBean.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));
				resp.setVehicleType(dbBean.getVehicletype());
				resp.setVehicleLen(dbBean.getVehiclelen()+"米");
				resp.setVehicleLoad(dbBean.getVehicleload()+"吨");
				resp.setVehicleOwner(dbBean.getVehicleowner());
				resp.setVehicleOwnerTel(dbBean.getVehicleownerTel());
				resp.setAuthstatus(String.valueOf(dbBean.getAuthtype()));
				
				resp.setTaxiLicenseNo(dbBean.getTaxilicenseno());
				resp.setRoadTransportNo(dbBean.getRoadtransportno());
				resp.setTaxiLicenseImg(dbBean.getTaxilicenseimg());
				
				resp.setVehicleImg(dbBean.getVehicleimg());
				
				resp.setDrivingLicenseNo(dbBean.getDrivinglicenseno());
				resp.setDrivingLicenseImg(dbBean.getDrivinglicenseimg());
				
				resp.setVehicleGradeNo(dbBean.getVehiclegradeno());
				resp.setVehicleGradeImg(dbBean.getVehiclegradeimg());
				
				rs.setData(resp);
			}
		}
		return rs;
	}

	@Override
	public Result vehicleAuth(VechicleRegVehicleAuthReq req) {
		Result rs =Result.getErrorResult();
		//保存认证信息  以供后台审核
		return rs;
	}

	@Override
	public Result vehicleAuthTicket(VechicleRegVehicleTicketAuthReq req) {
		Result rs =Result.getErrorResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			//获取车辆信息
			FileVehicleNew dbBean=fileVehicleNewMapper.selectByPrimaryKey(req.getCurrVId());
			if( dbBean !=null ){
				//修改其他的记录信息为作废记录 
				//fileVehicleRecordNewMapper.update
				FileVehicleRecordNew  saveBean =new FileVehicleRecordNew();
				try {
					//原有信息记录
					BeanUtils.copyProperties(saveBean, dbBean);
					saveBean.setId(UUIDUtil.getId());
					saveBean.setCreatetime(System.currentTimeMillis());
					
					//新信息保存
					saveBean.setAuthtype(Byte.valueOf("3"));
					saveBean.setNature(req.getNature());
					saveBean.setQuality(req.getQuality());
					saveBean.setIdcardno(req.getIdcardno());
					saveBean.setCertificateno(req.getCertificateno());
					saveBean.setExpirydata(req.getExpirydata());
					saveBean.setIdentification(req.getIdentification());
					saveBean.setMotor(req.getMotor());
					saveBean.setMotorno(req.getMotor());
					fileVehicleRecordNewMapper.insert(saveBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return rs;
	}
	//输出参数类型转换
	private VehicleRegDriverListResp convert2DriverResp(VehicleDriverNew bean){
		VehicleRegDriverListResp resp =null;
		if( bean!=null ){
			
			resp =new VehicleRegDriverListResp();
			resp.setName(bean.getDrivername());
			resp.setIdcard(bean.getDriveridcard());
			resp.setCheckStatus(String.valueOf(bean.getCheckstatus()));
			resp.setAuthSatus(String.valueOf(bean.getAuthstats()));
			resp.setTelphone(bean.getDriverlinktel());
			resp.setId(bean.getId());
			resp.setAuthRemark(bean.getAuthremark());
		}
		return resp;
	}
	//输出参数类型转换
	private List<VehicleRegDriverListResp> convert2DriverRespList(List<VehicleDriverNew> list){
		List<VehicleRegDriverListResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs=new ArrayList<VehicleRegDriverListResp>();
			for(VehicleDriverNew item:list){
				if(convert2DriverResp(item) !=null){
					rs.add(convert2DriverResp(item));
				}
			}
		}
		return rs;
	}
	//验证参数
	private boolean checkSaveBeanReq(VechicleRegDriverSaveReq req){
		boolean rs =false;
		if( req !=null ){
			if(StringUtils.isNotBlank(req.getDriverName())  ){
				if(StringUtils.isNotBlank(req.getDriverLinkTel()) ){
					if(StringUtils.isNotBlank(req.getDriverIdCard()) ){
						rs =true;
					}
				}
			}
		}
		return rs;
	}
	
}
