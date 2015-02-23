package exercise3_3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executor;

import exercise3.TextLoop;


public class ResponsiveUi  {
	private static final int NUM_THREADS = 25;
	private static final int NUM_USERS = 300;
	Executor ex = new ExecutorImpl(NUM_THREADS);
	Users users = new Users(NUM_USERS);
	
	public ResponsiveUi(){

	}
	
	public static void main(String[] args) {
		ResponsiveUi ui = new ResponsiveUi();
		ui.run();
		

		
	}
	
	private void run(){
		int numExecuted = 0;
		while (users.hasTasks()){
			ex.execute(users.getTask(((ExecutorImpl) ex).getMaxPendingTime()));
			numExecuted++;
		}
		System.out.println("Finished executing " + numExecuted + " tasks!");
	}

}