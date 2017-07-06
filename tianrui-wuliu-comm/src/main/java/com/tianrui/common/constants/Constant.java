package com.tianrui.common.constants;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @类描述：一些常量类工具
 * @创建人：tank
 * @创建时间：2016年1月17日下午4:51:26
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午4:51:26
 * @修改备注：
 *
 */
public class Constant {
	
	public final static Integer ZERO = 0;
	
	public final static Integer ONE = 1;
	
	public final static Integer TWO = 2;
	
	public final static Integer THREE = 3;

	
	public final static String ZERO_STR = "0";
	
	public final static String ONE_STR = "1";
	
	public final static String TWO_STR = "2";
	
	public final static String THREE_STR = "3";
    
    public final static String INFO = "[INFO]";
    
    public final static int MAX_PAGESIZE = 200;
    
    //图片文件前缀
    public static String FILE_URL_PRE="";
    //app接口验证串
    public static String apiAuthKey ="";
    //redis 缓存的前缀 wl2_|-正式-43|  wl_|-开发-23|
    public  static String PRE_REDIS ="";
    //万能验证码是否生校   1生效
    public static String isAuthCodeUsed="";
    //万能验证码值
    public static String authCodeValue="";
    //nc支付url
    public static String NC_PAY_URL="";
    //安联开票URL
    public static String ANLIAN_PIAO_URL;
    //安联用户名
    public static String ANLIAN_USERNAME;
    //安联密码
    public static String ANLIAN_PASSWORD;
    //微信appid
    public static String WEIXIN_APPID;
    //微信secret
    public static String WEIXIN_SECRET;
   
    //请求短信接口的频率 5分钟
    public static Integer GET_VCODE_FREE;
    //规定时间内最多请求次数 20次
    public static Integer GET_VCODE_MAX;
    //禁止访问时间 2小时
    public static Integer GET_FORBIDDEN_TIME;
    
    public static String IOS_PUSH_KEY="";
    public static String IOS_PUSH_SERECTKET="";
    public static String IOS_PUSH_TYPE="";
    
    //添加关系是否需要同意
    public static String SYSTEM_PUSH_STATUS;
    //系统承运方
    public static String SYSTEM_SHIPPER;
    //运单推送交通部URL
    public static String JTB_PUT_URL;
    
    //日志文件 访问日志 记录访问来源
    public final static String ACCESS ="access";
    //日志文件  性能日志 记录返回时间
    public final static String PREF ="pref";
    //消息 替换符合
    public final static String MESSAGE_SPLIT="<_>";
    // * 1 实名认证
    public final static String AUTHCHECK_USER ="1";
    // * 2 货主认证
    public final static String AUTHCHECK_OWNER ="2";
    // * 3 司机认证
    public final static String AUTHCHECK_DRIVBR ="3";
    // * 4 车主认证
    public final static String AUTHCHECK_VEHICLE_OWNER="4";
    
    
    //认证申请状态
    //初始化
    public final static String AUTHSTATUS_INIT ="0";
    //认证通过
    public final static String AUTHSTATUS_PASS ="1";
    //认证中
    public final static String AUTHSTATUS_ING ="2";
    //认证失败
    public final static String AUTHSTATUS_FAIL ="3";
    //认证申请状态
    //初始化
    public final static Short AUTHSTATUS_INIT_SHORT = 0;
    //认证通过
    public final static Short AUTHSTATUS_PASS_SHORT = 1;
    //认证中
    public final static Short AUTHSTATUS_ING_SHORT = 2;
    //认证失败
    public final static Short AUTHSTATUS_FAIL_SHORT = 3;
    
    //运单类型
    //普通运单
    public final static String BILL_TYPE_0 = "0";
    //熟车运单
    public final static String BILL_TYPE_1 = "1";
    //批量运单
    public final static String BILL_TYPE_2 = "2";

    /**
     * 账单常量
     */
    //无效
    public final static Integer DATA_INVALID = 0;
    //有效
    public final static Integer DATA_VALID = 1;
    //收货人身份
    //司机
    public final static Integer PAY_INVOICE_DRIVER = 1;
    //车主
    public final static Integer PAY_INVOICE_VENDER = 2;
    //审核状态
    //未审核
    public final static Integer NOT_AUDIT = 0;
    //审核中
    public final static Integer AUDIT_ING = 1;
    //已审核
    public final static Integer YES_AUDIT = 2;
    //推单状态
    //未推单
    public final static Integer NOT_PUSH = 0;
    //推单中
    public final static Integer PUSH_ING = 1;
    //已推单
    public final static Integer YES_PUSH = 2;
    //支付状态
    //未支付
    public final static Integer NOT_PAY = 0;
    //支付中
    public final static Integer PAY_ING = 1;
    //已支付
    public final static Integer YES_PAY = 2;
    
    /**
     * NC供应商推送状态
     */
    //供应商不存在
    public final static Integer NC_MEMBER_PUSH_STATUS_DOES_NOT_EXIST = 1;
    //未审核
    public final static Integer NC_MEMBER_PUSH_STATUS_NOT_AUDIT = 2;
    //审核未通过
    public final static Integer NC_MEMBER_PUSH_STATUS_AUDIT_REFUSED = 3;
    //审核中
    public final static Integer NC_MEMBER_PUSH_STATUS_AUDIT_ING = 4;
    //审核通过，但组织未分配
    public final static Integer NC_MEMBER_PUSH_STATUS_NOT_ORG = 5;
    //审核通过，但组织已分配
    public final static Integer NC_MEMBER_PUSH_STATUS_YES_ORG = 6;
    
