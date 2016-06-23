package nl.tudelft.jpacman.level;

import org.junit.Test;

/**
 * Test suite created for the class CollisionMap.
 * @author Matthijs
 *
 */
@SuppressWarnings({"checkstyle:javadocmethod"})
abstract class CollisionMapTest {

	private CollisionMap cMap;
	
	public CollisionMap getCollisionMap() {
		return cMap;
	}

	public void setCollisionMap(CollisionMap map) {
		cMap = map;
	}
	
	@Test
	public abstract void collidePlayerPelletTest();
	
	@Test
	public abstract void collidePlayerGhostTest();
	
	@Test
	public abstract void collideGhostPlayerTest();
	
	@Test
	public abstract void collidePelletPelletTest();
	
	@Test
	public abstract void collidePlayerPlayerTest();
	
	@Test
	public abstract void collideGhostGhostTest();
	
	@Test
	public abstract void collideGhostPelletTest();
}
