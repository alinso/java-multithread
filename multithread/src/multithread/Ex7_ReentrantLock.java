package multithread;
import java.util.concurrent.locks.*;



//Reentrant locks are similar to synchronized blocks
//By locking and unlocking the lock, we manage the synchronization of code block between threads.
//If we remove the lock.lock() and lock.unlock() methods we will see that the counter cannot hit 2000.
public class Ex7_ReentrantLock {

	private static int counter = 0;
	private static ReentrantLock lock  = new ReentrantLock();
	
	public static void increment() {
		
		//After this line no other thread can enter the following seciton
		lock.lock();
		try {
		for(int i=0;i<1000;i++) {
		counter++;
		}
		}
		finally {
			//After this line the last lock is released so the restriction is over.
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
			e.printStackTrace();
		}
		
		  System.out.println("counter:"+counter);
		
	}
}
