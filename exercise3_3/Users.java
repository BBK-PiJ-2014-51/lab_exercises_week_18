package exercise3_3;

import java.util.LinkedList;
import java.util.Queue;
public class Users {

	private Queue<Runnable> tasks;
	private static final int MAX_WAIT = 5000;
	public Users(int numRequests){
		tasks = new LinkedList();
		while (numRequests > 0){
			tasks.add(new TimedTask(80));
			numRequests--;
		}
	}

	public boolean hasTasks() {
		return !tasks.isEmpty();
	}

	public Runnable getTask(int responseTime) {
		if (responseTime > MAX_WAIT){
			System.out.println("The system is down, I will come back later! Current wait time: " + responseTime);
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!tasks.isEmpty()){
			return tasks.poll();
		} else
			return null;
	}

}