package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.count.RouteTableReq;
import com.tianrui.api.resp.count.RouteTableResp;

public interface ICountRouteTableService {

	List<RouteTableResp> find(RouteTableReq req)throws Exception;
	
	boolean update(RouteTableReq req)throws Exception;
}
