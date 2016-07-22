package com.tianrui.api.admin.intf;

import java.util.List;

import com.tianrui.api.req.admin.FileOrgCargoReq;
import com.tianrui.api.resp.admin.FileOrgCargoResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 我的货物接口类
 * <p>
 * @author guyuke
 * @time 2016年5月22日 下午4:39:40
 */
public interface IFileOrgCargoService {
	/**
	 * 根据条件进行我的货物信息查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午5:33:39
	 */
	public PaginationVO<FileOrgCargoResp> queryMyCargoInfoByCondition(FileOrgCargoReq req) throws Exception;
	/**
	 * 
	 * @描述:根据条件进行我的货物信息查询/不分页
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 List<FileOrgCargoResp>
	 * @创建人 lsj
	 * @创建时间 2016年6月18日上午11:32:49
	 */
	public List<FileOrgCargoResp> queryMyCargoInfo(FileOrgCargoReq req) throws Exception;
	/**
	 * 新增我的货物信息保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月23日 下午12:31:18
	 */
	public Result insert(FileOrgCargoReq req) throws Exception;
	
	/**
	 * 我的货物信息修改后保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月23日 下午12:31:18
	 */
	public Result updateByPrimaryKeySelective(FileOrgCargoReq req) throws Exception;
	/**
	 * 
	 * @描述:批量修改
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年7月20日上午11:22:34
	 */
	boolean batchUpdateByPrimaryKeySelective(FileOrgCargoReq req) throws Exception;
	
	/**
	 * 我的货物信息删除操作
	 * <p>
	 * @param id
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月23日 下午12:31:18
	 */
	public Result deleteByPrimaryKey(String id) throws Exception;
	
	/**
	 * 我的货物信息删除操作
	 * <p>
	 * @param id
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月23日 下午12:31:18
	 */
	public Result findOne(String id) throws Exception;
	
}

