package com.createway;

public class InitWayTest {
	
	public static void main(String[] args) {
		initExtendWay();
		initImplementWay();
		initAnonymousExtendWay();
		initanonymousImplementWay();
	}
	
	/**
	 * 方法1.实例化并启动继承式线程
	 */
	public static void initExtendWay(){
		ExtendWay extendWay = new ExtendWay();
		extendWay.start();
	}
	
	/**
	 * 方法2.实例化并启动实现式线程
	 */
	public static void initImplementWay(){
		ImplementWay implementWay = new ImplementWay();
		Thread thread = new Thread(implementWay, "实现式");
		System.out.println(thread.getName());
		thread.start();
	}
	
	/**
	 * 方法3.通过创建Thread的匿名子类来创建线程
	 */
	public static void initAnonymousExtendWay(){
		Thread thread = new Thread("匿名子类——继承"){
			public void run(){
				System.out.println("initAnonymousExtendWay");
			}
		};
		System.out.println(thread.getName());
		thread.start();
	}
	
	/**
	 * 方法4.通过创建Runnable的匿名子类来创建线程
	 */
	public static void initanonymousImplementWay(){
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println("initanonymousImplementWay");
			}
		};
		
		Thread thread = new Thread(runnable, "匿名子类——实现");
		System.out.println(thread.getName());
		thread.start();
	}
}

/**
 * 继承式线程类
 * @author youn
 *
 */
class ExtendWay extends Thread {
	@Override
	public void run(){
		System.err.println("ThreadWay");
	}
}

/**
 * 实现式线程类
 * @author youn
 *
 */
class ImplementWay implements Runnable {

	public void run() {
		System.err.println("ImplementWay");
	}
}
