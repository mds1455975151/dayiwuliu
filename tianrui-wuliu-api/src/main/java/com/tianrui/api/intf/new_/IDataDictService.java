
/**
 * @标题: IDataDictService.java
 * @功能描述：TODO
 * @作者： lsj
 * @创建时间： 2016年5月4日 下午3:35:22
 */

package com.tianrui.api.intf.new_;

import java.util.List;

import com.tianrui.api.req.front.system.DataDictReq;
import com.tianrui.api.resp.common.DataDictResp;



/**
 * @类描述：
 * @创建人：lsj
 * @创建时间：2016年5月4日下午3:35:22
 *
 * @修改人：lsj
 * @修改时间：2016年5月4日下午3:35:22
 * @修改备注：
 *
 */

public interface IDataDictService  {

	/**
	 * 
	 * @描述:查询数据列表
	 * @param subCode 数据编号
	 * @return
	 * @返回类型 List<DataDict>
	 * @创建人 lsj
	 * @创建时间 2016年5月4日下午3:45:15
	 */
	List<DataDictResp> findDictList(DataDictReq req)throws Exception;
	/**
	 * 
	 * @描述:精确查询数据字典
	 * @param subCode
	 * @param itemCode
	 * @return
	 * @返回类型 S
	 * @创建人 lsj
	 * @创建时间 2016年5月4日下午4:13:42
	 */
	DataDictResp findDict(DataDictReq req)throws Exception;
}
