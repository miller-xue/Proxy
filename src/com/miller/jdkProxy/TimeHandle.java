package com.miller.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.script.Invocable;
import javax.script.ScriptException;

public class TimeHandle implements InvocationHandler {

	/**
	 * ������Ķ���
	 */
	private Object target;
	
	
	public TimeHandle(Object target) {
		super();
		this.target = target;
	}


	/**
	 * ������
	 * proxy ������Ķ���
	 * method ���������ķ���
	 * args �����Ĳ���
	 * ����ֵ
	 * 	object ��������ֵ
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long startTime = System.currentTimeMillis();
		System.out.println("������ʼ��ʻ........");
		
		method.invoke(target);
		
		long endTime = System.currentTimeMillis();
		System.out.println("����������ʻ....... ������ʻʱ��:" + (endTime - startTime) + " ����");
		System.out.println();	
		
		return null;
	}

}
