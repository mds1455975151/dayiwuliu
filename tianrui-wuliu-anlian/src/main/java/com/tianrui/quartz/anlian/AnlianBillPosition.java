package com.tianrui.quartz.anlian;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.req.front.bill.AnlianBillUpdateReq;
import com.tianrui.api.resp.front.bill.AnlianBillResp;
import com.tianrui.common.constants.Constant;
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
	@Autowired
	ICrossVehicleService crossVehicleService;
	
	/** 每天一点执行定时 查询近90天订单*/
	@Scheduled(cron="0 0 1 * * ?")
    public void getncPayDay() {  
    	Long st = new Date().getTime();
    	logger.info("60-定时器[AnlianBillPosition]启动.时间是 :" + DateUtil.getDateString());  
        try {
        	AnlianBillFindReq req = new AnlianBillFindReq();
        	req.setDesc4("配载单已到货!");
        	req.setCreatetime(System.currentTimeMillis()-90*24*60*60*1000);
        	List<AnlianBillResp> list = anlianBillService.findAll(req);
        	int a = 0;
        	for(AnlianBillResp resp : list){
        		a = a+1;
        		logger.info("60-执行数据总量[{}],执行到第[{}]条:",list.size(),a); 
        		if(!StringUtils.contains(resp.getDesc4(),"已到货")){
        			Result rs = Result.getSuccessResult();
        			rs = anlianService.detail(resp.getBillno());
        			JSONObject json = (JSONObject) rs.getData();
        			AnlianBillUpdateReq upt = new AnlianBillUpdateReq();
        			upt.setId(resp.getId());
        			if("000000".equals(rs.getCode())){
        				BillAnlianPosition t = new BillAnlianPosition();
        				PropertyUtils.copyProperties(t, json);
        				t.setShipmentno(resp.getBillno());
        				t.setCreatetime(System.currentTimeMillis());
        				if(t!= null){
        					billAnlianPositionDao.save(t);
        				}
        				if(resp.getPtBegintime()==null){
        					//第一次查询到位置 保存时间 开始时间 结束时间 均为当前时间
        					upt.setPtBegintime(System.currentTimeMillis());
        					upt.setPtEndtime(System.currentTimeMillis());
        				}else{
        					//有开始时间 结束时间保存为当前时间
        					upt.setPtEndtime(System.currentTimeMillis());
        				}
        				upt.setDesc4("运输中");
        			}else{
        				upt.setDesc4(rs.getError());
        			}
        			anlianBillService.update(upt);
        		}
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		logger.info("60-定时任务[AnlianBillPosition]完成.耗时：{}",new Object[]{(new Date().getTime()-st)});
    }
	
	/** 每五分钟执行定时*/
//	@Scheduled(cron="0 0/5 *  * * ? ")
	@Scheduled(cron="0/5 * *  * * ? ")
    public void getncPay() {  
		HttpServletRequest request = null ;
		Long st = new Date().getTime();
    	logger.info("定时器[AnlianBillPosition]启动.时间是 :" + DateUtil.getDateString());  
        try {
        	AnlianBillFindReq req = new AnlianBillFindReq();
        	req.setDesc4("配载单已到货!");
        	String size = Constant.ANLIAN_TIME;
        	req.setCreatetime(System.currentTimeMillis()-Integer.valueOf(size)*24*60*60*1000);
        	List<AnlianBillResp> list = anlianBillService.findAll(req);
        	int a = 0;
        	for(AnlianBillResp resp : list){
        		try {
					crossVehicleService.updateLogoStatus(request,resp.getCph(), "1",resp.getHpmc());
				} catch (Exception e) {
					logger.info("开启中交车辆状态失败"); 
				}
        		a = a+1;
        		logger.info("执行数据总量[{}],执行到第[{}]条:",list.size(),a); 
        		if(!StringUtils.contains(resp.getDesc4(),"已到货")){
        			Result rs = Result.getSuccessResult();
        			rs = anlianService.detail(resp.getBillno());
        			JSONObject json = (JSONObject) rs.getData();
        			AnlianBillUpdateReq upt = new AnlianBillUpdateReq();
        			upt.setId(resp.getId());
        			if("000000".equals(rs.getCode())){
        				BillAnlianPosition t = new BillAnlianPosition();
        				PropertyUtils.copyProperties(t, json);
        				t.setShipmentno(resp.getBillno());
        				t.setCreatetime(System.currentTimeMillis());
        				if(t!= null){
        					billAnlianPositionDao.save(t);
        				}
        				if(resp.getPtBegintime()==null){
        					//第一次查询到位置 保存时间 开始时间 结束时间 均为当前时间
        					upt.setPtBegintime(System.currentTimeMillis());
        					upt.setPtEndtime(System.currentTimeMillis());
        					try {
								crossVehicleService.updateLogoStatus(request,resp.getCph(), "1",resp.getHpmc());
							} catch (Exception e) {
								logger.info("开启中交车辆状态失败"); 
							}
        				}else{
        					//有开始时间 结束时间保存为当前时间
        					upt.setPtEndtime(System.currentTimeMillis());
        				}
        				upt.setDesc4("运输中");
        			}else{
        				upt.setDesc4(rs.getError());
        				if(StringUtils.equals("配载单已到货!", rs.getError())){
        					try {
								crossVehicleService.updateLogoStatus(request,resp.getCph(), "0",resp.getHpmc());
							} catch (Exception e) {
								logger.info("关闭中交车辆状态失败"); 
							}
        				}
        			}
        			anlianBillService.update(upt);
        		}
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		logger.info("定时任务[AnlianBillPosition]完成.耗时：{}",new Object[]{(new Date().getTime()-st)});
    }
}
