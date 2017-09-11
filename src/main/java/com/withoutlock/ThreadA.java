package com.withoutlock;

import java.util.concurrent.CountDownLatch;

public class ThreadA extends Thread {

	private CountDownLatch latch;
	
	private Actor actor;

	public ThreadA(Actor act, CountDownLatch lat) {
		this.latch = lat;
		this.actor = act;
	}

	@Override
	public void run() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.actor.increase(1);
	}
}
