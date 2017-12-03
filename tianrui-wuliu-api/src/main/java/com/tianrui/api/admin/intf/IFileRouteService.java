package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.FileRouteReq;
import com.tianrui.api.req.admin.FileRouteSaveReq;
import com.tianrui.api.req.admin.FileRouteUpdateReq;
import com.tianrui.api.resp.admin.FileRouteResp;
import com.tianrui.api.resp.admin.RoutePosition;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 路径档案服务
 * @author Administrator
 *
 */
public interface IFileRouteService {

	/**
	 * 分页查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	PaginationVO<FileRouteResp> findAllPage(FileRouteReq req)throws Exception;
	
	/**
	 * 新增加路径
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result saveFileRoute(FileRouteSaveReq req)throws Exception;
	/** 通过route id 获取起运地 目的地信息*/
	RoutePosition getPositionByRouteId(String id)throws Exception;
	
	/**
	 * 修改路径
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result updateFileRoute(FileRouteUpdateReq req)throws Exception;
	
	/**
	 * 启用 停用路径
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result delFileRoute(FileRouteReq req)throws Exception;
	/**
	 * 
	  * @Title: detailFileRoute 
	  * @Description: 获取详情 
	  * @param 
	  * @return Result   
	  * @throws 
	  *
	 */
	Result detailFileRoute(FileRouteReq req) throws Exception ;
	/**
	 * 
	 * @描述:删除
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年7月6日上午9:52:24
	 */
	boolean delete(String id)throws Exception ;
}

