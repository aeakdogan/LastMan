import java.util.ArrayList;
/**
 * @author Burcu Canakci
 * Definition: This class is for the character object for LastMan.
 * It is both for bots and player controlled characters.
 *
 */
public class Character
{
	//Constants
	public static final int EASY = -3;
	public static final int MEDIUM = -2;
	public static final int HARD = -1;
	public static final int MOVE_CONSTANT = 4;
	
	//Properties
	private Location location;
	private int hP;
	private int level;
	private int noWeaponOneUsageFor;
	private int noWeaponTwoUsageFor;	
	private int deathTime;
	private Hero hero;
	private Game game;
	
	//Constructor
	/**
	 * Initializes the character for the game.
	 * @param heroId determines which hero this character will be.
	 * @param l determines which level this character will be.
	 * @param g determines the game in which this character will be.
	 */
	public Character(String heroId, int l, Game g) 
	{
		hero = new Hero(heroId);
		hP = hero.getInitialHealthPoints();
		hero.getWeaponTwo().setCharacter(this);
		level = l;
		noWeaponOneUsageFor = 0;
		noWeaponTwoUsageFor = 0;
		game = g;
		location = new Location(0,0);
		deathTime = -1;
	}
	

	//Methods
	/**
	 * sets the weapon one for this character if it is not used.
	 * @return returns true if it sets the weapon one or otherwise
	 */
	public boolean setWeaponOne()
	{
		if(noWeaponOneUsageFor == 0)
		{
			game.getGameMap().addToCurrentWeapons(location, hero.getWeaponOne());
			noWeaponOneUsageFor = Weapon.ONE_DELAY;
			return true;
		}
		return false;	
	}
	
	/**
	 * sets the weapon two for this character if it is not used.
	 * @return returns true if it sets the weapon two or otherwise
	 */
	public boolean setWeaponTwo()
	{
		if(noWeaponTwoUsageFor == 0)
		{
			game.getGameMap().addToCurrentWeapons(location, hero.getWeaponTwo());
			noWeaponTwoUsageFor = hero.getWeaponTwo().getDelayTime();
			return true;
		}
		return false;	
	}
	
	/**
	 * Implements the pack which is taken by the character.
	 * @param p determines what kind of pack is taken
	 * @return is the pack taken or not
	 */
	private boolean implementPack(Pack p)
	{
		if(p.getId().equals("healthBoost"))
		{
			hP += Pack.HEALTH_BOOST;
			return true;
		}
		if(p.getId().equals("weaponBoost"))
		{
			hero.getWeaponOne().setDamage( hero.getWeaponOne().getDamage() + Pack.WEAPON_BOOST);
			return true;
		}
		return false;
	}
	
	public Location getLocation() 
	{
		return location;
	}

	/**
	 * This method is for collecting packs. First it checks if there is a pack in the
	 * current location Then it checks the level of the character. If character is easy 
	 * level it doesn't pick up the packs, if character is medium level it takes the pack 
	 * but doesn't use, if character is hard level it picks up the pack and implements it.
	 * @param location determines the current location
	 */
	public void setLocation(Location location) 
	{
		this.location = location;
		
		ArrayList<Location> packsToCollect = new ArrayList<Location>();		
		if(level!= EASY)
		{
			for(Location loc : game.getGameMap().getCurrentPacks().keySet())
			{
				int highYOfWall = loc.getY();
				int lowYOfWall = loc.getY() + Location.CELL;
				int highYOfChar = location.getY();
				int lowYOfChar = location.getY() + Location.CHAR_CELL;
				int leftXOfWall = loc.getX();
				int rightXOfWall = loc.getX() + Location.CELL;
				int leftXOfChar = location.getX();
				int rightXOfChar = location.getX() + Location.CHAR_CELL;
				
				if(highYOfChar < lowYOfWall && highYOfWall < lowYOfChar 
						&& leftXOfChar < rightXOfWall && leftXOfWall < rightXOfChar)
				{
					packsToCollect.add(loc);
				}
			}
			if(level == HARD)
			{
				for(Location loc: packsToCollect)
				{
					implementPack(game.getGameMap().getPackAt(loc));
				}
			}
			
			for(Location loc: packsToCollect)
				game.getGameMap().removePackAt(loc);
		}
	}

	public int gethP() 
	{
		return hP;
	}

	public void sethP(int hP) 
	{
		if(hP < 0) hP = 0;
		this.hP = hP;
	}

	public int getLevel() 
	{
		return level;
	}

	public void setLevel(int level) 
	{
		this.level = level;
	}

	public int getNoWeaponOneUsageFor() 
	{
		return noWeaponOneUsageFor;
	}

	public void setNoWeaponOneUsageFor(int noWeaponOneUsageFor) 
	{
		if(noWeaponOneUsageFor < 0) noWeaponOneUsageFor = 0;
		this.noWeaponOneUsageFor = noWeaponOneUsageFor;
	}

	public int getNoWeaponTwoUsageFor() 
	{
		return noWeaponTwoUsageFor;
	}

	public void setNoWeaponTwoUsageFor(int noWeaponTwoUsageFor) 
	{
		if(noWeaponTwoUsageFor < 0) noWeaponTwoUsageFor = 0;
		this.noWeaponTwoUsageFor = noWeaponTwoUsageFor;
	}

	public Hero getHero() 
	{
		return hero;
	}

	public void setHero(Hero hero) 
	{
		this.hero = hero;
	}

	public Game getGame() 
	{
		return game;
	}

	public void setGame(Game game) 
	{
		this.game = game;
	}

	public int getDeathTime() 
	{
		return deathTime;
	}

	public void setDeathTime(int deathTime) 
	{
		this.deathTime = deathTime;
	}
}
