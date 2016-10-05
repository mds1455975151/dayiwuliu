package com.tianrui.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.Base64;
import com.tianrui.api.intf.IFileService;
import com.tianrui.api.req.front.system.FileUploadReq;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.util.ImageUtils;

@Service
public class FileUploadService implements IFileService{
	public Logger logger =LoggerFactory.getLogger(FileUploadService.class);
	
	@Autowired
	private GridFsTemplate gridFsTemplate;


	//data:image/png;base64,
	/**
	 * 保存图片数据到mongo
	 * TODO 图片的上传记录信息
	 */
	public Result uploadImg(FileUploadReq fileUploadReq) throws Exception {
		Result result=Result.getSuccessResult();
		if( fileUploadReq !=null && StringUtils.isNotBlank(fileUploadReq.getImgStr()) ){
			//验证图片格式
			if( fileUploadReq.getImgStr().startsWith("data:image/png;base64,")  ){
				try {
					String imgURI = UUIDUtil.getId()+".png";
					fileUploadReq.getImgStr();
					byte[] out = Base64.decodeFast(fileUploadReq.getImgStr().substring(22));
					
					InputStream input = new ByteArrayInputStream(out);
					gridFsTemplate.store(input, imgURI);
					String imgURL = Constant.FILE_URL_PRE+imgURI;
					result.setData(imgURL);
					
				} catch (Exception e) {
					logger.error("{}",e.getMessage(),e);
					result =new Result("error","上传图片服务异常" );
				}
			}else{
				result =new Result("error","上传图片格式有问题" );
			}
		}else{
			result =new Result("error","上传图片为空" );
		}
		logger.info("图片上传结束！ result:{}",JSON.toJSON(result));
		return result;
	}
	
	public Result uploadByteImg(MultipartFile file,HttpServletRequest request) throws Exception {
		Result result=Result.getSuccessResult();
		// 判断文件是否为空  
        if (!file.isEmpty()) {  
        	String sd = file.getOriginalFilename();
        	String fix = sd.substring(sd.lastIndexOf(".")+1);
        	//限制文件格式
        	if("jpg".equals(fix)||"png".equals(fix)||"JPG".equals(fix)||"".equals(fix)||"PNG".equals(fix)){
        		try {  
        			// 文件保存路径  
        			String filePath = request.getSession().getServletContext().getRealPath("/") + "resources/js/temp/"  
        					+ file.getOriginalFilename();  
        			// 本地保存文件  
        			file.transferTo(new File(filePath));
        			byte[] out = ImageUtils.getBytesDelFile(filePath);
        			
        			String imgURI = UUIDUtil.getId()+".png";
        			InputStream input = new ByteArrayInputStream(out);
        			gridFsTemplate.store(input, imgURI);
        			String imgURL = Constant.FILE_URL_PRE+imgURI;
        			result.setData(imgURL);
        		} catch (Exception e) {  
        			e.printStackTrace();  
        		}  
        	}else{
        		result.setCode("1");
        		result.setError("图片格式有误");
        	}
        }  
		logger.info("图片上传结束！ result:{}",JSON.toJSON(result));
		return result;
	}
}
