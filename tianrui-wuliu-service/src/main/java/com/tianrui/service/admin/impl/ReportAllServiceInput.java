package com.tianrui.service.admin.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IReportAllInputService;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.bean.Merchant;
import com.tianrui.service.admin.mapper.FileRouteMapper;
import com.tianrui.service.admin.mapper.MerchantMapper;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.bean.OrgSigner;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.ReportBillAll;
import com.tianrui.service.bean.ReportPayAll;
import com.tianrui.service.bean.ReportPlanAll;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.MemberBankCardMapper;
import com.tianrui.service.mapper.OrgSignerMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mapper.ReportBillAllMapper;
import com.tianrui.service.mapper.ReportPayAllMapper;
import com.tianrui.service.mapper.ReportPlanAllMapper;
import com.tianrui.service.mapper.SystemMemberMapper;


@Service
public class ReportAllServiceInput implements IReportAllInputService{

	Logger logger = LoggerFactory.getLogger(ReportAllServiceInput.class);
	
	@Autowired
	ReportBillAllMapper reportBillAllMapper;
	@Autowired
	ReportPayAllMapper reportPayAllMapper;
	@Autowired
	ReportPlanAllMapper reportPlanAllMapper;
	@Autowired
	MerchantMapper merchantMapper;
	@Autowired
	FileRouteMapper FileRouteMapper;
	@Autowired
	OrgSignerMapper orgSignerMapper;
	@Autowired
	SystemMemberMapper memberMapeer;
	@Autowired
	MemberBankCardMapper memberBankCardMapper;
	@Autowired
	CacheClient cacheClient;
	@Autowired
	PlanMapper planMapper;
	

	@Override
	public Result planUpdate() throws Exception {
		//获取当前的所有计划
		List<ReportPlanAll> planList =mkReportPlanAll();
		logger.info("获取db的计划完成,总数为{}条",planList.size());
		//删除计划报表
		reportPlanAllMapper.deleteAll();
		logger.info("清空计划统计表完成");
		//添加计划数据
		if( CollectionUtils.isNotEmpty(planList)  ){
			int size=planList.size();
			if( size>2000 ){
				int count=planList.size()/2000;
				for(int i=0;i<count;i++){
					if( i==(count-1)){
						reportPlanAllMapper.insertBatch(planList.subList(2000*i, size));
						logger.info("批量插入计划完成,条数为{}条",(size-2000*i));
					}else{
						reportPlanAllMapper.insertBatch(planList.subList(2000*i,2000*(i+1)));
						logger.info("批量插入计划完成,条数为{}条",2000);
					}
				}
			}else{
				reportPlanAllMapper.insertBatch(planList);
				logger.info("批量插入计划完成,条数为{}条",planList.size());
			}
		}
		return Result.getSuccessResult();
	}

	@Override
	public Result billAlianUpdate() throws Exception {
		//删除报表历史数据
		reportBillAllMapper.deleteAnlianBill();
		logger.info("删除开票运单报表历史数据");
		//获取安联运单数据总数
		long size=reportBillAllMapper.getAnlianBillCount();
		logger.info("获取db的开票运单完成,总数为{}条",size);
		//批量获取和更新
		int count =(int)size%2000+1;
		for( int i=0;i<count;i++ ){
			List<ReportBillAll> anlianBillList =mkReportBillAnlian(i*2000, 2000);
			if( CollectionUtils.isNotEmpty(anlianBillList) ){
				reportBillAllMapper.insertBatch(anlianBillList);
				logger.info("批量插入运单完成,条数为{}条",anlianBillList.size());
			}
		}
		return Result.getSuccessResult();
	}

	@Override
	public Result billCommonUpdate() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result payAlianUpdate() throws Exception {
		//获取当前所有的账单数据
		List<ReportPayAll> payList =mkReportPayAnlian();
		logger.info("获取db的计划完成,总数为{}条",payList.size());
		//删除当前安联账单报表
		reportPayAllMapper.deleteByType("1");
		//批量插入安联账单
		if( CollectionUtils.isNotEmpty(payList)  ){
			int size=payList.size();
			if( size>2000 ){
				int count=payList.size()/2000;
				for(int i=0;i<count;i++){
					if( i==(count-1)){
						reportPayAllMapper.insertBatch(payList.subList(2000*i, size));
						logger.info("批量插入安联账单完成,条数为{}条",(size-2000*i));
					}else{
						reportPayAllMapper.insertBatch(payList.subList(2000*i,2000*(i+1)));
						logger.info("批量插入安联账单完成,条数为{}条",2000);
					}
				}
			}else{
				reportPayAllMapper.insertBatch(payList);
				logger.info("批量插入安联账单完成,条数为{}条",payList.size());
			}
		}
		return Result.getSuccessResult();
	}

	@Override
	public Result cacheUpdate() throws Exception {
		//客商档案   
		ksCacheUpdate();
		//线路档案
		xlCacheUpdate();
		//签收人档案 
		qsrCacheUpdate();
		//用户档案
		memberCacheUpdate();
		//银行卡档案
		yhkCacheUpdate();
		return Result.getSuccessResult();
	}
	
