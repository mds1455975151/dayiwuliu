package com.tianrui.service.bean;

public class VehicleAndDriver implements IModel {
	
	private static final long serialVersionUID = -7190183760043617974L;
	
	private String id;
	/** 用户主键 */
	private String memberid;
	
	private String vehiOwnerTel;
	
	private String vehiOwnerName;
	
	private String vehilicenseimgpath;
	
	private String billstatus;
	/** app搜索字段*/
	private String search;
	/** app搜索车牌号*/
	private String searchno;
	/** app搜索车牌号前缀*/
	private String searchfix;
	/** 车辆主键*/
	private String vehiid;
	/** 车牌号前缀 */
	private String vehiprefix;
	/** 车牌号 */
	private String vehino;
	/** 车辆类型(1:箱式,2:车板,3:冷藏) */
	private String vehitype;
	/** 车辆类型名称 */
	private String vehitypename;
	/** 车辆长度(米) */
	private String vehilength;
	/** 车辆重量(吨) */
	private String vehiweight;
	/** 车头照片路径 */
	private String vehiheadimgpath;
	/** 车辆状态(-1:认证失败,0:未认证,1:认证成功,2:认证中) */
	private String status;
	/** 认证失败原因 */
	private String memo;
	/** 创建认证时间*/
	private Long createtime;
	/** 审核时间*/
	private Long audittime;
	
	/** 车辆司机表主键 */
	private String vehidriverid;
	/** 司机主键 */
	private String driverid;
	/** 司机姓名 */
	private String drivername;
	/** 司机电话 */
	private String drivertel;
	
	/** 驾驶证/身份证号 */
	private String identityCard;
	 /** 分页开始位置 */
    private Integer start;
    
    /** 分页数据行数 */
    private Integer limit;
	/**
	 * 获取用户主键
	 * @return memberid
	 */
	public String getMemberid() {
		return memberid;
	}

	public String getId() {
		return id;
	}

