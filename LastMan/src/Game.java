import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Burcu Canakci
 * This represents the game object associated with each individual game. 
 * It implements LastManModel and has a GameView associated with it.
 */
public class Game implements LastManModel
{

	//Properties
	private ArrayList<Character> aliveCharacters;
	private ArrayList<Character> deadCharacters;
	private ArrayList<Character> bots;
	private Character player;
	private GameMap gameMap;
	private GameView view;
	private int maxTime;
	private int currentTime;
	private boolean state;//game has started/not started.
	
	//Constructor
	public Game() 
	{
		aliveCharacters = new ArrayList<Character>();
		deadCharacters = new ArrayList<Character>();
		bots = new ArrayList<Character>();
		maxTime = -1;
		state = false;
	}
	
	//Methods
	/**
	 * Given the parameter, creates bots with appropriate levels.
	 * It uses the hero list provided in docs to create random heroes.
	 * @param no
	 * @param level
	 */
	public void createBots(int no, int level)
	{
		for( int i = 0; i < no; i++)
		{
			try 
			{
				Scanner heroInfo;
				heroInfo = new Scanner(new FileReader("docs//herolist.txt"));
				int heroSize = heroInfo.nextInt();
				String[] heroes = new String[heroSize];
				int index = 0;
				while (heroInfo.hasNext())
				{
					heroes[index++] = heroInfo.next();
				}
				heroInfo.close();
				int prob = (int)(Math.random()*(heroSize));
				Character newBot = new Character(heroes[prob], level, this);
				aliveCharacters.add(newBot);
				bots.add(newBot);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}			
		}
	}
	
	/**
	 * This first checks if the game properties are valid. If not returns invalid value.
	 * It then locates players to the corners of the map.
	 * Then it initializes related game properties and returns true for success.
	 * @return is the readiness of the game.
	 */
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
	
	/**
	 * This first increments the currentTime. It then updates the times of the gameMap.
	 * Then it updates the list of alive and dead chaarcters by looking at their hP.
	 * @param elapsed is the time that passed.
	 */
	public void update(int elapsed)
	{
		currentTime += elapsed;
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
	
	/**
	 * This method first changes the state of the game to off. It then calls the GameView's endGame methods.
	 * @param resultScreenInfo is the info required for the results screen.
	 */
	public void endGame(String resultScreenInfo) 
	{
		state = !state;
		view.endGame(resultScreenInfo);
	}
	
	
	//Getter and Setter Methods
	public ArrayList<Character> getBots() 
	{
		return bots;
	}

	public void setBots(ArrayList<Character> bots) 
	{
		this.bots = bots;
	}
	
	public ArrayList<Character> getAliveCharacters() 
	{
		return aliveCharacters;
	}

	public void setAliveCharacters(ArrayList<Character> aliveCharacters) 
	{
		this.aliveCharacters = aliveCharacters;
	}

	public ArrayList<Character> getDeadCharacters() 
	{
		return deadCharacters;
	}

	public void setDeadCharacters(ArrayList<Character> deadCharacters) 
	{
		this.deadCharacters = deadCharacters;
	}

	public Character getPlayer() 
	{
		return player;
	}

	public void setPlayer(Character player) 
	{
		this.player = player;
		aliveCharacters.add(player);
	}

	public GameView getView() 
	{
		return view;
	}

	public void setView(GameView view) 
	{
		this.view = view;
	}

	public int getMaxTime() 
	{
		return maxTime;
	}

	public void setMaxTime(int maxTime) 
	{
		this.maxTime = maxTime;
	}

	public int getCurrentTime() 
	{
		return currentTime;
	}

	public void setCurrentTime(int currentTime) 
	{
		this.currentTime = currentTime;
	}

	public boolean isState() 
	{
		return state;
	}

	public void setState(boolean state) 
	{
		this.state = state;
	}

	public GameMap getGameMap() 
	{
		return gameMap;
	}

	public void setGameMap(GameMap gameMap) 
	{
		this.gameMap = gameMap;
	}
}
