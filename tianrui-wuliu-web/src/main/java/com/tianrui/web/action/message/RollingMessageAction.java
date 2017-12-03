package com.tianrui.web.action.message;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.message.intf.IMessageRollingService;
import com.tianrui.api.resp.money.MessageRollingResp;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("/trwuliu/Member/rollingMessage")
public class RollingMessageAction {
	@Autowired
	protected IMessageRollingService messageService;
	
	/**
	 * 获取首页滚动消息
	 * @param request
	 * @param number
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findRollingMessage")
	@ResponseBody
	public Result findMessage(HttpServletRequest request,Integer number) throws Exception{
		Result rs = Result.getSuccessResult();
		if( number ==null || number ==0 ){
			number = 10;
		}
		List<MessageRollingResp> page = messageService.findRollingMessage(number);
		rs.setData(page);
		return rs;
	}
	/**
	 * 消息点击次数+1
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateConsultNumber")
	@ResponseBody
	public Result updateMessage(HttpServletRequest request,Long id) throws Exception{
		Result rs =messageService.updateConsultNumber(id);
		return rs;
	}
}