	//获取当前db里面所有的计划信息
	private List<ReportPlanAll> mkReportPlanAll(){
		List<ReportPlanAll> rs =new ArrayList<ReportPlanAll>();
		String dateStr =DateUtil.getDateString();
		//查询计划数量
		List<Plan> db=planMapper.selectByCondition(null);
		if( CollectionUtils.isNotEmpty(db) ){
			for( Plan plan :db){
				ReportPlanAll item =new ReportPlanAll();
				item.setId(plan.getId());
				item.setPlanOwnerId(plan.getCreator());
				item.setPlanVenderId(plan.getVehicleownerid());
				item.setPlanCreateTime(plan.getCreatetime());
				item.setPlanCode(plan.getPlancode());
				
				item.setPlanBeginTime(plan.getStarttime());
				item.setPlanEndTime(plan.getEndtime());
				item.setPlanWeight(String.valueOf(plan.getTotalplanned()));
				if(plan.getCompleted()!=null  ){
					item.setComplitWeight(BigDecimal.valueOf(plan.getCompleted()).setScale(2, RoundingMode.HALF_UP).toString());
				}else{
					item.setComplitWeight("0");
				}
				
				if( plan.getCompleted()!=null && plan.getTotalplanned()!=null){
					BigDecimal tempo =BigDecimal.valueOf(plan.getCompleted()).divide(BigDecimal.valueOf(plan.getTotalplanned()),2,RoundingMode.HALF_UP);
					item.setTempo(tempo.toString());
				}else{
					item.setTempo("0");
				}
				
				item.setPlanStatus(String.valueOf(plan.getStatus()));
				item.setCargoName(plan.getCargoname());
				if( StringUtils.isNotBlank(plan.getRouteid()) ){
					FileRoute route=cacheClient.getObj(CacheModule.FILE_XL+plan.getRouteid(),FileRoute.class );
					if(route!=null){
						item.setRouteName(route.getRoutename());
					}
				}
				
				if( StringUtils.isNotBlank(plan.getShipperMerchant()) ){
					Merchant merchant=cacheClient.getObj(CacheModule.FILE_KS+plan.getShipperMerchant(),Merchant.class );
					if(merchant!=null){
						item.setSendMan(merchant.getName());
					}
				}
				item.setSendPersion(plan.getSendperson());
				
				item.setVenderName(plan.getVehicleownername());
				if( StringUtils.isNotBlank(plan.getConsigneeMerchant()) ){
					Merchant merchant=cacheClient.getObj(CacheModule.FILE_KS+plan.getConsigneeMerchant(),Merchant.class );
					if(merchant!=null){
						item.setReceiptMan(merchant.getName());
					}
				}
				item.setDistant(String.valueOf(plan.getDistance()));
				item.setPrice(String.valueOf(plan.getPrice()));
				
				
				item.setTax(String.valueOf(plan.getTallage()));
				item.setPayMent(plan.getPayment());
				if(StringUtils.isNotBlank(plan.getReceiveid())){
					OrgSigner orgSigner=cacheClient.getObj(CacheModule.FILE_QSL+plan.getReceiveid(),OrgSigner.class );
					if(orgSigner!=null){
						item.setReceiptPersion(orgSigner.getMembername());
					}
				}
				item.setDesc4(dateStr);
				rs.add(item);
			}
		}
		
		
		return rs;
	} 
	
	//获取当前db所有的安联账单信息
	private List<ReportPayAll> mkReportPayAnlian(){
		List<ReportPayAll> list=reportPayAllMapper.getReportPayAnlian();
		if( CollectionUtils.isNotEmpty(list) ){
			String dateStr= DateUtil.getDateString();
			for( ReportPayAll item :list){
				if( item !=null ){
					//线路信息获取
					item.setDesc4(dateStr);
					if( StringUtils.isNotBlank(item.getRouteId()) ){
						FileRoute route=cacheClient.getObj(CacheModule.FILE_XL+item.getRouteId(),FileRoute.class );
						if(route!=null){
							item.setRouteName(route.getRoutename());
						}
					}
					//发货方获取
					if( StringUtils.isNotBlank(item.getSendManId()) ){
						Merchant merchant=cacheClient.getObj(CacheModule.FILE_KS+item.getSendManId(),Merchant.class );
						if(merchant!=null){
							item.setSendMan(merchant.getName());
						}
					}
					//收货方获取
					if( StringUtils.isNotBlank(item.getReceiptMan()) ){
						Merchant merchant=cacheClient.getObj(CacheModule.FILE_KS+item.getReceiptMan(),Merchant.class );
						if(merchant!=null){
							item.setReceiptMan(merchant.getName());
						}
					}
					//银行名称
					if( StringUtils.isNotBlank(item.getPayBankId()) ){
						MemberBankCard memberBankCard=cacheClient.getObj(CacheModule.FILE_YHK+item.getPayBankId(),MemberBankCard.class );
						if(memberBankCard!=null){
							item.setPayBankName(memberBankCard.getBankname());
						}
					}
				}
			}
		}
		
		return list;
	}
	
