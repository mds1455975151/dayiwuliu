package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IRoleService;
import com.tianrui.api.req.BaseReq;
import com.tianrui.api.req.admin.RoleCopyReq;
import com.tianrui.api.req.admin.RoleDeleteReq;
import com.tianrui.api.req.admin.RoleGrankReq;
import com.tianrui.api.req.admin.RoleReq;
import com.tianrui.api.req.admin.RoleSaveReq;
import com.tianrui.api.req.admin.RoleUpdateReq;
import com.tianrui.api.resp.admin.MainMenuResp;
import com.tianrui.api.resp.admin.RoleResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileCargo;
import com.tianrui.service.admin.bean.Mainmenu;
import com.tianrui.service.admin.bean.Role;
import com.tianrui.service.admin.bean.RoleMenu;
import com.tianrui.service.admin.mapper.RoleMapper;
import com.tianrui.service.admin.mapper.RoleMenuMapper;
import com.tianrui.service.admin.mapper.UserRoleMapper;
@Service
public class RoleService implements IRoleService {
	
	@Autowired
	RoleMenuMapper roleMenuMapper;
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	UserRoleMapper userRoleMapper;

	public PaginationVO<RoleResp> findRoleByPage(RoleReq req) {
		PaginationVO<RoleResp> page =null;
		if( req!=null && req.getPageNo() >0 ){
			page =new PaginationVO<RoleResp>();
			
			
			Role role =new Role();
			role.setName(req.getRoleName());
			role.setNumber(req.getRoleNumber());

			
			long total =roleMapper.countWithCondition(role);
			
			if(total>0){
				role.setStart((req.getPageNo()-1)*req.getPageSize());
				role.setLimit(req.getPageSize());
				List<Role> roleList=roleMapper.findWithCondition(role);
				
				page.setList(conver2RoleResp(roleList));
			}
			page.setPageNo(req.getPageNo());
			page.setTotal(total);
			
		}
		return page;
	}


	public List<RoleResp> findCondition(RoleReq req) {
		List<RoleResp> rs =null;
		
		Role role =new Role();
		role.setName(req.getRoleName());
		role.setNumber(req.getRoleNumber());
		
		List<Role> list =roleMapper.findWithCondition(role);
		rs=conver2RoleResp(list);
		return rs;
	}

	public RoleResp findOne(RoleReq req) {
		RoleResp resp =null;
		if( req!=null && req.getId() !=null ){
			Role role =roleMapper.selectByPrimaryKey(req.getId());
			resp =conver2RoleResp(role);
		}
		return resp;
	}

