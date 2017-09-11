package com.messagebetweenthread;

/**
 * 本示例解决了数据一致性问题，同时也保证了顺序
 * @author youn
 *
 */
public class MessageBetweenThreadWait {

	public static void main(String[] args) {
		Student1 s = new Student1();
		s.setFull(false);
		
		for(int i = 0; i < 100; i++){
			new Thread(new Input1(s, i % 2 == 0)).start();
			new Thread(new Output1(s)).start();
		}
	}

}

class Input1 implements Runnable{

	public Student1 student;
	public boolean bool;
	
	public Input1(Student1 s, boolean boo){
		this.student = s;
		this.bool = boo;
	}
	
	public void run() {
		synchronized (student) {
			while(!student.isFull){
				if(bool){
					student.setName("中文");
					student.setSex("男");
				}else{
					student.setName("English");
					student.setSex("girl");
				}
				student.setFull(true);
				student.notify();
			}
		}
	}
	
}

class Output1 implements Runnable{
	
	public Student1 student;
	public Output1(Student1 s){
		this.student = s;
	}
	
	public void run() {
		synchronized(student){
			while(student.isFull){
				System.out.println(student.name +  student.sex);
				student.setFull(false);
				student.notify();
			}
		}
	}
	
}

class Student1 {
	
	public String name;
	public String sex;
	public boolean isFull;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public boolean isFull() {
		return isFull;
	}
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	
}