package com.tianrui.service;

public class Test {

	public static void main(String[] args) {
		mkl();
		mkl();
		mkl();
		mkl();
		Method1();
		Method1();
		Method1();
		Method1();
	}
	
	public static void mkl(){
		System.out.println("132");
	}
	
	public synchronized static void Method1(){
		System.out.println("我是对象锁也是方法锁");
        try
        {
            Thread.sleep(1500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}
