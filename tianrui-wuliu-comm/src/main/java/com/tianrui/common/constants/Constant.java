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
    
    //运单类型
    //普通运单
    public final static String BILL_TYPE_0 = "0";
    //熟车运单
    public final static String BILL_TYPE_1 = "1";
    //批量运单
    public final static String BILL_TYPE_2 = "2";
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
    
    
}
