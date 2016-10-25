package com.tianrui.quartz.plan;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianrui.api.intf.IPayInvoiceService;
import com.tianrui.api.req.front.pay.PayInvoiceQueryReq;
import com.tianrui.api.resp.pay.PayInvoiceResp;
import com.tianrui.common.utils.DateUtil;

public class getNcPayTime {
	/**
	 * 定时任务参数
	 */
	private String para ; 
	@Autowired
	protected IPayInvoiceService payInvoiceService;

	public String getPara() {  
        return para;  
    }     
    public void setPara(String para) {  
        this.para = para;  
    }     
    
    public void getncPay() {  
    	Long st = new Date().getTime();
        System.out.println("nc定时器正常启动:"  + " ! 时间是 :" + DateUtil.getDateString());  
        //获取已过时的货运计划
        try {
        	PayInvoiceQueryReq req = new PayInvoiceQueryReq();
        	req.setPaystatus("2");
        	payInvoiceService.queryNCPayStatus(req);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("nc定时任务完成总时间："+(new Date().getTime()-st));
    }
}
