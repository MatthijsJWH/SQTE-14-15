package nl.tudelft.jpacman.board;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test class based on Exercise 5 & 6 of Assignment 1.
 * @author Matthijs
 *
 */
@RunWith(Parameterized.class)
public class WithinBordersTest {
	private int x, y;
	private boolean expectedOut;

	/**
	 * Class that runs the withinBordersTest with all parameters shown below.
	 * @param xIn is the x-coördinate.
	 * @param yIn is the y-coördinate.
	 * @param out is the outcome of the test.
	 */
	public WithinBordersTest(int xIn, int yIn, boolean out) {
		this.x = xIn;
		this.y = yIn;
		this.expectedOut = out;	
	}
	
	/**
	 * Test based on Exercise 6 of Assignment 1.
	 * Used to test the withinBorders method of the Board class.
	 */
	@Test
	public void withinBordersTest() {
		// Create an empty square.
		Square[][] grid = new Square[5][5];
		
		// Create a BoardFactory.
		PacManSprites sp = new PacManSprites();
		BoardFactory factory = new BoardFactory(sp);
		
		// Fill the grid.
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				grid[i][j] = factory.createGround();
			}
		}
		
		// Create a new Board.
		Board board = factory.createBoard(grid);
		
		// Test if coördinates x and y are within the borders.
		boolean out = board.withinBorders(x, y);
		// Tests if the outcomes are as expected.
		assertEquals(expectedOut, out);
		
	}
	
	/**
	 * All parameters used in the withinBordersTest.
	 * @return a collection of Parameters.
	 */
	@Parameters
	public static Collection<Object[]> data() {
		Object[][]	values = {
				{ 0, 2, true },
				{ -1, 3, false },
				{ 5, 1, false },
				{ 4, 4, true },
				{ 2, 0, true },
				{ 3, -1, false },
				{ 1, 5, false },
				{ 4, 4, true }
		};
		
		return Arrays.asList(values);
	}

}
