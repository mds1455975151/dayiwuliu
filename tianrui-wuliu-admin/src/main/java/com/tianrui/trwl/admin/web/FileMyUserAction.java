package com.tianrui.trwl.admin.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IOrgMemberService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.admin.OrgMemberReq;
import com.tianrui.api.req.front.member.MemberMassageReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.member.MemberUpdateReq;
import com.tianrui.api.resp.admin.OrgMemberResp;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.member.SystemMemberResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;
import com.tianrui.trwl.admin.util.WebManager;

@Controller
@RequestMapping("/file/filemyuser")
public class FileMyUserAction {
	@Autowired
	private IOrgMemberService orgMemberService;
	@Autowired
	private ISystemMemberService systemMemberService;
	/**
	 * 
	 * @描述:跳转到我的会员页面
	 * @return
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年6月6日下午5:20:13
	 */
	@RequestMapping("/filemyuser")
	public ModelAndView filemyuser(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/fileMyUsers/file_myuser");
		return view;
	}
	/**
	 * 
	 * @描述:添加我的会员
	 * @param cellPhone
	 * @param massage
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月6日下午5:21:02
	 */
	@RequestMapping("/saveOrgMember")
	@ResponseBody
	public Result saveOrgMember(
			String cellPhone,
			String massage,
			String memberName,
			HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
	    Users user = SessionManager.getSessionMember(request);
		OrgMemberReq req = new OrgMemberReq();
		req.setId(UUIDUtil.getId());
		req.setOrganizationid(user.getOrgid());
		Date data = new Date();
		req.setCreator(user.getAccount());
		req.setCreatetime(data.getTime());
		req.setMembertel(cellPhone);
		req.setMemberdesc(massage);
		req.setMembername(memberName);
		MemberMassageReq find = new MemberMassageReq();
		find.setCellphone(cellPhone);
		List<SystemMemberResp> list = systemMemberService.findMemberByMassage(find);
		
		short a = 1;
		//个人认证状态
		short userp = list.get(0).getUserpercheck();
		//司机认证状态
		short driverp = list.get(0).getDriverpercheck();
		//企业认证状态
		short comp = list.get(0).getCompanypercheck();
		
		if(list.size()!=1){
			rs.setCode("1");
			rs.setError("无效的用户");
			return rs;
		}else if("0".equals(list.get(0).getStatus())){
			rs.setCode("1");
			rs.setError("用户已被禁用");
			return rs;
		}else if(!(userp == 1 || driverp == 1 || comp == 1)){
			rs.setCode("1");
			rs.setError("用户暂未认证");
			return rs;
		}else if(StringUtils.isNotBlank(list.get(0).getOrgid())){
			rs.setCode("1");
			rs.setError("用户已绑定");
			return rs;
		}else if(!orgMemberService.saveEntity(req)){
			rs.setCode("1");
			rs.setError("添加失败");
			return rs;
		}
		MemberUpdateReq up = new MemberUpdateReq();
		up.setId(list.get(0).getId());
		up.setOrgid(user.getOrgid());
		if(!systemMemberService.updateMember(up)){
			rs.setCode("1");
			rs.setError("添加失败");
			return rs;
		};
		//清除前台用户登录信息
		WebManager.removeWebSession(list.get(0).getId(),list.get(0).getCellphone());
		return rs;
	}
	/**
	 * 
	 * @描述:查询我的会员
	 * @param req
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月7日上午9:07:50
	 */
	@RequestMapping("/findOrgMember")
	@ResponseBody
	public Result findOrgMember(OrgMemberReq req,
			HttpServletRequest request
			) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setOrganizationid(user.getOrgid());
		PageResp<OrgMemberResp> resp = orgMemberService.findByEntity(req);
		rs.setData(resp);
		return rs;
	}
	/**
	 * 
	 * @描述:修改关系
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月7日上午10:37:54
	 */
	@RequestMapping("/updateOrgMember")
	@ResponseBody
	public Result updateOrgMember(
			OrgMemberReq req,
			HttpServletRequest request
			) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setCreator(user.getAccount());
		if(!orgMemberService.updateEntity(req)){
			rs.setCode("1");
			rs.setError("修改失败");
		}
		return rs;
	}
	/**
	 * 
	 * @描述:删除会员组织关系
	 * @param id
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月7日下午2:30:28
	 */
	@RequestMapping("/deleteOrgMember")
	@ResponseBody
	public Result deleteOrgMember(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		OrgMemberResp om = orgMemberService.findById(id);
		if(!orgMemberService.deleteEntity(id)){
			rs.setCode("1");
			rs.setError("删除失败");
		}
		MemberMassageReq find = new MemberMassageReq();
		find.setCellphone(om.getMembertel());
		List<SystemMemberResp> list = systemMemberService.findMemberByMassage(find);
		MemberUpdateReq up = new MemberUpdateReq();
		up.setId(list.get(0).getId());
		up.setOrgid("");
		systemMemberService.updateMember(up);
		//清除前台用户登录信息
		WebManager.removeWebSession(list.get(0).getId(),list.get(0).getCellphone());
		return rs;
	} 
	/** 查询账号*/
	@RequestMapping("/findPhone")
	@ResponseBody
	public Result findPhone(String phone) throws Exception{
		Result rs = Result.getSuccessResult();
		
		MemberReq req = new MemberReq();
		req.setTelnum(phone);
		MemberResp resp = systemMemberService.findMemberByTelnum(req);
		if(resp==null){
			rs.setCode("1");
			rs.setError("无效的用户");
			return rs;
		}
		
		OrgMemberReq om = new OrgMemberReq();
		om.setMembertel(phone);
		List<OrgMemberResp> list = orgMemberService.findlist(om);
		if(list.size()==1){
			rs.setCode("1");
			rs.setError("用户已绑定");
			return rs;
		}
		
		short a = 1;
		//个人认证状态
		short userp = resp.getUserpercheck();
		//司机认证状态
		short driverp = resp.getDriverpercheck();
		//企业认证状态
		short comp = resp.getCompanypercheck();
		
		if("0".equals(resp.getStatus())){
			rs.setCode("1");
			rs.setError("用户已被禁用");
			return rs;
		}else if(!(userp == a || driverp == a || comp == a)){
			rs.setCode("1");
			rs.setError("用户暂未认证");
			return rs;
		}else{
			rs.setData(resp);
		}
		return rs;
	}
}
