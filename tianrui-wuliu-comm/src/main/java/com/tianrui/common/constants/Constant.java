package com.tianrui.common.constants;

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
    
    public final static String FILE_URL_PRE="http://www.trwl.com/img/";
    
    public final static String apiAuthKey ="!&@#2016#";
    
    
    //redis 缓存的前缀
    //public final static String PRE_REDIS ="wl2_";
    public final static String PRE_REDIS ="wl_";
    
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
    
    
}
