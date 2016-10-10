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
	private Long starttime;
	//结束时间
	private Long endtime;
	//货主
	private String memberid;
	//承运商
	private String vendername;
	//物料
	private String cargoname;
	//起运地
	private String startcity;
	//目的地
	private String endcity;
	//排序方式
	private String order;
	
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getVendername() {
		return vendername;
	}
	public void setVendername(String vendername) {
		this.vendername = vendername;
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
