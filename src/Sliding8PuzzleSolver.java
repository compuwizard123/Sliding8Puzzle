import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sliding8PuzzleSolver {

	public class State {
		private int heuristicValue;
		private String state;
		
		public State(String state) {
			this.state = state;
			heuristicValue = 0;
		}
		
		public State(String state, State goalState) {
			this.state = state;
			heuristicValue = evaluate(goalState);
		}

		// TODO return anything?
		private void makeBabies() {
			/*
			 * 
			 * makes arrayList of babies goes through arraylist discardCopies
			 * evaluate on remaining put into priority queue
			 */

		}

		private void discardCopies(ArrayList visited) {

		}

		private int evaluate(State goalState) {
			return manhattanDistanceHeuristic(goalState);
			//return subtractHeuristic(goalState);
		}
		
		private int manhattanDistanceHeuristic(State goalState) {
			char[] temp = this.state.toCharArray();
			char[] goalStateChar = goalState.getState().toCharArray();
			
			return 0;
		}
		
		private int subtractHeuristic(State goalState) {
			return 0;
		}
		
		public String toString() {
			char[] temp = this.state.toCharArray();
			String stateString = "";
			for(int i = 0; i < 9; i++) {
				stateString += temp[i] + "\t";
				if((i+1)%3 == 0) {
					stateString += "\n";
				}
			}
			stateString += "Heuristic Value - " + this.heuristicValue + "\n";
			return stateString;
		}
		
		public String getState() {
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

		String startStateInput = null;

		// get startState from user input
		try {
			startStateInput = br.readLine();
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your name!");
			System.exit(1);
		}
		
		long startStateInt = new Long(startStateInput);
		if (startStateInt < 12345678 || startStateInt > 876543210 || startStateInput.toCharArray().length != 9) {
			throw new IllegalArgumentException("Illegal Start State");
		}
		State startState = puzzle.new State(startStateInput, goalState);
		
		System.out.println(startState.toString());
		System.out.println(goalState.toString());
		/*
		 * 
		 * startState -> makeBabies loop on priorityQueue makeBabies on each
		 * check goal
		 */
	}
}