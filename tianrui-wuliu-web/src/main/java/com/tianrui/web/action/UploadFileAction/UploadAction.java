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
	
	/** 文件上传*/
	@RequestMapping(value="add",method=RequestMethod.POST )
	@ResponseBody
	public Result upload(MultipartFile file,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		// 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
                // 文件保存路径  
                String filePath = request.getSession().getServletContext().getRealPath("/") + "resources/js/temp/"  
                        + file.getOriginalFilename();  
                System.out.println("path="+filePath);
                // 转存文件  
                file.transferTo(new File(filePath));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
		return rs;
	}
}
