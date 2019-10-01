package no.ntnu.imt3281.sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {

	@Test
	public void testEmptyConstructor() {
		Main sudoku = new Main();
		assertTrue(sudoku instanceof Main);
	}
}
