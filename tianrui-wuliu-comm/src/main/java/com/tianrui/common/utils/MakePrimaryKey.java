package com.tianrui.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @类描述：用于流水号等需求
 * 创建唯一字符串  规则 当前时间字符串+5位流水号 共18位
 * @创建人：tank
 * @创建时间：2016年1月17日下午5:07:35
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午5:07:35
 * @修改备注：
 *
 */
public class MakePrimaryKey {
    private static final int MAX_GENERATE_COUNT = 99999;
    private static int generateCount = 0;
    private static DecimalFormat df = new DecimalFormat("00000");
    private MakePrimaryKey() {
    }

    /**创建唯一字符串
     * @return 18位唯一字符串  13位时间+5位流水号
     */
    public static synchronized String getPrimaryKey() {
        generateCount++;
        if (generateCount > MAX_GENERATE_COUNT)
            generateCount = 1;
        String uniqueNumber = Long.toString(System.currentTimeMillis()) + df.format(generateCount);
        return uniqueNumber;
    }
    /**创建唯一字符串
     * @return 14位唯一字符串  8位时间+5位流水号 s开头标明是短信信息key
     */
    public static synchronized String getLocalCacheKey() {
        generateCount++;
        if (generateCount > MAX_GENERATE_COUNT)
            generateCount = 1;
        String uniqueNumber ="s"+ new SimpleDateFormat("MMddHHmm").format( new Date()) + df.format(generateCount);
        return uniqueNumber;
    }
    public static void main(String args[]){
        for (int i = 0; i < 500; i++) {
            String id = getLocalCacheKey();
            System.err.println(id);
            System.err.println(id.length());
        }
    }
    /**
	 * 
	 * @描述:判断手机合法性
	 * @param mobiles
	 * @return
	 * @返回类型 boolean
	 * @创建人 tank
	 * @创建时间 2016年1月29日下午3:03:46
	 */
    public static  boolean isMobileNO(String mobiles){  
		if(mobiles == null){
			return false;
		}
		Pattern p = Pattern.compile("^1[0-9]{10}$");  
		Matcher m = p.matcher(mobiles);  
		return m.matches();  
	} 
}
