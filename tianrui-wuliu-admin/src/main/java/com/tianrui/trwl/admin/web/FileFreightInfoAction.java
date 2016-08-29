package com.tianrui.trwl.admin.web;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.admin.intf.IFreightInfoService;
import com.tianrui.api.intf.IFreightService;
import com.tianrui.api.req.admin.freight.AdminFreightReq;
import com.tianrui.api.req.admin.freight.AdminFreightUptReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.freight.AdminFreightResp;
import com.tianrui.api.resp.admin.freight.FreightLineResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

/**
 * 
 * @类描述：后台运价策略审核
 * @创建人：lsj
 * @创建时间：2016年8月20日下午2:22:13
 *
 * @修改人：lsj
 * @修改时间：2016年8月20日下午2:22:13
 * @修改备注：
 *
 */
@Controller
@RequestMapping("freightinfo")
public class FileFreightInfoAction {

	@Autowired
	private IFreightInfoService freightInfoService;
	
	@RequestMapping("freightlist")
	public ModelAndView freightlist(){
		ModelAndView view =new ModelAndView();
		view.setViewName("/file/filePrice/file_priceinfo");
		return view;
	}
	@RequestMapping("freightLine")
	public ModelAndView freightLine(AdminFreightReq req) throws Exception{
		ModelAndView view =new ModelAndView();
		view.setViewName("/file/filePrice/file_priceline");
		view.addObject("freightid", req.getId());
		return view;
	}
	
	/** 运价策略查询页面*/
	@RequestMapping("index")
	@ResponseBody
	public Result index(AdminFreightReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setOrganizationid(user.getOrgid());
		PageResp<AdminFreightResp> resp = freightInfoService.find(req);
		rs.setData(resp);
		return rs;
	}
	/** 运价策略审核查询页面*/
	@RequestMapping("indexAudit")
	@ResponseBody
	public Result indexAudit(AdminFreightReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PageResp<AdminFreightResp> resp = freightInfoService.find(req);
		rs.setData(resp);
		return rs;
	}
	/** 运价策略审核*/
	@RequestMapping("update")
	@ResponseBody
	public Result update(AdminFreightUptReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setUpdater(user.getAccount());
		rs = freightInfoService.update(req);
		return rs;
	}
	/** 运价策略变化折线图*/
	@RequestMapping("lineChart")
	@ResponseBody
	public Result lineChart(AdminFreightReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		req.setStatus("1");
		List<FreightLineResp> list = freightInfoService.lineChart(req);
		rs.setData(list);
		return rs;
	}
}
