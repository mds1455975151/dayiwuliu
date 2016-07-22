package com.tianrui.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.TypeReference;
import com.tianrui.common.vo.AppParam;

public class TypeReferenceUtil {
	
	public static Map<String,Type> map=new HashMap<String,Type>();
	
	public static <T> Type getTypeReference(Class<T> clazz){
		if(!map.containsKey(clazz.toString())){
			Type type =type(AppParam.class,clazz.getTypeParameters());
			map.put(clazz.toString(), type(AppParam.class,clazz));
		}
		return map.get(clazz.toString());
	}
	
	static ParameterizedType type(final Class<?> raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
