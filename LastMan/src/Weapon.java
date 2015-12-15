/**
 * @author Burcu Canakci
 *
 * Weapon class holds properties of weapons
 * There are 2 different weapon type which are Default Weapon and Special Weapon
 * and this class determines these specifications such as ranges and delay times. 
 *
 * 
 */
public class Weapon 
{
	//Constants
	public final static int ONE_RANGE = 2*Location.CELL;
	public final static int ONE_DELAY = 3;
	public final static int TWO_DELAY = 1;
	
	//Properties
	private int range;
	private int damage;
	private int delayTime;
	private int time;
	private boolean firstType;
	private Character character;
		
	/**
	 * Initializing weapon
	 * @param range determine range of the weapon
	 * @param damage determine damage of the weapon
	 * @param delayTime determine cooldown time of the weapon
	 * @param firstType determine type of the weapon
	 */
	//Constructor
	public Weapon(int range, int damage, int delayTime, boolean firstType) 
	{
		this.range = range;
		this.damage = damage;
		this.delayTime = delayTime;
		this.firstType = firstType;
		character = null;
	}
	
	//Methods	
	public int getRange() 
	{
		return range;
	}

	public void setRange(int range)
	{
		this.range = range;
	}
	
	public int getDamage() 
	{
		return damage;
	}
	
	public void setDamage(int damage) 
	{
		this.damage = damage;
	}
	
	public int getDelayTime() 
	{
		return delayTime;
	}
	
	public void setDelayTime(int delayTime) 
	{
		this.delayTime = delayTime;
	}
	
	public int getTime() 
	{
		return time;
	}
	
	public void setTime(int time) 
	{
		if(time < 0) time = 0;
		this.time = time;
	}
	
	public boolean isFirstType() 
	{
		return firstType;
	}
	
	public void setFirstType(boolean firstType) 
	{
		this.firstType = firstType;
	}

	public Character getCharacter() 
	{
		return character;
	}

	public void setCharacter(Character character)
	{
		this.character = character;
	}
}