	public RoleResp findOneWithMenu(RoleReq req) throws Exception{
		RoleResp resp =findOne(req);
		if(resp !=null){
			List<Mainmenu> list=roleMenuMapper.findWithRId(req.getId());
			if( CollectionUtils.isNotEmpty(list) ){
				resp.setMenuRespList(convert2MainMenuList(list));
			}
		}
		return resp;
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
			resp.setUsercounts(role.getDesc4());
		}
		return  resp;
	}

	@Override
	public Result saveRole(RoleSaveReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if(req != null){
			Boolean flag = getSaveFlag(req.getRoleName(),req.getRoleNumber(), rs);
			if(flag){
				Role role =new Role();
				role.setName(req.getRoleName());
				role.setNumber(req.getRoleNumber());
				role.setDescription(req.getRoleDescription());
				role.setCreatetime(DateUtil.getDateString());
				role.setCreator(req.getCreator());
				roleMapper.insert(role);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		
		return rs;
	}

	/**
	 * 
	 * @描述:判断输入是否合法
	 * @param roleName
	 * @param roleNumber
	 * @param rs
	 * @return
	 * @返回类型 Boolean
	 * @创建人 tank
	 * @创建时间 2016年6月21日下午12:43:12
	 */
	private Boolean getSaveFlag(String  roleName,String roleNumber, Result rs) {
		Boolean flag = true;
		if(StringUtils.isNotBlank(roleName)){
			Role role =new Role();
			role.setName(roleName);
			if(roleMapper.countWithCondition(role) > 0){
				rs.setCode("01");
				rs.setError("角色名称已经存在，请重新输入...");
				flag = false;
			}else{
				if ( StringUtils.isNotBlank(roleNumber)) {
					role =new Role();
					role.setNumber(roleNumber);
					if(roleMapper.countWithCondition(role) > 0){
						rs.setCode("11");
						rs.setError("角色编码已经存在，请重新输入...");
						flag = false;
					}
				}else {
					rs.setCode("1");
					rs.setError("角色编码不能为空，请输入...");
					flag = false;
				}
				
			}
		}else {
			rs.setCode("0");
			rs.setError("角色名称不能为空，请输入...");
			flag = false;
		}
		return flag;
	}

	@Override
	public Result updateRole(RoleUpdateReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if(req != null && req.getId() != null){
			Boolean flag = getUpdateFlag(req, rs);
			if(flag){
				Role role =new Role();
				role.setName(req.getRoleName());
				role.setNumber(req.getRoleNumber());
				role.setDescription(req.getRoleDescription());
				role.setId(req.getId());
				roleMapper.updateByPrimaryKeySelective(role);
			}
		}else {
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	/**
	 * 
	 * @描述:判断修改输入是否合法
	 * @param req
	 * @param rs
	 * @return
	 * @返回类型 Boolean
	 * @创建人 tank
	 * @创建时间 2016年6月22日下午5:42:08
	 */
	private Boolean getUpdateFlag(RoleUpdateReq req, Result rs) {
		Boolean flag = true;
		if(StringUtils.isNotBlank(req.getRoleName())){
			Role role =new Role();
			role.setName(req.getRoleName());
			if(roleMapper.findWithCondition(role).size() > 0){
				if(req.getId() != roleMapper.findWithCondition(role).get(0).getId()){
					rs.setCode("01");
					rs.setError("角色名称已经存在，请重新输入");
					flag = false;
				}
			}
		}else {
			rs.setCode("0");
			rs.setError("角色名称不能为空，请输入...");
			flag = false;
		}
		if ( StringUtils.isNotBlank(req.getRoleNumber())) {
			Role role =new Role();
			role.setNumber(req.getRoleNumber());
			if(roleMapper.findWithCondition(role).size() > 0){
				if(req.getId() != roleMapper.findWithCondition(role).get(0).getId()){
					rs.setCode("11");
					rs.setError("角色编码已经存在，请重新输入...");
					flag = false;
				}
			}
		}else {
			rs.setCode("1");
			rs.setError("角色编码不能为空，请输入...");
			flag = false;
		}
			
		return flag;
	}


	@Override
	public Result deleteRole(RoleDeleteReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( CollectionUtils.isNotEmpty(req.getIdList()) ){
			if(CollectionUtils.isNotEmpty(userRoleMapper.findWithRIds(req.getIdList()))){
				rs =new Result("error","该角色有关联的用户");
			}else{
				userRoleMapper.deleteByuIds(req.getIdList());
				for( int id :req.getIdList() ){
					roleMapper.deleteByPrimaryKey(id);
				}
			}
		}
		return rs;
	}


	@Override
	public Result grantMenuWithRid(RoleGrankReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req!=null && req.getRid() !=null ){
			roleMenuMapper.deleteWithrId(req.getRid());
			if( CollectionUtils.isNotEmpty(req.getmIds()) ){
				RoleMenu menu =null;
				for(Integer mid : req.getmIds()){
					menu =new RoleMenu();
					menu.setRoleidM(req.getRid());
					menu.setMenuidR(mid);
					roleMenuMapper.insert(menu);
				}
			}
		
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}


	@Override
	public Result copyRole(RoleCopyReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req!=null && req.getId() !=null){
			Boolean flag = getSaveFlag(req.getRoleName(),req.getRoleNumber(), rs);
			if( flag ){
				int id =req.getId();
				//角色重新保存
				Role role =new Role();
				role.setName(req.getRoleName());
				role.setNumber(req.getRoleNumber());
				role.setDescription(req.getRoleDescription());
				role.setCreatetime(DateUtil.getDateString());
				role.setCreator(req.getCreator());
				roleMapper.insert(role);
				
				//获取绑定的菜单
				List<Mainmenu> list=roleMenuMapper.findWithRId(id);
				if( CollectionUtils.isNotEmpty(list) ){
					for( Mainmenu mainmenu:list ){
						RoleMenu menu =new RoleMenu();
						menu.setRoleidM(role.getId());
						menu.setMenuidR(mainmenu.getId());
						roleMenuMapper.insert(menu);
					}
				}
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}

	
}
