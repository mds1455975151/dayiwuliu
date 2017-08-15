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
	/** 停用 desc1 = 0 为停用 1 启用*/
	Result delete(MerchantReq req)throws Exception;
	
	MerchantResp findByid(String id)throws Exception;
	
	void uptErrorMassage()throws Exception;
}
