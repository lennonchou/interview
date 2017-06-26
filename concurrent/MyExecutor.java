package concurrent;

import java.security.InvalidParameterException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class MyExecutor<V>{
	final private Executor executor;
//	final private ExecutorService executor;
	final private ConcurrentHashMap<Integer, Future<V>> map = new ConcurrentHashMap<>();

	
	public MyExecutor() {
//		executor = Executors.newFixedThreadPool(5);
		executor = new Executor();
	}
	
	public void addTask(int taskId, Task<V> task) {
		Future<V> newFT = executor.submit(task);
		if (map.containsKey(taskId)) {
			throw new InvalidParameterException("The task already exist");
		} else {
			map.put(taskId, newFT);
		}
	}
	
	public boolean checkIfDone(int taskId) throws InvalidParameterException{
		if (!map.containsKey(taskId)) {
			throw new InvalidParameterException("The task is not in this executor");
		}
		return map.get(taskId).isDone();
	}
	
	public V getResult(int taskId) throws InterruptedException, ExecutionException, InvalidParameterException{
		if (!map.containsKey(taskId)) {
			throw new InvalidParameterException("The task is not in this executor");
		}
		return map.get(taskId).get();
	}
	
	public static void main(String[] args) {
		MyExecutor<Object> myExecutor = new MyExecutor<>();
		String[] words = {"000", "001", "002", "003", "004"};
		for (int i = 0; i < words.length; i++) {
			if (i % 2 == 0) {
				final Integer taskId = new Integer(i);
				Task<Object> task = new Task<Object>() {

					@Override
					public Object run() {
						
						try {
							String threadName = Thread.currentThread().getName();
							TimeUnit.SECONDS.sleep(5 - taskId);
							System.out.println("This is task " + taskId + " running on " + threadName);
							
						} catch (InterruptedException e) {
							
							System.out.println("The task " + taskId + " is interrupted");
						}
						return null;
					}
					
				};
				myExecutor.addTask(taskId, task);
			} else {
				final Integer taskId = new Integer(i);
				final String word = words[i];
				Task<Object> task = new Task<Object>() {

					@Override
					public Object run() {
						
						try {
							String threadName = Thread.currentThread().getName();
							TimeUnit.SECONDS.sleep(5 - taskId);
							System.out.println("This is task " + taskId + " running on " + threadName);
						} catch (InterruptedException e) {
							
							System.out.println("The task " + taskId + " is interrupted");
						}
						return word.charAt(50);
					}
					
				};
				myExecutor.addTask(taskId, task);
			}
			
		}
		boolean status = myExecutor.checkIfDone(0);
		System.out.println(status);
		try {
			System.out.println(myExecutor.getResult(3));
			System.out.println(myExecutor.getResult(2));
			System.out.println(myExecutor.getResult(1));
			System.out.println(myExecutor.getResult(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
