package nl.tudelft.jpacman.board;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Class for testing the Board class.
 * @author Matthijs
 *
 */
public class BoardTest {

	/**
	 * Mandotory test for assignment 0.
	 */
	@Test
	public void test() {
		Square[][] sqs1 = new Square[1][1];
		Square sq = new BasicSquare();
		sqs1[0][0] = sq;
		Board b1 = new Board(sqs1);
		
		assertNotNull(b1);
		assertTrue(b1.invariant());
	}

}
