package com.tianrui.app.web;

import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.cargoplan.PlanEditReq;
import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;

@SuppressWarnings("unused")
public class TestAppOwnerPlanInstance {

	private String id = "3fe63075979d451e8e4095ad8e697e4f";
	private String tokenId = "8a3aec9cedfb448b975133888b9adef5";
	private String tel = "15234567891";
	
	public static void main(String[] args) {
		TestAppOwnerPlanInstance t = new TestAppOwnerPlanInstance();
		//查询我发布的计划
//		t.queryPlan();
		//查询计划详情
//		t.queryDetail();
		//收回计划
//		t.cancle();
		//删除计划
//		t.delete();
		//完成计划
//		t.complete();
		//新增计划
//		t.save();
		//修改计划
		t.edit();
	}
	
	private void edit(){
		try {
			PlanEditReq req = new PlanEditReq();
			req.setId("10c4d77f8d8649eb816e7e01fc54543c");
			req.setTotalplanned("500");
			req.setStarttimeStr("2016-10-27 09:00:00");
			req.setEndtimeStr("2016-10-31 09:00:00");
			req.setLinkman("天猫自营超市");
			req.setTelephone("15234567891");
			req.setIsFamily("0");
			req.setVenderId("0be75d4f142149b89ab36c965be91e4b");
//			req.setIstemplate("1");//等于1时，存为模板
			TestAppConnection.queryParams("/app/planowner/edit", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void save(){
		try {
			PlanSaveReq req = new PlanSaveReq();
			req.setCargoid("4125f18f5aad40b4bed67639b1b67ed4");
			req.setRouteid("5d9977449ceb45aca44d5ee15e5dc435");
			req.setFreightid("17b271c79c0b40588226274db52a789d");
			req.setOrganizationname("天瑞集团水泥有限公司");
			req.setTotalplanned("500");
			req.setStarttimeStr("2016-10-27 09:00:00");
			req.setEndtimeStr("2016-10-31 09:00:00");
			req.setLinkman("天猫自营超市");
			req.setTelephone("15234567891");
			req.setIsFamily("0");
			req.setVenderId("0be75d4f142149b89ab36c965be91e4b");
			req.setIstemplate("1");//等于1时，存为模板
			TestAppConnection.queryParams("/app/planowner/add", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void complete(){
		try {
			PlanConfirmReq req = new PlanConfirmReq();
			req.setId("f36db835d8ff4a7aa1027ab137e97852");
			TestAppConnection.queryParams("/app/planowner/complete", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void delete(){
		try {
			MemberOwnerReq req = new MemberOwnerReq();
			req.setId("e75ba72e74ef4cef8932f392435790e2");
			TestAppConnection.queryParams("/app/planowner/delete", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void cancle(){
		try {
			MemberOwnerReq req = new MemberOwnerReq();
			req.setId("e75ba72e74ef4cef8932f392435790e2");
			TestAppConnection.queryParams("/app/planowner/cancle", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void queryDetail(){
		try {
			MemberOwnerReq req = new MemberOwnerReq();
			req.setId("04917affc378430588d8c1fd4015e8a8");
			TestAppConnection.queryParams("/app/planowner/detail", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void queryPlan(){
		try {
			MemberOwnerReq req = new MemberOwnerReq();
			TestAppConnection.queryParams("/app/planowner/page", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
