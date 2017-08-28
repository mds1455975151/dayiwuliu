package com.tianrui.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
import com.tianrui.service.bean.FileReg;
import com.tianrui.service.mongo.FileRegDao;

@Service
public class FileUploadService implements IFileService{
	public Logger logger =LoggerFactory.getLogger(FileUploadService.class);
	
	@Autowired
	private GridFsTemplate gridFsTemplate;
	@Autowired
	private FileRegDao fileRegDao;


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
					byte[] out = Base64.decodeFast(fileUploadReq.getImgStr().substring(22));
					
					InputStream input = new ByteArrayInputStream(out);
					gridFsTemplate.store(input, imgURI);
					String imgURL = Constant.FILE_URL_PRE+imgURI;
					result.setCode("000000");
					result.setData(imgURL);
					
					//TODO 上传文件路径 时间
					saveFileReg(imgURL,imgURI,fileUploadReq.getuId());
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
	
	public Result uploadByteImg(MultipartFile file,String uId) throws Exception {
		Result result=Result.getSuccessResult();
		// 判断文件是否为空  
        if (!file.isEmpty()) {  
        	String sd = file.getOriginalFilename();
        	String fix = sd.substring(sd.lastIndexOf(".")+1);
        	//限制文件格式
        	if("jpg".equals(fix)||"png".equals(fix)||"JPG".equals(fix)||"".equals(fix)||"PNG".equals(fix)||"JPEG".equals(fix)||"".equals(fix)||"jpeg".equals(fix)){
        		try {  
        			InputStream input = file.getInputStream();
        			String imgURI = UUIDUtil.getId()+"."+fix;
        			gridFsTemplate.store(input, imgURI);
        			String imgURL = Constant.FILE_URL_PRE+imgURI;
        			result.setData(imgURL);
        			
        			//TODO 上传文件路径 时间
					saveFileReg(imgURL,imgURI,uId);
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
	
	
	
	private void saveFileReg(String url,String fileName,String uId){
		FileReg fileReg=new FileReg();
		fileReg.setId(UUIDUtil.getId());
		fileReg.setTimeStamp(System.currentTimeMillis());
		fileReg.setFileName(fileName);
		fileReg.setUrl(url);
		fileReg.setUserId(uId);
		fileReg.setIsDelFlag((short)0);
		fileRegDao.save(fileReg);
	}
	
}
