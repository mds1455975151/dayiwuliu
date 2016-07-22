package com.tianrui.web.action.vehicle;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IMemberOwnerService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.front.member.MemberFindReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.api.req.front.vehicle.VehicleDriverReq;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.vehicle.MemberOwnerResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

/**
 * 我的车主处理类
 * <p>
 * @author guyuke
 * @time 2016年6月01日 上午11:53:38
 */
@Controller
@RequestMapping("/trwuliu/Member/myVehiOwner")
public class MyVehicleOwnerAction {
	public static Logger logger =LoggerFactory.getLogger(MyVehicleOwnerAction.class);
	@Autowired
	private IMemberOwnerService memberOwnerService;
	@Autowired
	private IVehicleDriverService iVehicleDriverService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private ISystemMemberService systemMemberService;
	
	/**
	 * 我的车主跳转页面
	 * <p>
	 * @return 我的车主页面
	 * @throws IOException
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping("/myVehiOwnerPage")
	@AuthValidation(autyType=Constant.AUTHCHECK_OWNER)
	public ModelAndView indexPage() throws IOException{
		return new ModelAndView("/member/vehicle/myVehiOwnerPage");
	}
	
	/**
	 * 添加车主跳转页面
	 * <p>
	 * @return 添加车主页面
	 * @throws IOException
	 * <p>
	 * @author guyuke
	 * @time 2016年6月6日 上午11:54:00
	 */
	@RequestMapping("/addVehiOwnerPage")
	public ModelAndView addVehiOwnerPage() throws IOException {
		return new ModelAndView("/member/vehicle/addVehiOwnerPage");
	}
	
