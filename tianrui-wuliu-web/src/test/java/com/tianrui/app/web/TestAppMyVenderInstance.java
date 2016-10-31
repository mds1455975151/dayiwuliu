package com.tianrui.app.web;

import com.tianrui.api.req.front.capa.CapaReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;

@SuppressWarnings("unused")
public class TestAppMyVenderInstance {

	private String id = "3fe63075979d451e8e4095ad8e697e4f";
	private String tokenId = "eed775989be44347a7af3722c64a0c3b";
	private String tel = "15234567891";
	
	public static void main(String[] args) {
		TestAppMyVenderInstance t = new TestAppMyVenderInstance();
		//查询我的车主
//		t.queryMyVender();
		//添加车主
//		t.saveMyVender();
		//删除车主
//		t.deleteMyVender();
		//开启/关闭车主
//		t.updateMyVender("0");
		//查询车主车辆
//		t.venderCapa();
		//搜索我的车主
		t.searchMyVender();
	}
	
	private void searchMyVender() {
		try {
			MemberOwnerReq req = new MemberOwnerReq();
			req.setOwnerTel("15234567892");
			TestAppConnection.queryParams("/app/appMyVenderAction/searchMyVender", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void venderCapa() {
		try {
			CapaReq req = new CapaReq();
			req.setMemberid("0be75d4f142149b89ab36c965be91e4b");
			TestAppConnection.queryParams("/app/appMyVenderAction/venderCapa", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateMyVender(String isEnabled) {
		try {
			MemberOwnerReq req = new MemberOwnerReq();
			req.setId("949a92a8c9cf4690a74e0bd8b3f325f7");
			req.setIsEnabled(isEnabled);
			TestAppConnection.queryParams("/app/appMyVenderAction/updateMyVender", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteMyVender() {
		try {
			MemberOwnerReq req = new MemberOwnerReq();
			req.setId("949a92a8c9cf4690a74e0bd8b3f325f7");
			TestAppConnection.queryParams("/app/appMyVenderAction/deleteMyVender", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveMyVender() {
		try {
			MemberOwnerReq req = new MemberOwnerReq();
			req.setOwnerId("0be75d4f142149b89ab36c965be91e4b");
			req.setOwnerName("德邦物流公司");
			req.setOwnerTel("15234567892");
			TestAppConnection.queryParams("/app/appMyVenderAction/saveMyVender", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void queryMyVender(){
		try {
			MemberOwnerReq req = new MemberOwnerReq();
			req.setOwnerTel("15234567892");
			TestAppConnection.queryParams("/app/appMyVenderAction/getMyVender", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
