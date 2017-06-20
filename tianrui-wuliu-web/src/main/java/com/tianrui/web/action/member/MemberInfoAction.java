package com.tianrui.web.action.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.resp.front.member.MemberInfoRecordResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/Memberinfo")
public class MemberInfoAction {

	@Autowired
	private ISystemMemberInfoService systemMemberInfoService;
	
	@RequestMapping("page")
	public ModelAndView page(String type,HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo = SessionManager.getSessionMember(request);
		MemberInfoRecordResp resp = systemMemberInfoService.selectMemberInfo(vo.getId());
		
		switch (type) {
		case "company":
			view.setViewName("member/info/company");
			break;
		case "driver":
			view.setViewName("member/info/driver");
			break;
		case "user":
			view.setViewName("member/info/user_member");
			break;
		}
		view.addObject("member", resp);
		return view;
	}
}
