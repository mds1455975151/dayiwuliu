package com.tianrui.common.enums;

public enum MessageCodeEnum {

	
	DEMO_1(1,"测试demo"),
	
	//计划相关(30-70)
	//发送给车主
	PLAN_2VENDER_CREATE(30,"货运计划[<_>]已被用户(<_>)指派给您，请查看详情"),
	PLAN_2VENDER_CANCLE(31,"货运计划[<_>]已被用户(<_>)收回."),
	//发送给货主
	PLAN_2OWNER_CREATE(50,"货运计划[<_>]已被您创建成功，请查看详情"),
	PLAN_2OWNER_UPDATE(51,"货运计划[<_>]已被您修改成功，请查看详情"),
	PLAN_2OWNER_ACCEPT(52,"货运计划[<_>]已被车主(<_>)接受,请查看详情"),
	PLAN_2OWNER_REFUSE(53,"货运计划[<_>]已被车主(<_>)拒绝,请查看详情"),
	
	//运单相关(101 - 160)
	//发送给车主
	BILL_2VENDER_CREATE(101,"运单号[<_>]已创建成功,请查看详情."),
	BILL_2VENDER_ACCEPT(102,"驾驶员(<_>)已接受运单号[<_>].请查看详情."),
	BILL_2VENDER_REFUSE(103,"驾驶员(<_>)拒绝接受运单号[<_>].请查看详情."),
	BILL_2VENDER_PICKUP(104,"驾驶员(<_>)到达发货地点,开始装货确认,运单号[<_>].请查看详情."),
	BILL_2VENDER_DEPARTURE(105,"驾驶员(<_>)已装货完毕,运单号[<_>].请查看详情."),
	BILL_2VENDER_ARRIVED(106,"驾驶员(<_>)已到达收货地点,准备卸货.运单号[<_>].请查看详情."),
	BILL_2VENDER_DISCHARGE(107,"驾驶员(<_>)已经卸货完成.运单号[<_>].请查看详情."),
	BILL_2VENDER_SIGN(108,"运单[<_>]已被用户(<_>)签收.请查看详情"),

	//发送给货主
	BILL_2OWNER_DISCHARGE(121,"驾驶员(<_>)已经卸货完成.运单号[<_>].请进行签收确认."),

	//发送给司机
	BILL_2DRIVER_CREATE(140,"运单[<_>]已被用户(<_>)指派给您,请查看详情."),
	BILL_2DRIVER_UPDATE(141,"运单[<_>]已被用户(<_>)修改内容,请查看详情."),
	BILL_2DRIVER_CANCLE(145,"运单[<_>]已被用户(<_>)取消,请查看详情."),
	
	BILL_2DRIVER_PLANCOMPLATE(146,"计划[<_>]已完成.运单[<_>]自动关闭,请查看详情."),
	
	/** ========================= 运力相关(161 - 180) ======================= */
	// 发送给司机
	VEHI_2DRIVER_ADD(161,"[<_>]请求添加您为司机，请确认。"),
	// 发送给车主
	VEHI_2OWNER_ADD(162,"[<_>]请求添加您为车主，请确认。"),
	// 发送给车主
	VEHI_2VEHICLE_ADD(163,"车牌号为[<_>]的车辆已提交审核，请尽快确认。"),
	
	/** ========================= 消息相关(181 - 200) ======================= */
	MSG_2OWNWE_REFUSE(181,"车主[<_>]拒绝您添加车主的请求。"),
	MSG_2OWNER_APPLY(182,"车主[<_>]同意您添加车主的请求。"),
	MSG_2VENDER_REFUSE(183,"司机[<_>]拒绝您添加司机的请求。"),
	MSG_2VENDER_APPLY(184,"司机[<_>]请求您添加司机的请求。"),
	

	/** ========================= 后台审核相关(201 - 220) ======================= */
	ADMIN_USER_PASS(201,"账号[<_>],个人认证后台审核通过"),
	ADMIN_USER_NOTPASS(202,"账号[<_>],个人认证后台审核未通过"),
	ADMIN_DRIVER_PASS(203,"账号[<_>],司机认证后台审核通过"),
	ADMIN_DRIVER_NOTPASS(204,"账号[<_>],司机认证后台审核未通过"),
	ADMIN_VEHICLE_PASS(205,"车牌号[<_>],车辆认证后台审核通过"),
	ADMIN_VEHICLE_NOTPASS(206,"车牌号[<_>],车辆认证后台审核未通过"),
	ADMIN_COMPANY_PASS(207,"账号[<_>],企业认证后台审核通过"),
	ADMIN_COMPANY_NOTPASS(208,"账号[<_>],企业认证后台审核未通过"),
	
	/** ========================= 司机换班相关(221 - 240) ======================= */
	DRIVER_TRANSFER_BEG(221,"司机[<_>],请求与您换班"),
	DRIVER_TRANSFER_AGREE(222,"司机[<_>],同意了您的换班请求"),
	DRIVER_TRANSFER_REFUSE(223,"司机[<_>],拒绝了您的换班请求");
	//消息code
	private int code;
	//消息描述
	private String desc;
	private MessageCodeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
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
	
	
}