	public String getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}

	public void setSearchno(String searchno) {
		this.searchno = searchno;
	}

	public void setSearchfix(String searchfix) {
		this.searchfix = searchfix;
	}

	public String getVehiOwnerTel() {
		return vehiOwnerTel;
	}

	public void setVehiOwnerTel(String vehiOwnerTel) {
		this.vehiOwnerTel = vehiOwnerTel;
	}

	public String getVehiOwnerName() {
		return vehiOwnerName;
	}

	public void setVehiOwnerName(String vehiOwnerName) {
		this.vehiOwnerName = vehiOwnerName;
	}

	public String getVehilicenseimgpath() {
		return vehilicenseimgpath;
	}

	public void setVehilicenseimgpath(String vehilicenseimgpath) {
		this.vehilicenseimgpath = vehilicenseimgpath;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearchno() {
		if(search!=null&&search!=""){
			if(search.length()>2){
				boolean isLetter = false;
				//判断是否包含字母
				for (int j = 0; j < search.length(); j++) {
					if(Character.isLetter(search.charAt(j))){
						isLetter = true;
					};
				}
				if(isLetter){
					searchno = search.substring(2,search.length());
				}
			}
		}
		return searchno;
	}

	public String getSearchfix() {
		if(search.length()>2){
			boolean isLetter = false;
			//判断是否包含字母
			for (int j = 0; j < search.length(); j++) {
				if(Character.isLetter(search.charAt(j))){
					isLetter = true;
				};
			}
			if(isLetter){
				searchfix = search.substring(0,2);
			}
		}
		return searchfix;
	}

	/**
	 * 设置用户主键
	 * @param newMemberid
	 */
	public void setMemberid(String newMemberid) {
		this.memberid = newMemberid;
	}
	
	public Integer getStart() {
		return start;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public Long getAudittime() {
		return audittime;
	}

	public void setAudittime(Long audittime) {
		this.audittime = audittime;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * 获取车辆主键
	 * @return vehiid
	 */
	public String getVehiid() {
		return vehiid;
	}

	/**
	 * 设置车辆主键
	 * @param newVehiid
	 */
	public void setVehiid(String newVehiid) {
		this.vehiid = newVehiid;
	}

	/**
	 * 获取车牌号前缀
	 * @return vehiprefix
	 */
	public String getVehiprefix() {
		return vehiprefix;
	}

	/**
	 * 设置车牌号前缀
	 * @param newVehiprefix
	 */
	public void setVehiprefix(String newVehiprefix) {
		this.vehiprefix = newVehiprefix;
	}

	/**
	 * 获取车牌号
	 * @return vehino
	 */
	public String getVehino() {
		return vehino;
	}

	/**
	 * 设置车牌号
	 * @param newVehino
	 */
	public void setVehino(String newVehino) {
		this.vehino = newVehino;
	}

	/**
	 * 获取车辆类型(1:箱式,2:车板,3:冷藏)
	 * @return vehitype
	 */
	public String getVehitype() {
		return vehitype;
	}

	/**
	 * 设置车辆类型(1:箱式,2:车板,3:冷藏)
	 * @param newVehitype
	 */
	public void setVehitype(String newVehitype) {
		this.vehitype = newVehitype;
	}

	/**
	 * 获取车辆类型名称
	 * @return vehitypename
	 */
	public String getVehitypename() {
		return vehitypename;
	}

	/**
	 * 设置车辆类型名称
	 * @param newVehitypename
	 */
	public void setVehitypename(String newVehitypename) {
		this.vehitypename = newVehitypename;
	}

	/**
	 * 获取车辆长度(米)
	 * @return vehilength
	 */
	public String getVehilength() {
		return vehilength;
	}

	/**
	 * 设置车辆长度(米)
	 * @param newVehilength
	 */
	public void setVehilength(String newVehilength) {
		this.vehilength = newVehilength;
	}

	/**
	 * 获取车辆重量(吨)
	 * @return vehiweight
	 */
	public String getVehiweight() {
		return vehiweight;
	}

	/**
	 * 获取车辆重量(吨)
	 * @return newVehiweight
	 */
	public void setVehiweight(String newVehiweight) {
		this.vehiweight = newVehiweight;
	}

	/**
	 * 设置车头照片路径
	 * @param vehiheadimgpath
	 */
	public String getVehiheadimgpath() {
		return vehiheadimgpath;
	}

	/**
	 * 获取车头照片路径
	 * @return newVehiheadimgpath
	 */
	public void setVehiheadimgpath(String newVehiheadimgpath) {
		this.vehiheadimgpath = newVehiheadimgpath;
	}

	/**
	 * 设置车辆状态(-1:认证失败,0:未认证,1:认证成功,2:认证中) 

	 * @param status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置车辆状态(-1:认证失败,0:未认证,1:认证成功,2:认证中) 

	 * @param newStatus
	 */
	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	/**
	 * 获取认证失败原因
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置认证失败原因
	 * @param newMemo
	 */
	public void setMemo(String newMemo) {
		this.memo = newMemo;
	}
	
	/**
	 * 获取车辆司机表主键
	 * @return vehidriverid
	 */
	public String getVehidriverid() {
		return vehidriverid;
	}

	/**
	 * 设置车辆司机表主键
	 * @param newVehidriverid
	 */
	public void setVehidriverid(String newVehidriverid) {
		this.vehidriverid = newVehidriverid;
	}

	/**
	 * 获取司机主键
	 * @return driverid
	 */
	public String getDriverid() {
		return driverid;
	}

	/**
	 * 设置司机主键
	 * @param newDriverid
	 */
	public void setDriverid(String newDriverid) {
		this.driverid = newDriverid;
	}

	/**
	 * 获取司机姓名
	 * @return drivername
	 */
	public String getDrivername() {
		return drivername;
	}

	/**
	 * 设置司机姓名
	 * @param newDrivername
	 */
	public void setDrivername(String newDrivername) {
		this.drivername = newDrivername;
	}

	/**
	 * 获取司机电话
	 * @return drivertel
	 */
	public String getDrivertel() {
		return drivertel;
	}

	/**
	 * 设置司机电话
	 * @param newDrivertel
	 */
	public void setDrivertel(String newDrivertel) {
		this.drivertel = newDrivertel;
	}

	/**
	 * 获取驾驶证/身份证号
	 * @return identityCard
	 */
	public String getIdentityCard() {
		return identityCard;
	}

	/**
	 * 设置驾驶证/身份证号
	 * @param newIdentityCard
	 */
	public void setIdentityCard(String newIdentityCard) {
		this.identityCard = newIdentityCard;
	}

}