package com.miller.proxy;

/**
 * @author Miller
 * ������
 */
public class Clinet {

	public static void main(String[] args) {
//		Car car = new Car();
//		car.move();
		// ʹ�ü̳еķ�ʽ��ɴ���
//		Car2 car = new Car2();
//		car.move();
		// ʹ�þۺϵķ�����ɴ���
//		CarTimeProxy car3 = new CarTimeProxy(new Car());
//		car3.move();
		
		Car car = new Car();
		CarTimeProxy car3 = new CarTimeProxy(car);
		CarLogProxy logCar = new CarLogProxy(car3);
		logCar.move();
	}
}
