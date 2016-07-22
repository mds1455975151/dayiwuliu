package com.tianrui.api.admin.intf;

import java.util.List;

import com.tianrui.api.req.admin.OrganizationReq;
import com.tianrui.api.req.admin.OrganizationSaveReq;
import com.tianrui.api.req.admin.OrganizationUpdateReq;
import com.tianrui.api.req.admin.UserQueryReq;
import com.tianrui.api.req.admin.UserReq;
import com.tianrui.api.resp.admin.OrganizationResp;
import com.tianrui.api.resp.admin.UserResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
/**
 * 
* @ClassName: IOrganizationService
* @Description: 组织档案service接口
* @author wuchl
* @date 2016年5月20日
*
 */
public interface IOrganizationService {
	/**
	 * 
	* @Description: 组织档案保存方法
	* @param @param req
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年5月20日
	 */
	Result saveOrganization(OrganizationSaveReq req) throws Exception;
	/**
	 * 
	* @Description: 
	* @param @param req
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年5月20日
	 */
	Result updateOrganization(OrganizationUpdateReq req) throws Exception;
	/**
	 * 
	* @Description: 组织查询
	* @param @param req
	* @param @return
	* @param @throws Exception    
	* @return List<OrganizationUpdateReq>    
	* @throws
	* @author wuchl
	* @date 2016年5月20日
	 */
	public List<OrganizationResp> findCondition(OrganizationReq req)throws Exception;
	/**
	 * 
	* @Description: TODO
	* @param @param req
	* @param @return
	* @param @throws Exception    
	* @return PaginationVO<OrganizationResp>    
	* @throws
	* @author wuchl
	* @date 2016年5月21日
	 */
	PaginationVO<OrganizationResp> findOrgByPage(OrganizationReq req)throws Exception;
	/**
	 * 
	* @Description: TODO
	* @param @param userReq
	* @param @return
	* @param @throws Exception    
	* @return OrganizationResp    
	* @throws
	* @author wuchl
	* @date 2016年5月21日
	 */
	OrganizationResp findOne(String id)throws Exception;
	/**
	 * 
	* @Description: TODO
	* @param @param resp
	* @param @throws Exception    
	* @return void    
	* @throws
	* @author wuchl
	* @date 2016年5月21日
	 */
	void update(OrganizationResp resp) throws Exception;
	/**
	 * 
	 * @描述:删除组织
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年7月5日上午10:21:31
	 */
	boolean deleteOrg(String id)throws Exception;
}
