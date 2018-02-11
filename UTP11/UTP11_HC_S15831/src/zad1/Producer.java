package zad1;

public class Producer implements Runnable 
{
	Buffer buffer;

	public Producer(Buffer buffer)
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
				int wylosowanaLiczba = (int)( Math.random()*3000);
				buffer.put(wylosowanaLiczba);
				long czasDoUspienia = (long)(Math.random()*2001);
				Thread.sleep(czasDoUspienia);
			} catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}
	}
}
