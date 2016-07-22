package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.FileCargoReq;
import com.tianrui.api.resp.admin.FileCargoResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 货物接口类
 * <p>
 * @author guyuke
 * @time 2016年5月22日 下午4:39:40
 */
public interface IFileCargoService {
	/**
	 * 根据条件进行货物信息查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午5:33:39
	 */
	public PaginationVO<FileCargoResp> queryCargoInfoByCondition(FileCargoReq req) throws Exception;
	/**
	 * 
	 * @描述:id查询
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 FileCargoResp
	 * @创建人 lsj
	 * @创建时间 2016年7月5日下午6:03:25
	 */
	public FileCargoResp findByid(String id) throws Exception;
	
	/**
	 * 新增货物信息保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月23日 下午12:31:18
	 */
	public Result insert(FileCargoReq req) throws Exception;
	
	/**
	 * 货物信息修改后保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月23日 下午12:31:18
	 */
	public Result updateByPrimaryKeySelective(FileCargoReq req) throws Exception;
	/**
	 * 
	 * @描述:批量停用
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年7月20日下午2:05:06
	 */
	boolean batchUpdateByPrimaryKeySelective(FileCargoReq req) throws Exception;
	/**
	 * 货物信息删除操作
	 * <p>
	 * @param id
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月23日 下午12:31:18
	 */
	public Result deleteByPrimaryKey(String id) throws Exception;
	
}

