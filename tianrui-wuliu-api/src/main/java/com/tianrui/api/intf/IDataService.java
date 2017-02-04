package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.data.WebDictReq;
import com.tianrui.api.resp.data.WebDictResp;
/**
 * 查询车辆类型
 * @author jh
 *
 */
public interface IDataService {

	/**查询数据类型*/
	List<WebDictResp> find(WebDictReq req)throws Exception;
}
