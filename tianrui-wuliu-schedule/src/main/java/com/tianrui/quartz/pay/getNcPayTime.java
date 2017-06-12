package com.tianrui.quartz.pay;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.IPayInvoiceService;
import com.tianrui.api.req.front.pay.PayInvoiceQueryReq;
import com.tianrui.common.utils.DateUtil;
@Component 
public class getNcPayTime {
	
	private Logger logger = LoggerFactory.getLogger(getNcPayTime.class);
	@Autowired
	protected IPayInvoiceService payInvoiceService;

	//@Scheduled(cron="0 0/30 *  * * ? ")
    public void getncPay() {  
    	Long st = new Date().getTime();
    	logger.info("定时器[getncPay]启动.时间是 :" + DateUtil.getDateString());  
    	int count=0; 
        try {
        	PayInvoiceQueryReq req = new PayInvoiceQueryReq();
        	req.setPaystatus("2");
        	payInvoiceService.queryNCPayStatus(req);
        	count++;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		logger.info("定时任务[getncPay]完成.处理数据{}条,耗时：{}",new Object[]{count,(new Date().getTime()-st)});
    }
}
