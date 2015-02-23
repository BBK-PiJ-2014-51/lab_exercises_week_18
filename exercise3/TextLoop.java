package exercise3;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class TextLoop implements Runnable {

	public static final int COUNT = 10;
	private final String name;
	
	public TextLoop(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < COUNT; i++) {
			System.out.println("Loop:" + name + ", iteration:" + i + ".");
		}
	}
	
	public static void main(String args[]) {
		args = new String[1];
		args[0] = "1";
		if (args.length < 1 || (!args[0].equals("0") && !args[0].equals("1"))) {
			System.out.println("USAGE: java TextLoop <mode>");
			System.out.println(" mode 0: without threads"); //will iterate in order
			System.out.println(" mode 1: with threads"); //will appear random
		} else if (args[0].equals("0")) {
			for (int i = 0; i < 10; i++) {
				Runnable r = new TextLoop("Thread " + i);
				r.run();
			}
		} else {
			Executor ex = new ExecutorImpl(5);
			for (int i = 0; i < 10; i++) {
				Runnable r = new TextLoop("Thread " + i);
				ex.execute(r);
				//Thread t = new Thread(r);
				//t.start();
			}
		}
	}
}