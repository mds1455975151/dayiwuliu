package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.freight.AdminFreightReq;
import com.tianrui.api.resp.admin.freight.AdminFreightResp;
import com.tianrui.common.vo.PaginationVO;
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
	PaginationVO<AdminFreightResp> find(AdminFreightReq req) throws Exception;
}
