package multithread;

//In this example two threads are working concurrently



//This a seperate thread from main
class Runner1 extends Thread{

	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("Runner1 dowloads an image... : "+i);
		}
		
	}
}



//This is another thread from main and Runner1, in main method these 3 threads will run concurrently.
class Runner2 extends Thread{

	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("Runner2 plays a song from youtube...: "+i);
		}
	}
}

public class RunnerAndThreadExample {

	public static void main(String[] args) {
		
		Runner1 t1 = new Runner1();
		Runner2 t2 = new Runner2();
		
		
		
		//After creating objects, we start the threads
		//When they are created, they just run seperately from each other
		t1.start();	
		t2.start();
		
		//Remember, the main thread is still running...
		for(int i=0;i<100;i++) {
			System.out.println("Main programs runs the computer...: "+i);
		}
		
		
		
		//By using join, we mak sure that program is waiting for these threads to finish
		//At this point main thread finished to count, and does not terminate program until other two finish their jobs.
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	

	}
}
