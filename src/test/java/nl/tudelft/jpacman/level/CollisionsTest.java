package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.ghost.Ghost;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

/**
 * Test class created for PlayerCollisions.class.
 * @author Matthijs
 *
 */
public class CollisionsTest {

	private Player player;
	private Pellet pellet;
	private Ghost ghost;
	private PlayerCollisions collisions = new PlayerCollisions(); 

	/**
	 * Setup methos, used to mock the different objects needed.
	 */
	@Before
	public void setup() {
		player = Mockito.mock(Player.class);
		pellet = Mockito.mock(Pellet.class);
		ghost = Mockito.mock(Ghost.class);
	}
	
	/**
	 * Tests the collision between a Player and a Pellet.
	 */
	@Test
	public void collidePlayerPelletTest() {
		collisions.collide(player, pellet);
		verify(pellet).leaveSquare();
		verify(player).addPoints(pellet.getValue());
	}
	
	/**
	 * Tests the collision between a Player and a Ghost.
	 */
	@Test
	public void collidePlayerGhostTest() {
		collisions.collide(player, ghost);
		verify(player).setAlive(false);
	}
	
	/**
	 * Tests the collision between a ghost and a player.
	 */
	@Test
	public void collideGhostPlayerTest() {
		collisions.collide(ghost, player);
		verify(player).setAlive(false);
	}
}
