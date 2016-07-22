package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.api.resp.front.vehicle.MemberOwnerResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 我的车主接口类
 * <p>
 * @author guyuke
 * @time 2016年6月1日 下午2:15:37
 */
public interface IMemberOwnerService {
	
	/**
	 * 根据条件进行我的车主信息分页查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public PaginationVO<MemberOwnerResp> queryMyVehiOwnerByPage(MemberOwnerReq req) throws Exception;
	
	/**
	 * 根据条件进行我的车主信息查询
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public List<MemberOwnerResp> queryMyVehiOwnerByCondition(MemberOwnerReq req) throws Exception;
	
	/**
	 * 新增我的车主信息保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result insert(MemberOwnerReq req) throws Exception;
	
	/**
	 * 我的车主信息修改后保存操作
	 * <p>
	 * @param req
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result updateByPrimaryKeySelective(MemberOwnerReq req) throws Exception;
	
	/**
	 * 我的车主信息删除操作
	 * <p>
	 * @param id
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年6月1日 下午2:15:37
	 */
	public Result deleteByPrimaryKey(String id) throws Exception;
}
