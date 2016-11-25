package com.tianrui.api.resp.front.CreditScore;

import com.tianrui.api.resp.BaseResp;

public class CreditScoreResp extends BaseResp {
	
	private static final long serialVersionUID = 6858019523956527131L;

	private String id;

    private String creditmanageid;

    private Integer addassist;

    private Integer addspecial;

    private Integer addcontribute;

    private Integer submanned;

    private Integer suboperation;

    private Integer subaccident;

    private Integer subcomplaint;

    private Integer subturnover;

    private Integer subdisrupt;

    private Long createtime;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCreditmanageid() {
        return creditmanageid;
    }

    public void setCreditmanageid(String creditmanageid) {
        this.creditmanageid = creditmanageid == null ? null : creditmanageid.trim();
    }

    public Integer getAddassist() {
        return addassist;
    }

    public void setAddassist(Integer addassist) {
        this.addassist = addassist;
    }

    public Integer getAddspecial() {
        return addspecial;
    }

    public void setAddspecial(Integer addspecial) {
        this.addspecial = addspecial;
    }

    public Integer getAddcontribute() {
        return addcontribute;
    }

    public void setAddcontribute(Integer addcontribute) {
        this.addcontribute = addcontribute;
    }

    public Integer getSubmanned() {
        return submanned;
    }

    public void setSubmanned(Integer submanned) {
        this.submanned = submanned;
    }

    public Integer getSuboperation() {
        return suboperation;
    }

    public void setSuboperation(Integer suboperation) {
        this.suboperation = suboperation;
    }

    public Integer getSubaccident() {
        return subaccident;
    }

    public void setSubaccident(Integer subaccident) {
        this.subaccident = subaccident;
    }

    public Integer getSubcomplaint() {
        return subcomplaint;
    }

    public void setSubcomplaint(Integer subcomplaint) {
        this.subcomplaint = subcomplaint;
    }

    public Integer getSubturnover() {
        return subturnover;
    }

    public void setSubturnover(Integer subturnover) {
        this.subturnover = subturnover;
    }

    public Integer getSubdisrupt() {
        return subdisrupt;
    }

    public void setSubdisrupt(Integer subdisrupt) {
        this.subdisrupt = subdisrupt;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}