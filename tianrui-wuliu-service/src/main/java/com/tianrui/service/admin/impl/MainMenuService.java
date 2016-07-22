package com.tianrui.service.admin.impl;
 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IMainMenuService;
import com.tianrui.api.req.admin.MainMenuGrantReq;
import com.tianrui.api.req.admin.MainMenuReq;
import com.tianrui.api.req.admin.MainMenuSaveReq;
import com.tianrui.api.req.admin.MainMenuUpdateReq;
import com.tianrui.api.resp.admin.MainMenuResp;
import com.tianrui.api.resp.admin.RoleResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Mainmenu;
import com.tianrui.service.admin.bean.Role;
import com.tianrui.service.admin.bean.RoleMenu;
import com.tianrui.service.admin.mapper.MainmenuMapper;
import com.tianrui.service.admin.mapper.RoleMenuMapper;
import com.tianrui.service.admin.mapper.UserRoleMapper;

@Service
public class MainMenuService implements IMainMenuService{
	
	@Autowired
	MainmenuMapper mainmenuMapper;
	@Autowired
	RoleMenuMapper roleMenuMapper;
	@Autowired
	UserRoleMapper userRoleMapper;

	public boolean findwithMenuName(MainMenuReq req) {
		boolean flag =false;
		if( req !=null && StringUtils.isNotBlank(req.getMenuName()) ){
			Mainmenu query = new  Mainmenu();
			query.setNodename(req.getMenuName());
			long count =mainmenuMapper.countByCondition(query);
			if( count >0 ){
				flag =true;
			}
		}
		return flag;
	}

	public MainMenuResp findOne(MainMenuReq req) {
		MainMenuResp resp =null;
		if( req !=null && req.getId() !=null ){
			Mainmenu mainmenu  =mainmenuMapper.selectByPrimaryKey(req.getId());
			resp= convert2MainMenu(mainmenu);
		}
		return resp;
	}

	public MainMenuResp findOneWithRole(MainMenuReq req) {
		MainMenuResp mainMenuResp =findOne(req);
		if( mainMenuResp !=null ){
			List<Role> roleList=roleMenuMapper.findWithMId(mainMenuResp.getId());
			mainMenuResp.setRoleRespList(conver2RoleResp(roleList));
		}
		return mainMenuResp;
	}
	
	public List<MainMenuResp> findCondition(MainMenuReq req) {
		List<MainMenuResp> rs =null;
		if(req !=null){
			Mainmenu query =new Mainmenu();
			//TODO 查询条件拼装			
			query.setNodename(req.getMenuName());
			query.setId(req.getId());
			
			List<Mainmenu>  list =mainmenuMapper.selectByCondition(query);
			rs =convert2MainMenuList(list);
		}
		
		return rs;
	}
	
	//获取所有的二级菜单集合 该菜单包含子菜单
	public List<MainMenuResp> findAllMenu(MainMenuReq req) {
		List<MainMenuResp> rs =new ArrayList<MainMenuResp>();
		//查询db所有数据
		List<Mainmenu>  dbList =mainmenuMapper.selectByCondition(null);
		if( CollectionUtils.isNotEmpty(dbList) ){
			Integer rootKey=null;
			Map<Integer,List<Mainmenu>> mapL = new HashMap<Integer,List<Mainmenu>>();
			for(Mainmenu mainmenu :dbList ){
				//只获取父节点不为空的元素  排除根节点
				if( mainmenu .getParentnodeid() !=null ){
					if( CollectionUtils.isNotEmpty(mapL.get(mainmenu.getParentnodeid()) ) ){
						mapL.get(mainmenu.getParentnodeid()).add(mainmenu);
					}else{
						List<Mainmenu> list =new ArrayList<Mainmenu>();
						list.add(mainmenu);
						mapL.put(mainmenu.getParentnodeid(), list);
					}
					
				}else{
					rootKey=mainmenu.getId();
				}
			}
			//内存处理返回数据的目录结构
			if( rootKey !=null ){
				List<Mainmenu>  rootList=mapL.get(rootKey);
				if( CollectionUtils.isNotEmpty(rootList) ){
					rs =convert2MainMenuList(rootList);
					for(MainMenuResp resp  :rs ){
						if(   CollectionUtils.isNotEmpty(mapL.get(resp.getId()) )){
							resp.setNodeList(convert2MainMenuList(mapL.get(resp.getId()) ));
						};
					}
				}
			}
		}
		
		return rs;
	}

