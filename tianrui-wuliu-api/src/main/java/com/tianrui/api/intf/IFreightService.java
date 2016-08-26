package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.cargoplan.FreightReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.cargoplan.FreightResp;
import com.tianrui.api.resp.front.cargoplan.FreightlistResp;
import com.tianrui.api.resp.front.cargoplan.FreightsResp;
import com.tianrui.common.vo.Result;

/**
 * 
 * @类描述：运价档案
 * @创建人：lsj
 * @创建时间：2016年5月23日上午10:44:59
 *
 * @修改人：lsj
 * @修改时间：2016年5月23日上午10:44:59
 * @修改备注：
 *
 */
public interface IFreightService {

	List<FreightResp> findByEntity(FreightReq req) throws Exception;
	
	/**
	 * 
	 * @描述:查询组织下符合符合的运价策略
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 List<FreightResp>
	 * @创建人 lsj
	 * @创建时间 2016年6月17日下午3:46:18
	 */
	List<FreightResp> findByStatus(FreightReq req) throws Exception;
	/**
	 * 
	 * @描述:后台管理—运价策略查询—分页
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 PageResp<FreightlistResp>
	 * @创建人 lsj
	 * @创建时间 2016年6月17日下午3:48:37
	 */
	PageResp<FreightlistResp> findByLisrEntity(FreightReq req) throws Exception;
	/**
	 * 
	 * @描述:根据策略名称获取策略ID来判定是否可以添加或者修改
	 * @param desc1Name
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年6月26日下午3:13:43
	 */
	String findByName(String desc1Name) throws Exception;
	
	FreightResp findById(String id) throws Exception;
	
	FreightsResp findsById(String id) throws Exception;
	
	boolean delctcByIdStr(String idStr)throws Exception;
	
	boolean saveEntity(FreightReq req)throws Exception;
	
	Result updateEntity(FreightReq req)throws Exception;
	Result delectEntity(FreightReq req)throws Exception;
	/**
	 * 
	 * @描述:批量修改
	 * @param req
	 * @throws Exception
	 * @返回类型 void
	 * @创建人 lsj
	 * @创建时间 2016年7月20日上午9:34:46
	 */
	boolean batchUpdate(FreightReq req)throws Exception;
}
