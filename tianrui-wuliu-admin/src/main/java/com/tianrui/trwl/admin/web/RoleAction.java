package com.tianrui.trwl.admin.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IRoleService;
import com.tianrui.api.req.admin.RoleCopyReq;
import com.tianrui.api.req.admin.RoleDeleteReq;
import com.tianrui.api.req.admin.RoleGrankReq;
import com.tianrui.api.req.admin.RoleReq;
import com.tianrui.api.req.admin.RoleSaveReq;
import com.tianrui.api.req.admin.RoleUpdateReq;
import com.tianrui.api.resp.admin.RoleResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;


/**
 * 
 * @类描述：管理系统角色
 * @创建人：tank
 * @创建时间：2016年4月12日下午12:00:55
 *
 * @修改人：tank
 * @修改时间：2016年4月12日下午12:00:55
 * @修改备注：
 *
 */
@Controller
@RequestMapping(value = "/role")
public class RoleAction {

	@Autowired
	private IRoleService roleService;
	/**
	 * 
	 * @描述:角色管理页面跳转
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月12日下午5:03:29
	 */
	@RequestMapping("/role")
	public ModelAndView user() throws IOException{
		return new ModelAndView("/system/role/role");
	}
	/**
	 * 
	 * @描述:查询系统全部角色
	 * @return
	 * @返回类型 List<Role>
	 * @创建人 tank
	 * @创建时间 2016年4月12日下午12:02:04
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public Result query() throws Exception{
		Result rs =Result.getSuccessResult();
		List<RoleResp> list = roleService.findCondition(new RoleReq());
		rs.setData(list);
		return rs;
	}
	/**
	 * 
	 * @描述:分页查询
	 * @param roleName
	 * @param roleNumber
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月13日下午2:41:10
	 */
	@RequestMapping("/queryByPage")
	@ResponseBody
	public Result queryRoleByPage(
			@RequestParam(defaultValue = "") String roleName,
			@RequestParam(defaultValue = "") String roleNumber,
			@RequestParam(defaultValue = "1") String pageNo,
			@RequestParam(defaultValue = "10") String pageSize
			) throws Exception{
		Result rs = Result.getSuccessResult();
		RoleReq req =new RoleReq();
		req.setRoleName(roleName);
		req.setRoleNumber(roleNumber);
		req.setPageNo(Integer.valueOf(pageNo));
		req.setPageSize(Integer.valueOf(pageSize));
		PaginationVO<RoleResp> pagination = roleService.findRoleByPage(req);
		
		rs.setData(pagination);

		return rs;
	}
	/**
	 * 
	 * @描述:添加角色
	 * @param Role
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月12日下午12:02:29
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(
			@RequestParam(defaultValue = "") String roleName,
			@RequestParam(defaultValue = "") String roleNumber,
			@RequestParam(defaultValue = "") String roleDescription,
			HttpServletRequest request) throws Exception {
		
		Users currUser =SessionManager.getSessionMember(request);
		
		RoleSaveReq req= new RoleSaveReq();
		req.setRoleName(roleName);
		req.setRoleNumber(roleNumber);
		req.setRoleDescription(roleDescription);
		req.setCreator(currUser.getAccount());
		
		Result rs = roleService.saveRole(req);
		return rs;
	}
	/**
	 * 
	 * @描述:修改角色
	 * @param roleName
	 * @param roleNumber
	 * @param roleDescription
	 * @param id
	 * @return
	 * @throws Exception 
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月13日上午10:36:59
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result updateRole(
			@RequestParam(defaultValue = "") String roleName,
			@RequestParam(defaultValue = "") String roleNumber,
			@RequestParam(defaultValue = "") String roleDescription,
            @RequestParam (defaultValue = "0") String id) throws Exception {
		
		RoleUpdateReq req= new RoleUpdateReq();
		req.setRoleName(roleName);
		req.setRoleNumber(roleNumber);
		req.setRoleDescription(roleDescription);
		req.setId(Integer.valueOf(id));
		
		Result rs = roleService.updateRole(req);
		return  rs;
	}

	/**
	 * 
	 * @描述:批量删除角色。id直接以 , 分割
	 * @param idstr
	 * @return
	 * @throws Exception 
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月13日上午10:41:54
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Result delete(@RequestParam(defaultValue = "")  String idstr) throws Exception {
		
		
		RoleDeleteReq req= new RoleDeleteReq();
		String[] ids = idstr.split(",");
		List<Integer> list =new ArrayList<Integer>();
		for(String id : ids){
			if( StringUtils.isNotBlank(id) ){
				list.add(Integer.valueOf(id));
			}
		}
		req.setIdList(list);
		
		Result rs = roleService.deleteRole(req);
		return rs;
	}
	/**
	 * 
	 * @描述:根据id获取角色
	 * @param id
	 * @return
	 * @throws Exception 
	 * @返回类型 Role
	 * @创建人 tank
	 * @创建时间 2016年4月13日上午10:43:52
	 */
	@RequestMapping(value = "/getById")
	@ResponseBody
	public Result getById(@RequestParam(defaultValue = "") String id) throws Exception {
		
		Result rs = Result.getSuccessResult();
		
		RoleReq req=new RoleReq();
		req.setId(Integer.valueOf(id));
		RoleResp roleResp =roleService.findOne(req);
		
		rs.setData(roleResp);
		return rs;
	}
	/**
	 * 
	 * @描述:根据角色ID对菜单权限进行赋权
	 * @param menuIds
	 * @param roleId
	 * @return
	 * @throws Exception 
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月13日下午3:13:12
	 */
	@RequestMapping(value = "/grantMenuPermissions",method = RequestMethod.POST)
	@ResponseBody
	public Result grantMenuPermissions(
			@RequestParam(defaultValue = "")  String menuIds,
			@RequestParam(defaultValue = "")  String roleId ) throws Exception{
		RoleGrankReq req =new RoleGrankReq();
		req.setRid(Integer.valueOf(roleId));
		
		String[] ids = menuIds.split(",");
		List<Integer> list =new ArrayList<Integer>();
		for(String id : ids){
			if( StringUtils.isNotBlank(id) ){
				list.add(Integer.valueOf(id));
			}
		}
		req.setmIds(list);
		
		Result rs=roleService.grantMenuWithRid(req);
		return rs;
	}
	
	/**
	 * 
	 * @描述:复制一个角色的权限
	 * @param roleName
	 * @param roleNumber
	 * @param roleDescription
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月13日下午6:30:10
	 */
	@RequestMapping(value = "/copy", method = RequestMethod.POST)
	@ResponseBody
	public Result copy(@RequestParam(defaultValue = "")  String roleName,
			@RequestParam(defaultValue = "") String roleNumber,
			@RequestParam(defaultValue = "") String roleDescription,
			@RequestParam (defaultValue = "0") String id,HttpServletRequest request) throws Exception {
		RoleCopyReq req =new RoleCopyReq();
		req.setId(Integer.valueOf(id));
		req.setRoleDescription(roleDescription);
		req.setRoleName(roleName);
		req.setRoleNumber(roleNumber);
		req.setCreator(SessionManager.getSessionMember(request).getAccount());
		
		Result rs=roleService.copyRole(req);
		return rs;
	}
}
