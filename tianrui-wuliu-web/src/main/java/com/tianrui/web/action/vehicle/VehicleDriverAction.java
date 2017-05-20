package com.tianrui.web.action.vehicle;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.front.vehicle.VehicleDriverReq;
import com.tianrui.api.resp.front.vehicle.VehicleDriverResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.SessionManager;

/**
 * 车辆司机处理类
 * <p>
 * @author guyuke
 * @time 2016年6月06日 上午11:53:38
 */
@Controller
@RequestMapping("/trwuliu/Member/vehiDriver")
public class VehicleDriverAction {
	public static Logger logger =LoggerFactory.getLogger(VehicleDriverAction.class);
	@Autowired
	private IVehicleDriverService vehicleDriverService;
	
	/**
	 * 根据条件查询车辆司机信息
	 * <p>
	 * @param id            主键
	 * @param memberId      当前用户主键
	 * @param driverId      司机主键
	 * @param driverName    司机姓名
	 * @param driverTel     司机电话
	 * @param driverRemark  司机备注
	 * @param vehicleId     车辆主键
	 * @param vehicleNo     车牌号
	 * @param vehiTypeName  车型
	 * @param vehiRemark    车辆备注
	 * @param pageNo        页码
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 上午11:54:00
	 */
	@RequestMapping(value = "/getVehiDriverByPage", method = RequestMethod.POST)
	@ResponseBody
	public Result queryVehiDriverByPage(String id, 
	                                     String memberId, 
		                                  String driverId, 
		                                   String driverName, 
		                                    String driverTel, 
			                                 String driverRemark, 
			                                  String vehicleId, 
			                                   String vehicleNo, 
			                                    String vehiTypeName, 
			                                     String vehiRemark, 
			                                      String pageNo) throws Exception{
		Result rs = Result.getSuccessResult();
		VehicleDriverReq vehiDriverReq = new VehicleDriverReq();
		// 主键
		vehiDriverReq.setId(id);
		// 创建人
		vehiDriverReq.setCreator(memberId);
		// 司机主键
		vehiDriverReq.setDriverId(driverId);
		// 司机姓名
		vehiDriverReq.setDriverName(driverName);
		// 司机电话
		vehiDriverReq.setDriverTel(driverTel);
		// 司机备注
		vehiDriverReq.setDriverRemark(driverRemark);
		// 车辆主键
		vehiDriverReq.setVehicleId(vehicleId);
		// 车牌号
		vehiDriverReq.setVehicleNo(vehicleNo);
		// 车型
		vehiDriverReq.setVehicleTypeName(vehiTypeName);
		// 车辆备注
		vehiDriverReq.setVehicleRemark(vehiRemark);
		// 页码
		if (pageNo != null) {
			vehiDriverReq.setPageNo(Integer.parseInt(pageNo));
		} else {
			vehiDriverReq.setPageNo(0);
		}
		PaginationVO<VehicleDriverResp> pageVo = vehicleDriverService.queryVehiDriverByPage(vehiDriverReq);
		rs.setData(pageVo);
		return rs;
	}
	
	/**
	 * 根据条件查询我的司机信息(查询所有)
	 * <p>
	 * @param id            主键
	 * @param memberId      当前用户主键
	 * @param driverId      司机主键
	 * @param driverName    司机姓名
	 * @param driverTel     司机电话
	 * @param driverRemark  司机备注
	 * @param vehicleId     车辆主键
	 * @param vehicleNo     车牌号
	 * @param vehiTypeName  车型
	 * @param vehiRemark    车辆备注
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 上午11:54:00
	 */
	@RequestMapping(value = "/getVehiDriver", method = RequestMethod.POST)
	@ResponseBody
	public Result queryMyDriverByCondition(String id, 
								            String memberId, 
								             String driverId, 
								              String driverName, 
								               String driverTel, 
								                String driverRemark, 
								                 String vehicleId, 
								                  String vehicleNo, 
								                   String vehiTypeName, 
								                    String vehiRemark) throws Exception{
		
		Result rs = Result.getSuccessResult();
		VehicleDriverReq vehiDriverReq = new VehicleDriverReq();
		// 主键
		vehiDriverReq.setId(id);
		// 创建人
		vehiDriverReq.setCreator(memberId);
		// 司机主键
		vehiDriverReq.setDriverId(driverId);
		// 司机姓名
		vehiDriverReq.setDriverName(driverName);
		// 司机电话
		vehiDriverReq.setDriverTel(driverTel);
		// 司机备注
		vehiDriverReq.setDriverRemark(driverRemark);
		// 车辆主键
		vehiDriverReq.setVehicleId(vehicleId);
		// 车牌号
		vehiDriverReq.setVehicleNo(vehicleNo);
		// 车型
		vehiDriverReq.setVehicleTypeName(vehiTypeName);
		// 车辆备注
		vehiDriverReq.setVehicleRemark(vehiRemark);
		//TODO
		List<VehicleDriverResp> vehiDriverRespList = vehicleDriverService.queryVehiDriverByCondition(vehiDriverReq);
		rs.setData(vehiDriverRespList);
		return rs;
	}
	
