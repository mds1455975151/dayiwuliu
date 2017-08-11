package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.vehicle.VehicleDriverMemberReq;
import com.tianrui.api.req.front.vehicle.VehicleDriverReq;
import com.tianrui.api.resp.admin.MyVehicleResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverMemberResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 车辆司机关系接口类
 * <p>
 * @author guyuke
 * @time 2016年6月1日 下午2:15:37
 */
public interface IVehicleDriverService {
	
	/**
	 * 根据条件进行车辆司机关系信息分页查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public PaginationVO<VehicleDriverResp> queryVehiDriverByPage(VehicleDriverReq req) throws Exception;
	
	/**
	 * 根据条件进行车辆司机关系信息查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public List<VehicleDriverResp> queryVehiDriverByCondition(VehicleDriverReq req) throws Exception;
	
	/**
	 * 新增车辆司机关系信息保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result insert(VehicleDriverReq req) throws Exception;
	/** 已知安联账号，车辆司机绑定*/
	public Result anlianInsert(VehicleDriverReq req)throws Exception;
	
	/**
	 * 运力查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public PaginationVO<VehicleDriverMemberResp> find(VehicleDriverMemberReq req)throws Exception;
	/**
	 * 运力解绑
	 * @param vehicleId
	 * @return
	 * @throws Exception
	 */
	public Result unbundled(String id)throws Exception;
	/**
	 * 运力解绑详情
	 * @param vehicleId
	 * @return
	 * @throws Exception
	 */
	public VehicleDriverResp findUnbundledById(String id)throws Exception;
	/**
	 * @Title: bind 
	 * @Description: 车辆绑定
	 * @param @param id
	 * @param @return
	 * @param @throws Exception   
	 * @return Result    
	 * @throws
	 */
	public  Result bind(String id,String driverid,String type)throws Exception;
	/**
	 * 根据手机号搜索全平台司机
	 */
	public MemberResp bindDriver(String phone)throws Exception;
		
	/**
	 * 车辆司机关系信息修改后保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result updateByPrimaryKeySelective(VehicleDriverReq req) throws Exception;
	
	/**
	 * 车辆司机关系信息删除操作
	 * <p>
	 * @param id
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result deleteByPrimaryKey(String id) throws Exception;
	/**
	 * 
	 * @描述:判断是否为车主
	 * @param id 用户id
	 * @return
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年7月10日下午4:26:29
	 */
	boolean getIFvehicleOwer(String id);
}
