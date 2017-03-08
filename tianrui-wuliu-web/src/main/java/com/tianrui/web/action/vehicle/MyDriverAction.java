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

import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.intf.IOwnerDriverService;
import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.front.member.MemberFindReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.front.vehicle.OwnerDriverReq;
import com.tianrui.api.req.front.vehicle.VehicleDriverReq;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.vehicle.OwnerDriverResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

/**
 * 我的司机处理类
 * <p>
 * @author guyuke
 * @time 2016年6月01日 上午11:53:38
 */
@Controller
@RequestMapping("/trwuliu/Member/myDriver")
public class MyDriverAction {
	public static Logger logger =LoggerFactory.getLogger(MyDriverAction.class);
	@Autowired
	private IOwnerDriverService ownerDriverService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	private IVehicleDriverService vehicleDriverService;
	
	/**
	 * 我的司机跳转页面
	 * <p>
	 * @return 我的司机页面
	 * @throws IOException
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping("/myDriverPage")
	@AuthValidation(autyType=Constant.AUTHCHECK_USER)
	public ModelAndView indexPage() throws IOException {
		return new ModelAndView("/member/vehicle/myDriverPage");
	}
	
	/**
	 * 添加司机跳转页面
	 * <p>
	 * @return 添加司机页面
	 * @throws IOException
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping("/addDriverPage")
	public ModelAndView addDriverPage() throws IOException {
		return new ModelAndView("/member/vehicle/addDriverPage");
	}
	
	/**
	 * 根据条件查询我的司机信息(分页)
	 * <p>
	 * @param id          信息主键
	 * @param memberId    用户主键
	 * @param driverId    司机主键
	 * @param driverName  司机名字
	 * @param driverTel   司机电话
	 * @param remarkName  备注名
	 * @param pageNo      页码
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/getMyDriverByPage", method = RequestMethod.POST)
	@ResponseBody
	public Result queryMyDriverByPage(String id, 
			                           String memberId,
			                            String driverId,
			                             String driverName,
			                              String driverTel,
			                               String remarkName,
			                                String pageNo,
			                                 String pageSize) throws Exception {
		Result rs = Result.getSuccessResult();
		OwnerDriverReq driverReq = new OwnerDriverReq();
		// 主键
		driverReq.setId(id);
		// 车主ID
		driverReq.setVehicleownerid(memberId);
		// 司机ID
		driverReq.setDriverid(driverId);
		// 司机姓名
		driverReq.setDrivername(driverName);
		// 司机电话
		driverReq.setDrivertel(driverTel);
		// 司机备注名称
		driverReq.setRemarkname(remarkName);
		// 页码
		if (pageNo != null) {
			driverReq.setPageNo(Integer.parseInt(pageNo));
		} else {
			driverReq.setPageNo(0);
		}
		if(StringUtils.isNotBlank(pageSize) && Integer.parseInt(pageSize) >= 1){
			driverReq.setPageSize(Integer.parseInt(pageSize));
		}
		PaginationVO<OwnerDriverResp> pageVo = ownerDriverService.queryMyDriverInfoByPage(driverReq);
		rs.setData(pageVo);
		return rs;
	}
	
	/**
	 * 根据条件查询我的司机信息(查询所有)
	 * <p>
	 * @param id          信息主键
	 * @param memberId    用户主键
	 * @param driverId    司机主键
	 * @param driverName  司机名字
	 * @param driverTel   司机电话
	 * @param remarkName  备注名
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/getMyDriver", method = RequestMethod.POST)
	@ResponseBody
	public Result queryMyDriverByCondition(String id, 
				                            String memberId,
				                             String driverId,
				                              String driverName,
				                               String driverTel,
				                                String remarkName) throws Exception {
		
		Result rs = Result.getSuccessResult();
		OwnerDriverReq driverReq = new OwnerDriverReq();
		// 主键
		driverReq.setId(id);
		// 车主ID
		driverReq.setVehicleownerid(memberId);
		// 司机ID
		driverReq.setDriverid(driverId);
		// 司机姓名
		driverReq.setDrivername(driverName);
		// 司机电话
		driverReq.setDrivertel(driverTel);
		// 司机备注名称
		driverReq.setRemarkname(remarkName);
		
		List<OwnerDriverResp> driverRespList = ownerDriverService.queryMyDriverInfoByCondition(driverReq);
		rs.setData(driverRespList);
		return rs;
	}
	
	/**
	 * 根据条件查询【非】我的司机信息(查询所有)
	 * <p>
	 * @param memberId    车主主键
	 * @param driverTel   司机电话
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/getInfoOutOfDriverRange", method = RequestMethod.POST)
	@ResponseBody
	public Result queryInfoOutOfMyDriverRange(String memberId,
					                           String driverTel) throws Exception {
		
		Result rs = Result.getSuccessResult();
		
		MemberReq re = new MemberReq();
		re.setTelnum(driverTel);
		MemberResp member = systemMemberService.findMemberByTelnum(re);
		
		// 无用户数据
		if(member == null){
			rs.setCode("1");
			rs.setError("没有该司机的信息，请联系其确认注册认证！");
			return rs;
		} else {
			short a = 1;//司机认证，没成功
			if (member.getDriverpercheck() != a) {
				rs.setCode("2");
				rs.setError("该用户尚不是司机，请联系其认证！");
				return rs;
			}else if("0".equals(member.getStatus())){
				rs.setCode("4");
				rs.setError("该用户已被禁用");
				return rs;
			} else {
				// 用户ID
				String id = member.getId();
				
				OwnerDriverReq driverReq = new OwnerDriverReq();
				// 车主ID
				driverReq.setVehicleownerid(memberId);
				// 司机ID
				driverReq.setDriverid(id);
				// 根据车主ID与司机ID查询数据
				List<OwnerDriverResp> list = ownerDriverService.queryMyDriverInfoByCondition(driverReq);
				
				// 已经存在与车主绑定关系
				if (list.size() > 0&&!"2".equals(list.get(0).getStatus())) {
					rs.setCode("1");
					rs.setError("该用户已请求过，不用重复添加");
					return rs;
				} else {
					rs.setData(member);
					return rs;
				}
			}
		}
	}
	
	/**
	 * 根据主键查询我的司机信息
	 * <p>
	 * @param request
	 * @return
	 * @throws Exception
	 * <p>
	 * @author wuchl
	 * @time 2016年6月1日 下午6:15:39
	 */
	@RequestMapping("/findById")
	@ResponseBody
	public Result findByMemberId(HttpServletRequest request) throws Exception {
		Result rs =Result.getSuccessResult();
		MemberVo member = SessionManager.getSessionMember(request);
		List<OwnerDriverResp> resp = ownerDriverService.findOwnDriveById(member.getId());
		rs.setData(resp);
		return rs;
	}
	
