/**
 * @author Burcu Canakci
 * GameController class is one of the most important classes of the project.
 *  It is used to take user input to control the game. This class is responsible for creating and setting
 *  up the game, to organize the game dynamics during the game, to finish a game and to direct the Player
 *  to the Results Screen at the end of the game.
 */
public class GameController 
{
	//Properties
	private Game game;
	private int packTime;
	
	//Constructor
	public GameController()
	{
		game = new Game();
		packTime = 0;
	}

	//Methods
	/**
	 * @param no
	 * @param level
	 * This method creates the bots of the game as specified as the number and level such as: no: 3, 
	 * level: medium.
	 */
	public void charactersSelected(int no, int level)
	{
		game.createBots(no, level);
	}
	
	/**
	 * @param heroId
	 * This method sets the player as the player chosen and its usability of the packs is determined as 
	 * HARD, such that the player can pick up and use the packs in the game. 
	 */
	public void heroSelected(String heroId)
	{
		Character player = new Character(heroId, Character.HARD, game);
		game.setPlayer(player);
	}
	
	/**
	 * @param mapId
	 * This method sets the map to the one that is chosen. 
	 */
	public void mapSelected(String mapId)
	{
		game.setGameMap(new GameMap(mapId, game));
	}
	
	/**
	 * @param t
	 * @returns true if the chosen time is between 30 and 300 seconds, returns false otherwise.
	 */
	private boolean isValidTimer(int t)
	{
		return (t >= 30 && t <= 300);			
	}

	/**
	 * @param t
	 * @returns true if the game time is valid and sets the game time as the decided game time.
	 */
	public boolean timerSelected(int t)
	{
		if (!isValidTimer(t))
			return false;
		game.setMaxTime(t);
		return true;
	}
	
	/**
	 * @returns true if the game is ready and starts the game. 
	 */
	public boolean startGame()
	{
		if(game.isReady())
			return true;
		return false;
	}
	
	/**
	 * @param elapsed
	 * updates the game as time passes. 
	 */
	public void updateGame(int elapsed)
	{
		if(game.getCurrentTime() + 1 == game.getMaxTime() | game.getDeadCharacters().contains(game.getPlayer()) | game.getAliveCharacters().size() == 1)
		{
			endGame();
			return;
		}
		if(++packTime == Pack.RATE)
		{
			int randomX = Location.CELL + (int)(Math.random()*(Location.X_LIMIT - 3*Location.CELL) + 1);
			int randomY = Location.CELL + (int)(Math.random()*(Location.Y_LIMIT - 3*Location.CELL) + 1);
			Location loc = new Location(randomX, randomY);
			int prob = (int)(Math.random()*2);
			Pack newPack;
			if(prob == 0)
			{
				newPack = new Pack("healthBoost");
			}
			else
			{
				newPack = new Pack("weaponBoost");
			}
			packTime = 0;
			game.getGameMap().addToCurrentPacks(loc, newPack);
		}
		game.update(elapsed);
	}
	
	/**
	 * This method is used for determining the players and bots' status at the end of the game. It is used
	 * for viewing the results screen and has the information needed for printing the result of the game. 
	 */
	public void endGame()
	{
		String resultScreenInfo = "";
		for(int i = 0; i < game.getDeadCharacters().size(); i++)
		{
			if(game.getDeadCharacters().get(i) == game.getPlayer())
				resultScreenInfo = "PLAYER: " + game.getPlayer().getHero().getId() + 
				", HP: 0, Death Time: " + game.getPlayer().getDeathTime() + "\n" + resultScreenInfo;
			else
				resultScreenInfo = "BOT: " + game.getDeadCharacters().get(i).getHero().getId() + 
				", HP: 0, Death Time: " + game.getDeadCharacters().get(i).getDeathTime() + "\n" + resultScreenInfo;
		}
		for(int i = 0; i < game.getAliveCharacters().size(); i++)
		{
			if(game.getAliveCharacters().get(i) == game.getPlayer())
				resultScreenInfo = "PLAYER: " + game.getPlayer().getHero().getId() + 
				", HP:" + game.getPlayer().gethP() + ", ALIVE\n" + resultScreenInfo;
			else
				resultScreenInfo = "BOT: " + game.getAliveCharacters().get(i).getHero().getId() + 
				", HP: " + game.getAliveCharacters().get(i).gethP() + ", ALIVE\n" + resultScreenInfo;
		}
		resultScreenInfo = "GAME TIME: " + game.getCurrentTime() + "\n" + resultScreenInfo;
		game.endGame(resultScreenInfo);
	}
	
	public Game getGame() 
	{
		return game;
	}

	public void setGame(Game game) 
	{
		this.game = game;
	}
}
