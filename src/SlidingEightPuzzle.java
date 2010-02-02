import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class SlidingEightPuzzle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// prompt the user to enter the start state
		System.out.print("Enter start state: ");

		// debug for timing heuristics
		//long startTime = System.currentTimeMillis();
		
		// open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String startStateInput = null;

		// get startState from user input
		try {
			startStateInput = br.readLine();
		} catch (IOException ioe) {
			System.out.println("IO error trying to read start state.");
			System.exit(1);
		}

		// Begin the search for the solution
		String goalString = "123456780";
		PriorityQueue<Tray> bestHeuristics = new PriorityQueue<Tray>();
		int queueCount = 0;
		ArrayList<String> alreadyChecked = new ArrayList<String>();
		Tray startTray = new Tray(startStateInput, goalString, new ArrayList<String>());
		boolean solutionFound = false;

		// Initialize for first loop through
		alreadyChecked.add(startTray.getLayout());
		startTray.makeBabies();
		Tray tray = startTray;

		// Continue to search until a solution is found
		while (!solutionFound) {
			// Generate Children and add them to the PriorityQueue
			ArrayList<String> babyList = tray.getBabies();

			for (int i = 0; i < tray.getBabies().size(); i++) {
				Tray nextTray = new Tray(babyList.get(i), goalString, new ArrayList<String>(tray.getSolutionPath()));
				if (!alreadyChecked.contains(nextTray.getLayout())) {
					bestHeuristics.add(nextTray);
					queueCount++;
				}
			}

			// Check if current tray is a solution
			if (tray.getLayout().equals(goalString)) {
				solutionFound = true;
			} else {
				// Add current Tray to the list of completed trays. Retrieve the
				// next Tray.
				alreadyChecked.add(tray.getLayout());
				tray = bestHeuristics.poll();
				tray.makeBabies();
			}
		}

		// Print solution
		Iterator<String> solutionIterator = tray.getSolutionPath().iterator();
		System.out.println("\n");
		while (solutionIterator.hasNext()) {
			char[] temp = solutionIterator.next().toCharArray();
			String stateString = "";
			for (int i = 0; i < 9; i++) {
				stateString += temp[i] + "\t";
				if ((i + 1) % 3 == 0) {
					stateString += "\n";
				}
			}
			System.out.println(stateString + "\n" + "-------------------\n");
		}
		System.out.println("\nSolution found in "
				+ (tray.getSolutionPath().size() - 1) + " moves.\nThe Priority Queue count was " + queueCount + ".\n");

		// debug for timing heuristics
		//long endTime = System.currentTimeMillis();
		//System.out.println("Time taken was " + (endTime - startTime) + " Milliseconds");
	}
}