	/**
	 * 新增车辆司机信息
	 * <p>
	 * @param id            主键
	 * @param memberId      当前用户主键
	 * @param driverId      司机主键
	 * @param driverName    司机姓名
	 * @param driverTel     司机电话
	 * @param driverRemark  司机备注
	 * @param vehicleId     车辆主键
	 * @param vehicleNo     车牌号
	 * @param vehiTypeName  车型
	 * @param vehiRemark    车辆备注
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 上午11:54:00
	 */
	@RequestMapping(value = "/saveVehiDriver", method = RequestMethod.POST)
	@ResponseBody
	public Result saveVehiDriver(String memberId, 
						          String driverId, 
						           String driverName, 
						            String driverTel, 
						             String driverRemark, 
						              String vehicleId, 
						               String vehicleNo, 
						                String vehiTypeName, 
						                 String vehiRemark) throws Exception{
		
		Result rs = Result.getSuccessResult();
		
		VehicleDriverReq vehiDriverReq = new VehicleDriverReq();
		// 主键
		vehiDriverReq.setId(getUUId());
		// 创建人
		vehiDriverReq.setCreator(memberId);
		// 司机主键
		vehiDriverReq.setDriverId(driverId);
		// 司机姓名
		vehiDriverReq.setDriverName(driverName);
		// 司机电话
		vehiDriverReq.setDriverTel(driverTel);
		// 司机备注
		vehiDriverReq.setDriverRemark(driverRemark);
		// 车辆主键
		vehiDriverReq.setVehicleId(vehicleId);
		// 车牌号
		vehiDriverReq.setVehicleNo(vehicleNo);
		// 车型
		vehiDriverReq.setVehicleTypeName(vehiTypeName);
		// 车辆备注
		vehiDriverReq.setVehicleRemark(vehiRemark);
		
		// 插入操作
		rs = vehicleDriverService.insert(vehiDriverReq);
		
		return rs;
	}
	
