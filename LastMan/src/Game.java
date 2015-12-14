import java.util.ArrayList;

public class Game {

	//properties
	private ArrayList<Character> aliveCharacters;
	private ArrayList<Character> deadCharacters;
	private ArrayList<Character> bots;
	private Character player;
	private GameMap gameMap;
	private GameView view;
	private int maxTime;
	private int currentTime;
	private boolean state;
	
	//constructor
	public Game() 
	{
		aliveCharacters = new ArrayList<Character>();
		deadCharacters = new ArrayList<Character>();
		bots = new ArrayList<Character>();
		maxTime = -1;
		state = false;
	}
	
	//methods
	public void createBots(int no, int level)
	{
		for( int i = 0; i < no; i++)
		{
			int prob = 0 + (int)(Math.random()*101);
			String randomHero;
			if(prob < 50)
				randomHero = "kil";
			else
				randomHero = "ayibogan";
			Character newBot = new Character(randomHero, level, this);
			aliveCharacters.add(newBot);
			bots.add(newBot);
		}
	}

	public ArrayList<Character> getBots() {
		return bots;
	}

	public void setBots(ArrayList<Character> bots) {
		this.bots = bots;
	}

	public boolean isReady()
	{
		if(aliveCharacters == null)
			return false;
		if(deadCharacters == null)
			return false;
		if(player == null)
			return false;
		if(gameMap == null)
			return false;
		if(maxTime < 0)
			return false;
		
		for(int i = 0; i < aliveCharacters.size(); i++)
		{
			if(i == 0)
			{
				aliveCharacters.get(i).getLocation().setX(Location.CELL);
				aliveCharacters.get(i).getLocation().setY(Location.CELL);
			}
			if(i == 1)
			{
				aliveCharacters.get(i).getLocation().setX(Location.CELL*(Location.X_WALL_COUNT - 2));
				aliveCharacters.get(i).getLocation().setY(Location.CELL);
			}
			if(i == 2)
			{
				aliveCharacters.get(i).getLocation().setX(Location.CELL);
				aliveCharacters.get(i).getLocation().setY(Location.CELL*(Location.Y_WALL_COUNT - 2));
			}
			if(i == 3)
			{
				aliveCharacters.get(i).getLocation().setX(Location.CELL*(Location.X_WALL_COUNT - 2));
				aliveCharacters.get(i).getLocation().setY(Location.CELL*(Location.Y_WALL_COUNT - 2));
			}
		}
		
		currentTime = 0;
		state = true;
		return true;	
	}
	
	public void update(int elapsed)
	{
		currentTime++;
		gameMap.updateTimes(elapsed);
		for(int i = 0; i < aliveCharacters.size(); i++)
		{
			if(aliveCharacters.get(i).gethP() <= 0)
			{
				aliveCharacters.get(i).setDeathTime(currentTime);
				deadCharacters.add(aliveCharacters.get(i));
				aliveCharacters.remove(aliveCharacters.get(i));
			}
		}
		view.updateView();		
	}
	
	public ArrayList<Character> getAliveCharacters() {
		return aliveCharacters;
	}

	public void setAliveCharacters(ArrayList<Character> aliveCharacters) {
		this.aliveCharacters = aliveCharacters;
	}

	public ArrayList<Character> getDeadCharacters() {
		return deadCharacters;
	}

	public void setDeadCharacters(ArrayList<Character> deadCharacters) {
		this.deadCharacters = deadCharacters;
	}

	public Character getPlayer() {
		return player;
	}

	public void setPlayer(Character player) {
		this.player = player;
		aliveCharacters.add(player);
	}

	public GameView getView() {
		return view;
	}

	public void setView(GameView view) {
		this.view = view;
	}

	public int getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public GameMap getGameMap() {
		return gameMap;
	}

	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}

	public void endGame(String resultScreenInfo) 
	{
		state = !state;
		view.endGame(resultScreenInfo);
	}
}
