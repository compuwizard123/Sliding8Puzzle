import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;


public class Testing extends TestCase {
	
	public void testingMake2Babies(){
		Sliding8PuzzleSolver puzzle = new Sliding8PuzzleSolver();
		Sliding8PuzzleSolver.State goalState = puzzle.new State("123456780"); // hardcoded goal state
		
		String startStateInput = "012345678"; 
		
		Sliding8PuzzleSolver.State startState = puzzle.new State(startStateInput, goalState);
		assertEquals(startState.getState(), startStateInput);
		String[] temp = new String[2];
		temp[0] = "312045678";
		temp[1] = "102345678";
		
		ArrayList<Sliding8PuzzleSolver.State> temp2 = startState.makeBabies(goalState);
		Iterator<Sliding8PuzzleSolver.State> i = temp2.iterator();
		int k = 0;
		while(i.hasNext()) {
			assertEquals(i.next().getState(),temp[k]); 
			k++;
		}
		
	}
	
	public void testingMake3Babies(){
		Sliding8PuzzleSolver puzzle = new Sliding8PuzzleSolver();
		Sliding8PuzzleSolver.State goalState = puzzle.new State("123456780"); // hardcoded goal state
		
		String startStateInput = "102345678"; 
		
		Sliding8PuzzleSolver.State startState = puzzle.new State(startStateInput, goalState);
		assertEquals(startState.getState(), startStateInput);
		String[] temp = new String[3];
		temp[0] = "142305678";
		temp[1] = "012345678";
		temp[2] = "120345678";
		
		ArrayList<Sliding8PuzzleSolver.State> temp2 = startState.makeBabies(goalState);
		Iterator<Sliding8PuzzleSolver.State> i = temp2.iterator();
		int k = 0;
		while(i.hasNext()) {
			assertEquals(i.next().getState(),temp[k]); 
			k++;
		}
		
	}
	
	public void testingMake4Babies(){
		Sliding8PuzzleSolver puzzle = new Sliding8PuzzleSolver();
		Sliding8PuzzleSolver.State goalState = puzzle.new State("123456780"); // hardcoded goal state
		
		String startStateInput = "123405678"; 
		
		Sliding8PuzzleSolver.State startState = puzzle.new State(startStateInput, goalState);
		assertEquals(startState.getState(), startStateInput);
		String[] temp = new String[4];
		temp[0] = "103425678";
		temp[1] = "123475608";
		temp[2] = "123045678";
		temp[3] = "123450678";
		
		ArrayList<Sliding8PuzzleSolver.State> temp2 = startState.makeBabies(goalState);
		Iterator<Sliding8PuzzleSolver.State> i = temp2.iterator();
		int k = 0;
		while(i.hasNext()) {
			//System.out.println(i.next().getState() + " - " + temp[k]);
			assertEquals(i.next().getState(),temp[k]);
			k++;
		}
		
	}
	
	public static void main(String args[]) {
		junit.swingui.TestRunner.run(Testing.class);
	}
}
