package exercise3_3;

public class Task implements Runnable{
	private int time;
	private boolean running;
	
	public Task(int time){
		this.time = time;
		running = true;
	}
	
	@Override
	public void run() {
		try {
		    Thread.sleep(time);
		    running = false;
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	public boolean isRunning(){
		return running;
	}
}
