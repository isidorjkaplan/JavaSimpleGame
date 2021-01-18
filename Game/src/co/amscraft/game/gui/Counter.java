package co.amscraft.game.gui;

public class Counter
{

	private double time;
	private boolean on;

	public void startTimer(double intervale)
	{
		time = 0;
		on = true;
		new java.util.Timer().schedule(new java.util.TimerTask()
		{
			@Override
			public void run()
			{
				while (on)
				{
					try
					{
						Thread.sleep((long) (intervale * 1000));
					}
					catch (InterruptedException e)
					{
						// ignore
					}
					time = time + intervale;

				}
			}
		}, 1);

	}

	public void stopTimer()
	{
		on = false;
	}

	public double getTime()
	{
		return time;
	}
}
