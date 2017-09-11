package com.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyCache {

	public static Map<String, Object> map = new HashMap<String, Object>();
	public static ReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);
	
	public static Lock readLock = readWriteLock.readLock();
	public static Lock writeLock = readWriteLock.writeLock();
	
	public static Object getValue(String key){
		readLock.lock();
		Object obj = null;
		try {
			obj = map.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			readLock.unlock();
		}
		return obj;
	}
	
	public static final boolean putValue(String key, Object value){
		writeLock.lock();
		Object obj = null;
		try {
			obj = map.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			writeLock.unlock();
		}
		return obj == null ? false : true;
	}
	
	public static final boolean erase(){
		writeLock.lock();
		try {
			map.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			writeLock.unlock();
		}
		return map.size() <= 0 ? true : false;
	}
	
}
