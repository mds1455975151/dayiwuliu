package com.tianrui.api.intf;

import com.tianrui.api.resp.front.version.AppVersionResp;
/**
 * 
 * @类描述：app版本控制
 * @创建人：lsj
 * @创建时间：2016年8月22日上午10:53:05
 *
 * @修改人：lsj
 * @修改时间：2016年8月22日上午10:53:05
 * @修改备注：
 *
 */
public interface IAppVersionService {
	/** 查询app版本 appType = ios / android*/
	AppVersionResp selectByid(String appType)throws Exception;
}
