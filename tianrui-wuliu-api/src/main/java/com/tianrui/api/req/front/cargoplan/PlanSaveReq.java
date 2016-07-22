package com.tianrui.api.req.front.cargoplan;

import com.tianrui.api.req.BaseReq;

/**
 * 
  * @ClassName: PlanSaveReq 
  * @Description: 计划保存实体
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月23日 下午4:45:32 
  *
 */
public class PlanSaveReq extends BaseReq{
	private static final long serialVersionUID = -3942295359427263186L;
	
    //策略id
    private String freightid;
    //路径id
    private String routeid;
    //货物id
    private String cargoid;
    //车主id
    private String venderId;
    //是否为熟车   1为熟车  0普通
    private String isFamily;
    
    //计划总量
    private String totalplanned;
    //开始时间
    private String starttimeStr;
    //结束时间
    private String endtimeStr;
    //计划费用
    private String planprice;
    //联系人
    private String linkman;
    //联系电话
    private String telephone;
    //是否为模版  1 为模版
    private String istemplate;
    //当前用户id
    private String curruId;

	public String getFreightid() {
		return freightid;
	}

	public void setFreightid(String freightid) {
		this.freightid = freightid;
	}

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getCargoid() {
		return cargoid;
	}

	public void setCargoid(String cargoid) {
		this.cargoid = cargoid;
	}

	public String getVenderId() {
		return venderId;
	}

	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}

	public String getIsFamily() {
		return isFamily;
	}

	public void setIsFamily(String isFamily) {
		this.isFamily = isFamily;
	}

	public String getTotalplanned() {
		return totalplanned;
	}

	public void setTotalplanned(String totalplanned) {
		this.totalplanned = totalplanned;
	}

	public String getStarttimeStr() {
		return starttimeStr;
	}

	public void setStarttimeStr(String starttimeStr) {
		this.starttimeStr = starttimeStr;
	}

	public String getEndtimeStr() {
		return endtimeStr;
	}

	public void setEndtimeStr(String endtimeStr) {
		this.endtimeStr = endtimeStr;
	}

	public String getPlanprice() {
		return planprice;
	}

	public void setPlanprice(String planprice) {
		this.planprice = planprice;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIstemplate() {
		return istemplate;
	}

	public void setIstemplate(String istemplate) {
		this.istemplate = istemplate;
	}

	public String getCurruId() {
		return curruId;
	}

	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}
    
    
    
    
    
   
}