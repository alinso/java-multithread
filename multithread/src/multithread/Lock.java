package multithread;

public class Lock {
	private static int count1=0;
	private static int count2 = 0;
	
	private static Object lock1= new Object();
	private static Object lock2 = new Object();
	
	
	public static void add1() {
		synchronized (lock1) {
			count1++;
		}
			
		
	
	}
	
	public static void add2() {
		synchronized (lock1) {
		count2++;
		}
		
	}
	
	public static void compute() {
		for(int i=0;i<1000;i++) {
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
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(count1+ " - "+count2);
		
	}
	
}
