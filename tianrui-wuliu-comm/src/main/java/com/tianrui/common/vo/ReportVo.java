package com.tianrui.common.vo;

import java.io.Serializable;
/**
 * 报表Vo
 * @author zhanggaohao
 * @createtime 2016年9月27日 上午9:19:58
 * @classname ReportVo.java
 */
public class ReportVo implements Serializable {

	private static final long serialVersionUID = 7548300858102200849L;
	//开始时间
	private String starttime;
	//结束时间
	private String endtime;
	//承运商
	private String venderid;
	//物料
	private String cargoname;
	//起运地
	private String startcity;
	//目的地
	private String endcity;
	//排序方式
	private String order;
	
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getVenderid() {
		return venderid;
	}
	public void setVenderid(String venderid) {
		this.venderid = venderid;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	public String getStartcity() {
		return startcity;
	}
	public void setStartcity(String startcity) {
		this.startcity = startcity;
	}
	public String getEndcity() {
		return endcity;
	}
	public void setEndcity(String endcity) {
		this.endcity = endcity;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
}
