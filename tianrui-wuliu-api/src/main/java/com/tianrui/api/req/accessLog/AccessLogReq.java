package com.tianrui.api.req.accessLog;

public class AccessLogReq {
    private String id;
    
    private Integer pageNo;
    
    private Integer pageSize;

    private String systemUrl;

    private String systemToken;

    private String vehicleNo;

    private String beginTime;

    private String endTime;

    private String respTotal;

    private String respCode;

    private String respError;

    private String respData;

    private String creater;

    private Long createtime;

    private String updater;

    private Long updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSystemUrl() {
        return systemUrl;
    }

    public void setSystemUrl(String systemUrl) {
        this.systemUrl = systemUrl == null ? null : systemUrl.trim();
    }

    public String getSystemToken() {
        return systemToken;
    }

    public void setSystemToken(String systemToken) {
        this.systemToken = systemToken == null ? null : systemToken.trim();
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getRespTotal() {
        return respTotal;
    }

    public void setRespTotal(String respTotal) {
        this.respTotal = respTotal == null ? null : respTotal.trim();
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode == null ? null : respCode.trim();
    }

    public String getRespError() {
        return respError;
    }

    public void setRespError(String respError) {
        this.respError = respError == null ? null : respError.trim();
    }

    public String getRespData() {
        return respData;
    }

    public void setRespData(String respData) {
        this.respData = respData == null ? null : respData.trim();
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
}