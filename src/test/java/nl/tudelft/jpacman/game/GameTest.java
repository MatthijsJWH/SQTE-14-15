package nl.tudelft.jpacman.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Level.LevelObserver;
import nl.tudelft.jpacman.level.Player;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test class based on the State Transition Tree.
 * @author Matthijs
 *
 */
@SuppressWarnings("visibilitymodifier")
public abstract class GameTest {
	
	protected Launcher launcher;
	protected LevelObserver observer;
	
	/**
	 * Tests if the game starts in the required state.
	 */
	@Test
	public void initialStateTest() {
		Game game = launcher.getGame();
		
		assertFalse(game.isInProgress());
	}
	
	/**
	 * Tests if the game is in the expected state when executing the start event.
	 */
	@Test
	public void startTest() {
		Game game = launcher.getGame();
		
		// execute required events.
		game.start();
		
		assertTrue(game.isInProgress());
	}
	
	/**
	 * Tests if the game is in the expected state after executing the following events:
	 * 	- start.
	 * 	- stop.
	 */	
	@Test
	public void pauseTest() {
		Game game = launcher.getGame();

		// execute required events.
		game.start();
		game.stop();
		
		assertFalse(game.isInProgress());
	}
	
	/**
	 * Tests if the game is in the expected state after executing the following events:
	 * 	- start.
	 * 	- stop.
	 * 	- start.
	 */
	@Test
	public void resumeTest() {
		Game game = launcher.getGame();

		// execute required events.
		game.start();
		game.stop();
		game.start();
		
		assertTrue(game.isInProgress());
	}
	
	/**
	 * Tests if the game is in the expected state after executing the following events:
	 * 	- start.
	 * 	- move.
	 * 
	 * In this case the move is made in such a way that the game is not yet won,
	 * and that the player does not die.
	 */
	@Test
	public void singleMoveTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);

		// execute required events.
		game.start();
		game.move(player, Direction.NORTH);
		
		assertTrue(game.isInProgress());
	}
	
	/**
	 * Tests if the game is in the expected state after the player has collected all the pellets.
	 */
	@Test 
	public void levelWonTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);
		Level level = game.getLevel();
		level.addObserver(observer);

		// execute required events.
		game.start();
		game.move(player, Direction.EAST);
		
		assertFalse(game.isInProgress());
		Mockito.verify(observer).levelWon();
	}
	
	/**
	 * Tests if the game is in the expected state after the player collides with a ghost.
	 */
	@Test
	public void levelLostTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);
		Level level = game.getLevel();
		level.addObserver(observer);

		// execute required events.
		game.start();
		game.move(player, Direction.NORTH);
		game.move(player, Direction.NORTH);
		game.move(player, Direction.WEST);
		game.move(player, Direction.WEST);
		game.move(player, Direction.SOUTH);
		game.move(player, Direction.SOUTH);
		
		assertFalse(game.isInProgress());
		Mockito.verify(observer).levelLost();
	}

}
