import java.util.Map;

public class GameMap {

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
}
