package multithread;

 class Worker implements Runnable{
	volatile Boolean  isTerminated =true;
	@Override
	public void run() {
		while(!isTerminated) {
			System.out.println("Worker works...");
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	public Boolean getIsTerminated() {
		return isTerminated;
	}
	public void setIsTerminated(Boolean isTerminated) {
		this.isTerminated = isTerminated;
	}
	
}

public class Volatile {

	
	public static void main(String[] args) {
		Worker worker = new Worker();
		Thread t1 = new Thread(worker);
		t1.run();
		
		
	//	worker.setIsTerminated(true);
		System.out.println("finished");
	}

}
