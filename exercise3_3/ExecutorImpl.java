package exercise3_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

public class ExecutorImpl implements Executor {
	Queue<Runnable> pendingTasks;
	Thread[] threads;
	int numTasks, nThreads;
	boolean running;
	
	public ExecutorImpl(int nThreads){
		pendingTasks = new LinkedList<Runnable>();
		threads = new Thread[nThreads];
		this.nThreads = nThreads;
		numTasks = 0;
		running = false;
	}
	
	@Override
	public void execute(Runnable command) {
		pendingTasks.add(command);
		numTasks++;
		if (!running) runTasks();
	}
	
	private void runTasks(){
		running = true;
		while (numTasks > 0){
			for (int i = 0; i < nThreads; i++){
				if (threads[i] == null){
					sendNextTaskToExecute(i);
				} else if (!threads[i].isAlive()){
					sendNextTaskToExecute(i);
				}
			}
		}
		running = false;
	}
	
	private void sendNextTaskToExecute(int index){
		if (!pendingTasks.isEmpty()){
			Thread t = new Thread(pendingTasks.poll());
			threads[index] = t;
			t.start();
			numTasks--;
		}
	}
	
	public int getNumThreadsRunning(){
		int numRunning = 0;
		for (int i = 0; i < nThreads; i++){
			if (threads[i] != null){
				if (threads[i].isAlive()) numRunning++;
			}
		}
		return numRunning;
	}
	
	public int getMaxPendingTime(){
		return getNumThreadsRunning() * TimedTask.MAX_SLEEP_TIME;
	}
}
