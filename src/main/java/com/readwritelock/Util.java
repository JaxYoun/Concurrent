package com.readwritelock;

public class Util {

	public static void main(String[] args) {
		ReaderA readerA = new ReaderA();
		WriterA writerA = new WriterA();
		WriterA writerB = new WriterA();
		
		writerB.start();
		writerA.start();
		readerA.start();
	}

}
