package com.tianrui.trwl.admin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IFilePositionService;
import com.tianrui.api.admin.intf.IFileRouteService;
import com.tianrui.api.intf.IOrgSignerService;
import com.tianrui.api.req.admin.FilePositionReq;
import com.tianrui.api.req.admin.FileRouteReq;
import com.tianrui.api.req.admin.FileRouteSaveReq;
import com.tianrui.api.req.admin.FileRouteUpdateReq;
import com.tianrui.api.req.admin.OrgSignerFindReq;
import com.tianrui.api.resp.admin.FilePositionResp;
import com.tianrui.api.resp.admin.FileRouteResp;
import com.tianrui.api.resp.admin.OrgSignerResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;
/**
  * @ClassName: FilePositionAction 
  * @Description: 位置档案
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年5月25日 上午11:08:56 
  *
 */
@Controller
@RequestMapping("/file/fileroute")
public class FileRouteAction {
	
	@Autowired
	IFileRouteService fileRouteService;
	@Autowired
	IFilePositionService filePositionService;
	@Autowired
	IOrgSignerService  orgSignerService;
	
	//主页面
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request) throws Exception{
		ModelAndView model =new ModelAndView("/file/route/main");
		//获取位置下拉框
		Users user=SessionManager.getSessionMember(request);
		FilePositionReq req = new FilePositionReq();
		req.setCurrOrgId(user.getOrgid());
		List<FilePositionResp> positionList =filePositionService.findWithCondition(req);
		
		OrgSignerFindReq om = new OrgSignerFindReq();
		om.setOrgid(user.getOrgid());
		List<OrgSignerResp> list = orgSignerService.findlist(om);
		
		model.addObject("signer", list);
		model.addObject("positionList", positionList);
		return model;
	}
	
	
	//分页数据获取 包含查询
	@RequestMapping("/page")
	@ResponseBody
	public Result page(@RequestParam(defaultValue="1",required=true)int pageNo,
			@RequestParam(defaultValue="",required=false)String name,
			@RequestParam(defaultValue="",required=false)String status,
			HttpServletRequest request) throws Exception{
		//返回结果
		Result rs =Result.getSuccessResult();
		//拼装参数
		FileRouteReq req =new  FileRouteReq();
		req.setPageNo(pageNo);
		req.setRoutename(name);
		req.setStatus(status);
		//当前登录用户  获取组个id
		Users user=SessionManager.getSessionMember(request);
		req.setCurrOrgId(user.getOrgid());
		PaginationVO<FileRouteResp> page =fileRouteService.findAllPage(req);
		rs.setData(page);
		return rs;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Result save(FileRouteSaveReq req,HttpServletRequest request) throws Exception{
		//当前登录用户  获取组个id
		Users user=SessionManager.getSessionMember(request);
		req.setCurrUser(user.getAccount());
		req.setCurrOrgId(user.getOrgid());
		req.setCurrOrgName(user.getOrgname());
		
		Result rs =fileRouteService.saveFileRoute(req);
		return rs;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(FileRouteUpdateReq req,HttpServletRequest request) throws Exception{
		//获取当前用户的
		Users user=SessionManager.getSessionMember(request);
		req.setCurrUser(user.getAccount());
		req.setCurrOrgId(user.getOrgid());
		Result rs =fileRouteService.updateFileRoute(req);
		return rs;
	}
	
	
	@RequestMapping("/disable")
	@ResponseBody
	public Result disable(FileRouteReq req,HttpServletRequest request) throws Exception{
		//获取当前用户的
		Users user=SessionManager.getSessionMember(request);
		req.setCurrUser(user.getAccount());
		req.setCurrOrgId(user.getOrgid());
		Result rs =fileRouteService.delFileRoute(req);
		return rs;
	}
	@RequestMapping("/detail")
	@ResponseBody
	public Result detail(FileRouteReq req,HttpServletRequest request) throws Exception{
		//获取当前用户的
		Users user=SessionManager.getSessionMember(request);
		req.setCurrUser(user.getAccount());
		req.setCurrOrgId(user.getOrgid());
		Result rs =fileRouteService.detailFileRoute(req);
		return rs;
	}
	//批量删除
	@RequestMapping("/deleteRoute")
	@ResponseBody
	public Result deleteRoute(String id)throws Exception{
		Result rs = Result.getSuccessResult();
		String[] idsrt = id.split(",");
		for(String ids : idsrt){
			fileRouteService.delete(ids);
		}
		return rs;
	}
	//批量禁用
	@RequestMapping("/updateStatus")
	@ResponseBody
	public Result updateStatus(String id,HttpServletRequest request)throws Exception{
		Result rs = Result.getSuccessResult();
		Users user=SessionManager.getSessionMember(request);
		String[] idsrt = id.split(",");
		for(String ids : idsrt){
			FileRouteReq req = new FileRouteReq();
			req.setCurrUser(user.getAccount());
			req.setCurrOrgId(user.getOrgid());
			req.setId(ids);
			req.setStatus("0");//0-禁用，1-启用
			fileRouteService.delFileRoute(req);
		}
		return rs;
	}
	
}
