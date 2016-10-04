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
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.ImageUtils;
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
        	String sd = file.getOriginalFilename();
        	String fix = sd.substring(sd.lastIndexOf(".")+1);
        	System.out.println(fix+"="+fix.indexOf("|jpg|png|JPG|PNG|"));
        	if("jpg".equals(fix)||"png".equals(fix)||"JPG".equals(fix)||"".equals(fix)||"PNG".equals(fix)){
        		try {  
        			// 文件保存路径  
        			String filePath = request.getSession().getServletContext().getRealPath("/") + "resources/js/temp/"  
        					+ file.getOriginalFilename();  
        			// 转存文件  
        			file.transferTo(new File(filePath));
        			byte[] b = ImageUtils.getBytesDelFile(filePath);
        			rs = iFileService.uploadByteImg(b);
        		} catch (Exception e) {  
        			e.printStackTrace();  
        		}  
        	}else{
        		rs.setCode("1");
        		rs.setError("图片格式有误");
        	}
        }  
		return rs;
	}
}
