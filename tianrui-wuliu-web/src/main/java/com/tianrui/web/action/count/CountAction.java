package com.tianrui.web.action.count;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ICountAddService;
import com.tianrui.api.intf.ICountSumService;
import com.tianrui.api.req.count.CountAddReq;
import com.tianrui.api.req.count.CountSumReq;
import com.tianrui.api.resp.count.CountAddResp;
import com.tianrui.api.resp.count.CountSumResp;
import com.tianrui.common.vo.Result;

/**
 * 
 * @类描述：统计页面展示
 * @创建人：lsj
 * @创建时间：2016年10月28日下午4:14:39
 *
 * @修改人：lsj
 * @修改时间：2016年10月28日下午4:14:39
 * @修改备注：
 *
 */
@Controller
@RequestMapping("count")
public class CountAction {
	@Autowired
	ICountSumService countSumService;
	@Autowired
	ICountAddService countAddService;
	
	/** 运单统计
	 * @throws Exception */
	@RequestMapping("bill")
	public ModelAndView bill() throws Exception{
		ModelAndView view = index();
		view.setViewName("count/bill");
		return view;
	}
	@RequestMapping("billLine")
	@ResponseBody
	public Result billLine() throws Exception{
		//1-路线，2货运，3-车辆，4-交易，5-运费
		Result rs = Result.getSuccessResult();
		CountAddReq req = new CountAddReq();
		req.setType("1");
		List<CountAddResp> list = countAddService.selectByCondition(req);
		rs.setData(list);
		return rs;
	}
	
	/** 运费
	 * @throws Exception */
	@RequestMapping("pay")
	public ModelAndView pay() throws Exception{
		ModelAndView view = index();
		view.setViewName("count/pay");
		return view;
	}
	
	/** 计划
	 * @throws Exception */
	@RequestMapping("plan")
	public ModelAndView plan() throws Exception{
		ModelAndView view = index();
		view.setViewName("count/plan");
		return view;
	}
	
	/** 路线
	 * @throws Exception */
	@RequestMapping("route")
	public ModelAndView route() throws Exception{
		ModelAndView view = index();
		view.setViewName("count/route");
		return view;
		
	}
	
	/** 车辆
	 * @throws Exception */
	@RequestMapping("vehicle")
	public ModelAndView vehicle() throws Exception{
		ModelAndView view = index();
		view.setViewName("count/vehicle");
		return view;
	}
	/** 初始页面查询
	 * @throws Exception */
	public ModelAndView index() throws Exception{
		ModelAndView view = new ModelAndView();
		CountSumReq req = new CountSumReq();
		req.setShowtime(getDay());
		List<CountSumResp> list = countSumService.selectByCondition(req);
		//1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额,6-活跃车辆
		for (int i = 0; i < list.size(); i++) {
			switch(list.get(i).getType()){
			case "1":
				view.addObject("route", list.get(i));
				break;
			case "2":
				view.addObject("plan", list.get(i));
				break;
			case "3":
				view.addObject("vehicle", list.get(i));
				break;
			case "4":
				view.addObject("bill", list.get(i));
				break;
			case "5":
				view.addObject("pay", list.get(i));
				break;
			case "6":
				view.addObject("actvehicle", list.get(i));
				break;
			}
		}
		return view;
	}
	
	public long getDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
}
