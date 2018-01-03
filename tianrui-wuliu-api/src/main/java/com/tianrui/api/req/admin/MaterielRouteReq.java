package com.tianrui.api.req.admin;

public class MaterielRouteReq {

	private String id;
    //路线的id拼成的字符串
    private String routeIds;

    private String materieId;

    private String materieName;

    private String routeName;

    private String creator;

    private Long createDate;

    private String modifier;

    private Long modifierTime;

    private String materieStatus;

    private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getRouteIds() {
		return routeIds;
	}

	public void setRouteIds(String routeIds) {
		this.routeIds = routeIds;
	}

	public String getMaterieId() {
		return materieId;
	}

	public void setMaterieId(String materieId) {
		this.materieId = materieId;
	}

	public String getMaterieName() {
		return materieName;
	}

	public void setMaterieName(String materieName) {
		this.materieName = materieName;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Long getModifierTime() {
		return modifierTime;
	}

	public void setModifierTime(Long modifierTime) {
		this.modifierTime = modifierTime;
	}

	public String getMaterieStatus() {
		return materieStatus;
	}

	public void setMaterieStatus(String materieStatus) {
		this.materieStatus = materieStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
