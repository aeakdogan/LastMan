
public class Game{
	private GameMap gameMap;
	private GameView gameView;
	
	public Game(){
		gameMap = new GameMap(new Map());
	}
	
	public GameMap getGameMap(){
		return gameMap;
	}
}
