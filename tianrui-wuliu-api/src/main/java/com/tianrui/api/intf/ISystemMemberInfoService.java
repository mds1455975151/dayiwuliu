package com.tianrui.api.intf;

import com.tianrui.api.req.front.member.MemberInfoReq;
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
	
	
}