    /**
     * 银行卡开户人身份
     */
    //个人
    public final static String BANK_ACCOUNT_PERSON_IDENTITY_GR = "1";
    //公司
    public final static String BANK_ACCOUNT_PERSON_IDENTITY_GS = "2";
    
	public static String getFILE_URL_PRE() {
		return FILE_URL_PRE;
	}

	public void setFILE_URL_PRE(String fILE_URL_PRE) {
		FILE_URL_PRE = fILE_URL_PRE;
	}
	public void setApiAuthKey(String apiAuthKey) {
		Constant.apiAuthKey = apiAuthKey;
	}
	public void setPRE_REDIS(String pRE_REDIS) {
		PRE_REDIS = pRE_REDIS;
	}
	public static void setIsAuthCodeUsed(String isAuthCodeUsed) {
		Constant.isAuthCodeUsed = isAuthCodeUsed;
	}
	public static void setAuthCodeValue(String authCodeValue) {
		Constant.authCodeValue = authCodeValue;
	}
    public static String getNC_PAY_URL() {
		return NC_PAY_URL;
	}
	public static void setNC_PAY_URL(String nC_PAY_URL) {
		NC_PAY_URL = nC_PAY_URL;
	}
	public static String getAuthCode(){
    	String authCode =null;
    	if( StringUtils.equals("1", isAuthCodeUsed) ){
    		authCode=authCodeValue;
    	}
    	return authCode;
    }

	public static String getIOS_PUSH_KEY() {
		return IOS_PUSH_KEY;
	}

	public static void setIOS_PUSH_KEY(String iOS_PUSH_KEY) {
		IOS_PUSH_KEY = iOS_PUSH_KEY;
	}

	public static String getIOS_PUSH_SERECTKET() {
		return IOS_PUSH_SERECTKET;
	}

	public static void setIOS_PUSH_SERECTKET(String iOS_PUSH_SERECTKET) {
		IOS_PUSH_SERECTKET = iOS_PUSH_SERECTKET;
	}

	public static String getIOS_PUSH_TYPE() {
		return IOS_PUSH_TYPE;
	}

	public static void setIOS_PUSH_TYPE(String iOS_PUSH_TYPE) {
		IOS_PUSH_TYPE = iOS_PUSH_TYPE;
	}

	public static Integer getGET_VCODE_FREE() {
		return GET_VCODE_FREE;
	}

	public static void setGET_VCODE_FREE(Integer gET_VCODE_FREE) {
		GET_VCODE_FREE = gET_VCODE_FREE;
	}

	public static Integer getGET_VCODE_MAX() {
		return GET_VCODE_MAX;
	}

	public static void setGET_VCODE_MAX(Integer gET_VCODE_MAX) {
		GET_VCODE_MAX = gET_VCODE_MAX;
	}

	public static Integer getGET_FORBIDDEN_TIME() {
		return GET_FORBIDDEN_TIME;
	}

	public static void setGET_FORBIDDEN_TIME(Integer gET_FORBIDDEN_TIME) {
		GET_FORBIDDEN_TIME = gET_FORBIDDEN_TIME;
	}

	public static String getANLIAN_PIAO_URL() {
		return ANLIAN_PIAO_URL;
	}

	public static void setANLIAN_PIAO_URL(String aNLIAN_PIAO_URL) {
		ANLIAN_PIAO_URL = aNLIAN_PIAO_URL;
	}

	public static String getANLIAN_USERNAME() {
		return ANLIAN_USERNAME;
	}

	public static void setANLIAN_USERNAME(String aNLIAN_USERNAME) {
		ANLIAN_USERNAME = aNLIAN_USERNAME;
	}

	public static String getANLIAN_PASSWORD() {
		return ANLIAN_PASSWORD;
	}

	public static void setANLIAN_PASSWORD(String aNLIAN_PASSWORD) {
		ANLIAN_PASSWORD = aNLIAN_PASSWORD;
	}

	public static String getSYSTEM_PUSH_STATUS() {
		return SYSTEM_PUSH_STATUS;
	}

	public static void setSYSTEM_PUSH_STATUS(String sYSTEM_PUSH_STATUS) {
		SYSTEM_PUSH_STATUS = sYSTEM_PUSH_STATUS;
	}

	public static String getSYSTEM_SHIPPER() {
		return SYSTEM_SHIPPER;
	}

	public static void setSYSTEM_SHIPPER(String sYSTEM_SHIPPER) {
		SYSTEM_SHIPPER = sYSTEM_SHIPPER;
	}

	public static String getJTB_PUT_URL() {
		return JTB_PUT_URL;
	}

	public static void setJTB_PUT_URL(String jTB_PUT_URL) {
		JTB_PUT_URL = jTB_PUT_URL;
	}

	public static String getWEIXIN_APPID() {
		return WEIXIN_APPID;
	}

	public static void setWEIXIN_APPID(String wEIXIN_APPID) {
		WEIXIN_APPID = wEIXIN_APPID;
	}

	public static String getWEIXIN_SECRET() {
		return WEIXIN_SECRET;
	}

	public static void setWEIXIN_SECRET(String wEIXIN_SECRET) {
		WEIXIN_SECRET = wEIXIN_SECRET;
	}
}
