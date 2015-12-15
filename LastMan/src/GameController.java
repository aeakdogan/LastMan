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
	public void charactersSelected(int no, int level)
	{
		game.createBots(no, level);
	}
	
	public void heroSelected(String heroId)
	{
		Character player = new Character(heroId, Character.HARD, game);
		game.setPlayer(player);
	}
	
	public void mapSelected(String mapId)
	{
		game.setGameMap(new GameMap(mapId, game));
	}
	
	private boolean isValidTimer(int t)
	{
		if(t >= 30 && t <= 300)
			return true;
		return false;
	}

	public boolean timerSelected(int t)
	{
		if (!isValidTimer(t))
			return false;
		game.setMaxTime(t);
		return true;
	}
	
	public boolean startGame()
	{
		if(game.isReady())
			return true;
		return false;
	}
	
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
