package com.tianrui.app.web;

import com.tianrui.api.req.front.cargoplan.PlanTemplateReq;

@SuppressWarnings("unused")
public class TestAppPlanTemplateInstance {

	private String id = "3fe63075979d451e8e4095ad8e697e4f";
	private String tokenId = "a00091c50b0b446e854acc53dd3120c5";
	private String tel = "15234567891";
	
	public static void main(String[] args) {
		TestAppPlanTemplateInstance t = new TestAppPlanTemplateInstance();
		//查询计划模板
//		t.getPlanTemplate();
		//查询计划模板详情
//		t.getPlanTemplateDetail();
		//删除计划模板
		t.deletePlanTemplate();
	}

	private void deletePlanTemplate(){
		try {
			PlanTemplateReq req = new PlanTemplateReq();
			req.setId("80adeccc3fd048e3b4b92bfae75fecc3");
			TestAppConnection.queryParams("/app/plantemplate/delete", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getPlanTemplateDetail(){
		try {
			PlanTemplateReq req = new PlanTemplateReq();
			req.setId("80adeccc3fd048e3b4b92bfae75fecc3");
			TestAppConnection.queryParams("/app/plantemplate/detail", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getPlanTemplate(){
		try {
			PlanTemplateReq req = new PlanTemplateReq();
			TestAppConnection.queryParams("/app/plantemplate/page", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
