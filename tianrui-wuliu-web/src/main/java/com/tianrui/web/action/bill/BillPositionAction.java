package com.tianrui.web.action.bill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IBillService;
import com.tianrui.api.resp.front.bill.BillPositionResp;
import com.tianrui.common.vo.Result;
/**
 * 获取运单轨迹信息
 * @author jh
 *
 */
@Controller
@RequestMapping("/trwuliu/billPosition")
public class BillPositionAction {

	@Autowired
	IBillService billService;
	
	@RequestMapping(value="/index",method=RequestMethod.POST)
	@ResponseBody
	public Result index(String bid) throws Exception{
		Result rs = new Result();
		rs.setCode("000000");
		List<BillPositionResp> list = billService.getBillPosition(bid);
		rs.setData(list);
		return rs;
	}
}
