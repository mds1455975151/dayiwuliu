package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.SystemlogReq;
import com.tianrui.api.resp.admin.SystemlogResp;
import com.tianrui.common.vo.PaginationVO;

/**
 * 系统日志管理service接口
 * @author tank
 * @date 2016-01-12
 *
 */
public interface ISystemLogService {

	PaginationVO<SystemlogResp> findSystemLogByPage(SystemlogReq req);

}
