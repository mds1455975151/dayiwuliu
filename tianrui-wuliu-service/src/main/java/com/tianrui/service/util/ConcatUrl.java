package com.tianrui.service.util;

import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @类描述：拼接请求
 * @创建人：tank
 * @创建时间：2016年5月10日下午3:23:09
 *
 * @修改人：tank
 * @修改时间：2016年5月10日下午3:23:09
 * @修改备注：
 *
 */
public class ConcatUrl {
	/**
 	 * 
 	 * @描述: 链接url与参数
 	 * @param url
 	 * @param param
 	 * @return
 	 * @返回类型 String
 	 * @创建人 tank
 	 * @创建时间 2016年1月17日下午4:52:49
 	 */
    public static String concatUrl(String url, Map<String, String> param) {
        StringBuffer s = new StringBuffer();
        s.append(url);
        if (param != null && param.size() > 0) {
            Set<Entry<String, String>> set = param.entrySet();
            if (url.contains("?")) {// 已经有问号
                for (Entry<String, String> e : set) {
                    if(e.getValue()!=null && !"".equals(e.getValue())){
                        s.append("&");
                        s.append(e.getKey());
                        s.append("=");
                        s.append(e.getValue());
                    }
                }
            } else {
                Iterator<Entry<String, String>> it = set.iterator();
                Entry<String, String> en = it.next();
                s.append("?");
                s.append(en.getKey());
                s.append("=");
                s.append(en.getValue());
                if (it.hasNext()) {
                    for (int i = 1; i < param.size(); i++) {
                        Entry<String, String> e = it.next();
                        s.append("&");
                        s.append(e.getKey());
                        s.append("=");
                        s.append(e.getValue());
                    }
                }
            }
        }
        return s.toString();
    }

    /**
     * 
     * @描述:链接url与参数
     * @param url
     * @param paramKey
     * @param paramValue
     * @return
     * @返回类型 String
     * @创建人 tank
     * @创建时间 2016年1月17日下午4:53:40
     */
    public static String concatUrl(String url, String paramKey,
            String paramValue) {
        if(paramValue!=null && !"".equals(paramValue)){
            StringBuffer s = new StringBuffer();
            s.append(url);
            if (url.contains("?")) {// 已经有问号
                s.append("&");
                s.append(paramKey);
                s.append("=");
                s.append(paramValue);
            } else {
                s.append("?");
                s.append(paramKey);
                s.append("=");
                s.append(paramValue);
            }
            return s.toString();
        }else{
            return url;
        }
        
    }

    /**
     * 
     * @描述:链接url与参数
     * @param url
     * @param str
     * @return
     * @返回类型 String
     * @创建人 tank
     * @创建时间 2016年1月17日下午4:53:48
     */
    public static String concatUrl(String url, StringBuffer str) {
        StringBuffer s = new StringBuffer();
        s.append(url);
        s.append(str.toString());
        return s.toString();
    }

   /**
    * 
    * @描述:链接url与参数
    * @param url
    * @param str
    * @return
    * @返回类型 String
    * @创建人 tank
    * @创建时间 2016年1月17日下午4:53:57
    */
    public static String concatUrl(String url, String str) {
        StringBuffer s = new StringBuffer();
        s.append(url);
        s.append(str);
        return s.toString();
    }
}
