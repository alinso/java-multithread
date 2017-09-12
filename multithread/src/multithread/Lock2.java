package multithread;

import java.util.concurrent.locks.*;

public class Lock2 {

	private static int counter = 0;
	private static ReentrantLock lock  = new ReentrantLock();
	
	public static void increment() {
		lock.lock();
		
		try {
		for(int i=0;i<1000;i++) {
		counter++;
		}
		}
		finally {
			lock.unlock();
		}
	}
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				increment();
			}
			
		});
		
		Thread t2= new Thread(new Runnable() {
			public void run() {
				increment();
			}
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  System.out.println("counter:"+counter);
		
	}
}
