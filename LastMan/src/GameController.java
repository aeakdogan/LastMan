
public class GameController 
{
	//properties
	private Game game;
	
	//constructor
	public GameController()
	{
		game = new Game();
	}

	//methods
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
		game.setGameMap(new GameMap(mapId));
	}
	
	private boolean isValidTimer(int t)
	{
		if(t > 10 && t < 300)
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
			endGame();
		game.update(elapsed);
	}
	
	public void endGame()
	{
		//create game info for results screen
	}
}
