package com.miller.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {
	
	private Enhancer enhancer = new Enhancer();
	
	public Object getProxy(Class clazz) {
		// 设置创建子类的类
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(new CglibProxy());
		return enhancer.create();
	}

	/**
	 * 拦截所有目标类方法的调用
	 * obj 目标类的实例
	 * method 目标方法的反射对象
	 * args 方法的参数
	 * proxy 代理类的实例
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("日志开始");
		//代理类调用父类的方法
		methodProxy.invokeSuper(obj, args);
		
		return null;
	}

}
