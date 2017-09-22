package com.tianrui.service.mongo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
import com.tianrui.service.mongo.CodeGenDao;

@Repository("codeGenDao")
public class CodeGenDaoImpl extends BaseDaoImpl<CodeGen,String> implements CodeGenDao{

	
	@Autowired
	protected MongoTemplate mongoTemplate;

	@Override
	public synchronized String codeGen(int type) {
		String rs =null;
		Query query =new Query();
		Criteria criteria =Criteria.where("currdata").is(getCurrDate()).and("type").is(type);
		query.addCriteria(criteria);
		//排序条件
		query.with(new Sort(Direction.ASC,"timestamp"));
		
		List<CodeGen> list =mongoTemplate.find(query,CodeGen.class);
		
		if( CollectionUtils.isNotEmpty(list) ){
			CodeGen code =list.get(0);
			long  newCoe=code.getCode()+1;
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
		return rs;
	}

	
	//获取当前日期 比如20140102
	private int getCurrDate(){
		return Integer.valueOf(DateUtil.getDateString(new Date(), "yyMMdd"));
	}
	
	
}
