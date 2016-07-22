package com.tianrui.common.exception;

/**
 * 
 * @类描述：自定义的应用异常类类，用于对异常信息的处理
 * @创建人：tank
 * @创建时间：2016年1月17日下午4:50:47
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午4:50:47
 * @修改备注：
 *
 */
public class ApplicationExectpion extends RuntimeException{
	
	private static final long serialVersionUID = 4330661996199117850L;

	public ApplicationExectpion() {
	}

	public ApplicationExectpion(String message) {
		super(message);
	}

	public ApplicationExectpion(Throwable cause) {
		super(cause);
	}

	public ApplicationExectpion(String message, Throwable cause) {
		super(message, cause);
	}
}
