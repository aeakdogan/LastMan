/**
 * 
 * @author Burcu Canakci
 * Definition: This class is the location object of the LastMan.
 * It holds properties which hold values for other usage in GameMap, Character
 * Pack etc. It is used for movement of characters, place of the walls and packs etc. 
 *
 */
public class Location 
{
	//Constants
	public static final int BLOCK = 8;
	public static final int CELL = 40;
	public static final int CHAR_CELL = 40;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_LEFT = 3;
	public static final int DIRECTION_DOWN = 4;
	public static final int X_WALL_COUNT = 21;
	public static final int Y_WALL_COUNT = 11;
	public static final int X_LIMIT = CELL * X_WALL_COUNT;
	public static final int Y_LIMIT = CELL * Y_WALL_COUNT;
	
	//Properties
	private int x;
	private int y;
	
	//Constructor
	/**
	 * Initializes the location
	 * @param x horizontal coordinate of the location
	 * @param y vertical coordinate of the location
	 */
	public Location(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	//Methods
	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}
	
	public int getY() 
	{
		return y;
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	
	/**
	 * checks if given location is equal to current location
	 * @param l the given location
	 * @return are locations the same or not?
	 */
	public boolean equals(Location l)
	{
		if(l.getX() == x && l.getY() == y)
			return true;
		return false;
	}
}
