package com.tianrui.trwl.admin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IOrgSignerService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.admin.OrgMemberReq;
import com.tianrui.api.req.admin.OrgSignerFindReq;
import com.tianrui.api.req.admin.OrgSignerReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.resp.admin.OrgMemberResp;
import com.tianrui.api.resp.admin.OrgSignerResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;
/** 我的收货员*/
@RequestMapping("/file/fileOrgSigner")
@Controller
public class OrgSignerAction {

	@Autowired
	IOrgSignerService  orgSignerService;
	@Autowired
	private ISystemMemberService systemMemberService;
	
	@RequestMapping("page")
	public ModelAndView page() throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/fileMyUsers/file_orgSigner");
		return view;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(OrgSignerFindReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<OrgSignerResp> page = orgSignerService.select(req);
		rs.setData(page);
		return rs;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Result save(OrgSignerReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setOrgid(user.getOrgid());
		req.setCreater(user.getAccount());
		rs = orgSignerService.insert(req);
		return rs;
	}
	
	@RequestMapping("upt")
	@ResponseBody
	public Result upt(OrgSignerReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setUpter(user.getAccount());
		rs = orgSignerService.update(req);
		return rs;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = orgSignerService.delete(id);
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
		List<OrgSignerResp> list = orgSignerService.findCellphonr(phone);
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
