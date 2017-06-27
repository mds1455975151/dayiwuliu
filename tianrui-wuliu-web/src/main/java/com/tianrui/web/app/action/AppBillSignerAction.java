package com.tianrui.web.app.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.intf.IOrgSignerService;
import com.tianrui.api.intf.ISignerBillService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.admin.OrgSignerFindReq;
import com.tianrui.api.req.front.bill.AnlianBillSignerReq;
import com.tianrui.api.req.front.bill.BillConfirmPriceReq;
import com.tianrui.api.req.front.bill.SignerBillFindReq;
import com.tianrui.api.req.front.bill.SignerBillReq;
import com.tianrui.api.resp.admin.OrgSignerResp;
import com.tianrui.api.resp.front.bill.SignerBillResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/app/billSigner")
public class AppBillSignerAction {

	@Autowired
	ISignerBillService signerService;
	@Autowired
	IOrgSignerService  orgSignerService;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	IAnlianBillService anlianBillService;
	
	
	//查询我签收的运单
	@RequestMapping(value="/find",method=RequestMethod.POST)
	@ApiParamRawType(SignerBillFindReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult find(AppParam<SignerBillFindReq> appParam) throws Exception{
		AppResult rs = new AppResult();
		SignerBillFindReq req = appParam.getBody();
		Head vo = appParam.getHead();
		req.setReceiveMemberid(vo.getId());
		PaginationVO<SignerBillResp> resp =signerService.select(req);
		rs.setCode("000000");
		rs.setReturnData(resp);
		return rs;
	}
	//查询收货人
	@RequestMapping(value="/signer",method=RequestMethod.POST)
	@ApiParamRawType(OrgSignerFindReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult signer(AppParam<OrgSignerFindReq> appParam) throws Exception{
		AppResult rs = new AppResult();
		rs.setCode("000000");
		OrgSignerFindReq orgSigner = new OrgSignerFindReq();
		Head head = appParam.getHead();
		MemberResp member = systemMemberService.findById(head.getId());
		orgSigner.setOrgid(member.getOrganizationid());
		List<OrgSignerResp> signer = orgSignerService.findlist(orgSigner);
		rs.setReturnData(signer);
		return rs;
	}
	
	//安联运单签收
	@RequestMapping(value="/billSignAl",method=RequestMethod.POST)
	@ApiParamRawType(AnlianBillSignerReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult billSignDy(AppParam<AnlianBillSignerReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		rs.setCode("000000");
		AnlianBillSignerReq req = appParam.getBody();
		rs = anlianBillService.billSigner(req);
		return AppResult.valueOf(rs);
	}
	
	//前台运价确认
	@RequestMapping(value="/confirmTotalPrice",method=RequestMethod.POST)
	@ApiParamRawType(BillConfirmPriceReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult confirmTotalPrice(AppParam<BillConfirmPriceReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		Head head = appParam.getHead();
		BillConfirmPriceReq req = appParam.getBody();
		req.setCreater(head.getId());
		rs = signerService.BillConfirmPrice(req);
		return AppResult.valueOf(rs);
	}
}