	public Result save(MainMenuSaveReq req)throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null ){
			if( StringUtils.isNotBlank(req.getAfternodename()) ){
				Mainmenu query = new  Mainmenu();
				query.setNodename(req.getAfternodename());
				long count =mainmenuMapper.countByCondition(query);
				if( count >0 ){
					rs=new Result("error","菜单名称已存在");
				}else{
					PropertyUtils.copyProperties(query, req);
					mainmenuMapper.insert(query);
				}
			}
		}else{
			rs=new Result("error","参数异常");
		}
		
		return rs;
	}

	public Result updateMainMenu(MainMenuUpdateReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		//验证参数
		if(req !=null && req.getId() !=null){
			//菜单名称不能重复
			if( StringUtils.isNotBlank(req.getAfternodename())  ){
				//查询db数据
				MainMenuReq query =new MainMenuReq();
				query.setMenuName(req.getAfternodename());
				List<MainMenuResp> queryList =   findCondition(query);
				//设置重复标识
				boolean flag =false;
				if( CollectionUtils.isNotEmpty(queryList) ){
					for(MainMenuResp mainMenuResp :queryList  ){
						if(  mainMenuResp.getId() != req.getId() ){
							rs=new Result("error","菜单名称已存在");
							flag=true;
							break;
						}
					}
				}
				if( !flag ){
					Mainmenu mainmenu =new Mainmenu();
					PropertyUtils.copyProperties(mainmenu, req);
					mainmenuMapper.updateByPrimaryKeySelective(mainmenu);
				}
			}
			
		}else{
			rs=new Result("error","参数异常");
		}
		return rs;
	}

	public Result deleteMainMenu(MainMenuReq req) {
		Result rs = Result.getSuccessResult();
		//验证参数
		if(req !=null && req.getId() !=null){
			List<Mainmenu> queryList= mainmenuMapper.findWithRole(req.getId());
			if( CollectionUtils.isNotEmpty(queryList) ){
				String msg= "";
				for(Mainmenu mainmenu :queryList ){
					msg+=mainmenu.getAfternodename();
					msg+=".";
				}
				rs=new Result("error","请先解除菜单【"+msg.substring(0, msg.lastIndexOf("."))+"】绑定的角色");
			}else{
				mainmenuMapper.deleteByPrimaryKey(req.getId() );
			}
		}
		return rs;
	}
	

	public Result GrantRoleToMainu(MainMenuGrantReq grantReq) throws Exception {
		Result rs =Result.getSuccessResult();
		if(  grantReq !=null && grantReq.getId() !=null ){
			roleMenuMapper.deleteWithmId(grantReq.getId());
			if(   CollectionUtils.isNotEmpty(grantReq.getRoleIds()) ){
				for(Integer roleId: grantReq.getRoleIds() ){
					RoleMenu  record =new RoleMenu();
					record.setMenuidR(grantReq.getId());
					record.setRoleidM(roleId);
					roleMenuMapper.insert(record);
				}
			}
		}else{
			rs = new Result("error","传入参数异常");
		}
		return rs;
	}

	private List<MainMenuResp> convert2MainMenuList(List<Mainmenu> list){
		List<MainMenuResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs =new ArrayList<MainMenuResp>();
			for(Mainmenu mainmenu:list){
				rs.add(convert2MainMenu(mainmenu));
			}
		}
		return rs;
	}
	
	private MainMenuResp convert2MainMenu(Mainmenu mainmenu){
		MainMenuResp resp =null;
		if( mainmenu !=null ){
			resp= new MainMenuResp();
			resp.setId(mainmenu.getId());
			resp.setAfternodename(mainmenu.getAfternodename());
			resp.setAfterurl(mainmenu.getAfterurl());
			resp.setCreatetime(mainmenu.getCreatetime());
			resp.setFronturl(mainmenu.getFronturl());
			resp.setNodename(mainmenu.getNodename());
			resp.setOrder1(mainmenu.getOrder1());
			resp.setParentnodeid(mainmenu.getParentnodeid());
		}
		return resp;
	}

	private List<RoleResp> conver2RoleResp(List<Role> roleList){
		List<RoleResp> rs = null;
		if( CollectionUtils.isNotEmpty(roleList) ){
			rs =new ArrayList<RoleResp>();
			for( Role role:roleList  ){
				rs.add(conver2RoleResp(role));
			}
		}
		return rs;
	}
	private RoleResp conver2RoleResp(Role role){
		RoleResp resp =null;
		if( role!=null ){
			resp =new RoleResp();
			resp.setId(role.getId());
			resp.setDescription(role.getDescription());
			resp.setCreatetime(role.getCreatetime());
			resp.setName(role.getName());
			resp.setNumber(role.getNumber());
		}
		return  resp;
	}

	/**
	 * 
	  * <p>Title: findWithUser</p> 
	  * <p>Description: 获取用户的菜单权限</p>
	  * 前置条件  
	  * @see com.tianrui.api.admin.intf.IMainMenuService#findWithUser(com.tianrui.api.req.admin.MainMenuReq)
	 */
	@Override
	public List<MainMenuResp> findWithUser(MainMenuReq req) throws Exception {
		List<MainMenuResp> rs =null;
		
		//根据用户获取所有角色
		if( req !=null && req.getUserId() !=null ){
			
			List<Role> list =userRoleMapper.findWithUId(req.getUserId());
			if( CollectionUtils.isNotEmpty(list) ){
				List<Integer> rIds = new ArrayList<Integer>(list.size());
				for(Role item :list){
					rIds.add(item.getId());
				}
				List<Mainmenu> dbList =roleMenuMapper.findWithRIds(rIds);
				if( CollectionUtils.isNotEmpty(dbList) ){
					if( CollectionUtils.isNotEmpty(dbList) ){
						Map<Integer,List<Mainmenu>> mapL = new HashMap<Integer,List<Mainmenu>>();
						for(Mainmenu mainmenu :dbList ){
							//只获取父节点不为空的元素  排除根节点
							if( mainmenu .getParentnodeid() !=null ){
								if( CollectionUtils.isNotEmpty(mapL.get(mainmenu.getParentnodeid()) ) ){
									mapL.get(mainmenu.getParentnodeid()).add(mainmenu);
								}else{
									List<Mainmenu> itemlist =new ArrayList<Mainmenu>();
									itemlist.add(mainmenu);
									mapL.put(mainmenu.getParentnodeid(), itemlist);
								}
								
							}
						}
						//存在二级节点
						if( mapL.keySet() !=null && mapL.keySet().contains(Integer.valueOf(1))){
							List<Mainmenu>  rootList=mapL.get(Integer.valueOf(1));
							if( CollectionUtils.isNotEmpty(rootList) ){
								rs =convert2MainMenuList(rootList);
								for(MainMenuResp resp  :rs ){
									if(   CollectionUtils.isNotEmpty(mapL.get(resp.getId()) )){
										List<MainMenuResp> tempMenuList = convert2MainMenuList(mapL.get(resp.getId()));
										resp.setNodeList(convert2MainMenuList(mapL.get(resp.getId()) ));
										//把父菜单的url链接设置为第一个子菜单的url
										resp.setAfterurl(tempMenuList.get(0).getAfterurl());
									}
								}
							}
						}
					}
				}
				
			}
		}
		return rs;
	}

	
}
