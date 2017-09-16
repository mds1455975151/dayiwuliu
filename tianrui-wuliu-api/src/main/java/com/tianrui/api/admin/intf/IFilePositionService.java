package com.tianrui.api.admin.intf;

import java.util.List;

import com.tianrui.api.req.admin.FilePositionReq;
import com.tianrui.api.resp.admin.FilePositionResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
  * @ClassName: IPositionService 
  * @Description: 位置服务
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年5月25日 上午9:19:43 
  *
 */
public interface IFilePositionService {

	/**
	 * 分页查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	PaginationVO<FilePositionResp> findAllPage(FilePositionReq req)throws Exception;
	
	/**
	 * 新增加路径
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result saveFilePosition(FilePositionReq req)throws Exception;
	
	
	/**
	 * 修改路径
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result updateFilePosition(FilePositionReq req)throws Exception;
	
	/**
	 * 修改路径
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result delFilePosition(FilePositionReq req)throws Exception;
	/**
	 * 查看路径
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result detailFilePosition(FilePositionReq req)throws Exception;
	/**
	 * 
	 * @描述:删除路径
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年7月6日上午9:30:46
	 */
	boolean deletePosition(String id)throws Exception;
	
	/**
	  * @Title: findWithCondition 
	  * @Description:  根据条件查询
	  * @param 
	  * @return List<FilePositionResp>   
	  * @throws 
	  *
	 */
	List<FilePositionResp> findWithCondition(FilePositionReq req)throws Exception;
	/**
	 * 查看详细地点
	 * @Title: selectRountename 
	 * @Description: TODO
	 * @param @param req
	 * @param @return
	 * @param @throws Exception   
	 * @return Result    
	 * @throws
	 */
	Result selectRountename(FilePositionReq req)throws Exception;
}

