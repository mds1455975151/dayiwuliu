package com.tianrui.web.action.UploadFileAction;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
		rs = iFileService.uploadByteImg(file);
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
	
	/** 文件上传
	 * @throws Exception */
	@RequestMapping(value="add1",method=RequestMethod.POST )
	@ResponseBody
	public Result uploada(MultipartFile file,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
    	//限制文件格式
		try {  
			InputStream input = file.getInputStream();
			//input 转 byte[]
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = input.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        byte[] in2b = swapStream.toByteArray();  
	        getFile(in2b,"E:\\","testone.txt");
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		return rs;
	}
	
	/** 
     * 根据byte数组，生成文件 
     */  
    public void getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    }  
	
}
