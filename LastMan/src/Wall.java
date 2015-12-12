public class Wall 
{
	//constants
	public static final int HARD = 2;
	public static final int SOFT = 1;
	
	//properties
	private Location location;
	private int resistance;
	
	//constructor
	public Wall(Location location, int resistance) 
	{
		this.location = location;
		this.resistance = resistance;
	}
	
	//methods
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