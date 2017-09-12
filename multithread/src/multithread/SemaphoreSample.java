package multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader{
	INSTANCE;
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

public class SemaphoreSample {

	public static void main(String[] args) {
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
