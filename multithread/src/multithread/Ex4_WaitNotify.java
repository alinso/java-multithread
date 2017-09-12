package multithread;

//In this example we can see the messaging between runnig threads.


class ProduceConsume {


	public void produce() throws InterruptedException {
		//By wait() method, this synchronized block will stop and wait for a notify() from any other thread.
		synchronized (this) {
			System.out.println("Producer method stops here by wait()...");
			wait();
			System.out.println("->...Produces again, after notify() we came to wait state!");
		}
	}

	public void consume() throws InterruptedException {
		//By notify method, other running thread will start to runnig state from waiting state.
		synchronized(this) {
			Thread.sleep(2000);
			System.out.println("Consumer method, after notify() waiting thread will run->...");
			notify();
			Thread.sleep(2000);

		}
	}
}



public class Ex4_WaitNotify {
	public static void main(String[] args) throws InterruptedException{
		ProduceConsume pc = new ProduceConsume();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					pc.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run(){
				try {
					pc.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		
		
		//Threads run concurrently and message each other here.
		t1.start();
		t2.start();

		t1.join();
		t2.join();

	}
}
