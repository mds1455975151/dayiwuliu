package com.tianrui.api.admin.intf;


import com.tianrui.api.req.admin.merchant.MerchantReq;
import com.tianrui.api.resp.admin.merchant.MerchantResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 客商档案接口
 * @author jh
 *
 */
public interface IMerchantService {

	/**客商档案分页查询*/
	PaginationVO<MerchantResp> find(MerchantReq req)throws Exception;
	/**新增
	 * @throws Exception */
	Result insert(MerchantReq req) throws Exception;
	/**修改*/
	Result update(MerchantReq req)throws Exception;
	/**删除 假删 desc1 = 0 为删除*/
	Result delete(MerchantReq req)throws Exception;
	
	MerchantResp findByid(String id)throws Exception;
}
