package zad1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer
{
	Lock lock = new ReentrantLock();
	Condition full = lock.newCondition();
	Condition empty = lock.newCondition();
	Integer[] tab = new Integer[5];
	int aktualnyPut = 0, aktualnyGet = 0, liczbaElementow = 0;

	public void put(int wylosowanaLiczba) throws InterruptedException
	{
		lock.lock();
		try
		{
			while (liczbaElementow == tab.length)
			{
				System.out.println("Nie moge dodać liczby bo bufor jest zapełniony");
				full.await();
			}
			tab[aktualnyPut] = wylosowanaLiczba;
			aktualnyPut++;
			if (aktualnyPut == tab.length)
				aktualnyPut = 0;
			liczbaElementow++;
			System.out.println("Dodałem nowa liczbe ta liczba to " + wylosowanaLiczba); 
			empty.signal();
		} finally
		{
			lock.unlock();
		}
	}

	public int get() throws InterruptedException
	{
		lock.lock();
		try
		{
			while (liczbaElementow == 0)
			{
				System.out.println("Nie mogę wyciagnac liczby ponieważ bufor jest pusty ");
				empty.await();
			}
			int tmp = tab[aktualnyGet];
			aktualnyGet++;
			if (aktualnyGet == tab.length)
				aktualnyGet = 0;
			liczbaElementow--;
			full.signal();
			return tmp;
		} finally
		{
			lock.unlock();
		}
		// TODO Auto-generated method stub

	}

}
