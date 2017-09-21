package multithread;
//Thanks to Executors class we don't need to create new Thread object for number of runnables.
//By extennding an executorService, we can run many threads easily.


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex8_Executor {

	
	public static void main(String[] args) {
		ExecutorService executorService  = Executors.newFixedThreadPool(5);
		for(int i=0;i<5;i++) {
			executorService.submit(new Worker());
		}
	}
	

}


class Worker implements Runnable {

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println(i);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
