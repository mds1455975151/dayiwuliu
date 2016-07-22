package com.tianrui.api.admin.intf;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.tianrui.api.req.admin.OrgMemberReq;
import com.tianrui.api.resp.admin.OrgMemberResp;
import com.tianrui.api.resp.admin.PageResp;

public interface IOrgMemberService {

	/**
	 * 
	 * @描述:添加关系
	 * @param req
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年6月6日下午5:11:42
	 */
	boolean saveEntity(OrgMemberReq req) throws Exception;
	/**
	 * 
	 * @描述:查询我的会员
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 List<OrgMemberResp>
	 * @创建人 lsj
	 * @创建时间 2016年6月7日上午9:09:25
	 */
	PageResp<OrgMemberResp> findByEntity(OrgMemberReq req) throws Exception;
	/**
	 * 
	 * @描述:查询我的会员
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 List<OrgMemberResp>
	 * @创建人 lsj
	 * @创建时间 2016年7月5日上午10:39:12
	 */
	List<OrgMemberResp> findlist(OrgMemberReq req) throws Exception;
	/**
	 * 
	 * @描述:id查询
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 OrgMemberResp
	 * @创建人 lsj
	 * @创建时间 2016年6月22日下午5:43:38
	 */
	OrgMemberResp findById(String id)throws Exception;
	/**
	 * 
	 * @描述:修改我的会员
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年6月7日上午10:27:35
	 */
	boolean updateEntity(OrgMemberReq req)throws Exception;
	/**
	 * 
	 * @描述:删除
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年6月7日下午2:31:37
	 */
	boolean deleteEntity(String id)throws Exception;
}
