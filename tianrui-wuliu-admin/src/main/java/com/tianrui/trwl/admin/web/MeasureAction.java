package com.tianrui.trwl.admin.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IMeasureService;
import com.tianrui.api.req.admin.MeasureCountReq;
import com.tianrui.api.req.admin.MeasureReq;
import com.tianrui.api.resp.admin.MeasureResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.service.bean.Measure;
import com.tianrui.trwl.admin.util.SessionManager;

/**
 * 
 * @类描述：计量单位
 * @创建人：lsj
 * @创建时间：2016年5月16日下午3:41:43
 *
 * @修改人：lsj
 * @修改时间：2016年5月16日下午3:41:43
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/measure")
public class MeasureAction {
	public static Logger logger =LoggerFactory.getLogger(MeasureAction.class);
	@Autowired
	protected IMeasureService measureService;
	/**
	 * 
	 * @描述:添加计量记录，并根据基本单位的改变修改其他想对应的换算率
	 * @param measureCode 计量编码
	 * @param measureName 计量名称
	 * @param measureType 所属量纲
	 * @param measureBase 是否基本单位 0是，，1否
	 * @param conversion 换算率
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 lsj
	 * @创建时间 2016年5月6日上午10:06:17
	 */
	@RequestMapping("/measureSave")
	@ResponseBody
	public Result measureSave(
			String measureCode,
			String measureName,
			String measureType,
			String measureBase,
			String conversion,
			HttpServletRequest request
			) throws Exception{
		
		Result rs =Result.getSuccessResult();
		//非空验证
		if(measureCode==null||"".equals(measureCode)||
				measureName==null||"".equals(measureName)||
				measureType==null||"".equals(measureType)||
				measureBase==null||"".equals(measureBase)||
				conversion==null||"".equals(conversion)
				){
			rs.setCode("1");
			rs.setError("数据不能为空");
		}
		Users users = SessionManager.getSessionMember(request);
		MeasureReq measure = new MeasureReq();
		measure.setCreateId(users.getId());
		measure.setCreateName(users.getAccount());
		measure.setId(UUIDUtil.getId());
		measure.setConversion(Double.parseDouble(conversion));
		measure.setMeasureBase(measureBase);
		measure.setMeasureCode(measureCode);
		measure.setMeasureName(measureName);
		measure.setMeasureType(measureType);
		rs= measureService.insert(measure);
		return rs;
	}
	
	/**
	 * 
	 * @描述:修改measure
	 * @return
	 * @throws Exception 
	 * @返回类型 String
	 * @创建人 lsj
	 * @创建时间 2016年5月6日下午3:41:45
	 */
	@RequestMapping("/updateMeasureBase")
	@ResponseBody
	public Result updateMeasureBase(MeasureReq req) throws Exception{
		Result rs =Result.getSuccessResult();
		rs = measureService.update(req);
		return rs;
	}
	
	/**
	 * 
	 * @描述:添加量纲之间换算
	 * @param measureType 量纲一
	 * @param measureMain 以哪个量纲为主
	 * @param conversion 量纲间换算单位 (量纲间基本单位换算)
	 * @return
	 * @throws Exception 
	 * @返回类型 String
	 * @创建人 lsj
	 * @创建时间 2016年5月6日下午5:01:25
	 */
	@RequestMapping("/measureTypeConver")
	@ResponseBody
	public Result measureTypeConver(
			HttpServletRequest request,
			String measureType,String measureMain,String conversion) throws Exception{
		Result rs =Result.getSuccessResult();
		if(measureType==null||"".equals(measureType)||
				measureMain==null||"".equals(measureMain)||
				conversion==null||"".equals(conversion)
				){
			rs.setCode("1");
			rs.setError("数据不能为空");
			return rs;
		}
		Users user = SessionManager.getSessionMember(request);
		MeasureReq req = new MeasureReq();
		req.setCreateId(user.getId());
		req.setCreateName(user.getAccount());
		req.setMeasureCode(measureType);
		req.setMeasureMain(measureMain);
		req.setConversion(Double.parseDouble(conversion));
		rs = measureService.saveMain(req);
		return rs;
	}
	
	/**
	 * 
	 * @描述:相同量纲间计算
	 * @param value 上传的数值，必须为数字字符串
	 * @param measureCode 计量编码
	 * @return
	 * @throws Exception 
	 * @返回类型 String
	 * @创建人 lsj
	 * @创建时间 2016年5月7日上午9:35:27
	 */
	@RequestMapping("/reckonMeasureType")
	@ResponseBody
	public Result reckonMeasureType(String value,String measureCode) throws Exception{
		Result rs =Result.getSuccessResult();
		if(value==null||"".equals(value)||
				measureCode==null||"".equals(measureCode)
				){
			rs.setCode("1");
			rs.setError("数据不能为空");
			return rs;
		}
		MeasureCountReq req = new MeasureCountReq();
		req.setValue(value);
		req.setMeasureCode(measureCode);
		rs = measureService.similarCalculation(req);
		return rs;
	}
	/**
	 * 
	 * @描述:不同量纲间计算
	 * @param value 上传的数值，必须为数字字符串
	 * @param measureCode 计量编码
	 * @param measureMain 要换算的量纲
	 * @return
	 * @throws Exception 
	 * @返回类型 String
	 * @创建人 lsj
	 * @创建时间 2016年5月7日上午9:35:27
	 */
	@RequestMapping("/reckonMeasureMain")
	@ResponseBody
	public Result reckonMeasureMain(String value,String measureCode,String measureMain) throws Exception{
		Result rs =Result.getSuccessResult();
		if(value==null||"".equals(value)||
				measureCode==null||"".equals(measureCode)||
				StringUtils.isBlank(measureMain)
				){
			rs.setCode("1");
			rs.setError("数据不能为空");
			return rs;
		}
		MeasureCountReq req = new MeasureCountReq();
		req.setValue(value);
		req.setMeasureCode(measureCode);
		req.setMeasureMain(measureMain);
		rs = measureService.similarCalculation(req);
		return rs;
	}
	/**
	 * 
	 * @描述:获取量纲基本单位
	 * @param measureType
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月17日上午11:16:40
	 */
	@RequestMapping("/findMeasureCode")
	@ResponseBody
	public Result findMeasureCode(String measureType) throws Exception{
		Result rs = Result.getSuccessResult();
		if(StringUtils.isBlank(measureType)){
			rs.setCode("1");
			rs.setError("数据不能为空");
			return rs;
		}
		MeasureReq req = new MeasureReq();
		req.setMeasureType(measureType);
		req.setMeasureBase("0");
		
		List<MeasureResp> resp = measureService.findByEntityList(req);
		if(resp.size()!=1){
			rs.setCode("1");
			rs.setError("后台数据有误");
			return rs;
		}else{
			rs.setData(resp.get(0));
			return rs;
		}
	}
	/**
	 * 
	 * @描述:单位换算初始化查询
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月17日上午11:34:52
	 */
	@RequestMapping("/findMeasureInit")
	public ModelAndView findMeasureInit() throws Exception{
		MeasureReq req = new MeasureReq();
		req.setMeasureBase("0");
		//查询所有量纲
		List<MeasureResp> resp = measureService.findByEntityList(req);
		ModelAndView view = new ModelAndView();
		view.addObject("measureBase",resp);
		view.setViewName("/file/measure/measure_manager");
		return view;
	}
	
	/**
	 * 
	 * @描述:条件查询
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月17日上午11:34:52
	 */
	@RequestMapping("/findMeasureBlur")
	@ResponseBody
	public Result findMeasureBlur(@RequestParam(defaultValue = "") String oCode,
			@RequestParam(defaultValue = "") String oName,
			@RequestParam(defaultValue = "") String oType) throws Exception{
		MeasureReq req = new MeasureReq();
		req.setMeasureType(oType);
		req.setMeasureCode(oCode);
		req.setMeasureName(oName);
		List<MeasureResp> measures = measureService.findMeasureBlur(req);
		Result result = Result.getSuccessResult();
		result.setData(measures);
		return result;
	}
	/**
	 * 
	 * @描述:删除measure
	 * @param id
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月20日上午10:55:09
	 */
	@RequestMapping("/deleteMeasureById")
	@ResponseBody
	public Result deleteMeasureById(String id) throws Exception{
		Result rs = new Result();
		rs = measureService.delectMeasureById(id);
		return rs;
	}
	
	/**
	 * 
	 * @描述:量纲间换算查询
	 * @param req
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月20日下午2:25:43
	 */
	@RequestMapping("/findMeasureMain")
	@ResponseBody
	public Result findMeasureMain(MeasureReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		List<MeasureResp> resp = measureService.findMeasonMainNotNull();
		rs.setData(resp);
		return rs;
	}
	
	/**
	 * 
	 * @描述:通过id查询
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月21日上午8:47:41
	 */
	@RequestMapping("findMeasureById")
	@ResponseBody
	public Result findMeasureById(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		MeasureResp resp = measureService.findMeasureById(id);
		rs.setData(resp);
		return rs;
	}
}
