package com.tianrui.common.constants;

public enum ErrorCode {

	//系统异常
	SYSTEM_ERROR("E000001","服务器繁忙."),
	SYSTEM_SERVER_ERROR("E000002","后台服务器繁忙."),
	SYSTEM_SUCCESS("000000","操作成功"),
	
	//参数异常
	PARAM_ERROR("E100001","参数异常."),
	PARAM_NULL_ERROR("E100002","参数异常,参数不能为空."),
	PARAM_TOKEN_ERROR("E100003","参数异常."),
	PARAM_PARAM_ERROR("E100004","校验码异常."),
	PARAM_FU_ERROR("E100005","参数不能为负."),
	
	
	//用户异常
	MEMBER_LOGIN_NOREG_ERROR("E200001","用户尚未注册."),
	MEMBER_LOGIN_PSWD_ERROR("E200002","用户密码错误."),
	MEMBER_USER_ERROR("E200003","用户尚未登录."),
	MEMBER_USER_LOGIN_MAST("E200004","该用户已经在别的设备登录,是否强制登录?"),
	MEMBER_PUSH_ERROR("E200005","供应商推单失败."),
	MEMBER_PUSH_ERROR1("222222","供应商推单数据重复."),
	MEMBER_NC_NOT_ORG("E200006","供应商NC未审核通过且未分配组织."),
	
	//路径档案
	FILEROUTE_USER_IS_NULL("E201001","登录用户或者用户组织为空."),
	FILEROUTE_REFUSE_NOT_ORG("E201002","不能操作不是同一组织的数据."),
	
	//运单信息
	BILL_NOT_FIND("E301001","不能操作不是同一组织的数据."),
	BILL_PERMISSIONS("E301002","该用户没有权限操作此运单."),
	BILL_STATUS_ERROR("E301003","操作失败,请刷新"),
	BILL_STATUS_VEHICLE_ONLYONE("E301004","同一个运力只能有一个在途运单"),
	BILL_STATUS_IMG_UPLOAD("E301005","磅单图片上传失败"),
	BILL_VEHICLE_BILLSTATUS("E301006","非空闲车辆不能接单"),
	
	BILL_DRIVER_DEL("E301007","该运单司机已删除"),
	BILL_VENDER_DEL("E301008","该运单车主已删除"),
	BILL_OWNER_DEL("E301009","该运单货主已删除"),
	
	//运价策略审核
	FILE_FREIGHT_NULL("E401000","请选择是否通过审核"),
	FILE_FREIGHT_AUDIT_NULL("E401001","请输入审核不通过原因"),
	FILE_FREIGHT_ERROR("E401002","操作失败，请稍后再试"),
	FILE_FREIGHT_INFO("E401003","未查到审核信息"),
	FILE_FREIGHT_AUDIT0("E401004","非审核中不能审核"),
	FILE_FREIGHT_UPDATE("E401005","审核中策略不能修改"),
	FILE_FREIGHT_UPT("E401006","运价策略有变动请再次查看"),
	
	//账单
	PAY_ITEMS_THAN_MAX("E401001","请最多选择20个账单."),
	PAY_DATA_NOT_EXIST("E401002","部分账单信息异常,请重试."),
	PAY_DATA_TYPE_NOT_EQUAL("E401003","账单发票类型不一致."),
	PAY_DATA_NOT_EXISTSS("E401004","账单发票查不到."),
	PAY_DATA_NOT_ADVICE("E401005","账单发票尚未审核通过."),
	PAY_DATA_NOT_STATUS_PUSHED("E401006","账单发票状态不合法.不是已推单状态."),
	PAY_DATA_NOT_STATUS_NULL("E401007","提交数据不全"),
	PAY_DATA_NOT_STATUS_EQUALE("E401008","有已经开票的运单,数据不合法."),
	PAY_DATA_NOT_USERPAY("E401009","非本人账单不能操作."),
	PAY_DATA_NOT_ADMIN("E401010","非本组织账单不能操作."),
	PAY_DATA_NOT_ISCLEAN("E401011","运单已确认，不能重复操作"),
	PAY_DATA_PAY_ADVICE("E401012","不合法的支付单状态"),
	PAY_INVOICE_ERROR("E401013","账单推单失败."),
	PAY_INVOICE_ERROR1("E401013","账单已推过，请勿重复推单."),
	PAY_INVOICE_ERROR2("E401014","账单支付中不能更换银行卡."),
	PAY_INVOICE_ERROR3("E401015","账单不存在."),
	PAY_INVOICE_ERROR4("E401016","账单应付金额不一致."),
	PAY_INVOICE_ERROR5("E401017","账单收款人名称不一致."),
	PAY_INVOICE_ERROR6("E401018","账单收款人证件号不一致."),
	PAY_INVOICE_ERROR7("E401019","账单收款人银行卡号不一致."),
	//运力共享_
	VEHICLE_CAPA_EXIST("E501000","运力已添加"),
	VEHICLE_CAPA_VEHICLE("E501001","车辆不存在或未绑定司机"),
	
