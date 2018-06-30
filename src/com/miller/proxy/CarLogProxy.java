package com.miller.proxy;

public class CarLogProxy implements Moveable {

	private Moveable m;
	
	
	public CarLogProxy(Moveable m) {
		super();
		this.m = m;
	}


	@Override
	public void move() {
		System.out.println("��־��ʼ........");
		
		m.move();
		
		System.out.println("��־����");
	}

}
