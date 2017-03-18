package com.tianrui.web.action.member;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IPositionService;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.ReadExcel;

@Controller
@RequestMapping("excel")
public class ExcelTest {

	@Autowired
	IPositionService positionService;
	@RequestMapping("main")
	@ResponseBody
	public Result index() throws IOException{
//		positionService.update(ReadExcel.readXls());
		return null;
	}
}
