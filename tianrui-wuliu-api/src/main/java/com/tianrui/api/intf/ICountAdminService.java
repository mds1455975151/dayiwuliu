package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.count.CountAdminReq;
import com.tianrui.api.resp.count.CountAdminResp;

/***
 * 
 * @类描述：统计管理
 * @创建人：lsj
 * @创建时间：2016年11月23日上午10:16:02
 *
 * @修改人：lsj
 * @修改时间：2016年11月23日上午10:16:02
 * @修改备注：
 *
 */
public interface ICountAdminService {

	List<CountAdminResp> find(CountAdminReq req)throws Exception;
	
	CountAdminResp findId(String id)throws Exception;
	
	boolean save(CountAdminReq req)throws Exception;
	
	boolean update(CountAdminReq req)throws Exception;
	
	boolean delete(String id)throws Exception;
}
