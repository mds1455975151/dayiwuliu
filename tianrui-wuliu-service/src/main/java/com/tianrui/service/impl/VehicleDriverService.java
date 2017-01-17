package com.tianrui.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.front.vehicle.VehicleDriverReq;
import com.tianrui.api.resp.front.vehicle.VehicleDriverResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;

/**
 * 车辆司机关联接口实现类
 * <p>
 * @author guyuke
 * @time 2016年6月6日 下午2:15:00
 */
@Service
public class VehicleDriverService implements IVehicleDriverService {
	
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	
	/**
	 * 父类方法重写，根据条件进行车辆司机关联信息查询
	 * 
	 * @see com.tianrui.api.admin.intf.IVehicleDriverService#queryVehiDriverByPage(com.tianrui.api.req.admin.VehicleDriverReq)
	 */
	@Override
	public PaginationVO<VehicleDriverResp> queryVehiDriverByPage(VehicleDriverReq req)
			throws Exception {
		
		// 分页VO
		PaginationVO<VehicleDriverResp> pageVo =new PaginationVO<VehicleDriverResp>();
		VehicleDriver reqCondition = new VehicleDriver();
		copyProperties(reqCondition, req);
		
		long total = vehicleDriverMapper.selectCountByCondition(reqCondition);
		
		if (total == 0) {
			return pageVo;
		} else {
			if (req.getPageNo() <= 0) {
				req.setPageNo(1);
			}
			// 查询开始位置
			reqCondition.setStart((req.getPageNo()-1)*req.getPageSize());
			// 查询数量
			reqCondition.setLimit(req.getPageSize());
			// 查询操作
			List<VehicleDriver> orgCargoList = vehicleDriverMapper.selectMyVehiDriverByCondition(reqCondition);
			
			// 数据转换
			List<VehicleDriverResp> orgCargoRespList = convert2VehicleDriverList(orgCargoList);
			pageVo.setList(orgCargoRespList);
			pageVo.setPageNo(req.getPageNo());
			pageVo.setTotal(total);
			
			return pageVo;
		}
	}
	
	/**
	 * 父类方法重写，根据条件进行车辆司机关联信息查询
	 * 
	 * @see com.tianrui.api.intf.IVehicleDriverService#queryVehiDriverByCondition(com.tianrui.api.req.front.vehicle.VehicleDriverReq)
	 */
	@Override
	public List<VehicleDriverResp> queryVehiDriverByCondition(VehicleDriverReq req)
			throws Exception {
		
		VehicleDriver reqCondition = new VehicleDriver();
		// 复制前台内容至查询条件
		copyProperties(reqCondition, req);
		// 查询操作 
		List<VehicleDriver> ownDriverList = vehicleDriverMapper.selectMyVehiDriverByCondition(reqCondition);
		
		// 数据转换
		List<VehicleDriverResp> ownDriverRespList = convert2VehicleDriverList(ownDriverList);
		
		return ownDriverRespList;
	}
	
	/**
	 * 父类方法重写，车辆司机关联信息新增保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IVehicleDriverService#insert(com.tianrui.api.req.admin.VehicleDriverReq)
	 */
	@Override
	public Result insert(VehicleDriverReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		VehicleDriver vehicleDriver = new VehicleDriver();
		copyProperties(vehicleDriver, req);
		
		// 设置创建时间
		if (vehicleDriver.getCreatetime() == null) {
			vehicleDriver.setCreatetime(new Date().getTime());
		}
		
		// 数据插入操作
		long count = vehicleDriverMapper.insert(vehicleDriver);
		long a = 1;
		if(count!=a){
			rs.setCode("1");
			rs.setError("操作失败");
			return rs;
		}
		return rs;
	}
	
	/**
	 * 父类方法重写，车辆司机关联信息修改后保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IVehicleDriverService#update(com.tianrui.api.req.admin.VehicleDriverReq)
	 */
	@Override
	public Result updateByPrimaryKeySelective(VehicleDriverReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		VehicleDriver vehicleDriver = new VehicleDriver();
		copyProperties(vehicleDriver, req);
		
		// 数据更新操作
		long count = vehicleDriverMapper.updateByPrimaryKeySelective(vehicleDriver);
		
		rs.setData(count);
		
		return rs;
	}
	
