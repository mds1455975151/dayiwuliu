package com.tianrui.api.intf;

import com.tianrui.api.req.front.vehicle.TicketFindReq;
import com.tianrui.api.req.front.vehicle.VehicleTicketReq;
import com.tianrui.api.resp.front.vehicle.VehicleTicketResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 前台开票认证
 * @author jh
 *
 */
public interface IVehicleTicketService {

	/** 前台申请开票认证*/
	public Result insert(VehicleTicketReq req)throws Exception;
	
	/** 后台申请开票认证*/
	public Result save(VehicleTicketReq req)throws Exception;
	/** 申请开票认证修改*/
	public Result upt(VehicleTicketReq req)throws Exception;
	/** 查询开票认证车辆
	 * pageNo == null 不分页
	 * pageNo != null 分页
	 * */
	public PaginationVO<VehicleTicketResp> page(TicketFindReq req)throws Exception;
	/** 开票审核*/
	public Result shenhe(VehicleTicketReq req)throws Exception;
	/**车辆id查询开票记录*/
	public Result findByVehicleId(TicketFindReq req) throws Exception;
}
