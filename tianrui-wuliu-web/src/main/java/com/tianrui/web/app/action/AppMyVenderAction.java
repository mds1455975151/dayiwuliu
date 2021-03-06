package com.tianrui.web.app.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IMemberCapaService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.front.capa.CapaReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.api.resp.front.capa.MemberCapaListResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.vehicle.MemberOwnerResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.MemberOwnerService;
import com.tianrui.service.impl.MessageService;
import com.tianrui.service.impl.SystemMemberService;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
 * @author zhanggaohao
 * @createtime 2016年9月2日 下午3:41:01
 * @classname AppPlanAppointVenderAction.java
 */
@Controller
@RequestMapping("/app/appMyVenderAction")
public class AppMyVenderAction {
	
	public Logger logger = LoggerFactory.getLogger(AppMyVenderAction.class);
	
	@Autowired
	protected MemberOwnerService memberOwnerService;
	@Autowired
	protected SystemMemberService systemMemberService;
	@Autowired
	protected IVehicleDriverService iVehicleDriverService;
	@Autowired
	protected MessageService messageService;
	@Autowired
	private IMemberCapaService memberCapaService;
	
	/**
	 * 查询我的车主
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyVender", method = RequestMethod.POST)
	@ApiParamRawType(MemberOwnerReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult getMyVender(AppParam<MemberOwnerReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		Head he = appParam.getHead();
		MemberOwnerReq req = appParam.getBody();
		req.setMemberId(he.getId());
		PaginationVO<MemberOwnerResp> pageVo = memberOwnerService.queryMyVehiOwnerByPage(req);
		appResult.setCode("000000");
		appResult.setTotal(pageVo.getTotalInt());
		appResult.setReturnData(pageVo.getList());
		return appResult;
	}
	/**
	 * 搜索车主
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMyVender", method = RequestMethod.POST)
	@ApiParamRawType(MemberOwnerReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult searchMyVender(AppParam<MemberOwnerReq> appParam) throws Exception {
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		Head head = appParam.getHead();
		MemberOwnerReq req = appParam.getBody();
		MemberReq re = new MemberReq();
		re.setTelnum(req.getOwnerTel());
		MemberResp member = systemMemberService.findMemberByTelnum(re);
		// 无用户数据
		if(member == null){
			appResult.setCode("1");
			appResult.setMessage("没有该车主的信息，请联系其确认注册认证！");
			return appResult;
		} else {
			// 用户ID
			String id = member.getId();
			//TODO
			CapaReq creq = new CapaReq();
			creq.setMemberid(id);
			long count = memberCapaService.indexCount(creq);
			// 不是车主
			if (count == 0) {
				appResult.setCode("2");
				appResult.setMessage("该用户尚不是车主，请联系其认证！");
				return appResult;
			} else {
				MemberOwnerReq ownerReq = new MemberOwnerReq();
				// 车主ID
				ownerReq.setMemberId(head.getId());
				// 司机ID
				ownerReq.setOwnerId(id);
				// 根据车主ID与司机ID查询数据
				List<MemberOwnerResp> ownerRespList = memberOwnerService.queryMyVehiOwnerByCondition(ownerReq);
				
				// 已经存在与车主绑定关系
				if (ownerRespList.size() > 0 && !"2".equals(ownerRespList.get(0).getStatus())) {
					appResult.setCode("3");
					appResult.setMessage("该用户已经绑定，无法再次进行绑定！");
					return appResult;
				} else {
					appResult.setReturnData(member);
					return appResult;
				}
			}
		}
	}
	
	/**
	 * 添加车主
	 * @param appParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveMyVender", method = RequestMethod.POST)
	@ApiParamRawType(MemberOwnerReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult saveMyVender(AppParam<MemberOwnerReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		Head he = appParam.getHead();
		String userName = he.getAccount();
		MemberOwnerReq req = appParam.getBody();
		MemberOwnerReq req1 = new MemberOwnerReq();
		// 会员主键
		req1.setMemberId(he.getId());
		// 车主主键
		req1.setOwnerId(req.getOwnerId());
		List<MemberOwnerResp> list = memberOwnerService.queryMyVehiOwnerByCondition(req1);
		if(list.size()>0){
			for(MemberOwnerResp resp : list){
				if("2".equals(resp.getStatus())){
					memberOwnerService.deleteByPrimaryKey(resp.getId());
				}else{
					appResult.setCode("1");
					appResult.setMessage("该用户已添加");
					return appResult;
				}
			}
		}
		MemberOwnerReq ownerReq = new MemberOwnerReq();
		// 主键
		ownerReq.setId(UUIDUtil.getId());
		// 会员主键
		ownerReq.setMemberId(he.getId());
		// 车主主键
		ownerReq.setOwnerId(req.getOwnerId());
		// 车主名字
		ownerReq.setOwnerName(req.getOwnerName());
		// 车主电话
		ownerReq.setOwnerTel(req.getOwnerTel());
		String sd =  Constant.SYSTEM_PUSH_STATUS;
		// 状态
		ownerReq.setStatus(sd);
		
		// 插入操作
		Result rs = memberOwnerService.insert(ownerReq);
		
		if ("000000".equals(rs.getCode())) {
			if(sd.equals("0")){
				// 发送消息
				SendMsgReq sendMsgReq = new SendMsgReq();
				// 会员
				sendMsgReq.setType("2");
				// 车主
				sendMsgReq.setCodeEnum(MessageCodeEnum.VEHI_2OWNER_ADD);
				sendMsgReq.setRecType(MessageCodeEnum.VEHI_2OWNER_ADD.getType());
				sendMsgReq.setKeyid(ownerReq.getId());
				List<String> paramList = new ArrayList<String>();
				paramList.add(userName);
				sendMsgReq.setParams(paramList);
				sendMsgReq.setSendid(he.getId());
				sendMsgReq.setSendname(userName);
				sendMsgReq.setRecid(req.getOwnerId());
				sendMsgReq.setRecname(req.getOwnerName());
				rs = messageService.sendMessageInside(sendMsgReq);
			}else if(sd.equals("1")){
				// 发送消息
				SendMsgReq sendMsgReq = new SendMsgReq();
				// 会员
				sendMsgReq.setType("2");
				// 车主
				sendMsgReq.setCodeEnum(MessageCodeEnum.VEHI_2OWNER_USERADD);
				sendMsgReq.setRecType(MessageCodeEnum.VEHI_2OWNER_USERADD.getType());
				sendMsgReq.setKeyid(ownerReq.getId());
				List<String> paramList = new ArrayList<String>();
				paramList.add(userName);
				sendMsgReq.setParams(paramList);
				sendMsgReq.setSendid(he.getId());
				sendMsgReq.setSendname(userName);
				sendMsgReq.setRecid(req.getOwnerId());
				sendMsgReq.setRecname(req.getOwnerName());
				rs = messageService.sendMessageInside(sendMsgReq);
			}
		} else {
			rs.setCode("1");
		}
		appResult = AppResult.valueOf(rs);
		return appResult;
	}
	
	/**
	 * 删除我的车主信息
	 * @param appParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteMyVender", method = RequestMethod.POST)
	@ApiParamRawType(MemberOwnerReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult deleteMyVender(AppParam<MemberOwnerReq> appParam) throws Exception{
		Result rs = memberOwnerService.deleteByPrimaryKey(appParam.getBody().getId());
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 开启/关闭我的车主
	 * @param appParam -- id && isenabled 是否开启(1：开启，0：关闭)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMyVender", method = RequestMethod.POST)
	@ApiParamRawType(MemberOwnerReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult updateMyVender(AppParam<MemberOwnerReq> appParam) throws Exception {
		MemberOwnerReq req = appParam.getBody();
		Result result = memberOwnerService.updateByPrimaryKeySelective(req);
		return AppResult.valueOf(result);
	}
	
	/**
	 * 查询车主车辆
	 * @param appParam  memberid--车主id，search--搜索条件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/venderCapa", method = RequestMethod.POST)
	@ApiParamRawType(CapaReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult venderCapa(AppParam<CapaReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		CapaReq req = appParam.getBody();
		//查询类型，车主运力查询时，后台筛选未绑定车辆
		req.setType("capa");
		PaginationVO<MemberCapaListResp> pageVo = memberCapaService.index(req);
		appResult.setCode("000000");
		appResult.setTotal(pageVo.getTotalInt());
		appResult.setReturnData(pageVo.getList());
		return appResult;
	}
	
	@RequestMapping(value = "/getAllMyVender", method = RequestMethod.POST)
	@ApiParamRawType(MemberOwnerReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult getAllMyVender(AppParam<MemberOwnerReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		MemberOwnerReq req = appParam.getBody();
		req.setMemberId(uId);
		req.setStatus("1");//0-待确认，1-已同意，2-已拒绝
		List<MemberOwnerResp> list = memberOwnerService.queryMyVehiOwnerByCondition(req);
		appResult.setCode("000000");
		appResult.setReturnData(list);
		return appResult;
	}
	
}
