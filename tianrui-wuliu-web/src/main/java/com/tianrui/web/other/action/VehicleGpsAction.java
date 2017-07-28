package com.tianrui.web.other.action;

import java.util.ArrayList;
import java.util.List;

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
import com.tianrui.api.req.front.api.APIVehicleGpsReq;
import com.tianrui.api.resp.front.api.APIVehicleGpsResp;
import com.tianrui.common.constants.ApiErrorCode;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.Md5Utils;
import com.tianrui.common.vo.Result;


/**
 * 车辆位置第三方接口
 * 1 使用方 安联
 * @author lixp 2017年7月27日 15:17:08
 *
 */
@Controller
@RequestMapping("/otherApi/vehicle")
public class VehicleGpsAction {

	private Logger logger = LoggerFactory.getLogger(VehicleGpsAction.class);
	
	@Autowired
	IApiPostionService apiPostionService;
	
	//获取承运计划列表
	@RequestMapping(value="/queryTrack",method=RequestMethod.POST)
	@ResponseBody
	public Result queryTrack(@RequestBody APIVehicleGpsReq req) throws Exception{
		Result rs =new Result();
		//交验
		// 1:md5验证防篡改 (使用原始md5值替换并生成md5串)
		// 2:key验证来源(使用)
		// 3:参数不能为空
		ApiErrorCode error =validParam(req);
		if( StringUtils.equals(error.getCode(),ApiErrorCode.API_SYSTEM_SUCCESS.getCode()) ){
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			rs.setData(getData());
		}else{
			rs.setCode(error.getCode());
			rs.setError(error.getMsg());
		}
		
		logger.info("queryTrack 参数:{},返回值:{}",JSON.toJSON(req),rs.getCode());
		return rs;
	}
	
	private ApiErrorCode validParam(APIVehicleGpsReq req){
		ApiErrorCode errorCode = ApiErrorCode.API_SYSTEM_ERROR;
		if( req!=null ){
			//参数为空的验证
			if(  StringUtils.isNotBlank(req.getToken()) &&  StringUtils.isNotBlank(req.getBeginTime()) &&  StringUtils.isNotBlank(req.getVehicleNO()) ){
				//车牌号格式
				if( !checkVehciNoParam(req.getToken()) ){
					errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR7;
				//用户私钥	
				}else if(!checkTokenParam(req.getToken(),req.getTime())){
					errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR8;
				//开始时间 结束时间
				}else if(!checkTimeParam(req.getBeginTime(),req.getEndTime())){
					errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR9;
				}else{
					//验证通过
					errorCode=ApiErrorCode.API_SYSTEM_SUCCESS;
				}
			}
		}
		return errorCode;
	}
	
	//车牌号验证
	private boolean checkVehciNoParam(String vehicleNo){
		boolean rs =false;
		if( StringUtils.isNotBlank(vehicleNo)  && vehicleNo.length()==7 ){
			rs=true;
		}
		return rs;
	}
	//用户私钥验证
	private boolean checkTokenParam(String token,String time){
		boolean rs =false;
		if( StringUtils.isNotBlank(token)  && StringUtils.isNotBlank(time)  ){
			String key="anlian!@2017#";
			String md5 =Md5Utils.MD5(key+time);
			if( md5.equals(token) ){
				rs=true;
			}
		}
		return rs;
	}
	//开始时间结束时间验证.
	private boolean checkTimeParam(String beginTime,String endTime){
		boolean rs =false;
		if( StringUtils.isNotBlank(beginTime)   ){
			rs=true;
		}
		return rs;
	}
	
	
	private List<APIVehicleGpsResp>  getData(){
		List<APIVehicleGpsResp> list =new ArrayList<APIVehicleGpsResp>();
		
		APIVehicleGpsResp item1 =new APIVehicleGpsResp();
		item1.setLat("30.77665");
		item1.setLon("116.856165");;
		item1.setUtc("1496826230000");
		list.add(item1);
		
		APIVehicleGpsResp item2 =new APIVehicleGpsResp();
		item1.setLat("30.78665");
		item1.setLon("116.866165");
		item1.setUtc("1496826330000");
		list.add(item2);
		
		return list;
	}
	
}
	