package com.tianrui.web.app.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IMemberOwnerService;
import com.tianrui.api.intf.IMemberVehicleService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.intf.IOwnerDriverService;
import com.tianrui.api.intf.ISystemMemberInfoRecordService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.intf.IVehicleNOService;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.api.req.front.vehicle.MemberVehicleReq;
import com.tianrui.api.req.front.vehicle.OwnerDriverReq;
import com.tianrui.api.req.front.vehicle.VehicleAndDriverReq;
import com.tianrui.api.req.front.vehicle.VehicleDriverReq;
import com.tianrui.api.req.front.vehicle.VehicleOnlyReq;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.vehicle.MemberVehicleResp;
import com.tianrui.api.resp.front.vehicle.OwnerDriverResp;
import com.tianrui.api.resp.front.vehicle.VehicleAndDriverResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;
import com.tianrui.web.util.SessionManager;

/**
 * 
 * @类描述：app我的车主接口
 * @创建人：lsj
 * @创建时间：2016年6月29日下午3:18:54
 *
 * @修改人：lsj
 * @修改时间：2016年6月29日下午3:18:54
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/app/VehicleAndDriver")
public class AppVehicleAndDriverAction {
	
	@Autowired
	private IOwnerDriverService ownerDriverService;
	@Autowired
	private IMemberVehicleService memberVehicleService;
	@Autowired
	private IMemberOwnerService memberOwnerService;
	@Autowired
	IVehicleNOService vehicleNOService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	protected ISystemMemberInfoRecordService systemMemberInfoRecordService;
	@Autowired
	private IVehicleDriverService vehicleDriverService;
	/**
	 * 
	 * @描述:我的司机
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年6月29日下午4:10:49
	 */
	@RequestMapping(value="/myDriver",method=RequestMethod.POST)
	@ApiParamRawType(OwnerDriverReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult myDriver(AppParam<OwnerDriverReq> appParam) throws Exception{
		OwnerDriverReq req = appParam.getBody();
		req.setCreator(appParam.getBody().getMemberId());
		PaginationVO<OwnerDriverResp> pageVo = ownerDriverService.queryMyDriverInfoByPage(req);
		AppResult result = new AppResult();
		result.setCode("000000");
		result.setReturnData(pageVo.getList());
		result.setTotal(pageVo.getTotalInt());
		return result;
	}
	/*** 我的司机不分页*/
	@RequestMapping(value="/myDriverList",method=RequestMethod.POST)
	@ApiParamRawType(OwnerDriverReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult myDriverList(AppParam<OwnerDriverReq> appParam) throws Exception{
		OwnerDriverReq req = appParam.getBody();
		req.setCreator(appParam.getBody().getMemberId());
		List<OwnerDriverResp> list = ownerDriverService.queryMyDriverInfoByCondition(req);
		AppResult result = new AppResult();
		result.setCode("000000");
		result.setReturnData(list);
//		result.setTotal(pageVo.getTotalInt());
		return result;
	}
		
	/**
	 * 
	 * @描述:添加司机
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年6月30日上午9:18:12
	 */
	@RequestMapping(value="/addDriver",method=RequestMethod.POST)
	@ApiParamRawType(OwnerDriverReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult addDriver(AppParam<OwnerDriverReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		String id = appParam.getHead().getId();
		
		//根据账号查询用户信息
		MemberReq member = new MemberReq();
		member.setTelnum(appParam.getHead().getAccount());
		MemberResp resp = systemMemberService.findMemberByTelnum(member);
		String userName = "";
		if(StringUtils.isNotBlank(resp.getUserName())){
			userName = resp.getUserName();
		}else if(StringUtils.isNotBlank(resp.getNickname())){
			userName = resp.getNickname();
		}else{
			userName = resp.getCellPhone();
		}
		OwnerDriverReq req = appParam.getBody();
		
		OwnerDriverReq q = new OwnerDriverReq();
		q.setVehicleownerid(id);
		q.setDriverid(req.getDriverid());
		List<OwnerDriverResp> list = ownerDriverService.queryMyDriverInfoByCondition(q);
		if(list.size()!=0){
			if("2".equals(list.get(0).getStatus())||"3".equals(list.get(0).getStatus())){//已拒绝
				ownerDriverService.deleteByPrimaryKey(list.get(0).getId());
			}else{
				rs.setCode("1");
				rs.setError("该用户已请求过，不用重复添加");
				return AppResult.valueOf(rs);
			}
		}
		req.setId(UUIDUtil.getId());
		req.setVehicleownerid(id);
		req.setCreator(id);
		String sd =  Constant.SYSTEM_PUSH_STATUS;
		req.setStatus(sd);// 默认待回复
		rs = ownerDriverService.insert(req);
		
		if ("000000".equals(rs.getCode())) {
			if(sd.equals("0")){
				// 发送消息
				SendMsgReq sendMsgReq = new SendMsgReq();
				// 会员
				sendMsgReq.setType("2");
				// 司机
				sendMsgReq.setCodeEnum(MessageCodeEnum.VEHI_2DRIVER_ADD);
				sendMsgReq.setRecType(MessageCodeEnum.VEHI_2DRIVER_ADD.getType());
				sendMsgReq.setKeyid(req.getId());
				List<String> paramList = new ArrayList<String>();
				paramList.add(userName);
				sendMsgReq.setParams(paramList);
				sendMsgReq.setSendid(resp.getId());
				sendMsgReq.setSendname(userName);
				sendMsgReq.setRecid(req.getDriverid());
				sendMsgReq.setRecname(req.getDrivername());
				rs = messageService.sendMessageInside(sendMsgReq);
			}else if(sd.equals("1")){
				// 发送消息
				SendMsgReq sendMsgReq = new SendMsgReq();
				// 会员
				sendMsgReq.setType("2");
				// 司机
				sendMsgReq.setCodeEnum(MessageCodeEnum.VEHI_2DRIVER_USERADD);
				sendMsgReq.setRecType(MessageCodeEnum.VEHI_2DRIVER_USERADD.getType());
				sendMsgReq.setKeyid(req.getId());
				List<String> paramList = new ArrayList<String>();
				paramList.add(userName);
				sendMsgReq.setParams(paramList);
				sendMsgReq.setSendid(resp.getId());
				sendMsgReq.setSendname(userName);
				sendMsgReq.setRecid(req.getDriverid());
				sendMsgReq.setRecname(req.getDrivername());
				rs = messageService.sendMessageInside(sendMsgReq);
			}
		} else {
			rs.setCode("1");
		}
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 
	 * @描述:删除我的司机
	 * @param appParam
	 * @return
	 * @throws Exception 
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年7月1日上午9:07:18
	 */
	@RequestMapping(value="/detDriver",method=RequestMethod.POST)
	@ApiParamRawType(OwnerDriverReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult detDriver(AppParam<OwnerDriverReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		String id = appParam.getBody().getId();
		OwnerDriverResp resp = ownerDriverService.findById(id);
		VehicleDriverReq req = new VehicleDriverReq();
		req.setDriverId(resp.getDriverid());
		req.setCreator(id);
		List<VehicleDriverResp> list = vehicleDriverService.queryVehiDriverByCondition(req);
		if(list.size()!=0){
			rs.setCode("1");
			rs.setError("该司机已有绑定关系");
			return AppResult.valueOf(rs);
		}else{
			rs = ownerDriverService.deleteByPrimaryKey(id);
		}
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 
	 * @描述:我的车辆
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年6月29日下午4:46:40
	 */
	@RequestMapping(value="/myVehicle",method=RequestMethod.POST)
	@ApiParamRawType(VehicleAndDriverReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult myVehicle(AppParam<VehicleAndDriverReq> appParam) throws Exception{
		
		VehicleAndDriverReq req = appParam.getBody();
		PaginationVO<VehicleAndDriverResp> pageVo = memberVehicleService.queryVehicleAndDriverByCondition(req);
		AppResult result = new AppResult();
		result.setCode("000000");
		result.setReturnData(pageVo.getList());
		result.setTotal(pageVo.getTotalInt());
		return result;
	}
	/** 获取临时车牌号*/
	@RequestMapping("/getVehicleNo")
	@ApiParamRawType(VehicleAndDriverReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult getVehicleNo(AppParam<VehicleAndDriverReq> appParam) throws Exception {
		Result rs = Result.getSuccessResult();
		rs.setData(vehicleNOService.getVehicleNo());
		return AppResult.valueOf(rs);
	}
	
	/**验证车牌号唯一
	 * @throws Exception */
	@RequestMapping("vehicleVerify")
	@ApiParamRawType(VehicleOnlyReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult vehicleVerify(AppParam<VehicleOnlyReq> parme) throws Exception{
		Result rs = null;
		VehicleOnlyReq req = parme.getBody();
		rs = memberVehicleService.vehicleNOByOnly(req);
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 
	 * @描述:新增车辆
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年6月30日上午8:57:00
	 */
	@RequestMapping(value="/addVehicle",method=RequestMethod.POST)
	@ApiParamRawType(MemberVehicleReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult addVehicle(AppParam<MemberVehicleReq> appParam) throws Exception{
		
		Result result = Result.getSuccessResult();
		
		MemberVehicleReq req = appParam.getBody();
		String id = UUIDUtil.getId();
		req.setId(id);
		req.setVehicleId(id);
		req.setCreator(appParam.getHead().getId());
		req.setMemberId(appParam.getHead().getId());
		req.setDesc2("2");
		result = memberVehicleService.insert(req);
		return AppResult.valueOf(result);
	}
	/** 添加临时车辆*/
	@RequestMapping(value="/addLinVehicle",method=RequestMethod.POST)
	@ApiParamRawType(MemberVehicleReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult addLinVehicle(AppParam<MemberVehicleReq> appParam) throws Exception{
		
		Result result = Result.getSuccessResult();
		
		MemberVehicleReq req = appParam.getBody();
		String id = UUIDUtil.getId();
		req.setId(id);
		req.setVehicleId(id);
		req.setCreator(appParam.getHead().getId());
		req.setMemberId(appParam.getHead().getId());
		req.setDesc2("1");
		result = memberVehicleService.insert(req);
		return AppResult.valueOf(result);
	}
	
	/**
	 * 
	 * @描述:车辆重新认证
	 * @param appParam
	 * @return
	 * @throws Exception 
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年7月1日上午8:58:09
	 */
	@RequestMapping(value="/uptVehicle",method=RequestMethod.POST)
	@ApiParamRawType(MemberVehicleReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult uptVehicle(AppParam<MemberVehicleReq> appParam) throws Exception{
		
		Result rs = Result.getSuccessResult();
		MemberVehicleReq vehiReq = appParam.getBody();
		
		MemberVehicleReq rreq = new MemberVehicleReq();
		rreq.setVehicleNo(vehiReq.getVehicleNo());
		rreq.setVehiclePrefix(vehiReq.getVehiclePrefix());
		List<MemberVehicleResp> list = memberVehicleService.queryMyVehicleInfoByCondition(rreq);
		if(list.size()!=0){
			if(!vehiReq.getId().equals(list.get(0).getId())){
				rs.setCode("1");
				rs.setError("该车辆已存在");
				return AppResult.valueOf(rs);
			}
		}
		//修改车辆信息，车辆再次进入认证状态，后台认证时间为createtime
		vehiReq.setCreateTime(new Date().getTime());
		vehiReq.setStatus("2");
		// 更新操作
		rs = memberVehicleService.updateByPrimaryKeySelective(vehiReq);
		return AppResult.valueOf(rs);
	}
	
	@RequestMapping(value="/uptLinVehicle",method=RequestMethod.POST)
	@ApiParamRawType(MemberVehicleReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult uptLinVehicle(AppParam<MemberVehicleReq> appParam) throws Exception{
		
		Result rs = Result.getSuccessResult();
		MemberVehicleReq vehiReq = appParam.getBody();
		
		MemberVehicleReq rreq = new MemberVehicleReq();
		rreq.setVehicleNo(vehiReq.getVehicleNo());
		rreq.setVehiclePrefix(vehiReq.getVehiclePrefix());
		List<MemberVehicleResp> list = memberVehicleService.queryMyVehicleInfoByCondition(rreq);
		if(list.size()!=0){
			if(!vehiReq.getId().equals(list.get(0).getId())){
				rs.setCode("1");
				rs.setError("该车辆已存在");
				return AppResult.valueOf(rs);
			}
		}
		//修改车辆信息，车辆再次进入认证状态，后台认证时间为createtime
		vehiReq.setCreateTime(new Date().getTime());
		vehiReq.setStatus("2");
		//2-认证车辆
		vehiReq.setDesc2("2");
		// 更新操作
		rs = memberVehicleService.updateByPrimaryKeySelective(vehiReq);
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 
	 * @描述:车辆司机绑定
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年6月30日下午5:03:58
	 */
	@RequestMapping(value="/addVehicleDriver",method=RequestMethod.POST)
	@ApiParamRawType(VehicleDriverReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult addVehicleDriver(AppParam<VehicleDriverReq> appParam) throws Exception{
		Result result = Result.getSuccessResult();
		VehicleDriverReq req = appParam.getBody();
		//验证车辆司机是否已绑定关系
		VehicleDriverReq reck = new VehicleDriverReq();
		reck.setDriverId(req.getDriverId());
		reck.setVehicleId(req.getVehicleId());
		List<VehicleDriverResp> list = vehicleDriverService.queryVehiDriverByCondition(reck);
		if(list.size()!=0){
			result.setCode("1");
			result.setError("该车辆司机已经绑定");
			return AppResult.valueOf(result);
		}
		//绑定关系
		req.setId(UUIDUtil.getId());
		req.setCreator(appParam.getHead().getId());
		result = vehicleDriverService.insert(req);
		return AppResult.valueOf(result);
	}
	
	/**已知安联账号，车辆司机绑定
	 * @throws Exception  */
	@RequestMapping("alSaveDriverVehicle")
	@ApiParamRawType(VehicleDriverReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult alSaveDriverVehicle(AppParam<VehicleDriverReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleDriverService.anlianInsert(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 
	 * @描述:司机车辆解除绑定
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年6月30日下午5:54:51
	 */
	@RequestMapping(value="/delVehicleDriver",method=RequestMethod.POST)
	@ApiParamRawType(VehicleDriverReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult delVehicleDriver(AppParam<VehicleDriverReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleDriverService.deleteByPrimaryKey(appParam.getBody().getId());
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 
	 * @描述:开启/关闭我的车主
	 * @param appParam
	 * @return
	 * @throws Exception 
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年7月1日下午2:27:39
	 */
	@RequestMapping(value="/updOwnerDriver",method=RequestMethod.POST)
	@ApiParamRawType(MemberOwnerReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult updOwnerDriver(AppParam<MemberOwnerReq> appParam) throws Exception{
		MemberOwnerReq ownerReq = new MemberOwnerReq();
		ownerReq.setId(appParam.getBody().getId());
		ownerReq.setStatus(appParam.getBody().getStatus());
		Result rs = memberOwnerService.updateByPrimaryKeySelective(ownerReq);
		return AppResult.valueOf(rs);
	}
	/**
	 * 
	 * @描述:删除我的车主
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年7月1日下午3:26:54
	 */
	@RequestMapping(value="/detOwnerDriver",method=RequestMethod.POST)
	@ApiParamRawType(MemberOwnerReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult detOwnerDriver(AppParam<MemberOwnerReq> appParam) throws Exception{
		String odid=appParam.getBody().getId();
		Result rs = memberOwnerService.deleteByPrimaryKey(odid);
		return AppResult.valueOf(rs);
	}
	
}
