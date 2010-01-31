import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;

public class Testing extends TestCase
{

	public void testingMake2Babies()
	{

		Tray testingTray = new Tray("012345678", null, new ArrayList<String>());
		testingTray.makeBabies();
		ArrayList<String> list = testingTray.getBabies();

		assertEquals(list.size(), 2); // 2 children
		assertEquals("102345678", list.get(0)); // Right Child
		assertEquals("312045678", list.get(1)); // Bottom Child

	}

	public void testingMake3Babies()
	{

		Tray testingTray = new Tray("102345678", null, new ArrayList<String>());
		testingTray.makeBabies();
		ArrayList<String> list = testingTray.getBabies();

		assertEquals(list.size(), 3); // 3 children
		assertEquals("012345678", list.get(0)); // Left Child
		assertEquals("120345678", list.get(1)); // Right Child
		assertEquals("142305678", list.get(2)); // Bottom Child
	}

	public void testingMake4Babies()
	{
		Tray testingTray = new Tray("412305678", null, new ArrayList<String>());
		testingTray.makeBabies();
		ArrayList<String> list = testingTray.getBabies();

		assertEquals(list.size(), 4); // 4 children
		assertEquals("412035678", list.get(0)); // Left Child
		assertEquals("412350678", list.get(1)); // Right Child
		assertEquals("402315678", list.get(2)); // Top Child
		assertEquals("412375608", list.get(3)); // Bottom Child
	}

	public static void main(String args[])
	{
		junit.swingui.TestRunner.run(Testing.class);
	}
}
