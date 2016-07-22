package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.vehicle.OwnerDriverReq;
import com.tianrui.api.req.front.vehicle.VehicleAndDriverReq;
import com.tianrui.api.resp.front.vehicle.OwnerDriverResp;
import com.tianrui.api.resp.front.vehicle.VehicleAndDriverResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 我的司机接口类
 * <p>
 * @author guyuke
 * @time 2016年6月1日 下午2:15:37
 */
public interface IOwnerDriverService {
	
	List<OwnerDriverResp> findOwnDriveById(String memberId) throws Exception;
	
	/**
	 * 
	 * @描述:
	 * @param id主键
	 * @return
	 * @throws Exception
	 * @返回类型 OwnerDriverResp
	 * @创建人 lsj
	 * @创建时间 2016年6月28日上午10:55:09
	 */
	OwnerDriverResp findById(String id)throws Exception;
	/**
	 * 根据条件进行我的未绑定车辆的司机信息查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月13日 下午2:15:37
	 */
	public List<OwnerDriverResp> queryMyDriverOutsideByCondition(OwnerDriverReq req) throws Exception;
	
	/**
	 * 根据条件进行我的司机信息分页查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public PaginationVO<OwnerDriverResp> queryMyDriverInfoByPage(OwnerDriverReq req) throws Exception;
	
	/**
	 * 根据条件进行我的司机信息查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public List<OwnerDriverResp> queryMyDriverInfoByCondition(OwnerDriverReq req) throws Exception;
	
	/**
	 * 根据条件进行我的司机车辆联合信息查询，我的司机为主LEFT JOIN车辆信息
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月15日 下午12:08:39
	 */
	public List<VehicleAndDriverResp> queryDriverAndVehicleByCondition(VehicleAndDriverReq req) throws Exception;
	
	/**
	 * 新增我的司机信息保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result insert(OwnerDriverReq req) throws Exception;
	
	/**
	 * 我的司机信息修改后保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result updateByPrimaryKeySelective(OwnerDriverReq req) throws Exception;
	
	/**
	 * 我的司机信息删除操作
	 * <p>
	 * @param id
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result deleteByPrimaryKey(String id) throws Exception;
	
}
