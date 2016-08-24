package com.tianrui.service.admin.bean;

public class FreightInfo {
    private String id;

    private String freightid;

    private Long taketime;

    private Double price;

    private Double tallage;

    private String freighttype;

    private String status;

    private String recent;

    private String creater;

    private Long createtime;

    private String updater;

    private Long updatetime;

    private String desc1;

    private String desc2;

    private String desc3;

    private String desc4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFreightid() {
        return freightid;
    }

    public void setFreightid(String freightid) {
        this.freightid = freightid == null ? null : freightid.trim();
    }

    public Long getTaketime() {
        return taketime;
    }

    public void setTaketime(Long taketime) {
        this.taketime = taketime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTallage() {
        return tallage;
    }

    public void setTallage(Double tallage) {
        this.tallage = tallage;
    }

    public String getFreighttype() {
        return freighttype;
    }

    public void setFreighttype(String freighttype) {
        this.freighttype = freighttype == null ? null : freighttype.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRecent() {
        return recent;
    }

    public void setRecent(String recent) {
        this.recent = recent == null ? null : recent.trim();
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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    public String getDesc4() {
        return desc4;
    }

    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
    }
}