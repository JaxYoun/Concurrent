package com.readwritelock;

public class ReaderA extends Thread {

	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("读\t" + MyCache.getValue("" + i));
		}
	}
	
}
