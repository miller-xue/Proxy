package com.miller.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {
	
	private Enhancer enhancer = new Enhancer();
	
	public Object getProxy(Class clazz) {
		// ���ô����������
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(new CglibProxy());
		return enhancer.create();
	}

	/**
	 * ��������Ŀ���෽���ĵ���
	 * obj Ŀ�����ʵ��
	 * method Ŀ�귽���ķ������
	 * args �����Ĳ���
	 * proxy �������ʵ��
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("��־��ʼ");
		//��������ø���ķ���
		methodProxy.invokeSuper(obj, args);
		
		return null;
	}

}
