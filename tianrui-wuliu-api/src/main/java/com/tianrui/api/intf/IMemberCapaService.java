package com.tianrui.api.intf;

import com.tianrui.api.req.front.capa.CapaReq;
import com.tianrui.api.resp.front.capa.MemberCapaListResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 
 * @类描述：运力资源共享
 * @创建人：lsj
 * @创建时间：2016年8月31日上午9:40:50
 *
 * @修改人：lsj
 * @修改时间：2016年8月31日上午9:40:50
 * @修改备注：
 *
 */
public interface IMemberCapaService {
	/** 我的运力查询*/
	PaginationVO<MemberCapaListResp> index(CapaReq req)throws Exception;
	/** 查询运力*/
	Result selectVehicle(CapaReq req)throws Exception;
	/** 添加运力*/
	Result save(CapaReq req)throws Exception;
	/** 同意/拒绝*/
	Result update(CapaReq req)throws Exception;
	/** 删除我的运力*/
	Result delete(CapaReq req)throws Exception;
}
