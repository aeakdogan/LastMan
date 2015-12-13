import java.util.ArrayList;
import java.util.HashMap;

public class GameMap {
	
	//constants
	private static final int X_BOUND = 800;
	private static final int Y_BOUND = 800;
	
	//properties
	private LMap map;
	private HashMap<Location, Weapon> currentWeapons;
	private HashMap<Location, Pack> currentPacks;

	private Game game;
	
	public LMap getMap() {
		return map;
	}

	public void setMap(LMap map) {
		this.map = map;
	}

	//constructor
	public GameMap(String mapId, Game game)
	{
		this.game = game;
		map = new LMap(mapId);
		currentWeapons = new HashMap<Location, Weapon>();
		currentPacks = new HashMap<Location, Pack>();
	}
	
	//methods
	public ArrayList<Character> getCharactersIn(Location l, int range)
	{
		ArrayList<Character> chars = new ArrayList<Character>();
		int distance;
		for(Character c: game.getAliveCharacters())
		{
			distance = (int)Math.sqrt(Math.pow(l.getX() - c.getLocation().getX(), 2) + Math.pow(l.getY() - c.getLocation().getY(), 2));
			if(distance < range)
				chars.add(c);
		}
		return chars;
	}
	
	public ArrayList<Wall> getWallsIn(Location l, int range)
	{
		ArrayList<Wall> walls = new ArrayList<Wall>();
		int distance;
		for(Wall w : map.getWalls())
		{
			distance = (int)Math.sqrt(Math.pow(l.getX() - w.getLocation().getX(), 2) + Math.pow(l.getY() - w.getLocation().getY(), 2));
			if(distance < range)
				walls.add(w);
		}
		return walls;
	}
	public boolean addToCurrentWeapons(Location l, Weapon w)
	{
		for(Location loc : currentWeapons.keySet())
		{
			if (loc.equals(l))
				return false;
		}
		
		if(w.isFirstType())
			w.setTime(w.getDelayTime());
		else
			w.setTime(0);
		currentWeapons.put(l, w);
		return true;
	}
	
	public boolean addToCurrentPacks(Location l, Pack p)
	{
		for(Location loc : currentPacks.keySet())
		{
			if (loc.equals(l))
				return false;
		}
		
		p.setTime(p.getDelayTime());
		currentPacks.put(l, p);
		return true;
	}
	
	public Pack getPackAt(Location l)
	{
		return currentPacks.get(l);
	}
	
	public void removePackAt(Location l)
	{
		currentPacks.remove(l);
	}

	public Weapon getWeaponAt(Location l)
	{
		return currentWeapons.get(l);
	}
	
	public void removeWeaponAt(Location l)
	{
		currentWeapons.remove(l);
	}
	
	public void updateTimes(int elapsed)
	{
		for (Character c : game.getAliveCharacters())
		{
			c.setNoWeaponOneUsageFor(c.getNoWeaponOneUsageFor() - elapsed);
			c.setNoWeaponTwoUsageFor(c.getNoWeaponTwoUsageFor() - elapsed);
		}
		
		for( Location l : currentWeapons.keySet())
		{
			currentWeapons.get(l).setTime(currentWeapons.get(l).getTime() - elapsed);
		}
		
		for( Location l : currentPacks.keySet())
		{
			currentPacks.get(l).setTime(currentPacks.get(l).getTime() - elapsed);
		}
		
		checkForExplosions();			
	}
	
	private void checkForExplosions()
	{
		for( Location l : currentPacks.keySet())
		{
			if (currentPacks.get(l).getTime() == 0)
				removePackAt(l);
		}
		
		for( Location l : currentWeapons.keySet())
		{
			if (currentWeapons.get(l).getTime() == 0)
				explodeWeapon(l);
		}	
	}
	
	private void explodeWeapon(Location l)
	{
		Weapon w = currentWeapons.get(l);
		ArrayList<Character> affectedC = getCharactersIn(l, w.getRange());
		ArrayList<Wall> affectedW = getWallsIn(l, w.getRange());
		
		for(Character c : affectedC)
		{
			c.sethP(c.gethP() - w.getDamage());
		}
		
		for(Wall wall : affectedW)
		{
			if(wall.getResistance() == Wall.SOFT)
				map.removeWall(wall);
		}		
		removeWeaponAt(l);
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
	
	public HashMap<Location, Weapon> getCurrentWeapons() {
		return currentWeapons;
	}

	public void setCurrentWeapons(HashMap<Location, Weapon> currentWeapons) {
		this.currentWeapons = currentWeapons;
	}

	public HashMap<Location, Pack> getCurrentPacks() {
		return currentPacks;
	}

	public void setCurrentPacks(HashMap<Location, Pack> currentPacks) {
		this.currentPacks = currentPacks;
	}
}
