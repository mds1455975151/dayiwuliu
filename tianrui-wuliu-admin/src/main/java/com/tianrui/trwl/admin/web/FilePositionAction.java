package com.tianrui.trwl.admin.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IFilePositionService;
import com.tianrui.api.req.admin.FilePositionReq;
import com.tianrui.api.resp.admin.FilePositionResp;
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
@RequestMapping("/file/fileposition")
public class FilePositionAction {
	
	@Autowired
	IFilePositionService filePositionService;
	
	//主页面
	@RequestMapping("/main")
	public ModelAndView main() throws IOException{
		ModelAndView model =new ModelAndView("/file/position/main");
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
		FilePositionReq req =new  FilePositionReq();
		req.setPageNo(pageNo);
		req.setName(name);
		req.setStatus(status);
		//当前登录用户  获取组个id
		Users user=SessionManager.getSessionMember(request);
		req.setCurrOrgId(user.getOrgid());
		PaginationVO<FilePositionResp> page =filePositionService.findAllPage(req);
		rs.setData(page);
		return rs;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Result save(FilePositionReq req,HttpServletRequest request) throws Exception{
		//当前登录用户  获取组个id
		Users user=SessionManager.getSessionMember(request);
		req.setCurrUser(user.getAccount());
		req.setCurrOrgId(user.getOrgid());
		
		Result rs =filePositionService.saveFilePosition(req);
		return rs;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(FilePositionReq req,HttpServletRequest request) throws Exception{
		//获取当前用户的
		Users user=SessionManager.getSessionMember(request);
		req.setCurrUser(user.getAccount());
		req.setCurrOrgId(user.getOrgid());
		Result rs =filePositionService.updateFilePosition(req);
		return rs;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(FilePositionReq req,HttpServletRequest request) throws Exception{
		//获取当前用户的
		Users user=SessionManager.getSessionMember(request);
		req.setCurrUser(user.getAccount());
		req.setCurrOrgId(user.getOrgid());
		Result rs =filePositionService.delFilePosition(req);
		return rs;
	}
	@RequestMapping("/detail")
	@ResponseBody
	public Result detail(FilePositionReq req,HttpServletRequest request) throws Exception{
		//获取当前用户的
		Users user=SessionManager.getSessionMember(request);
		req.setCurrUser(user.getAccount());
		req.setCurrOrgId(user.getOrgid());
		Result rs =filePositionService.detailFilePosition(req);
		return rs;
	}
	//删除地点档案
	@RequestMapping("/deletePosition")
	@ResponseBody
	public Result deletePosition(String id,HttpServletRequest request)throws Exception{
		//获取当前用户的
		Result rs = Result.getSuccessResult();
		String[] idsrt = id.split(",");
		for(String ids : idsrt){
			filePositionService.deletePosition(ids);
		}
		return rs;
	}
}
