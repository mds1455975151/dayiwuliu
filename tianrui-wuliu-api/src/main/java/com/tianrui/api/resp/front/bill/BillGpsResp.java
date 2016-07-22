package com.tianrui.api.resp.front.bill;

import com.tianrui.api.resp.BaseResp;

/**
 * 运单经纬度返回
  * @ClassName: BillGpsResp 
  * @Description: TODO
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月28日 下午5:34:37 
  *
 */

public class BillGpsResp extends BaseResp{

	private static final long serialVersionUID = 3588964458488414595L;

	private String id;
  
    
    private String startName; 
    private String endName; 
  
    private String startLat;
    private String startLon;
    
    private String endLat;
    private String endLon;
    
	private String currLat;
	private String currLon;
    private String uploadTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartName() {
		return startName;
	}
	public void setStartName(String startName) {
		this.startName = startName;
	}
	public String getEndName() {
		return endName;
	}
	public void setEndName(String endName) {
		this.endName = endName;
	}
	public String getStartLat() {
		return startLat;
	}
	public void setStartLat(String startLat) {
		this.startLat = startLat;
	}
	public String getStartLon() {
		return startLon;
	}
	public void setStartLon(String startLon) {
		this.startLon = startLon;
	}
	public String getEndLat() {
		return endLat;
	}
	public void setEndLat(String endLat) {
		this.endLat = endLat;
	}
	public String getEndLon() {
		return endLon;
	}
	public void setEndLon(String endLon) {
		this.endLon = endLon;
	}
	public String getCurrLat() {
		return currLat;
	}
	public void setCurrLat(String currLat) {
		this.currLat = currLat;
	}
	public String getCurrLon() {
		return currLon;
	}
	public void setCurrLon(String currLon) {
		this.currLon = currLon;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
}
