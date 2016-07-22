package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.UserDeleteReq;
import com.tianrui.api.req.admin.UserLoginReq;
import com.tianrui.api.req.admin.UserQueryReq;
import com.tianrui.api.req.admin.UserReq;
import com.tianrui.api.req.admin.UserSaveReq;
import com.tianrui.api.req.admin.UserUpdatePassReq;
import com.tianrui.api.req.admin.UserUpdateReq;
import com.tianrui.api.resp.admin.UserResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * @修改人：tank
 * @修改时间：2016年4月14日下午5:33:18
 * @修改备注：
 *
 */
public interface IUserService{

	Result login(UserLoginReq req)throws Exception;
	
	Result deleteUsers(UserDeleteReq req)throws Exception;
	
	Result updataPass(UserUpdatePassReq req)throws Exception;
	
	Result updataUser(UserUpdateReq req)throws Exception;
	
	Result sendMsgCode(UserReq userReq)throws Exception;
	
	UserResp findOne(UserReq userReq)throws Exception;
	
	UserResp findOneWithRole(UserReq userReq)throws Exception;

	PaginationVO<UserResp> findUserByPage(UserQueryReq req)throws Exception;

	Result saveUser(UserSaveReq req)throws Exception;

}
