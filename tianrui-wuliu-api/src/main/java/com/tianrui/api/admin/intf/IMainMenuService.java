package com.tianrui.api.admin.intf;

import java.util.List;

import com.tianrui.api.req.admin.MainMenuGrantReq;
import com.tianrui.api.req.admin.MainMenuReq;
import com.tianrui.api.req.admin.MainMenuSaveReq;
import com.tianrui.api.req.admin.MainMenuUpdateReq;
import com.tianrui.api.resp.admin.MainMenuResp;
import com.tianrui.common.vo.Result;


public interface IMainMenuService {
	
	/**
	 * 
	 * @param req
	 * @return true 存在  false 不存在
	 */
	boolean findwithMenuName(MainMenuReq  req);
	
	/**
	 * 获取菜单信息 根据主键查询
	 * @param req
	 * @return
	 */
	MainMenuResp findOne(MainMenuReq  req);
	
	/**
	 * 获取菜单信息 包含菜单对应的角色
	 * @param req
	 * @return
	 */
	MainMenuResp findOneWithRole(MainMenuReq  req)throws Exception;
	
	
	List<MainMenuResp> findCondition(MainMenuReq  req)throws Exception;
	
	
	List<MainMenuResp> findWithUser(MainMenuReq  req)throws Exception;
	
	
	
	//获取所有的二级菜单集合 该菜单包含子菜单
	List<MainMenuResp> findAllMenu(MainMenuReq  req)throws Exception;
	
	//新增菜单
	Result save(MainMenuSaveReq req)throws Exception;
	//修改菜单
	Result updateMainMenu(MainMenuUpdateReq req) throws Exception;
	//删除菜单
	Result deleteMainMenu(MainMenuReq  req)throws Exception;
	//设置菜单对应的角色
	Result GrantRoleToMainu(MainMenuGrantReq grantReq )throws Exception;
}
