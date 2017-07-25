package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.app.AppGetCodeReq;
import com.tianrui.api.req.app.AppMemberReq;
import com.tianrui.api.req.app.AppMemberRoleReq;
import com.tianrui.api.req.front.member.MemberFindReq;
import com.tianrui.api.req.front.member.MemberMassageReq;
import com.tianrui.api.req.front.member.MemberReq;
import com.tianrui.api.req.front.member.MemberSaveReq;
import com.tianrui.api.req.front.member.MemberUpdateReq;
import com.tianrui.api.req.weixin.CancelMember;
import com.tianrui.api.req.weixin.WeixinMemberReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.member.MemberInfoMassageResp;
import com.tianrui.api.resp.front.member.MemberInfoResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.member.SystemMemberResp;
import com.tianrui.common.vo.Result;
/**
 * 
 * @类描述：用户
 * @创建人：lsj
 * @创建时间：2016年6月20日下午4:11:31
 *
 * @修改人：lsj
 * @修改时间：2016年6月20日下午4:11:31
 * @修改备注：
 *
 */
public interface ISystemMemberService {

	/**
	 * 
	 * @描述:获取手机验证码-pc
	 * @param telnum
	 * @param type 	 @param type 0-注册；1-密码找回;2-验证码登陆
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 tank
	 * @创建时间 2016年5月23日下午2:26:04
	 */
	Result getValCode(String telnum, String type,String resource) throws Exception;
	/**
	 * 
	 * @描述:获取手机验证码-app
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 tank
	 * @创建时间 2016年5月23日下午2:28:29
	 */
	Result sendAuthCodeMsg(AppGetCodeReq req)throws Exception;
	/**
	 * 
	 * @描述:通过电话查询用户
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年6月20日下午5:02:05
	 */
	MemberResp findMemberByTelnum(MemberReq req)throws Exception;
	/**
	 * 
	 * @描述:添加用户
	 * @param memberSaveReq true-添加成功，false-添加失败
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年6月20日下午5:12:09
	 */
	boolean saveMember(MemberSaveReq memberSaveReq)throws Exception;
	/**
	 * 
	 * @描述:会员登陆
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 MemberResp
	 * @创建人 lsj
	 * @创建时间 2016年6月21日上午8:41:22
	 */
	MemberResp login(MemberReq req)throws Exception;
	/**
	 * 
	 * @描述:微信端登陆
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 MemberResp
	 * @创建人 lsj
	 * @创建时间 2016年7月25日下午1:59:46
	 */
	Result wxlogin(WeixinMemberReq req)throws Exception;
	/**
	 * 
	 * @描述:微信取消绑定
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年7月26日下午2:37:27
	 */
	Result cancelLogin(CancelMember req)throws Exception;
	/**
	 * 
	 * @描述:openid登陆
	 * @param openid
	 * @return
	 * @throws Exception
	 * @返回类型 MemberResp
	 * @创建人 lsj
	 * @创建时间 2016年7月25日下午3:01:49
	 */
	MemberResp findByOpenid(String openid)throws Exception;
	/**
	 * 
	 * @描述:移动端登录
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 tank
	 * @创建时间 2016年5月23日下午2:25:39
	 */
	Result applogin(AppMemberReq req)throws Exception;
	/**
	 * 
	 * @描述:通过id查询用户
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 MemberResp
	 * @创建人 lsj
	 * @创建时间 2016年6月21日上午9:59:40
	 */
	MemberResp findById(String id)throws Exception;
	/**
	 * 通过id查询司机信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	MemberResp findByMemberId(String id)throws Exception;
	/**
	 * 
	 * @描述:修改用户信息
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 boolean
	 * @创建人 lsj
	 * @创建时间 2016年6月21日上午11:01:33
	 */
	boolean updateMember(MemberUpdateReq req)throws Exception;
	/**
	 * 
	 * @描述:查询会员
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 PageResp<MemberResp>
	 * @创建人 lsj
	 * @创建时间 2016年6月21日下午1:44:22
	 */
	PageResp<MemberResp> findMemberlist(MemberFindReq req)throws Exception;
	/**
	 * 
	 * @描述:查询会员信息列表
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 MemberResp
	 * @创建人 lsj
	 * @创建时间 2016年6月22日下午4:51:58
	 */
	List<SystemMemberResp> findMemberByMassage(MemberMassageReq req)throws Exception;

	MemberInfoMassageResp findInfoMassageById(String id)throws Exception;
	/**
	 * 
	 * @描述:会员认证信息
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 MemberInfoResp
	 * @创建人 lsj
	 * @创建时间 2016年6月23日下午3:18:54
	 */
	MemberInfoResp authenticationInfoByid(String id)throws Exception;
	/**
	 * 
	 * @param appMemberRoleReq
	 * @return
	 * @throws Exception
	 */
	Result chooseRole(AppMemberRoleReq appMemberRoleReq) throws Exception;
}
