package com.tianrui.api.resp.front.message;

public class AppPlatformMessageResp {

	private String dataName;//数据名称
    
    private String dataValue;//统计数量
    
    private String trend;//数据趋势

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}
    
}
