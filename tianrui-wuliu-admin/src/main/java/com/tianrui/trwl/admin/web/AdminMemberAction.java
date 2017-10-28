
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IMyVehicleService;
import com.tianrui.api.intf.IDataDictService;
import com.tianrui.api.intf.IDataService;
import com.tianrui.api.intf.IFileService;
import com.tianrui.api.intf.IMemberCapaService;
import com.tianrui.api.intf.IMemberOwnerService;
import com.tianrui.api.intf.IMemberVehicleService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.intf.ISystemMemberInfoRecordService;
import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.admin.MyVehicleReq;
import com.tianrui.api.req.data.WebDictReq;
import com.tianrui.api.req.front.capa.CapaReq;
import com.tianrui.api.req.front.member.MemberFindReq;
import com.tianrui.api.req.front.member.MemberInfoReq;
import com.tianrui.api.req.front.member.MemberUpdateReq;
import com.tianrui.api.req.front.system.DataDictReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.api.req.front.vehicle.MemberVehicleReq;
import com.tianrui.api.req.front.vehicle.VehicleDriverMemberReq;
import com.tianrui.api.req.front.vehicle.VehicleDriverReq;
import com.tianrui.api.resp.admin.MyVehicleResp;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.common.DataDictResp;
import com.tianrui.api.resp.data.WebDictResp;
import com.tianrui.api.resp.front.capa.MemberCapaListResp;
import com.tianrui.api.resp.front.member.MemberInfoRecordResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.vehicle.MemberOwnerResp;
import com.tianrui.api.resp.front.vehicle.MemberVehicleResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverMemberResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.bean.VehicleDriverMember;
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
	private IMemberVehicleService memberVehicleService;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	protected ISystemMemberInfoRecordService systemMemberInfoRecordService;
	@Autowired
	private ISystemMemberInfoService systemMemberInfoService;
	@Autowired
	protected IFileService iFileService;
	@Autowired
	IMessageService messageService;
	@Autowired
	IMemberCapaService memberCapaService;
	@Autowired
	IMemberOwnerService memberOwnerService;
	@Autowired
	IDataService dataService;
	@Autowired
	private IVehicleDriverService vehicleDriverService;
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
	public ModelAndView driverShenhe(String id,String pageNo) throws Exception{
		MemberInfoRecordResp resp = systemMemberInfoRecordService.findByMemberId(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberInfo", resp);
		mav.addObject("pageNo", pageNo);
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
	public ModelAndView carShenhe(String id,String pageNo) throws Exception{
		MyVehicleResp veh = vehicleService.findById(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("Vehicle", veh);
		mav.addObject("pageNo", pageNo);
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
	public ModelAndView carManager(String pageNo) throws Exception{
		ModelAndView view = new ModelAndView();
		WebDictReq req = new WebDictReq();
		req.setType("vehicle");
		List<WebDictResp> list = dataService.find(req);
		view.addObject("vType", list);
		view.setViewName("/adminMember/car_manager");
		view.addObject("pageNo", pageNo);
		return view;
	}
	/**
	 * 运力管理界面
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Capacity")
	public ModelAndView chezhuManager(String pageNo,VehicleDriverReq req) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminMember/management");
		return view;
	}
	/**
	 * 运力查询
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findCapacity")
	@ResponseBody
	public Result findCapacity(VehicleDriverMemberReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<VehicleDriverMemberResp> page = vehicleDriverService.find(req);
		rs.setData(page);
		return rs;
		
	}
	/**
	 * 运力解绑
	 * @param vehicleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/unbundled")
	@ResponseBody
	public Result unbundled(String id)throws Exception{
		Result rs = Result.getSuccessResult();
		rs=vehicleDriverService.unbundled(id);
		return rs;
	}
	/**
	 * 运力解绑详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findUnbundledById")
	@ResponseBody
	public Result findUnbundledById(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		VehicleDriverResp resp = vehicleDriverService.findUnbundledById(id);
		rs.setData(resp);
		return rs;
	}
	/**
	 * @throws Exception 
	 * @Title: findReason 
	 * @Description: 注册用户认证失败原因
	 * @param @param id
	 * @param @return   
	 * @return Result    
	 * @throws
	 */
	@RequestMapping("/findReason")
	@ResponseBody
	public Result findReason(String id,Long submitDate) throws Exception {
		Result rs = Result.getSuccessResult();
		rs = systemMemberInfoRecordService.findReason(id,submitDate);
		return rs;
	}
	/**
	 * 运力全平台司机绑定
	 * @Title: bind 
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @param @throws Exception   
	 * @return Result    
	 * @throws
	 */
	@RequestMapping("/bind")
	@ResponseBody
	public Result bind(String id,String driverid,String alDriver) throws Exception {
		Result rs = Result.getSuccessResult();
		rs= vehicleDriverService.bind(id,driverid,alDriver);
		return rs;
	}
	/**
	 * @throws Exception 
	 * @Title: bindDriver 
	 * @Description: 全平台司机搜索
	 * @param @return   
	 * @return Result    
	 * @throws
	 */
	@RequestMapping("/bindDriver")
	@ResponseBody
	public Result bindDriver(String phone) throws Exception{
		Result rs = new Result();
		MemberResp resp =vehicleDriverService.bindDriver(phone);
		if(resp==null){
			rs.setCode("1");
			rs.setError("无效的用户");
			return rs;
		}else if(resp.getDriverpercheck().toString().equals("1")){
			rs.setCode("000000");
			rs.setData(resp);
		}else{
			rs.setCode("2");
			rs.setError("请先输入认证通过的司机账号！");
		}
		return rs;
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
	public ModelAndView userDriver(String pageNo) throws Exception{
		ModelAndView view = new ModelAndView();
		DataDictReq dreq = new DataDictReq();
		dreq.setSubCode("perchstatus");
		List<DataDictResp> perchstatus = dataDict.findDictList(dreq);
		view.addObject("perchstatus",perchstatus);
		view.addObject("pageNo",pageNo);
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
	public ModelAndView userShenhe(String id,String pageNo) throws Exception{
		MemberInfoRecordResp resp = systemMemberInfoRecordService.findByMemberId(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberInfo", resp);
		mav.addObject("pageNo", pageNo);
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
	public ModelAndView userPerson(String pageNo) throws Exception{
		
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
		view.addObject("pageNo",pageNo);
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
	/** 我的承运商查询*/
	@RequestMapping("/vehicOwner")
	@ResponseBody
	public Result vehicOwner(MemberOwnerReq req)throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<MemberOwnerResp> pageVo = memberOwnerService.queryMyVehiOwnerByPage(req);
		rs.setData(pageVo);
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
	 * 查询运力管理中司机详情
	 * @param member
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findDriverDetail")
	@ResponseBody
	public Result findDriverDetail(String driverId) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberResp resp = systemMemberService.findByMemberId(driverId);
		rs.setData(resp);
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
	public Result findMyDriver(CapaReq req) throws Exception{
		Result rs = Result.getSuccessResult();
//		List<MyDriverResp> list = driverService.findByEntity(req);
		//TODO
		PaginationVO<MemberCapaListResp> pa = memberCapaService.index(req);
		rs.setData(pa);
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
	/** 修改道路运输证
	 * @throws Exception */
	@RequestMapping("updateCarMamage")
	@ResponseBody
	public Result updateCarMamage(MyVehicleReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleService.updateVehicle(req);
		return rs;
	}
	
	/** 车辆信息补全 页面跳转*/
	@RequestMapping("/carBuquanPage")
	public ModelAndView carBuquanPage(String id,String pageNo) throws Exception{
		MyVehicleResp veh = vehicleService.findById(id);
		ModelAndView view = new ModelAndView();
		view.addObject("Vehicle", veh);
		view.addObject("pageNo", pageNo);
		WebDictReq req = new WebDictReq();
		req.setType("vehicle");
		view.addObject("vt", dataService.find(req));
		view.setViewName("/adminMember/car_buquan");
		return view;
	}
	
	/** 车辆信息补全
	 * @throws Exception */
	@RequestMapping("carBUquan")
	@ResponseBody
	public Result carBUquan(MemberVehicleReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		
		MemberVehicleReq rreq = new MemberVehicleReq();
		rreq.setVehicleNo(req.getVehicleNo());
		rreq.setVehiclePrefix(req.getVehiclePrefix());
		List<MemberVehicleResp> list = memberVehicleService.queryMyVehicleInfoByCondition(rreq);
		if(list.size()!=0){
			MemberVehicleResp resp = list.get(0);
			if(!resp.getId().equals(req.getId())){
				rs.setCode("1");
				rs.setError("该车牌号已被认证");
				return rs;
			}
		}
		
		req.setDesc2("2");
		req.setStatus("1");
		rs = memberVehicleService.updateByPrimaryKeySelective(req);
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
			String vehiclePrefix,
			String vehicleNo,
			String massage,
			String memberid,
			HttpServletRequest request) throws Exception{
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
		Users user = SessionManager.getSessionMember(request);
		MemberVehicleReq req = new MemberVehicleReq();
		req.setId(id);
		req.setStatus(type);
		req.setMemo(massage);
		req.setAudittime(new Date().getTime());
		req.setModifier(user.getAccount());
		req.setModifyTime(System.currentTimeMillis());
		//修改车辆认证状态
		rs = memberVehicleService.updateByPrimaryKeySelective(req);
		return rs;
	}
	/** 修改车辆照片
	 * @throws Exception */
	@RequestMapping(value="uptVehicPic",method=RequestMethod.POST )
	@ResponseBody
	public Result uptVehicPic(HttpServletRequest request,String id , String file , String type, String code) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user =SessionManager.getSessionMember(request);
//		rs = iFileService.uploadByteImg(file,user.getAccount());
		if("000000".equals(rs.getCode())){
			MemberVehicleReq req = new MemberVehicleReq();
			req.setId(id);
			if("1".equals(type)){
				req.setVehiHeadImgPath(file);
			}else if("2".equals(type)){
				req.setVehiLicenseImgPath(file);
			}else if("3".equals(type)){
				req.setRegistcode(code);
				req.setRegistimage(file);
			}else if("4".equals(type)){
				req.setOpercode(code);
				req.setOperimage(file);
			}else if("5".equals(type)){
				req.setRoadtransportcode(code);
				req.setRoadtransportimage(file);
			}else if("6".equals(type)){
				req.setIdentitycode(code);
				req.setIdentieyimage(file);
			}
			rs = memberVehicleService.updateByPrimaryKeySelective(req);
		}
		return rs;
	}
	/** 修改用户照片
	 * @throws Exception */
	@RequestMapping(value="uptMemberPic",method=RequestMethod.POST )
	@ResponseBody
	public Result uptMemberPic(HttpServletRequest request,String id , String file , String type,String code) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user =SessionManager.getSessionMember(request);
//		rs = iFileService.uploadByteImg(file,user.getAccount());
		if("000000".equals(rs.getCode())){
			MemberInfoReq req = new MemberInfoReq();
			req.setId(id);
			//身份证
			if("1".equals(type)){
				req.setIdcardsImagePath(file);
			//驾驶证
			}else if("2".equals(type)){
				req.setDriveImagePath(file);
			//企业
			}else if("3".equals(type)){
				req.setLicenseImagePath(file);
			//道路运输经营许可证
			}else if("4".equals(type)){
				req.setRtblimgurl(file);
				req.setRtblno(code);
			}else if("5".equals(type)){//身份证正面
				req.setPositive(file);
			}else if("6".equals(type)){//身份证反面
				req.setOpposite(file);
			}else{
				rs.setCode("1");
				rs.setError("数据不全");
				return rs;
			}
			rs = systemMemberInfoService.uptMemberPic(req);
		}
		return rs;
	}
	
	@RequestMapping("pushNc")
	@ResponseBody
	public Result pushNc(String id){
		Result result = Result.getErrorResult();
		try {
			result = systemMemberInfoService.pushNc(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
