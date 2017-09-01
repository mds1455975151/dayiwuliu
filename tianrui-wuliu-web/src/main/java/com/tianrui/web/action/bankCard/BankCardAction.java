package com.tianrui.web.action.bankCard;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.intf.bankcard.IMemberBankCardService;
import com.tianrui.api.req.bankcard.MemberBankCardReq;
import com.tianrui.api.req.front.vehicle.AddVehicleBankCardReq;
import com.tianrui.api.resp.bankcard.MemberBankCardResp;
import com.tianrui.api.resp.front.member.MemberInfoMassageResp;
import com.tianrui.api.resp.front.member.MemberInfoRecordResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.OwnerDriverService;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/bank/card")
public class BankCardAction {

	@Autowired
	IMemberBankCardService memberBankCardService;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	private ISystemMemberInfoService systemMemberInfoService;
	@Autowired
	OwnerDriverService ownerDriverService;

	/** 我的银行卡

	 * @throws Exception */
	@RequestMapping("page")
	public ModelAndView page(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("/bank/findBankCard");
		return view;
	}
	/** 我的银行卡
	 * @throws Exception */
	@RequestMapping("vender/page")
	public ModelAndView venderPage(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals(vo.getCompanypercheck(), Constant.AUTHSTATUS_PASS)){
			view.setViewName("/bank/company/findBankCard");
		}else{
			view.setViewName("/bank/findBankCard");
		}
		return view;
	}
	
	/** 添加页面
	 * @throws Exception */
	@RequestMapping("savePage")
	public ModelAndView savePage(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo = SessionManager.getSessionMember(request);
		MemberInfoMassageResp member = systemMemberService.findInfoMassageById(vo.getId());
		view.addObject("member", member);
		view.setViewName("/bank/saveBankCard");
		return view;
	}
	/**
	 * @Title: saveOwnerPage 
	 * @Description: 添加车主银行卡页面
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView    
	 * @throws
	 */
	@RequestMapping("saveOwnerPage")
	public ModelAndView saveOwnerPage(HttpServletRequest request)throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo = SessionManager.getSessionMember(request);
