package multithread;

//In this example we use synchronized keyword for a method, so at the same time only one thread can run this method

public class Ex2_Synchronizaton {

	private static int counter = 0;

	// If we remove synchronized keyword from  method, the last value of counter generally won't
	// be 2000, because two threads increments same value sometimes.
	// But by sycnchronizing the method we prevent this happening and the total will
	// be 2000.
	public static synchronized void incremetTheCounter() {
		counter++;
	}

	public static void process() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					incremetTheCounter();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					incremetTheCounter();
				}
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
	}

	public static void main(String[] args) {
		process();
		System.out.println(counter);
	}
}
