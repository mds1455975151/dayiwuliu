package com.tianrui.api.req.front.cargoplan;

import com.tianrui.api.req.BaseReq;
import com.tianrui.common.vo.MemberVo;

/**
 * 
 * @author zhanggaohao
 * @createtime 2016年9月1日 下午3:51:14
 * @classname PlanAppointReq.java
 * 
 */
public class PlanAppointReq extends BaseReq{
	
	private static final long serialVersionUID = 7546718450850754454L;
	
	private String planid;
    //开始时间
    private String begintime;
    //结束时间
    private String endtime;
    //计划总量
    private String totalplanned;
    //车主id
    private String venderid;
    //车主姓名
    private String venderName;
    //车主联系电话
    private String venderTel;
    
    private MemberVo memberVo;
    
	public String getPlanid() {
		return planid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getTotalplanned() {
		return totalplanned;
	}
	public void setTotalplanned(String totalplanned) {
		this.totalplanned = totalplanned;
	}
	public String getVenderid() {
		return venderid;
	}
	public void setVenderid(String venderid) {
		this.venderid = venderid;
	}
	public String getVenderName() {
		return venderName;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	public String getVenderTel() {
		return venderTel;
	}
	public void setVenderTel(String venderTel) {
		this.venderTel = venderTel;
	}
	public MemberVo getMemberVo() {
		return memberVo;
	}
	public void setMemberVo(MemberVo memberVo) {
		this.memberVo = memberVo;
	}

}