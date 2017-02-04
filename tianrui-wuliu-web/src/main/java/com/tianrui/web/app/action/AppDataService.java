package com.tianrui.web.app.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IDataService;
import com.tianrui.api.req.data.WebDictReq;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.resp.data.WebDictResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/***
 * 移动端获取数据类型
 * @author jh
 *
 */
@Controller
@RequestMapping("/app/data")
public class AppDataService {

	@Autowired
	IDataService dataService;
	
	/** 移动端获取数据类型*/
	@RequestMapping("find")
	@ApiParamRawType(WebDictReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult find(AppParam<WebDictReq> appParam) throws Exception{
		AppResult ar = new AppResult();
		WebDictReq req = appParam.getBody();
		List<WebDictResp> list = dataService.find(req);
		
		ar.setCode("000000");
		ar.setReturnData(list);
		return ar;
	}
}
