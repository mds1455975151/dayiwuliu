package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.vehicle.MemberVehicleReq;
import com.tianrui.api.req.front.vehicle.VehicleAndDriverReq;
import com.tianrui.api.req.front.vehicle.VehicleOnlyReq;
import com.tianrui.api.resp.front.vehicle.MemberVehicleResp;
import com.tianrui.api.resp.front.vehicle.VehicleAndDriverResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 我的车辆接口类
 * <p>
 * @author guyuke
 * @time 2016年6月1日 下午2:15:37
 */
public interface IMemberVehicleService {
	
	
	/**
	 * 
	 * @描述:通过id查询车辆审核未通过信息
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 MemberVehicleResp
	 * @创建人 lsj
	 * @创建时间 2016年6月25日下午3:52:51
	 */
	MemberVehicleResp findMemoMassageById(String id)throws Exception;
	/**
	 * 根据主键进行我的车辆信息查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月7日 下午2:15:37
	 */
	public MemberVehicleResp queryMyVehicleInfoById(String id) throws Exception;
	
	/**
	 * 根据条件进行我的车辆信息分页查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public PaginationVO<MemberVehicleResp> queryMyVehicleInfoByPage(MemberVehicleReq req) throws Exception;
	
	/**
	 * 根据条件进行我的车辆信息查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public List<MemberVehicleResp> queryMyVehicleInfoByCondition(MemberVehicleReq req) throws Exception;
	
	/**
	 * 根据条件进行我的车辆信息查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public PaginationVO<VehicleAndDriverResp> queryVehicleAndDriverByCondition(VehicleAndDriverReq req) throws Exception;
	
	/**
	 * 新增我的车辆信息保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result insert(MemberVehicleReq req) throws Exception;
	
	/**
	 * 我的车辆信息修改后保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result updateByPrimaryKeySelective(MemberVehicleReq req) throws Exception;
	/**
	 * 
	 * @描述:修改车辆运输状态
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年7月9日下午2:48:46
	 */
	public Result updateVehiclebillStatus(MemberVehicleReq req)throws Exception;
	/**
	 * 我的车辆信息删除操作
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
     * 根据vehicleIds进行车辆司机联合信息查询
     * <p>
     * @param vehicleIds
     * @return
     * <p>
     * @author zhanggh
     * @time 2016年7月28日 上午11:33:00
     */
	public List<MemberVehicleResp> selectVehicleByIds(List<String> vehicleIds) throws Exception;
	/** 验证车牌号唯一*/
	public Result vehicleNOByOnly(VehicleOnlyReq req)throws Exception;
}
