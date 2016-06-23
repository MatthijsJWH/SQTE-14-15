package nl.tudelft.jpacman.game;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;

import nl.tudelft.jpacman.group22.MyExtension;
import nl.tudelft.jpacman.level.Level.LevelObserver;

/**
 * Test suite created for a Single Level Game.
 * @author Matthijs
 *
 */
public class SingleLevelGameTest extends Part2GameTest {

	/**
	 * Setup required launcher and observer.
	 */
	@Before
	public void setUp() {
		launcher = new MyExtension();
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
}
