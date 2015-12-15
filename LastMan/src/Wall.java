/**
 * @author Burcu Canakci
 * 
 * Wall class holds properties of walls, these properties are location and resistance of wall
 * 	
 */
public class Wall 
{
	//Constants
	public static final int HARD = 2;
	public static final int SOFT = 1;
	
	//Properties
	private Location location;
	private int resistance;
	
	//Constructor
	public Wall(Location location, int resistance) 
	{
		this.location = location;
		this.resistance = resistance;
	}
	
	//Methods
	public Location getLocation() 
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public int getResistance()
	{
		return resistance;
	}

	public void setResistance(int resistance) 
	{
		this.resistance = resistance;
	}
}
