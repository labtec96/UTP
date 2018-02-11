package kolejki;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class KolejkiMain
{

	public static void main(String[] args) throws InterruptedException
	{
		Buffer b = new Buffer();
		Producer producer = new Producer(b);
		Consumer consumer = new Consumer(b);
		ExecutorService pool;
		pool = Executors.newFixedThreadPool(2);
		pool.execute(producer);
		pool.execute(consumer);
		Thread.sleep(15000);
		pool.shutdownNow();
		System.exit(0);
	}

}