//		List<OwnerDriverResp> list = ownerDriverService.findOwnerById(vo.getId());
		List <MemberBankCardResp> list = memberBankCardService.findOwnerById(vo.getId());
		view.addObject("list",list);
		view.setViewName("/bank/saveOwnerBankCard");
		return view;
		
	}
	/**
	 * 
	 * @Title: addVehicleownerBankcard 
	 * @Description: TODO添加车主银行卡id
	 * @param @return
	 * @param @throws Exception   
	 * @return Result    
	 * @throws
	 */
	@RequestMapping("/addVehicleownerBankcard")
	@ResponseBody
	public Result addVehicleownerBankcard(HttpServletRequest request,String vehicleownerid)throws Exception {
		MemberVo vo = SessionManager.getSessionMember(request);
		AddVehicleBankCardReq addVehicle = new AddVehicleBankCardReq();
		addVehicle.setDriverid(vo.getId());
		addVehicle.setVehicleownerid(vehicleownerid);
		addVehicle.setId(UUIDUtil.getId());
		Result rs = ownerDriverService.addVehicleownerBankcard(addVehicle);
		
		return rs;
	}
	
	/** 添加页面
	 * @throws Exception */
	@RequestMapping("/vender/savePage")
	public ModelAndView venderSavePage(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals(vo.getCompanypercheck(), Constant.AUTHSTATUS_PASS)){
			MemberInfoRecordResp info = systemMemberInfoService.selectMemberInfo(vo.getId());
			view.addObject("info", info);
			view.setViewName("/bank/company/saveBankCard");
		}else{
			MemberInfoMassageResp member = systemMemberService.findInfoMassageById(vo.getId());
			view.addObject("member", member);
			view.setViewName("/bank/saveBankCard");
		}
		return view;
	}
	/** 查询开户行.
	 * @throws Exception */
	@RequestMapping("findAddress")
	@ResponseBody
	public Result findAddress(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = memberBankCardService.findBankSubbranch(id);
		return rs;
	}
	/** 查询开户行连行号.
	 * @throws Exception 
	 **/
	@RequestMapping("findBankNum")
	@ResponseBody
	public Result findBankNum(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = memberBankCardService.findBankNum(id);
		return rs;
	}
	/** 查询*/
	@RequestMapping("find")
	@ResponseBody
	public Result find(MemberBankCardReq req,HttpServletRequest request) throws Exception{
		Result rs = Result .getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreater(vo.getId());
		PaginationVO<MemberBankCardResp> page = memberBankCardService.selectBankCard(req);
		rs.setData(page);
		return rs;
	}
	
	@RequestMapping("uptAutidPage")
	public ModelAndView uptAutidPage(MemberBankCardReq req,HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo = SessionManager.getSessionMember(request);
		MemberInfoMassageResp member = systemMemberService.findInfoMassageById(vo.getId());
		Result rs = memberBankCardService.findBankCardById(req.getId());
		view.addObject("member", member);
		view.setViewName("/bank/uptBankCard");
		view.addObject("bank", rs.getData());
//		view.addObject("bankid", req.getId());
//		view.addObject("bankcard", req.getBankcard());
		return view;
	}
	
	@RequestMapping("/vender/uptAutidPage")
	public ModelAndView venderUptAutidPage(MemberBankCardReq req,HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals(vo.getCompanypercheck(), Constant.AUTHSTATUS_PASS)){
			MemberInfoRecordResp info = systemMemberInfoService.selectMemberInfo(vo.getId());
			view.addObject("info", info);
			view.addObject("bankid", req.getId());
			view.addObject("bankcard", req.getBankcard());
			view.setViewName("/bank/company/uptBankCard");
		}else{
			MemberInfoMassageResp member = systemMemberService.findInfoMassageById(vo.getId());
			view.addObject("member", member);
			view.setViewName("/bank/uptBankCard");
			view.addObject("bankid", req.getId());
			view.addObject("bankcard", req.getBankcard());
		}
		return view;
	}
	
	/** 重新认证*/
	@RequestMapping("uptAutid")
	@ResponseBody
	public Result uptAutid(MemberBankCardReq req,HttpServletRequest request) throws Exception{
		Result rs = Result .getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreater(vo.getId());
		rs = memberBankCardService.update(req);
		return rs;
	}
	
	/** 添加
	 * @throws Exception */
	@RequestMapping("save")
	@ResponseBody
	public Result save(MemberBankCardReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreater(vo.getId());
		rs = memberBankCardService.insertBankCard(req);
		return rs;
	}
	//查询银行类型
	@RequestMapping("bankCardType")
	@ResponseBody
	public Result bankCardType(String bankcode) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = memberBankCardService.findBankType();
		return rs;
	}
	//查询银行类型
	@RequestMapping("bankCardOnly")
	@ResponseBody
	public Result bankCardOnly(String bankcode,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		rs = memberBankCardService.findBankOnly(vo.getId(), bankcode);
		return rs;
	}
	
	/** 设置默认银行卡*/
	@RequestMapping("upt")
	@ResponseBody
	public Result upt(MemberBankCardReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreater(vo.getId());
		rs = memberBankCardService.defaulBankCard(req);
		return rs;
	}
	/**删除*/
	@RequestMapping("delt")
	@ResponseBody
	public Result delt(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = memberBankCardService.deltBankCard(id);
		return rs;
	}

	@RequestMapping("findAuditBankCard")
	@ResponseBody
	public Result findAuditBankCard(MemberBankCardReq req,HttpServletRequest request) throws Exception{
		Result rs = Result .getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreater(vo.getId());
		rs = memberBankCardService.findAuditBankCard(req);
		return rs;
	}
	
}
