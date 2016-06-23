package nl.tudelft.jpacman.level;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.npc.NPC;
import nl.tudelft.jpacman.npc.ghost.Ghost;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;

/**
 * Test class for the MapParser class in the jpacman framework.
 * @author Matthijs Halvemaan
 *
 */
public class MapParserTest {

	private LevelFactory lFactory = Mockito.mock(LevelFactory.class);
	private BoardFactory bFactory = Mockito.mock(BoardFactory.class);
	private Pellet pellet = Mockito.mock(Pellet.class);
	private Ghost ghost = Mockito.mock(Ghost.class);
	private MapParser parser;
	
	/**
	 * Setup method to create the parser.
	 */
	@Before
	public void setUp() {
		parser = new MapParser(lFactory, bFactory); 
	}
	
	/**
	 * Test to see if the createBoard method is called upon.
	 */
	@Test
	public void parseMapBoardTest() {
		char[][] map = {{'P', '#', ' '}};
		parser.parseMap(map);
		verify(bFactory).createBoard((Square[][]) anyObject());
	}
	
	/**
	 * Test to see if the createLevel method is called upon.
	 */
	@SuppressWarnings({"unchecked", "linelength"})
	@Test
	public void parseMapLevelTest() {
		char[][] map = {{'P', '#', ' '}};
		parser.parseMap(map);
		verify(lFactory).createLevel((Board) anyObject(), (List<NPC>) anyObject(), (List<Square>) anyObject());
	}
	
	/**
	 * Test to see if the createWall method is called upon when parsing a wall ('#').
	 */
	@Test
	public void parseWallTest() {
		char[][] map = {{'P', '#', ' '}};
		parser.parseMap(map);
		verify(bFactory).createWall();
	}
	
	/**
	 * Test to see if the createGround method is called upon when parsing a player or a empty space.
	 */
	@Test
	public void parseGroundTest() {
		char[][] map = {{'P', '#', ' '}};
		parser.parseMap(map);
		verify(bFactory, times(2)).createGround();
	}
	
	/**
	 * Test to see if the createPellet method is called upon when parsing a pellet.
	 */
	@Test 
	public void parsePelletTest() {
		char[][] map = {{'P', '#', ' ', '.'}};
		when(lFactory.createPellet()).thenReturn(pellet);
		parser.parseMap(map);
		verify(lFactory).createPellet();
	}
	
	/**
	 * Test to see if the createGround method is called upon when parsing a ghost.
	 */
	@Test
	public void parseGhostBoardTest() {
		char[][] map = {{'P', '#', ' ', 'G'}};
		when(lFactory.createGhost()).thenReturn(ghost);
		parser.parseMap(map);
		verify(bFactory, times(3)).createGround();
	}
	
	/**
	 * Test to see if the createGhost method is called upon when parsing a ghost.
	 */
	@Test
	public void parseGhostLevelTest() {
		char[][] map = {{'P', '#', ' ', 'G'}};
		when(lFactory.createGhost()).thenReturn(ghost);
		parser.parseMap(map);
		verify(lFactory).createGhost();
	}
	
	/**
	 * Tests if an exception is thrown when called upon the default case in parseMap.
	 */
	@Test(expected = PacmanConfigurationException.class)
	public void unknownCharTest() {
		char[][] map = {{'%'}};
		parser.parseMap(map);
	}
	
	/**
	 * Tests if an exception is thrown when the input is null.
	 */
	@Test(expected = PacmanConfigurationException.class)
	public void nullListTest() {
		List<String> map = null;
		parser.parseMap(map);
	}
	
	/**
	 * Tests if an exception is thrown when the input is empty.
	 */
	@Test(expected = PacmanConfigurationException.class)
	public void emptyListTest() {
		List<String> map = new ArrayList<String>();
		parser.parseMap(map);
	}
	
	/**
	 * Tests if an exception is thrown when the string is an empty string.
	 */
	@Test(expected = PacmanConfigurationException.class)
	public void emptyStringTest() {
		List<String> map = new ArrayList<String>();
		map.add("");
		parser.parseMap(map);
	}
	
	/**
	 * Tests if an exception is thrown when the input has strings of different sizes.
	 */
	@Test(expected = PacmanConfigurationException.class)
	public void differentSizesTest() {
		List<String> map = new ArrayList<String>();
		map.add(" # ");
		map.add(" #");
		parser.parseMap(map);
	}
	
	/**
	 * Test to see if no exceptions are thrown in a good weather scenario.
	 * Also test if the map is converted from {@link List<String>} to {@link char[][]} correctly.
	 */
	@Test
	public void stringListTest() {
		List<String> map = new ArrayList<String>();
		map.add(" # ");
		map.add(" ##");
		char[][] testmap = {{' ', '#', ' '},
							{' ', '#', '#'}};
		
		Level level1 = parser.parseMap(map);
		Level level2 = parser.parseMap(testmap);
		
		assertEquals(level2, level1);
	}
	
	/**
	 * Test to see if the correct exception is thrown when we use an invalid inputstream.
	 * 
	 * @throws IOException is the exception we expect to be thrown.
	 */
	@Test(expected = IOException.class)
	public void invalidInputStreamTest() throws IOException {
		InputStream input = new FileInputStream("/test/noPath");
		parser.parseMap(input);
		
		input.close();
	}
	
	/**
	 * Test to see if an map from an inputstream is parsed correctly.
	 * @throws IOException is the exception that is thrown when an incorrect path is used.
	 */
	@Test
	public void inputStreamTest() throws IOException {
		InputStream input = new FileInputStream("src/test/resources/simplemap.txt");
		char[][] testmap = {{'P'}};
		
		Level level1 = parser.parseMap(input);
		Level level2 = parser.parseMap(testmap);
		
		assertEquals(level2, level1);
		
		input.close();
	}
}