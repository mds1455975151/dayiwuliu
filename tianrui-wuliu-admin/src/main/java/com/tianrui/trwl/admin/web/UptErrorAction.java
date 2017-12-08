package com.tianrui.trwl.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tianrui.api.intf.IMoenyDisposeService;
import com.tianrui.api.intf.IUptErrorBillImgService;

/** 修改错误信息*/

@Controller
@RequestMapping("uptError")
public class UptErrorAction {

	@Autowired
	IUptErrorBillImgService uptErrorBillImgService;
	@Autowired
	IMoenyDisposeService moenyDisposeService;
	
	/***
	 * (六)
	 * @return
	 */
	@RequestMapping("saveWithDrawFail")
	public void saveWithDrawFail() throws Exception{
		System.out.println("开始");
		moenyDisposeService.saveWithDrawFail();
		System.out.println("执行结束");
	}
	
	/***
	 * (五)
	 * @return
	 */
	@RequestMapping("saveWithDrawSuccess")
	public void saveWithDrawSuccess() throws Exception{
		System.out.println("开始");
		moenyDisposeService.saveWithDrawSuccess();
		System.out.println("执行结束");
	}
	
	/***
	 * (四)
	 * @return
	 */
	@RequestMapping("saveWithDraw")
	public void saveWithDraw() throws Exception{
		System.out.println("开始");
		moenyDisposeService.saveWithDraw();
		System.out.println("结束");
	}
	
	/***
	 * (三)
	 * @return
	 */
	@RequestMapping("uptPandMoney")
	public void uptPandMoney() throws Exception{
		System.out.println("开始");
		moenyDisposeService.uptPandMoney();
		System.out.println("执行结束");
	}
	
	/***
	 * (二)
	 * @return
	 */
	@RequestMapping("oldALSaveBillMoney")
	public void oldALSaveBillMoney() throws Exception{
		System.out.println("开始");
		moenyDisposeService.oldALSaveBillMoney();
		System.out.println("执行结束");
	}
	
	/***
	 * (一)
	 * @return
	 */
	@RequestMapping("oldDYSaveBillMoney")
	public void saveOldDYBill() throws Exception{
		System.out.println("开始");
		moenyDisposeService.oldDYSaveBillMoney();
		System.out.println("执行结束");
	}
	
//	@RequestMapping("upt")
//	public Result upt() throws Exception{
//		Result rs = Result.getSuccessResult();
//		uptErrorBillImgService.uptBillImg();
//		return rs;
//	}
}
