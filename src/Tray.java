import java.util.ArrayList;

//New, Cleaner Implementation of the State class
public class Tray implements Comparable<Tray> {
	private String layout; // String Representation of the board
	private String goal; // String Representation of the goal
	private Integer heuristic; // Value used for sorting Priority
	private ArrayList<String> babyCarriage; // Stores Babies' Strings
	private ArrayList<String> solutionPath; // Stores Parents & earlier

	/**
	 * Creates a representation of the current game board.
	 * 
	 * @param layout
	 *            String representation of the game board. Tiles are 1-8, and
	 *            the empty space is 0.
	 * @param goal
	 *            String representation of the game board when solved. Tiles are
	 *            1-8, and the empty space is 0.
	 * @param parent
	 *            ArrayList containing the string layouts of all prior states
	 *            visited in direct ancestry to this one.
	 */
	public Tray(String layout, String goal, ArrayList<String> parent) {
		this.layout = layout;
		this.goal = goal;
		this.solutionPath = parent;
		this.solutionPath.add(this.layout);
		this.babyCarriage = new ArrayList<String>();

		this.evaluateHeuristic();
	}

	private void evaluateHeuristic() {
		// Add path length to this state.
		this.heuristic = this.solutionPath.size();

		// Place calls to various heuristics. Comment out the ones not used.
		this.manhattanDistance(); // Relatively fast, gives near-optimal solution
		// this.breadthFirst(); //VERY slow, but gives optimal solution every time
		// this.subtractLayouts(); //Fast, but gives long solutions
		// this.outOfPlaceTiles();

	}

	private void outOfPlaceTiles() {
		char[] goalChars = this.goal.toCharArray();
		char[] layoutChars = this.layout.toCharArray();

		for (int i = 0; i < layoutChars.length; i++) {
			if (goalChars[i] == layoutChars[i]) {
				this.heuristic--;
			}
		}

	}

	private void subtractLayouts() {
		this.heuristic = this.heuristic
				+ Math.abs(new Integer(this.layout) - new Integer(this.goal));
	}

	private void breadthFirst() {
		// Does Nothing - Heuristic is based on length of path to this case,
		// which is already accounted for in evaluateHeuristic()
	}

	/**
	 * Calculates the Heuristic according to the Manhattan Distance criteria.
	 * Written By: Kevin Risden Modified By: Ryan Matthys
	 */
	private void manhattanDistance() {
		char[] goalStateChar = this.goal.toCharArray();

		for (int i = 0; i < 9; i++) {
			int tempGoalVar = this.layout.indexOf(goalStateChar[i]);
			int row = tempGoalVar / 3;
			int col = tempGoalVar % 3;
			int goalRow = (i / 3);
			int goalCol = i % 3;
			this.heuristic += Math.abs(goalCol - col) + Math.abs(goalRow - row);
		}
	}

	@Override
	/*
	 * * Compares Heuristic values, intended for use with the Priority Queue
	 */
	public int compareTo(Tray tray) {
		return this.heuristic.compareTo(tray.heuristic);
	}

	/**
	 * Creates the children of this state. A state may have 1, 2, 3, or 4
	 * children, depending upon its layout. Uses helper methods
	 * make[direction]Baby.
	 */
	public ArrayList<String> makeBabies() {
		int zeroLocation = layout.indexOf("0");
		if (zeroLocation % 3 != 0) {
			this.makeLeftBaby();
		}
		if ((zeroLocation) % 3 != 2) {
			this.makeRightBaby();
		}
		if (zeroLocation > 2) {
			this.makeTopBaby();
		}
		if (zeroLocation < 6) {
			this.makeBottomBaby();
		}

		if (this.babyCarriage.isEmpty()) {
			System.out.println("Empty Carriage!");
		}
		return this.babyCarriage;
	}

	private void makeBottomBaby() {
		char[] babyString = this.layout.toCharArray();
		int zeroLocation = this.layout.indexOf("0");
		char temp = this.layout.charAt(zeroLocation + 3);
		char zero = this.layout.charAt(zeroLocation);

		babyString[zeroLocation] = temp;
		babyString[zeroLocation + 3] = zero;

		this.babyCarriage.add(new String(babyString));
	}

	private void makeTopBaby() {
		char[] babyString = this.layout.toCharArray();
		int zeroLocation = this.layout.indexOf("0");
		char temp = this.layout.charAt(zeroLocation - 3);
		char zero = this.layout.charAt(zeroLocation);

		babyString[zeroLocation] = temp;
		babyString[zeroLocation - 3] = zero;

		this.babyCarriage.add(new String(babyString));
	}

	private void makeRightBaby() {
		char[] babyString = this.layout.toCharArray();
		int zeroLocation = this.layout.indexOf("0");
		char temp = this.layout.charAt(zeroLocation + 1);
		char zero = this.layout.charAt(zeroLocation);

		babyString[zeroLocation] = temp;
		babyString[zeroLocation + 1] = zero;

		this.babyCarriage.add(new String(babyString));
	}

	private void makeLeftBaby() {
		char[] babyString = this.layout.toCharArray();
		int zeroLocation = this.layout.indexOf("0");
		char temp = this.layout.charAt(zeroLocation - 1);
		char zero = this.layout.charAt(zeroLocation);

		babyString[zeroLocation] = temp;
		babyString[zeroLocation - 1] = zero;

		this.babyCarriage.add(new String(babyString));
	}

	public ArrayList<String> getBabies() {
		return this.babyCarriage;
	}

	public ArrayList<String> getSolutionPath() {
		return this.solutionPath;
	}

	public String getLayout() {
		return this.layout;
	}
}
