import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Sliding8PuzzleSolver
{
	private PriorityQueue<State> unchecked = new PriorityQueue<State>();
	private ArrayList<State> alreadyFound = new ArrayList<State>();

	public class State implements Comparable<State>
	{
		private int heuristicValue;
		private String state;
		ArrayList<State> ancestry = new ArrayList<State>();

		//Used to make the goal state
		public State(String state)
		{
			this.state = state;
			this.heuristicValue = 0;
		}

		//Used to make the start state
		public State(String state, State goalState)
		{
			this.state = state;
			this.heuristicValue = evaluate(goalState);
		}

		//Used to calculate new states
		public State(String state, State goalState, ArrayList<State> parents)
		{
			this.state = state;
			this.heuristicValue = evaluate(goalState);
			this.ancestry = parents;
		}

		// TODO return anything?
		public ArrayList<State> makeBabies(State goalState)
		{
			char[] temp = this.state.toCharArray();
			int row = (int) Math.floor(this.state.indexOf("0") / 3);
			int col = this.state.indexOf("0") % 3;
			ArrayList<State> children = new ArrayList<State>();
			int count = 0;

			if ((row - 1) > -1)
			{
				count++;
				char[] temp1 = temp.clone();
				temp1[3 * row + col] = temp1[3 * (row - 1) + col];
				temp1[3 * (row - 1) + col] = '0';
				ancestry.add(this);
				children
						.add(new State(new String(temp1), goalState, ancestry));
				// System.out.println("Baby " + count + "\n" + new State(new
				// String(temp1), goalState, ancestery).toString());
			}
			if ((row + 1) < 3)
			{
				count++;
				char[] temp2 = temp.clone();
				temp2[3 * row + col] = temp2[3 * (row + 1) + col];
				temp2[3 * (row + 1) + col] = '0';
				ancestry.add(this);
				children
						.add(new State(new String(temp2), goalState, ancestry));
				// System.out.println("Baby " + count + "\n" + .toString());
			}
			if ((col - 1) > -1)
			{
				count++;
				char[] temp3 = temp.clone();
				temp3[3 * row + col] = temp3[3 * (row) + (col - 1)];
				temp3[3 * (row) + (col - 1)] = '0';
				ancestry.add(this);
				children
						.add(new State(new String(temp3), goalState, ancestry));
				// System.out.println("Baby " + count + "\n" + new State(new
				// String(temp3), goalState, ancestery).toString());
			}
			if ((col + 1) < 3)
			{
				count++;
				char[] temp4 = temp.clone();
				temp4[3 * row + col] = temp4[3 * (row) + (col + 1)];
				temp4[3 * (row) + (col + 1)] = '0';
				ancestry.add(this);
				children
						.add(new State(new String(temp4), goalState, ancestry));
			}
			Iterator<State> i = children.iterator();
			while (i.hasNext())
			{
				State tempState = i.next();
				if (tempState.discardCopies())
				{
					i.remove();
				} else
				{
					// Add to the List of states to check, and to the list of states to discard.
					unchecked.add(tempState);
				}
			}
			
			return children;

			/*
			 * 
			 * makes arrayList of babies goes through arraylist discardCopies
			 * evaluate on remaining put into priority queue
			 */

		}

		private boolean discardCopies()
		{
			Iterator<State> i = ancestry.iterator();
			while (i.hasNext())
			{
				if (this == i.next())
				{
					return false;
				}
			}
			return true;
		}

		private int evaluate(State goalState)
		{
//			return manhattanDistanceHeuristic(goalState);
			 return subtractHeuristic(goalState);
		}

		private int manhattanDistanceHeuristic(State goalState)
		{
			int heuristic = 0;

			char[] goalStateChar = goalState.getState().toCharArray();

			for (int i = 0; i < 9; i++)
			{
				int col = (int) Math
						.floor(this.state.indexOf(goalStateChar[i]) / 3);
				int row = this.state.indexOf(goalStateChar[i]) % 3;
				int goalCol = (int) Math.floor(i / 3);
				int goalRow = i % 3;
				heuristic += Math.abs(goalCol - col) + Math.abs(goalRow - row);
				// System.out.println(goalStateChar[i] + " - (" + goalCol + ","
				// + goalRow + ")" + " - " + "(" + col + "," + row + ")" + " - "
				// + (Math.abs(goalCol - col) + Math.abs(goalRow - row)));
			}
			return heuristic;
		}

		private int subtractHeuristic(State goalState)
		{
			int stateInt = new Integer(this.state);
			int goalStateInt = new Integer(goalState.getState());
			return Math.abs(goalStateInt - stateInt);
		}

		public String toString()
		{
			char[] temp = this.state.toCharArray();
			String stateString = "";
			for (int i = 0; i < 9; i++)
			{
				stateString += temp[i] + "\t";
				if ((i + 1) % 3 == 0)
				{
					stateString += "\n";
				}
			}
			stateString += "Heuristic Value - " + this.heuristicValue + "\n";
			return stateString;
		}

		public String getState()
		{
			return this.state;
		}

		public int getHeuristicValue()
		{
			return this.heuristicValue;
		}

		public ArrayList getAncestry()
		{
			return this.ancestry;
		}

	
		public int compareTo(State state)
		{
			Integer var1 = new Integer(state.heuristicValue);
			Integer var2 = new Integer(this.heuristicValue);
			return var1.compareTo(var2);
		}
	}

	public static void main(String args[])
	{

		Sliding8PuzzleSolver puzzle = new Sliding8PuzzleSolver();
		State goalState = puzzle.new State("123456780"); // hardcoded goal state

		// prompt the user to enter the start state
		System.out.print("Enter start state: ");

		// open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String startStateInput = null;

		// get startState from user input
		try
		{
			startStateInput = br.readLine();
		} catch (IOException ioe)
		{
			System.out.println("IO error trying to read your name!");
			System.exit(1);
		}

		long startStateInt = new Long(startStateInput);
		if (startStateInt < 12345678 || startStateInt > 876543210
				|| startStateInput.toCharArray().length != 9)
		{
			throw new IllegalArgumentException("Illegal Start State");
		}
		State startState = puzzle.new State(startStateInput, goalState);
		System.out.println("Start State \n" + startState.toString());
		puzzle.unchecked.add(startState);

		
		State finalState = null;
		// Start Solution Loop
		while (!puzzle.unchecked.isEmpty())
		{
			if (puzzle.unchecked.peek().getHeuristicValue() == 0)
			{
				finalState = puzzle.unchecked.poll();
				System.out.println("Should have Broken");
				break;

			}
			puzzle.unchecked.poll().makeBabies(goalState);
		}

		Iterator i = finalState.getAncestry().iterator();
		System.out.println("Solution Path");
		while (i.hasNext())
		{
			System.out.println(i.next().toString());
		}
		System.out.println("Goal State \n" + goalState.toString());
		/*
		 * 
		 * startState -> makeBabies loop on priorityQueue makeBabies on each
		 * check goal
		 */
	}
}