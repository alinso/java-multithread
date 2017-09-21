package multithread;

//By using future and callable we can track the return values of parallel threads.



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


//The Callable interface is similar to Runnable, in that both are designed for classes whose instances are potentially 
//executed by another thread. A Runnable, however, does not return a result and cannot throw a checked exception.
class ProcessorFuture implements Callable{

	private int id;
	
	public ProcessorFuture(int id) {
		this.id = id;
	}
	
	@Override
	public Object call() throws Exception {
		Thread.sleep(1000);
		return "ID"+id;
	}
	
}


public class Ex9_CallableAndFuture {
	
	
	public static void main(String[] args) {
		List<Future<String>> list = new ArrayList<>();
		
		//ExecutorService is easy way to create thrads
		ExecutorService executorService  = Executors.newFixedThreadPool(2);

		for(int i=0;i<5;i++) {
			Future<String> future = executorService.submit(new ProcessorFuture(i+1));
			list.add(future);
		}

		for(Future<String> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		
		//At the end of the threads, we call this method.
		//It means no new threads will be accepted any more, but the running threads won't be interrupted too.
		executorService.shutdown();
	
	}
}