package com.miller.myProxy;

import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {

	private Object target;
	
	
	public TimeHandler(Object target) {
		super();
		this.target = target;
	}


	@Override
	public void invoke(Object obj, Method method) {
		try {
			long startTime = System.currentTimeMillis();
			System.out.println("������ʼ��ʻ........");
			
			method.invoke(target);

			long endTime = System.currentTimeMillis();
			System.out.println("����������ʻ....... ������ʻʱ��:" + (endTime - startTime) + " ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
