package com.tianrui.api.admin.intf;

import java.util.List;

import com.tianrui.api.req.admin.RoleCopyReq;
import com.tianrui.api.req.admin.RoleDeleteReq;
import com.tianrui.api.req.admin.RoleGrankReq;
import com.tianrui.api.req.admin.RoleReq;
import com.tianrui.api.req.admin.RoleSaveReq;
import com.tianrui.api.req.admin.RoleUpdateReq;
import com.tianrui.api.resp.admin.RoleResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;


/**
 * 
 * @类描述：角色Service接口
 * @创建人：tank
 * @创建时间：2016年4月12日下午2:54:57
 *
 * @修改人：tank
 * @修改时间：2016年4月12日下午2:54:57
 * @修改备注：
 *
 */
public interface IRoleService {


	public PaginationVO<RoleResp> findRoleByPage(RoleReq req)throws Exception;
	//保存判断角色名称不能重复
	Result  saveRole(RoleSaveReq req)throws Exception;
	//修改判断角色名称不能重复
	Result  updateRole(RoleUpdateReq req)throws Exception;
	//判断绑定的角色不能删除
	Result  deleteRole(RoleDeleteReq req)throws Exception;
	//给制定的角色赋值菜单权限
	Result grantMenuWithRid(RoleGrankReq req)throws Exception;
	//角色复制
	Result copyRole(RoleCopyReq req)throws Exception;

	public List<RoleResp> findCondition(RoleReq req)throws Exception;
	
	//根据Id查询
	public RoleResp findOne(RoleReq req)throws Exception;
	//根据Id查询 返回包含菜单信息
	public RoleResp findOneWithMenu(RoleReq req)throws Exception;
	

}
