package com.miller.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.script.Invocable;
import javax.script.ScriptException;

public class TimeHandle implements InvocationHandler {

	/**
	 * 被代理的对象
	 */
	private Object target;
	
	
	public TimeHandle(Object target) {
		super();
		this.target = target;
	}


	/**
	 * 参数：
	 * proxy 被代理的对象
	 * method 被代理对象的方法
	 * args 方法的参数
	 * 返回值
	 * 	object 方法返回值
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long startTime = System.currentTimeMillis();
		System.out.println("汽车开始行驶........");
		
		method.invoke(target);
		
		long endTime = System.currentTimeMillis();
		System.out.println("汽车结束行驶....... 汽车行驶时间:" + (endTime - startTime) + " 毫秒");
		System.out.println();	
		
		return null;
	}

}
