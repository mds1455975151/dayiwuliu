package com.tianrui.web.action.member;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IFileService;
import com.tianrui.api.intf.ISystemMemberInfoRecordService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.front.member.MemberInfoReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.member.MemberUpdateReq;
import com.tianrui.api.req.front.system.FileUploadReq;
import com.tianrui.api.resp.front.member.MemberInfoMassageResp;
import com.tianrui.api.resp.front.member.MemberInfoRecordResp;
import com.tianrui.api.resp.front.member.MemberInfoResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.web.util.SessionManager;

/**
 * 
 * @类描述：平台登录会员模块web响应
 * @创建人：tank
 * @创建时间：2016年4月22日上午11:12:02
 *
 * @修改人：tank
 * @修改时间：2016年4月22日上午11:12:02
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/trwuliu/Member")
public class MemberAction{

	public static Logger logger =LoggerFactory.getLogger(MemberAction.class);
	@Autowired
	protected IFileService iFileService;
	@Autowired
	protected ISystemMemberInfoRecordService systemMemberInfoRecordService;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	private CacheClient cacheClient;
	/**
	 * 
	 * @描述:页面跳转
	 * @return
	 * @throws Exception 
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年5月9日下午6:19:18
	 */
	@RequestMapping("/personalDataPage")
	public ModelAndView personaldata(HttpServletRequest request) throws Exception{
		MemberVo vo = SessionManager.getSessionMember(request);
		MemberReq req = new MemberReq();
		req.setTelnum(vo.getCellphone());
		MemberResp resp = systemMemberService.findMemberByTelnum(req);
		ModelAndView view = new ModelAndView();
		view.addObject("member", resp);
		view.setViewName("member/personaldata/personalDataPage");
		return view;
	}
	/**
	 * 
	 * @描述:会员认证页面跳转
	 * @return
	 * @throws Exception 
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月23日下午4:31:10
	 */
	@RequestMapping("/authenPage")
	public ModelAndView authenPage(HttpServletRequest request) throws Exception{
		SessionManager.flushMember(request);
		return new ModelAndView("/member/authentication/authenPage");
	}
	/**
	 * 
	 * @描述:页面跳转
	 * @return
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年5月4日下午4:43:00
	 */
	@RequestMapping("/authenListPage")
	public ModelAndView authenListPage(){
		return new ModelAndView("/member/authentication/authenListPage");
	}
	
	/**
	 * 
	 * @描述:审核明细页面
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年5月3日下午6:01:44
	 */
	@RequestMapping("/authenDetailPage")
	public ModelAndView authenDetailPage() throws IOException{
		return new ModelAndView("/member/authentication/authenDetailPage");
	}
	
	/**
	 * 
	 * @描述:审核状态页面
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年5月3日下午6:00:30
	 */
	@RequestMapping("/authenStatePage")
	public ModelAndView authenStatePage() throws IOException{
		return new ModelAndView("/member/authentication/authenStatePage");
	}
	
	/**
	 * 
	 * @描述:认证失败页面
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年4月27日上午10:37:03
	 */
	@RequestMapping("/authenFailPage")
	public ModelAndView authenFailPage() throws IOException{
		return new ModelAndView("/member/authentication/authenFailPage");
	}
	/**
	 * 
	 * @描述:认证成功页面
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年4月27日上午10:37:20
	 */
	@RequestMapping("/authenSuccessPage")
	public ModelAndView authenSuccessPage() throws IOException{
		return new ModelAndView("/member/authentication/authenSuccessPage");
	}
	/**
	 * 
	 * @描述:个人认证
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年4月26日下午6:13:21
	 */
	@RequestMapping("/perAuthenPage")
	public ModelAndView perAuthenPage() throws IOException{
		return new ModelAndView("/member/authentication/perAuthenPage");
	}
	/**
	 * @描述:会员资料信息页面
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月30日上午10:07:28
	 */
	@RequestMapping("/detailsPage")
	public ModelAndView detailsPage(HttpServletRequest request) throws IOException{
		ModelAndView model =new ModelAndView("/member/detailsPage");
		model.addObject("user", SessionManager.getSessionMember(request));
		return model;
	}
	/**
	 * 企业认证
	 * @描述:
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年4月26日下午6:14:16
	 */
	@RequestMapping("/corpAuthenPage")
	public ModelAndView corpAuthenPage() throws IOException{
		return new ModelAndView("/member/authentication/corpAuthenPage");
	}
	
	/**
	 * 
	 * @描述:会员查看、修改页面跳转
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月23日下午4:31:10
	 */
	@RequestMapping("/memberPage")
	public ModelAndView memberPage() throws IOException{
		return new ModelAndView("/member/memberPage");
	}
	
	/**
	 * 
	 * @描述:个人会员认证
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月23日下午4:31:10
	 */
	@RequestMapping(value = "/personalAuthentication", method = RequestMethod.POST)
	@ResponseBody
	public Result personalAuthentication(@RequestParam(defaultValue = "")String userName,
			@RequestParam(defaultValue = "")String id,
			@RequestParam(defaultValue = "")String identityCard,
			@RequestParam(defaultValue = "")String telphone,
			@RequestParam(defaultValue = "")String licenseType,
			@RequestParam(defaultValue = "")String type,//2-驾驶证；1-身份证
			String rtblno, //道路运输许可证号 
			MultipartFile file,//2-驾驶证；1-身份证 图片留
			MultipartFile rtblimg,//道路运输许可证号  图片
			HttpServletRequest request
			) throws Exception{
		
		Result rs =Result.getSuccessResult();
		if(userName==null||"".equals(userName)||
				id==null||"".equals(id)||
				identityCard==null||"".equals(identityCard)||
				telphone==null||"".equals(telphone)||
				file==null
				){
			rs.setCode("1");
			rs.setError("数据不能为空");
			return rs;
		}
		try {
			MemberInfoReq req = new MemberInfoReq();
			//保存道路许可证图片
			if( rtblimg !=null ){
				rs = iFileService.uploadByteImg(rtblimg);
				if("000000".equals(rs.getCode())){
					req.setRtblimgurl(rs.getData().toString());
					req.setRtblno(rtblno);
				}
			}
			//保存图片
			rs = iFileService.uploadByteImg(file);
			//图片保存成功，进行下一步操作
			if("000000".equals(rs.getCode())){
				req.setUserName(userName);
				req.setMemberId(id);
				req.setIdentityCard(identityCard);
				req.setTelphone(telphone);
				if("1".equals(type)){//2-驾驶证；1-身份证
					req.setIdcardsImagePath(rs.getData().toString());
					rs= systemMemberInfoRecordService.personalAuthentication(req);
				}else if("2".equals(type)){
					req.setLicenseType(licenseType);
					req.setDriveImagePath(rs.getData().toString());
					rs= systemMemberInfoRecordService.driverAuthentication(req);
				}
				//更新session
				SessionManager.flushMember(request);
			}else{
				rs.setCode("1");
				rs.setError("图片保存失败");
			}
		
		} catch (Exception e) {
			logger.info("personalAuthentication错误信息：{}",e.getMessage());
			rs.setCode("1");
			rs.setError("操作失败");
			return rs;
		}
		return rs;
	}
	/**
	 * 
	 * @描述:企业会员认证
	 * @return
	 * @throws Exception 
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月23日下午4:31:10
	 */
	@RequestMapping(value = "/enterpriseAuthentication", method = RequestMethod.POST)
	@ResponseBody
	public Result enterpriseAuthentication(@RequestParam(defaultValue = "")String id,
			@RequestParam(defaultValue = "")String companyName,
			@RequestParam(defaultValue = "")String companyAddress,
			@RequestParam(defaultValue = "")String companyContact,
			@RequestParam(defaultValue = "")String contactTel,
			@RequestParam(defaultValue = "")String companycode,
			String rtblno, //道路运输许可证号 
			MultipartFile file,//身份证照片
			MultipartFile rtblimg,//道路运输许可证号  图片
			HttpServletRequest request
			) throws Exception{
		Result rs =Result.getSuccessResult();
		if(id==null||"".equals(id)||
				companyName==null||"".equals(companyName)||
				companyAddress==null||"".equals(companyAddress)||
				companyContact==null||"".equals(companyContact)||
				contactTel==null||"".equals(contactTel)||
				file==null
				){
			rs.setCode("1");
			rs.setError("请求数据不能为空");
			return rs;
		}
		try {
			MemberInfoReq req = new MemberInfoReq();
			//保存道路许可证图片
			if( rtblimg !=null ){
				rs = iFileService.uploadByteImg(rtblimg);
				if("000000".equals(rs.getCode())){
					req.setRtblimgurl(rs.getData().toString());
					req.setRtblno(rtblno);
				}
			}
			
			rs = iFileService.uploadByteImg(file);
			if("000000".equals(rs.getCode())){
				req.setMemberId(id);
				req.setCompanyName(companyName);
				req.setCompanyAddress(companyAddress);
				req.setCompanyContact(companyContact);
				req.setContactTel(contactTel);
				req.setCompanycode(companycode);
				req.setLicenseImagePath(rs.getData().toString());
				rs = systemMemberInfoRecordService.enterpriseAuthentication(req);
				//更新session
				SessionManager.flushMember(request);
			}
		} catch (Exception e) {
			logger.info("enterpriseAuthentication错误信息：{}",e.getMessage());
			rs.setCode("1");
			rs.setError("操作失败");
			return rs;
		}
		return rs;
	}
	/**
	 * 
	 * @描述:会员认证信息
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 lsj
	 * @创建时间 2016年4月27日上午11:41:29
	 */
	@RequestMapping(value = "/authenticationInfoByid",method=RequestMethod.POST)
	@ResponseBody
	public Result authenticationInfoByid(@RequestParam(defaultValue = "")String id) throws Exception{
		Result rs =Result.getSuccessResult();
		if(StringUtils.isBlank(id)){
			rs.setCode("1");
			rs.setError("id不能为空");
			return rs;
		}
		try {
			MemberInfoResp resp = systemMemberService.authenticationInfoByid(id);
			rs.setData(resp);
		} catch (Exception e) {
			logger.info("authenticationInfoByid错误信息：{}",e.getMessage());
			rs.setCode("1");
			rs.setError("操作失败");
			return rs;
		}
		return rs;
		
	}
	
	/**
	 * 
	 * @描述:会员基本信息修改
	 * @return
	 * @throws Exception 
	 * @返回类型 Result -->json
	 * @创建人 tank
	 * @创建时间 2016年4月23日下午4:31:10
	 * 
	 */
	@RequestMapping( value="/updateMember", method = RequestMethod.POST)
	@ResponseBody
	public Result updateMember(
			@RequestParam(defaultValue = "") String userName,
			@RequestParam(defaultValue = "") String phone,
			@RequestParam(defaultValue = "") String loginName,
			@RequestParam(defaultValue = "") String imgstr,
			@RequestParam(defaultValue = "") String sex,
			HttpServletRequest request
			) throws Exception{
		Result rs =Result.getSuccessResult();
		if(StringUtils.isNotBlank(imgstr)){
			FileUploadReq req =new FileUploadReq();
			req.setImgStr(imgstr);
			req.setuId(SessionManager.getSessionMember(request).getId());
			rs = iFileService.uploadImg(req);
			if("000000".equals(rs.getCode())){
				MemberUpdateReq mreq = new MemberUpdateReq();
				mreq.setId(SessionManager.getSessionMember(request).getId());
				mreq.setUsername(userName);
				mreq.setSex(sex);
				mreq.setPhone(phone);
				mreq.setLoginname(loginName);
				mreq.setAvatarspath(rs.getData().toString());
				if(systemMemberService.updateMember(mreq)){
					rs.setCode("1");
					rs.setError("修改失败");
					return rs;
				}
			}
		}else{
			MemberUpdateReq mreq = new MemberUpdateReq();
			mreq.setId(SessionManager.getSessionMember(request).getId());
			mreq.setUsername(userName);
			mreq.setLoginname(loginName);
			mreq.setSex(sex);
			mreq.setPhone(phone);
			if(systemMemberService.updateMember(mreq)){
				rs.setCode("1");
				rs.setError("修改失败");
				return rs;
			}
		}
		//更新session
		SessionManager.flushMember(request);
		return rs;
	}
	
	/**
	 * 
	 * @描述:查询info列表
	 * @param memberId 
	 * @return
	 * @throws Exception 
	 * @返回类型 String
	 * @创建人 lsj
	 * @创建时间 2016年5月4日上午11:07:05
	 */
	@RequestMapping("/InfoListByMemberId")
	@ResponseBody
	public Result InfoListByMemberId(String memberId) throws Exception{
		Result rs = Result.getSuccessResult();
		if(StringUtils.isBlank(memberId)){
			rs.setCode("1");
			rs.setError("memberId不能为空");
			return rs;
		}
		try {
			MemberInfoReq req = new MemberInfoReq();
			req.setMemberId(memberId);
			MemberInfoRecordResp resp = systemMemberInfoRecordService.findByMemberId(memberId);
			rs.setData(resp);
		} catch (Exception e) {
			logger.info("错误提示:{}","操作失败，请核对信息");
			rs.setCode("1");
			rs.setError("操作失败，请核对信息");
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述:会员个人基本信息
	 * @param id member的id(),与memberInfoById区分
	 * @return 
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 lsj
	 * @创建时间 2016年4月27日上午11:34:04
	 */
	@RequestMapping(value="/memberInfoByid",method=RequestMethod.POST)
	@ResponseBody
	public Result memberInfoByid(@RequestParam(defaultValue = "")String id) throws Exception{
		Result rs =Result.getSuccessResult();
		if(id==null||"".equals(id)){
			rs.setCode("1");
			rs.setError("id不能为空");
			return rs;
		}
		try {
			MemberResp member = systemMemberService.findById(id);
			rs.setData(member);
		} catch (Exception e) {
			logger.info("memberInfoByid错误信息：{}","操作失败");
			rs.setCode("1");
			rs.setError("操作失败");
			return rs;
		}
		return rs;
	}
	/**
	 * 
	 * @描述:查询认证信息/查看认证失败原因
	 * @param id -memberid
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月16日上午9:25:46
	 */
	@RequestMapping("memberInfoMassage")
	@ResponseBody
	public Result memberInfoMassage(@RequestParam(defaultValue = "")String id) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberInfoMassageResp resp = systemMemberService.findInfoMassageById(id);
		rs.setData(resp);
		return rs;
	}
	
	/**
	* @Title: logout
	* @Description: 会员注销登录
	* @return ModelAndView    
	* @throws IOException    
	* @author wuchl
	* @date 2016年5月19日
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) throws IOException{
		SessionManager.removeSessionMember(request);
		return new ModelAndView("redirect:/count/route");
	}
	
	@RequestMapping("/chooseRole")
	public ModelAndView chooseRole(HttpServletRequest request){
		return new ModelAndView("/member/chooseRole");
	}
	
	@RequestMapping("/bindRole")
	@ResponseBody
	public Result bindRole(HttpServletRequest request,String role){
		Result re = Result.getSuccessResult();
		if(StringUtils.isNotBlank(role)){
			SessionManager.setSessionRole(request, role);
			re.setCode("000000");
		}else{
			re.setCode("000001");
			re.setError("选择角色错误！");
		}
		return re;
	}
	/** 修改密码
	 * @throws Exception */
	@RequestMapping("/uptPassword")
	@ResponseBody
	public Result uptPassword(String passWord,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
//		MemberResp resp = systemMemberService.findById(vo.getId());
		MemberUpdateReq req = new MemberUpdateReq();
		req.setPassword(passWord);
		req.setId(vo.getId());
		if(!systemMemberService.updateMember(req)){
			rs.setCode("1");
			rs.setError("修改失败");
		}
		return rs;
	}
	/** 修改密码页面跳转
	 * @throws Exception */
	@RequestMapping("/uptPassPage")
	@ResponseBody
	public ModelAndView uptPassPage(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo = SessionManager.getSessionMember(request);
		MemberResp resp = systemMemberService.findById(vo.getId());
		view.setViewName("/member/uptPass");
		view.addObject("password", resp.getPassWord());
		return view;
	}
	
}
