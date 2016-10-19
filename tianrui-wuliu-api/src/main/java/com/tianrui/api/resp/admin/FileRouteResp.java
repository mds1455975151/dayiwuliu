package com.tianrui.api.resp.admin;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.util.DateTypeUtil;

/**
 * 
 * @author Administrator
 *
 */
public class FileRouteResp extends BaseResp {

	private static final long serialVersionUID = -7190183760043617974L;
	private String id;

	private String routename;
	private String status;
	private String organizationname;

	 private String count;//被引用次数
	 
	//收发货地点
    private String opositionid;
    private String oaddr;
    private String dpositionid;
    private String daddr;
    //收发货联系人
    private String sendpersion;
    private String sendpersionphone;
    private String receivepersion;
    private String receivepersionphone;

	private Double distance;
	
	private String distanceStr;
	private String modifier;
	private Long modifytime;
	private String modifytimeStr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Double getDistance() {
		return DateTypeUtil.doubleType(distance);
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getDistanceStr() {
		String distanceStr=""; 
		if( this.distance !=null ){
			DecimalFormat    df   = new DecimalFormat("######0.00");  
			distanceStr=String .valueOf(df.format(this.distance));
		}
		return distanceStr;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Long getModifytime() {
		return modifytime;
	}

	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}

	public String getModifytimeStr() {
		String str ="";
		if( this.modifytime !=null ){
			str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(modifytime));
		}
		return str;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}

	public String getOpositionid() {
		return opositionid;
	}

	public void setOpositionid(String opositionid) {
		this.opositionid = opositionid;
	}

	public String getOaddr() {
		return oaddr;
	}

	public void setOaddr(String oaddr) {
		this.oaddr = oaddr;
	}

	public String getDpositionid() {
		return dpositionid;
	}

	public void setDpositionid(String dpositionid) {
		this.dpositionid = dpositionid;
	}

	public String getDaddr() {
		return daddr;
	}

	public void setDaddr(String daddr) {
		this.daddr = daddr;
	}

	public String getSendpersion() {
		return sendpersion;
	}

	public void setSendpersion(String sendpersion) {
		this.sendpersion = sendpersion;
	}

	public String getSendpersionphone() {
		return sendpersionphone;
	}

	public void setSendpersionphone(String sendpersionphone) {
		this.sendpersionphone = sendpersionphone;
	}

	public String getReceivepersion() {
		return receivepersion;
	}

	public void setReceivepersion(String receivepersion) {
		this.receivepersion = receivepersion;
	}

	public String getReceivepersionphone() {
		return receivepersionphone;
	}

	public void setReceivepersionphone(String receivepersionphone) {
		this.receivepersionphone = receivepersionphone;
	}

	
}
