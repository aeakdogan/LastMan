import java.util.ArrayList;

public class Game {

	//properties
	private ArrayList<Character> aliveCharacters;
	private ArrayList<Character> deadCharacters;
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
		maxTime = -1;
		state = false;
		gameMap = new GameMap("tmp");
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
		}
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
		
		currentTime = 0;
		state = true;
		return true;	
	}
	
	public void update(int elapsed)
	{
		currentTime++;
		gameMap.updateTimes(elapsed);
		updateView();
		for(Character c : aliveCharacters)
		{
			if(c.gethP() <= 0)
			{
				c.setDeathTime(currentTime);
				deadCharacters.add(c);
				aliveCharacters.remove(c);
			}
		}
		
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
	
	public void updateView()
	{
	
	}
}