	/**
	 * 新增我的司机信息
	 * <p>
	 * @param memberId    用户主键
	 * @param driverId    司机主键
	 * @param driverName  司机名字
	 * @param driverTel   司机电话
	 * @param remarkName  备注名
	 * @param remarkName  当前用户名
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/saveMyDriver", method = RequestMethod.POST)
	@ResponseBody
	public Result saveMyDriver(String memberId,
					            String driverId,
					             String driverName,
					              String driverTel,
					               String remarkName,
					                HttpServletRequest request
			) throws Exception {
		
		Result rs = Result.getSuccessResult();
		MemberVo session = SessionManager.getSessionMember(request);
		String userName = "";
		if(StringUtils.isNotBlank(session.getUserName())){
			userName = session.getUserName();
		}else if(StringUtils.isNotBlank(session.getNickname())){
			userName = session.getNickname();
		}else{
			userName = session.getCellphone();
		}
		
		OwnerDriverReq q = new OwnerDriverReq();
		q.setVehicleownerid(memberId);
		q.setDriverid(driverId);
		List<OwnerDriverResp> list = ownerDriverService.queryMyDriverInfoByCondition(q);
		if(list.size()!=0){
			if("2".equals(list.get(0).getStatus())||"3".equals(list.get(0).getStatus())){//已拒绝
				ownerDriverService.deleteByPrimaryKey(list.get(0).getId());
			}else{
				rs.setCode("1");
				rs.setError("该用户已请求过，不用重复添加");
				return rs;
			}
		}
		OwnerDriverReq driverReq = new OwnerDriverReq();
		// 主键
		driverReq.setId(getUUId());
		// 车主ID
		driverReq.setVehicleownerid(memberId);
		// 司机ID
		driverReq.setDriverid(driverId);
		// 司机姓名
		driverReq.setDrivername(driverName);
		// 司机电话
		driverReq.setDrivertel(driverTel);
		// 司机备注名称
		driverReq.setRemarkname(remarkName);
		String sd =  Constant.SYSTEM_PUSH_STATUS;
		// 默认待回复
		driverReq.setStatus("0");
		// 创建人
		driverReq.setCreator(memberId);
		
		// 插入操作
		rs = ownerDriverService.insert(driverReq);
		
		if ("000000".equals(rs.getCode())) {
			// 发送消息
			SendMsgReq sendMsgReq = new SendMsgReq();
			// 会员
			sendMsgReq.setType("2");
			// 司机
			sendMsgReq.setCodeEnum(MessageCodeEnum.VEHI_2DRIVER_ADD);
			sendMsgReq.setRecType(MessageCodeEnum.VEHI_2DRIVER_ADD.getType());
			sendMsgReq.setKeyid(driverReq.getId());
			List<String> paramList = new ArrayList<String>();
			paramList.add(userName);
			sendMsgReq.setParams(paramList);
			sendMsgReq.setSendid(memberId);
			sendMsgReq.setSendname(userName);
			sendMsgReq.setRecid(driverId);
			sendMsgReq.setRecname(driverName);
			rs = messageService.sendMessageInside(sendMsgReq);
		} else {
			rs.setCode("1");
		}
		
		return rs;
	}
	
	/**
	 * 更新我的司机信息
	 * <p>
	 * @param id          信息主键
	 * @param memberId    用户主键
	 * @param driverId    司机主键
	 * @param driverName  司机名字
	 * @param driverTel   司机电话
	 * @param remarkName  备注名
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/updateMyDriver", method = RequestMethod.POST)
	@ResponseBody
	public Result updateMyDriver(String id, 
					              String memberId,
					               String driverId,
						            String driverName,
						             String driverTel,
						              String remarkName) throws Exception {
		Result rs = Result.getSuccessResult();
		
		OwnerDriverReq driverReq = new OwnerDriverReq();
		// 主键
		driverReq.setId(id);
		// 车主ID
		driverReq.setVehicleownerid(memberId);
		// 司机ID
		driverReq.setDriverid(driverId);
		// 司机姓名
		driverReq.setDrivername(driverName);
		// 司机电话
		driverReq.setDrivertel(driverTel);
		// 司机备注名称
		driverReq.setRemarkname(remarkName);
		
		// 更新操作
		rs = ownerDriverService.updateByPrimaryKeySelective(driverReq);
		
		return rs;
	}
	
	/**
	 * 删除我的司机信息
	 * <p>
	 * @param id    档案信息主键
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/deleteMyDriver", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteMyDriver(String id,HttpServletRequest request) throws Exception {

		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		OwnerDriverResp resp = ownerDriverService.findById(id);
		VehicleDriverReq req = new VehicleDriverReq();
		req.setDriverId(resp.getDriverid());
		req.setCreator(vo.getId());
		List<VehicleDriverResp> list = vehicleDriverService.queryVehiDriverByCondition(req);
		if(list.size()!=0){
			rs.setCode("1");
			rs.setError("该司机已有绑定关系");
			return rs;
		}else{
			rs = ownerDriverService.deleteByPrimaryKey(id);
		}
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
