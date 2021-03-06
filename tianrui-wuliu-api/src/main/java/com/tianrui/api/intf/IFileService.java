package com.tianrui.api.intf;


import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.tianrui.api.req.front.system.FileUploadReq;
import com.tianrui.common.vo.Result;

/**
 * 
 * @类描述：附件业务接口类
 * @创建人：tank
 * @创建时间：2016年1月17日下午5:13:17
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午5:13:17
 * @修改备注：
 *
 */
public interface IFileService  {
	/** base64 上传图片*/
	Result uploadImg(FileUploadReq fileUploadReq)throws Exception;
	/** 处理运单异常图片（直接上传Base64） 11.02*/
	Result uploadBillImg(FileUploadReq fileUploadReq)throws Exception;
	
	/** byte[]上传图片*/ 
	Result uploadByteImg(MultipartFile file,String uid) throws Exception;
	
	/** Banner  base64 上传图片*/
	Result uploadBannerImg(FileUploadReq fileUploadReq)throws Exception;
}
