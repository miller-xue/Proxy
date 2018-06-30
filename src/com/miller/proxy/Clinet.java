package com.miller.proxy;

/**
 * @author Miller
 * 测试类
 */
public class Clinet {

	public static void main(String[] args) {
//		Car car = new Car();
//		car.move();
		// 使用继承的方式完成代理
//		Car2 car = new Car2();
//		car.move();
		// 使用聚合的方法完成代理
//		CarTimeProxy car3 = new CarTimeProxy(new Car());
//		car3.move();
		
		Car car = new Car();
		CarTimeProxy car3 = new CarTimeProxy(car);
		CarLogProxy logCar = new CarLogProxy(car3);
		logCar.move();
	}
}
