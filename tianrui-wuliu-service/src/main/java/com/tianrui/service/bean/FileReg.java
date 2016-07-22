package com.tianrui.service.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fileReg")
public class FileReg implements Serializable{

	private static final long serialVersionUID = 5579767148660524131L;

	@Id
	private String id;
	
	//图片 上传时间
	private long timeStamp;
	//图片上传类型   
	private String fileType;
	//图片url
	private String url;
	//图片是否已经被删除  0：未删除 1：已经删除
	private short isDelFlag;
	//图片上传者表示
	private String userId;
	//图片原始名称
	private String fileName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public short getIsDelFlag() {
		return isDelFlag;
	}
	public void setIsDelFlag(short isDelFlag) {
		this.isDelFlag = isDelFlag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
}
