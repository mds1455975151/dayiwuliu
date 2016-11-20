package com.tianrui.quartz.pay;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.IPayInvoiceDetailService;
import com.tianrui.api.intf.IPayInvoiceService;
import com.tianrui.api.req.front.pay.PayInvoiceQueryReq;
import com.tianrui.common.utils.DateUtil;
@Component 
public class getNcDriverPay {
	
	private Logger logger = LoggerFactory.getLogger(getNcDriverPay.class);
	@Autowired
	protected IPayInvoiceDetailService payInvoiceDetailService;

//	@Scheduled(cron="0 0/30 *  * * ? ")
	@Scheduled(cron="0/5 * *  * * ? ")
    public void getncPay() {  
    	Long st = new Date().getTime();
    	logger.info("定时器[getncPay]启动.时间是 :" + DateUtil.getDateString());  
        try {
        	payInvoiceDetailService.ncDriverPay();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		logger.info("定时任务[getNcDriverPay]完成.耗时：{}",new Object[]{(new Date().getTime()-st)});
    }
}
