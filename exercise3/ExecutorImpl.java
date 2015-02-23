package exercise3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

public class ExecutorImpl implements Executor {
	Queue<Runnable> currentTasks, pendingTasks;
	Thread[] threads;
	int numTasks, nThreads;
	boolean running;
	
	public ExecutorImpl(int nThreads){
		currentTasks = new LinkedList<Runnable>();
		pendingTasks = new LinkedList<Runnable>();
		threads = new Thread[nThreads];
		this.nThreads = nThreads;
		numTasks = 0;
		running = false;
	}
	
	@Override
	public void execute(Runnable command) {
		if (numTasks > nThreads ){
			pendingTasks.add(command);
		} else {
			currentTasks.add(command);
		}
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
		if (!currentTasks.isEmpty()){
			Thread t = new Thread(currentTasks.poll());
			threads[index] = t;
			t.start();
			if (!pendingTasks.isEmpty())
				currentTasks.add(pendingTasks.poll());
			numTasks--;
		}
	}
}
