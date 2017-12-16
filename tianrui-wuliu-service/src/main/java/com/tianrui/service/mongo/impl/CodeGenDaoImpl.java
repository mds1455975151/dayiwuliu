package com.tianrui.service.mongo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.service.bean.CodeGen;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mongo.CodeGenDao;

@Repository("codeGenDao")
public class CodeGenDaoImpl extends BaseDaoImpl<CodeGen,String> implements CodeGenDao{

	Logger logger = LoggerFactory.getLogger(CodeGenDaoImpl.class);

	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@Autowired
	CacheClient cacheClient ;

	@Override
	public synchronized String codeGen(int type) {
		String rs =null;
		try {
			String key=CacheHelper.buildKey(CacheModule.COdE_GEN_STATUS, type+"");
			String check = cacheClient.getString(key);
			logger.info("获取上次单号："+check);
			rs =null;
			Query query =new Query();
			Criteria criteria =Criteria.where("currdata").is(getCurrDate()).and("type").is(type);
			query.addCriteria(criteria);
			//排序条件
			query.with(new Sort(Direction.ASC,"timestamp"));
			List<CodeGen> list =mongoTemplate.find(query,CodeGen.class);
			if( CollectionUtils.isNotEmpty(list) ){
				CodeGen code =list.get(0);
				long  newCoe=code.getCode()+1;
				logger.info("old:"+check+",new:"+newCoe);
				if(StringUtils.equals(check, String.valueOf(newCoe))){
					newCoe = newCoe +1;
					logger.info("获取上次单号和新单号相同新单号+1："+newCoe);
				}
				cacheClient.saveObject(key, newCoe);
				Update update =Update.update("code", newCoe);
				mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("id").is(code.getId())), update, CodeGen.class);
				rs = String.valueOf(newCoe);
			}else{
				rs = String.valueOf(getCurrDate()+"0001");
				CodeGen code =new CodeGen();
				code.setType(type);
				code.setCurrdata(getCurrDate());
				code.setTimestamp(System.currentTimeMillis());
				code.setId(UUIDUtil.getId());
				code.setCode(Long.parseLong(rs));
				mongoTemplate.insert(code);
			}
			switch(type){
			case 1:
				rs="JH"+rs;
				break;
			case 2:
				rs="YD"+rs;
				break;
			case 3:
				rs="JM"+rs;
				break;
			case 4:
				rs="ZD"+rs;		  
				break;
			default:
				
			}
			logger.info("最后生成单号："+rs);
		} catch (Exception e) {
			logger.info("获取单号失败"+e.getMessage());
		}
		return rs;
	}

	
	//获取当前日期 比如20140102
	private int getCurrDate(){
		return Integer.valueOf(DateUtil.getDateString(new Date(), "yyMMdd"));
	}
	
	
}
