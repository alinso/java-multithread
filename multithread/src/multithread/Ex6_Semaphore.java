package multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


//Semaphore decides how many threads can run the code at the same time.
enum Downloader{
	INSTANCE;
	
	//This smp oject allows only 3 threads to run this method concurrently
	private Semaphore smp  =new Semaphore(3,true);
	public void downloadData() {
		try {
			smp.acquire();
			download();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			smp.release();
		}
	}
	
	private void download() {
		System.out.println("downloading data.. ");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}





public class Ex6_Semaphore {

	public static void main(String[] args) {
		
		
		//ExecutorService create and run threads.
		//We can see in console that this code will work 3 by 3
		ExecutorService executorService =  Executors.newCachedThreadPool();
		for(int i=0;i<12;i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					Downloader.INSTANCE.downloadData();
					
				}
				
			});
		}
	}
}
