package com.tianrui.quartz.anlian;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.resp.front.bill.AnlianBillResp;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.BillAnlianPosition;
import com.tianrui.service.mongo.BillAnlianPositionDao;
@Component 
public class AnlianBillPosition {
	
	private Logger logger = LoggerFactory.getLogger(AnlianBillPosition.class);
	@Autowired
	protected BillAnlianPositionDao billAnlianPositionDao;
	@Autowired
	protected IAnlianBillService anlianBillService;
	@Autowired
	protected IAnlianService anlianService;
	
	
//	@Scheduled(cron="0 0/30 *  * * ? ")
	@Scheduled(cron="0/5 * *  * * ? ")
    public void getncPay() {  
    	Long st = new Date().getTime();
    	logger.info("定时器[AnlianBillPosition]启动.时间是 :" + DateUtil.getDateString());  
        try {
        	AnlianBillFindReq req = new AnlianBillFindReq();
        	List<AnlianBillResp> list = anlianBillService.findAll(req);
        	for(AnlianBillResp resp : list){
        		Result rs = Result.getSuccessResult();
        		rs = anlianService.detail(resp.getBillno());
        		if("000000".equals(rs.getCode())){
        			JSONObject json = (JSONObject) rs.getData();
        			BillAnlianPosition t = new BillAnlianPosition();
        			PropertyUtils.copyProperties(t, json);
        			t.setShipmentno(resp.getBillno());
        			t.setCreatetime(System.currentTimeMillis());
	        		if(t!= null){
	        			billAnlianPositionDao.save(t);
	        		}
        		}
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		logger.info("定时任务[AnlianBillPosition]完成.耗时：{}",new Object[]{(new Date().getTime()-st)});
    }
}
