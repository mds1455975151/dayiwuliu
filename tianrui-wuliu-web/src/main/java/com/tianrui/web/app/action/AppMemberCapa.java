package com.tianrui.web.app.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IMemberCapaService;
import com.tianrui.api.req.front.capa.CapaReq;
import com.tianrui.api.resp.front.capa.MemberCapaListResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.MemberVoService;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;
import com.tianrui.web.util.SessionManager;

/**
 * 移动端运力共享
 * @类描述：
 * @创建人：lsj
 * @创建时间：2016年8月31日上午9:35:49
 *
 * @修改人：lsj
 * @修改时间：2016年8月31日上午9:35:49
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/app/capa")
public class AppMemberCapa {

	@Autowired
	IMemberCapaService memberCapa;
	@Autowired
	MemberVoService memberVoService;
	
	/** 查询我的运力
	 * @throws Exception */
	@RequestMapping(value="/index",method=RequestMethod.POST)
	@ApiParamRawType(CapaReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult index(AppParam<CapaReq> appParam) throws Exception{
		AppResult app = new AppResult();
		
		Head head = appParam.getHead();
		CapaReq req = appParam.getBody();
		req.setMemberid(head.getId());
		PaginationVO<MemberCapaListResp> vo = memberCapa.index(req);
		
		app.setCode("000000");
		app.setReturnData(vo.getList());
		app.setTotal(vo.getTotalInt());
		return app;
	}
	/**查询车辆*/
	@RequestMapping(value="/selectVehicle",method=RequestMethod.POST)
	@ApiParamRawType(CapaReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult selectVhicle(AppParam<CapaReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		CapaReq req = appParam.getBody();
		rs = memberCapa.selectVehicle(req);
		return AppResult.valueOf(rs);
	}
	/**添加车辆*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ApiParamRawType(CapaReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult save(AppParam<CapaReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		Head head = appParam.getHead();
		CapaReq req = appParam.getBody();
		req.setMemberid(head.getId());
		req.setCellphone(head.getAccount());
		rs = memberCapa.save(req);
		return AppResult.valueOf(rs);
	}
	/** 检查该运力是否有未完成的运单*/
	@RequestMapping(value="/checkCapaState",method=RequestMethod.POST)
	@ApiParamRawType(CapaReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult checkCapaState(AppParam<CapaReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		Result rs = memberCapa.checkCapaState(appParam.getBody().getId(),uId);
		return AppResult.valueOf(rs);
	}
	/** 删除我的运力*/
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ApiParamRawType(CapaReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult delete(AppParam<CapaReq> appParam) throws Exception{
		MemberVo vo = memberVoService.get(appParam.getHead().getId());
		CapaReq capa = appParam.getBody();
		capa.setMemberid(vo.getId());
		capa.setCellphone(vo.getCellphone());
		Result rs = memberCapa.delete(capa);
		return AppResult.valueOf(rs);
	}
}
