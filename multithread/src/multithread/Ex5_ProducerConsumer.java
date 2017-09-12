package multithread;

import java.util.ArrayList;
import java.util.List;

//This a synchronized wait and notify solution for classical producer-consumer problem

class Processor {
	private List<Integer> list = new ArrayList<>();
	private final int LIMIT = 50;
	private final int BOTTOM = 0;
	private final Object lock = new Object();
	private int value = 0;

	// This method produces up to LIMIT and when it reaches to LIMIT goes to wait
	// state
	// By the way consumer thread runs concurrently
	public void produce() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (list.size() == LIMIT) {
					System.out.println("waiting for the remove items on the list");
					lock.wait();
				} else {
					System.out.println("Adding: " + value);
					list.add(value);
					value++;
					lock.notify();
				}
				Thread.sleep(100);
			}

		}
	}

	// This method produces up to BOTTOM and when it reaches to BOTTOM goes to wait
	// state
	// By the way consumer thread runs concurrently
	public void consume() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (list.size() == BOTTOM) {
					System.out.println("waiting for the add items to the list");
					lock.wait();
				} else {
					System.out.println("removed : " + list.remove(--value));
					lock.notify();
				}
				Thread.sleep(100);
			}

		}

	}
}




public class Ex5_ProducerConsumer {

	public static void main(String[] args) {

		Processor processor = new Processor();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// These two threads increment and decrement counter same time however it never
		// reaches bottom and limit values.
		// BUT THANKS TO LOCK, THEY WAIT FOR EACH OTHER
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}