package com.tianrui.app.web;

import com.tianrui.api.req.admin.FileOrgCargoReq;
import com.tianrui.api.req.front.cargoplan.FreightReq;
import com.tianrui.api.req.front.cargoplan.RouteReq;

@SuppressWarnings("unused")
public class TestAppFreightInstance {

	private String id = "3fe63075979d451e8e4095ad8e697e4f";
	private String tokenId = "ea11ff8ac8b148bcb327901964864493";
	private String tel = "15234567891";
	
	public static void main(String[] args) {
		TestAppFreightInstance t = new TestAppFreightInstance();
		//查询货物信息
//		t.cargo();
		//查询路线信息
//		t.route();
		//查询运价策略
//		t.freight();
		//查询运价策略有效性
//		t.validForFreight();
		//查询运价策略货物路线信息
		t.findFCR();
	}
	
	private void findFCR(){
		try {
			FreightReq req = new FreightReq();
			req.setId("3af78019f9b2418897ca9f9df1a65830");
			TestAppConnection.queryParams("/app/freight/findFCR", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void validForFreight(){
		try {
			FreightReq req = new FreightReq();
			req.setId("3af78019f9b2418897ca9f9df1a65830");
			TestAppConnection.queryParams("/app/freight/validForFreight", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void freight(){
		try {
			FreightReq req = new FreightReq();
			TestAppConnection.queryParams("/app/freight/freight", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void route(){
		try {
			RouteReq req = new RouteReq();
			req.setStatus("1");
			TestAppConnection.queryParams("/app/freight/route", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void cargo(){
		try {
			FileOrgCargoReq req = new FileOrgCargoReq();
			req.setState("1");
			TestAppConnection.queryParams("/app/freight/cargo", id, tokenId, tel, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
