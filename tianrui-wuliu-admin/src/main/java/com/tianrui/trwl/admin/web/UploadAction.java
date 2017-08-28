package com.tianrui.trwl.admin.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IFileService;
import com.tianrui.api.req.front.system.FileUploadReq;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;
@Controller
@RequestMapping("upload")
public class UploadAction {
	
	@Autowired
	protected IFileService iFileService;

	@RequestMapping("index")
	public ModelAndView index(){
		return new ModelAndView("upload/upload");
	}
	
	/** 文件上传
	 * @throws Exception */
	@RequestMapping(value="add",method=RequestMethod.POST )
	@ResponseBody
	public Result upload(HttpServletRequest request,MultipartFile file) throws Exception{
		Users user =SessionManager.getSessionMember(request);
		Result rs = Result.getSuccessResult();
		rs = iFileService.uploadByteImg(file,user.getAccount());
		return rs;
	}
	/** 文件上传
	 * @throws Exception */
	@RequestMapping(value="baes64Add",method=RequestMethod.POST )
	@ResponseBody
	public Result baes64Add(FileUploadReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = iFileService.uploadImg(req);
		return rs;
	}
}
