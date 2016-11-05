package com.tianrui.web.app.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IFileService;
import com.tianrui.api.req.front.system.FileUploadReq;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/upload")
public class AppUploadfileAction {

	@Autowired
	private IFileService iFileService;
	/**
	 * 
	 * @描述:app单独上传文件 Base64
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年8月22日上午11:12:44
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ApiParamRawType(FileUploadReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult save(AppParam<FileUploadReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		Head head = appParam.getHead();
		FileUploadReq freq = appParam.getBody();
		freq.setuId(head.getId());
		rs = iFileService.uploadImg(freq);
		return AppResult.valueOf(rs);
	}
}
