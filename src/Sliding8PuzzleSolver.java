import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sliding8PuzzleSolver {

	public class State {
		private int heuristicValue;

		public State() {
			
		}

		// TODO return anything?
		private void makeBabies(long state) {
			/*
			 * 
			 * makes arrayList of babies goes through arraylist discardCopies
			 * evaluate on remaining put into priority queue
			 */

		}

		private void discardCopies(long state, ArrayList visited) {

		}

		private void evaluate(long state, long goalState) {
			/*
			 * 
			 * manhattan distances subtract from goalstate
			 */
		}
	}

	public static void main(String args[]) {
		
		Sliding8PuzzleSolver puzzle = new Sliding8PuzzleSolver();
		State goalState = puzzle.new State();

		// prompt the user to enter their name
		System.out.print("Enter start state: ");

		// open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String startState = null;

		// read the username from the command-line; need to use try/catch with
		// the readLine() method
		try {
			startState = br.readLine();
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your name!");
			System.exit(1);
		}
		long startStateInt = new Long(startState);
		if (startStateInt < 12345678 || startStateInt > 876543210) {
			throw new IllegalArgumentException();
		}

		System.out.println(startState);

		/*
		 * 
		 * startState -> makeBabies loop on priorityQueue makeBabies on each
		 * check goal
		 */
	}
}