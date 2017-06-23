package com.tianrui.api.intf;

import java.lang.reflect.InvocationTargetException;

import com.tianrui.api.req.front.member.AdminMenberInfoReq;
import com.tianrui.api.req.front.member.MemberInfoReq;
import com.tianrui.api.resp.front.member.MemberInfoRecordResp;
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
public interface ISystemMemberInfoService {

	/**
	 * 
	 * @描述:用户审核
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月22日上午11:51:18
	 */
	Result userReview(MemberInfoReq req)throws Exception;
	/**
	 * 
	 * @描述:司机审核
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月22日下午2:01:38
	 */
	Result driverReview(MemberInfoReq req)throws Exception;
	/**
	 * 
	 * @描述:企业审核
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月22日下午2:31:29
	 */
	Result companyReview(MemberInfoReq req)throws Exception;
	/**
	 * 
	 * @描述:司机换班 查询所属车主
	 * @param dirverId
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年8月12日上午10:20:14
	 */
	public Result handView(String dirverId) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	/** 修改图片*/
	Result uptMemberPic(MemberInfoReq req)throws Exception;
	/** 后台司机安联认证*/
	Result uptDrvierAnlian(AdminMenberInfoReq req)throws Exception;
	/** 查看用户信息*/
	MemberInfoRecordResp selectMemberInfo(String id)throws Exception;
	/**
	 * @Description 查询推送成功但未分配组织（不能使用）的供应商，根据nc返回结果回写memberinfo表中ncStatus字段
	 * @exception 
	 * @author zhanggaohao
	 * @version 2017年6月23日 上午11:47:55
	 */
	void scheduleCallBackPushStatus();

}
