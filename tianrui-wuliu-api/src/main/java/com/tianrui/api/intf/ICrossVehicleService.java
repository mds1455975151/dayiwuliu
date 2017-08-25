package com.tianrui.api.intf;

import com.tianrui.api.req.admin.ZJXLVehicleReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.ZJXLVehicleResp;
import com.tianrui.common.vo.Result;

public interface ICrossVehicleService {
	/**
	 * 中交车辆管理列表查询
	 * @Title: findCrossVehicleList 
	 * @Description: TODO
	 * @param @param req
	 * @param @return
	 * @param @throws Exception   
	 * @return List<DataDictResp>    
	 * @throws
	 */
	PageResp<ZJXLVehicleResp> find(ZJXLVehicleReq req)throws Exception;
	
	Result insert(ZJXLVehicleReq req)throws Exception;
}
