package com.miller.proxy;

public class Car2 extends Car {

	@Override
	public void move() {
		long startTime = System.currentTimeMillis();
		System.out.println("������ʼ��ʻ........");
		
		super.move();
		
		long endTime = System.currentTimeMillis();
		System.out.println("����������ʻ....... ������ʻʱ��:" + (endTime - startTime) + " ����");
		System.out.println();
	}
}
