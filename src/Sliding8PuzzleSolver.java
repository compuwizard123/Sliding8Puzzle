import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sliding8PuzzleSolver {

	public class State {
		private int heuristicValue;
		private String state;

		public State() {
			
		}
		
		public State(String state) {
			this.state = state;
		}
		
		public State(String state, State goalState) {
			this.state = state;
			heuristicValue = evaluate(this.state, goalState);
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

		private int evaluate(String state, State goalState) {
			/*
			 * 
			 * manhattan distances subtract from goalstate
			 */
			
			return 0;
		}
		
		public String toString() {
			return this.state;
		}
	}

	public static void main(String args[]) {
		
		Sliding8PuzzleSolver puzzle = new Sliding8PuzzleSolver();
		State goalState = puzzle.new State("123456780"); // hardcoded goal state

		// prompt the user to enter the start state
		System.out.print("Enter start state: ");

		// open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String startState = null;

		// get startState from user input
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
		System.out.println(goalState.toString());
		/*
		 * 
		 * startState -> makeBabies loop on priorityQueue makeBabies on each
		 * check goal
		 */
	}
}