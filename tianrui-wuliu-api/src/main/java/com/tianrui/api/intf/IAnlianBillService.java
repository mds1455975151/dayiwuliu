package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.req.front.bill.AnlianBillSaveReq;
import com.tianrui.api.req.front.bill.AnlianBillSignerReq;
import com.tianrui.api.req.front.bill.AnlianBillUpdateReq;
import com.tianrui.api.req.front.bill.BillBankReq;
import com.tianrui.api.resp.front.bill.AnlianBillResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IAnlianBillService {
	/** 新增安联运单*/
	public Result alBillSave(AnlianBillSaveReq req)throws Exception;
	/** 查询开票运单分页*/
	public PaginationVO<AnlianBillResp> find(AnlianBillFindReq req)throws Exception;
	/** 查询开票运单详情*/
	public Result findByid(AnlianBillFindReq req)throws Exception;
	/** 查询所有*/
	public List<AnlianBillResp> findAll(AnlianBillFindReq req)throws Exception;
	/** 查询安联运单轨迹*/
	public Result findPosition(AnlianBillFindReq req)throws Exception;
	
	public Result update(AnlianBillUpdateReq req)throws Exception;
	/** 安联运单签收*/
	public Result billSigner(AnlianBillSignerReq req)throws Exception;
	
	public Result uptBankCard(BillBankReq req) throws Exception;
}
