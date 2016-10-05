package com.tianrui.web.action.UploadFileAction;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IFileService;
import com.tianrui.common.vo.Result;
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
	public Result upload(MultipartFile file,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = iFileService.uploadByteImg(file, request);
		return rs;
	}
}
