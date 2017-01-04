package com.tianrui.web.action.vehicle;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IFileService;
import com.tianrui.api.intf.IMemberVehicleService;
import com.tianrui.api.intf.IOwnerDriverService;
import com.tianrui.api.req.front.vehicle.MemberVehicleReq;
import com.tianrui.api.req.front.vehicle.OwnerDriverReq;
import com.tianrui.api.req.front.vehicle.VehicleAndDriverReq;
import com.tianrui.api.resp.front.vehicle.MemberVehicleResp;
import com.tianrui.api.resp.front.vehicle.OwnerDriverResp;
import com.tianrui.api.resp.front.vehicle.VehicleAndDriverResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.AuthValidation;

/**
 * 我的车辆处理类
 * <p>
 * @author guyuke
 * @time 2016年6月01日 上午11:53:38
 */
@Controller
@RequestMapping("/trwuliu/Member/myVehicle")
public class MyVehicleAction {
	public static Logger logger =LoggerFactory.getLogger(MyVehicleAction.class);
	@Autowired
	private IMemberVehicleService memberVehicleService;
	@Autowired
	private IOwnerDriverService ownerDriverService;
	@Autowired
	private IFileService iFileService;
	
	/**
	 * 我的车辆跳转页面
	 * <p>
	 * @return 我的车辆页面
	 * @throws IOException
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping("/myVehiclePage")
	@AuthValidation(autyType=Constant.AUTHCHECK_USER)
	public ModelAndView indexPage() throws IOException{
		return new ModelAndView("/member/vehicle/myVehiclePage");
	}
	/**
	 * 
	 * @描述:我的车辆信息修改（重新认证）跳转页面
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年6月27日上午10:26:31
	 */
	@RequestMapping("/updatePage")
	public ModelAndView updatePage(String id)throws Exception{
		MemberVehicleResp resp = memberVehicleService.queryMyVehicleInfoById(id);
		ModelAndView view = new ModelAndView();
		view.addObject("vehicle", resp);
		view.setViewName("/member/vehicle/updateDriverPage");
		return view;
	}
	/**
	 * 
	 * @描述:车辆审核失败页面
	 * @param id
	 * @return
	 * @throws Exception 
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年6月25日下午3:43:42
	 */
	@RequestMapping("/vehicleFilePage")
	public ModelAndView vehicleFilePage(String id)throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVehicleResp resp = memberVehicleService.findMemoMassageById(id);
		view.addObject("memo", resp);
		view.setViewName("/member/vehicle/vehicleFailPage");
		return view;
	}
	
	/**
	 * 添加车辆跳转页面
	 * <p>
	 * @return 添加车辆页面
	 * @throws IOException
	 * <p>
	 * @author guyuke
	 * @time 2016年6月13日 上午14:54:00
	 */
	@RequestMapping("/addVehiclePage")
	public ModelAndView addDriverPage() throws IOException {
		return new ModelAndView("/member/vehicle/addVehiclePage");
	}
	
	/**
	 * 根据条件查询我的车辆信息(分页)
	 * <p>
	 * @param id           主键
	 * @param vehiId       用户主键
	 * @param vehiWholeNo  车牌号
	 * @param pageNo       页码
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/getMyVehicleByPage", method = RequestMethod.POST)
	@ResponseBody
	public Result queryMyVehicleByPage(String id, 
							            String vehiId, 
							             String vehiWholeNo,
							              String pageNo) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVehicleReq vehicleReq = new MemberVehicleReq();
		// 主键
		vehicleReq.setId(id);
		// 车辆主键
		vehicleReq.setVehicleId(vehiId);
		// 车牌号
		if (vehiWholeNo != null && !"".equals(vehiWholeNo)) {
			// 车牌号前缀
			vehicleReq.setVehiclePrefix(vehiWholeNo.substring(0,2));
			// 车牌号
			vehicleReq.setVehicleNo(vehiWholeNo.substring(2));
		} else {
			// 车牌号前缀
			vehicleReq.setVehiclePrefix(null);
			// 车牌号
			vehicleReq.setVehicleNo(null);
		}
		// 页码
		if (pageNo != null) {
			vehicleReq.setPageNo(Integer.parseInt(pageNo));
		} else {
			vehicleReq.setPageNo(0);
		}
		PaginationVO<MemberVehicleResp> pageVo = memberVehicleService.queryMyVehicleInfoByPage(vehicleReq);
		rs.setData(pageVo);
		return rs;
	}
	
	/**
	 * 根据条件查询我的车辆信息(查询所有)
	 * <p>
	 * @param id           主键
	 * @param memberId     用户主键
	 * @param vehiId       车辆主键
	 * @param vehiWholeNo  车牌号
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/getMyVehicle", method = RequestMethod.POST)
	@ResponseBody
	public Result queryMyVehicleByCondition(String id, 
			                                 String memberId,
								              String vehiId, 
								              String status,
								               String vehiWholeNo) throws Exception{
		
		Result rs = Result.getSuccessResult();
		MemberVehicleReq vehicleReq = new MemberVehicleReq();
		// 主键
		vehicleReq.setId(id);
		// 用户主键
		vehicleReq.setMemberId(memberId);
		// 车辆主键
		vehicleReq.setVehicleId(vehiId);
		
		vehicleReq.setStatus(status);
		// 车牌号
		if (vehiWholeNo != null && !"".equals(vehiWholeNo)) {
			// 车牌号前缀
			vehicleReq.setVehiclePrefix(vehiWholeNo.substring(0,2));
			// 车牌号
			vehicleReq.setVehicleNo(vehiWholeNo.substring(2));
		} else {
			// 车牌号前缀
			vehicleReq.setVehiclePrefix(null);
			// 车牌号
			vehicleReq.setVehicleNo(null);
		}
		
		List<MemberVehicleResp> vehicleRespList = memberVehicleService.queryMyVehicleInfoByCondition(vehicleReq);
		rs.setData(vehicleRespList);
		return rs;
	}
	
	/**
	 * 根据条件进行我的未绑定车辆的司机信息查询
	 * <p>
	 * @param memberId     用户主键
	 * @param driverName   司机名字
	 * @param driverTel    司机电话
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月13日 上午11:54:00
	 */
	@RequestMapping(value = "/getMyDriverOutOfRange", method = RequestMethod.POST)
	@ResponseBody
	public Result queryMyDriverOutsideByCondition(String memberId,
												   String driverName,
												    String driverTel) throws Exception{
		
		Result rs = Result.getSuccessResult();
		OwnerDriverReq driverReq = new OwnerDriverReq();
		// 用户主键
		driverReq.setVehicleownerid(memberId);
		// 司机姓名
		driverReq.setDrivername(driverName);
		// 司机电话
		driverReq.setDrivertel(driverTel);
		driverReq.setStatus("1");
		// 查询操作
//		List<OwnerDriverResp> driverRespList = ownerDriverService.queryMyDriverOutsideByCondition(driverReq);
		List<OwnerDriverResp> list = ownerDriverService.queryMyDriverInfoByCondition(driverReq);
		rs.setData(list);
		return rs;
	}
	
	/**
	 * 根据条件联合查询车辆司机关联信息(查询所有)
	 * <p>
	 * @param id           主键
	 * @param memberId     会员主键
	 * @param ownerId      车主主键
	 * @param ownerName    车主名字
	 * @param ownerTel     车主电话
	 * @param status       状态 
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/getVehiAndDriver", method = RequestMethod.POST)
	@ResponseBody
	public Result queryVehiAndDriverByCondition(String memberId, 
									             String vehiId, 
									              String vehiNo, 
									              String vehiFix, 
									               String vehiLenth, 
									                String vehiType,
									                String pageNo,
									                String pageSize,
									                 String driverName,
									                  String driverTel) throws Exception{
		
		Result rs = Result.getSuccessResult();
		VehicleAndDriverReq vehiAndDriverReq = new VehicleAndDriverReq();
		// 用户主键
		vehiAndDriverReq.setMemberId(memberId);
		// 车辆主键
		vehiAndDriverReq.setVehiId(vehiId);
		// 车牌号前缀
		vehiAndDriverReq.setVehiPrefix(vehiFix);
		// 车牌号
		vehiAndDriverReq.setVehiNo(vehiNo);
		
		// 车长
		vehiAndDriverReq.setVehiLength(vehiLenth);
		// 车型
		vehiAndDriverReq.setVehiType(vehiType);
		// 司机名字
		vehiAndDriverReq.setDriverName(driverName);
		// 司机电话
		vehiAndDriverReq.setDriverTel(driverTel);
		if(StringUtils.isNotBlank(pageNo)){
			vehiAndDriverReq.setPageNo(Integer.valueOf(pageNo));
		}
		if(StringUtils.isNotBlank(pageSize)){
			vehiAndDriverReq.setPageSize(Integer.valueOf(pageSize));
		}
		PaginationVO<VehicleAndDriverResp> vehiAndDriverRespList = memberVehicleService.queryVehicleAndDriverByCondition(vehiAndDriverReq);
		rs.setData(vehiAndDriverRespList);
		return rs;
	}
	
	/**
	 * 新增我的车辆信息
	 * <p>
	 * @param vehiReq    页面信息
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/saveMyVehicle", method = RequestMethod.POST)
	@ResponseBody
	public Result saveMyVehicleInfo(
			MemberVehicleReq vehiReq,
			String vehiWholeNo
			) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVehicleReq vehicleReq = new MemberVehicleReq();
		PropertyUtils.copyProperties(vehicleReq, vehiReq);
		
		String id = getUUId();
		vehicleReq.setId(id);
		vehicleReq.setVehicleId(id);
		if (vehiWholeNo != null && !"".equals(vehiWholeNo)) {
			// 车牌号前缀
			vehicleReq.setVehiclePrefix(vehiWholeNo.substring(0,2));
			// 车牌号
			vehicleReq.setVehicleNo(vehiWholeNo.substring(2));
		} else {
			// 车牌号前缀
			vehicleReq.setVehiclePrefix(null);
			// 车牌号
			vehicleReq.setVehicleNo(null);
		}
		vehicleReq.setCreator(vehiReq.getMemberId());
		// 插入操作
		rs = memberVehicleService.insert(vehicleReq);
		return rs;
	}
	
	/**
	 * 更新我的车辆信息
	 * <p>
	 * @param vehiReq    页面信息
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/updateMyVehicle", method = RequestMethod.POST)
	@ResponseBody
	public Result updateMyVehicleInfo(MemberVehicleReq vehiReq) throws Exception{
		Result rs = Result.getSuccessResult();
		
		MemberVehicleReq rreq = new MemberVehicleReq();
		rreq.setVehicleNo(vehiReq.getVehicleNo());
		rreq.setVehiclePrefix(vehiReq.getVehiclePrefix());
		rreq.setStatus("1");
		List<MemberVehicleResp> list = memberVehicleService.queryMyVehicleInfoByCondition(rreq);
		if(list.size()!=0){
			rs.setCode("1");
			rs.setError("该车牌号已被认证");
			return rs;
		}
		
		//修改车辆信息，车辆再次进入认证状态，后台认证时间为createtime
		vehiReq.setCreateTime(new Date().getTime());
		vehiReq.setStatus("2");
		// 更新操作
		rs = memberVehicleService.updateByPrimaryKeySelective(vehiReq);
		
		return rs;
	}
	
	/**
	 * 删除我的车辆信息
	 * <p>
	 * @param id    主键
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
	 */
	@RequestMapping(value = "/deleteMyVehicle", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteMyVehicleInfo(String id) throws Exception{

		Result rs = memberVehicleService.deleteByPrimaryKey(id);
		return rs;
	}
	
	/**
	 * 获取UUID作为主键
	 * <p>
	 * @return id
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 上午11:54:00
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
