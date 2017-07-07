package com.tianrui.common.constants;

public class HttpUrl {

	//NC url ip+端口好
	public final static String NC_URL_IP_PORT = "http://172.18.6.207";//Constant.NC_PAY_URL; 
	//支付申请单保存接口--司机
	public final static String PAY_INVOICE_DRIVER_PUSH = "/tcp/paySupplier/querySupplier";
	//支付申请单保存接口--车主
	public final static String PAY_INVOICE_VENDER_PUSH = "/tcp/payinvoice/savePay";
	//查询司机账单并回写已付金额
	public final static String PAY_INVOICE_DRIVER_CALLBACK_PAIDAMOUNT = "/tcp/paySupplier/queryPayStatus";
	//查询车主账单并回写已付金额
	public final static String PAY_INVOICE_VENDER_CALLBACK_PAIDAMOUNT = "/tcp/payinvoice/queryPayStatus";
	//账单支付状态
	public final static String PAY_INVOICE_PAY_STATUS = "/service/TrPayStatusQryServlet";
	
	
	//供应商推送接口
	public final static String MEMBER_INFO_PUSH = "/service/TrSupplierAddServlet";
	//供应商推送NC状态查询接口
	public final static String MEMBER_INFO_PUSH_NC_STATUS = "/service/TrSupplierQryServlet";
	//供应商银行卡推送到NC
	public final static String BANK_CARD_PUSH_NC = "/service/TrSupBankAddServlet";
	
}
