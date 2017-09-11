package com.withoutlock;

import java.util.concurrent.CountDownLatch;

public class Util {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5000);
		Actor actor = new Actor();
		for(int i = 0; i < 5000; i++){
			ThreadA a = new ThreadA(actor, latch);
			a.start();
			latch.countDown();
			System.err.println("+++++++++++++++++++++++++");
		}
	}

}
