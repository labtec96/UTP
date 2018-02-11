package kolejki;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer
{
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
	
	public void put(int wylosowanaLiczba) throws InterruptedException
	{
		queue.put(wylosowanaLiczba);
		System.out.println("Dodałem liczbe " + wylosowanaLiczba);
	}

	public int get() throws InterruptedException
	{
		int otrzymanaLiczba = queue.take();
		return otrzymanaLiczba;
	}

}
