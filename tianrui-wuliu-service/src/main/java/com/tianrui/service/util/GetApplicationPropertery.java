package com.tianrui.service.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @类描述：获得application.properties
 * 此类采用静态方法完成对属性文件的读取
 * @创建人：tank
 * @创建时间：2016年1月17日下午5:06:41
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午5:06:41
 * @修改备注：
 *
 */
public class GetApplicationPropertery {
    private static Properties props = null;

    static{
        try {
           	props = PropertiesLoader.loadProperties("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 从application.properties中获取值
     * @param key
     * @return
     */
    public static String getValueByKey(String key) {
        String value = props.getProperty(key);
        return value;
    }
}
