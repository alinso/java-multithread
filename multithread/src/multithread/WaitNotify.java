package multithread;

class ProduceConsume {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("producer method");
			wait();
			System.out.println("produces again, after notify we came to wait state");
		}
	}

	public void consume() throws InterruptedException {
		synchronized(this) {
			Thread.sleep(2000);
			System.out.println("consumer method, after notify waiting thread will run");
			notify();
			Thread.sleep(2000);

		}
	}
}



public class WaitNotify {
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

		t1.start();
		t2.start();

		t1.join();
		t2.join();

	}
}
