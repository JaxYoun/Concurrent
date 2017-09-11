package com.messagebetweenthread;

/**
 * 本示例解决了数据一致性问题，但是没有保证顺序
 * @author youn
 *
 */
public class MessageBetweenThread {

	public static void main(String[] args) {
		Student s = new Student();
		
		for(int i = 0; i < 1000; i++){
			new Thread(new Input(s, i % 2 == 0)).start();
			new Thread(new Output(s)).start();
		}
	}

}

class Input implements Runnable{

	public Student student;
	public boolean bool;
	
	public Input(Student s, boolean boo){
		this.student = s;
		this.bool = boo;
	}
	
	public void run() {
		synchronized (student) {
			if(bool){
				student.setName("中文");
				student.setSex("男");
			}else{
				student.setName("English");
				student.setSex("girl");
			}
		}
	}
	
}

class Output implements Runnable{
	
	public Student student;
	public Output(Student s){
		this.student = s;
	}
	
	public void run() {
		synchronized(student){
			System.out.println(student.name +  student.sex);
		}
	}
	
}

class Student {
	
	public String name;
	public String sex;
	
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
	
}