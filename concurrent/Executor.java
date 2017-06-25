package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class Executor {
	public <V> Future<V> submit(Task<V> t) {
		RunnableFuture<V> rFuture = new FutureTask<V>(new Callable<V>() {

			@Override
			public V call() throws Exception {
				
				return t.run();
			}
			
		});
		new Thread(rFuture).start();
		return rFuture;
	}
}
