package com.tianrui.api.req.front.system;


import com.tianrui.api.req.BaseReq;

public class FileIndexReq extends BaseReq {

	private static final long serialVersionUID = 1298849672113160842L;
	private String id; 
	/**
	 * 用户id
	 */
	private Long authorId; 
	/**
	 * 文件存放的物理路径
	 */
	private String filePath; 
	/**
	 * 源文件的名称
	 */
	private String fileTitle; 
	/**
	 * 文件大小
	 */
	private Long fileSize;  
	/**
	 * 文件状态
	 */
	private int status; 
	/**
	 * 加密形式
	 */
	private String md5;  
	/**
	 * taskId
	 */
	private String taskId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	} 
	
	
	
}
