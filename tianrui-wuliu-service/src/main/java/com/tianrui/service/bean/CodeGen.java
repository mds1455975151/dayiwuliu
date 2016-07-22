package com.tianrui.service.bean;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
  * @ClassName: BillTrack 
  * @Description: 运单轨迹
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月4日 下午3:38:40 
  *
 */
@Document(collection = "codeGen")
public class CodeGen implements Serializable{
	private static final long serialVersionUID = 8379994076474746067L;
	
	private String id;
	//类型  1为计划 2为运单  
	private int type;
	//当前日期
	private int currdata;
	
	private long code;
	
	
	private long timestamp;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getCurrdata() {
		return currdata;
	}


	public void setCurrdata(int currdata) {
		this.currdata = currdata;
	}


	public void setCode(long code) {
		this.code = code;
	}


	public long getCode() {
		return code;
	}




	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
    
    
}