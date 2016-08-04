package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.transfer.TransferReq;
import com.tianrui.api.resp.front.transfer.TransferResp;
import com.tianrui.common.vo.Result;
/**
 * 
 * @类描述：司机换班记录
 * @创建人：lsj
 * @创建时间：2016年7月31日下午2:14:46
 *
 * @修改人：lsj
 * @修改时间：2016年7月31日下午2:14:46
 * @修改备注：
 *
 */
public interface ITransferService {

	/** 修改--同意或拒绝交班申请*/
	public Result update(TransferReq req)throws Exception;
	/** 新增--发起交班申请*/
	public Result save(TransferReq req)throws Exception;
	/** 删除--司机收回转运请求*/
	public Result delete(String driverid)throws Exception;
	/** 查询--是否已申请过交班*/
	public int isHand(String driverid)throws Exception;
	/** 查询--是否有收到交班申请*/
	public List<TransferResp> isAccept(String driverid) throws Exception;
	
}
