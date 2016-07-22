package com.tianrui.trwl.admin.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IRoleService;
import com.tianrui.api.admin.intf.IUserService;
import com.tianrui.api.req.admin.RoleReq;
import com.tianrui.api.req.admin.UserDeleteReq;
import com.tianrui.api.req.admin.UserLoginReq;
import com.tianrui.api.req.admin.UserQueryReq;
import com.tianrui.api.req.admin.UserReq;
import com.tianrui.api.req.admin.UserSaveReq;
import com.tianrui.api.req.admin.UserUpdatePassReq;
import com.tianrui.api.req.admin.UserUpdateReq;
import com.tianrui.api.resp.admin.RoleResp;
import com.tianrui.api.resp.admin.UserResp;
import com.tianrui.common.utils.Md5Utils;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;


/**
 * 
 * @类描述：后台管理系统用户管理模块web响应
 * @创建人：tank
 * @创建时间：2016年4月14日下午5:34:03
 *
 * @修改人：tank
 * @修改时间：2016年4月14日下午5:34:03
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/user")
public class UserAction {
	
	@Resource
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	/**
	 * 
	 * @描述:系统首页页面跳转
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年6月12日下午4:45:12
	 */
	@RequestMapping("/index")
	public ModelAndView index() throws IOException{
		return new ModelAndView("/system/index");
	}
	
	/**
	 * 
	 * @描述:用户管理页面跳转
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月15日上午9:45:12
	 */
	@RequestMapping("/user")
	public ModelAndView user() throws IOException{
		return new ModelAndView("/system/user/user");
	}
	
	/**
	 * 
	 * @描述:跳转后台登录界面
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月15日上午9:45:28
	 */
	@RequestMapping("/login")
	public ModelAndView login() throws IOException{
		return new ModelAndView("login");
	}
	
	/**
	 * 
	 * @描述:保存用户
	 * @param username 账号
	 * @param name 姓名
	 * @param status 状态 0-禁用，1-有效
	 * @param tel 手机号
	 * @param roleIds 角色Id字符串 , 号分割
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月14日上午10:30:46
	 * @modifier guyuke
	 * @modifyTime 2016年5月27日上午11:25:00
	 */
	@RequestMapping("/savevUser")
	@ResponseBody
	public Result save(@RequestParam(defaultValue = "") String username,
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String status,
			@RequestParam(defaultValue = "") String tel,
			@RequestParam(defaultValue = "") String roleIds,
			@RequestParam(defaultValue = "") String orgId,
			@RequestParam(defaultValue = "") String orgName) throws Exception{
		UserSaveReq req =new  UserSaveReq();
		req.setPassword(Md5Utils.MD5("666666"));
		req.setAccount(username);
		req.setStatus(Integer.parseInt(status));
		req.setCreatetime(com.tianrui.common.utils.DateUtil.getDateString());
		req.setUsertype(name);
		req.setTelnum(tel);
		// 组织ID
		req.setOrgid(orgId);
		// 组织名称
		req.setOrgname(orgName);
		List<Integer> ids = null;
		if( StringUtils.isNotBlank(roleIds) ){
			ids = new ArrayList<Integer>();
			for( String str : roleIds.split(",")){
				if(StringUtils.isNotBlank(str)) ids.add(Integer.valueOf(str));
			}
		}
		req.setRoleIds(ids);
		Result rs = userService.saveUser(req);
		
		return rs;
	}
	
	/**
	 * 
	 * @描述 分页查询系统用户
	 * @param username
	 * @param name
	 * @param tel
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月15日上午9:43:07
	 * @modifier guyuke
	 * @modifyTime 2016年5月27日上午11:25:00
	 */
	@RequestMapping("/queryUser")
	@ResponseBody
	public Result query(@RequestParam(defaultValue = "") String username,
				@RequestParam(defaultValue = "") String name,
				@RequestParam(defaultValue = "") String tel,
				@RequestParam(defaultValue = "") String orgId,
				@RequestParam(defaultValue = "1") String pageNo,
				@RequestParam(defaultValue = "10") String pageSize
			) throws Exception{
		Result rs = Result.getSuccessResult();
		UserQueryReq req=new UserQueryReq();
		req.setUsername(username);
		req.setName(name);
		req.setTel(tel);
		// 组织ID
		req.setOrgid(orgId);
		req.setPageNo(Integer.valueOf(pageNo));
		req.setPageSize(Integer.valueOf(pageSize));
		PaginationVO<UserResp> pagination = userService.findUserByPage(req);
		rs.setData(pagination);

		return rs;
	}
	
	/**
	 * 
	 * @描述:后台系统登录
	 * @param account  账户
	 * @param passWord  密码
	 * @param pass  验证码
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月15日上午9:49:34
	 */
	@RequestMapping(value="/loginin",method=RequestMethod.POST)
	@ResponseBody
	public Result loginin(@RequestParam(defaultValue = "") String account,
			@RequestParam(defaultValue = "") String passWord,
			@RequestParam(defaultValue = "") String pass,HttpServletRequest request) throws Exception {
		
		UserLoginReq req =new UserLoginReq();
		req.setAccount(account);
		req.setPassword(passWord);
		req.setAuthCode(pass);
		
		Result result =userService.login(req);
		if( result !=null && StringUtils.equals(result.getCode(), "000000") ){
			
			Users user =(Users)result.getData();
			SessionManager.setSessionMember(request, user);
		}
		return result;
	}
	
	/**
	 * 用户注销（返回登录页面）
	 * 
	 */
	@RequestMapping("/loginOff")
	public ModelAndView loginOff(HttpServletRequest request) throws IOException{
		SessionManager.setSessionMember(request, null);
		return new ModelAndView("/login");
	}
	
	/**
	 * 
	 * @描述:删除系统用户
	 * @param idstr  , 号分割
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月15日上午9:48:13
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Result deleteUser(@RequestParam(defaultValue = "") String idstr) throws Exception {
		UserDeleteReq req =new UserDeleteReq();
		String[] strArr =idstr.split(",");
		List<Integer> ids =new ArrayList<Integer>();
		for(String str:strArr){
			if( StringUtils.isNotBlank(str) ){
				ids.add(Integer.valueOf(str));
			}
		}
		req.setIds(ids);
		Result rs =userService.deleteUsers(req);
		return rs;
	}
	
	/**
	 * 
	 * @描述:获取用户已拥有的角色和系统全部角色
	 * @param id 用户id
	 * @return
	 * @throws Exception
	 * @返回类型 String  own 0-未拥有；1-已拥有
	 * @创建人 tank
	 * @创建时间 2016年4月15日上午11:54:16
	 */
	@RequestMapping("/getUserRole")
	@ResponseBody
	public Result getUserRole(@RequestParam(defaultValue = "") String id) throws Exception {
		Result rs =Result.getSuccessResult();
		List<RoleResp> roleList = roleService.findCondition(new RoleReq());
		
		UserReq query = new UserReq();
		query.setId(Integer.valueOf(id));
		UserResp resp =userService.findOneWithRole(query);
		
		for( RoleResp roleResp : roleList  ){
			roleResp.setOwn(0);
			if( resp !=null && CollectionUtils.isNotEmpty(resp.getRoleList()) ){
				for(RoleResp roleRespItem:resp.getRoleList() ){
					if( roleRespItem.equals(roleResp) ){
						roleResp.setOwn(1);
						break;
					}
				}
			}
		}
		rs.setData(roleList);
		return rs;
		
	}
	/**
	 * 
	 * @描述:获取用户所拥有的角色
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 tank
	 * @创建时间 2016年6月23日下午4:38:27
	 */
	@RequestMapping("/getRoleByUserID")
	@ResponseBody
	public Result getRoleByUserID(@RequestParam(defaultValue = "") String id) throws Exception {
		Result rs =Result.getSuccessResult();
		
		UserReq query = new UserReq();
		query.setId(Integer.valueOf(id));
		UserResp resp =userService.findOneWithRole(query);
		if( resp !=null && CollectionUtils.isNotEmpty(resp.getRoleList()) ){
			rs.setData(resp.getRoleList());
		}
		return rs;
		
	}
	
	/**
	 * 
	 * @描述:管理员修改系统用户
	 * @param username
	 * @param name
	 * @param status
	 * @param tel
	 * @param id 用户id
	 * @param ifReset 是否重置密码 0-否  1 -是
	 * @param roleIds  角色id字符串  , 号分割
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月15日上午11:04:40
	 */
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ResponseBody
	public Result updateUser(
			@RequestParam(defaultValue = "") String username,
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String status,
			@RequestParam(defaultValue = "") String tel,
			@RequestParam(defaultValue = "") String id,
			@RequestParam(defaultValue = "") String ifReset,
			@RequestParam(defaultValue = "") String roleIds) throws Exception {
		UserUpdateReq req =new UserUpdateReq();
		req.setId(Integer.valueOf(id));
		req.setAccount(username);
		req.setUsertype(name);
		req.setStatus(Integer.valueOf(status));
		req.setTelnum(tel);
		
		if( StringUtils.equals(ifReset,"1") ){
			req.setPassword(Md5Utils.MD5("666666"));
		}
		
		List<Integer> ids =null;
		if( StringUtils.isNotBlank(roleIds) ){
			ids =new ArrayList<Integer>();
			for( String str : roleIds.split(",")){
				if(StringUtils.isNotBlank(str)) ids.add(Integer.valueOf(str));
			}
		}
		req.setrIdList(ids);
		
		Result rs = userService.updataUser(req);
		
		return rs;
		
	}
	
	/**
	 * 
	 * @描述:根据登录账户获取验证码
	 * @param userName
	 * @param request
	 * @return
	 * @throws Exception
	 * @返回类型 Map<String,Object>
	 * @创建人 tank
	 * @创建时间 2016年4月15日上午9:46:50
	 */
	@RequestMapping(value = "/getValCode.json",method=RequestMethod.POST)
	@ResponseBody
	public Result getValCode(@RequestParam(defaultValue = "") String userName,HttpServletRequest request
								) throws Exception{
	
		UserReq req =new UserReq();
		req.setUserName(userName);
		Result rs =userService.sendMsgCode(req);
		return rs;
	}
	
	/**
	 * 
	 * @描述:修改本人密码
	 * @param passWord
	 * @param request
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 tank
	 * @创建时间 2016年6月20日下午12:49:46
	 */
	@RequestMapping("/updatemyPass")
	@ResponseBody
	public Result updatemyPass(@RequestParam(defaultValue = "") String passWord,
			HttpServletRequest request) throws Exception {
		Users user =SessionManager.getSessionMember(request);
		UserUpdatePassReq passReq =new UserUpdatePassReq();
		if(passWord != ""){
			passReq.setPassWord(Md5Utils.MD5(passWord));
		}
		passReq.setId(user.getId());
		Result rs =userService.updataPass(passReq);
		if( rs !=null && StringUtils.equals(rs.getCode(), "000000") ){
			user.setPassword(Md5Utils.MD5(passWord));
		}
		return rs;
	}
	/**
	 * 
	 * @描述:修改用户基本信息
	 * @param passWord
	 * @param username
	 * @param request
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 tank
	 * @创建时间 2016年6月20日下午1:42:47
	 */
	@RequestMapping("/updatemyUser")
	@ResponseBody
	public Result updatemyUser(@RequestParam(defaultValue = "") String tel,
			@RequestParam(defaultValue = "") String username,
			HttpServletRequest request) throws Exception {
		Users user =SessionManager.getSessionMember(request);
		UserUpdateReq passReq =new UserUpdateReq();
		if(tel != ""){
			passReq.setTelnum(tel);
		}
		if(username != ""){
			passReq.setUsertype(username);;
		}
		passReq.setId(user.getId());
		Result rs =userService.updataUser(passReq);
		if( rs !=null && StringUtils.equals(rs.getCode(), "000000") ){
			user.setTelnum(tel);
			user.setUsertype(username);
		}
		return rs;
	}
}
