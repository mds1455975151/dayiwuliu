package com.tianrui.service.mongo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DBCollection;
import com.tianrui.common.vo.PaginationVO;



public interface BaseDao <T extends Serializable, ID extends Serializable>{
	
	
	public void save(T t);
	
	public void insert(T t);
	
	public void insert(List<T> ts);
	
	public T getById(ID id);
	
	public T findOne(String id);
	
	public T findOne(Query query);
	
	public List<T> find(String id);
	
	public List<T> find(Query query);
	
	public void remove(ID id);
	
	public void removeAll(ID[] ids);
	
	public List<T> getList(Query query);
	
	public PaginationVO<T> getPage(Integer currentPage, Query query);
	
	public PaginationVO<T> getPage(Integer currentPage,Integer pageSize, Query query);
	
	public T findAndModify(Query query, Update update);
	
	public int updateMulti(Query query, Update update);
	
	public List<T> getAll();
	
	public String getColName();
	
	public boolean exists(Query query);
	
	public MongoTemplate getMongoTemplate();
	
	public DBCollection getCollection();
	
	public long count(Query query);
	
}
