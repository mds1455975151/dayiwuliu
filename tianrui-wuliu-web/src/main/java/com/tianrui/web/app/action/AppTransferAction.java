package com.tianrui.web.app.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.intf.ITransferService;
import com.tianrui.api.req.front.member.MemberPushSaveReq;
import com.tianrui.api.req.front.transfer.TransferReq;
import com.tianrui.api.resp.front.transfer.TransferResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
 * @类描述：司机换班
 * @创建人：lsj
 * @创建时间：2016年8月4日上午10:54:40
 *
 * @修改人：lsj
 * @修改时间：2016年8月4日上午10:54:40
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/app/transfer")
public class AppTransferAction {

	@Autowired
	ITransferService transferService;
	@Autowired
	IBillService billService;
	@Autowired
	ISystemMemberInfoService systemMemberInfoService;
	/**
	 * 
	 * @描述:转运请求
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年8月4日上午10:58:37
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ApiParamRawType(TransferReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult save(AppParam<TransferReq> appParam) throws Exception{
		Date date = new Date();
		TransferReq req = appParam.getBody();
		req.setId(UUIDUtil.getId());
		req.setStatus("0");// 0-未处理，1-同意，2 -拒绝'
		req.setIsvalid("1");//0-已删除 1-正常 2-被回收
		req.setStarttime(date.getTime());
		Result rs = transferService.save(req);
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 
	 * @描述:是否同意换班
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年8月4日上午11:28:52
	 */
	@RequestMapping(value="/isReply",method=RequestMethod.POST)
	@ApiParamRawType(TransferReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult isReply(AppParam<TransferReq> appParam) throws Exception{
		Date date = new Date();
		TransferReq req = appParam.getBody();
		req.setId(UUIDUtil.getId());
		req.setSendtime(date.getTime());
		Result rs = transferService.update(req);
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 
	 * @描述:收回换班请求
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年8月4日上午11:28:52
	 */
	@RequestMapping(value="/isback",method=RequestMethod.POST)
	@ApiParamRawType(TransferReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult isback(AppParam<TransferReq> appParam) throws Exception{
		String driverid = appParam.getHead().getId();
		Result rs = transferService.delete(driverid);
		return AppResult.valueOf(rs);
	}
	/**
	 * 
	 * @描述:是否已申请过交班
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年8月4日下午2:16:20
	 */
	@RequestMapping(value="/isHand",method=RequestMethod.POST)
	@ApiParamRawType(TransferReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult isHand(AppParam<TransferReq> appParam)throws Exception{
		Result rs = Result.getSuccessResult();
		String driverid = appParam.getHead().getId();
		int a = transferService.isHand(driverid);
		rs.setData(a);
		return AppResult.valueOf(rs);
	}
	/**
	 * 
	 * @描述:是否有收到交班申请
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年8月4日下午2:25:55
	 */
	@RequestMapping(value="/isAccept",method=RequestMethod.POST)
	@ApiParamRawType(TransferReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult isAccept(AppParam<TransferReq> appParam) throws Exception{
		String driverid = appParam.getHead().getId();
		List<TransferResp> list = transferService.isAccept(driverid);
		Result rs = Result.getSuccessResult();
		rs.setData(list);
		return AppResult.valueOf(rs);
	}
	/**
	 * 
	 * @描述:查询我所属的车主
	 * @param appParam
	 * @return
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年8月4日下午2:42:13
	 */
	@RequestMapping(value="/headView",method=RequestMethod.POST)
	@ApiParamRawType(TransferReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult headView(AppParam<TransferReq> appParam){
		Result rs = Result.getSuccessResult();
		String driverid = appParam.getHead().getId();
		rs = systemMemberInfoService.handView(driverid);
		return AppResult.valueOf(rs);
	}
}
