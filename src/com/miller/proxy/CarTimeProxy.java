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
		System.out.println("汽车开始行驶........");
		
		m.move();
		
		long endTime = System.currentTimeMillis();
		System.out.println("汽车结束行驶....... 汽车行驶时间:" + (endTime - startTime) + " 毫秒");
		System.out.println();		
	}

}