	//分页获取开票运单数据
	private List<ReportBillAll> mkReportBillAnlian(int start,int limit){
		List<ReportBillAll> list =reportBillAllMapper.getAnlianBill(limit, start);
		String dateStr =DateUtil.getDateString();
		if( CollectionUtils.isNotEmpty(list) ){
			for(ReportBillAll item :list ){
				item.setDesc4(dateStr);
				item.setBillType("al");
				//线路名称
				if( StringUtils.isNotBlank(item.getRouteId()) ){
					FileRoute route=cacheClient.getObj(CacheModule.FILE_XL+item.getRouteId(),FileRoute.class );
					if(route!=null){
						item.setRouteName(route.getRoutename());
					}
				}
				//发货方获取
				if( StringUtils.isNotBlank(item.getSendManId()) ){
					Merchant merchant=cacheClient.getObj(CacheModule.FILE_KS+item.getSendManId(),Merchant.class );
					if(merchant!=null){
						item.setSendMan(merchant.getName());
					}
				}
				//收货方获取
				if( StringUtils.isNotBlank(item.getReceiptMan()) ){
					Merchant merchant=cacheClient.getObj(CacheModule.FILE_KS+item.getReceiptMan(),Merchant.class );
					if(merchant!=null){
						item.setReceiptMan(merchant.getName());
					}
				}
			}
		}
		
		return list ;
	}
	
	
	//客户档案缓存更新  缓存时间3天
	//数量级为1000.
	private void ksCacheUpdate(){
		long begin =System.currentTimeMillis();
		//查询所有客商数据
		 List<Merchant> list =merchantMapper.selectByCondition(null);
		 for( Merchant merchant :list){
			 if(merchant !=null && StringUtils.isNotBlank(merchant.getId())  ){
				 cacheClient.saveObject(CacheModule.FILE_KS+merchant.getId(),72*60*60);
			 }
		 }
		 logger.info("客商档案缓存更新完成,耗时:{}ms,更新数量为:{}.",(System.currentTimeMillis()-begin),(list==null?0:list.size()));
	}
	
	//线路档案缓存更新  缓存时间3天
	//数量级为1000.
	private void xlCacheUpdate(){
		long begin =System.currentTimeMillis();
		//查询所有线路数据
		List<FileRoute> list =FileRouteMapper.selectAll();
		for( FileRoute bean :list){
			if(bean !=null && StringUtils.isNotBlank(bean.getId())  ){
				cacheClient.saveObject(CacheModule.FILE_XL+bean.getId(),72*60*60);
			}
		}
		logger.info("线路档案缓存更新完成,耗时:{}ms,更新数量为:{}.",(System.currentTimeMillis()-begin),(list==null?0:list.size()));
	}
	
	//签收人档案缓存更新  缓存时间3天
	//数量级为1000.
	private void qsrCacheUpdate(){
		long begin =System.currentTimeMillis();
		//查询所有签收人
		List<OrgSigner> list =orgSignerMapper.selectByCondition(null);
		for( OrgSigner bean :list){
			if(bean !=null && StringUtils.isNotBlank(bean.getId())  ){
				cacheClient.saveObject(CacheModule.FILE_QSL+bean.getId(),72*60*60);
			}
		}
		logger.info("签收人档案缓存更新完成,耗时:{}ms,更新数量为:{}.",(System.currentTimeMillis()-begin),(list==null?0:list.size()));
	}
	
	//用户档案缓存更新  缓存时间3天
	//数量级为10000.
	private void memberCacheUpdate(){
		long begin =System.currentTimeMillis();
		//查询所有签收人
		List<SystemMember> list =memberMapeer.selectByCondition(null);
		for( SystemMember bean :list){
			if(bean !=null && StringUtils.isNotBlank(bean.getId())  ){
				cacheClient.saveObject(CacheModule.FILE_MEMBER+bean.getId(),72*60*60);
			}
		}
		logger.info("用户档案缓存更新完成,耗时:{}ms,更新数量为:{}.",(System.currentTimeMillis()-begin),(list==null?0:list.size()));
	}
	//银行卡档案缓存更新  缓存时间3天
	//数量级为1000.
	private void yhkCacheUpdate(){
		long begin =System.currentTimeMillis();
		//查询所有签收人
		List<MemberBankCard> list =memberBankCardMapper.selectAll();
		for( MemberBankCard bean :list){
			if(bean !=null && StringUtils.isNotBlank(bean.getId())  ){
				cacheClient.saveObject(CacheModule.FILE_YHK+bean.getId(),72*60*60);
			}
		}
		logger.info("银行卡档案缓存更新完成,耗时:{}ms,更新数量为:{}.",(System.currentTimeMillis()-begin),(list==null?0:list.size()));
	}
	

}
