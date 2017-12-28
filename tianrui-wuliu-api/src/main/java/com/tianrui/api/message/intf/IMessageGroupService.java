package com.tianrui.api.message.intf;

import com.tianrui.api.req.groupMsg.CustomRcordReq;
import com.tianrui.api.req.groupMsg.MemberGroupReq;
import com.tianrui.api.req.groupMsg.MessageGroupPushReq;
import com.tianrui.api.req.groupMsg.MessageGroupReq;
import com.tianrui.api.req.groupMsg.PushGroupMessageReq;
import com.tianrui.api.resp.groupMsg.MessageGroupPushResp;
import com.tianrui.api.resp.groupMsg.MessageGroupResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IMessageGroupService {
	/** 推送消息
	 * @throws Exception */
	public Result pushGroupMsg(PushGroupMessageReq req) throws Exception;
	/** 修改消息
	 * @throws Exception */
	public Result uptErrMsg(CustomRcordReq req)throws Exception;
	/** 查询消息分组 -群体维护*/
	public PaginationVO<MessageGroupResp> selectMsgGroup(MessageGroupReq req)throws Exception;
	/** 查询消息分组推送*/
	public PaginationVO<MessageGroupPushResp> selectMsgGroupPush(MessageGroupPushReq req)throws Exception;
	/** 更新用户分组
	 * @throws Exception */
	public Result uptMemberGroup(MemberGroupReq req) throws Exception;
}
