package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianrui.api.admin.intf.IUserService;
import com.tianrui.api.intf.ISendMobileMessage;
import com.tianrui.api.req.admin.UserDeleteReq;
import com.tianrui.api.req.admin.UserLoginReq;
import com.tianrui.api.req.admin.UserQueryReq;
import com.tianrui.api.req.admin.UserReq;
import com.tianrui.api.req.admin.UserSaveReq;
import com.tianrui.api.req.admin.UserUpdatePassReq;
import com.tianrui.api.req.admin.UserUpdateReq;
import com.tianrui.api.req.common.SmsDetails;
import com.tianrui.api.resp.admin.RoleResp;
import com.tianrui.api.resp.admin.UserResp;
import com.tianrui.common.utils.Md5Utils;
import com.tianrui.common.utils.MsgUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.common.vo.UserLoginVo;
import com.tianrui.service.admin.bean.FileOrg;
import com.tianrui.service.admin.bean.Role;
import com.tianrui.service.admin.bean.UserRole;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.service.admin.mapper.FileOrgMapper;
import com.tianrui.service.admin.mapper.UserRoleMapper;
import com.tianrui.service.admin.mapper.UsersMapper;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;

@Service
public class UserService implements IUserService{
	@Autowired
	UsersMapper  usersMapper;
	@Autowired
	UserRoleMapper  userRoleMapper;
	@Autowired
	CacheClient cache ;
	@Autowired
	ISendMobileMessage sendMobileMessage;
	@Autowired
	FileOrgMapper fileOrgMapper;
	@Override
	public Result login(UserLoginReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		if( req ==null || StringUtils.isBlank(req.getAccount()) || StringUtils.isBlank(req.getPassword()) || StringUtils.isBlank(req.getAuthCode())){
			rs =new Result("error", "登录参数错误");
		}else{
			Users query = new Users();
			query.setAccount(req.getAccount());
			List<Users> list =usersMapper.selectByCondition(query);
			if( CollectionUtils.isNotEmpty(list) ){
				Users loginUser =list.get(0);
				if( !StringUtils.equals(loginUser.getPassword(), Md5Utils.MD5(req.getPassword())) ){
					rs =new Result("error", "您输入的密码错误");
				}else{
					//判断用户所属组织是否禁用
					if(loginUser.getOrgid() != null && StringUtils.isNotBlank(loginUser.getOrgid())){
						FileOrg org = fileOrgMapper.selectByPrimaryKey(loginUser.getOrgid());
						if(org == null ){
							rs =new Result("error", "您的组织已被管理员删除，请联系管理员处理！");
						}else if ("2".equals(org.getStatus())) {
							rs =new Result("error", "您的组织已被管理员禁用，请联系管理员处理！");
						}else {
							rs.setData(loginUser);
						}
					}else {
						rs.setData(loginUser);
					}
				}
			}else{
				rs =new Result("error", "您输入的用户名不存在，请重新输入...");
			}
		}
		return rs;
	}



	@Override
	public Result saveUser(UserSaveReq req) throws Exception {
		
		Result rs =Result.getSuccessResult();
		Users user =new Users();
		if(req != null && StringUtils.isNotBlank(req.getAccount())){
			user.setAccount(req.getAccount());
			long userSise = usersMapper.countByCondition(user);
			if(userSise >0){
				rs.setCode("1");
				rs.setError("用户账号已存在，请重新输入");
			}else {
				PropertyUtils.copyProperties(user, req);
				usersMapper.insert(user);
				//保存对应的角色关系
				if( CollectionUtils.isNotEmpty(req.getRoleIds()) ){
					UserRole ur =null;
					for(Integer rid :req.getRoleIds() ){
						ur=new UserRole();
						ur.setRoleidU(rid);
						ur.setUserid(user.getId());
						userRoleMapper.insert(ur);
					}
				}
			}
		}else {
			rs.setCode("0");
			rs.setError("用户账号不能为空，请重新输入");
		}
		return rs;
	}



	@Override
	public Result updataPass(UserUpdatePassReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if(req ==null ||req.getId() ==null || StringUtils.isBlank(req.getPassWord()) ){
			rs =new Result("error", "参数异常");
		}else{
			Users update =new Users();
			update.setId(req.getId());
			update.setPassword(req.getPassWord());
			usersMapper.updateByPrimaryKeySelective(update);
		}
		
		return rs;
	}


	@Override
	public Result updataUser(UserUpdateReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if(req ==null ||req.getId() ==null || StringUtils.isBlank(req.getAccount())|| StringUtils.isBlank(req.getTelnum())){
			rs =new Result("error", "参数异常");
		}else{
			Users update =new Users();
			update.setId(req.getId());
			update.setTelnum(req.getTelnum());
			update.setAccount(req.getAccount());
			update.setUsertype(req.getUsertype());
			update.setStatus(req.getStatus());
			usersMapper.updateByPrimaryKeySelective(update);
			//删除所有关系
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(req.getId());
			userRoleMapper.deleteByuIds(ids);
			if( CollectionUtils.isNotEmpty(req.getrIdList()) ){
				//保存关系
				UserRole ur =null;
				for(Integer rid :req.getrIdList() ){
					ur=new UserRole();
					ur.setRoleidU(rid);
					ur.setUserid(req.getId());
					userRoleMapper.insert(ur);
				}
			}
		}
		return rs;
	}




