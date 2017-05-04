package com.tianrui.web.app.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.resp.front.bill.AnlianBillResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;
@Controller
@RequestMapping("/app/billAnlian")
public class AppBillAnlianAction {

	@Autowired
	IAnlianBillService anlianBillService;
	@Autowired
	IAnlianService anlianService;
	@Autowired
	ISystemMemberService systemMemberService;
	
	@RequestMapping("detail")
	@ApiParamRawType(AnlianBillFindReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult detail(AppParam<AnlianBillFindReq> appParam) throws Exception{
		AppResult as = new AppResult();
		as.setCode("000000");
		AnlianBillFindReq req = appParam.getBody();
		Result rs = anlianBillService.findByid(req);
		AnlianBillResp bill = (AnlianBillResp) rs.getData();
		MemberResp resp = systemMemberService.findById(bill.getDriverid());
		bill.setDrivertel(resp.getCellPhone());
		//安联账户
		bill.setAldriverid(resp.getAldriverid());
		as.setReturnData(bill);
		return as;
	}
	
	/**查询列表
	 * @throws Exception */
	@RequestMapping("page")
	@ApiParamRawType(AnlianBillFindReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult page(AppParam<AnlianBillFindReq> appParam) throws Exception{
		AppResult rs = new AppResult();
		Head head = appParam.getHead();
		AnlianBillFindReq req = appParam.getBody();
		if("driver".equals(req.getType())){
			req.setDriverid(head.getId());
		}else if("vender".equals(req.getType())){
			req.setVenderid(head.getId());
		}else if("owner".equals(req.getType())){
			req.setOwnerid(head.getId());
		}else{
			rs.setCode("1");
			rs.setMessage("请选择身份");
		}
		PaginationVO<AnlianBillResp> page = anlianBillService.find(req);
		rs.setReturnData(page.getList());
		rs.setCode("000000");
		rs.setTotal(page.getTotalInt());
		return rs;
	}
	
	/** 查询运单轨迹
	 * @throws Exception */
	@RequestMapping("position")
	@ApiParamRawType(AnlianBillFindReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult position(AppParam<AnlianBillFindReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = anlianBillService.findPosition(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	
}
