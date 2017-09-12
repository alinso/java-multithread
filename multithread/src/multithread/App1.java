package multithread;

class Runner1 extends Thread{

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println("Runner1 :"+i);
		}
		
	}
}

class Runner2 extends Thread{

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println("Runner2 :"+i);
		}
	}
}

public class App1 {

	public static void main(String[] args) {
		Runner1 t1 = new Runner1();
		Runner2 t2 = new Runner2();
		
		t1.start();
	
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
		for(int i=0;i<30;i++) {
			System.out.println("main"+i);
		}
		
	

	}
}
