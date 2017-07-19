package com.tianrui.common.enums;

public enum MessageCodeEnum {

	
	DEMO_1(1,"测试demo",0),
	
	//计划相关(30-70)
	//发送给车主
	PLAN_2VENDER_CREATE(30,"货运计划[<_>]已被用户(<_>)指派给您，请查看详情",2),
	PLAN_2VENDER_CANCLE(31,"货运计划[<_>]已被用户(<_>)收回.",2),
	PLAN_2VENDER_APPOINT(32,"货运计划[<_>]已被用户(<_>)委派给您，请查看详情",2),
	//发送给货主
	PLAN_2OWNER_CREATE(50,"货运计划[<_>]已被您创建成功，请查看详情",3),
	PLAN_2OWNER_ACCEPT(52,"货运计划[<_>]已被车主(<_>)接受,请查看详情",3),
	PLAN_2OWNER_REFUSE(53,"货运计划[<_>]已被车主(<_>)拒绝,请查看详情",3),
	
	//运单相关(101 - 160)
	//发送给车主
	BILL_2VENDER_CREATE(101,"运单号[<_>]已创建成功,请查看详情.",2),
	BILL_2VENDER_ACCEPT(102,"驾驶员(<_>)已接受运单号[<_>].请查看详情.",2),
	BILL_2VENDER_REFUSE(103,"驾驶员(<_>)拒绝接受运单号[<_>].请查看详情.",2),
	BILL_2VENDER_PICKUP(104,"驾驶员(<_>)到达发货地点,开始装货确认,运单号[<_>].请查看详情.",2),
	BILL_2VENDER_DEPARTURE(105,"驾驶员(<_>)已装货完毕,运单号[<_>].请查看详情.",2),
	BILL_2VENDER_ARRIVED(106,"驾驶员(<_>)已到达收货地点,准备卸货.运单号[<_>].请查看详情.",2),
	BILL_2VENDER_DISCHARGE(107,"驾驶员(<_>)已经卸货完成.运单号[<_>].请查看详情.",2),
	BILL_2VENDER_SIGN(108,"运单[<_>]已被用户(<_>)签收.请查看详情",2),
	BILL_2VENDER_ALSIGN(109,"安联运单[<_>]已被用户(<_>)签收.请查看详情",2),

	//发送给货主
	BILL_2OWNER_DISCHARGE(121,"驾驶员(<_>)已经卸货完成.运单号[<_>].请进行签收确认.",3),

	//发送给司机
	BILL_2DRIVER_CREATE(140,"运单[<_>]已被用户(<_>)指派给您,请查看详情.",1),
	BILL_2DRIVER_UPDATE(141,"运单[<_>]已被用户(<_>)修改内容,请查看详情.",1),
	BILL_2DRIVER_CANCLE(145,"运单[<_>]已被用户(<_>)取消,请查看详情.",1),
	
	BILL_2DRIVER_PLANCOMPLATE(146,"计划[<_>]已完成.运单[<_>]自动关闭,请查看详情.",1),
	
	/** ========================= 运力相关(161 - 180) ======================= */
	// 发送给司机
	VEHI_2DRIVER_ADD(161,"[<_>]请求添加您为司机，请确认。",1),
	// 发送给车主
	VEHI_2OWNER_ADD(162,"[<_>]请求添加您为车主，请确认。",2),
	// 发送给车主
	//VEHI_2VEHICLE_ADD(163,"车牌号为[<_>]的车辆已提交审核，请尽快确认。",2),
	VEHI_2DRIVER_USERADD(164,"[<_>]已经添加您为司机",1),
	VEHI_2OWNER_USERADD(165,"[<_>]已经添加您为车主",2),
	
	/** ========================= 消息相关(181 - 200) ======================= */
	MSG_2OWNWE_REFUSE(181,"车主[<_>]拒绝您添加车主的请求。",3),
	MSG_2OWNER_APPLY(182,"车主[<_>]同意您添加车主的请求。",3),
	MSG_2VENDER_REFUSE(183,"司机[<_>]拒绝您添加司机的请求。",2),
	MSG_2VENDER_APPLY(184,"司机[<_>]同意您添加司机的请求。",2),
	

	/** ========================= 后台审核相关(201 - 220) ======================= */
	ADMIN_USER_PASS(201,"账号[<_>],个人认证后台审核通过",0),
	ADMIN_USER_NOTPASS(202,"账号[<_>],个人认证后台审核未通过",0),
	ADMIN_DRIVER_PASS(203,"账号[<_>],司机认证后台审核通过",2),
	ADMIN_DRIVER_NOTPASS(204,"账号[<_>],司机认证后台审核未通过",2),	
	ADMIN_VEHICLE_PASS(205,"车牌号[<_>],车辆认证后台审核通过",2),
	ADMIN_VEHICLE_NOTPASS(206,"车牌号[<_>],车辆认证后台审核未通过",2),
	ADMIN_COMPANY_PASS(207,"账号[<_>],企业认证后台审核通过",0),
	ADMIN_COMPANY_NOTPASS(208,"账号[<_>],企业认证后台审核未通过",0),
	ADMIN_BANKCARD_PASS(209,"银行卡[<_>],后台审核通过",0),
	ADMIN_BANKCARD_NOTPASS(210,"银行卡[<_>],后台审核未通过,不通过原因[<_>]",0),	
	
	/** ========================= 司机换班相关(221 - 240) ======================= */
	DRIVER_TRANSFER_BEG(221,"司机[<_>],请求与您换班",1),
	DRIVER_TRANSFER_AGREE(222,"司机[<_>],同意了您的换班请求",1),
	DRIVER_TRANSFER_REFUSE(223,"司机[<_>],拒绝了您的换班请求",1),
	
	/** ========================= 运力共享相关(221 - 240) ======================= */
	DRIVER_CAPA_BEG(241,"用户[<_>],请求使用您的车辆[<_>]",2),
	DRIVER_CAPA_AGREE(242,"车主[<_>],同意了您的使用请求",2),
	DRIVER_CAPA_REFUSE(243,"车主[<_>],拒绝了您的使用请求",2),
	DRIVER_CAPA_USERBEG(244,"用户[<_>],添加了您的车辆[<_>]",2);
	
	//消息code
	private int code;
	//消息描述
	private String desc;
	//1 发送给司机 2 发送给车主 3 发送给货主 0通用
	private int type;
	private MessageCodeEnum(int code, String desc,int type) {
		this.code = code;
		this.desc = desc;
		this.type = type;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
