package exercise3;

public class TimedTask implements Runnable {
	public final static int MAX_SLEEP_TIME = 1000;
	int sleepTime;
	
	public TimedTask(int time){
		sleepTime = (time > MAX_SLEEP_TIME) ? MAX_SLEEP_TIME : time;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
