package com.tianrui.api.intf.memberMerger;

import com.tianrui.api.req.memberMerger.MergerQueryReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.member.MemberResp;

/** 前台账户合并*/
public interface IMemberMergerService {

	/** 查询待合并账号
	 * @throws Exception */
	PageResp<MemberResp> selectMergerCellhpone(MergerQueryReq req) throws Exception;
}
