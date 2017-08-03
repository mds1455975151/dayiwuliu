package com.tianrui.quartz.pushNCTask;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.admin.intf.IPayInvoiceService;
import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.intf.bankcard.IMemberBankCardService;
import com.tianrui.common.utils.DateUtil;

/**
  * cron配置
	"0 0 12 * * ?"    每天中午十二点触发 
	"0 15 10 ? * *"    每天早上10：15触发 
	"0 15 10 * * ?"    每天早上10：15触发 
	"0 15 10 * * ? *"    每天早上10：15触发 
	"0 15 10 * * ? 2005"    2005年的每天早上10：15触发 
	"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发 
	"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 
	"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
	"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发 
	"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
	"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发 
 *
 */
@Component  
public class NCPushSchedule {
	
	Logger logger = LoggerFactory.getLogger(NCPushSchedule.class);
	
	@Autowired
	private ISystemMemberInfoService systemMemberInfoService;
	@Autowired
	IMemberBankCardService memberBankCardService;
	@Autowired
	private IPayInvoiceService payInvoiceService;

	/**
	 * @annotation 定时查询供应商NC审核状态并回写
	 */
	@Scheduled(cron="0 0/3 *  * * ? ")
	public void callBackMemberPushStatus(){
		Long st = new Date().getTime();
		logger.info("定时器查询供应商NC审核状态[callBackMemberPushStatus]启动.时间是 :" + DateUtil.getDateString());  
		systemMemberInfoService.callBackMemberPushStatus();
		logger.info("定时器查询供应商NC审核状态[callBackMemberPushStatus]结束.耗时/毫秒 :" + (new Date().getTime()-st));  
		
	}

	/**
     * @annotation 定时推送银行卡并回写推送状态
     */
	@Scheduled(cron="0 0/3 *  * * ? ")
	public void pushBankCardAndCallBackPushStatus(){
		Long st = new Date().getTime();
		logger.info("定时器推送银行卡[pushBankCardAndCallBackPushStatus]启动.时间是 :" + DateUtil.getDateString());  
		memberBankCardService.pushBankCardAndCallBackPushStatus();
		logger.info("定时器推送银行卡[pushBankCardAndCallBackPushStatus]结束.耗时/毫秒 :" + (new Date().getTime()-st));  
		
	}

//	@Scheduled(cron="0 0/3 *  * * ? ")
//	public void callBackPayInvoicePaidAmount(){
//		payInvoiceService.callBackPayInvoicePaidAmount();
//	}

	/**
     * @annotation 定时查询支付状态和金额并回写
     */
	@Scheduled(cron="0 0/3 *  * * ? ")
	public void callBackPayInvoicePayStatus(){
		Long st = new Date().getTime();
		logger.info("定时器查询支付状态和金额[callBackPayInvoicePayStatus]启动.时间是 :" + DateUtil.getDateString());  
		payInvoiceService.callBackPayInvoicePayStatus();
		logger.info("定时器查询支付状态和金额[callBackPayInvoicePayStatus]结束.耗时/毫秒 :" + (new Date().getTime()-st));  
	}
	
}
