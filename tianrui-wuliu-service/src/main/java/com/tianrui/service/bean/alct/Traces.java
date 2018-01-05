package com.tianrui.service.bean.alct;

public class Traces {

	private String shipmentCode;
	
	private String coordinationType;
	
	private TracesPosition traces;

	public String getShipmentCode() {
		return shipmentCode;
	}

	public void setShipmentCode(String shipmentCode) {
		this.shipmentCode = shipmentCode;
	}

	public String getCoordinationType() {
		return coordinationType;
	}

	public void setCoordinationType(String coordinationType) {
		this.coordinationType = coordinationType;
	}

	public TracesPosition getTraces() {
		return traces;
	}

	public void setTraces(TracesPosition traces) {
		this.traces = traces;
	}
}