	/**
	 * 根据条件查询我的车主信息
	 * <p>
	 * @param id           主键
	 * @param memberId     会员主键
	 * @param ownerId      车主主键
	 * @param ownerName    车主名字
	 * @param ownerTel     车主电话
	 * @param status       状态 
	 * @param pageNo       页码
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/getMyVehiOwnerByPage", method = RequestMethod.POST)
	@ResponseBody
	public Result queryMyVehiOwnerByPage(String id, 
		                                  String memberId, 
		                                   String ownerId, 
		                                    String ownerName, 
		                                     String ownerTel, 
		                                      String status, 
		                                       String pageNo) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberOwnerReq ownerReq = new MemberOwnerReq();
		// 主键
		ownerReq.setId(id);
		// 会员主键
		ownerReq.setMemberId(memberId);
		// 车主主键
		ownerReq.setOwnerId(ownerId);
		// 车主名字
		ownerReq.setOwnerName(ownerName);
		// 车主电话
		ownerReq.setOwnerTel(ownerTel);
		// 状态
		ownerReq.setStatus(status);
		// 页码
		if (pageNo != null) {
			ownerReq.setPageNo(Integer.parseInt(pageNo));
		} else {
			ownerReq.setPageNo(0);
		}
		PaginationVO<MemberOwnerResp> pageVo = memberOwnerService.queryMyVehiOwnerByPage(ownerReq);
		rs.setData(pageVo);
		return rs;
	}
	
	/**
	 * 根据条件查询我的车主信息(查询所有)
	 * <p>
	 * @param id           主键
	 * @param memberId     会员主键
	 * @param ownerId      车主主键
	 * @param ownerName    车主名字
	 * @param ownerTel     车主电话
	 * @param status       状态 
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/getMyVehiOwner", method = RequestMethod.POST)
	@ResponseBody
	public Result queryMyDriverByCondition(String id, 
								            String memberId, 
								             String ownerId, 
								              String ownerName, 
								               String ownerTel, 
								                String status) throws Exception{
		
		Result rs = Result.getSuccessResult();
		MemberOwnerReq ownerReq = new MemberOwnerReq();
		// 主键
		ownerReq.setId(id);
		// 会员主键
		ownerReq.setMemberId(memberId);
		// 车主主键
		ownerReq.setOwnerId(ownerId);
		// 车主名字
		ownerReq.setOwnerName(ownerName);
		// 车主电话
		ownerReq.setOwnerTel(ownerTel);
		// 状态
		ownerReq.setStatus(status);
		
		List<MemberOwnerResp> ownerRespList = memberOwnerService.queryMyVehiOwnerByCondition(ownerReq);
		rs.setData(ownerRespList);
		return rs;
	}
	
	/**
	 * 根据条件查询【非】我的车主信息(查询所有)
	 * <p>
	 * @param memberId    用户主键
	 * @param ownerTel    车主电话
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/getInfoOutOfOwnerRange", method = RequestMethod.POST)
	@ResponseBody
	public Result queryInfoOutOfMyOwnerRange(String memberId,
					                           String ownerTel) throws Exception {
		
		Result rs = Result.getSuccessResult();
		
		MemberReq re = new MemberReq();
		re.setTelnum(ownerTel);
		MemberResp member = systemMemberService.findMemberByTelnum(re);
		// 无用户数据
		if(member == null){
			rs.setCode("1");
			rs.setError("没有该车主的信息，请联系其确认注册认证！");
			return rs;
		} else {
			// 用户ID
			String id = member.getId();
			// 根据创建人查询数据
			VehicleDriverReq vehiDriverReq = new VehicleDriverReq();
			// 创建人
			vehiDriverReq.setCreator(id);
			List<VehicleDriverResp> vehiDriverRespList = iVehicleDriverService.queryVehiDriverByCondition(vehiDriverReq);
			// 不是车主
			if (vehiDriverRespList == null || vehiDriverRespList.size() < 1) {
				rs.setCode("2");
				rs.setError("该用户尚不是车主，请联系其认证！");
				return rs;
			} else {
				MemberOwnerReq ownerReq = new MemberOwnerReq();
				// 车主ID
				ownerReq.setMemberId(memberId);
				// 司机ID
				ownerReq.setOwnerId(id);
				// 根据车主ID与司机ID查询数据
				List<MemberOwnerResp> ownerRespList = memberOwnerService.queryMyVehiOwnerByCondition(ownerReq);
				
				// 已经存在与车主绑定关系
				if (ownerRespList.size() > 0 && !"2".equals(ownerRespList.get(0).getStatus())) {
					rs.setCode("3");
					rs.setError("该用户已经绑定，无法再次进行绑定！");
					return rs;
				} else {
					rs.setData(member);
					return rs;
				}
			}
		}
	}
	
	/**
	 * 新增我的车主信息
	 * <p>
	 * @param id           主键
	 * @param memberId     会员主键
	 * @param ownerId      车主主键
	 * @param ownerName    车主名字
	 * @param ownerTel     车主电话
	 * @param status       状态 
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/saveMyVehicleOwner", method = RequestMethod.POST)
	@ResponseBody
	public Result saveMyVehiOwner(String memberId, 
						           String ownerId, 
						            String ownerName, 
						             String ownerTel, 
						              String status,
//						               String userName,
						               HttpServletRequest request
			) throws Exception{
		
		Result rs = Result.getSuccessResult();
		MemberVo voca= SessionManager.getSessionMember(request);
		String userName = "";
		if(StringUtils.isNotBlank(voca.getCompanyName())){
			userName = voca.getCompanyName();
		}else if(StringUtils.isNotBlank(voca.getUserName())){
			userName = voca.getUserName();
		}else if(StringUtils.isNotBlank(voca.getCellphone())){
			userName = voca.getCellphone();
		}
		MemberOwnerReq req = new MemberOwnerReq();
		// 会员主键
		req.setMemberId(memberId);
		// 车主主键
		req.setOwnerId(ownerId);
		List<MemberOwnerResp> list = memberOwnerService.queryMyVehiOwnerByCondition(req);
		if(list.size()>0){
			for(MemberOwnerResp resp : list){
				if("2".equals(resp.getStatus())){
					memberOwnerService.deleteByPrimaryKey(resp.getId());
				}else{
					rs.setCode("1");
					rs.setError("该用户已添加");
					return rs;
				}
			}
		}
		MemberOwnerReq ownerReq = new MemberOwnerReq();
		// 主键
		ownerReq.setId(getUUId());
		// 会员主键
		ownerReq.setMemberId(memberId);
		// 车主主键
		ownerReq.setOwnerId(ownerId);
		// 车主名字
		ownerReq.setOwnerName(ownerName);
		// 车主电话
		ownerReq.setOwnerTel(ownerTel);
		// 状态
		if (status == null || "".equals(status)) {
			// 默认待回复
			ownerReq.setStatus("0");
		} else {
			ownerReq.setStatus(status);
		}
		
		// 插入操作
		rs = memberOwnerService.insert(ownerReq);
		
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
			sendMsgReq.setSendid(memberId);
			sendMsgReq.setSendname(userName);
			sendMsgReq.setRecid(ownerId);
			sendMsgReq.setRecname(ownerName);
			rs = messageService.sendMessageInside(sendMsgReq);
		} else {
			rs.setCode("1");
		}
		
		return rs;
	}
	
	/**
	 * 更新我的车主信息
	 * <p>
	 * @param id           主键
	 * @param memberId     会员主键
	 * @param ownerId      车主主键
	 * @param ownerName    车主名字
	 * @param ownerTel     车主电话
	 * @param isEnabled    是否开启
	 * @param status       状态 
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/updateMyVehicleOwner", method = RequestMethod.POST)
	@ResponseBody
	public Result updateMyVehiOwner(String id, 
						             String memberId, 
						              String ownerId, 
						               String ownerName, 
						                String ownerTel,
						                 String isEnabled,
						                  String status) throws Exception{
		Result rs = Result.getSuccessResult();
		
		MemberOwnerReq ownerReq = new MemberOwnerReq();
		// 主键
		ownerReq.setId(id);
		// 会员主键
		ownerReq.setMemberId(memberId);
		// 车主主键
		ownerReq.setOwnerId(ownerId);
		// 车主名字
		ownerReq.setOwnerName(ownerName);
		// 车主电话
		ownerReq.setOwnerTel(ownerTel);
		// 是否启用
		ownerReq.setIsEnabled(isEnabled);
		// 状态
		ownerReq.setStatus(status);
		
		// 更新操作
		rs = memberOwnerService.updateByPrimaryKeySelective(ownerReq);
		
		return rs;
	}
	
	/**
	 * 删除我的车主信息
	 * <p>
	 * @param id    主键
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/deleteMyVehicleOwner", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteMyVehiOwner(String id) throws Exception{

		Result rs = memberOwnerService.deleteByPrimaryKey(id);
		return rs;
	}
	
	/**
	 * 获取UUID作为主键
	 * <p>
	 * @return id
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
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
