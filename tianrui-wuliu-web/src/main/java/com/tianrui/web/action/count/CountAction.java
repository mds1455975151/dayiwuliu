package com.tianrui.web.action.count;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.davidcarboni.cryptolite.HashMac;
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
	/** 运费折线
	 * @throws Exception */
	@RequestMapping("payLine")
	@ResponseBody
	public Result payLine() throws Exception{
		Result rs = Result.getSuccessResult();
		CountAddReq req = new CountAddReq();
		req.setType("5");
		List<CountAddResp> list = countAddService.selectByCondition(req);
		rs.setData(list);
		return rs;
	}
	
	/** 计划
	 * @throws Exception */
	@RequestMapping("plan")
	public ModelAndView plan() throws Exception{
		ModelAndView view = index();
		view.setViewName("count/plan");
		return view;
	}
	@RequestMapping("planLine")
	@ResponseBody
	public Result planLine() throws Exception{
		Result rs = Result.getSuccessResult();
		//1-路线，2货运，3-车辆，4-交易，5-运费
		CountAddReq req = new CountAddReq();
		req.setType("2");
		List<CountAddResp> alist = countAddService.selectByCondition(req);
		CountSumReq sum = new CountSumReq();
		sum.setType("2");
		List<CountSumResp> slist = countSumService.selectByCondition(sum);
		Map<String,List> map = new HashMap<String,List>();
		map.put("add", alist);
		map.put("sum", slist);
		rs.setData(map);
		return rs;
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
	@RequestMapping("vehicleLine")
	@ResponseBody
	public Result vehicleLine() throws Exception{
		Result rs = Result.getSuccessResult();
		//TODO
		//1-路线，2货运，3-车辆，4-交易，5-运费
		CountAddReq req = new CountAddReq();
		req.setType("3");
		List<CountAddResp> alist = countAddService.selectByCondition(req);
		CountSumReq sum = new CountSumReq();
		sum.setType("6");
		List<CountSumResp> actlist = countSumService.selectByCondition(sum);
		Map<String,List> map = new HashMap<String,List>();
		map.put("add", alist);
		map.put("act", actlist);
		rs.setData(map);
		return rs;
	}
	/** 初始页面查询
	 * @throws Exception */
	public ModelAndView index() throws Exception{
		ModelAndView view = new ModelAndView();
		CountSumReq req = new CountSumReq();
		req.setShowtime(getDay());
		List<CountSumResp> list = countSumService.selectByCondition(req);
		
		CountSumReq old = new CountSumReq();
		old.setShowtime(getOld30Day());
		List<CountSumResp> listold = countSumService.selectByCondition(old);
		//1-路线，2货运总量，3-车辆总数，4-交易总量，5-运费总额,6-活跃车辆
		//获取上月数据
		for (int i = 0; i < listold.size(); i++) {
			switch(listold.get(i).getType()){
			case "1":
				view.addObject("monthroute", listold.get(i));
				break;
			case "2":
				view.addObject("monthplan", listold.get(i));
				break;
			case "3":
				view.addObject("monthvehicle", listold.get(i));
				break;
			case "4":
				view.addObject("monthbill", listold.get(i));
				break;
			case "5":
				view.addObject("monthpay", listold.get(i));
				break;
			case "6":
				view.addObject("monthactvehicle", listold.get(i));
				break;
			}
		}
		//获取当日数据
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
	
	/** 上月最后一天*/
	public long getOld30Day(){
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.DATE, -1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
	public static void main(String[] args) {
		//1478793600000
		//1477756800000
		long a;
		a = Long.parseLong("1475251200000");
		
		Date date = new Date(a);
		String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
		System.out.println(format);
	}
	
}
