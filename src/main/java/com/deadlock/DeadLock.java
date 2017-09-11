package com.deadlock;

public class DeadLock {
	
	public static void main(String[] args) {
		
		new Thread(new MyThread(true)).start();
		new Thread(new MyThread(false)).start();
		
	}
}

class MyThread implements Runnable{
	
	private boolean bool;
	
	MyThread(boolean boo){
		this.bool = boo;
	}
	
	public void run() {
		if(bool){
			synchronized (MyLock.obj1) {
				System.err.println("拿到1锁！");
				synchronized (MyLock.obj2) {
					System.err.println("拿到2锁！");
				}
			}
		}else{
			synchronized (MyLock.obj2) {
				System.out.println("拿到2锁！");
				synchronized (MyLock.obj1) {
					System.out.println("拿到1锁！");
				}
			}
		}
	}
}

class MyLock{
	public static final Object obj1 = new Object();
	public static final Object obj2 = new Object();
}