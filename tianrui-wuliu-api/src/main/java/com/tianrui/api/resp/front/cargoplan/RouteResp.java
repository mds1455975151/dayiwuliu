package com.tianrui.api.resp.front.cargoplan;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.util.DateTypeUtil;

/**
 * 
 * @类描述：
 * @创建人：lsj
 * @创建时间：2016年5月23日下午3:33:53
 *
 * @修改人：lsj
 * @修改时间：2016年5月23日下午3:33:53
 * @修改备注：
 *
 */
public class RouteResp extends BaseResp  {

	/**
     * ID
     * @mbggenerated
     */
    private String id;
    
    private String oaddr;
    
    private String daddr;
    
    private String sendpersion;
    
    private String sendpersionphone;
    
    private String receivepersion;
    
    private String receivepersionphone;
    
    private String receiveid;
    /**
     * 路径名称
     * @mbggenerated
     */
    private String routename;
    /**
     * 结算公里数
     * @mbggenerated
     */
    private Double distance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public String getOaddr() {
		return oaddr;
	}

	public void setOaddr(String oaddr) {
		this.oaddr = oaddr;
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

	public Double getDistance() {
		return DateTypeUtil.doubleType(distance);
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getReceiveid() {
		return receiveid;
	}

	public void setReceiveid(String receiveid) {
		this.receiveid = receiveid;
	}
    
}