	VEHICLE_TICKET_IDNULL("E502001","无效的认证id"),
	VEHICLE_VEHICLE_IDNULL("E502002","无效的车辆id"),
	VEHICLE_TICKET_NOTONLY("E502003","车辆开票认证已存在"),
	VEHICLE_DRIVER_NOTONLY("E502004","非空闲车辆不能进行开票认证"),
	VEHICLE_DRIVER1_NOTONLY("E502004","绑定车辆司机不能进行开票认证"),
	
	//资金账户
	CAPITAL_IN_PROCESS("E880000","资金账户正在处理中，请稍后。"),
	CAPITAL_ACCOUNT_NULL("E880001","用户资金账户不存在，无法操作！"),
	CAPITAL_NOT_ENOUGH("E880002","用户资金账户金额不足，无法提现！"),
	CAPITALNO_NOT_FIND("E880003","未发现的提现流水号！"),
	CAPITALNO_DISPOSED("E880004","提现流水号已经处理完成！"),
	CAPITAL_WAYBILLNO_NULL("E880005","运单编号不能为空！"),
	CAPITAL_WAYBILLNO_DISPOSED("E880006","运单编号对应的运费记录已存在，请勿重复操作！"),
	CAPITAL_WAYBILLNO_NOT_PENDING("E880007","运单编号对应的运费记录不存在，请确认参数正确！"),
	CAPITAL_WAYBILLNO_PAYMENT("E880008","运单编号对应的运费已支付，请确认参数正确！"),
	CAPITAL_NOT_CELLPHONE_OR_USERYHNO("E880009","用户登录账号和银行唯一标识不能为空！"),
	CAPITAL_NOT_TRANSACTIONTYPE("E880010","不支持的交易类型！"),
	CAPITAL_RECORD_NULL("E880011","资金流水异常，系统未发现前置正常资金流水，无法操作！"),
	CAPITAL_PENDINGMONEY_ZERO("E880012","待收入运费金额必须大于0！"),
	CAPITAL_PENDMONEY_ZERO("E880013","收入运费金额必须大于0！"),
	CAPITAL_WITHDROW_ZERO("E880014","提现金额必须大于0！"),
	
	//银行卡
	NOT_FIND_BANK("E600000","没有找到匹配的银行."),
	NOT_PUSH_BANK("E600001","银行未推送到NC."),
	BANK_CARD_NOT_EXIST("E600002","该银行卡不存在"),
	BANK_CARD_NOT_AUDIT("E600003","银行卡未通过审核."),
	
	MONEY_CHECK_PW_NULL("E700001","用户未设置支付密码"),
	MONEY_CHECK_PW_TYPE("E700002","未选择密码校验类型"),
	MONEY_CHECK_PW_TP1("E700003","支付密码校验未通过"),
	MONEY_CHECK_PW_TP2("E700004","手势密码校验未通过"),
	MONEY_CHECK_PW_FALL("E700005","密码校验失败"),
	
	MONEY_CHECK_TP1_NULL("E700006","未设置支付密码"),
	MONEY_CHECK_TP2_NULL("E700007","未设置手势密码"),
	MONEY_CHECK_TP2_FALL("E700008","未开启手势密码"),
	
	
	MEMBER_MERGER_NULL("E800001","未查询到该用户"),
	MEMBER_VEHICLE_STATUS("E800002","存在非空闲车辆"),
	
	MESSAGE_GROUP_ERNULL("E900001","未查到对应数据"),
	MESSAGE_GROUP_ERMEMBER("E900002","推送用户id有误"),
	MESSAGE_GROUP_PUSHIDNULL("E900003","未找到用户推送ID"),
	MESSAGE_GROUP_MSGNULL("E900004","推送消息为空"),
	MESSAGE_GROUP_GRNULL("E900005","未找到消息分组")
	;
	
	private String code;
	private String msg;
	
	private ErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
