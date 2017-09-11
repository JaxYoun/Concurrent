package com.zookeeperlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ZookeeperLock implements Lock {

	private final String ZK_IP_PORT = "localhost:000";
	private final String LOCK_MODE = "localhost:000";
	
	private CountDownLatch latch ;
	
	private ZkClient zkClient = new ZkClient(ZK_IP_PORT);
	
	/**
	 * 
	 */
	private void waitForLock(){
		//创建节点监听
		IZkStateListener listener = new IZkStateListener(){

			public void handleNewSession() throws Exception {
				if(latch != null){
					latch.countDown();
				}
			}

			public void handleStateChanged(KeeperState arg0) throws Exception {
				
			}
			
		};
		
		zkClient.subscribeDataChanges(LOCK_MODE, (IZkDataListener) listener);
		if(zkClient.exists(LOCK_MODE)){
			latch = new CountDownLatch(1);
		}
		zkClient.unsubscribeChildChanges(LOCK_MODE, (IZkChildListener) listener);
	}
	
	/**
	 * 阻塞式
	 */
	public void lock() {
		// TODO Auto-generated method stub

	}

	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
	}

	/**
	 * 非阻塞式
	 */
	public boolean tryLock() {
		zkClient.createPersistent(LOCK_MODE);
		return false;
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	public void unlock() {
		zkClient.delete(LOCK_MODE);
		// TODO Auto-generated method stub

	}

	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
