package com.tianrui.web.other.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.IAccessLogService;
import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.api.req.admin.ZJXLVehicleReq;
import com.tianrui.api.req.front.api.VehicleGpsReq;
import com.tianrui.api.resp.admin.ZJXLVehicleResp;
import com.tianrui.api.resp.front.api.APIVehicleGpsResp;
import com.tianrui.common.constants.ApiErrorCode;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.exception.ApplicationExectpion;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.Md5Utils;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.VehicleGpsZjxl;
import com.tianrui.service.mongo.VehicleGpsZjxlDao;


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
	VehicleGpsZjxlDao vehicleGpsZjxlDao;
	@Autowired
	IAccessLogService accessLogService;
	@Autowired
	ICrossVehicleService crossVehicleService;
	
	//获取承运计划列表
	@RequestMapping(value="/queryTrack",method=RequestMethod.POST)
	@ResponseBody
	public Result queryTrack(@RequestBody VehicleGpsReq req) throws Exception{
		Result rs =new Result();
		
		ApiErrorCode error =ApiErrorCode.API_SYSTEM_ERROR;
		try{
			error=validParam(req);
			if( StringUtils.equals(error.getCode(),ApiErrorCode.API_SYSTEM_SUCCESS.getCode()) ){
				//校验车牌号是否添加中交
				error = checkZjxlVehicle(req.getVehicleNO());
				if(StringUtils.equals(error.getCode(),ApiErrorCode.API_SYSTEM_SUCCESS.getCode())){
					long begin = DateUtil.parse(req.getBeginTime(), "yyyy-MM-dd HH:mm:ss");
					long end = DateUtil.parse(req.getEndTime(), "yyyy-MM-dd HH:mm:ss");
					List<VehicleGpsZjxl> list = vehicleGpsZjxlDao.getVehicleTrack(req.getVehicleNO(), begin, end);
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					rs.setData(getData(list));
				}else{
					rs.setCode(error.getCode());
					rs.setError(error.getMsg());
				}
			}else{
				rs.setCode(error.getCode());
				rs.setError(error.getMsg());
			}
		}catch(ParseException e){
			logger.debug("queryTrack 调用失败.异常:{}",e.getMessage(),e);
			rs.setCode(ErrorCode.PARAM_ERROR.getCode());
			rs.setError(ErrorCode.PARAM_ERROR.getMsg());
		}catch(ApplicationExectpion e2){
			logger.debug("queryTrack 调用失败.异常:{}",e2.getMessage(),e2);
			rs.setCode(ErrorCode.PARAM_ERROR.getCode());
			rs.setError(e2.getMessage());
		}catch(Exception e1){
			logger.warn("queryTrack 调用失败.异常:{}",e1.getMessage(),e1);
			rs.setCode(ErrorCode.SYSTEM_ERROR.getCode());
			rs.setError(ErrorCode.SYSTEM_ERROR.getMsg());
		}
		//保存日志记录
		accessLogService.saveLog(rs, req, "/otherApi/vehicle/queryTrack");
//		accessLog(rs,req,"/otherApi/vehicle/queryTrack");
		logger.info("queryTrack 参数:{},返回值:{}",JSONObject.toJSON(req),rs.getCode());
		return rs;
	}
	
//	protected void accessLog(Result rs,VehicleGpsReq req,String url) {
//		try {
//			AccessLogReq save = new AccessLogReq();
//			if(StringUtils.isNotBlank(req.getVehicleNO())){
//				save.setVehicleNo(req.getVehicleNO());
//			}
//			if(StringUtils.isNotBlank(req.getBeginTime())){
//				save.setBeginTime(req.getBeginTime());
//			}
//			if(StringUtils.isNotBlank(req.getEndTime())){
//				save.setEndTime(req.getEndTime());
//			}
//			if(StringUtils.isNotBlank(req.getToken())){
//				save.setSystemToken(req.getToken());
//			}
//			save.setSystemUrl(url);
//			save.setRespCode(rs.getCode());
//			if(StringUtils.isNotBlank(rs.getError())){
//				save.setRespError(rs.getError());
//			}
//			if(rs.getData()!=null){
//				save.setRespData(rs.getData().toString());
//				List<APIVehicleGpsResp> list = (List<APIVehicleGpsResp>) rs.getData();
//				save.setRespTotal(String.valueOf(list.size()));
//			}
//			accessLogService.save(save);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	private ApiErrorCode validParam(VehicleGpsReq req) throws Exception{
		ApiErrorCode errorCode = ApiErrorCode.API_SYSTEM_ERROR;
		if( req!=null ){
			//参数为空的验证
			if(  StringUtils.isNotBlank(req.getToken()) &&  StringUtils.isNotBlank(req.getBeginTime()) &&  StringUtils.isNotBlank(req.getVehicleNO()) ){
				//车牌号格式
				if( !checkVehciNoParam(req.getVehicleNO()) ){
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
	
	protected ApiErrorCode checkZjxlVehicle(String vehicleNo) throws Exception{
		ApiErrorCode errorCode = ApiErrorCode.API_SYSTEM_ERROR;
		ZJXLVehicleReq req = new ZJXLVehicleReq();
		req.setVehicleno(vehicleNo);
		List<ZJXLVehicleResp> list = crossVehicleService.findList(req);
		if(list.size()==1){
			ZJXLVehicleResp resp = list.get(0);
			if(!StringUtils.equals("1", resp.getCrossloge())){
				//车辆未入网
				errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR11;
			}else if(!StringUtils.equals("1", resp.getVehiclelogo())){
				//车辆未开启
				errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR12;
			}else{
				//验证通过
				errorCode=ApiErrorCode.API_SYSTEM_SUCCESS;
			}
		}else{
			//车辆未添加
			errorCode=ApiErrorCode.API_POSITION_PARAM_ERROR10;
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
	private boolean checkTimeParam(String beginTime,String endTime) throws ParseException{
		boolean rs =false;
		if( StringUtils.isNotBlank(beginTime)   ){
			long t1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime).getTime();
			long t2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime).getTime();
			if( t1>=t2){
				throw new ApplicationExectpion("结束时间必须大于开始时间.");
			}else{
				rs =true;
			}
		}
		return rs;
	}
	
	private List<APIVehicleGpsResp>  getData(List<VehicleGpsZjxl> list){
		List<APIVehicleGpsResp> resp =new ArrayList<APIVehicleGpsResp>();
		for(VehicleGpsZjxl sp : list){
			APIVehicleGpsResp item1 =new APIVehicleGpsResp();
			item1.setLat(BigDecimal.valueOf(sp.getLat()).setScale(6,BigDecimal.ROUND_HALF_UP).toString());
			item1.setLon(BigDecimal.valueOf(sp.getLon()).setScale(6,BigDecimal.ROUND_HALF_UP).toString());;
			item1.setUtc(sp.getUtc().toString());
			resp.add(item1);
		}
		return resp;
	}
	
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		//c.s
	}
}
	