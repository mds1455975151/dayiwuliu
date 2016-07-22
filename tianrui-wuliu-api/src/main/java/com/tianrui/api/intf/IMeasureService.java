
/**
 * @标题: IMeasureService.java
 * @功能描述：TODO
 * @作者： lsj
 * @创建时间： 2016年5月6日 上午8:53:42
 */

package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.admin.MeasureCountReq;
import com.tianrui.api.req.admin.MeasureReq;
import com.tianrui.api.resp.admin.MeasureResp;
import com.tianrui.common.vo.Result;



/**
 * @类描述：
 * @创建人：lsj
 * @创建时间：2016年5月6日上午8:53:42
 *
 * @修改人：lsj
 * @修改时间：2016年5月6日上午8:53:42
 * @修改备注：
 *
 */

public interface IMeasureService {
	/**
	 * 修改Measure
	 */
	Result update(MeasureReq req)throws Exception;
	/**
	 * 查询measure
	 * @描述:
	 * @param measure
	 * @return
	 * @返回类型 List<Measure>
	 * @创建人 lsj
	 * @创建时间 2016年5月7日上午8:42:32
	 */
	List<MeasureResp> findByEntityList(MeasureReq req)throws Exception;
	/**
	 * 
	 * @描述:mesure模糊查询
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 List<MeasureResp>
	 * @创建人 lsj
	 * @创建时间 2016年5月19日下午4:49:23
	 */
	List<MeasureResp> findMeasureBlur(MeasureReq req)throws Exception;
	/**
	 * 
	 * @描述:measure  id查询
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 MeasureResp
	 * @创建人 lsj
	 * @创建时间 2016年5月21日上午8:51:06
	 */
	MeasureResp findMeasureById(String id)throws Exception;
	/**
	 * 添加
	 * @描述:
	 * @param req
	 * @return
	 * @返回类型 int
	 * @创建人 lsj
	 * @创建时间 2016年5月16日下午3:34:59
	 */
	Result insert(MeasureReq req)throws Exception;
	/**
	 * 
	 * @描述:添加联合量纲
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月17日上午9:05:39
	 */
	Result saveMain(MeasureReq req)throws Exception;
	/**
	 * 
	 * @描述:同类量纲计算
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月17日上午9:50:37
	 */
	Result similarCalculation(MeasureCountReq req)throws Exception;
	/**
	 * 
	 * @描述:通过id删除measure
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年5月20日上午10:56:54
	 */
	Result delectMeasureById(String id)throws Exception;
	/**
	 * 
	 * @描述:MeasonMain非空查询
	 * @return
	 * @throws Exception
	 * @返回类型 List<MeasureResp>
	 * @创建人 lsj
	 * @创建时间 2016年5月20日下午2:31:10
	 */
	List<MeasureResp> findMeasonMainNotNull()throws Exception;
}
