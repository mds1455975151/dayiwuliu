package com.tianrui.api.intf;

import com.tianrui.api.req.front.api.APIPositionReq;
import com.tianrui.common.constants.ApiErrorCode;

/**
 * 安联对接接口
 * @author lixp 2016年12月30日 10:24:32
 */
public interface IApiPostionService {

	ApiErrorCode save(APIPositionReq req);
	
}