	/**
	 * 父类方法重写，车辆司机关联信息删除功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IVehicleDriverService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public Result deleteByPrimaryKey(String id) throws Exception {
		
		Result rs = Result.getSuccessResult();
		
		VehicleDriver vd = vehicleDriverMapper.selectByPrimaryKey(id);
		if(vd!=null){
			SystemMember member = systemMemberMapper.selectByPrimaryKey(vd.getDriverid());
			MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(vd.getVehicleid());
			//安联 司机 车辆 认证通过 不能解除绑定关系
			if(StringUtils.isNotBlank(member.getAldriverid())&&"1".equals(vehicle.getDesc1())){
				rs.setCode("1");
				rs.setError("安联认证通过的司机-车辆，不能解除绑定关系");
				return rs;
			}
		}
		
		// 数据删除操作
		long count = vehicleDriverMapper.deleteByPrimaryKey(id);
		long a = 1;
		if(count!=a){
			rs.setCode("1");
			rs.setError("操作失败");
			return rs;
		}
		
		return rs;
	}
	
	/**
	 * 不同类型的List间转换操作
	 * <p>
	 * @param argVehicleDriverList
	 * @return
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 下午6:23:00
	 */
	private List<VehicleDriverResp> convert2VehicleDriverList(List<VehicleDriver> argVehicleDriverList) {
		List<VehicleDriverResp> vehicleDriverRespList = null;
		if (argVehicleDriverList != null) {
			vehicleDriverRespList = new ArrayList<VehicleDriverResp>();
			for (VehicleDriver vehicleDriver : argVehicleDriverList) {
				VehicleDriverResp vehicleDriverResp =  new VehicleDriverResp();
				// 主键
				vehicleDriverResp.setId(vehicleDriver.getId());
				// 司机主键
				vehicleDriverResp.setDriverId(vehicleDriver.getDriverid());;
				// 车辆主键
				vehicleDriverResp.setVehicleId(vehicleDriver.getVehicleid());
				// 车牌号
				vehicleDriverResp.setVehicleNo(vehicleDriver.getVehicleno());
				// 车型
				vehicleDriverResp.setVehicleTypeName(vehicleDriver.getVehicletypename());
				// 车辆备注名
				vehicleDriverResp.setVehicleRemark(vehicleDriver.getVehicleremark());
				// 司机名字
				vehicleDriverResp.setDriverName(vehicleDriver.getDrivername());
				// 司机电话
				vehicleDriverResp.setDriverTel(vehicleDriver.getDrivertel());
				// 司机备注名
				vehicleDriverResp.setDriverRemark(vehicleDriver.getDriverremark());
				// 创建人
				vehicleDriverResp.setCreator(vehicleDriver.getCreator());
				// 创建时间
				vehicleDriverResp.setCreateTime(convertLongTODate(vehicleDriver.getCreatetime()));
				vehicleDriverRespList.add(vehicleDriverResp);
			}
		}
		return vehicleDriverRespList;
	}
	
	/**
	 * VehicleDriver与VehicleDriverReq属性的交换
	 * <p>
	 * @param argVehicleDriver
	 * @param argVehicleDriverReq
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 下午2:12:10
	 */
	public void copyProperties(VehicleDriver argVehicleDriver, VehicleDriverReq argVehicleDriverReq) {
		// 主键
		argVehicleDriver.setId(argVehicleDriverReq.getId());
		// 司机主键
		argVehicleDriver.setDriverid(argVehicleDriverReq.getDriverId());;
		// 车辆主键
		argVehicleDriver.setVehicleid(argVehicleDriverReq.getVehicleId());
		// 车牌号
		argVehicleDriver.setVehicleno(argVehicleDriverReq.getVehicleNo());
		// 车型
		argVehicleDriver.setVehicletypename(argVehicleDriverReq.getVehicleTypeName());
		// 车辆备注名
		argVehicleDriver.setVehicleremark(argVehicleDriverReq.getVehicleRemark());
		// 司机名字
		argVehicleDriver.setDrivername(argVehicleDriverReq.getDriverName());
		// 司机电话
		argVehicleDriver.setDrivertel(argVehicleDriverReq.getDriverTel());
		// 司机备注名
		argVehicleDriver.setDriverremark(argVehicleDriverReq.getDriverRemark());
		// 创建人
		argVehicleDriver.setCreator(argVehicleDriverReq.getCreator());
		//查询关键字
		argVehicleDriver.setQueryKey(argVehicleDriverReq.getQueryKey());
		// 创建时间
		if (argVehicleDriverReq.getCreateTime() != null) {
			argVehicleDriver.setCreatetime(Long.parseLong(argVehicleDriverReq.getCreateTime()));
		} else {
			argVehicleDriver.setCreatetime(null);
		}
	}
	
	/**
	 * 时间戳转换为"yyyy-MM-dd HH:mm:ss"格式
	 * <p>
	 * @param argTime
	 * @return
	 * <p>
	 * @author guyuke
	 * @time 2016年6月6日 上午10:01:36
	 */
	public String convertLongTODate(long argTime) {
		
		// 时间戳转换为时间格式
		Date dateTime = new Date(argTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return dateFormat.format(dateTime);
	}

	@Override
	public boolean getIFvehicleOwer(String id) {
		VehicleDriver ve = new VehicleDriver();
		ve.setCreator(id);
		long a = vehicleDriverMapper.selectCountByCondition(ve);
		if(a>0){
			return true;
		}
		return false;
	}
	
}
