package com.tianrui.web.action.message;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.req.front.message.MessageQueryReq;
import com.tianrui.api.req.front.message.MessageReplayReq;
import com.tianrui.api.resp.front.message.MessageResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.BillService;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/Member/message")
public class MessageAction {
	@Autowired
	protected IMessageService messageService;
	@Autowired
	protected ICargoPlanService cargoPlanService;
	@Autowired
	protected BillService billService;
	/**
	 * 
	* @Description: 跳转到消息页面
	* @param @return    
	* @return ModelAndView    
	* @throws
	* @author wuchl
	* @date 2016年6月7日
	 */
	@RequestMapping("/message")
	public ModelAndView planProgress(){
		return new ModelAndView("/member/message/message");
	}
	/**
		* @Description: 查询消息
		* @param @param req
		* @param @return
		* @param @throws Exception    
		* @return Result    
		* @throws
		* @author wuchl
		* @date 2016年6月7日
	 */
	@RequestMapping("/findMessage")
	@ResponseBody
	public Result findMessage(HttpServletRequest request,Integer pageNo) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo cUser = SessionManager.getSessionMember(request);
		MessageQueryReq req = new MessageQueryReq();
		if( pageNo ==null || pageNo ==0 ){
			req.setPageNo(1);
		}else{
			req.setPageNo(pageNo);
		}
		req.setCurruId(cUser.getId());
		PaginationVO<MessageResp> page = messageService.page(req);
		rs.setData(page);
		return rs;
	}
	@RequestMapping("/findUnread")
	@ResponseBody
	public Result findUnread(HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo cUser = SessionManager.getSessionMember(request);
		MessageQueryReq req = new MessageQueryReq();
		req.setCurruId(cUser.getId());
		Long total = messageService.queryUnreadTotal(req);
		rs.setData(total);
		return rs;
	}
	
	/**
	 * 
	* @Description: 更新消息状态
	* @param @param request
	* @param @param req
	* @param @return
	* @param @throws Exception    
	* @return ModelAndView    
	* @throws
	* @author wuchl
	* @date 2016年6月7日
	 */
	@RequestMapping("/updateMessage")
	@ResponseBody
	public Result updateMessage(HttpServletRequest request,MessageReplayReq req) throws Exception{
		MessageReplayReq messageReq = new MessageReplayReq();
		messageReq.setId(req.getId());
		messageReq.setIsreply(req.getIsreply());
		Result rs =messageService.replayMessage(messageReq);
		return rs;
	}
}
