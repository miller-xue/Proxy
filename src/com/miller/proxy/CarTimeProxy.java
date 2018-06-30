package com.miller.proxy;

public class CarTimeProxy implements Moveable {

	private Moveable m;
	
	
	public CarTimeProxy(Moveable m) {
		super();
		this.m = m;
	}


	@Override
	public void move() {
		long startTime = System.currentTimeMillis();
		System.out.println("������ʼ��ʻ........");
		
		m.move();
		
		long endTime = System.currentTimeMillis();
		System.out.println("����������ʻ....... ������ʻʱ��:" + (endTime - startTime) + " ����");
		System.out.println();		
	}

}
