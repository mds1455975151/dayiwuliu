package com.tianrui.trwl.admin.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IFileOrgCargoService;
import com.tianrui.api.intf.IFreightService;
import com.tianrui.api.intf.IRouteService;
import com.tianrui.api.req.admin.FileOrgCargoReq;
import com.tianrui.api.req.front.cargoplan.FreightReq;
import com.tianrui.api.req.front.cargoplan.RouteReq;
import com.tianrui.api.resp.admin.FileOrgCargoResp;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.cargoplan.FreightResp;
import com.tianrui.api.resp.front.cargoplan.FreightlistResp;
import com.tianrui.api.resp.front.cargoplan.RouteResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

/**
 * 
 * @类描述：运价档案
 * @创建人：lsj
 * @创建时间：2016年5月26日上午9:36:23
 *
 * @修改人：lsj
 * @修改时间：2016年5月26日上午9:36:23
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/frieght")
public class FileFrieghtAction {
	
	@Autowired
	private IFreightService freightService;
	@Autowired
	private IRouteService routeService;
	@Autowired
	private IFileOrgCargoService fileOrgCargoService;
	
	@RequestMapping("/filePrice")
	public ModelAndView indexPage(HttpServletRequest request) throws Exception{
		Users user = SessionManager.getSessionMember(request);
		ModelAndView view = new ModelAndView();
		
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
		
		RouteReq req = new RouteReq();
		req.setOrganizationid(user.getOrgid());
		req.setStatus("1");
		List<RouteResp> list1 = routeService.findRouteByEntity(req);
		
		FileOrgCargoReq fr = new FileOrgCargoReq();
		fr.setOrgId(user.getOrgid());
		fr.setState("1");
		List<FileOrgCargoResp> f =fileOrgCargoService.queryMyCargoInfo(fr);
		
		view.addObject("route", list1);
		view.addObject("cargo", f);
		view.addObject("datenow", sdf.format(c.getTime()));
		view.setViewName("/file/filePrice/file_price");
		return view;
	}
	/**
	 * 
	 * @描述:Freight 查询
	 * @param req
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月26日上午10:21:43
	 */
	@RequestMapping("/findByFreightEntity")
	@ResponseBody
	public Result findByFreightEntity(FreightReq req,
			HttpServletRequest request
			) throws Exception{
		Users user = SessionManager.getSessionMember(request);
		req.setOrganizationid(user.getOrgid());
		Result rs = Result.getSuccessResult();
		PageResp<FreightlistResp> list = freightService.findByLisrEntity(req);
		rs.setData(list);
		return rs;
	}
	/**
	 * 
	 * @描述:Freight 添加
	 * @param req
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月26日上午11:39:29
	 */
	@RequestMapping("/saveFreight")
	@ResponseBody
	public Result saveFreight(
			FreightReq req,
			HttpServletRequest request
			) throws Exception{
		Result rs = Result.getSuccessResult();
		if(freightService.findByName(req.getFreightName()) != null){
			rs.setCode("11");
			rs.setError("策略名称已存在，请重新输入");
		}else {
			req.getFreightName();
			Users user = SessionManager.getSessionMember(request);
			Date date = new Date();
			req.setId(UUIDUtil.getId());
			req.setStatus("0");
			req.setCreator(user.getAccount());
			req.setCreatetime(date.getTime());
			req.setOrganizationid(user.getOrgid());
			req.setOrganizationname(user.getOrgname());
			req.setAuditstatus("0");
			if(!freightService.saveEntity(req)){
				rs.setCode("1");
				rs.setError("添加失败");
			}
		}
		return rs;
	}
	/**
	 * 
	 * @描述:删除 freight
	 * @param id
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月26日下午1:40:45
	 */
	@RequestMapping("/delectFreight")
	@ResponseBody
	public Result delectFreight(
			@RequestParam(defaultValue = "") String id,
			HttpServletRequest request
			) throws Exception{
		
		Users user = SessionManager.getSessionMember(request);
		Result rs = Result.getSuccessResult();
		FreightReq req = new FreightReq();
		req.setId(id);
		req.setStatus("2");//删除
		Date data = new Date();
		req.setModifier(user.getAccount());
		req.setModifytime(data.getTime());
		rs = freightService.delectEntity(req);
		return rs;
	}
	/**
	 * 
	 * @描述:修改 freight
	 * @param req
	 * @param request
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月26日下午2:16:59
	 */
	@RequestMapping("/updateFreight")
	@ResponseBody
	public Result updateFreight(
			FreightReq req,
			HttpServletRequest request
			) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setModifier(user.getAccount());
		Date date = new Date();
		req.setModifytime(date.getTime());
		req.setAuditstatus("0");
		rs = freightService.updateEntity(req);
		return rs;
	}
	/** 策略停用*启用
	 * @throws Exception */
	@RequestMapping("/closeFreight")
	@ResponseBody
	public Result closeFreight(FreightReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setModifier(user.getAccount());
		Date date = new Date();
		req.setModifytime(date.getTime());
		rs = freightService.closeFreight(req);
		return rs;
	}
	
	/**
	 * 
	 * @描述:批量停用
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年7月6日上午11:37:43
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public Result updateStatus(String id,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		String[] idsrt = id.split(",");
		Users user = SessionManager.getSessionMember(request);
		Date date = new Date();
		FreightReq req = new FreightReq();
		req.setModifier(user.getAccount());
		req.setModifytime(date.getTime());
		req.setId(id);
		req.setStatus("1");
		freightService.batchUpdate(req);
		return rs;
	}
	
	/**
	 * 
	 * @描述:通过id查询freight
	 * @param id
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年5月26日下午3:02:14
	 */
	@RequestMapping("/findByFreightId")
	@ResponseBody
	public Result findByFreightId(
			@RequestParam(defaultValue = "") String id) throws Exception{
		Result rs = Result.getSuccessResult();
		FreightResp resp = freightService.findById(id);
		rs.setData(resp);
		return rs;
	}
	
}
