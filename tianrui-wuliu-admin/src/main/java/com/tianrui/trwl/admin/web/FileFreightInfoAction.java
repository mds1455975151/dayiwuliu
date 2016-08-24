package com.tianrui.trwl.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IFreightInfoService;
import com.tianrui.api.intf.IFreightService;
import com.tianrui.api.req.admin.freight.AdminFreightReq;
import com.tianrui.api.resp.admin.freight.AdminFreightResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

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
	
	/** 运价策略审核查询页面*/
	@RequestMapping("index")
	@ResponseBody
	public Result index(AdminFreightReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<AdminFreightResp> resp = freightInfoService.find(req);
		rs.setData(resp);
		return rs;
	}
}
