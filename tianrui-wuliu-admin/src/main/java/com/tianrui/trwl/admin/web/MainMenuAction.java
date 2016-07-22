package com.tianrui.trwl.admin.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IMainMenuService;
import com.tianrui.api.admin.intf.IRoleService;
import com.tianrui.api.req.admin.MainMenuGrantReq;
import com.tianrui.api.req.admin.MainMenuReq;
import com.tianrui.api.req.admin.MainMenuSaveReq;
import com.tianrui.api.req.admin.MainMenuUpdateReq;
import com.tianrui.api.req.admin.RoleReq;
import com.tianrui.api.resp.admin.MainMenuResp;
import com.tianrui.api.resp.admin.RoleResp;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;


/**
 * 
 * @类描述：后台系統菜单树结构
 * @创建人：tank
 * @创建时间：2016年4月11日下午2:52:43
 *
 * @修改人：tank
 * @修改时间：2016年4月11日下午2:52:43
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/mainMenu")
public class MainMenuAction  {

    @Autowired
    private IMainMenuService menuService;
    @Resource
	private IRoleService roleService;
    
    static Logger logger =LoggerFactory.getLogger(MainMenuAction.class);
    
    /**
	 * 
	 * @描述:菜单管理页面跳转
	 * @return
	 * @throws IOException
	 * @返回类型 ModelAndView
	 * @创建人 tank
	 * @创建时间 2016年4月12日下午5:03:29
	 */
	@RequestMapping("/menu")
	public ModelAndView user() throws IOException{
		return new ModelAndView("/system/menu/menu");
	}
    /**
     * 
     * @描述:根据角色id获取菜单树 desc2 为yes 为拥有的权限菜单
     * @param RoleId
     * @return
     * @throws Exception 
     * @返回类型 Map<String,Object> Spring 框架自动转换为json字符串
     * @创建人 tank
     * @创建时间 2016年4月13日下午5:04:07
     */
	@RequestMapping(value = "/getMenuByRoleId",method = RequestMethod.POST)
	@ResponseBody
	public Result getMenuByRoleId(
			@RequestParam(defaultValue = "")  String roleId ) throws Exception{
		Result rs =Result.getSuccessResult();
		
		//获取该角色包含的子菜单  以及角色
		RoleReq req =new RoleReq();
		req.setId(Integer.valueOf(roleId));
		RoleResp roleResp =roleService.findOneWithMenu(req);
		
		List<MainMenuResp> menuListUser =null;
		if( roleResp !=null ){
			menuListUser =roleResp.getMenuRespList();
		}
		// 获取所有的2级节点 一级子节点
		List<MainMenuResp> menuListAll=null;
		try {
			menuListAll = menuService.findAllMenu(new MainMenuReq());
		} catch (Exception e) {
			logger.error("{}",e.getMessage(),e);
		}
		if( CollectionUtils.isNotEmpty(menuListAll) ){
			for( MainMenuResp item:menuListAll ){
				//该角色是否包含该权限
				if( CollectionUtils.isNotEmpty(menuListUser)  ){
					for(MainMenuResp itemResp:menuListUser  ){
						if( itemResp.getId() == item.getId() ){
							item.setDesc2("yes");
							break;
						}
					}
				}
				//循环子节点  及第三层
				if( CollectionUtils.isNotEmpty(item.getNodeList())  ){
					for( MainMenuResp nodeItem:item.getNodeList() ){
						//该角色是否包含该权限
						if( CollectionUtils.isNotEmpty(menuListUser)  ){
							for(MainMenuResp itemResp:menuListUser  ){
								if( itemResp.getId() == nodeItem.getId() ){
									nodeItem.setDesc2("yes");
									break;
								}
							}
						}
					}
				}
			}
		}
		rs.setData(menuListAll);
        return rs;
		
	}
    /**
     * 
     * @描述:根据登录用户的权限获取后台系统菜单树
     * @return
     * @throws Exception 
     * @返回类型 Map<String,Object> Spring 框架自动转换为json字符串
     * @创建人 tank
     * @创建时间 2016年4月11日下午2:57:13
     */
    @RequestMapping("/queryMainMenu.json")
    @ResponseBody
    public Result queryMainMenu(HttpServletRequest request) throws Exception {
    	Result rs =Result.getSuccessResult();
    	Users user =SessionManager.getSessionMember(request);
    	if( user !=null  ){
    		MainMenuReq req =new MainMenuReq();
    		List<MainMenuResp> list =new ArrayList<MainMenuResp>();
    		if( StringUtils.equals(user.getAccount(), "admin") ){
    			list=menuService.findAllMenu(req);
    		}else{
    			//系统首页 菜单id为4，为所有用户增加首页权限
    			req.setId(4);
    			list.add(menuService.findOne(req));
    			req.setUserId(user.getId());
    			if(CollectionUtils.isNotEmpty(menuService.findWithUser(req))){
    				list.addAll(menuService.findWithUser(req));
    			}
    		}
    		rs.setData(list);
    	}
        return rs;
    }
    /**
     * 
     * @描述:菜单管理 获得所有菜单
     * @return
     * @返回类型 Map<String,Object>
     * @创建人 tank
     * @创建时间 2016年4月16日上午9:27:49
     */
    @RequestMapping("/listAll.json")
    @ResponseBody
    public Result listAll() {
    	Result rs =Result.getSuccessResult();
    	
    	// 获取所有菜单权限 并初始化desc1为no desc2 为no
    	List<MainMenuResp> menuListAll=null;
		try {
			menuListAll = menuService.findAllMenu(null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	rs.setData(menuListAll);
        return rs;
    }
    
    
    /**
     * 
     * @描述:添加菜单
     * @param parentMenuId 父菜单ID ,系统根菜单为1
     * @param menuName
     * @param url
     * @param order  显示顺序，正序
     * @return
     * @throws Exception
     * @返回类型 String
     * @创建人 tank
     * @创建时间 2016年4月16日上午11:23:48
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(
			@RequestParam(defaultValue = "1") String parentMenuId,
			@RequestParam(defaultValue = "") String menuName,
			@RequestParam(defaultValue = "") String url,
			@RequestParam(defaultValue = "100") String order) throws Exception {
    	
    	//拼装数据
    	MainMenuSaveReq  req =new MainMenuSaveReq();
    	req.setAfternodename(menuName);
		req.setAfterurl(url);
		req.setOrder1(Integer.parseInt(order));
		req.setCreatetime(DateUtil.getDateString());
		req.setParentnodeid(Integer.valueOf(parentMenuId));
		//保存
		Result rs=menuService.save(req);
        return rs;
    }
    /**
     * 
     * @描述:修改菜单，只有名称、链接、序号可修改
     * @param menuId
     * @param menuName
     * @param url
     * @param order
     * @return
     * @throws Exception
     * @返回类型 String
     * @创建人 tank
     * @创建时间 2016年4月16日上午11:53:22
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
   	@ResponseBody
   	public Result update(
   			@RequestParam(defaultValue = "0") String menuId,
   			@RequestParam(defaultValue = "") String menuName,
   			@RequestParam(defaultValue = "") String url,
   			@RequestParam(defaultValue = "100") String order) throws Exception {
       	
    	//拼装数据
    	MainMenuUpdateReq  req =new MainMenuUpdateReq();
    	req.setAfternodename(menuName);
		req.setAfterurl(url);
		req.setOrder1(Integer.parseInt(order));
		req.setCreatetime(DateUtil.getDateString());
		req.setId(Integer.valueOf(menuId));
		//保存
		Result rs=menuService.updateMainMenu(req);
        return rs;
       }
    /**
     * 
     * @描述:根据菜单 id 删除菜单。
     * 根节点不能删除id=1.有角色绑定的菜单不能删除。有子菜单的菜单删除时应给提示信息。（该菜单有子菜单是否确认删除？）
     * @param menuId
     * @return
     * @throws Exception
     * @返回类型 String
     * @创建人 tank
     * @创建时间 2016年4月16日下午12:45:13
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
   	@ResponseBody
   	public Result delete(@RequestParam(defaultValue = "1") String menuId) throws Exception {
    	//拼装数据
    	MainMenuReq  req =new MainMenuReq();
		req.setId(Integer.valueOf(menuId));
		//保存
		Result rs=menuService.deleteMainMenu(req);

    	return rs;
    }
    /**
     * 
     * @描述:获取拥有该菜单的角色和系统全部角色
     * @param menuId
     * @return
     * @throws Exception 
     * @返回类型 String
     * @创建人 tank
     * @创建时间 2016年4月16日下午1:22:57
     */
	@RequestMapping(value = "/getRoleByMenuId",method = RequestMethod.GET)
	@ResponseBody
	public Result getRoleByMenuId(@RequestParam(defaultValue = "")  String menuId ) throws Exception{
		Result rs =Result.getSuccessResult();
		//获取所有角色   并且
		List<RoleResp> list =roleService.findCondition(new RoleReq());
		MainMenuReq req = new MainMenuReq();
		req.setId(Integer.valueOf(menuId));
		MainMenuResp mainMenuResp =menuService.findOneWithRole(req);
		
		for(RoleResp r:list){
			r.setAllow("no");
			if(mainMenuResp!=null && CollectionUtils.isNotEmpty(mainMenuResp.getRoleRespList())   ){
				for(RoleResp rr :mainMenuResp.getRoleRespList() ){
					if( rr.equals(r) ){
						r.setAllow("yes");
					}
				}
			}
		}
		rs.setData(list);
		return rs;
		
	}
	/**
	 * 
	 * @描述:设置菜单的访问权限
	 * @param menuId
	 * @param roleIds
	 * @return
	 * @throws Exception 
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年4月17日上午9:13:08
	 */
	@RequestMapping(value = "/grantMenuToRole",method = RequestMethod.POST)
	@ResponseBody
	public Result grantMenuToRole(
			@RequestParam(defaultValue = "")  String menuId,
			@RequestParam(defaultValue = "")  String roleIds ){
		
		MainMenuGrantReq req =new  MainMenuGrantReq();
		req.setId(Integer.valueOf(menuId));
		String[] strArr =roleIds.split(",");
		List<Integer> rList =new ArrayList<Integer>();
		for(String str: strArr){
			if(StringUtils.isNotBlank(str)){
				rList.add(Integer.valueOf(str));
			}
		}
		req.setRoleIds(rList);
		Result rs =null;
		try {
			rs = menuService.GrantRoleToMainu(req);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			rs=new Result("error",e.getMessage());
		}
		
		return rs;
	}
}
