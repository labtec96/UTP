package kolejki;

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
				System.out.println("Wyciagnalem liczbe " + buffer.get());
				Thread.sleep((long) (Math.random() * 2001));
			} catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();;
			}
		}

	}
}
