package multithread;


//generally the total wont be 2000, because of the loops are incrementing same value some times.
//to prevent this we will use synchronized method
public class Sync {

	private static int counter=0;
	
	public static void process() {
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=0;i<1000;i++) {
					counter++;
				}
				
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=0;i<1000;i++) {
					counter++;
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
