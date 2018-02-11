package zad1;

public class Consumer implements Runnable
{
	Buffer buffer;

	public Consumer(Buffer buffer)
	{
		this.buffer = buffer;
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				int otrzymanaLiczba = buffer.get();
				System.out.println("Wyciagnalem liczbe " + otrzymanaLiczba);
				Thread.sleep((long) (Math.random() * 2001));
			} catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}

	}
}
