package com.tianrui.web.app.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IOrgSignerService;
import com.tianrui.api.intf.ISignerBillService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.admin.OrgSignerFindReq;
import com.tianrui.api.req.front.bill.SignerBillReq;
import com.tianrui.api.resp.admin.OrgSignerResp;
import com.tianrui.api.resp.front.bill.SignerBillResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/billSigner")
public class AppSignerBillAction {

	@Autowired
	ISignerBillService signerService;
	@Autowired
	IOrgSignerService  orgSignerService;
	@Autowired
	private ISystemMemberService systemMemberService;
	
	
	//查询我签收的运单
	@RequestMapping(value="/find",method=RequestMethod.POST)
	@ApiParamRawType(SignerBillReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult find(AppParam<SignerBillReq> appParam) throws Exception{
		AppResult rs = new AppResult();
		SignerBillReq req = appParam.getBody();
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
		//TODO
		OrgSignerFindReq orgSigner = new OrgSignerFindReq();
		Head head = appParam.getHead();
		MemberResp member = systemMemberService.findById(head.getId());
		orgSigner.setOrgid(member.getOrganizationid());
		List<OrgSignerResp> signer = orgSignerService.findlist(orgSigner);
		rs.setReturnData(signer);
		return rs;
	}
}
