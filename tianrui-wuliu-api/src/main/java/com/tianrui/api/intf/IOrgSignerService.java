package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.admin.OrgSignerFindReq;
import com.tianrui.api.req.admin.OrgSignerReq;
import com.tianrui.api.resp.admin.OrgSignerResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IOrgSignerService {

	public Result insert(OrgSignerReq req) throws Exception;
	
	public Result delete(String id)throws Exception;
	
	public Result update(OrgSignerReq req)throws Exception;
	
	public PaginationVO<OrgSignerResp> select(OrgSignerFindReq req)throws Exception;
	
	public List<OrgSignerResp> findlist(OrgSignerFindReq req)throws Exception;
	
	public Result detail(String id)throws Exception;
}
