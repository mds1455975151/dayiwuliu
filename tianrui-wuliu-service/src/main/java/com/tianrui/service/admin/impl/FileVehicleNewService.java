package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IFileVehicleNewService;
import com.tianrui.api.req.admin.vehicle_new.FileVehicleNewReq;
import com.tianrui.api.resp.admin.vehicle_new.FileVehicleNewResp;
import com.tianrui.common.utils.Md5Utils;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.bean.VehicleReg;
import com.tianrui.service.bean.vehiclereg.FileVehicleNew;
import com.tianrui.service.bean.vehiclereg.VehicleDriverNew;
import com.tianrui.service.mapper.FileVehicleNewMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.VehicleDriverNewMapper;
import com.tianrui.service.mongo.VehicleRegDao;
@Service
public class FileVehicleNewService implements IFileVehicleNewService{

	@Autowired
	FileVehicleNewMapper fileVehicleNewMapper;
	@Autowired
	VehicleRegDao vehicleRegDao;
	@Autowired
	VehicleDriverNewMapper vehicleDriverNewMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	SystemMemberInfoMapper systemMemberInfoMapper;
	
	@Override
	public PaginationVO<FileVehicleNewResp> find(FileVehicleNewReq req) throws Exception {
		PaginationVO<FileVehicleNewResp> page = new PaginationVO<FileVehicleNewResp>();
		
		FileVehicleNew record = new FileVehicleNew();
		PropertyUtils.copyProperties(record, req);
		if(req.getPageNo()!=null){
			record.setStart(req.getPageNo()*req.getPageSize());
			record.setLimit(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		Long a = fileVehicleNewMapper.selectByCount(record);
		List<FileVehicleNew> list = fileVehicleNewMapper.selectByContent(record);
		page.setList(copyProperties(list));
		page.setTotal(a);
		return page;
	}
	
	public List<FileVehicleNewResp> copyProperties(List<FileVehicleNew> list)throws Exception{
		List<FileVehicleNewResp> resp = new ArrayList<FileVehicleNewResp>();
		for(FileVehicleNew n : list){
			FileVehicleNewResp r = new FileVehicleNewResp();
			PropertyUtils.copyProperties(r, n);
			resp.add(r);
		}
		return resp;
	}

	@Override
	public Result saveVehicleAndDriver(String id,String chackType) throws Exception {
		Result rs = Result.getSuccessResult();
		VehicleReg req = vehicleRegDao.findVehicleById(id);
		short a = 0;
		if(req.getCheckStatus()!=a){
			rs.setCode("1");
			rs.setError("该车辆已经审核过了");
			return rs;
		}else{
			//审核通过
			if(StringUtils.equals("1", chackType)){
				String driverid = UUIDUtil.getId();
				String vehicleid = UUIDUtil.getId();
				//创建司机账号
				saveDrvier(req,vehicleid,driverid);
				//创建车辆
				saveVehicle(req,vehicleid,driverid);
				saveVehicle_member(vehicleid,req.getVehicleNo());
				req.setCheckStatus((short)1);
			}else if(StringUtils.equals("2", chackType)){//审核不通过
				req.setCheckStatus((short)2);
			}
			vehicleRegDao.remove(req.getId());
			vehicleRegDao.save(req);
		}
		return rs;
	}
	/** 创建车辆登录账号*/
	protected void saveVehicle_member(String id,String vehicleNo) {
		//TODO
		SystemMember member = new SystemMember();
		member.setId(id);
		member.setCellphone(vehicleNo);
		member.setStatus("1");
		//默认密码666666
		member.setPassword(Md5Utils.MD5("666666"));
		systemMemberMapper.insertSelective(member);
		SystemMemberInfo info = new SystemMemberInfo();
		info.setId(id);
		info.setMemberid(id);
		systemMemberInfoMapper.insertSelective(info);
	}
	
	/** 司机账户*/
	protected String saveDrvier(VehicleReg req,String vehicleid,String driverid){
		
		VehicleDriverNew vd = new VehicleDriverNew();
		vd.setId(driverid);
		vd.setVehicleid(vehicleid);
		vd.setDrivername(req.getDriverName());
		vd.setDriversex(req.getDriverSex());
		vd.setDriveridcard(req.getDriverIdCard());
		vd.setDriverbirthdate(req.getDriverBirthDate());
		vd.setDriverlinktel(req.getDriverLinkTel());
		vd.setDriveridcardaddr(req.getDriverIdCardAddr());
		vd.setDrivercardfirstlicens(req.getDriverCardFirstlicens());
		vd.setDrivercardlicenceorg(req.getDriverCardLicenceorg());
		vd.setDrivercardregdate(req.getDriverCardRegDate());
		vd.setDrivercardusefullife(req.getDriverCardUsefullife());
		vd.setDrivercardtype(req.getDriverCardType());
		vd.setDrivercardimg(req.getDriverCardImg());
		//默认车辆已选中改司机
		vd.setCheckstatus((byte)1);
		//审核状态 审核成功
		vd.setAuthstats((byte)1);
//		vd.setAuthremark(req.getauthRemark);
//		vd.setAuthuser(authuser);
		vd.setAuthtime(System.currentTimeMillis());
		vd.setCreatetime(req.getCreateTime());
		vehicleDriverNewMapper.insertSelective(vd);
		return driverid;
	}
	/** 新增车辆信息*/
	protected void saveVehicle(VehicleReg req,String vehicle,String driverId){
		
		FileVehicleNew ve = new FileVehicleNew();
		ve.setId(vehicle);
		ve.setVehicleno(req.getVehicleNo());
		ve.setDriverid(driverId);
		ve.setVehiclemobile(req.getVehicleMobile());
		ve.setVehicletype(req.getVehicleType());
		ve.setVehiclelen(req.getVehicleLen());
		ve.setVehicleload(req.getVehicleLoad());
		ve.setVehicleowner(req.getVehicleOwner());
		ve.setVehicleowneridcard(req.getVehicleOwnerIdCard());
		ve.setTaxilicenseno(req.getTaxiLicenseNo());
		ve.setRoadtransportno(req.getRoadTransportNo());
		ve.setTaxilicenseimg(req.getTaxiLicenseImg());
		ve.setVehicleimg(req.getVehicleImg());
		ve.setDrivinglicenseno(req.getDrivingLicenseNo());
		ve.setDrivinglicenseimg(req.getDrivingLicenseImg());
		ve.setVehiclegradeno(req.getVehicleGradeNo());
		ve.setVehiclegradeimg(req.getVehicleGradeImg());
        //开票认证  
//		ve.setNature(req.getnature);
//		ve.setQuality(req.getquality);
//		ve.setIdcardno(req.getidcardno);
//		ve.setCertificateno(req.getcertificateno);
//		ve.setExpirydata(req.getexpirydata);
//		ve.setIdentification(req.getidentification);
//		ve.setMotor(req.getmotor);
//		ve.setMotorno(req.getmotorno);
		//0:默认 1:完全  2:临时  3:开票
		ve.setAuthtype((byte)req.getAuthType());
		//用户添加
		ve.setVehiclesource((byte)1);
		//空闲状态
		ve.setBillstatus((byte) 0);
		ve.setCreatetime(System.currentTimeMillis());
		fileVehicleNewMapper.insertSelective(ve);
	}

	@Override
	public Result selectByid(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		FileVehicleNewResp resp = new FileVehicleNewResp();
		FileVehicleNew ne = fileVehicleNewMapper.selectByPrimaryKey(id);
		PropertyUtils.copyProperties(resp, ne);
		rs.setData(resp);
		return rs;
	}
}
