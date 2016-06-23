package nl.tudelft.jpacman.group22;

import java.util.List;

import com.google.common.collect.ImmutableList;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;

/**
 * Class that creates the Multi Level Game for the JPacman Game.
 * @author Matthijs
 *
 */
public class MultiLevelGame extends Game {

	/**
	 * The player of this game.
	 */
	private final Player player;

	/**
	 * The levels of this game.
	 */
	private final List<Level> levelList;
	
	/**
	 * The current level of the game.
	 */
	private int currentLevel; 

	/**
	 * Create a new single player game for the provided level and player.
	 * 
	 * @param p
	 *            The player.
	 * @param l
	 *            The level.
	 */
	protected MultiLevelGame(Player p, List<Level> l) {
		assert p != null;
		assert l != null;

		this.player = p;
		this.levelList = l;
		currentLevel = 0;
		levelList.get(currentLevel).registerPlayer(p);
	}

	@Override
	public List<Player> getPlayers() {
		return ImmutableList.of(player);
	}

	/**
	 * Getter for the levelList.
	 * @return
	 * 			The list of levels.
	 */
	public List<Level> getLevelList() {
		return levelList;
	}
	
	@Override
	public Level getLevel() {
		return levelList.get(currentLevel);
	}
	
	@Override
	public void levelWon() {
		if (currentLevel + 1 < levelList.size()) {
			currentLevel++;
			stop();
			getLevel().registerPlayer(player);
		}
		else {
			stop();
		}
	}
	
}
