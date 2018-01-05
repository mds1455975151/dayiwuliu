package com.tianrui.service.bean;

public class MaterielRoute {
    private String id;

    private String materieId;

    private String materieName;

    private String routename;

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
        this.id = id == null ? null : id.trim();
    }

    public String getMaterieId() {
        return materieId;
    }

    public void setMaterieId(String materieId) {
        this.materieId = materieId == null ? null : materieId.trim();
    }

    public String getMaterieName() {
        return materieName;
    }

    public void setMaterieName(String materieName) {
        this.materieName = materieName == null ? null : materieName.trim();
    }

    public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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
        this.modifier = modifier == null ? null : modifier.trim();
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
        this.materieStatus = materieStatus == null ? null : materieStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}