	/**
	 * 删除旧信息并新增车辆司机信息(车辆绑定司机时使用)
	 * <p>
	 * @param id            主键
	 * @param memberId      当前用户主键
	 * @param vehiDriverId  车辆司机表主键
	 * @param driverId      司机主键
	 * @param driverName    司机姓名
	 * @param driverTel     司机电话
	 * @param driverRemark  司机备注
	 * @param vehicleId     车辆主键
	 * @param vehicleNo     车牌号
	 * @param vehiTypeName  车型
	 * @param vehiRemark    车辆备注
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 上午11:54:00
	 */
	@RequestMapping(value = "/delAndSaveVehiDriver", method = RequestMethod.POST)
	@ResponseBody
	public Result delAndSaveVehiDriver(String memberId, 
				                        String vehiDriverId,
							             String driverId, 
							              String driverName, 
							               String driverTel, 
							                String driverRemark, 
							                 String vehicleId, 
							                  String vehicleNo, 
							                   String vehiTypeName, 
							                    String vehiRemark) throws Exception{
		
		Result rs = Result.getSuccessResult();
		
		if (!"".equals(vehiDriverId)) {
			rs = vehicleDriverService.deleteByPrimaryKey(vehiDriverId);
		}
		
		VehicleDriverReq vehiDriverReq = new VehicleDriverReq();
		// 主键
		vehiDriverReq.setId(getUUId());
		// 创建人
		vehiDriverReq.setCreator(memberId);
		// 司机主键
		vehiDriverReq.setDriverId(driverId);
		// 司机姓名
		vehiDriverReq.setDriverName(driverName);
		// 司机电话
		vehiDriverReq.setDriverTel(driverTel);
		// 司机备注
		vehiDriverReq.setDriverRemark(driverRemark);
		// 车辆主键
		vehiDriverReq.setVehicleId(vehicleId);
		// 车牌号
		vehiDriverReq.setVehicleNo(vehicleNo);
		// 车型
		vehiDriverReq.setVehicleTypeName(vehiTypeName);
		// 车辆备注
		vehiDriverReq.setVehicleRemark(vehiRemark);
		
		// 插入操作
		rs = vehicleDriverService.insert(vehiDriverReq);
		
		return rs;
	}
	/**已知安联账号，车辆司机绑定
	 * @throws Exception  */
	@RequestMapping("alSaveDriverVehicle")
	@ResponseBody
	public Result alSaveDriverVehicle(VehicleDriverReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreator(vo.getId());
		vehicleDriverService.anlianInsert(req);
		return rs;
	}
	
	/**
	 * 更新车辆司机信息
	 * <p>
	 * @param id            主键
	 * @param memberId      当前用户主键
	 * @param driverId      司机主键
	 * @param driverName    司机姓名
	 * @param driverTel     司机电话
	 * @param driverRemark  司机备注
	 * @param vehicleId     车辆主键
	 * @param vehicleNo     车牌号
	 * @param vehiTypeName  车型
	 * @param vehiRemark    车辆备注
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 上午11:54:00
	 */
	@RequestMapping(value = "/updateVehiDriver", method = RequestMethod.POST)
	@ResponseBody
	public Result updateVehiDriver(String id, 
					                String memberId, 
						             String driverId, 
						              String driverName, 
						               String driverTel, 
						                String driverRemark, 
						                 String vehicleId, 
						                  String vehicleNo, 
						                   String vehiTypeName, 
						                    String vehiRemark) throws Exception{
		Result rs = Result.getSuccessResult();
		
		VehicleDriverReq vehiDriverReq = new VehicleDriverReq();
		// 主键
		vehiDriverReq.setId(id);
		// 创建人
		vehiDriverReq.setCreator(memberId);
		// 司机主键
		vehiDriverReq.setDriverId(driverId);
		// 司机姓名
		vehiDriverReq.setDriverName(driverName);
		// 司机电话
		vehiDriverReq.setDriverTel(driverTel);
		// 司机备注
		vehiDriverReq.setDriverRemark(driverRemark);
		// 车辆主键
		vehiDriverReq.setVehicleId(vehicleId);
		// 车牌号
		vehiDriverReq.setVehicleNo(vehicleNo);
		// 车型
		vehiDriverReq.setVehicleTypeName(vehiTypeName);
		// 车辆备注
		vehiDriverReq.setVehicleRemark(vehiRemark);
		
		// 更新操作
		rs = vehicleDriverService.updateByPrimaryKeySelective(vehiDriverReq);
		
		return rs;
	}
	
	/**
	 * 删除车辆司机信息
	 * <p>
	 * @param id    主键
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 上午11:54:00
	 */
	@RequestMapping(value = "/deleteVehiDriver", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteVehiDriver(String id) throws Exception{

		Result rs = vehicleDriverService.deleteByPrimaryKey(id);
		return rs;
	}
	
	/**
	 * 获取UUID作为主键
	 * <p>
	 * @return id
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 上午11:54:00
	 */
	private String getUUId() {
		// 获取UUID
		String uuid = UUID.randomUUID().toString();
		// 去除字符串中的"-"
		String[] uuid_array = uuid.split("-"); 
		StringBuffer idBuffer = new StringBuffer();
		for (String id : uuid_array) {
			idBuffer.append(id);
		}
		
		return idBuffer.toString();
	}
}
