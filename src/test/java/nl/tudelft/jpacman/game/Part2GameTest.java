package nl.tudelft.jpacman.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests based on the "don't care" situations in the state transition table.
 * @author Matthijs
 *
 */
public abstract class Part2GameTest extends GameTest {
	
	/**
	 * Tests if the game's state does not change when executing stop on the start screen.
	 */
	@Test
	public void startScreenStopTest() {
		Game game = launcher.getGame();
		Level level = game.getLevel();
		level.addObserver(observer);
		
		// execute required events.
		game.stop();
		
		assertFalse(game.isInProgress());
		Mockito.verifyZeroInteractions(observer);
	}
	
	/**
	 * Tests if the game's state does not change when executing a move on the start screen.
	 */
	@Test
	public void startScreenMoveTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);

		// execute required events.
		game.move(player, Direction.WEST);
		
		assertFalse(game.isInProgress());
		Mockito.verifyZeroInteractions(observer);
	}
	
	/**
	 * Tests if the game's state does not change when executing start while already in Progress.
	 */
	@Test
	public void inProgressStartTest() {
		Game game = launcher.getGame();

		// execute required events.
		game.start();
		game.start();
		
		assertTrue(game.isInProgress());
		Mockito.verifyZeroInteractions(observer);
	}
	
	/**
	 * Tests if the game's state does not change when executing stop while suspended.
	 */
	@Test
	public void suspendedStopTest() {
		Game game = launcher.getGame();

		// execute required events.
		game.start();
		game.stop();
		game.stop();
		
		assertFalse(game.isInProgress());
		Mockito.verifyZeroInteractions(observer);
	}
	
	/**
	 * Tests if the game's state does not change when executing a move while suspended.
	 */
	@Test
	public void suspendedMoveTest() {
		Game game = launcher.getGame();
		Player player = game.getPlayers().get(0);

		// execute required events.
		game.start();
		game.stop();
		game.move(player, Direction.NORTH);
		
		assertFalse(game.isInProgress());
		Mockito.verifyZeroInteractions(observer);
	}
	
	/**
	 * Tests if the game's state does not change when executing start when the game is over.
	 */
	@Test
	public void gameOverStartTest() {
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
		Mockito.verify(observer, Mockito.times(1)).levelLost();
	}
	
	/**
	 * Tests if the game's state does not change when executing stop when the game is over.
	 */
	@Test
	public void gameOverStopTest() {
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
		Mockito.verify(observer, Mockito.times(1)).levelLost();
	}
	
	/**
	 * Tests if the game's state does not change when executing a move when the game is over.
	 */
	@Test
	public void gameOverMoveTest() {
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
		Mockito.verify(observer, Mockito.times(1)).levelLost();
	}

}
