package com.innerclass;

public class MemberInnerClass {
	
	private int id;
	
	private void go(){
		System.out.println("out method");
	}
	
	public class Inner_1{
		public void doSth(){
			System.out.println(id);
		}
	}

	/**
	 * 要想在访问成员内部内的属性或方法，必须先实例化成员内部类对象然后方能通过外部类调用
	 */
	public void useInnerMethod(){
		Inner_1 inner_1 = new Inner_1();
		inner_1.doSth();
	}
	
	public static void main(String[] args) {
		MemberInnerClass out = new MemberInnerClass();
		out.go();
		out.useInnerMethod();
		
		MemberInnerClass.Inner_1 inner_1 = out.new Inner_1();
		inner_1.doSth();
				
	}
}
