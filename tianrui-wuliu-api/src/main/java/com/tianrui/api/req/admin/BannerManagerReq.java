package com.tianrui.api.req.admin;

public class BannerManagerReq {
    private String id;

    private String picName;

    private String httpUrl;

    private Byte picStatus;

    private Byte pushStatus;

    private String creator;

    private Long createDate;

    private String modifier;

    private Long modifierTime;

    private String status;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl == null ? null : httpUrl.trim();
    }

    public Byte getPicStatus() {
        return picStatus;
    }

    public void setPicStatus(Byte picStatus) {
        this.picStatus = picStatus;
    }

    public Byte getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Byte pushStatus) {
        this.pushStatus = pushStatus;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}