package com.tianrui.api.resp.front.cargoplan;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.util.DateTypeUtil;

public class PlanStatResp extends BaseResp{

	private double compTotal;
	private double tranTotal;
	public double getCompTotal() {
		return DateTypeUtil.doubleType(compTotal);
	}
	public void setCompTotal(double compTotal) {
		this.compTotal = compTotal;
	}
	public double getTranTotal() {
		return DateTypeUtil.doubleType(tranTotal);
	}
	public void setTranTotal(double tranTotal) {
		this.tranTotal = tranTotal;
	}
	
	
	
	
}
