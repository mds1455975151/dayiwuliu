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
 * @修改人：guyuke
 * @修改时间：2017年06月09日下午4:04:00
 * @修改备注：①新增关于我们画面；
 *            ②新增联系我们画面。
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
	public Result billLine(CountAddReq req) throws Exception{
		//1-路线，2货运，3-车辆，4-交易，5-运费
		Result rs = Result.getSuccessResult();
		req.setType("4");
		List<CountAddResp> list = countAddService.selectByCondition(req);
		rs.setData(list);
		return rs;
	}
	
	/** 运费
	 * @throws Exception */
	@RequestMapping("pay")
	public ModelAndView pay(CountAddReq req) throws Exception{
		ModelAndView view = index();
		req.setType("6");
		List<CountAddResp> list = countAddService.selectByCondition(req);
		view.addObject("paybill", list);
		view.setViewName("count/pay");
		return view;
	}
	/** 运费折线
	 * @throws Exception */
	@RequestMapping("payLine")
	@ResponseBody
	public Result payLine(CountAddReq req) throws Exception{
		Result rs = Result.getSuccessResult();
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
	/** 货运基地
	 * @throws Exception */
	@RequestMapping("freightDemand")
	public ModelAndView freightDemand() throws Exception{
		ModelAndView view = index();
		view.setViewName("count/freightDemand");
		return view;
	}
	/** 货运计划
	 * @throws Exception */
	@RequestMapping("freightPlan")
	public ModelAndView freightPlan() throws Exception{
		ModelAndView view = index();
		view.setViewName("count/freightPlan");
		return view;
	}
	/** 在途运单
	 * @throws Exception */
	@RequestMapping("onWaybill")
	public ModelAndView onWaybill() throws Exception{
		ModelAndView view = index();
		view.setViewName("count/onWaybill");
		return view;
	}
	@RequestMapping("planLine")
	@ResponseBody
	public Result planLine(Integer pageNo,Integer pageSize) throws Exception{
		Result rs = Result.getSuccessResult();
		//1-路线，2货运，3-车辆，4-交易，5-运费
		CountAddReq req = new CountAddReq();
		req.setPageNo(pageNo);
		req.setPageSize(pageSize);
		req.setType("2");
		List<CountAddResp> alist = countAddService.selectByCondition(req);
		CountSumReq sum = new CountSumReq();
		sum.setPageNo(pageNo);
		sum.setPageSize(pageSize);
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
		
		CountSumReq sum = new CountSumReq();
		sum.setShowtime(getDay());
		sum.setType("8");
		sum.setStype("route");
		List<CountSumResp> list = countSumService.selectByCondition(sum);
		view.addObject("routemax", list);
		view.setViewName("count/route");
		return view;
		
	}
	@RequestMapping("routeLine")
	@ResponseBody
	public Result routeLine(CountSumReq sum ,String routename) throws Exception{
		Result rs = Result.getSuccessResult();
		sum.setShowtime(getDay());
		sum.setType("8");
		sum.setStype("route");
		sum.setDesc1(routename);
		List<CountSumResp> list = countSumService.selectByCondition(sum);
		rs.setData(list);
		return rs;
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
	public Result vehicleLine(Integer pageNo,Integer pageSize) throws Exception{
		Result rs = Result.getSuccessResult();
		//TODO
		//1-路线，2货运，3-车辆，4-交易，5-运费
		CountAddReq req = new CountAddReq();
		req.setPageNo(pageNo);
		req.setPageSize(pageSize);
		req.setType("3");
		List<CountAddResp> alist = countAddService.selectByCondition(req);
		CountSumReq sum = new CountSumReq();
		sum.setType("6");
		sum.setPageNo(pageNo);
		sum.setPageSize(pageSize);
		List<CountSumResp> actlist = countSumService.selectByCondition(sum);
		Map<String,List> map = new HashMap<String,List>();
		map.put("add", alist);
		map.put("act", actlist);
		rs.setData(map);
		return rs;
		
		
		
		
	}
	
	/** 关于我们
	 * @throws Exception */
	@RequestMapping("aboutUs")
	public ModelAndView aboutUs() throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("count/aboutus");
		return view;
	}
	
	/** 联系我们
	 * @throws Exception */
	@RequestMapping("contactUs")
	public ModelAndView countactUs() throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("count/contactus");
		return view;
	}
	
	/** 初始页面查询
	 * @throws Exception */
	public ModelAndView index() throws Exception{
		ModelAndView view = new ModelAndView();
		CountSumReq req = new CountSumReq();
		//总量显示昨天数据
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
	// 昨天
	public long getDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
	/** 上个月最后一天*/
	public long getOld30Day(){
		Calendar c = Calendar.getInstance();   
	    c.set(Calendar.DAY_OF_MONTH,0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
}
