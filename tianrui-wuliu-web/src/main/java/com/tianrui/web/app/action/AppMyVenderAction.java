package com.tianrui.web.app.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IMemberCapaService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.req.front.capa.CapaReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.api.req.front.vehicle.VehicleDriverReq;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.vehicle.MemberOwnerResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverResp;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.MemberOwnerService;
import com.tianrui.service.impl.MessageService;
import com.tianrui.service.impl.SystemMemberService;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;
import com.tianrui.web.util.SessionManager;

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
//		List<MemberOwnerResp> list = memberOwnerService.queryMyVehiOwnerByCondition(req);
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
			// 根据创建人查询数据
//			VehicleDriverReq vehiDriverReq = new VehicleDriverReq();
//			// 创建人
//			vehiDriverReq.setCreator(id);
//			List<VehicleDriverResp> vehiDriverRespList = iVehicleDriverService.queryVehiDriverByCondition(vehiDriverReq);
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
				ownerReq.setMemberId(req.getMemberId());
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
		ownerReq.setId(getUUId());
		// 会员主键
		ownerReq.setMemberId(he.getId());
		// 车主主键
		ownerReq.setOwnerId(req.getOwnerId());
		// 车主名字
		ownerReq.setOwnerName(req.getOwnerName());
		// 车主电话
		ownerReq.setOwnerTel(req.getOwnerTel());
		// 状态
		if (req.getStatus() == null || "".equals(req.getStatus())) {
			// 默认待回复
			ownerReq.setStatus("0");
		} else {
			ownerReq.setStatus(req.getStatus());
		}
		
		// 插入操作
		Result rs = memberOwnerService.insert(ownerReq);
		
		if ("000000".equals(rs.getCode())) {
			// 发送消息
			SendMsgReq sendMsgReq = new SendMsgReq();
			// 会员
			sendMsgReq.setType("2");
			// 车主
			sendMsgReq.setCodeEnum(MessageCodeEnum.VEHI_2OWNER_ADD);
			sendMsgReq.setKeyid(ownerReq.getId());
			List<String> paramList = new ArrayList<String>();
			paramList.add(userName);
			sendMsgReq.setParams(paramList);
			sendMsgReq.setSendid(he.getId());
			sendMsgReq.setSendname(userName);
			sendMsgReq.setRecid(req.getOwnerId());
			sendMsgReq.setRecname(req.getOwnerName());
			rs = messageService.sendMessageInside(sendMsgReq);
		} else {
			rs.setCode("1");
		}
		appResult = AppResult.valueOf(rs);
		return appResult;
	}
	
	/**
	 * 获取UUID作为主键
	 * @return
	 */
	private String getUUId() {
		// 获取UUID
		String uuid = UUID.randomUUID().toString();
		// 去除字符串中的"-"
		String[] uuid_array = uuid.split("-"); 
		StringBuffer idBuffer = new StringBuffer();
		for (String id : uuid_array) {
			idBuffer.append(id);
		}
		
		return idBuffer.toString();
	}
}
