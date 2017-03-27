package com.tianrui.api.admin.intf;

import java.util.List;

import com.tianrui.api.req.admin.MyVehicleReq;
import com.tianrui.api.resp.admin.MyVehicleResp;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.common.vo.Result;
/**
 * 
 * @类描述：平台车辆管理
 * @创建人：lsj
 * @创建时间：2016年6月4日上午10:22:44
 *
 * @修改人：lsj
 * @修改时间：2016年6月4日上午10:22:44
 * @修改备注：
 *
 */
public interface IMyVehicleService {

	PageResp findByEntity(MyVehicleReq req)throws Exception;
	
	MyVehicleResp findById(String id)throws Exception;
	
	Result updateVehicle(MyVehicleReq req)throws Exception;
}
