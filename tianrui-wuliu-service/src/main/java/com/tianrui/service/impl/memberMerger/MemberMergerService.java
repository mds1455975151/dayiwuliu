package com.tianrui.service.impl.memberMerger;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.intf.memberMerger.IMemberMergerService;
import com.tianrui.api.req.front.member.MemberFindReq;
import com.tianrui.api.req.memberMerger.MergerCellphoneReq;
import com.tianrui.api.req.memberMerger.MergerQueryReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberCapa;
import com.tianrui.service.bean.MemberCapaList;
import com.tianrui.service.bean.MemberOwner;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.OwnerDriver;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.mapper.MemberCapaMapper;
import com.tianrui.service.mapper.MemberOwnerMapper;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.OwnerDriverMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;



@Service
public class MemberMergerService implements IMemberMergerService{
 
	Logger logger=LoggerFactory.getLogger(MemberMergerService.class);
	
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	SystemMemberInfoMapper systemMemberInfoMapper;
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	@Autowired
	MemberCapaMapper memberCapaMapper;
	@Autowired
	MemberOwnerMapper memberOwnerMapper;
	@Autowired
	OwnerDriverMapper ownerDriverMapper;
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	
	@Override
	public PageResp<MemberResp> selectMergerCellhpone(MergerQueryReq req) throws Exception {
		MemberFindReq member = new MemberFindReq();
		member.setIdCard(req.getIdCard());
		PageResp<MemberResp> list = systemMemberService.findMemberlist(member);
		return list;
	}

	@Override
	public Result mergerCellphone(MergerCellphoneReq req) {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getMainMemberid());
		SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(req.getMainMemberid());
		//查询查询用户是否存在
		if(member!=null){
			String[] ids = req.getOtherMemberids().split(";");
			logger.info("待合并用户："+ids.length+","+ids);
			if(ids.length!=0){
				if(checkVehicle(ids)){
					for(String memberId:ids){
						//待合并用户
						SystemMember base = systemMemberMapper.selectByPrimaryKey(memberId);
						if(base!=null){
							//wuliu_member_vehicle  处理用户车辆数据 修改车辆归属人
							MemberVehicle vehicle = new MemberVehicle();
							vehicle.setMemberid(memberId);
							List<MemberVehicle> vehList = memberVehicleMapper.selectMyVehicleByCondition(vehicle);
							for(MemberVehicle vehic : vehList){
								MemberVehicle upt = new MemberVehicle();
								upt.setId(vehic.getId());
								upt.setMemberid(memberId);
								memberVehicleMapper.updateByPrimaryKeySelective(upt);
								//被引用的车辆  解除引用关系
								MemberCapa capac = new MemberCapa();
								capac.setVehicleid(vehic.getId());
								List<MemberCapaList> cpCr = memberCapaMapper.selectByCondition(capac);
								for(MemberCapaList cp : cpCr){
									memberCapaMapper.deleteByPrimaryKey(cp.getId());
								}
								VehicleDriver vdd = new VehicleDriver();
								vdd.setVehicleid(vehic.getId());//删除车辆绑定关系
								List<VehicleDriver> vddList = vehicleDriverMapper.selectMyVehiDriverByCondition(vdd);
								for(VehicleDriver db : vddList){
									vehicleDriverMapper.deleteByPrimaryKey(db.getId());
								}
							}
							//---------------------------------------
							//处理我的运力  wuliu_member_capa  解除用户绑定关系
							MemberCapa capao = new MemberCapa();
							capao.setMemberid(memberId);
							List<MemberCapaList> cpOw = memberCapaMapper.selectByCondition(capao);
							for(MemberCapaList cp : cpOw){
								memberCapaMapper.deleteByPrimaryKey(cp.getId());
							}
							//----------------------------------------
							//处理wuliu_member_owner  解除车主关系表
							MemberOwner ownerM = new MemberOwner();
							ownerM.setMemberid(memberId);
							List<MemberOwner> ownerMList = memberOwnerMapper.selectMyVehiOwnerByCondition(ownerM);
							for(MemberOwner ow : ownerMList){
								memberOwnerMapper.deleteByPrimaryKey(ow.getId());
							}
							MemberOwner ownerO = new MemberOwner();
							ownerO.setOwnerid(memberId);
							List<MemberOwner> ownerOList = memberOwnerMapper.selectMyVehiOwnerByCondition(ownerO);
							for(MemberOwner ow : ownerOList){
								memberOwnerMapper.deleteByPrimaryKey(ow.getId());
							}
							//-----------------------------------------------
							//处理 wuliu_owner_driver 解除司机绑定关系
							OwnerDriver odd = new OwnerDriver();
							odd.setDriverid(memberId);
							List<OwnerDriver> oddList = ownerDriverMapper.selectMyDriverByCondition(odd);
							for(OwnerDriver od : oddList){
								ownerDriverMapper.deleteByPrimaryKey(od.getId());
							}
							OwnerDriver odc = new OwnerDriver();
							odc.setCreator(memberId);
							List<OwnerDriver> odcList = ownerDriverMapper.selectMyDriverByCondition(odc);
							for(OwnerDriver od : odcList){
								ownerDriverMapper.deleteByPrimaryKey(od.getId());
							}
							//--------------------------------------------------
							//处理 wuliu_vehicle_driver 删除车辆司机绑定关系
							VehicleDriver vd = new VehicleDriver();
							vd.setCreator(memberId);//删除车主绑定
							List<VehicleDriver> vdList = vehicleDriverMapper.selectMyVehiDriverByCondition(vd);
							for(VehicleDriver db : vdList){
								vehicleDriverMapper.deleteByPrimaryKey(db.getId());
							}
							VehicleDriver vdd = new VehicleDriver();
							vdd.setDriverid(memberId);//删除司机绑定
							List<VehicleDriver> vddList = vehicleDriverMapper.selectMyVehiDriverByCondition(vdd);
							for(VehicleDriver db : vddList){
								vehicleDriverMapper.deleteByPrimaryKey(db.getId());
							}
							//-------------------------------------------------------
						}
					}
				}else{
					rs.setErrorCode(ErrorCode.MEMBER_VEHICLE_STATUS);
				};
			}else{
				rs.setErrorCode(ErrorCode.MEMBER_MERGER_NULL);
			}
		}else{
			rs.setErrorCode(ErrorCode.MEMBER_MERGER_NULL);
		}
		return rs;
	}

	/** 校验是否有非空闲车辆*/
	private boolean checkVehicle(String[] ids) {
		boolean a = true;
		for(String memberId:ids){
			MemberVehicle vehicle = new MemberVehicle();
			vehicle.setMemberid(memberId);
			List<MemberVehicle> vehList = memberVehicleMapper.selectMyVehicleByCondition(vehicle);
			for(MemberVehicle veh : vehList){
				if(!veh.getBillstatus().equals("5")){
					a = false;
					logger.info("非空闲车辆车牌号："+veh.getId()+veh.getVehicleprefix()+veh.getVehicleno());
				}
			}
		}
		return a;
	}

}
