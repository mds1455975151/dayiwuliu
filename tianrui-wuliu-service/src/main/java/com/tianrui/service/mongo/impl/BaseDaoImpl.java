package com.tianrui.service.mongo.impl;

import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.mongo.BaseDao;

@Repository("mongoBaseDao")
public class BaseDaoImpl<T extends Serializable, ID extends Serializable> implements BaseDao<T,ID> {
	
	Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
	
	private Class<T> type; 
	
	protected static final Integer PAGE_SIZE = 10; 
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	public BaseDaoImpl(Class<T> type) {  
        this.type = type;  
    }  
	
	public BaseDaoImpl(){
	}
	
	public void save(T t){
		mongoTemplate.save(t);
	}
	
	
	public void insert(T t){
		mongoTemplate.insert(t);
	}
	
	
	public String getColName(){
		return this.mongoTemplate.getCollectionName(type);
	}

	
	public T getById(ID id){
		return mongoTemplate.findById(id, type);
	}
	
	
    public List<T> find(Query query){
		return mongoTemplate.find(query, type);
	}
	
	public T findOne(Query query){
		return mongoTemplate.findOne(query, type);
	}
	
	
	public void remove(ID id) {
		mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), type);
	}
	
	
	public void removeAll(ID[] ids){
		mongoTemplate.remove(new Query(Criteria.where("_id").in(ids)), type);
	}
	
	
	public PaginationVO<T> getPage(Integer currentPage, Query query) {
		return getPage(currentPage,null,query);
	}
	
	
	public List<T> getList(Query query) {
		return this.mongoTemplate.find(query, type);
	}
	
	
	public PaginationVO<T> getPage(Integer currentPage, Integer pageSize, Query query) {
		if(null == pageSize) pageSize = PAGE_SIZE;
		Long totalCount = this.mongoTemplate.count(query, type);  
		logger.info("totalCount : "+totalCount);
        //总页数  
		PaginationVO<T> page = new PaginationVO<T>(totalCount,currentPage );  
        if( totalCount>0 ){
        	query.skip((currentPage - 1) * pageSize);
        	query.limit(pageSize);// 从skip开始,取多少条记录  
        	List<T> datas = this.mongoTemplate.find(query, type);  
        	page.setList(datas);//获取数据      
        }
        return page;  
	}


	
	public T findAndModify(Query query, Update update){
		return mongoTemplate.findAndModify(query, update, type);
	}
	
	
	public int updateMulti(Query query, Update update){
		
		
		WriteResult rs = mongoTemplate.updateMulti(query, update, type);
		return rs.getN();
	}

	
	public List<T> getAll(){
		return mongoTemplate.findAll(type);
	}

	
	public void insert(List<T> ts){
		mongoTemplate.insert(ts, type);

	}
	
	
	public MongoTemplate getMongoTemplate(){
		return mongoTemplate;
	}
    //这种方法在id与_id对应后
	public T findOne(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, type);
	}
    //这种方法在id与_id对应后
	
	public List<T> find(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.find(query, type);
	}

	public boolean exists(Query query) {
		return mongoTemplate.exists(query, type);
	}

	
	public DBCollection getCollection() {
		return mongoTemplate.getDb().getCollection(type.getSimpleName().toLowerCase());
	}
	

	
	public long count(Query query){
		return mongoTemplate.count(query, type);
	}
	
}
