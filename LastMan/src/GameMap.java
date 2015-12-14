import java.util.ArrayList;
import java.util.HashMap;

public class GameMap 
{	
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
			w.setTime(Weapon.TWO_DELAY);
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
		ArrayList<Location> packsToRemove = new ArrayList<Location>();
		
		for( Location l : currentPacks.keySet())
		{
			if (currentPacks.get(l).getTime() == 0)
				packsToRemove.add(l);
		}
		
		for(Location l : packsToRemove)
		{
			removePackAt(l);
		}
		
		ArrayList<Location> weaponsToExplode = new ArrayList<Location>();

		for( Location l : currentWeapons.keySet())
		{
			if (currentWeapons.get(l).getTime() == 0)
			{
				weaponsToExplode.add(l);
			}
		}
		
		for(Location l : weaponsToExplode)
		{
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
			if(!(!w.isFirstType() && w.getCharacter() == c))
				c.sethP(c.gethP() - w.getDamage());
		}
		
		for(Wall wall : affectedW)
		{
			if(wall.getResistance() == Wall.SOFT)
				map.removeWall(wall);
		}		
		removeWeaponAt(l);		
		game.getView().playSound("sounds\\explosion.wav");
	}
	
	public Location validateLocation(Location newLoc, int direction) 
	{		
		for(Wall w : map.getWalls())
		{
			int highYOfWall = w.getLocation().getY();
			int lowYOfWall = w.getLocation().getY() + Location.CELL;
			int highYOfChar = newLoc.getY();
			int lowYOfChar = newLoc.getY() + Location.CELL;
			int leftXOfWall = w.getLocation().getX();
			int rightXOfWall = w.getLocation().getX() + Location.CELL;
			int leftXOfChar = newLoc.getX();
			int rightXOfChar = newLoc.getX() + Location.CELL;
			
			if(highYOfChar < lowYOfWall && highYOfWall < lowYOfChar 
					&& leftXOfChar < rightXOfWall && leftXOfWall < rightXOfChar)
			{
				if(direction == Location.DIRECTION_UP)
				{
						newLoc.setY(lowYOfWall);
						break;
				}
				if(direction == Location.DIRECTION_DOWN)
				{
						newLoc.setY(highYOfWall - Location.CELL);
						break;
				}
				if(direction == Location.DIRECTION_RIGHT)
				{
						newLoc.setX(leftXOfWall - Location.CELL);
						break;
				}
				if(direction == Location.DIRECTION_LEFT)
				{
						newLoc.setX(rightXOfWall);
						break;
				}
			}
		}
		return newLoc;
	}
	
	public HashMap<Location, Weapon> getCurrentWeapons() 
	{
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