	@Override
	public Result sendMsgCode(UserReq userReq) throws Exception {
		Result rs = Result.getSuccessResult();
		
		//验证码
		int authCode=(int)((Math.random()*9+1)*100000);
		
		//查询用户信息
		Users query =new Users();
		query.setAccount(userReq.getUserName());
		List<Users> dblist =usersMapper.selectByCondition(query);
		//用户存在
		if( CollectionUtils.isNotEmpty(dblist) ){
			Users users =dblist.get(0);
			//用户电话号码存在
			if( StringUtils.isNotBlank(users.getTelnum()) ){
				//redis存在的key
				String key = CacheHelper.buildKey(CacheModule.ADMINLOGIN, users.getTelnum());
				UserLoginVo value = (UserLoginVo) cache.getObj(key,UserLoginVo.class);
				//用户为第一次发送
				if( value ==null ){
					UserLoginVo u =new UserLoginVo(); 
					u.setCount(1);
					u.setStartTime(System.currentTimeMillis());
					u.setEndTime(System.currentTimeMillis());
					u.setUserCode(authCode);
					u.setUserName(userReq.getUserName());
					cache.saveObject(key, u, 60*30); //半小时内有效
				//用户为第N次发送
				}else{
					boolean isTimeOut = true;// 获取时间是否超时
					int count=value.getCount();
					long stime=value.getStartTime();
					long now=System.currentTimeMillis();
					isTimeOut = now - stime > 60 * 1000;
					if(isTimeOut && count<5){
						count=count+1;
						value.setCount(count);
						cache.saveObject(key, value, Long.valueOf((30 * 60 - (now - stime) / 1000)).intValue());
					}else{
						if(count>=5){
							rs = new Result("4","用户频繁操作");
						}else{
							rs = new Result("5","一分钟内请求次数过多");
						}
					}
				}
				
				if( StringUtils.equals(rs.getCode(), "000000") ){
					//获取短信内容
					String msg = MsgUtil.getUserLogin(authCode);
					//发送短信
					SmsDetails sms = new SmsDetails();
					sms.setTelephoneReceiver(users.getTelnum());
					sms.setSmsContent(msg);
					sendMobileMessage.sendMobileMessage(sms);
				}
				
			}else{
				rs = new Result("2","该用户没有手机号");
			}
			
		}else{
			rs = new Result("1","查不到用户信息");
		}
		
		return rs;
	}




	@Override
	public UserResp findOne(UserReq req) throws Exception {
		UserResp resp =null;
		if(req !=null && req.getId() !=null  ){
			Users user =usersMapper.selectByPrimaryKey(req.getId());
			resp =conver2User(user);
		}
		return resp;
	}




	@Override
	public UserResp findOneWithRole(UserReq req) throws Exception {
		UserResp resp =null;
		if(req !=null && req.getId() !=null  ){
			resp =findOne(req);
			
			if( resp!=null ){
					
				List<Role>  rList = userRoleMapper.findWithUId(req.getId());
				resp.setRoleList(conver2RoleResp(rList));
			}
		}		
		return resp;
	}


	@Override
	public Result deleteUsers(UserDeleteReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		
		if(CollectionUtils.isNotEmpty(req.getIds())  ){
			usersMapper.deleteByIds(req.getIds());
			userRoleMapper.deleteByuIds(req.getIds());
		}else{
			rs=new Result("error","传入参数为空");
		}

		return rs;
	}




	@Override
	public PaginationVO<UserResp> findUserByPage(UserQueryReq req) throws Exception {
		PaginationVO<UserResp> page =new PaginationVO<UserResp>();
		if( req !=null && req.getPageNo() >0){
			//查总数
			Users query =new Users();
			query.setAccount(req.getUsername());
			query.setUsertype(req.getName());
			query.setTelnum(req.getTel());
			query.setOrgid(req.getOrgid());
			long total =usersMapper.countByCondition(query);

			//查数据
			if( total >0 ){
				query.setStart((req.getPageNo()-1)*req.getPageSize());
				query.setLimit(req.getPageSize());
				List<Users> dblist =usersMapper.selectByCondition(query);
				page.setList(conver2UserList(dblist));
			}
			//拼装数据
			page.setPageNo(req.getPageNo());
			page.setTotal(total);
		}
		
		
		return page;
	}

	
	private List<UserResp> conver2UserList(List<Users> list){
		List<UserResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs =new ArrayList<UserResp>();
			for( Users user: list){
				rs.add(conver2User(user));
			}
		}
		return rs;
	}
	
	private UserResp conver2User(Users users){
		UserResp resp =null;
		if( users !=null ){
			resp =new UserResp();
			resp.setId(String.valueOf(users.getId()));
			resp.setName(users.getUsertype());
			resp.setAccount(users.getAccount());
			if( users.getStatus() ==1 ){
				resp.setStatus("有效");
			}else{
				resp.setStatus("禁用");
			}
			resp.setRole(users.getDesc1());
			resp.setTel(users.getTelnum());
			resp.setOrgid(users.getOrgid());
			resp.setOrgname(users.getOrgname());
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
}
