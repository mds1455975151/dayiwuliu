package com.tianrui.trwl.admin.web;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.api.req.admin.ZJXLVehicleReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.ZJXLVehicleResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

/**
 * 
  * <p>Title:CrossAction </p>
  * <p>Description:TODO中交兴路车辆管理 </p>
  * <p>Company: </p> 
  * @author   yangbobo
  * @date   2017年8月25日 上午10:14:20
 */
@Controller
@RequestMapping("fileCross")
public class CrossAction {
	public static Logger logger = LoggerFactory.getLogger(CrossAction.class);
	@Autowired
	ICrossVehicleService crossVehicleService;
	
	
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminMember/cross_vehicle");
		return view;
	}
	/**
	 * 
	 * @Title: crossVehicle 
	 * @Description: 车辆信息列表查询
	 * @param @param req
	 * @param @return
	 * @param @throws Exception   
	 * @return Result    
	 * @throws
	 */
	@RequestMapping("crossVehicle")
	@ResponseBody
	public Result crossVehicle(ZJXLVehicleReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PageResp<ZJXLVehicleResp> list = crossVehicleService.find(req);
		rs.setData(list);
		return rs;
	}
	/**
	 * 
	 * @Title: saveAdd 
	 * @Description: 添加车辆信息
	 * @param @param req
	 * @param @return
	 * @param @throws Exception   
	 * @return Result    
	 * @throws
	 */
	@RequestMapping("/saveAdd")
	@ResponseBody
	public Result saveAdd(ZJXLVehicleReq req,HttpServletRequest request) throws Exception{
		Users user = SessionManager.getSessionMember(request);
		req.setCreator(user.getAccount());
		Result rs = crossVehicleService.insert(req);
		return rs;
		
	}
	/**
	 * 
	 * @Title: vehiclelogo 
	 * @Description: 启用/禁用
	 * @param @return
	 * @param @throws Exception   
	 * @return Result    
	 * @throws
	 */
	@RequestMapping("/vehiclelogo")
	@ResponseBody
	public Result vehiclelogo(String id,String pageNo)throws Exception{
		Result rs = crossVehicleService.update(id);
		return rs;
		
	}
	/**
	 * 
	 * @Title: selectVehicle 
	 * @Description: 根据车牌号码搜索车辆信心
	 * @param @param vehicleno
	 * @param @return
	 * @param @throws Exception   
	 * @return Result    
	 * @throws
	 */
	@RequestMapping("/selectVehicle")
	@ResponseBody
	public Result selectVehicle(String vehicleno)throws Exception {
		Result rs =crossVehicleService.selectVehicle(vehicleno);
		return rs;
		
	}
	
}
