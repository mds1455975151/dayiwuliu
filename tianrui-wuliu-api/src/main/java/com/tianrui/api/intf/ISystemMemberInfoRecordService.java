package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.member.MemberInfoReq;
import com.tianrui.api.resp.front.member.MemberInfoRecordResp;
import com.tianrui.api.resp.front.member.MemberInfoResp;
import com.tianrui.common.vo.Result;

/**
 * 
 * @类描述：会员认证记录
 * @创建人：lsj
 * @创建时间：2016年6月20日下午3:10:38
 *
 * @修改人：lsj
 * @修改时间：2016年6月20日下午3:10:38
 * @修改备注：
 *
 */
public interface ISystemMemberInfoRecordService {

	/**
	 * 
	 * @描述:个人认证
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月21日上午10:27:42
	 */
	Result personalAuthentication(MemberInfoReq req)throws Exception;
	/**
	 * 
	 * @描述:企业认证
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月21日上午10:28:18
	 */
	Result enterpriseAuthentication(MemberInfoReq req)throws Exception;
	/**
	 * 
	 * @描述:司机认证
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月21日上午10:28:18
	 */
	Result driverAuthentication(MemberInfoReq req)throws Exception;
	/**
	 * 
	 * @描述:查询用户的认证信息
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 MemberInfoResp
	 * @创建人 lsj
	 * @创建时间 2016年6月22日上午9:18:58
	 */
	MemberInfoRecordResp findByMemberId(String id)throws Exception;
	
	MemberInfoRecordResp findByMemberRecordId(String id)throws Exception;
	/** 查看最新认证信息*/
	Result memberRecoredAutid(String id)throws Exception;
	/**
	 * 
	 * @描述:查询认证记录
	 * @param memberid
	 * @return
	 * @throws Exception
	 * @返回类型 List<MemberInfoRecordResp>
	 * @创建人 lsj
	 * @创建时间 2016年6月23日下午3:42:24
	 */
	List<MemberInfoRecordResp> findListByEntity(String memberid)throws Exception;
}
