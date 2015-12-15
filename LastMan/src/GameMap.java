import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Burcu Canakci
 * This represents the GameMap object of the project. It has a list to its underlying map,
 * list of weapons and packs on the map and it has a reference to its related
 * game object.
 */
public class GameMap 
{	
	//Properties
	private LMap map;
	private HashMap<Location, Weapon> currentWeapons;
	private HashMap<Location, Pack> currentPacks;
	private Game game;
	
	//Constructor
	public GameMap(String mapId, Game game)
	{
		this.game = game;
		map = new LMap(mapId);
		currentWeapons = new HashMap<Location, Weapon>();
		currentPacks = new HashMap<Location, Pack>();
	}
	
	//Methods
	/**
	 * Given the parameters, returns the list of the characters in the affected zone.
	 * @param l is the location of the center of the zone.
	 * @param range is the radius of the zone.
	 * @return is the characters in the zone.
	 */
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
	
	/**
	 * Given the parameters, returns the list of the walls in the affected zone.
	 * @param l is the location of the center of the zone.
	 * @param range is the radius of the zone.
	 * @return is the walls in the zone.
	 */
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
	
	/**
	 * Given a location and weapon, checks if there are any other weapons in the location. 
	 * If not sets the time till explosion of the weapon and adds it to the weapon list.
	 * @param l is the location of the weapon.
	 * @param w is the weapon.
	 * @return represents if the weapon is set or not.
	 */
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
	
	/**
	 * Given a location and pack, checks if there are any other packs in the location. 
	 * If not sets the time till disappearance of the pack and adds it to the pack list.
	 * @param l is the location of the pack.
	 * @param p is the pack.
	 * @return represents if the pack is set or not.
	 */
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
	
	/**
	 * First decrements the times of the characters. Then decrements times of weapons on the map.
	 * Then decrements times of packs on the map. Then calls the internal checkForExplosions() method.
	 * @param elapsed represents the time that passed.
	 */
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
	
	/**
	 * This first removes packs whose times have run out. It then does the same
	 * operation for weapons but by calling the internal explodeWeapon method.
	 */
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
	
	/**
	 * Given a location, this gets a list of affected characters and walls.
	 * It then decrements the health points of the characters, and destroys the SOFT walls in the map.
	 * It finally plays appropriate sound elements.
	 * @param l is the location of the weapon.
	 */
	private void explodeWeapon(Location l)
	{
		Weapon w = currentWeapons.get(l);
		ArrayList<Character> affectedC = getCharactersIn(l, w.getRange());
		ArrayList<Wall> affectedW = getWallsIn(l, w.getRange());
		
		for(Character c : affectedC)
		{
			//Character's own special weapon doesn't hurt the character.
			if(!(!w.isFirstType() && w.getCharacter() == c))
				c.sethP(c.gethP() - w.getDamage());
		}
		
		for(Wall wall : affectedW)
		{
			if(wall.getResistance() == Wall.SOFT)
				map.removeWall(wall);
		}		
			
		if(w.isFirstType())
			game.getView().playSound("sounds\\explosion.wav");
		else
		{
			String filename = "sounds\\" + w.getCharacter().getHero().getId() + "explo.wav";
			game.getView().playSound(filename);
		}
		removeWeaponAt(l);	
	}
	
	/**
	 * Given a location, this first checks if there are any collisions of the location with the wall.
	 * If so it revalidates this location by adjusting it appropriately.
	 * @param newLoc is the location to alter.
	 * @param direction is the direction of the move resulting in this newLoc.
	 * @return is the validated location value.
	 */
	public Location validateLocation(Location newLoc, int direction) 
	{		
		for(Wall w : map.getWalls())
		{
			int highYOfWall = w.getLocation().getY();
			int lowYOfWall = w.getLocation().getY() + Location.CELL;
			int highYOfChar = newLoc.getY();
			int lowYOfChar = newLoc.getY() + Location.CHAR_CELL;
			int leftXOfWall = w.getLocation().getX();
			int rightXOfWall = w.getLocation().getX() + Location.CHAR_CELL;
			int leftXOfChar = newLoc.getX();
			int rightXOfChar = newLoc.getX() + Location.CELL;
			
			if(highYOfChar < lowYOfWall && highYOfWall < lowYOfChar 
					&& leftXOfChar < rightXOfWall && leftXOfWall < rightXOfChar)//intersection between wall and character occurs
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
	
	//Getter and Setters
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
	
	public HashMap<Location, Weapon> getCurrentWeapons() 
	{
		return currentWeapons;
	}

	public void setCurrentWeapons(HashMap<Location, Weapon> currentWeapons) 
	{
		this.currentWeapons = currentWeapons;
	}

	public HashMap<Location, Pack> getCurrentPacks() 
	{
		return currentPacks;
	}

	public void setCurrentPacks(HashMap<Location, Pack> currentPacks) 
	{
		this.currentPacks = currentPacks;
	}
	
	public LMap getMap() 
	{
		return map;
	}

	public void setMap(LMap map) 
	{
		this.map = map;
	}
}