package nl.tudelft.jpacman;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Class in which all test are based on end-to-end test
 * 	stories, given in the assignment.
 * @author Matthijs
 *
 */
@SuppressWarnings("methodlength")
public class ExtendedLauncherSmokeTest {

	private Launcher launcher;
	
	/**
	 * Launch the user interface.
	 */
	@Before
	public void setUpPacman() {
		launcher = new Launcher();
		launcher.launch();
	}
	
	/**
	 * Quit the user interface when we're done.
	 */
	@After
	public void tearDown() {
		launcher.dispose();
	}
	
	/**
	 * Checks if the game launches cleanly.
	 * Based on Story 1.
	 */
	@Test
	public void startTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);
		
		// check if the game starts cleanly.
		assertFalse(game.isInProgress());
		game.start();
		assertTrue(game.isInProgress());
		assertEquals(0, player.getScore());
	}
	
	/**
	 * Test based on Story 2, Scenerio 2.1.
	 */
	@Test
	public void consumePelletTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);
		
		// Start the game cleanly.
		assertFalse(game.isInProgress());
		game.start();
		assertTrue(game.isInProgress());
		assertEquals(0, player.getScore());
		
		// Checks if the player's score has increased after moving to an pellet.
		game.move(player, Direction.EAST);
		assertEquals(10, player.getScore());
		
		// Checks if the pellet dissapeared when moving to the same square.
		game.move(player, Direction.WEST);
		game.move(player, Direction.EAST);
		assertEquals(10, player.getScore());

		// We stop the game cleanly.
		game.stop();
		assertFalse(game.isInProgress());
	}
	
	/**
	 * Test based on Story 2.2.
	 */
	@Test
	public void emptySquareTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);
		
		// Start the game cleanly.
		assertFalse(game.isInProgress());
		game.start();
		assertTrue(game.isInProgress());
		assertEquals(0, player.getScore());
		
		// Get some points.
		game.move(player, Direction.WEST);
		int score = player.getScore();
		
		// Checks if the score stays the same after moving to an empty square.
		game.move(player, Direction.EAST);
		assertEquals(score, player.getScore());
		
		// Stop the game cleanly
		game.stop();
		assertFalse(game.isInProgress());
	}
	
	/**
	 * Test based on Stroy 2.3.
	 * @throws InterruptedException since we wait in the test.
	 */
	@Test 
	public void playerDiesTest() throws InterruptedException {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);
		
		// Start the game cleanly.
		assertFalse(game.isInProgress());
		game.start();
		assertTrue(game.isInProgress());
		assertEquals(0, player.getScore());
		
		// We sleep in this test to let the ghosts move.
		Thread.sleep(500L);
		
		// Move towards, and into a ghost, thus killing ourselfs.
		game.move(player, Direction.WEST);
		for (int i = 0; i < 2; i++) {
			game.move(player, Direction.NORTH);
		}
		for (int i = 0; i < 3; i++) {
			game.move(player, Direction.WEST);
		}
		for (int i = 0; i < 7; i++) {
			game.move(player, Direction.NORTH);
		}
		for (int i = 0; i < 4; i++) {
			game.move(player, Direction.EAST);
		}
		
		// Checks if the player has died after running into a ghost.
		assertFalse(player.isAlive());
		
		// Stop the game cleanly.
		game.stop();
		assertFalse(game.isInProgress());
	}
	
	/**
	 * Test based on Story 2.4.
	 */
	@Test
	public void failingMoveTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);
		
		// Start the game cleanly;
		assertFalse(game.isInProgress());
		game.start();
		assertTrue(game.isInProgress());
		assertEquals(player.getScore(), 0);
		
		// We move as far right as possible without running into a wall.
		for (int i = 0; i < 6; i++) {
			game.move(player, Direction.EAST);
		}
		
		// We save the position of the player before running into the wall.
		Square square = player.getSquare();
		
		// We run into the wall and test if our position has changed.
		game.move(player, Direction.EAST);
		assertEquals(player.getSquare(), square);
		
		// We stop the game cleanly.
		game.stop();
		assertFalse(game.isInProgress());
	}
	
	/**
	 * Test based on Story 4.1.
	 */
	@Test
	public void suspendTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);
		
		// Start the game cleanly.
		assertFalse(game.isInProgress());
		game.start();
		assertTrue(game.isInProgress());
		assertEquals(player.getScore(), 0);
		
		// Checks if the game pauses.
		game.stop();
		assertFalse(game.isInProgress());
	}
	
	/**
	 * Test based on Story 4.2.
	 */
	@Test
	public void resumeTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);
		
		// Start the game cleanly.
		assertFalse(game.isInProgress());
		game.start();
		assertTrue(game.isInProgress());
		assertEquals(player.getScore(), 0);
		
		// Grab some pellets the increase our score.
		for (int i = 0; i < 4; i++) {
			game.move(player, Direction.EAST);
		}
		
		// Save the current score and position.
		int score = player.getScore();
		Square square = player.getSquare();
		
		// Pause the game.
		game.stop();
		assertFalse(game.isInProgress());
		
		// Resume the game.
		game.start();
		assertTrue(game.isInProgress());
		
		// Check if everything stayed the same.
		assertEquals(player.getScore(), score);
		assertEquals(player.getSquare(), square);
		
		// Stop the game cleanly.
		game.start();
		assertTrue(game.isInProgress());
	}
}
