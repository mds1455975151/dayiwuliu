
/**
 * @标题: AdminMemberAction.java
 * @功能描述：TODO
 * @作者： lsj
 * @创建时间： 2016年4月27日 下午1:22:28
 */

package com.tianrui.trwl.admin.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IMyDriverService;
import com.tianrui.api.admin.intf.IMyVehicleService;
import com.tianrui.api.intf.IDataDictService;
import com.tianrui.api.intf.IMemberVehicleService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.intf.IOwnerDriverService;
import com.tianrui.api.intf.ISystemMemberInfoRecordService;
import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.admin.MyDriverReq;
import com.tianrui.api.req.admin.MyVehicleReq;
import com.tianrui.api.req.front.member.MemberFindReq;
import com.tianrui.api.req.front.member.MemberInfoReq;
import com.tianrui.api.req.front.member.MemberUpdateReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.front.system.DataDictReq;
import com.tianrui.api.req.front.vehicle.MemberVehicleReq;
import com.tianrui.api.req.front.vehicle.VehicleAndDriverReq;
import com.tianrui.api.resp.admin.MyDriverResp;
import com.tianrui.api.resp.admin.MyVehicleResp;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.common.DataDictResp;
import com.tianrui.api.resp.front.member.MemberInfoRecordResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.vehicle.VehicleAndDriverResp;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

