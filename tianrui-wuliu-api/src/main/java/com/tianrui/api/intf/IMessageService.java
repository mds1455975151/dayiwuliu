package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.message.MessageQueryReq;
import com.tianrui.api.req.front.message.MessageReplayReq;
import com.tianrui.api.req.front.message.MessageReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.resp.front.message.MessageResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IMessageService {
	/**
	 * 
	* @Description: 更新/修改 消息
	* @param @param req
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年6月7日
	 */
	Result updateMessage(MessageReq req) throws Exception;
	/**
	 * 
	* @Description: 查询方法
	* @param @param req
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年6月7日
	 */
	List<MessageResp> findByEntity(MessageQueryReq req) throws Exception;
	/**
	 * 
	* @Description: 发送站内消息
	* @param @param req
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年6月13日
	 */
	Result sendMessageInside(SendMsgReq req) throws Exception;
	
	/**
	  * 分页查询站内信
	  * @Title: page 
	  * @Description: TODO 
	  * @param 
	  * @return PaginationVO<MessageResp>   
	  * @throws 
	  *
	 */
	public PaginationVO<MessageResp> page(MessageQueryReq req)throws Exception;
	
	/**
	  * @Title: replayMessage 
	  * @Description: TODO 
	  * @param 
	  * @return Result   
	  * @throws 
	  *
	 */
	public Result replayMessage(MessageReplayReq req)throws Exception;
	
	
	public long queryUnreadTotal(MessageQueryReq req)throws Exception;
}
