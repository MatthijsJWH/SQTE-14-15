package nl.tudelft.jpacman.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.group22.MultiLevelGame;
import nl.tudelft.jpacman.group22.MultiLevelLauncher;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Level.LevelObserver;
import nl.tudelft.jpacman.level.Player;

/**
 * Test suite created for a Multi Level game.
 * @author Matthijs
 *
 */
public class MultiLevelGameTest extends Part2GameTest {

	private MultiLevelLauncher multilauncher;
	
	/**
	 * Setup required launcher and observer.
	 */
	@Before
	public void setUp() {
		launcher = new MultiLevelLauncher();
		multilauncher = (MultiLevelLauncher) launcher;
		observer = Mockito.mock(LevelObserver.class);
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
	 * Tests if the 2nd level starts.
	 */
	@Test
	public void secondLevelStartTest() {
		MultiLevelGame game = multilauncher.getGame();
		Player player = game.getPlayers().get(0);
		Level level = game.getLevel();
		level.addObserver(observer);
		
		// execute required events.
		game.start();
		game.move(player, Direction.EAST);
		game.start();
		
		assertTrue(game.isInProgress());
		Mockito.verify(observer).levelWon();
	}
	
	/**
	 * Tests if the game is suspended after completing the second level.
	 */
	@Test
	public void secondLevelWonTest() {
		MultiLevelGame game = multilauncher.getGame();
		Player player = game.getPlayers().get(0);
		Level level = game.getLevel();
		level.addObserver(observer);
		
		// execute required events.
		game.start();
		game.move(player, Direction.EAST);
		
		level = game.getLevel();
		level.addObserver(observer);
		
		game.start();
		game.move(player, Direction.SOUTH);
		
		assertFalse(game.isInProgress());
		Mockito.verify(observer, Mockito.times(2)).levelWon();
	}
	
	/**
	 * Tests if the 3rd level starts.
	 */
	@Test
	public void thirdLevelStartTest() {
		MultiLevelGame game = multilauncher.getGame();
		Player player = game.getPlayers().get(0);
		Level level = game.getLevel();
		level.addObserver(observer);
		
		// execute required events.
		game.start();
		game.move(player, Direction.EAST);
		
		level = game.getLevel();
		level.addObserver(observer);
		
		game.start();
		game.move(player, Direction.SOUTH);
		game.start();
		
		assertTrue(game.isInProgress());
		Mockito.verify(observer, Mockito.times(2)).levelWon();
	}
	
	/**
	 * Tests if the game is suspended after completing the third level.
	 */
	@Test
	public void thirdLevelWonTest() {
		MultiLevelGame game = multilauncher.getGame();
		Player player = game.getPlayers().get(0);
		Level level = game.getLevel();
		level.addObserver(observer);
		
		// execute required events.
		game.start();
		game.move(player, Direction.EAST);

		level = game.getLevel();
		level.addObserver(observer);
		
		game.start();
		game.move(player, Direction.SOUTH);

		level = game.getLevel();
		level.addObserver(observer);
		
		game.start();
		game.move(player, Direction.WEST);
		
		assertFalse(game.isInProgress());
		Mockito.verify(observer, Mockito.times(3)).levelWon();
	}
	
	/**
	 * Tests if the last level starts.
	 */
	@Test
	public void lastLevelStartTest() {
		MultiLevelGame game = multilauncher.getGame();
		Player player = game.getPlayers().get(0);
		Level level = game.getLevel();
		level.addObserver(observer);
		
		// execute required events.
		game.start();
		game.move(player, Direction.EAST);

		level = game.getLevel();
		level.addObserver(observer);
		
		game.start();
		game.move(player, Direction.SOUTH);

		level = game.getLevel();
		level.addObserver(observer);
		
		game.start();
		game.move(player, Direction.WEST);
		
		level = game.getLevel();
		level.addObserver(observer);
		
		game.start();
		
		assertTrue(game.isInProgress());
		Mockito.verify(observer, Mockito.times(3)).levelWon();
	}
	
	/**
	 * Tests if the last level starts.
	 */
	@Test
	public void lastLevelWonTest() {
		MultiLevelGame game = multilauncher.getGame();
		Player player = game.getPlayers().get(0);
		Level level = game.getLevel();
		level.addObserver(observer);
		
		// execute required events.
		game.start();
		game.move(player, Direction.EAST);

		level = game.getLevel();
		level.addObserver(observer);
		game.start();
		game.move(player, Direction.SOUTH);

		level = game.getLevel();
		level.addObserver(observer);
		game.start();
		game.move(player, Direction.WEST);
		
		level = game.getLevel();
		level.addObserver(observer);
		game.start();
		game.move(player, Direction.NORTH);
		
		assertFalse(game.isInProgress());
		Mockito.verify(observer, Mockito.times(4)).levelWon();
	}
	
	/**
	 * Tests if the game can't be resumed after completing the last level.
	 */
	@Test
	public void lastLevelWonResumeTest() {
		MultiLevelGame game = multilauncher.getGame();
		Player player = game.getPlayers().get(0);
		Level level = game.getLevel();
		level.addObserver(observer);
		
		// execute required events.
		game.start();
		game.move(player, Direction.EAST);
		
		level = game.getLevel();
		level.addObserver(observer);
		game.start();
		game.move(player, Direction.SOUTH);
		
		level = game.getLevel();
		level.addObserver(observer);
		game.start();
		game.move(player, Direction.WEST);
		
		level = game.getLevel();
		level.addObserver(observer);
		game.start();
		game.move(player, Direction.NORTH);
		game.start();
		
		assertFalse(game.isInProgress());
		Mockito.verify(observer, Mockito.times(4)).levelWon();
	}
	
}