/**
 * @类描述：
 * @创建人：lsj
 * @创建时间：2016年4月27日下午1:22:28
 *
 * @修改人：lsj
 * @修改时间：2016年4月27日下午1:22:28
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/AdminMember")
public class AdminMemberAction {

	@Autowired
	private IDataDictService dataDict;
	@Autowired
	private IMyVehicleService vehicleService;
	@Autowired
	private IMemberVehicleService MemberVehicleService;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	protected ISystemMemberInfoRecordService systemMemberInfoRecordService;
	@Autowired
	private ISystemMemberInfoService systemMemberInfoService;
	@Autowired
	private IMyDriverService driverService;
	@Autowired
	IMessageService messageService;
	/**
	 * @描述:车主查询
	 * @return
	 * @throws Exception 
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年6月2日上午11:37:57
	 */
	@RequestMapping("/chezhuManager")
	public ModelAndView chezhuManager() throws Exception{
		ModelAndView view = new ModelAndView();
		DataDictReq dreq = new DataDictReq();
		dreq.setSubCode("perchstatus");
		List<DataDictResp> perchstatus = dataDict.findDictList(dreq);
		view.addObject("perchstatus",perchstatus);
		view.setViewName("/adminMember/chezhu_manager");
		return view;
	}
	/**
	 * 
	 * @描述:司机审核页面
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年6月1日下午4:30:25
	 */
	@RequestMapping("/driverShenhe")
	public ModelAndView driverShenhe(String id) throws Exception{
//		MemberInfoResp resp = infoService.findByMemberId(id);
		MemberInfoRecordResp resp = systemMemberInfoRecordService.findByMemberId(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberInfo", resp);
		mav.setViewName("/adminMember/user_driversh");
		return mav;

	}
	/**
	 * 
	 * @描述:车辆审核页面
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年6月4日下午4:28:28
	 */
	@RequestMapping("/carShenhe")
	public ModelAndView carShenhe(String id) throws Exception{
		MyVehicleResp veh = vehicleService.findById(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("Vehicle", veh);
		mav.setViewName("/adminMember/car_shenhe");
		return mav;
	}
	/**
	 * 
	 * @描述:车辆管理页面
	 * @return
	 * @throws Exception
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年6月4日上午9:10:20
	 */
	@RequestMapping("/carManager")
	public ModelAndView carManager() throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminMember/car_manager");
		return view;
	}
	
	/**
	 * 
	 * @描述:司机信息查询
	 * @return
	 * @throws Exception 
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年5月31日下午5:02:21
	 */
	@RequestMapping("/userDriver")
	public ModelAndView userDriver() throws Exception{
		ModelAndView view = new ModelAndView();
		DataDictReq dreq = new DataDictReq();
		dreq.setSubCode("perchstatus");
		List<DataDictResp> perchstatus = dataDict.findDictList(dreq);
		view.addObject("perchstatus",perchstatus);
		view.setViewName("/adminMember/user_driver");
		return view;
	}
	/**
	 * 
	 * @描述:普通会员审核
	 * @return
	 * @throws Exception 
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年4月28日上午10:26:05
	 */
	@RequestMapping("/userShenhe")
	public ModelAndView userShenhe(String id) throws Exception{
		MemberInfoRecordResp resp = systemMemberInfoRecordService.findByMemberId(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberInfo", resp);
		mav.setViewName("/adminMember/user_shenhe");
		return mav;
	}
	/**
	 * 
	 * @描述 会员信息查询页面,git更新代码测试
	 * @return
	 * @throws Exception 
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年4月28日上午10:23:31
	 */
	@RequestMapping("/userPerson")
	public ModelAndView userPerson() throws Exception{
		
		DataDictReq dreq = new DataDictReq();
		dreq.setSubCode("status");
		List<DataDictResp> status = dataDict.findDictList(dreq);
		
		dreq.setSubCode("perchstatus");
		List<DataDictResp> perchstatus = dataDict.findDictList(dreq);
		
		dreq.setSubCode("sourcetype");
		List<DataDictResp> sourcetype = dataDict.findDictList(dreq);
		
		dreq.setSubCode("userType");
		List<DataDictResp> usertype = dataDict.findDictList(dreq);
	
		ModelAndView view = new ModelAndView();
		view.addObject("status",status);
		view.addObject("usertype",usertype);
		view.addObject("perchstatus",perchstatus);
		view.addObject("sourcetype",sourcetype);
		view.setViewName("/adminMember/user_person");
		return view;
	}
	/**
	 * 
	 * @描述:会员信息查询
	 * @param member
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月2日上午10:13:58
	 */
	@RequestMapping("/findMemberList")
	@ResponseBody
	public Result findMemberList(MemberFindReq member) throws Exception{
		Result rs = Result.getSuccessResult();
		member.setUserType("0");//* 0-查询普通用户, 1-查询司机用户
		PageResp<MemberResp> list = systemMemberService.findMemberlist(member);
		
		rs.setData(list);
		return rs;
	}
	/**
	 * 
	 * @描述:查询司机用户
	 * @param member
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月31日下午5:22:29
	 */
	@RequestMapping("/findDriverMember")
	@ResponseBody
	public Result findDriverMember(MemberFindReq member) throws Exception{
		Result rs = Result.getSuccessResult();
		member.setUserType("1");//* 0-查询普通用户, 1-查询司机用户
		PageResp<MemberResp> list = systemMemberService.findMemberlist(member);
		rs.setData(list);
		return rs;
	}
	/**
	 * 
	 * @描述:车主档案查询
	 * @param member
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月3日上午9:26:22
	 */
	@RequestMapping("/findDriverOwner")
	@ResponseBody
	public Result findDriverOwner(MemberFindReq member) throws Exception{
		Result rs = Result.getSuccessResult();
		member.setUserType("2");//* 0-查询普通用户, 1-查询司机用户  ,2-查询车主用户
		PageResp<MemberResp> list = systemMemberService.findMemberlist(member);
		rs.setData(list);
		return rs;
	}
	/**
	 * 
	 * @描述:查询我的司机
	 * @param req
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月3日下午2:15:58
	 */
	@RequestMapping("/findMyDriver")
	@ResponseBody
	public Result findMyDriver(MyDriverReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		List<MyDriverResp> list = driverService.findByEntity(req);
		rs.setData(list);
		return rs;
	}
	
	/**
	 * 
	 * @描述:企业会员审核
	 * @param id
	 * @param massage
	 * @param companypercheck
	 * @param request
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月22日下午2:54:53
	 */
	@RequestMapping("/companyReview")
	@ResponseBody
	public Result adminReview(
			String id,
			String massage,
			String companypercheck,
			HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		MemberInfoReq req = new MemberInfoReq();
		req.setId(id);
		req.setAuditid(id);
		req.setAuditname(user.getAccount());
		req.setAudittime(new Date().getTime());
		req.setRejectReason(massage);
		req.setCompanypercheck(companypercheck);
		rs = systemMemberInfoService.companyReview(req);
		return rs;
	}
	/**
	 * 
	 * @描述:普通用户审核
	 * @param id
	 * @param massage
	 * @param userpercheck
	 * @param request
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月22日下午2:55:17
	 */
	@RequestMapping("/userReview")
	@ResponseBody
	public Result userReview(
			String id,
			String massage,
			String userpercheck,
			HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		MemberInfoReq req = new MemberInfoReq();
		req.setId(id);
		req.setAuditid(id);
		req.setAuditname(user.getAccount());
		req.setAudittime(new Date().getTime());
		req.setRejectReason(massage);
		req.setUserpercheck(userpercheck);
		rs = systemMemberInfoService.userReview(req);
		return rs;
	}
	/**
	 * 
	 * @描述:司机用户审核
	 * @param id
	 * @param massage
	 * @param driverpercheck
	 * @param request
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月22日下午2:55:32
	 */
	@RequestMapping("/driverReview")
	@ResponseBody
	public Result driverReview(
			String id,
			String massage,
			String driverpercheck,
			HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		MemberInfoReq req = new MemberInfoReq();
		req.setId(id);
		req.setAuditid(id);
		req.setAuditname(user.getAccount());
		req.setAudittime(new Date().getTime());
		req.setRejectReason(massage);
		req.setDriverpercheck(driverpercheck);
		rs = systemMemberInfoService.driverReview(req);
		return rs;
	}
	
	/**
	 * 
	 * @描述:后台启用/禁用用户
	 * @return
	 * @throws Exception 
	 * @返回类型 String
	 * @创建人 lsj
	 * @创建时间 2016年4月29日上午9:37:12
	 */
	@RequestMapping("/userprohibition")
	@ResponseBody
	public Result userprohibition(String id,
			String status) throws Exception{
		Result rs = Result.getSuccessResult();
		if(StringUtils.isBlank(id)||StringUtils.isBlank(status)){
			rs.setCode("1");
			rs.setError("数据不能为空");
			return rs;
		}
		MemberResp resp = systemMemberService.findById(id);
		if(resp==null){
			rs.setCode("1");
			rs.setError("无效的id");
			return rs;
		}
		String ss ="";
		if("1".equals(status)){
			ss="0";
		}
		if("0".equals(status)){
			ss="1";
		}
		MemberUpdateReq req = new MemberUpdateReq();
		req.setId(id);
		req.setStatus(ss);
		if(!systemMemberService.updateMember(req)){
			rs.setCode("1");
			rs.setError("修改失败");
			return rs;
		}
		return rs;
	}
	/**
	 * 
	 * @描述:查询平台车辆信息
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月4日上午10:35:12
	 */
	@RequestMapping("/findCarManager")
	@ResponseBody
	public Result findCarManager(MyVehicleReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PageResp list = vehicleService.findByEntity(req);
		rs.setData(list);
		return rs;
	}
	/**
	 * 
	 * @描述:车辆详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月4日下午2:53:14
	 */
	@RequestMapping("/findCarManagerById")
	@ResponseBody
	public Result findCarManagerById(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		MyVehicleResp resp = vehicleService.findById(id);
		rs.setData(resp);
		return rs;
	}
	/**
	 * 
	 * @描述:车辆审核
	 * @param id 车辆id
	 * @param type -1:认证失败   1:认证成功
	 * @param massage 认证失败愿意（认证成功不用传）
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月4日下午5:10:43
	 */
	@RequestMapping("/carReviw")
	@ResponseBody
	public Result carReviw(
			String id,
			String type,
			String massage,
			String memberid) throws Exception{
		Result rs = Result.getSuccessResult();
		if(id==null||"".equals(id)){
			rs.setCode("1");
			rs.setError("数据不能为空");
			return rs;
		}
		if(type==null||"".equals(type)){
			rs.setCode("1");
			rs.setError("数据不能为空");
			return rs;
		}
		if(memberid==null||"".equals(memberid)){
			rs.setCode("1");
			rs.setError("数据不能为空");
			return rs;
		}
		MemberVehicleReq req = new MemberVehicleReq();
		req.setId(id);
		req.setStatus(type);
		req.setMemo(massage);
		req.setAudittime(new Date().getTime());
		//修改车辆认证状态
		rs = MemberVehicleService.updateByPrimaryKeySelective(req);
		return rs;
	}
}
