package com.tianrui.api.admin.intf;

import java.util.Date;
import java.util.List;

import com.tianrui.api.req.admin.freight.AdminFreightReq;
import com.tianrui.api.req.admin.freight.AdminFreightUptReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.freight.AdminFreightResp;
import com.tianrui.api.resp.admin.freight.FreightLineResp;
import com.tianrui.common.vo.Result;
/**
 * 
 * @类描述：运价策略审核
 * @创建人：lsj
 * @创建时间：2016年8月24日上午9:58:21
 *
 * @修改人：lsj
 * @修改时间：2016年8月24日上午9:58:21
 * @修改备注：
 *
 */
public interface IFreightInfoService {
	/** 审核运价策略查询*/
	PageResp<AdminFreightResp> find(AdminFreightReq req) throws Exception;
	
	Result update(AdminFreightUptReq req)throws Exception;
	/** 查询运价变化折线图*/
	List<FreightLineResp> lineChart(AdminFreightReq req) throws Exception;
	/** 查询运价策略
	 * id:freight的id
	 *  Date:yyyy-MM-dd
	 *  */
	Result findFreightInfo(String id,Date date)throws Exception;
}
