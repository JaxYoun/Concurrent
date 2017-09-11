package com.readwritelock;

public class WriterA extends Thread {

	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			System.err.println("写\t" + MyCache.putValue("" + i, new Integer(i)));
//			System.out.println("清除\t" + MyCache.erase());
		}
	}
	
}
