package com.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(4);
		
		SenderThread sender = new SenderThread(semaphore);
		ReceiverThread receiver = new ReceiverThread(semaphore);
		
		new Thread(sender).start();
		new Thread(receiver).start();
	}

}



class SenderThread implements Runnable{

	private Semaphore semaphore;
	
	public SenderThread(Semaphore s){
		this.semaphore = s;
	}
	
	public void run() {
		while(true){
			try {
				System.out.println("发送！");
				this.semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class ReceiverThread implements Runnable{

	private Semaphore semaphore;
	
	public ReceiverThread(Semaphore s){
		this.semaphore = s;
	}
	
	public void run() {
		while(true){
			this.semaphore.release();
			System.out.println("接收");
		}
	}
	
}


