package com.tianrui.api.resp.admin;

public class OrgSignerResp {
    private String id;

    private String orgid;

    private String cellphone;

    private String memberid;

    private String membername;

    private String remark;

    private String creater;

    private Long createtime;

    private String upter;

    private Long upttime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername == null ? null : membername.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getUpter() {
        return upter;
    }

    public void setUpter(String upter) {
        this.upter = upter == null ? null : upter.trim();
    }

    public Long getUpttime() {
        return upttime;
    }

    public void setUpttime(Long upttime) {
        this.upttime = upttime;
    }
}