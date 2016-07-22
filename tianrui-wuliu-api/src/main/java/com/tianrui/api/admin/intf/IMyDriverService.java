package com.tianrui.api.admin.intf;

import java.util.List;

import com.tianrui.api.req.admin.MyDriverReq;
import com.tianrui.api.resp.admin.MyDriverResp;

public interface IMyDriverService {
	/**
	 * 后台查询我的车辆
	 * @描述:
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 List<MyDriverResp>
	 * @创建人 lsj
	 * @创建时间 2016年6月28日上午9:57:47
	 */
	List<MyDriverResp> findByEntity(MyDriverReq req)throws Exception;
}
