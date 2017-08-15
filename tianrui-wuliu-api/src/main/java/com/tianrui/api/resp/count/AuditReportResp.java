package com.tianrui.api.resp.count;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.tianrui.api.resp.BaseResp;
/**
 * 
  * <p>Title:AuditReportResp </p>
  * <p>Description:审核统计返回数据 </p>
  * <p>Company: </p> 
  * @author   yangbobo
  * @date   2017年7月22日 上午8:42:36
 */
public class AuditReportResp extends BaseResp{
	
	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = -232317128817836974L;
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 用户审核失败数量
	 */
	private String userByNum;
	/**
	 * 用户审核成功数量
	 */
	private String userFailNum;
	/**
	 * 司机审核失败数量
	 */
	private String driverByNum;
	/**
	 * 司机审核成功数量
	 */
	private String driverFailNum;
	/**
	 * 车辆审核失败数量
	 */
	private String vehicleByNum;
	/**
	 * 车辆审核成功数量
	 */
	private String vehicleFailNum;
	/**
	 * 统计
	 */
	private String vehicleByNumL;
	/**
	 * 临时认证车辆审核数量
	 */
	private String vehicleFailNumL;
	/**
	 * 开票车辆审核成功数量
	 */
	private String vehiclePass;
	/**
	 * 开票扯脸过审核失败数量
	 */
	private String vehicleNotPass;
	/**
	 * 银行卡审核失败数量
	 */
	private String bankcardByNum;
	/**
	 * 银行卡审核成功数量
	 */
	private String bankcardFailNum;
	/**
	 * 大易/交通部运单未推送数量
	 */
	private String waybillByPushDJ;
	/**
	 * 大易/交通部运单已推送数量
	 */
	private String waybillFailPushDJ;
	/**
	 * 安联/交通部运单未推送数量
	 */
	private String waybillByPushAJ;
	/**
	 * 安联/交通部运单已推送数量
	 */
	private String waybillFailPushAJ;
	/**
	 * 审核时间
	 */
	private Long reviewTime;
	/**
	 * 创建时间
	 */
	private Long creatertime;
	/**
	 * 修改人
	 */
	private String modify;
	/**
	 * 修改时间
	 */
	private String modifytime;
	
	/**
	 * 开始查询时间
	 */
	private String starttime;
	/**
	 * 结束查询时间
	 */
	private String endtime;
	
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
	
	
	private String creatertimes;
	private String reviewTimes;
	
	public String getReviewTimes() {
		if(reviewTime!=null){
			reviewTimes = new SimpleDateFormat("MM月dd日").format(new Date(reviewTime));
		}
		return reviewTimes;
	}
	public String getCreatertimes() {
		if(creatertime!=null){
			creatertimes = new SimpleDateFormat("MM月dd日").format(new Date(creatertime));
		}
		return creatertimes;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserByNum() {
		return userByNum;
	}
	public void setUserByNum(String userByNum) {
		this.userByNum = userByNum;
	}
	public String getUserFailNum() {
		return userFailNum;
	}
	public void setUserFailNum(String userFailNum) {
		this.userFailNum = userFailNum;
	}
	public String getDriverByNum() {
		return driverByNum;
	}
	public void setDriverByNum(String driverByNum) {
		this.driverByNum = driverByNum;
	}
	public String getDriverFailNum() {
		return driverFailNum;
	}
	public void setDriverFailNum(String driverFailNum) {
		this.driverFailNum = driverFailNum;
	}
	public String getVehicleByNum() {
		return vehicleByNum;
	}
	public void setVehicleByNum(String vehicleByNum) {
		this.vehicleByNum = vehicleByNum;
	}
	public String getVehicleFailNum() {
		return vehicleFailNum;
	}
	public void setVehicleFailNum(String vehicleFailNum) {
		this.vehicleFailNum = vehicleFailNum;
	}
	public String getBankcardByNum() {
		return bankcardByNum;
	}
	public void setBankcardByNum(String bankcardByNum) {
		this.bankcardByNum = bankcardByNum;
	}
	public String getBankcardFailNum() {
		return bankcardFailNum;
	}
	public void setBankcardFailNum(String bankcardFailNum) {
		this.bankcardFailNum = bankcardFailNum;
	}
	public String getWaybillByPushDJ() {
		return waybillByPushDJ;
	}
	public void setWaybillByPushDJ(String waybillByPushDJ) {
		this.waybillByPushDJ = waybillByPushDJ;
	}
	public String getWaybillFailPushDJ() {
		return waybillFailPushDJ;
	}
	public void setWaybillFailPushDJ(String waybillFailPushDJ) {
		this.waybillFailPushDJ = waybillFailPushDJ;
	}
	public String getWaybillByPushAJ() {
		return waybillByPushAJ;
	}
	public void setWaybillByPushAJ(String waybillByPushAJ) {
		this.waybillByPushAJ = waybillByPushAJ;
	}
	public String getWaybillFailPushAJ() {
		return waybillFailPushAJ;
	}
	public void setWaybillFailPushAJ(String waybillFailPushAJ) {
		this.waybillFailPushAJ = waybillFailPushAJ;
	}
	
	public String getModify() {
		return modify;
	}
	public void setModify(String modify) {
		this.modify = modify;
	}
	public String getModifytime() {
		return modifytime;
	}
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}
	public Long getCreatertime() {
		return creatertime;
	}
	public void setCreatertime(Long creatertime) {
		this.creatertime = creatertime;
	}

	public Long getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Long reviewTime) {
		this.reviewTime = reviewTime;
	}
	public String getVehicleByNumL() {
		return vehicleByNumL;
	}
	public void setVehicleByNumL(String vehicleByNumL) {
		this.vehicleByNumL = vehicleByNumL;
	}
	public String getVehicleFailNumL() {
		return vehicleFailNumL;
	}
	public void setVehicleFailNumL(String vehicleFailNumL) {
		this.vehicleFailNumL = vehicleFailNumL;
	}
	public String getVehiclePass() {
		return vehiclePass;
	}
	public void setVehiclePass(String vehiclePass) {
		this.vehiclePass = vehiclePass;
	}
	public String getVehicleNotPass() {
		return vehicleNotPass;
	}
	public void setVehicleNotPass(String vehicleNotPass) {
		this.vehicleNotPass = vehicleNotPass;
	}
	
	

	
	
	
	
	
}
