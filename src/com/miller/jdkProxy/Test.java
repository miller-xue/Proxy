package com.miller.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.miller.proxy.Car;
import com.miller.proxy.Moveable;

public class Test {
	public static void main(String[] args) {
		final Car car = new Car();
		Class<? extends Car> cls = car.getClass();
		/**
		 * loader �������
		 * interfaces ʵ�ֽӿ�
		 * 
		 * InvocationHandle
		 * 
		 * 
		 * ��̬����ʵ��˼·
		 * 
		 * ʵ�ֹ���,ͨ��Proxy��newProxyInstance���ش������
		 * 1.����һ��Դ��(��̬���ɴ�����)
		 * 2.����Դ��( JDK Compiler API),�����µ���(������)
		 * 3.�������load���ڴ浱��,����һ���µĶ���(�������)
		 * 4.�����������	
		 */ 
//		Moveable m = (Moveable)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new TimeHandle(car));
//		m.move();
//		
		
		Moveable m1 = (Moveable)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("��־");
				if(method.getName().equalsIgnoreCase("move")) {
					System.out.println("�Ҿ��ǲ��ƶ�");
				}else {
					method.invoke(car);
				}
				return null;
			}
		});
		m1.move();
	}
}
