package com.tianrui.api.util;

import java.math.BigDecimal;

/**
 * 
 * @类描述：设置数据格式
 * @创建人：lsj
 * @创建时间：2016年10月19日上午10:54:00
 *
 * @修改人：lsj
 * @修改时间：2016年10月19日上午10:54:00
 * @修改备注：
 *
 */
public class DateTypeUtil {
	/** 设置double类型格式*/
	public static Double doubleType(Double value){
		if(value == null){
			value = (double) 0;;
		}else{
			BigDecimal b = new BigDecimal(value); 
			value = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		}
		return value;
	}
}
