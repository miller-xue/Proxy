package com.miller.myProxy;

import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {

	/**
	 * 被代理类对象
	 */
	private Object target;
	
	
	public TimeHandler(Object target) {
		super();
		this.target = target;
	}


	@Override
	public void invoke(Object obj, Method method) {
		try {
			long startTime = System.currentTimeMillis();
			System.out.println("汽车开始行驶........");
			
			method.invoke(target);

			long endTime = System.currentTimeMillis();
			System.out.println("汽车结束行驶....... 汽车行驶时间:" + (endTime - startTime) + " 毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
