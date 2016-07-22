package com.tianrui.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.Base64;
import com.tianrui.api.intf.IFileService;
import com.tianrui.api.req.front.system.FileUploadReq;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;

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
}
