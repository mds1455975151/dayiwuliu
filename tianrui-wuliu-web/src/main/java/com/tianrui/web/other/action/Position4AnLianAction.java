package com.tianrui.web.other.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.IApiPostionService;
import com.tianrui.api.req.front.api.APIPositionReq;
import com.tianrui.common.constants.ApiErrorCode;
import com.tianrui.common.utils.Md5Utils;
import com.tianrui.common.vo.Result;
@Controller
@RequestMapping("/other/anlianApi")
public class Position4AnLianAction {

	private Logger logger = LoggerFactory.getLogger(Position4AnLianAction.class);
	
	@Autowired
	IApiPostionService apiPostionService;
	
	//获取承运计划列表
	@RequestMapping(value="/uploadPosition",method=RequestMethod.POST)
	@ResponseBody
	public Result uploadPosition(@RequestBody APIPositionReq req) throws Exception{
		Result rs =new Result();
		//交验
		// 1:md5验证防篡改 (使用原始md5值替换并生成md5串)
		// 2:key验证来源(使用)
		// 3:参数不能为空
		ApiErrorCode error =validParam(req);
		if( StringUtils.equals(error.getCode(),ApiErrorCode.API_SYSTEM_SUCCESS.getCode()) ){
			apiPostionService.save(req);
		}
		rs.setCode(error.getCode());
		rs.setError(error.getMsg());
		
		logger.info("uploadPosition 参数:{},返回值:{}",JSON.toJSON(req),rs.getCode());
		return rs;
	}
	
	private ApiErrorCode validParam(APIPositionReq req){
		ApiErrorCode errorCode = ApiErrorCode.API_SYSTEM_ERROR;
		if( req!=null ){
			String md5Value =req.getMd5();
			req.setMd5("!tR2016@#%");
			String jsonStr =JSON.toJSONString(req);
			String md5Str =Md5Utils.MD5(jsonStr);
			if( StringUtils.isNotBlank(req.getMd5()) ){
				if(StringUtils.equals(md5Str,md5Value)){
					String key = req.getKey();
					String time= req.getTime();
					if( StringUtils.isNotBlank(time)){
						String md5Key =Md5Utils.MD5(time+"anlian2016");
						if( StringUtils.equals(key, md5Key)  ){
							if( StringUtils.isNotBlank(req.getLat()) ){
								if( StringUtils.isNotBlank(req.getLng()) ){
									if( StringUtils.isNotBlank(req.getTrackingid()) ){
										errorCode=ApiErrorCode.API_SYSTEM_SUCCESS;
									}else{
										errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR5;
									}
								}else{
									errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR4;
								}
							}else{
								errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR3;
							}
						}else{
							errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR2;
						}
					}else{
						errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR6;
					}
				}else{
					errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR1;
				}
			}else{
				errorCode=ApiErrorCode.API_SYSTEM_ERROR;
			}
		}
		return errorCode;
	}
	
	
}
	