package com.tianrui.trwl.admin.web;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.message.intf.IMessageGroupService;
import com.tianrui.api.req.groupMsg.CustomRcordReq;
import com.tianrui.api.req.groupMsg.GroupMsgSaveReq;
import com.tianrui.api.req.groupMsg.MemberGroupReq;
import com.tianrui.api.req.groupMsg.MessageGroupPushReq;
import com.tianrui.api.req.groupMsg.MessageGroupReq;
import com.tianrui.api.req.groupMsg.PushGroupMessageReq;
import com.tianrui.api.req.money.TrackSelectReq;
import com.tianrui.api.resp.groupMsg.MessageGroupPushResp;
import com.tianrui.api.resp.groupMsg.MessageGroupResp;
import com.tianrui.api.resp.money.CustomRcordResp;
import com.tianrui.api.tracking.intf.ITrackingService;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;


@Controller
@RequestMapping("/admin/aberrant")
public class AberrantAction {
	
	@Autowired
	ITrackingService trackingService;
	@Autowired
	IMessageGroupService messageGroupService;
	
	@RequestMapping("/aberdeal")//浏览器访问路径  /admin/aberrant  +  /aberdeal
	public ModelAndView aberdeal(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/aberrant/aberdeal");//项目中页面放置的路径
		return view;
	}
	@RequestMapping("/abermessage")
	public ModelAndView abermessage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/aberrant/abermessage");
		return view;
	}
	@RequestMapping("/abergroup")
	public ModelAndView abergroup(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/aberrant/abergroup");
		return view;
	}
	
	@RequestMapping("select")
	@ResponseBody//返回数据      不写的话  返回的是页面
	public Result select(TrackSelectReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<CustomRcordResp> page = trackingService.select(req);
		rs.setData(page);
		return rs;
	}
	
	/** 群体消息*/
	@RequestMapping("selectMsgGroup")
	@ResponseBody//返回数据      不写的话  返回的是页面
	public Result selectMsgGroup(MessageGroupReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<MessageGroupResp> page = messageGroupService.selectMsgGroup(req);
		rs.setData(page);
		return rs;
	}
	/** 群体消息更新
	 * @throws Exception */
	@RequestMapping("updategroup")
	@ResponseBody
	public Result updategroup(MemberGroupReq req,HttpServletRequest request) throws Exception{
		Users user = SessionManager.getSessionMember(request);
		Result rs = Result.getSuccessResult();
		rs = messageGroupService.uptMemberGroup(req);
		return rs;
	}
	/**消息维护*/
	@RequestMapping("selectMsgGroupPush")
	@ResponseBody
	public Result selectMsgGroupPush(MessageGroupPushReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<MessageGroupPushResp> page = messageGroupService.selectMsgGroupPush(req);
		rs.setData(page);
		return rs;
	}
	/**消息维护新增*/
	@RequestMapping("groupPushMsg")
	@ResponseBody
	public Result groupPushMsg(GroupMsgSaveReq req,HttpServletRequest request) throws Exception{
		Users user = SessionManager.getSessionMember(request);
		Result rs = Result.getSuccessResult();
		rs = messageGroupService.groupPushMsg(req);
		return rs;
	}
	/** 异常处理发送消息推送
	 * @throws Exception */
	@RequestMapping("pushGroupMsg")
	@ResponseBody
	public Result pushGroupMsg(PushGroupMessageReq req,HttpServletRequest request) throws Exception{
		Users user = SessionManager.getSessionMember(request);
		Result rs = Result.getSuccessResult();
		req.setSysUser(user.getAccount());
		rs = messageGroupService.pushGroupMsg(req);
		return rs;
	}
	/** 异常处理关闭查看操作
	 * @throws Exception */
	@RequestMapping("customRcord")
	@ResponseBody
	public Result customRcord(CustomRcordReq req,HttpServletRequest request) throws Exception{
		Users user = SessionManager.getSessionMember(request);
		Result rs = Result.getSuccessResult();
		rs = messageGroupService.uptErrMsg(req);
		return rs;
	}
}
