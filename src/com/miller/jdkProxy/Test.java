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
		 * loader 类加载器
		 * interfaces 实现接口
		 * 
		 * InvocationHandle
		 * 
		 * 
		 * 动态代理实现思路
		 * 
		 * 实现功能,通过Proxy的newProxyInstance返回代理对象
		 * 1.声明一段源码(动态生成代理类)
		 * 2.编译源码( JDK Compiler API),产生新的类(代理类)
		 * 3.将这个类load到内存当中,产生一个新的对象(代理对象)
		 * 4.返回这个对象	
		 */ 
//		Moveable m = (Moveable)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new TimeHandle(car));
//		m.move();
//		
		
		Moveable m1 = (Moveable)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("日志");
				if(method.getName().equalsIgnoreCase("move")) {
					System.out.println("我就是不移动");
				}else {
					method.invoke(car);
				}
				return null;
			}
		});
		m1.move();
	}
}
