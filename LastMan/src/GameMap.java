import java.util.Map;

public class GameMap {
	
	//constants
	private static final int X_BOUND = 800;
	private static final int Y_BOUND = 800;
	
	//properties
	private Game game;
	private Map<Location, Weapon> currentWeapons;
	private Map<Location, Pack> currentPacks;
	
	//methods
	public void addToCurrentWeapons(Location l, Weapon w)
	{
		currentWeapons.put(l, w);
	}
	
	public Pack getPackAt(Location l)
	{
		return currentPacks.get(l);
	}
	
	public void removePackAt(Location l)
	{
		currentPacks.remove(l);
		game.updateView();
	}

	public Location validateLocation(Location newLoc) 
	{
		if(newLoc.getX() < 0)
			newLoc.setX(0);
		if(newLoc.getY() < 0)
			newLoc.setY(0);
		if(newLoc.getX() > X_BOUND)
			newLoc.setX(X_BOUND);
		if(newLoc.getY() > Y_BOUND)
			newLoc.setY(Y_BOUND);
		
		return newLoc;
	}
}
