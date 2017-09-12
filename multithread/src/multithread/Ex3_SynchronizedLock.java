package multithread;

//Using locks is another method for makign sure that only one thread runs a method or block
//In this example there are two counters and two threads
//First thread increments first and second counter, and second thread does the same.
//If we don't use locks both of the counters cannot make it to 2000 after  1000+1000 incremented loops.

public class Ex3_SynchronizedLock {
	private static int count1 = 0;
	private static int count2 = 0;

	//We use two diffrent locks for threads can run concurrently
	//If we had one lock, threads had to be wait for each other to run the blocks one by one.
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	public static void add1() {
		// Only one thread can run this block at the same time
		synchronized (lock1) {
			count1++;
		}
	}

	public static void add2() {
		// Only one thread can run this block at the same time
		synchronized (lock2) {
			count2++;
		}
	}

	public static void compute() {
		for (int i = 0; i < 1000; i++) {
			add1();
			add2();
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				compute();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				compute();
			}
		});

		// Two threads adds 1000 to the counter seperately, but thanks to our locks they
		// never conflict.
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count1 + " - " + count2);

	}

}
