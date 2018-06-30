package com.miller.myProxy;


import com.miller.proxy.Car;
import com.miller.proxy.Moveable;

public class Test {
	public static void main(String[] args) {
		try {
			InvocationHandler h = new TimeHandler(new Car());
			 Moveable m =  (Moveable)Proxy.newProxyInstance(Moveable.class,h);
			 m.move();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
