package nl.tudelft.jpacman.group22;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.google.common.collect.ImmutableList;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;

/**
 * Creates and launches a Multi Level version of the JPacman Game.
 * @author Matthijs
 *
 */
public class MultiLevelLauncher extends Launcher {

	private static final PacManSprites SPRITE_STORE = new PacManSprites();
	
	private MultiLevelGame game;
	
	/**
	 * Creates a new game using the level from {@link #makeLevel()}.
	 * 
	 * @return a new Game.
	 */
	@Override
	public MultiLevelGame makeGame() {
		PlayerFactory pf = new PlayerFactory(SPRITE_STORE);
		
		Level level1 = makeLevel("simplemapgame");
		Level level2 = makeLevel("simplemaplevel2");
		Level level3 = makeLevel("simplemaplevel3");
		Level level4 = makeLevel("simplemaplevel4");
		
		List<Level> levelList = ImmutableList.of(level1, level2, level3, level4);

		Player p = pf.createPacMan();
		game = new MultiLevelGame(p, levelList);
		return game;
	}
	
	@Override
	public MultiLevelGame getGame() {
		return game;
	}
	
	/**
	 * Creates a new level. By default this method will use the map parser to
	 * parse the default board stored in the <code>board.txt</code> resource.
	 * 
	 * @param map is the name of the map given.
	 * @return A new level.
	 */
	public Level makeLevel(String map) {
		MapParser parser = getMapParser();
		try (InputStream boardStream = Launcher.class
				.getResourceAsStream("/" + map + ".txt")) {
			return parser.parseMap(boardStream);
		} catch (IOException e) {
			throw new PacmanConfigurationException("Unable to create level.", e);
		}
	}
	
	/**
	 * Main execution method for the Launcher.
	 * 
	 * @param args
	 *            The command line arguments - which are ignored.
	 * @throws IOException
	 *             When a resource could not be read.
	 */
	public static void main(String[] args) {
		new MultiLevelLauncher().launch();
	}
}
