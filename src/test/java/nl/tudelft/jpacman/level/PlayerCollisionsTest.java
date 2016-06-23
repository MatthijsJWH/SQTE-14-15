package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.ghost.Ghost;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Test class created for PlayerCollisions.class.
 * @author Matthijs
 *
 */
public class PlayerCollisionsTest extends CollisionMapTest {

	private Player player;
	private Pellet pellet;
	private Ghost ghost;

	/**
	 * Setup methos, used to mock the different objects needed.
	 */
	@Before
	public void setUp() {
		player = Mockito.mock(Player.class);
		pellet = Mockito.mock(Pellet.class);
		ghost = Mockito.mock(Ghost.class);
		setCollisionMap(new PlayerCollisions());
	}
	
	/**
	 * Tests the collision between a Player and a Pellet.
	 */
	@Test
	public void collidePlayerPelletTest() {
		getCollisionMap().collide(player, pellet);
		verify(pellet).leaveSquare();
		verify(player).addPoints(pellet.getValue());
	}
	
	/**
	 * Tests the collision between a Player and a Ghost.
	 */
	@Test
	public void collidePlayerGhostTest() {
		getCollisionMap().collide(player, ghost);
		verify(player).setAlive(false);
	}
	
	/**
	 * Tests the collision between a ghost and a player.
	 */
	@Test
	public void collideGhostPlayerTest() {
		getCollisionMap().collide(ghost, player);
		verify(player).setAlive(false);
	}
	
	/**
	 * Tests the collision between two pellets.
	 */
	@Test
	public void collidePelletPelletTest() {
		getCollisionMap().collide(pellet, pellet);
		verifyZeroInteractions(player);
		verifyZeroInteractions(pellet);
	}
	
	/**
	 * Tests the collision between two players.
	 */
	@Test
	public void collidePlayerPlayerTest() {
		getCollisionMap().collide(player, player);
		verifyZeroInteractions(player);
	}
	
	/**
	 * Tests the collision between two ghosts.
	 */
	@Test
	public void collideGhostGhostTest() {
		getCollisionMap().collide(ghost, ghost);
		verifyZeroInteractions(player);
	}
	
	/**
	 * Tests the collision between a ghost and a pellet.
	 */
	@Test
	public void collideGhostPelletTest() {
		getCollisionMap().collide(ghost, pellet);
		verifyZeroInteractions(player);
		verifyZeroInteractions(pellet);
	}
}
