package com.tianrui.api.intf.memberMerger;

import com.tianrui.api.req.memberMerger.MergerCellphoneReq;
import com.tianrui.api.req.memberMerger.MergerQueryReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.vo.Result;

/** 前台账户合并*/
public interface IMemberMergerService {

	/** 查询待合并账号
	 * @throws Exception */
	PageResp<MemberResp> selectMergerCellhpone(MergerQueryReq req) throws Exception;
	/** 账号合并
	 * @throws Exception */
	Result mergerCellphone(MergerCellphoneReq req);
}
