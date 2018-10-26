package Boggle;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
   Displays the current time once every second.
 */
public class GameTimer
{
	Timer t;
	int timeRemaining;
	protected final int DELAY = 1000; // milliseconds between timer ticks
	protected ActionListener listener;
	
	public GameTimer(int startTime) {
		timeRemaining = startTime;

		class CurrentTime implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				timeRemaining--;
				if(timeRemaining <= 0) 
				{
					t.stop();
				}
			}
		}
		listener = new CurrentTime();
		t = new Timer(DELAY, listener);
		
	}
	
	public int getTimeRemaining() {
		return timeRemaining;
	}
	public void start()
	{
		t.start();
	}
	public void stop()
	{
		t.stop();
	}

	
	
}