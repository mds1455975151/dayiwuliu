package com.tianrui.web.smvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记需要进行权限有效性验证的方法
 * 1 实名认证
 * 2 货主认证
 * 3 司机认证
 * 4 车主认证
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthValidation {
	String autyType();
}
