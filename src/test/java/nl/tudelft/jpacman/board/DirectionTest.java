package nl.tudelft.jpacman.board;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A very simple (and not particularly useful)
 * test class to have a starting point where to put tests.
 * 
 * @author Arie van Deursen
 *
 */
public class DirectionTest {

	/**
	 * Do we get the correct delta when moving north?
	 */
	@Test
	public void testNorth() {
		Direction north = Direction.valueOf("NORTH");
		assertEquals(-1, north.getDeltaY());
	}
	
	/**
	 * Do we get the correct delta when moving north?
	 */
	@Test
	public void testSouth() {
		Direction south = Direction.valueOf("SOUTH");
		assertEquals(1, south.getDeltaY());
	}
	
	/**
	 * Do we get the correct delta when moving north?
	 */
	@Test
	public void testEast() {
		Direction east = Direction.valueOf("EAST");
		assertEquals(1, east.getDeltaX());
	}
	
	/**
	 * Do we get the correct delta when moving north?
	 */
	@Test
	public void testWest() {
		Direction west = Direction.valueOf("WEST");
		assertEquals(-1, west.getDeltaX());
	}

}
